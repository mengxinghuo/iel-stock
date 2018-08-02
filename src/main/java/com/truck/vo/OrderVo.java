package com.truck.vo;

import com.truck.pojo.Employee;
import com.truck.pojo.OrderOut;
import com.truck.pojo.OutDetail;
import com.truck.pojo.Project;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class OrderVo {

    private Integer orderId;

    private String orderNo;

    private Integer userId;

    private Integer shopId;

    private BigDecimal orderPrice;
    private BigDecimal paymentPrice;

    private Integer paymentType;
    private String paymentTypeDesc;

    private BigDecimal freight;

    private Integer orderStatus;
    private String statusDesc;

    private String paymentTime;

    private String createTime;

    private String updateTime;

    private ShopDetailVo shopDetailVo;

    private List<OrderDetailVo> orderDetailVoList;

    private Integer serviceType;

    private String serviceTypeDesc;

    private Integer serviceProgress;

    private String serviceProgressStr;

    private Set serviceProgressList;

    private Integer productId;

    private String orderName;

    //主机
    private OutDetail outDetail;

    private Integer employeeId;

    private EmployeeVo employeeVo;

    private String outNo;

    //配件
    private List<OrderOut> orderOuts;

    private String confirmWoNo;

    private String confirmDateOne;

    private String confirmContinueWo;

    private String confirmDateTwo;

    private String confirmContinueReason;

    //0 进场   1外出
    private Integer confirmInOut;
    //项目id
    private String confirmJobType;

    private Project projectJobType;

    //员工
    private Integer confirmGavingOrder;

    private Employee employeeGavingOrder;

    private Integer confirmReceiveOrder;

    private Employee employeeReceiveOrder;

    private String doneImg;

    private List<String> doneImgList;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Set getServiceProgressList() {
        return serviceProgressList;
    }

    public void setServiceProgressList(Set serviceProgressList) {
        this.serviceProgressList = serviceProgressList;
    }

    public Integer getServiceProgress() {
        return serviceProgress;
    }

    public void setServiceProgress(Integer serviceProgress) {
        this.serviceProgress = serviceProgress;
    }

    public String getServiceTypeDesc() {
        return serviceTypeDesc;
    }

    public void setServiceTypeDesc(String serviceTypeDesc) {
        this.serviceTypeDesc = serviceTypeDesc;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(BigDecimal paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public List<OrderDetailVo> getOrderDetailVoList() {
        return orderDetailVoList;
    }

    public void setOrderDetailVoList(List<OrderDetailVo> orderDetailVoList) {
        this.orderDetailVoList = orderDetailVoList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public ShopDetailVo getShopDetailVo() {
        return shopDetailVo;
    }

    public void setShopDetailVo(ShopDetailVo shopDetailVo) {
        this.shopDetailVo = shopDetailVo;
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
        this.orderNo = orderNo;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeDesc() {
        return paymentTypeDesc;
    }

    public void setPaymentTypeDesc(String paymentTypeDesc) {
        this.paymentTypeDesc = paymentTypeDesc;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public OutDetail getOutDetail() {
        return outDetail;
    }

    public void setOutDetail(OutDetail outDetail) {
        this.outDetail = outDetail;
    }


    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeVo getEmployeeVo() {
        return employeeVo;
    }

    public void setEmployeeVo(EmployeeVo employeeVo) {
        this.employeeVo = employeeVo;
    }

    public String getOutNo() {
        return outNo;
    }

    public void setOutNo(String outNo) {
        this.outNo = outNo;
    }

    public List<OrderOut> getOrderOuts() {
        return orderOuts;
    }

    public void setOrderOuts(List<OrderOut> orderOuts) {
        this.orderOuts = orderOuts;
    }

    public String getConfirmWoNo() {
        return confirmWoNo;
    }

    public void setConfirmWoNo(String confirmWoNo) {
        this.confirmWoNo = confirmWoNo;
    }

    public String getConfirmContinueWo() {
        return confirmContinueWo;
    }

    public void setConfirmContinueWo(String confirmContinueWo) {
        this.confirmContinueWo = confirmContinueWo;
    }

    public String getConfirmDateOne() {
        return confirmDateOne;
    }

    public void setConfirmDateOne(String confirmDateOne) {
        this.confirmDateOne = confirmDateOne;
    }

    public String getConfirmDateTwo() {
        return confirmDateTwo;
    }

    public void setConfirmDateTwo(String confirmDateTwo) {
        this.confirmDateTwo = confirmDateTwo;
    }

    public String getConfirmContinueReason() {
        return confirmContinueReason;
    }

    public void setConfirmContinueReason(String confirmContinueReason) {
        this.confirmContinueReason = confirmContinueReason;
    }

    public Integer getConfirmInOut() {
        return confirmInOut;
    }

    public void setConfirmInOut(Integer confirmInOut) {
        this.confirmInOut = confirmInOut;
    }

    public String getConfirmJobType() {
        return confirmJobType;
    }

    public void setConfirmJobType(String confirmJobType) {
        this.confirmJobType = confirmJobType;
    }

    public Integer getConfirmGavingOrder() {
        return confirmGavingOrder;
    }

    public void setConfirmGavingOrder(Integer confirmGavingOrder) {
        this.confirmGavingOrder = confirmGavingOrder;
    }

    public Integer getConfirmReceiveOrder() {
        return confirmReceiveOrder;
    }

    public void setConfirmReceiveOrder(Integer confirmReceiveOrder) {
        this.confirmReceiveOrder = confirmReceiveOrder;
    }

    public Project getProjectJobType() {
        return projectJobType;
    }

    public void setProjectJobType(Project projectJobType) {
        this.projectJobType = projectJobType;
    }

    public Employee getEmployeeGavingOrder() {
        return employeeGavingOrder;
    }

    public void setEmployeeGavingOrder(Employee employeeGavingOrder) {
        this.employeeGavingOrder = employeeGavingOrder;
    }

    public Employee getEmployeeReceiveOrder() {
        return employeeReceiveOrder;
    }

    public void setEmployeeReceiveOrder(Employee employeeReceiveOrder) {
        this.employeeReceiveOrder = employeeReceiveOrder;
    }

    public String getServiceProgressStr() {
        return serviceProgressStr;
    }

    public void setServiceProgressStr(String serviceProgressStr) {
        this.serviceProgressStr = serviceProgressStr;
    }

    public String getDoneImg() {
        return doneImg;
    }

    public void setDoneImg(String doneImg) {
        this.doneImg = doneImg;
    }

    public List<String> getDoneImgList() {
        return doneImgList;
    }

    public void setDoneImgList(List<String> doneImgList) {
        this.doneImgList = doneImgList;
    }
}
