package com.agilefly.commons;

import java.util.List;

/**
 * @author boleyn_renlei
 * @date May 14, 2012 11:17:00 AM 
 * @param 前端分页封装结果集
 */
public class QueryResult<T> {
	private List<T> resultlist;
	private long totalrecord;
	
	public List<T> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
}
