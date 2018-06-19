package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.ExportsListsMapper;
import com.truck.pojo.ExportsLists;
import com.truck.service.IExportsListsService;
import com.truck.util.DateTimeUtil;
import com.truck.util.Excel;
import com.truck.vo.ExportsListsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service("iExportsListsService")
public class ExportsListsServiceImpl implements IExportsListsService {

    @Autowired
    private ExportsListsMapper exportsListsMapper;

    public ServerResponse add(Integer userId , ExportsLists exportsLists){
        int rowCount = exportsListsMapper.insertSelective(exportsLists);
        if(rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("id",exportsLists.getId());
            return ServerResponse.createBySuccess("新建出口清单成功",result);
        }
        return ServerResponse.createByErrorMessage("新建出口清单失败");
    }


    public ServerResponse bachInsertExports (String path) {
//        String path =  "/Users/jianhe/Desktop/服务费new.xls";
        List<ExportsLists> list;
        list = null;
        int result=0;
        try {
            list = Excel.loadExportsLists(path);
            if (list != null) {
                result = exportsListsMapper.bachInsertExports(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result >0){
            return ServerResponse.createBySuccess("导入成功");
        } else{
            return ServerResponse.createByErrorMessage("导入失败");
        }
    }

    public ServerResponse<String> del(Integer exportsListsId){
        int rowCount = exportsListsMapper.deleteByPrimaryKey( exportsListsId);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("删除出口清单成功");
        }
        return ServerResponse.createByErrorMessage("删除出口清单失败");
    }

    public ServerResponse update(ExportsLists exportsLists){
        int rowCount = exportsListsMapper.updateByPrimaryKeySelective(exportsLists);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新出口清单成功");
        }
        return ServerResponse.createByErrorMessage("更新出口清单失败");
    }

    public ServerResponse<PageInfo> list(Integer adminId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<ExportsLists> exportsListsList =exportsListsMapper.selectAll();
        List<ExportsListsVo> exportsListsVoList = Lists.newArrayList();
        for(ExportsLists exportsListsItem : exportsListsList){
            ExportsListsVo exportsListsVo = assembleExportsListsVo(exportsListsItem);
            exportsListsVoList.add(exportsListsVo);
        }
        PageInfo pageInfo = new PageInfo(exportsListsList);
        pageInfo.setList(exportsListsVoList);
        return ServerResponse.createBySuccess(pageInfo);

    }

    public ExportsListsVo assembleExportsListsVo(ExportsLists exportsLists){
        ExportsListsVo exportsListsVo = new ExportsListsVo();

        exportsListsVo.setId(exportsLists.getId());
        exportsListsVo.setUserId(exportsLists.getUserId());
        exportsListsVo.setCustomsClearance(exportsLists.getCustomsClearance());
        exportsListsVo.setDestination(exportsLists.getDestination());
        exportsListsVo.setPackageNo(exportsLists.getPackageNo());
        exportsListsVo.setSerialNo(exportsLists.getSerialNo());
        exportsListsVo.setPartsNo(exportsLists.getPartsNo());
        exportsListsVo.setPartsName(exportsLists.getPartsName());
        exportsListsVo.setPartsEnName(exportsLists.getPartsEnName());
        exportsListsVo.setUnit(exportsLists.getUnit());
        exportsListsVo.setQuinty(exportsLists.getQuinty());
        exportsListsVo.setBuyPrice(exportsLists.getBuyPrice());
        exportsListsVo.setSalePrice(exportsLists.getSalePrice());
        exportsListsVo.setCartType(exportsLists.getCartType());
        exportsListsVo.setCreateTime(DateTimeUtil.dateToStr(exportsLists.getCreateTime()));
        exportsListsVo.setUpdateTime(DateTimeUtil.dateToStr(exportsLists.getUpdateTime()));
        return exportsListsVo;
    }

}
