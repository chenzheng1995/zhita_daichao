package com.zhita.model.manage;

import java.math.BigDecimal;

public class LoanClassification {
    private Integer id;

    private String businessclassification;

    private BigDecimal amount;

    public LoanClassification(Integer id, String businessclassification, BigDecimal amount) {
        this.id = id;
        this.businessclassification = businessclassification;
        this.amount = amount;
    }

    public LoanClassification() {
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
}