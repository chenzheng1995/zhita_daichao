package com.zhita.model.manage;

public class LoanInformation {
    private Integer id;

    private String name;

    private Integer userid;

    private String idcard;

    private String professionalidentity;

    private String monthlyincomerange;

    private String educationalbackground;

    private String sesamepoints;

    private String cellphonetime;

    private String iscreditcard;

    private String isaccumulationfund;

    private String issocialsecurity;

    private String iscar;

    private String ishouse;
    
    private String company;//公司名

    public LoanInformation(Integer id, String name, Integer userid, String idcard, String professionalidentity, String monthlyincomerange, String educationalbackground, String sesamepoints, String cellphonetime, String iscreditcard, String isaccumulationfund, String issocialsecurity, String iscar, String ishouse, String company) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.idcard = idcard;
        this.professionalidentity = professionalidentity;
        this.monthlyincomerange = monthlyincomerange;
        this.educationalbackground = educationalbackground;
        this.sesamepoints = sesamepoints;
        this.cellphonetime = cellphonetime;
        this.iscreditcard = iscreditcard;
        this.isaccumulationfund = isaccumulationfund;
        this.issocialsecurity = issocialsecurity;
        this.iscar = iscar;
        this.ishouse = ishouse;
        this.company = company;
    }

    public LoanInformation() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getProfessionalidentity() {
        return professionalidentity;
    }

    public void setProfessionalidentity(String professionalidentity) {
        this.professionalidentity = professionalidentity == null ? null : professionalidentity.trim();
    }

    public String getMonthlyincomerange() {
        return monthlyincomerange;
    }

    public void setMonthlyincomerange(String monthlyincomerange) {
        this.monthlyincomerange = monthlyincomerange == null ? null : monthlyincomerange.trim();
    }

    public String getEducationalbackground() {
        return educationalbackground;
    }

    public void setEducationalbackground(String educationalbackground) {
        this.educationalbackground = educationalbackground == null ? null : educationalbackground.trim();
    }

    public String getSesamepoints() {
        return sesamepoints;
    }

    public void setSesamepoints(String sesamepoints) {
        this.sesamepoints = sesamepoints == null ? null : sesamepoints.trim();
    }

    public String getCellphonetime() {
        return cellphonetime;
    }

    public void setCellphonetime(String cellphonetime) {
        this.cellphonetime = cellphonetime == null ? null : cellphonetime.trim();
    }

    public String getIscreditcard() {
        return iscreditcard;
    }

    public void setIscreditcard(String iscreditcard) {
        this.iscreditcard = iscreditcard == null ? null : iscreditcard.trim();
    }

    public String getIsaccumulationfund() {
        return isaccumulationfund;
    }

    public void setIsaccumulationfund(String isaccumulationfund) {
        this.isaccumulationfund = isaccumulationfund == null ? null : isaccumulationfund.trim();
    }

    public String getIssocialsecurity() {
        return issocialsecurity;
    }

    public void setIssocialsecurity(String issocialsecurity) {
        this.issocialsecurity = issocialsecurity == null ? null : issocialsecurity.trim();
    }

    public String getIscar() {
        return iscar;
    }

    public void setIscar(String iscar) {
        this.iscar = iscar == null ? null : iscar.trim();
    }

    public String getIshouse() {
        return ishouse;
    }

    public void setIshouse(String ishouse) {
        this.ishouse = ishouse == null ? null : ishouse.trim();
    }
    
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}