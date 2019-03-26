package com.zhita.model.manage;

public class TopUpAmount {
    private Integer id;

    private String operationdate;

    private String billingdate;

    private String firm;

    private Integer topupamount;

    private String cashreceipts;

    private String paymentaccount;

    private String contact;

    private String note;

    private String deleted;

    private String company;

    private String firmtype;
    
    private String modifyTime;

    public TopUpAmount(Integer id, String operationdate, String billingdate, String firm, Integer topupamount, String cashreceipts, String paymentaccount, String contact, String note, String deleted, String company, String firmtype,String modifyTime) {
        this.id = id;
        this.operationdate = operationdate;
        this.billingdate = billingdate;
        this.firm = firm;
        this.topupamount = topupamount;
        this.cashreceipts = cashreceipts;
        this.paymentaccount = paymentaccount;
        this.contact = contact;
        this.note = note;
        this.deleted = deleted;
        this.company = company;
        this.firmtype = firmtype;
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

    public String getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(String operationdate) {
        this.operationdate = operationdate == null ? null : operationdate.trim();
    }

    public String getBillingdate() {
        return billingdate;
    }

    public void setBillingdate(String billingdate) {
        this.billingdate = billingdate == null ? null : billingdate.trim();
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm == null ? null : firm.trim();
    }

    public Integer getTopupamount() {
        return topupamount;
    }

    public void setTopupamount(Integer topupamount) {
        this.topupamount = topupamount;
    }

    public String getCashreceipts() {
        return cashreceipts;
    }

    public void setCashreceipts(String cashreceipts) {
        this.cashreceipts = cashreceipts == null ? null : cashreceipts.trim();
    }

    public String getPaymentaccount() {
        return paymentaccount;
    }

    public void setPaymentaccount(String paymentaccount) {
        this.paymentaccount = paymentaccount == null ? null : paymentaccount.trim();
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

    public String getFirmtype() {
        return firmtype;
    }

    public void setFirmtype(String firmtype) {
        this.firmtype = firmtype == null ? null : firmtype.trim();
    }
    
    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }
}