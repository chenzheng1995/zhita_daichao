package com.zhita.model.manage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

//贷款分类表
public class LoanClassification implements Serializable{
    private Integer id;//贷款分类id

    private String businessClassification;//贷款分类

    private BigDecimal amount;//金额
    
    private List<LoansBusinesses> listLoanBusiness;//一个贷款分类下有多个贷款商家----一对多的关系

    public LoanClassification(Integer id, String businessClassification, BigDecimal amount) {
        this.id = id;
        this.businessClassification = businessClassification;
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


    public String getBusinessClassification() {
		return businessClassification;
	}

	public void setBusinessClassification(String businessClassification) {
		this.businessClassification = businessClassification;
	}

	public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

	public List<LoansBusinesses> getListLoanBusiness() {
		return listLoanBusiness;
	}

	public void setListLoanBusiness(List<LoansBusinesses> listLoanBusiness) {
		this.listLoanBusiness = listLoanBusiness;
	}
    
}