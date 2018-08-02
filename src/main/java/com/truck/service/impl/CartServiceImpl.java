package com.truck.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.CartMapper;
import com.truck.dao.StockMapper;
import com.truck.pojo.*;
import com.truck.service.ICartService;
import com.truck.util.BigDecimalUtil;
import com.truck.util.DateTimeUtil;
import com.truck.util.JsonUtil;
import com.truck.util.Post4;
import com.truck.vo.CartVo;
import com.truck.vo.OrderVo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    StockMapper stockMapper;

    public ServerResponse list(Integer adminId) {
        List<CartVo> cartVoList = this.getCartVoLimit(adminId,null);
        return ServerResponse.createBySuccess(cartVoList);
    }

    public ServerResponse baoJia(Integer adminId,String repairNo) {
        if(StringUtils.isBlank(repairNo)){
            return ServerResponse.createByErrorMessage("报修单号不能为空");
        }

        ServerResponse serverResponse2 = this.getCustomerByNo(repairNo);
        if(!serverResponse2.isSuccess()){
            return serverResponse2;
        }
        Customer customer =(Customer)serverResponse2.getData();
        if(customer==null)
        return ServerResponse.createByErrorMessage("报修单号单号有误，请重新输入");

        List<CartVo> cartVoList = this.getCartVoLimit(adminId,customer);
        return ServerResponse.createBySuccess(cartVoList);
    }


    public ServerResponse add(Integer adminId, Integer stockId, Integer count) {
        if (count == null || stockId == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        Stock stock = stockMapper.selectByPrimaryKey(stockId);
        Cart cart = cartMapper.selectCartByAdminIdStockId(adminId, stockId);
        if (cart == null) {
            //这个产品不在这个购物车里，需要新增一个产品记录
            Cart cartItem = new Cart();
            cartItem.setAdminId(adminId);
            cartItem.setAmount(count);
            cartItem.setStockId(stockId);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setCartPrice(stock.getSalesPrice());
            cartMapper.insertSelective(cartItem);
        } else {
            //这个产品已经在购物车中相加产品数量
            count = cart.getAmount() + count;
            if (count > stock.getQuantity()) {
                return ServerResponse.createByErrorMessage("产品库存不足,无法继续加入购物车");
            }
            cart.setAmount(count);
            if(count <=0){
                cartMapper.deleteByPrimaryKey(cart.getCartId());
            }else {
                cartMapper.updateByPrimaryKeySelective(cart);
            }
        }
        return this.list(adminId);
    }

    public ServerResponse<CartVo> update(Integer adminId, Integer count, Integer stockId, BigDecimal cartPrice) {
        if (stockId == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        if (count!=null && count < 1)
            return ServerResponse.createByErrorMessage("数量变化不能为负");
        Cart cart = cartMapper.selectCartByAdminIdStockId(adminId, stockId);
        if (cart != null){
            if (cartPrice !=null  ) {
                if (cartPrice.compareTo(new BigDecimal(0))<0) {
                    return  ServerResponse.createByErrorMessage("单价不可小于0");
                }
                cart.setCartPrice(cartPrice);
                cartMapper.updateByPrimaryKeySelective(cart);
            }
            if(count !=null){
                Integer real= cart.getAmount()+count;
                if(real <=0){
                    cartMapper.deleteByPrimaryKey(cart.getCartId());
                }else{
                    cart.setAmount(count);
                    cartMapper.updateByPrimaryKeySelective(cart);
                }
            }
        }
        return this.list(adminId);
    }


    public ServerResponse<CartVo> selectOrUnSelect(Integer adminId, Integer checked, Integer stockId) {
        cartMapper.checkedOrUncheckedProduct(adminId, stockId, checked);
        return this.list(adminId);
    }


    public ServerResponse<CartVo> deleteProduct(Integer adminId, String stockIds) {
        List<String> stockList = Splitter.on(",").splitToList(stockIds);
        if (CollectionUtils.isEmpty(stockList))
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        cartMapper.deleteByUserIdProductIds(adminId, stockList);

        return this.list(adminId);
    }


    public ServerResponse<Integer> getcartCount(Integer adminId) {
       Integer count = cartMapper.selectCartProductCount(adminId);
        if (count == null) {
            count =0;
        }
        return ServerResponse.createBySuccess(count);
    }

    public ServerResponse cleanCart(Integer adminId) {
        if (adminId == null)
            return ServerResponse.createByErrorMessage("请登录");
           int result =  cartMapper.deleteByAdminId(adminId);
        if (result >0) {
            return ServerResponse.createBySuccess("清空购物车成功");
        }
        return ServerResponse.createBySuccess("清空购物车失败");

    }

    private List<CartVo> getCartVoLimit(Integer adminId,Customer customer) {
        List<CartVo> cartVoList = Lists.newArrayList();
        List<Cart> cartLists = cartMapper.selectCartByAdminId(adminId);

        BigDecimal cartTotalPrice = new BigDecimal("0");
        for (Cart cartList : cartLists) {
            CartVo cartVo = this.assembleCartVo(cartList);
            cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(), cartVo.getCartPrice().doubleValue());
            cartVoList.add(cartVo);
        }
        String baoJiaNo = String.valueOf(this.generateOutNo());
        for (CartVo cartVo : cartVoList) {
            cartVo.setCartTotalPrice(cartTotalPrice);
            cartVo.setCustomer(customer);
            cartVo.setBaoJiaNo(baoJiaNo);
        }
        return cartVoList;
    }

    private long generateOutNo() {
        long currentTime = System.currentTimeMillis();
        return currentTime + new Random().nextInt(100);
    }


    public ServerResponse getCustomerByNo(String repairNo){
        String url = "http://39.104.139.229:8087/order/manage/get_customer.do";
        StringBuffer sb = new StringBuffer();
        sb.append("orderNo=").append(repairNo);
        String str = Post4.connectionUrl(url, sb,null);
        if (str.equals("error")) {
            return ServerResponse.createByErrorMessage("iel服务系统异常，查询服务信息失败");
        }
        JSONObject jsonObject = JSONObject.fromObject(str);
        String statuss = jsonObject.get("status").toString();
        if (statuss.equals("1")) {
            String errMsg = jsonObject.get("msg").toString();
            return ServerResponse.createByErrorMessage(errMsg);
        }
        String Str = jsonObject.get("data").toString();
        Customer customer = JsonUtil.string2Obj(Str,Customer.class);
        return ServerResponse.createBySuccess(customer);
    }


    private CartVo assembleCartVo(Cart cart){
        CartVo cartVo = new CartVo();
        cartVo.setCartId(cart.getCartId());
        cartVo.setAdminId(cart.getAdminId());
        cartVo.setStockId(cart.getStockId());
        cartVo.setAmount(cart.getAmount());
        cartVo.setChecked(cart.getChecked());
        Stock stock = stockMapper.selectByPrimaryKey(cart.getStockId());
        cartVo.setStock(stock);
        cartVo.setCreateTime(DateTimeUtil.dateToStr(cart.getCreateTime()));
        cartVo.setUpdateTime(DateTimeUtil.dateToStr(cart.getUpdateTime()));
        cartVo.setCartPrice(BigDecimalUtil.mul(cart.getCartPrice().doubleValue(),cart.getAmount().doubleValue()));
        return cartVo;
    }



    private boolean getAllCheckedStatus(Integer adminId) {
        if (adminId == null)
            return false;
        return cartMapper.selectCartProductCheckedStatusByUserId(adminId) == 0;
    }
}
