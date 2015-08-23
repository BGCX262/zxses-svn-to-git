package com.lcweb.bean.pojo;

import java.util.HashSet;
import java.util.Set;

 
public class BasicDepartment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long deptId;
	private String deptCode;
	private String deptName;
	private String upDeptCode;
	private String remark;
	private Set basicPersons = new HashSet(0);
	private BasicUnit basicUnit = new BasicUnit();
	private Set newsContents = new HashSet(0);

	private BasicDepartment parent;
	private Set childs = new HashSet(0);;
	
	public BasicDepartment getParent() {
		return parent;
	}

	public void setParent(BasicDepartment parent) {
		this.parent = parent;
	}

	public Set getChilds() {
		return childs;
	}

	public void setChilds(Set childs) {
		this.childs = childs;
	}

	public Long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public BasicUnit getBasicUnit() {
		return this.basicUnit;
	}

	public void setBasicUnit(BasicUnit basicUnit) {
		this.basicUnit = basicUnit;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUpDeptCode() {
		return this.upDeptCode;
	}

	public void setUpDeptCode(String upDeptCode) {
		this.upDeptCode = upDeptCode;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getBasicPersons() {
		return this.basicPersons;
	}

	public void setBasicPersons(Set basicPersons) {
		this.basicPersons = basicPersons;
	}

	public Set getNewsContents() {
		return newsContents;
	}

	public void setNewsContents(Set newsContents) {
		this.newsContents = newsContents;
	}

}