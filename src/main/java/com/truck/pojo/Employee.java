package com.truck.pojo;

import java.util.Date;

public class Employee {
    private Integer id;

    private String no;

    private String name;

    private String manager;

    private String location;

    private String employeeId;

    private String csp;

    private String addressBookTitle;

    private String officeEmail;

    private String companyPhone;

    private Date onBoard;

    private String onBoardStr;

    private String telephone;

    private String defStr;

    private Date createTime;

    private Date updateTime;

    public Employee(Integer id, String no, String name, String manager, String location, String employeeId, String csp, String addressBookTitle, String officeEmail, String companyPhone, Date onBoard, String telephone, String defStr, Date createTime, Date updateTime) {
        this.id = id;
        this.no = no;
        this.name = name;
        this.manager = manager;
        this.location = location;
        this.employeeId = employeeId;
        this.csp = csp;
        this.addressBookTitle = addressBookTitle;
        this.officeEmail = officeEmail;
        this.companyPhone = companyPhone;
        this.onBoard = onBoard;
        this.telephone = telephone;
        this.defStr = defStr;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Employee() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCsp() {
        return csp;
    }

    public void setCsp(String csp) {
        this.csp = csp == null ? null : csp.trim();
    }

    public String getAddressBookTitle() {
        return addressBookTitle;
    }

    public void setAddressBookTitle(String addressBookTitle) {
        this.addressBookTitle = addressBookTitle == null ? null : addressBookTitle.trim();
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail == null ? null : officeEmail.trim();
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public Date getOnBoard() {
        return onBoard;
    }

    public void setOnBoard(Date onBoard) {
        this.onBoard = onBoard;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getDefStr() {
        return defStr;
    }

    public void setDefStr(String defStr) {
        this.defStr = defStr == null ? null : defStr.trim();
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

    public String getOnBoardStr() {
        return onBoardStr;
    }

    public void setOnBoardStr(String onBoardStr) {
        this.onBoardStr = onBoardStr;
    }
}