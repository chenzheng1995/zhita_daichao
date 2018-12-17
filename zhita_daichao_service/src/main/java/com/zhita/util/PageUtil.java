package com.zhita.util;

/**
 * 分页工具类.泛型类
 */
public class PageUtil{
    private int page=1;      //当前页

    private int pageSize=2;     //每页显示的数量

    private int totalCount;     //总记录数

    private int totalPageCount;      //总页数


    /**
     * 通过构造函数，传入总记录数和当前页
     * @param pageNow
     * @param totalCount
     */
    public PageUtil(int page, int totalCount) {
        super();
        this.page=page;
        this.totalCount = totalCount;
    }
    
    public int getPage() {
    	this.page=(page-1)*pageSize;
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    //获取总页数
    public int getTotalPageCount() {
        totalPageCount=getTotalCount()/getPageSize();
        return (totalCount/pageSize==0) ? totalPageCount:totalPageCount+1;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
