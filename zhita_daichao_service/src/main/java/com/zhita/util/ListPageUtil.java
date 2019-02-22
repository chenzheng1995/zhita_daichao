package com.zhita.util;

import java.util.Collections;
import java.util.List;

public class ListPageUtil<T> {
	/**集合数据*/
    private List<T> data;//传进来的  需要进行分页的    集合数据

    /** 当前页 */
    private int currentPage;//当前页

    /** 每页条数 */
    private int pageSize;//每页显示的数量

    /** 总页数 */
    private int totalPage;//总页数

    /** 总数据条数 */
    private int totalCount;//总记录数

    public ListPageUtil(List<T> data,int currentPage,int pageSize) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("data must be not empty!");
        }
        
        if(currentPage<1) {
        	this.currentPage=1;
        }
        else if((currentPage)>(data.size() + pageSize - 1) / pageSize) {
        	this.currentPage=(data.size() + pageSize - 1) / pageSize;
        }
        else if((currentPage>=1)||(currentPage<=((data.size() + pageSize - 1) / pageSize))){
        	 this.currentPage = currentPage;
        }
        
        this.data = data;
        this.pageSize = pageSize;
        this.totalCount = data.size();
        this.totalPage = (totalCount + pageSize - 1) / pageSize;
        System.out.println(this.getData()+"test----"+this.getCurrentPage()+"test----"+this.getPageSize()+"test----"+this.getTotalCount()+"test---"+this.getTotalPage()+"test---");

    }

    //各个属性的get方法
    public List<T> getData() {
        int fromIndex = (currentPage - 1) * pageSize;//fromIndex是集合的开始下标
        if (fromIndex >= data.size()) {
            return Collections.emptyList();//空数组
        }
        if(fromIndex<0){
            return Collections.emptyList();//空数组
        }
        
        int toIndex = currentPage * pageSize;//toIndex是集合的结束下标
        if (toIndex >= data.size()) {
            toIndex = data.size();
        }
        return data.subList(fromIndex, toIndex);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }
    
    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }
}