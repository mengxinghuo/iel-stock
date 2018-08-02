package com.truck.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by geely
 */
public class ShopDetailVo {

    private Integer shopId;

    private Integer adminId;

    private String shopName;

    private String shopDesc;

    private String shopEmail;

    private String shopPhone;

    private String shopAddress;

    private String shopHeadimg;

    private String shopFirstimg;

    private List<String> shopSubimg;

    private String createTime;

    private String updateTime;

    private String shopAccount;

    private String shopTaxCard;

    private String shopSppkp;

    private String shopLicence;

    private String shopProxyCertificate;

    private Integer shopStatus;

    private List<String> proxyCertificateList;

    private List<ProductListVo> productListVoList;

    private String postCode;

    private BigDecimal positionLongitude;

    private BigDecimal positionLatitude;


    public List<ProductListVo> getProductListVoList() {
        return productListVoList;
    }

    public void setProductListVoList(List<ProductListVo> productListVoList) {
        this.productListVoList = productListVoList;
    }

    public Integer getShopId() {

        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public List<String> getShopSubimg() {
        return shopSubimg;
    }

    public void setShopSubimg(List<String> shopSubimg) {
        this.shopSubimg = shopSubimg;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopHeadimg() {
        return shopHeadimg;
    }

    public void setShopHeadimg(String shopHeadimg) {
        this.shopHeadimg = shopHeadimg;
    }

    public String getShopFirstimg() {
        return shopFirstimg;
    }

    public void setShopFirstimg(String shopFirstimg) {
        this.shopFirstimg = shopFirstimg;
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

    public String getShopAccount() {
        return shopAccount;
    }

    public void setShopAccount(String shopAccount) {
        this.shopAccount = shopAccount;
    }

    public String getShopTaxCard() {
        return shopTaxCard;
    }

    public void setShopTaxCard(String shopTaxCard) {
        this.shopTaxCard = shopTaxCard;
    }

    public String getShopSppkp() {
        return shopSppkp;
    }

    public void setShopSppkp(String shopSppkp) {
        this.shopSppkp = shopSppkp;
    }

    public String getShopLicence() {
        return shopLicence;
    }

    public void setShopLicence(String shopLicence) {
        this.shopLicence = shopLicence;
    }

    public String getShopProxyCertificate() {
        return shopProxyCertificate;
    }

    public void setShopProxyCertificate(String shopProxyCertificate) {
        this.shopProxyCertificate = shopProxyCertificate;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public List<String> getProxyCertificateList() {
        return proxyCertificateList;
    }

    public void setProxyCertificateList(List<String> proxyCertificateList) {
        this.proxyCertificateList = proxyCertificateList;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public BigDecimal getPositionLongitude() {
        return positionLongitude;
    }

    public void setPositionLongitude(BigDecimal positionLongitude) {
        this.positionLongitude = positionLongitude;
    }

    public BigDecimal getPositionLatitude() {
        return positionLatitude;
    }

    public void setPositionLatitude(BigDecimal positionLatitude) {
        this.positionLatitude = positionLatitude;
    }
}