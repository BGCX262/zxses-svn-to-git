package com.lcweb.bean.pojo;

import java.util.HashSet;
import java.util.Set;

public class NewsItemSmall implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer typeId;
	private NewsItemBig newsItemBig;
	//小类名称
	private String typeName;
	//栏目排序
	private Integer orderId;
	//是否显示
	private String ifDisplay;
	//审核标志
	private String checkFlag;
	//发布方式
	private String announceType = null;
	//链接地址
	private String httpUrl = null;

	private Set newsItemConfigs = new HashSet(0);
	private Set newsContentManages = new HashSet(0);

	public String getAnnounceType() {
		return announceType;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public NewsItemBig getNewsItemBig() {
		return newsItemBig;
	}

	public void setNewsItemBig(NewsItemBig newsItemBig) {
		this.newsItemBig = newsItemBig;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getIfDisplay() {
		return ifDisplay;
	}

	public void setIfDisplay(String ifDisplay) {
		this.ifDisplay = ifDisplay;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Set getNewsItemConfigs() {
		return newsItemConfigs;
	}

	public void setNewsItemConfigs(Set newsItemConfigs) {
		this.newsItemConfigs = newsItemConfigs;
	}

	public Set getNewsContentManages() {
		return newsContentManages;
	}

	public void setNewsContentManages(Set newsContentManages) {
		this.newsContentManages = newsContentManages;
	}

	public void setAnnounceType(String announceType) {
		this.announceType = announceType;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

}