package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.Transport;
import com.truck.service.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/transport/")
public class TransportController {

    @Autowired
    private ITransportService iTransportService;

    /**
     * 出口录入信息
     * @param session
     * @param transport
     * @return
     */
    @RequestMapping("add_transport.do")
    @ResponseBody
    public ServerResponse addTransport(HttpSession session, Transport transport){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iTransportService.addTransport(admin.getAdminId(),transport);
    }

    /**
     * 修改运输信息
     * @param session
     * @param transport
     * @return
     */
    @RequestMapping("update_transport.do")
    @ResponseBody
    public ServerResponse updateTransport(HttpSession session,Transport transport){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iTransportService.updateTransport(admin.getAdminId(),transport);
    }

    /**
     * 进口完善信息
     * @param session
     * @param id
     * @param salesList
     * @param entranceCost
     * @return
     */
    @RequestMapping("consummate_transport.do")
    @ResponseBody
    public ServerResponse consummateTransport(HttpSession session,Integer id,String salesList,String entranceCost){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iTransportService.consummateTransport(admin.getAdminId(),id,salesList,entranceCost);
    }

    /**
     * 查询所有列表，带分页 可根据状态查询
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_all_list.do")
    @ResponseBody
    public ServerResponse getAllList(@RequestParam(value = "status",required = false) Integer status,
                                     @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageNum",defaultValue = "10") int pageSize){
        return iTransportService.getAllList(status,pageNum,pageSize);
    }
}
