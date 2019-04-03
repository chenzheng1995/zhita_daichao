package com.zhita.model.manage;

public class TopUpAmount {
//    private Integer id;
//
//    private String operationdate;
//
//    private String billingdate;
//
//    private String firm;
//
//    private Integer topupamount;
//
//    private String cashreceipts;
//
//    private String paymentaccount;
//
//    private String contact;
//
//    private String note;
//
//    private String deleted;
//
//    private String company;
//
//    private String firmtype;
//    
//    private String modifyTime;
    
    
    private Integer id;

    private String operationDate;

    private String billingDate;

    private String firm;

    private Integer topUpAmount;

    private String cashReceipts;

    private String paymentAccount;

    private String contact;

    private String note;

    private String deleted;

    private String company;

    private String firmType;
    
    private String modifyTime;

    public TopUpAmount(Integer id, String operationDate, String billingDate, String firm, Integer topUpAmount, String cashReceipts, String paymentAccount, String contact, String note, String deleted, String company, String firmType,String modifyTime) {
        this.id = id;
        this.operationDate = operationDate;
        this.billingDate = billingDate;
        this.firm = firm;
        this.topUpAmount = topUpAmount;
        this.cashReceipts = cashReceipts;
        this.paymentAccount = paymentAccount;
        this.contact = contact;
        this.note = note;
        this.deleted = deleted;
        this.company = company;
        this.firmType = firmType;
        this.modifyTime = modifyTime;
    }

    public TopUpAmount() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getoperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate == null ? null : operationDate.trim();
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate == null ? null : billingDate.trim();
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm == null ? null : firm.trim();
    }

    public Integer getTopUpAmount() {
        return topUpAmount;
    }

    public void setTopUpAmount(Integer topUpAmount) {
        this.topUpAmount = topUpAmount;
    }

    public String getCashReceipts() {
        return cashReceipts;
    }

    public void setCashReceipts(String cashReceipts) {
        this.cashReceipts = cashReceipts == null ? null : cashReceipts.trim();
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount == null ? null : paymentAccount.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getFirmType() {
        return firmType;
    }

    public void setFirmType(String firmType) {
        this.firmType = firmType == null ? null : firmType.trim();
    }
    
    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }
}