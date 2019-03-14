package com.zhita.model.manage;

import java.math.BigDecimal;
import java.util.List;

public class LoanClassificationCopy {
	 private Integer id;//贷款分类id

	    private String businessClassification;//贷款分类

	    private BigDecimal amount;//金额
	    
	    private List<LoansBusinesses> listLoanBusiness;//一个贷款分类下有多个贷款商家----一对多的关系
	    
	    private String company;//公司名
	    
	    private String icon;//图标

	    public LoanClassificationCopy(Integer id, String businessClassification, BigDecimal amount,String company, String icon) {
	        this.id = id;
	        this.businessClassification = businessClassification;
	        this.amount = amount;
	        this.company = company;
	        this.icon = icon;
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

		@Override
		public String toString() {
			return "LoanClassification [id=" + id + ", businessClassification=" + businessClassification + ", amount="
					+ amount + ", listLoanBusiness=" + listLoanBusiness + ", company=" + company +", icon=" + icon + "]";
		}
	    
	}