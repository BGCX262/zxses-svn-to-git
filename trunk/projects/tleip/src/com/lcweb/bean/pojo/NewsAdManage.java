package com.lcweb.bean.pojo;

import java.util.Date;



//广告管理
public class NewsAdManage implements java.io.Serializable {

 
 
	private static final long serialVersionUID = 1L;
	private Integer adId;
	private NewsAdType newsAdType;
	//广告名称
	private String adName;
	//广告显示位置
	private Integer adPosition;
	//广告图片URL
	private String adImgUrl;
	//广告链接URL
	private String adHttpUrl;
	//发布人
	private String personCode;
	//发布日期
	private Date adAnnounceDate;
	//有效期
	private Date adValidityDate;
	//是否显示
	private String ifDisplay;

	 

	public Integer getAdId() {
		return this.adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public NewsAdType getNewsAdType() {
		return this.newsAdType;
	}

	public void setNewsAdType(NewsAdType newsAdType) {
		this.newsAdType = newsAdType;
	}

	public String getAdName() {
		return this.adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public Integer getAdPosition() {
		return this.adPosition;
	}

	public void setAdPosition(Integer adPosition) {
		this.adPosition = adPosition;
	}

	public String getAdImgUrl() {
		return this.adImgUrl;
	}

	public void setAdImgUrl(String adImgUrl) {
		this.adImgUrl = adImgUrl;
	}

	public String getAdHttpUrl() {
		return this.adHttpUrl;
	}

	public void setAdHttpUrl(String adHttpUrl) {
		this.adHttpUrl = adHttpUrl;
	}

	public String getPersonCode() {
		return this.personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public Date getAdAnnounceDate() {
		return this.adAnnounceDate;
	}

	public void setAdAnnounceDate(Date adAnnounceDate) {
		this.adAnnounceDate = adAnnounceDate;
	}

	public Date getAdValidityDate() {
		return this.adValidityDate;
	}

	public void setAdValidityDate(Date adValidityDate) {
		this.adValidityDate = adValidityDate;
	}

	public String getIfDisplay() {
		return this.ifDisplay;
	}

	public void setIfDisplay(String ifDisplay) {
		this.ifDisplay = ifDisplay;
	}

}