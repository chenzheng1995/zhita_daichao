package com.zhita.model.manage;

public class CustomerUrlResult {
    private String customerName;
    private String customerUrl;
    private String urlDate;
    private int state;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerUrl() {
        return customerUrl;
    }

    public void setCustomerUrl(String customerUrl) {
        this.customerUrl = customerUrl;
    }

    public String getUrlDate() {
        return urlDate;
    }

    public void setUrlDate(String urlDate) {
        this.urlDate = urlDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public CustomerUrlResult() {
    }

    public CustomerUrlResult(String customerName, String urlDate) {
        this.customerName = customerName;
        this.urlDate = urlDate;
    }
}
