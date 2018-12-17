package com.zhita.model.manage;

import java.io.Serializable;
import java.math.BigDecimal;

//贷款分类表
public class LoanClassification implements Serializable{
    private Integer id;//贷款分类id

    private String businessclassification;//贷款分类

    private BigDecimal amount;//金额

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