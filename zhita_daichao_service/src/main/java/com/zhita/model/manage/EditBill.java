package com.zhita.model.manage;

public class EditBill {
    private Integer id;

    private String operationdate;

    private String account;

    private Integer registrationnumber;

    private Integer price;

    private Integer realpay;

    private String note;

    private String accounttype;

    private String firmtype;

    private Integer sourceid;

    private Integer businessesid;

    private String modifytime;

    private String deleted;

    private String registrationtime;
    
    private String company;
    
    private String sourceTo;
    

    public EditBill(Integer id, String operationdate, String account, Integer registrationnumber, Integer price, Integer realpay, String note, String accounttype, String firmtype, Integer sourceid, Integer businessesid, String modifytime, String deleted, String registrationtime,String company,String sourceTo) {
        this.id = id;
        this.operationdate = operationdate;
        this.account = account;
        this.registrationnumber = registrationnumber;
        this.price = price;
        this.realpay = realpay;
        this.note = note;
        this.accounttype = accounttype;
        this.firmtype = firmtype;
        this.sourceid = sourceid;
        this.businessesid = businessesid;
        this.modifytime = modifytime;
        this.deleted = deleted;
        this.registrationtime = registrationtime;
        this.company = company;
        this.sourceTo = sourceTo;
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

    public String getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(String operationdate) {
        this.operationdate = operationdate == null ? null : operationdate.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getRegistrationnumber() {
        return registrationnumber;
    }

    public void setRegistrationnumber(Integer registrationnumber) {
        this.registrationnumber = registrationnumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRealpay() {
        return realpay;
    }

    public void setRealpay(Integer realpay) {
        this.realpay = realpay;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype == null ? null : accounttype.trim();
    }

    public String getFirmtype() {
        return firmtype;
    }

    public void setFirmtype(String firmtype) {
        this.firmtype = firmtype == null ? null : firmtype.trim();
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public Integer getBusinessesid() {
        return businessesid;
    }

    public void setBusinessesid(Integer businessesid) {
        this.businessesid = businessesid;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime == null ? null : modifytime.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public String getRegistrationtime() {
        return registrationtime;
    }

    public void setRegistrationtime(String registrationtime) {
        this.registrationtime = registrationtime == null ? null : registrationtime.trim();
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
}