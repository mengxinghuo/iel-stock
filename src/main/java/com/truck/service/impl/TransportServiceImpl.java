package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.ServerResponse;
import com.truck.dao.TransportMapper;
import com.truck.pojo.Transport;
import com.truck.service.ITransportService;
import com.truck.vo.TransportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("iTransportService")
public class TransportServiceImpl implements ITransportService {

    @Autowired
    private TransportMapper transportMapper;

    /**
     * 出口 录入信息
     * @param adminId
     * @param transport
     * @return
     */
    public ServerResponse addTransport(Integer adminId, Transport transport){
        if(StringUtils.isEmpty(transport.getDeclareNum())){
            return ServerResponse.createByErrorMessage("请填写报关次数");
        }
        int rowCount = transportMapper.selectByDeclareNum(transport.getDeclareNum());
        if(rowCount > 0){
            return ServerResponse.createByErrorMessage("报关次数已存在");
        }
        if(StringUtils.isEmpty(transport.getDestination())){
            return ServerResponse.createByErrorMessage("请填写目的地");
        }
        if(StringUtils.isEmpty(transport.getArrivalList()) || StringUtils.isEmpty(transport.getPurchaseList()) || StringUtils.isEmpty(transport.getPurchaseContract())
                || StringUtils.isEmpty(transport.getSalesContract()) || StringUtils.isEmpty(transport.getInvoice()) || StringUtils.isEmpty(transport.getExportCost())){
            return ServerResponse.createByErrorMessage("上传信息不足，请完善");
        }
        int resultCount = transportMapper.insertSelective(transport);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("信息录入成功");
        }
        return ServerResponse.createByErrorMessage("信息录入失败");
    }

    /**
     * 修改出口记录
     * @param adminId
     * @param transport
     * @return
     */
    public ServerResponse updateTransport(Integer adminId, Transport transport){
        if(StringUtils.isEmpty(transport.getId())){
            return ServerResponse.createByErrorMessage("请选择要修改的记录");
        }
        if(!StringUtils.isEmpty(transport.getDeclareNum())){
            int rowCount = transportMapper.checkoutDeclareNum(transport.getId(),transport.getDeclareNum());
            if(rowCount > 0){
                return ServerResponse.createByErrorMessage("报关次数已存在");
            }
        }
        //待定判断
        int resultCount = transportMapper.updateByPrimaryKeySelective(transport);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    /**
     * 进口 完善信息
     * @param adminId
     * @param id
     * @param salesList
     * @param entranceCost
     * @return
     */
    public ServerResponse consummateTransport(Integer adminId, Integer id, String salesList, String entranceCost){
        if(StringUtils.isEmpty(id)){
            return ServerResponse.createByErrorMessage("请选择记录");
        }
        if(StringUtils.isEmpty(salesList) || StringUtils.isEmpty(entranceCost)){
            return ServerResponse.createByErrorMessage("信息不完整");
        }
        Transport transport = new Transport();
        transport.setId(id);
        transport.setSalesList(salesList);
        transport.setEntranceCost(entranceCost);
        int resultCount = transportMapper.updateByPrimaryKeySelective(transport);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("完善成功");
        }
        return ServerResponse.createByErrorMessage("完善失败");
    }

    /**
     * 查询列表，带分页
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse getAllList(Integer status,int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Transport> transportList = transportMapper.getAllList(status);
        if(transportList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到任何记录");
        }
        List<TransportVo> transportVoList = Lists.newArrayList();
        for(Transport transportItem : transportList){
            TransportVo transportVo = this.assembleTransport(transportItem);
            transportVoList.add(transportVo);
        }
        PageInfo pageInfo = new PageInfo(transportList);
        pageInfo.setList(transportVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 数据转化
     * @param transport
     * @return
     */
    public TransportVo assembleTransport(Transport transport){
        TransportVo transportVo = new TransportVo();


        return transportVo;
    }
}
