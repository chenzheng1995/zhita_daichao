package com.zhita.model.manage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

//完全复制贷款分类model
public class CreditCardType implements Serializable{

    private Integer id;//贷款分类id

    private String businessClassification;//贷款分类

    private BigDecimal amount;//金额
    
    private List<CreditCard1> listLoanBusiness;//一个贷款分类下有多个贷款商家----一对多的关系
    
    private String company;//公司名
    
    private String icon;//图标
    
    private String deleted;//假删除（删除：1，没删除：0）

    public CreditCardType(Integer id, String businessClassification, BigDecimal amount,String company, String icon) {
        this.id = id;
        this.businessClassification = businessClassification;
        this.amount = amount;
        this.company = company;
        this.icon = icon;
    }

    public CreditCardType() {
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

    public List<CreditCard1> getListLoanBusiness() {
		return listLoanBusiness;
	}

	public void setListLoanBusiness(List<CreditCard1> listLoanBusiness) {
		this.listLoanBusiness = listLoanBusiness;
	}

	public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
    
    public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "CreditCardType [id=" + id + ", businessClassification=" + businessClassification + ", amount=" + amount
				+ ", listLoanBusiness=" + listLoanBusiness + ", company=" + company + ", icon=" + icon + ", deleted="
				+ deleted + "]";
	}

}
