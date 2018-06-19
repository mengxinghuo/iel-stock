package com.truck.controller.backend;

import com.truck.common.ServerResponse;
import com.truck.service.IEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/entry/")
public class EntryController {

    @Autowired
    private IEntryService iEntryService;

    /**
     * 查询入库单列表
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_entry_list.do")
    @ResponseBody
    public ServerResponse getEntryList(@RequestParam(value = "status",required = false) Integer status,
                                       @RequestParam(value = "declareNum",required = false) Integer declareNum,
                                       @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageNum",defaultValue = "10") int pageSize){
        return iEntryService.getEntryList(status,declareNum,pageNum,pageSize);
    }

    /**
     * 查询入库详情
     * @param entryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_entry_detail.do")
    @ResponseBody
    public ServerResponse getEntryDetail(Integer entryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageNum",defaultValue = "10") int pageSize){
        return iEntryService.getEntryDetail(entryId,pageNum,pageSize);
    }
}
