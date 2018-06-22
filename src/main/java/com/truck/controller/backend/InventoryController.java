package com.truck.controller.backend;

import com.truck.common.ServerResponse;
import com.truck.pojo.StockInventory;
import com.truck.service.IEntryService;
import com.truck.util.FTPUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage/inventory/")
public class InventoryController {


    private static  final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    /**
     * 生成盘点
     * @return
     */
    @RequestMapping("gen_inventory_order.do")
    @ResponseBody
    public ServerResponse getEntryList( String stockInventoryList){

        logger.info("传过来的参数:{}",stockInventoryList);
        /*for (StockInventory stockInventory : stockInventoryList) {
            logger.info("id===:{}",stockInventory.getId());
            logger.info("quantity===:{}",stockInventory.getQuantity());
            logger.info("pandian===:{}",stockInventory.getPandian());
        }*/
        JSONArray json = JSONArray.fromObject(stockInventoryList);
        List<StockInventory> list = JSONArray.toList(json,StockInventory.class);

        if(json!=null||json.size()!=0){
            for(int i=0;i<json.size();i++){
                JSONObject jo = JSONObject.fromObject(json.get(i));
                String id = jo.getString("id");
                String quantity = jo.getString("quantity");
                String pandian = jo.getString("pandian");
                logger.info("id===:{}",id);
            }
        }
        return null;
    }

}
