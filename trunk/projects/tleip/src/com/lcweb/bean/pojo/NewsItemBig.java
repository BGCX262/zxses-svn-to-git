package com.lcweb.bean.pojo;

import java.util.HashSet;
import java.util.Set;



 
public class NewsItemBig implements java.io.Serializable {

 
	private static final long serialVersionUID = 1L;
	private String classId;
	private SchCollegeDepartment schCollegeDepartment;
	private String yxdm;
	 
	private Integer ifIndex;
	 
	private String className;
	 
	private Integer orderId;
	 
	private String ifDisplay;
	 
	private String ifHaveAd;
	 
	private String url; 
	
	private Set newsItemNavigations = new HashSet(0);
	private Set newsItemSmalls = new HashSet(0);
	private Set newsAdTypes = new HashSet(0);

	 

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	public String getYxdm() {
		return this.yxdm;
	}

	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public SchCollegeDepartment getSchCollegeDepartment() {
		return this.schCollegeDepartment;
	}

	public void setSchCollegeDepartment(
			SchCollegeDepartment schCollegeDepartment) {
		this.schCollegeDepartment = schCollegeDepartment;
	}
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getIfDisplay() {
		return this.ifDisplay;
	}

	public void setIfDisplay(String ifDisplay) {
		this.ifDisplay = ifDisplay;
	}

	public String getIfHaveAd() {
		return this.ifHaveAd;
	}

	public void setIfHaveAd(String ifHaveAd) {
		this.ifHaveAd = ifHaveAd;
	}

	public Set getNewsItemNavigations() {
		return this.newsItemNavigations;
	}

	public void setNewsItemNavigations(Set newsItemNavigations) {
		this.newsItemNavigations = newsItemNavigations;
	}

	public Set getNewsItemSmalls() {
		return this.newsItemSmalls;
	}

	public void setNewsItemSmalls(Set newsItemSmalls) {
		this.newsItemSmalls = newsItemSmalls;
	}

	public Set getNewsAdTypes() {
		return this.newsAdTypes;
	}

	public void setNewsAdTypes(Set newsAdTypes) {
		this.newsAdTypes = newsAdTypes;
	}
	public Integer getIfIndex() {
		return this.ifIndex;
	}

	public void setIfIndex(Integer ifIndex) {
		this.ifIndex = ifIndex;
	}


}