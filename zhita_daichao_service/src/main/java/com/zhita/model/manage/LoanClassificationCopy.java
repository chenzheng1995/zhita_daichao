package com.zhita.model.manage;

import java.math.BigDecimal;

public class LoanClassificationCopy {
    private Integer id;

    private String businessclassification;

    private BigDecimal amount;

    private String company;

    public LoanClassificationCopy(Integer id, String businessclassification, BigDecimal amount, String company) {
        this.id = id;
        this.businessclassification = businessclassification;
        this.amount = amount;
        this.company = company;
    }

    public LoanClassificationCopy() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessclassification() {
        return businessclassification;
    }

    public void setBusinessclassification(String businessclassification) {
        this.businessclassification = businessclassification == null ? null : businessclassification.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}