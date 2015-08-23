package com.lcweb.bean.pojo;

import java.util.Date;



 
public class NewsContentReview implements java.io.Serializable {

 

 
	private static final long serialVersionUID = 1L;
	private Integer reviewId;
	private NewsContentManage newsContentManage;
	 
	private String reviewUser;
	 
	private String reviewContent;
	 
	private Date reviewDate;
	 
	private String email;
	 
	private String reviewIp;
 
	public Integer getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public NewsContentManage getNewsContentManage() {
		return this.newsContentManage;
	}

	public void setNewsContentManage(NewsContentManage newsContentManage) {
		this.newsContentManage = newsContentManage;
	}

	public String getReviewUser() {
		return this.reviewUser;
	}

	public void setReviewUser(String reviewUser) {
		this.reviewUser = reviewUser;
	}

	public String getReviewContent() {
		return this.reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Date getReviewDate() {
		return this.reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReviewIp() {
		return this.reviewIp;
	}

	public void setReviewIp(String reviewIp) {
		this.reviewIp = reviewIp;
	}

}