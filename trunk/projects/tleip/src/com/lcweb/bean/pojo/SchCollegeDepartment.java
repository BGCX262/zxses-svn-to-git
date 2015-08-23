package com.lcweb.bean.pojo;

import java.util.HashSet;
import java.util.Set;

public class SchCollegeDepartment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String yxdm;
	private Long deptId;
	private String xxdm;
	private String yxmc;
	private String yxsywmc;
	private String yxsjc;
	private String yxsbbm;
	private String yxslbm;
	private String jlny;
	private String xzfzr;
	private String dwfzr;
	private Set newsItemBigs = new HashSet(0);

	public String getYxdm() {
		return this.yxdm;
	}

	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}

	public String getXxdm() {
		return this.xxdm;
	}

	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}

	public String getYxmc() {
		return this.yxmc;
	}

	public void setYxmc(String yxmc) {
		this.yxmc = yxmc;
	}

	public String getYxsywmc() {
		return this.yxsywmc;
	}

	public void setYxsywmc(String yxsywmc) {
		this.yxsywmc = yxsywmc;
	}

	public String getYxsjc() {
		return this.yxsjc;
	}

	public void setYxsjc(String yxsjc) {
		this.yxsjc = yxsjc;
	}

	public String getYxsbbm() {
		return this.yxsbbm;
	}

	public void setYxsbbm(String yxsbbm) {
		this.yxsbbm = yxsbbm;
	}

	public String getYxslbm() {
		return this.yxslbm;
	}

	public void setYxslbm(String yxslbm) {
		this.yxslbm = yxslbm;
	}

	public String getJlny() {
		return this.jlny;
	}

	public void setJlny(String jlny) {
		this.jlny = jlny;
	}

	public String getXzfzr() {
		return this.xzfzr;
	}

	public void setXzfzr(String xzfzr) {
		this.xzfzr = xzfzr;
	}

	public String getDwfzr() {
		return this.dwfzr;
	}

	public void setDwfzr(String dwfzr) {
		this.dwfzr = dwfzr;
	}

	public Set getNewsItemBigs() {
		return this.newsItemBigs;
	}

	public void setNewsItemBigs(Set newsItemBigs) {
		this.newsItemBigs = newsItemBigs;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

}