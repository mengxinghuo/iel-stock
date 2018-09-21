package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.org.apache.regexp.internal.RE;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.EntryDetailMapper;
import com.truck.dao.EntryMapper;
import com.truck.dao.TransportMapper;
import com.truck.pojo.Entry;
import com.truck.pojo.EntryDetail;
import com.truck.pojo.Transport;
import com.truck.service.ITransportService;
import com.truck.util.DateTimeUtil;
import com.truck.util.FTPUtil;
import com.truck.util.Post4;
import com.truck.vo.TransportVo;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service("iTransportService")
public class TransportServiceImpl implements ITransportService {

    @Autowired
    private TransportMapper transportMapper;
    @Autowired
    private EntryMapper entryMapper;
    @Autowired
    private EntryDetailMapper entryDetailMapper;

    private static  final Logger logger = LoggerFactory.getLogger(TransportServiceImpl.class);

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
        /*if(StringUtils.isEmpty(transport.getArrivalList()) || StringUtils.isEmpty(transport.getPurchaseList()) || StringUtils.isEmpty(transport.getPurchaseContract())
                || StringUtils.isEmpty(transport.getSalesContract()) || StringUtils.isEmpty(transport.getInvoice()) || StringUtils.isEmpty(transport.getExportCost())){
            return ServerResponse.createByErrorMessage("上传信息不足，请完善");
        }*/

        transport.setStatus(Const.TransportStatusEnum.OVER_EXIT.getCode());

        String url = "http://localhost:8086/manage/transport/add_transport.do";
        ServerResponse errMsg = syncZhuJiTransport(url,transport);
        if (errMsg != null) return errMsg;

        if(!StringUtils.isEmpty(transport.getCreateTimeStr())){
            transport.setCreateTime(DateTimeUtil.strToDate(transport.getCreateTimeStr(),"yyyy-MM-dd"));
        }else{
            transport.setCreateTime(new Date());
        }
        int resultCount = transportMapper.insertSelective(transport);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("信息录入成功");
        }
        return ServerResponse.createByErrorMessage("信息录入失败");
    }

    private ServerResponse syncZhuJiTransport(String url,Transport transport) {
        StringBuffer sb = new StringBuffer();
        JSONObject json = JSONObject.fromObject(transport);
        sb.append("transport=").append(json.toString());
        logger.info("配件transport===json======{}",json.toString());
        String str = Post4.connectionUrl(url, sb,null);
        JSONObject jsonObject = JSONObject.fromObject(str);
        if (str.equals("error")) {
            return ServerResponse.createByErrorMessage("主机系统异常");
        }
        String status = jsonObject.get("status").toString();
        if (status.equals("1")) {
            String errMsg = jsonObject.get("msg").toString();
            return ServerResponse.createByErrorMessage(errMsg);
        }
        logger.info("主机系统增加成功");
        return null;
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
   /*     Transport transport1 = transportMapper.selectByPrimaryKey(transport.getId());
        if (transport1 != null) {
            if(org.apache.commons.lang3.StringUtils.isNotBlank(transport1.getSalesList()) ||
                    org.apache.commons.lang3.StringUtils.isNotBlank(transport1.getEntranceCost())){
                return ServerResponse.createByErrorMessage("进口已经在使用该记录，无法修改");
            }
        }*/
        if(!StringUtils.isEmpty(transport.getDeclareNum())){
            int rowCount = transportMapper.checkoutDeclareNum(transport.getId(),transport.getDeclareNum());
            if(rowCount > 0){
                return ServerResponse.createByErrorMessage("报关次数已存在");
            }
            //修改报关次数和船次
            Transport search = transportMapper.selectByPrimaryKey(transport.getId());
            //修改配件入库单
            ServerResponse serverResponse = this.updateEntry(search.getDeclareNum(),transport.getDeclareNum(),transport.getShipNum());
            if(!serverResponse.isSuccess()){
                return serverResponse;
            }
            //修改主机入库单
            String url = "http://localhost:8086/manage/transport/update_entry.do";
            StringBuffer sb = new StringBuffer();
            sb.append("oldDeclareNum=").append(search.getDeclareNum()).append("&declareNum=").append(transport.getDeclareNum()).append("&shipNum=").append(transport.getShipNum());
            String str = Post4.connectionUrl(url, sb,null);
            JSONObject jsonObject = JSONObject.fromObject(str);
            if (str.equals("error")) {
                return ServerResponse.createByErrorMessage("系统异常");
            }
            String status = jsonObject.get("status").toString();
            if(status.equals("1")){
                return ServerResponse.createByErrorMessage("主机入库单同步失败失败");
            }

        }
        //待定判断  修改判定  如果已经入库就不能进行修改了  对应的入库单
        String url = "http://localhost:8086/manage/transport/update_transport.do";
        ServerResponse errMsg = syncZhuJiTransport(url,transport);
        if (errMsg != null) return errMsg;

        if(!StringUtils.isEmpty(transport.getCreateTimeStr())){
            transport.setCreateTime(DateTimeUtil.strToDate(transport.getCreateTimeStr(),"yyyy-MM-dd"));
        }
        int resultCount = transportMapper.updateByPrimaryKeySelective(transport);
        if(resultCount > 0){
            Transport search = transportMapper.selectByPrimaryKey(transport.getId());
            if(StringUtils.isEmpty(transport.getSalesList()) && StringUtils.isEmpty(transport.getDeclareNum())){
                this.checkEntryByDeclareNum(search.getDeclareNum());
            }
            if(StringUtils.isEmpty(transport.getZhuJiSalesList()) && StringUtils.isEmpty(transport.getDeclareNum())){
                this.checkZhuJiEntryByDeclareNum(search.getDeclareNum());
            }
            return ServerResponse.createBySuccess("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    public ServerResponse updateEntry(String oldDeclareNum, String declareNum, String shipNum){
        Entry search = entryMapper.selectByDeclareNum(oldDeclareNum);
        if(search == null){
            return ServerResponse.createBySuccess();
        }
        Entry entry = new Entry();
        entry.setId(search.getId());
        entry.setDeclareNum(declareNum);
        entry.setShipNum(shipNum);
        int resultCount = entryMapper.updateByPrimaryKeySelective(entry);
        if(resultCount > 0){
            return ServerResponse.createBySuccess();
        }else{
            return ServerResponse.createByErrorMessage("数据异常入库单更改失败");
        }
    }

    /**
     * 删除出口记录
     * @param adminId
     * @param id
     * @return
     */
    public ServerResponse delTransport(Integer adminId, Integer id){
        if(StringUtils.isEmpty(id)){
            return ServerResponse.createByErrorMessage("请选择要删除的记录");
        }
/*        Transport transport = transportMapper.selectByPrimaryKey(id);
        if (transport != null) {
            if(org.apache.commons.lang3.StringUtils.isNotBlank(transport.getSalesList()) ||
                    org.apache.commons.lang3.StringUtils.isNotBlank(transport.getEntranceCost())){
                return ServerResponse.createByErrorMessage("进口已经在使用该记录，无法删除");
            }
        }*/
        //待定判断

        String url = "http://localhost:8086/manage/transport/del_transport.do";
        StringBuffer sb = new StringBuffer();
        sb.append("id=").append(id);
        String str = Post4.connectionUrl(url, sb,null);
        JSONObject jsonObject = JSONObject.fromObject(str);
        if (str.equals("error")) {
            return ServerResponse.createByErrorMessage("主机系统异常");
        }
        String status = jsonObject.get("status").toString();
        if (status.equals("1")) {
            String errMsg = jsonObject.get("msg").toString();
            return ServerResponse.createByErrorMessage(errMsg);
        }

        int resultCount = transportMapper.deleteByPrimaryKey(id);
        if(resultCount > 0){
//            entryMapper.deleteByPrimaryKey();
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 进口 完善信息
     * @param id
     * @param salesList
     * @return
     */
    public ServerResponse consummateTransport( Integer id, String salesList){
        if(StringUtils.isEmpty(id)){
            return ServerResponse.createByErrorMessage("请选择记录");
        }
        if(StringUtils.isEmpty(salesList)){
            return ServerResponse.createByErrorMessage("信息不完整");
        }
        Transport search = transportMapper.selectByPrimaryKey(id);
        Transport transport = new Transport();
        transport.setId(id);
        transport.setSalesList(salesList);
        if(StringUtils.isEmpty(search.getZhuJiSalesList())){
            transport.setStatus(Const.TransportStatusEnum.CONFIRM.getCode());
        }else{
            transport.setStatus(Const.TransportStatusEnum.OVER_CONFIRM.getCode());
        }
        int resultCount = transportMapper.updateByPrimaryKeySelective(transport);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("完善成功");
        }
        return ServerResponse.createByErrorMessage("完善失败");
    }

    public ServerResponse hostTransport( Integer id, String zhuJiList){
        if(StringUtils.isEmpty(id)){
            return ServerResponse.createByErrorMessage("请选择记录");
        }
        if(StringUtils.isEmpty(zhuJiList)){
            return ServerResponse.createByErrorMessage("信息不完整");
        }
        Transport search = transportMapper.selectByPrimaryKey(id);
        Transport transport = new Transport();
        transport.setId(id);
        transport.setZhuJiSalesList(zhuJiList);
        if(StringUtils.isEmpty(search.getZhuJiSalesList())){
            transport.setStatus(Const.TransportStatusEnum.CONFIRM.getCode());
        }else{
            transport.setStatus(Const.TransportStatusEnum.OVER_CONFIRM.getCode());
        }
        int resultCount = transportMapper.updateByPrimaryKeySelective(transport);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("上传成功");
        }
        return ServerResponse.createByErrorMessage("上传失败");
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
     * 创建入库单
     * @param id
     * @return
     */
    public ServerResponse createEntry(Integer id){
        logger.info("id==============={}",id);
        Transport transport = transportMapper.selectByPrimaryKey(id);
        logger.info("mmmm======{}",transport.getDeclareNum());
        int rowCount = entryMapper.checkoutDeclare(transport.getDeclareNum());
        if(rowCount > 0){
            return ServerResponse.createByErrorMessage("已存在");
            //业务待定
        }
        String entryNo = String.valueOf(this.generateEntryNo());
        Entry entry = new Entry();
        entry.setEntryNo(entryNo);
        entry.setDeclareNum(transport.getDeclareNum());
        entry.setDestination(transport.getDestination());
        entry.setStatus(Const.EntryStatusEnum.STANDBY.getCode());
        entry.setShipNum(transport.getShipNum());
        entry.setCreateTime(transport.getCreateTime());
        entry.setTransportId(id);
        int resultCount = entryMapper.insertSelective(entry);
        if(resultCount > 0){
            return ServerResponse.createBySuccess(entry.getId());
        }
        return ServerResponse.createByErrorMessage("创建失败");
    }

    public ServerResponse createHostEntry(Integer id){
        Transport transport = transportMapper.selectByPrimaryKey(id);
        logger.info("transport======{}",transport.toString());
        /*int rowCount = entryMapper.checkoutDeclare(transport.getDeclareNum());
        if(rowCount > 0){
            return ServerResponse.createByErrorMessage("已存在");
            //业务待定
        }*/
        String entryNo = String.valueOf(this.generateEntryNo());
        Entry entry = new Entry();
        entry.setEntryNo(entryNo);
        entry.setDeclareNum(transport.getDeclareNum());
        entry.setDestination(transport.getDestination());
        entry.setStatus(Const.EntryStatusEnum.STANDBY.getCode());
        entry.setShipNum(transport.getShipNum());
//        //报关时间
//        entry.setCreateTime(transport.getCreateTime());
        entry.setTransportId(id);
        JSONObject json = JSONObject.fromObject(entry);

        String url = "http://localhost:8086/manage/entry/create_entry.do";
        StringBuffer sb = new StringBuffer();
        logger.info("entryStr======{}",json.toString());
        sb.append("entryStr=").append(json.toString());
        String str = Post4.connectionUrl(url, sb,null);
        if (str.equals("error")) {
            return ServerResponse.createByErrorMessage("系统异常");
        }
        JSONObject jsonObject = JSONObject.fromObject(str);
        String status = jsonObject.get("status").toString();
        if(status.equals("0")){
            String Str = jsonObject.get("data").toString();
            return ServerResponse.createBySuccess(Integer.parseInt(Str));
        }
        return ServerResponse.createByErrorMessage("创建失败");
    }

    public ServerResponse checkEntryByDeclareNum(String declareNum){
        Entry entry = entryMapper.selectByDeclareNum(declareNum);
        if(entry != null){
            if(Const.EntryStatusEnum.FINISH.getCode() < entry.getStatus()){
                return ServerResponse.createByErrorMessage("本单已入库，无法替换");
            }
            entryDetailMapper.deleteByEntryId(entry.getId());
            List<EntryDetail> entryDetailList = entryDetailMapper.selectEntryDetail(entry.getId());
            if(entryDetailList.size() == 0){
                entryMapper.deleteByPrimaryKey(entry.getId());
                return ServerResponse.createBySuccess();
            }else{
                return ServerResponse.createByErrorMessage("清除失败");
            }
        }
        return ServerResponse.createBySuccess();
    }

    public ServerResponse checkZhuJiEntryByDeclareNum(String declareNum){
        String url = "http://localhost:8086/manage/transport/check_entry_by_declare_num.do";
        StringBuffer sb = new StringBuffer();
        sb.append("declareNum=").append(declareNum);
        String str = Post4.connectionUrl(url, sb,null);
        JSONObject jsonObject = JSONObject.fromObject(str);
        if (str.equals("error")) {
            return ServerResponse.createByErrorMessage("系统异常");
        }
        String status = jsonObject.get("status").toString();
        logger.info("status========={}:",status);
        if(status.equals("0")){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("清除失败");
    }

    @Override
    public ServerResponse<Transport> getTransByEntry(Entry entry) {
        Transport transport = transportMapper.selectByEntry(entry);
        if(transport==null)
            return ServerResponse.createByErrorMessage("配件系统找不到进出口信息");
        return ServerResponse.createBySuccess(transport);
    }

    private long generateEntryNo(){
        long currentTime =System.currentTimeMillis();
        return currentTime+new Random().nextInt(100);
    }

    /**
     * 数据转化
     * @param transport
     * @return
     */
    public TransportVo assembleTransport(Transport transport){
        TransportVo transportVo = new TransportVo();
        transportVo.setId(transport.getId());
        transportVo.setDeclareNum(transport.getDeclareNum());
        transportVo.setDestination(transport.getDestination());
        if(!StringUtils.isEmpty(transport.getArrivalList())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getArrivalList());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setArrivalList(mapList);
        }
        if(!StringUtils.isEmpty(transport.getPurchaseList())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getPurchaseList());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setPurchaseList(mapList);
        }
        if(!StringUtils.isEmpty(transport.getSalesContract())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getSalesContract());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setSalesContract(mapList);
        }
        if(!StringUtils.isEmpty(transport.getInvoice())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getInvoice());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setInvoice(mapList);
        }
        if(!StringUtils.isEmpty(transport.getPurchaseContract())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getPurchaseContract());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setPurchaseContract(mapList);
        }
        if(!StringUtils.isEmpty(transport.getExportCost())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getExportCost());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setExportCost(mapList);
        }
        if(!StringUtils.isEmpty(transport.getEntranceCost())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getEntranceCost());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setEntranceCost(mapList);
        }
        if(!StringUtils.isEmpty(transport.getSalesList())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getSalesList());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setSalesList(mapList);
        }
        if(!StringUtils.isEmpty(transport.getZhuJiSalesList())){
            List<Map>  mapList = Lists.newArrayList();
            List<String> list = Splitter.on(",").splitToList(transport.getZhuJiSalesList());
            for (String str : list) {
                Map map = Maps.newHashMap();
                String name = getFileName(str);
                map.put("name",name);
                map.put("url",str);
                mapList.add(map);
            }
            transportVo.setZhuJiSalesList(mapList);
        }
        transportVo.setStatus(transport.getStatus());
        transportVo.setStatusDesc(Const.TransportStatusEnum.codeOf(transport.getStatus()).getValue());
        transportVo.setCreateTime(DateTimeUtil.dateToStr(transport.getCreateTime(),"yyyy-MM-dd"));
        transportVo.setUpdateTime(DateTimeUtil.dateToStr(transport.getUpdateTime()));
        transportVo.setShipNum(transport.getShipNum());
        transportVo.setUrlPeiJian("http://cdn.ayotrust.com/upload/配件入库模板.xls");
        transportVo.setUrlZhuJi("http://cdn.ayotrust.com/upload/主机入库模板.xls");
        transportVo.setCreateTimeStr(DateTimeUtil.dateToStr(transport.getCreateTime(),"yyyy-MM-dd"));

        return transportVo;
    }

    public String getFileName(String url){
        String fileName = url.substring(url.lastIndexOf("/") +1);
        return fileName;
    }
}
