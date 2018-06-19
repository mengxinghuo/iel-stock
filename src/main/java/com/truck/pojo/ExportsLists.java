package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ExportsLists {
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

    private Date createTime;

    private Date updateTime;

    public ExportsLists(Integer id, Integer userId, String customsClearance, String destination, String packageNo, String serialNo, String partsNo, String partsName, String partsEnName, String unit, Integer quinty, BigDecimal buyPrice, BigDecimal salePrice, String cartType, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.customsClearance = customsClearance;
        this.destination = destination;
        this.packageNo = packageNo;
        this.serialNo = serialNo;
        this.partsNo = partsNo;
        this.partsName = partsName;
        this.partsEnName = partsEnName;
        this.unit = unit;
        this.quinty = quinty;
        this.buyPrice = buyPrice;
        this.salePrice = salePrice;
        this.cartType = cartType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ExportsLists() {
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
}