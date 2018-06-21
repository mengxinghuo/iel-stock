package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.EntryDetailMapper;
import com.truck.dao.StockMapper;
import com.truck.dao.RepertoryMapper;
import com.truck.pojo.EntryDetail;
import com.truck.pojo.Stock;
import com.truck.pojo.Repertory;
import com.truck.service.IStockService;
import com.truck.service.IRepertoryService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("iStockService")
public class StockServiceImpl implements IStockService {

    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private EntryDetailMapper entryDetailMapper;
    @Autowired
    private RepertoryMapper repertoryMapper;
    @Autowired
    private IRepertoryService iRepertoryService;

    public ServerResponse batchStockIn(Integer entryId){
        if (entryId == null ) {
            return ServerResponse.createByErrorMessage("入库参数错误");
        }
        List<EntryDetail> entryDetails = entryDetailMapper.selectEntryDetail(entryId);
        for (EntryDetail entryDetail : entryDetails) {
            if (entryDetail.getInspectStatus() == 0 && entryDetail.getEntryNum()==null) {
                return ServerResponse.createByErrorMessage("请确认入库或填写实际数量");
            }
            if (entryDetail.getEntryPosition()==null) {
                return ServerResponse.createByErrorMessage("请选择入库位置");
            }
            Stock stock = new Stock();
            stock = entryDetailToStock(entryDetail);
            int count = stockMapper.insertSelective(stock);
            if(count <= 0){
                return ServerResponse.createByErrorMessage("入库失败");
            }
        }
            return ServerResponse.createBySuccess("入库成功");
    }

    public ServerResponse getStockList(Integer entryId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Stock> stockList = stockMapper.selectByEntryId(entryId);
        if(stockList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到任何记录");
        }
        List<StockVo> stockVoList = Lists.newArrayList();
        for(Stock stockItem : stockList){
            StockVo stockVo = this.assembleStockVo(stockItem);
            stockVoList.add(stockVo);
        }
        PageInfo pageInfo = new PageInfo(stockList);
        pageInfo.setList(stockVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

/*    public ServerResponse updateStockStatus(Integer stockId,Integer inspectStatus){
        if (stockId == null || inspectStatus ==null) {
            return ServerResponse.createByErrorMessage("更新入库详情状态错误");
        }
        Stock stock = stockMapper.selectByPrimaryKey(stockId);
        stock.setInspectStatus(inspectStatus);
        int rowCount = stockMapper.updateByPrimaryKeySelective(stock);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新入库详情状态成功");
        }
        return ServerResponse.createByErrorMessage("更新入库详情状态失败");
    }

    public ServerResponse updateStockNum(Integer stockId,Integer stockNum){
        if (stockId == null || stockNum ==null) {
            return ServerResponse.createByErrorMessage("更新入库详情数量错误");
        }
        Stock stock = stockMapper.selectByPrimaryKey(stockId);
        stock.setStockNum(stockNum);
        int rowCount = stockMapper.updateByPrimaryKeySelective(stock);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新入库详情数量成功");
        }
        return ServerResponse.createByErrorMessage("更新入库详情数量失败");
    }*/

    public StockVo assembleStockVo(Stock stock){
        StockVo stockVo = new StockVo();
        stockVo.setId(stock.getId());
        stockVo.setEntryId(stock.getEntryId());
        stockVo.setCustomsClearance(stock.getCustomsClearance());
        stockVo.setDestination(stock.getDestination());
        stockVo.setPartsNo(stock.getPartsNo());
        stockVo.setPartsName(stock.getPartsName());
        stockVo.setPartsEnName(stock.getPartsEnName());
        stockVo.setUnit(stock.getUnit());
        stockVo.setQuantity(stock.getQuantity());
        stockVo.setSalesPrice(stock.getSalesPrice());
        stockVo.setDeviceType(stock.getDeviceType());
        stockVo.setRepertory(stock.getRepertory());
        stockVo.setPosition(stock.getPosition());
        stockVo.setCreateTime(DateTimeUtil.dateToStr(stock.getCreateTime()));
        stockVo.setUpdateTime(DateTimeUtil.dateToStr(stock.getUpdateTime()));
        return stockVo;
    }
    
    public Stock entryDetailToStock(EntryDetail entryDetail){
        Stock stock = new Stock();
        stock.setEntryId(entryDetail.getEntryId());
        stock.setCustomsClearance(entryDetail.getCustomsClearance());
        stock.setDestination(entryDetail.getDestination());
        stock.setPartsNo(entryDetail.getPartsNo());
        stock.setPartsName(entryDetail.getPartsName());
        stock.setPartsEnName(entryDetail.getPartsEnName());
        stock.setUnit(entryDetail.getUnit());
        if (entryDetail.getEntryNum() !=null){
            stock.setQuantity(entryDetail.getEntryNum());
        }else{
            stock.setQuantity(entryDetail.getPurchaseNum());
        }
        stock.setSalesPrice(entryDetail.getSalesPrice());
        stock.setDeviceType(entryDetail.getDeviceType());
        List<Integer> idList = Lists.newArrayList();
        iRepertoryService.findDeepParentId(idList,entryDetail.getEntryPosition());
        if (idList.size() > 0) {
            stock.setRepertory(idList.get(idList.size()-1));
        }
        stock.setPosition(entryDetail.getEntryPosition());
        return stock;
    }

}
