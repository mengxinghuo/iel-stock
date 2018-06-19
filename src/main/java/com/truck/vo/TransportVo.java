package com.truck.vo;

import com.truck.pojo.Transport;

public class TransportVo {
    private Integer id;

    private Integer declareNum;

    private String destination;

    private String arrivalList;

    private String purchaseList;

    private String salesContract;

    private String invoice;

    private String purchaseContract;

    private String exportCost;

    private String salesList;

    private String entranceCost;

    private Integer status;
    private String statusDesc;

    private String createTime;

    private String updateTime;

    private Transport transport;

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeclareNum() {
        return declareNum;
    }

    public void setDeclareNum(Integer declareNum) {
        this.declareNum = declareNum;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getArrivalList() {
        return arrivalList;
    }

    public void setArrivalList(String arrivalList) {
        this.arrivalList = arrivalList;
    }

    public String getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(String purchaseList) {
        this.purchaseList = purchaseList;
    }

    public String getSalesContract() {
        return salesContract;
    }

    public void setSalesContract(String salesContract) {
        this.salesContract = salesContract;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getPurchaseContract() {
        return purchaseContract;
    }

    public void setPurchaseContract(String purchaseContract) {
        this.purchaseContract = purchaseContract;
    }

    public String getExportCost() {
        return exportCost;
    }

    public void setExportCost(String exportCost) {
        this.exportCost = exportCost;
    }

    public String getSalesList() {
        return salesList;
    }

    public void setSalesList(String salesList) {
        this.salesList = salesList;
    }

    public String getEntranceCost() {
        return entranceCost;
    }

    public void setEntranceCost(String entranceCost) {
        this.entranceCost = entranceCost;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}