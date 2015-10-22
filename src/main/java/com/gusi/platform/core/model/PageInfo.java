package com.gusi.platform.core.model;

import java.util.List;

public class PageInfo {
	private List objList;// 查询结果存放位置
	private Integer totalRecords;// 总共的记录
	private Integer firstRow;// 第一行
	private Integer pageSize;// 每页的大小
	// private Integer currentPageNo = 1;
	private String condition;// 查询条件
	private String qurey;// 查询条件
	private String pageAction;
	private String method;

	private String url;// 页面跳转url，比如上一页，下一页的url前缀

	public PageInfo() {

	}

	public PageInfo(Integer pageSize) {
		this.firstRow = 0;
		this.pageSize = pageSize;
	}

	public PageInfo(Integer firstRow, Integer pageSize) {
		this.firstRow = firstRow;
		this.pageSize = pageSize;
	}

	public List getObjList() {
		return objList;
	}

	public void setObjList(List objList) {
		this.objList = objList;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Integer getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public PageInfo appendCondition(String condition) {
		if (this.condition == null || this.condition.isEmpty()) {
			this.condition = " 1=1 ";
		}
		this.condition += condition;
		return this;
	}

	public String getQurey() {
		return qurey;
	}

	public void setQurey(String qurey) {
		this.qurey = qurey;
	}

	public String getPageAction() {
		return pageAction;
	}

	public void setPageAction(String pageAction) {
		this.pageAction = pageAction;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取一共有多少页
	 * 
	 * @return
	 */
	public Integer getTotalPages() {
		return totalRecords % pageSize == 0 ? (totalRecords / pageSize) : (totalRecords / pageSize + 1);
	}

	public Integer getTopPageNo() {
		return 1;
	}

	public Integer getBottomPageNo() {
		return getTotalPages() == 0 ? 1 : getTotalPages();
	}

	public Integer getCurrentPageNo() {
		// int temp1 = firstRow / pageSize;
		// int temp2 = firstRow % pageSize;
		// if (temp1 == 0) {
		// return 1;
		// } else {
		// return (temp1 + 1);
		// }
		return (firstRow / pageSize + 1);
	}

	public Integer getPreviousPageNo() {
		if (getCurrentPageNo() <= 1) {
			return 1;
		} else {
			return (getCurrentPageNo() - 1);
		}
	}

	public Integer getNextPageNo() {
		if (getCurrentPageNo() >= getTotalPages()) {
			return getTotalPages() == 0 ? 1 : getTotalPages();
		} else {
			return getCurrentPageNo() + 1;
		}
	}

	public PageInfo getPageInfo(int pageNo) {
		this.firstRow = (pageNo - 1) * pageSize;
		return this;
	}

	public PageInfo getPreviousPageInof() {
		return getPageInfo(getPreviousPageNo());
	}

	public PageInfo getNextPageInfo() {
		return getPageInfo(getNextPageNo());
	}

	@Override
	public String toString() {
		return "firstRow=" + getFirstRow() + "; pageSize=" + getPageSize() + "; currentPageNo=" + getCurrentPageNo() + "; previousPageNo="
				+ getPreviousPageNo() + "; nextPageNo=" + getNextPageNo() + "; topPageNo=" + getTopPageNo() + "; bottomPageNo="
				+ getBottomPageNo() + "; totalRecords=" + getTotalRecords() + "; totalPages=" + getTotalPages() + "; condition="
				+ getCondition();
	}

}
