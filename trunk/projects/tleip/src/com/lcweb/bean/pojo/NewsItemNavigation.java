package com.lcweb.bean.pojo;

public class NewsItemNavigation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer navigationId;
	private NewsItemBig newsItemBig;
	private String navigationName;
	private String navigationUrl;
	private Integer orderId;

	public Integer getNavigationId() {
		return this.navigationId;
	}

	public void setNavigationId(Integer navigationId) {
		this.navigationId = navigationId;
	}

	public NewsItemBig getNewsItemBig() {
		return this.newsItemBig;
	}

	public void setNewsItemBig(NewsItemBig newsItemBig) {
		this.newsItemBig = newsItemBig;
	}

	public String getNavigationName() {
		return this.navigationName;
	}

	public void setNavigationName(String navigationName) {
		this.navigationName = navigationName;
	}

	public String getNavigationUrl() {
		return this.navigationUrl;
	}

	public void setNavigationUrl(String navigationUrl) {
		this.navigationUrl = navigationUrl;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}