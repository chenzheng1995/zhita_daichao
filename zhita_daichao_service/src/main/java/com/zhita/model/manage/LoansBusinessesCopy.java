package com.zhita.model.manage;

import java.math.BigDecimal;

public class LoansBusinessesCopy {
    private Integer id;

    private String businessname;

    private String intro;

    private String loanlimit;

    private String trademark;

    private String isstick;

    private Integer sort;

    private Integer applicationnumber;

    private String state;

    private Integer busclaid;
    
    private String busClaIdString;//做添加操作时  商家分类id字符串

    private BigDecimal loanlimitsmall;

    private BigDecimal loanlimitbig;

    private String cycle;

    private BigDecimal interestrate;

    private String applicationrequirements;

    private String applicationprocess;

    private String specialinstructions;

    private Integer successrate;

    private String lendingrate;

    private String registerlink;

    private BigDecimal prepaidamount;

    private String deleted;

    private Integer applications;

    private String company;
    
    private String onesourcename;

    private String twosourcename;

    public LoansBusinessesCopy(Integer id, String businessname, String intro, String loanlimit, String trademark, String isstick, Integer sort, Integer applicationnumber, String state, Integer busclaid, BigDecimal loanlimitsmall, BigDecimal loanlimitbig, String cycle, BigDecimal interestrate, String applicationrequirements, String applicationprocess, String specialinstructions, Integer successrate, String lendingrate, String registerlink, BigDecimal prepaidamount, String deleted, Integer applications, String company,String onesourcename,String twosourcename) {
        this.id = id;
        this.businessname = businessname;
        this.intro = intro;
        this.loanlimit = loanlimit;
        this.trademark = trademark;
        this.isstick = isstick;
        this.sort = sort;
        this.applicationnumber = applicationnumber;
        this.state = state;
        this.busclaid = busclaid;
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
        this.company = company;
        this.onesourcename = onesourcename;
        this.twosourcename = twosourcename;
    }

    public LoansBusinessesCopy() {
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

    public String getLoanlimit() {
        return loanlimit;
    }

    public void setLoanlimit(String loanlimit) {
        this.loanlimit = loanlimit == null ? null : loanlimit.trim();
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

    public Integer getBusclaid() {
        return busclaid;
    }

    public void setBusclaid(Integer busclaid) {
        this.busclaid = busclaid;
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


    public String getLendingrate() {
		return lendingrate;
	}

	public void setLendingrate(String lendingrate) {
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
    
    public String getOnesourcename() {
        return onesourcename;
    }

    public void setOnesourcename(String onesourcename) {
        this.onesourcename = onesourcename == null ? null : onesourcename.trim();
    }
    
    public String getTwosourcename() {
        return twosourcename;
    }

    public void setTwosourcename(String twosourcename) {
        this.twosourcename = twosourcename == null ? null : twosourcename.trim();
    }

	public String getBusClaIdString() {
		return busClaIdString;
	}

	public void setBusClaIdString(String busClaIdString) {
		this.busClaIdString = busClaIdString;
	}
    
}