package com.truck.controller.backend;


import com.github.pagehelper.PageInfo;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.ExportsLists;
import com.truck.service.IAdminService;
import com.truck.service.IExportsListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/exportsLists/")
public class ExportsListsController {

    @Autowired
    private IExportsListsService iExportsListsService;
    @Autowired
    private IAdminService iAdminService;

    @RequestMapping("bachInsertExports.do")
    @ResponseBody
    public ServerResponse bachInsertExports(HttpSession session, String path) {
        return iExportsListsService.bachInsertExports(path);
    }

  /*  @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, ExportsLists servicesFactor){
       User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iExportsListsService.add(admin.getAdminId(),servicesFactor);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }

    @RequestMapping("del.do")
    @ResponseBody
    public ServerResponse del(HttpSession session, Integer id){
      User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        return iExportsListsService.del(id);
    }*/

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session, ExportsLists servicesFactor){
   /*   User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }*/
        return iExportsListsService.update(servicesFactor);
    }


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpSession session,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
  /*    User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }*/
        return iExportsListsService.list(null,pageNum,pageSize);
    }




}
