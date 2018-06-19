package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.EntryDetailMapper;
import com.truck.dao.EntryMapper;
import com.truck.pojo.Entry;
import com.truck.pojo.EntryDetail;
import com.truck.service.IEntryService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.EntryDetailVo;
import com.truck.vo.EntryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("iEntryService")
public class EntryServiceImpl implements IEntryService {

    @Autowired
    private EntryMapper entryMapper;
    @Autowired
    private EntryDetailMapper entryDetailMapper;

    /**
     * 入库单列表
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse getEntryList(Integer status, Integer declareNum, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Entry> entryList = entryMapper.selectAllList(status,declareNum);
        if(entryList.size() == 0){
            return ServerResponse.createByErrorMessage("未查到任何记录");
        }
        List<EntryVo> entryVoList = Lists.newArrayList();
        for(Entry entryItem : entryList){
            EntryVo entryVo = this.assembleEntry(entryItem);
            entryVoList.add(entryVo);
        }
        PageInfo pageInfo = new PageInfo(entryList);
        pageInfo.setList(entryVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse getEntryDetail(Integer entryId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<EntryDetail> entryDetailList = entryDetailMapper.selectEntryDetail(entryId);
        List<EntryDetailVo> entryDetailVoList = Lists.newArrayList();
        for(EntryDetail entryDetailItem : entryDetailList){
            EntryDetailVo entryDetailVo = this.assembleEntryDetail(entryDetailItem);
            entryDetailVoList.add(entryDetailVo);
        }
        PageInfo pageInfo = new PageInfo(entryDetailList);
        pageInfo.setList(entryDetailVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public EntryVo assembleEntry(Entry entry){
        EntryVo entryVo = new EntryVo();
        entryVo.setId(entry.getId());
        entryVo.setEntryNo(entry.getEntryNo());
        entryVo.setDeclareNum(entry.getDeclareNum());
        entryVo.setDestination(entry.getDestination());
        entryVo.setStatus(entry.getStatus());
        entryVo.setStatusDesc(Const.EntryStatusEnum.codeOf(entry.getStatus()).getValue());
        entryVo.setInspector(entry.getInspector());
        entryVo.setCreateTime(DateTimeUtil.dateToStr(entry.getCreateTime()));
        entryVo.setUpdateTime(DateTimeUtil.dateToStr(entry.getUpdateTime()));
        return entryVo;
    }

    public EntryDetailVo assembleEntryDetail(EntryDetail entryDetail){
        EntryDetailVo entryDetailVo = new EntryDetailVo();
        entryDetailVo.setId(entryDetail.getId());
        entryDetailVo.setEntryId(entryDetail.getEntryId());
        entryDetailVo.setCustomsClearance(entryDetail.getCustomsClearance());
        entryDetailVo.setDestination(entryDetail.getDestination());
        entryDetailVo.setPackageNo(entryDetail.getPackageNo());
        entryDetailVo.setSerialNo(entryDetail.getSerialNo());
        entryDetailVo.setPartsNo(entryDetail.getPartsNo());
        entryDetailVo.setPartsName(entryDetail.getPartsName());
        entryDetailVo.setPartsEnName(entryDetail.getPartsEnName());
        entryDetailVo.setUnit(entryDetail.getUnit());
        entryDetailVo.setPurchaseNum(entryDetail.getPurchaseNum());
        entryDetailVo.setPurchasePrice(entryDetail.getPurchasePrice());
        entryDetailVo.setSalesPrice(entryDetail.getSalesPrice());
        entryDetailVo.setDeviceType(entryDetail.getDeviceType());
        if(!StringUtils.isEmpty(entryDetail.getEntryPosition())){
            entryDetailVo.setEntryPosition(entryDetail.getEntryPosition());
            //拼接 位置代码
        }
        if(!StringUtils.isEmpty(entryDetail.getInspectStatus())){
            entryDetailVo.setInspectStatus(entryDetail.getInspectStatus());
        }
        if(!StringUtils.isEmpty(entryDetail.getEntryNum())){
            entryDetailVo.setEntryNum(entryDetail.getEntryNum());
        }
        entryDetailVo.setCreateTime(DateTimeUtil.dateToStr(entryDetail.getCreateTime()));
        entryDetailVo.setUpdateTime(DateTimeUtil.dateToStr(entryDetail.getUpdateTime()));
        return entryDetailVo;
    }
}
