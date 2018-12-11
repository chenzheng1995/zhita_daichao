package com.zhita.model.manage;

import java.math.BigDecimal;

public class LoansBusinesses {
    private Integer id;

    private String businessname;

    private String intro;

    private BigDecimal loanlimit;

    private String trademark;

    private String isstick;

    private Integer sort;

    private Integer applicationnumber;

    private String state;

    private String businessclassification;

    private BigDecimal loanlimitsmall;

    private BigDecimal loanlimitbig;

    private String cycle;

    private BigDecimal interestrate;

    private String applicationrequirements;

    private String applicationprocess;

    private String specialinstructions;

    private Integer successrate;

    private Integer lendingrate;

    private String registerlink;

    private BigDecimal prepaidamount;

    private String deleted;

    private Integer applications;

    public LoansBusinesses(Integer id, String businessname, String intro, BigDecimal loanlimit, String trademark, String isstick, Integer sort, Integer applicationnumber, String state, String businessclassification, BigDecimal loanlimitsmall, BigDecimal loanlimitbig, String cycle, BigDecimal interestrate, String applicationrequirements, String applicationprocess, String specialinstructions, Integer successrate, Integer lendingrate, String registerlink, BigDecimal prepaidamount, String deleted, Integer applications) {
        this.id = id;
        this.businessname = businessname;
        this.intro = intro;
        this.loanlimit = loanlimit;
        this.trademark = trademark;
        this.isstick = isstick;
        this.sort = sort;
        this.applicationnumber = applicationnumber;
        this.state = state;
        this.businessclassification = businessclassification;
        this.loanlimitsmall = loanlimitsmall;
        this.loanlimitbig = loanlimitbig;
        this.cycle = cycle;
        this.interestrate = interestrate;
        this.applicationrequirements = applicationrequirements;
        this.applicationprocess = applicationprocess;
        this.specialinstructions = specialinstructions;
        this.successrate = successrate;
        this.lendingrate = lendingrate;
        this.registerlink = registerlink;
        this.prepaidamount = prepaidamount;
        this.deleted = deleted;
        this.applications = applications;
    }

    public LoansBusinesses() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname == null ? null : businessname.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public BigDecimal getLoanlimit() {
        return loanlimit;
    }

    public void setLoanlimit(BigDecimal loanlimit) {
        this.loanlimit = loanlimit;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark == null ? null : trademark.trim();
    }

    public String getIsstick() {
        return isstick;
    }

    public void setIsstick(String isstick) {
        this.isstick = isstick == null ? null : isstick.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getApplicationnumber() {
        return applicationnumber;
    }

    public void setApplicationnumber(Integer applicationnumber) {
        this.applicationnumber = applicationnumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getBusinessclassification() {
        return businessclassification;
    }

    public void setBusinessclassification(String businessclassification) {
        this.businessclassification = businessclassification == null ? null : businessclassification.trim();
    }

    public BigDecimal getLoanlimitsmall() {
        return loanlimitsmall;
    }

    public void setLoanlimitsmall(BigDecimal loanlimitsmall) {
        this.loanlimitsmall = loanlimitsmall;
    }

    public BigDecimal getLoanlimitbig() {
        return loanlimitbig;
    }

    public void setLoanlimitbig(BigDecimal loanlimitbig) {
        this.loanlimitbig = loanlimitbig;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle == null ? null : cycle.trim();
    }

    public BigDecimal getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(BigDecimal interestrate) {
        this.interestrate = interestrate;
    }

    public String getApplicationrequirements() {
        return applicationrequirements;
    }

    public void setApplicationrequirements(String applicationrequirements) {
        this.applicationrequirements = applicationrequirements == null ? null : applicationrequirements.trim();
    }

    public String getApplicationprocess() {
        return applicationprocess;
    }

    public void setApplicationprocess(String applicationprocess) {
        this.applicationprocess = applicationprocess == null ? null : applicationprocess.trim();
    }

    public String getSpecialinstructions() {
        return specialinstructions;
    }

    public void setSpecialinstructions(String specialinstructions) {
        this.specialinstructions = specialinstructions == null ? null : specialinstructions.trim();
    }

    public Integer getSuccessrate() {
        return successrate;
    }

    public void setSuccessrate(Integer successrate) {
        this.successrate = successrate;
    }

    public Integer getLendingrate() {
        return lendingrate;
    }

    public void setLendingrate(Integer lendingrate) {
        this.lendingrate = lendingrate;
    }

    public String getRegisterlink() {
        return registerlink;
    }

    public void setRegisterlink(String registerlink) {
        this.registerlink = registerlink == null ? null : registerlink.trim();
    }

    public BigDecimal getPrepaidamount() {
        return prepaidamount;
    }

    public void setPrepaidamount(BigDecimal prepaidamount) {
        this.prepaidamount = prepaidamount;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public Integer getApplications() {
        return applications;
    }

    public void setApplications(Integer applications) {
        this.applications = applications;
    }
}