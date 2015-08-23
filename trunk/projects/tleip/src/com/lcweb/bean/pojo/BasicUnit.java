package com.lcweb.bean.pojo;

import java.util.HashSet;
import java.util.Set;

 

//单位信息
public class BasicUnit implements java.io.Serializable {
	 
	private static final long serialVersionUID = 1L;
	private Long unitId;
	//单位代码
	private String unitCode;
	//单位名称
	private String unitName;
	//负责人
	private String unitMaster;
	//邮政编码
	private String postcode;
	//联系人
	private String linkman;
	//联系电话
	private String telephone;
	//传真电话
	private String fax;
	//电子信箱
	private String email;
	//主页地址
	private String homepage;
	//简介
	private String remark;
	private Set basicDepartments = new HashSet(0);

	 

	public Long getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitMaster() {
		return this.unitMaster;
	}

	public void setUnitMaster(String unitMaster) {
		this.unitMaster = unitMaster;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getBasicDepartments() {
		return this.basicDepartments;
	}

	public void setBasicDepartments(Set basicDepartments) {
		this.basicDepartments = basicDepartments;
	}

}