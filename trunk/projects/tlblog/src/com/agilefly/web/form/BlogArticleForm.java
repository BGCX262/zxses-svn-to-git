package com.agilefly.web.form;

import org.apache.struts.action.ActionForm;

public class BlogArticleForm extends ActionForm {
	private Integer id;
	private String articleTitle;
	private String articleContent;
	private Integer articleTypeId;
	//是否公开
    private Byte publicStatus;
    //是否可评论
    private Byte commentStatus;
    
    //当前博客用户
    private String currentUserName;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public Integer getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	public Byte getPublicStatus() {
		return publicStatus;
	}
	public void setPublicStatus(Byte publicStatus) {
		this.publicStatus = publicStatus;
	}
	public Byte getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(Byte commentStatus) {
		this.commentStatus = commentStatus;
	}
}
