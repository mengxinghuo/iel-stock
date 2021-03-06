package com.truck.pojo;

import java.util.Date;

public class Entry {
    private Integer id;

    private String entryNo;

    private String declareNum;

    private String destination;

    private Integer status;

    private String inspector;

    private Date createTime;

    private Date updateTime;

    private String shipNum;

    private Integer transportId;

    public Entry(Integer id, String entryNo, String declareNum, String destination, Integer status, String inspector, Date createTime, Date updateTime, String shipNum, Integer transportId) {
        this.id = id;
        this.entryNo = entryNo;
        this.declareNum = declareNum;
        this.destination = destination;
        this.status = status;
        this.inspector = inspector;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shipNum = shipNum;
        this.transportId = transportId;
    }


    public Entry() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(String entryNo) {
        this.entryNo = entryNo == null ? null : entryNo.trim();
    }

    public String getDeclareNum() {
        return declareNum;
    }

    public void setDeclareNum(String declareNum) {
        this.declareNum = declareNum;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector == null ? null : inspector.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getShipNum() {
        return shipNum;
    }

    public void setShipNum(String shipNum) {
        this.shipNum = shipNum;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }
}