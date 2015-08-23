package com.agilefly.bean;

import java.util.Set;

/**
 * @author boleyn_renlei
 * @date May 16, 2012 7:16:16 PM
 */
public class SysModule implements java.io.Serializable {
	private int id;
	/*模块名称*/
	private String name;
	/*模块编号*/
	private String sn;
	/*模块的入口地址*/
	private String url;
	/*模块排序号*/
	private int orderNo;
	private SysModule parent;
	private Set<SysModule> children;
	
	/*通过用户权限控制是否显示模块*/
	private Boolean isDisplay = false;

	public SysModule(){}
	
	public SysModule(int id, String name, String sn, String url, int orderNo, SysModule parent, Set<SysModule> children) {
		super();
		this.id = id;
		this.name = name;
		this.sn = sn;
		this.url = url;
		this.orderNo = orderNo;
		this.parent = parent;
		this.children = children;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public SysModule getParent() {
		return parent;
	}
	public void setParent(SysModule parent) {
		this.parent = parent;
	}
	public Set<SysModule> getChildren() {
		return children;
	}
	public void setChildren(Set<SysModule> children) {
		this.children = children;
	}
	
	public Boolean getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}
}
