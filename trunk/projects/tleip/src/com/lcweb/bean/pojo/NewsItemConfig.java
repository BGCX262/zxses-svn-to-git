package com.lcweb.bean.pojo;

public class NewsItemConfig implements java.io.Serializable {
 
	private static final long serialVersionUID = 1L;
	private Integer newsConfigId;
	private NewsItemSmall newsItemSmall;
	private Integer displayRowCount;
	private Integer titleCharacterCount;
	private String titleImgUrl;
	private String dateFormat;
	private Integer moreRowCount;
	private Integer moreTitleCount;
	private String moreTitleImgUrl;
	private String moreDateFormat;
	private String ifPopWindow;
	private String ifPermissionSearch;
	private String newFlagTime;

	 

	public Integer getNewsConfigId() {
		return this.newsConfigId;
	}

	public void setNewsConfigId(Integer newsConfigId) {
		this.newsConfigId = newsConfigId;
	}

	public NewsItemSmall getNewsItemSmall() {
		return this.newsItemSmall;
	}

	public void setNewsItemSmall(NewsItemSmall newsItemSmall) {
		this.newsItemSmall = newsItemSmall;
	}

	public Integer getDisplayRowCount() {
		return this.displayRowCount;
	}

	public void setDisplayRowCount(Integer displayRowCount) {
		this.displayRowCount = displayRowCount;
	}

	public Integer getTitleCharacterCount() {
		return this.titleCharacterCount;
	}

	public void setTitleCharacterCount(Integer titleCharacterCount) {
		this.titleCharacterCount = titleCharacterCount;
	}

	public String getTitleImgUrl() {
		return this.titleImgUrl;
	}

	public void setTitleImgUrl(String titleImgUrl) {
		this.titleImgUrl = titleImgUrl;
	}

	public String getDateFormat() {
		return this.dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Integer getMoreRowCount() {
		return this.moreRowCount;
	}

	public void setMoreRowCount(Integer moreRowCount) {
		this.moreRowCount = moreRowCount;
	}

	public Integer getMoreTitleCount() {
		return this.moreTitleCount;
	}

	public void setMoreTitleCount(Integer moreTitleCount) {
		this.moreTitleCount = moreTitleCount;
	}

	public String getMoreTitleImgUrl() {
		return this.moreTitleImgUrl;
	}

	public void setMoreTitleImgUrl(String moreTitleImgUrl) {
		this.moreTitleImgUrl = moreTitleImgUrl;
	}

	public String getMoreDateFormat() {
		return this.moreDateFormat;
	}

	public void setMoreDateFormat(String moreDateFormat) {
		this.moreDateFormat = moreDateFormat;
	}

	public String getIfPopWindow() {
		return this.ifPopWindow;
	}

	public void setIfPopWindow(String ifPopWindow) {
		this.ifPopWindow = ifPopWindow;
	}

	public String getIfPermissionSearch() {
		return this.ifPermissionSearch;
	}

	public void setIfPermissionSearch(String ifPermissionSearch) {
		this.ifPermissionSearch = ifPermissionSearch;
	}

	public String getNewFlagTime() {
		return newFlagTime;
	}

	public void setNewFlagTime(String newFlagTime) {
		this.newFlagTime = newFlagTime;
	}

}