package com.truck.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RepertoryVo {
    private Integer id;

    private Integer adminId;

    private Integer parentId;

    private String name;

    private String code;

    private Boolean status;

    private Integer sortOrder;

    private String createTime;

    private String updateTime;

    private BigDecimal positionLongitude;

    private BigDecimal positionLatitude;

    private List<RepertoryVo> repertoryVoList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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

    public List<RepertoryVo> getRepertoryVoList() {
        return repertoryVoList;
    }

    public void setRepertoryVoList(List<RepertoryVo> repertoryVoList) {
        this.repertoryVoList = repertoryVoList;
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