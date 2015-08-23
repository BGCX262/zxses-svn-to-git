package com.lcweb.bean.pojo;

import java.util.HashSet;
import java.util.Set;

 

//广告种类
public class NewsAdType implements java.io.Serializable {

	 
	private static final long serialVersionUID = 1L;
	//种类编号
	private Integer adTypeId;
	private NewsItemBig newsItemBig;
	//种类名称
	private String adTypeName;
	//高度
	private Integer adHeight;
	//宽度
	private Integer adWidth;
	//广告种类
	private String adTypeTag;
	private Set newsAdManages = new HashSet(0);

	 

	public Integer getAdTypeId() {
		return this.adTypeId;
	}

	public void setAdTypeId(Integer adTypeId) {
		this.adTypeId = adTypeId;
	}

	public NewsItemBig getNewsItemBig() {
		return this.newsItemBig;
	}

	public void setNewsItemBig(NewsItemBig newsItemBig) {
		this.newsItemBig = newsItemBig;
	}

	public String getAdTypeName() {
		return this.adTypeName;
	}

	public void setAdTypeName(String adTypeName) {
		this.adTypeName = adTypeName;
	}

	public Integer getAdHeight() {
		return this.adHeight;
	}

	public void setAdHeight(Integer adHeight) {
		this.adHeight = adHeight;
	}

	public Integer getAdWidth() {
		return this.adWidth;
	}

	public void setAdWidth(Integer adWidth) {
		this.adWidth = adWidth;
	}

	public String getAdTypeTag() {
		return this.adTypeTag;
	}

	public void setAdTypeTag(String adTypeTag) {
		this.adTypeTag = adTypeTag;
	}

	public Set getNewsAdManages() {
		return this.newsAdManages;
	}

	public void setNewsAdManages(Set newsAdManages) {
		this.newsAdManages = newsAdManages;
	}

}