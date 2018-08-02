package com.truck.pojo;

import java.util.List;

public class OrderOut {
    private Integer id;

    private Integer orderId;

    private String orderNo;

    private String outNo;

    private List<OutDetail> outDetails;

    public OrderOut(Integer id, Integer orderId, String orderNo, String outNo) {
        this.id = id;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.outNo = outNo;
    }

    public OrderOut() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOutNo() {
        return outNo;
    }

    public void setOutNo(String outNo) {
        this.outNo = outNo == null ? null : outNo.trim();
    }

    public List<OutDetail> getOutDetails() {
        return outDetails;
    }

    public void setOutDetails(List<OutDetail> outDetails) {
        this.outDetails = outDetails;
    }
}