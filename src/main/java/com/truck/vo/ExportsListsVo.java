package com.truck.vo;

import java.math.BigDecimal;

public class ExportsListsVo {
    private Integer id;

    private Integer userId;

    private String customsClearance;

    private String destination;

    private String packageNo;

    private String serialNo;

    private String partsNo;

    private String partsName;

    private String partsEnName;

    private String unit;

    private Integer quinty;

    private BigDecimal buyPrice;

    private BigDecimal salePrice;

    private String cartType;

    private String createTime;

    private String updateTime;


    public ExportsListsVo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCustomsClearance() {
        return customsClearance;
    }

    public void setCustomsClearance(String customsClearance) {
        this.customsClearance = customsClearance == null ? null : customsClearance.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo == null ? null : packageNo.trim();
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo == null ? null : partsNo.trim();
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName == null ? null : partsName.trim();
    }

    public String getPartsEnName() {
        return partsEnName;
    }

    public void setPartsEnName(String partsEnName) {
        this.partsEnName = partsEnName == null ? null : partsEnName.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getQuinty() {
        return quinty;
    }

    public void setQuinty(Integer quinty) {
        this.quinty = quinty;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType == null ? null : cartType.trim();
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