package com.truck.controller.backend;

import com.truck.common.ServerResponse;
import com.truck.service.IEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage/inventory/")
public class InventoryController {


    /**
     * 生成盘点
     * @return
     */
    @RequestMapping("gen_inventory_order.do")
    @ResponseBody
    public ServerResponse getEntryList( List stockInventoryList){
        return null;
    }

}
