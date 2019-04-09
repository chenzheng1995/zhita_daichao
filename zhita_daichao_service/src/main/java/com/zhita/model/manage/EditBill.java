package com.zhita.model.manage;

public class EditBill {
    private Integer id;

    private String operationDate;

    private String account;

    private Integer registrationNumber;

    private Integer price;

    private Integer realPay;

    private String note;

    private String accountType;

    private String firmType;

    private Integer sourceId;

    private Integer businessesId;

    private String modifyTime;

    private String deleted;

    private String registrationTime;
    
    private String company;
    
    private String sourceTo;
    
    private String sourcename;
    
    private Integer topUpAmount; //充值金额
    
    private Integer remainingAmount; //充值金额
    
    private String businessname;
    
    
    

    public EditBill(Integer id, String operationDate, String account, Integer registrationNumber, Integer price, Integer realPay, String note, String accountType, String firmType, Integer sourceId, Integer businessesId, String modifyTime, String deleted, String registrationTime,String company,String sourceTo,String sourcename,Integer topUpAmount,Integer remainingAmount,String businessname) {
        this.id = id;
        this.operationDate = operationDate;
        this.account = account;
        this.registrationNumber = registrationNumber;
        this.price = price;
        this.realPay = realPay;
        this.note = note;
        this.accountType = accountType;
        this.firmType = firmType;
        this.sourceId = sourceId;
        this.businessesId = businessesId;
        this.modifyTime = modifyTime;
        this.deleted = deleted;
        this.registrationTime = registrationTime;
        this.company = company;
        this.sourceTo = sourceTo;
        this.sourcename = sourcename;
        this.topUpAmount = topUpAmount;
        this.remainingAmount = remainingAmount;
        this.businessname = businessname;
    }

    public EditBill() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate == null ? null : operationDate.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRealPay() {
        return realPay;
    }

    public void setRealPay(Integer realPay) {
        this.realPay = realPay;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getFirmType() {
        return firmType;
    }

    public void setFirmType(String firmType) {
        this.firmType = firmType == null ? null : firmType.trim();
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getBusinessesId() {
        return businessesId;
    }

    public void setBusinessesId(Integer businessesId) {
        this.businessesId = businessesId;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime == null ? null : registrationTime.trim();
    }
    
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
    
    public String getSourceTo() {
        return sourceTo;
    }

    public void setSourceTo(String sourceTo) {
        this.sourceTo = sourceTo == null ? null : sourceTo.trim();
    }
    
    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename == null ? null : sourcename.trim();
    }
    
    public Integer getTopUpAmount() {
        return topUpAmount;
    }

    public void setTopUpAmount(Integer topUpAmount) {
        this.topUpAmount = topUpAmount;
    }
    
    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
    
    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname == null ? null : businessname.trim();
    }
}