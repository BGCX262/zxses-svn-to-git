package com.agilefly.bean;

import java.util.ArrayList;
import java.util.List;

public class TypeArticle implements java.io.Serializable {
	private SysType sysType;
	private List<BlogArticle> blogArticleList = new ArrayList<BlogArticle>();
	
	public List<BlogArticle> getBlogArticleList() {
		return blogArticleList;
	}
	public void setBlogArticleList(List<BlogArticle> blogArticleList) {
		this.blogArticleList = blogArticleList;
	}
	public SysType getSysType() {
		return sysType;
	}
	public void setSysType(SysType sysType) {
		this.sysType = sysType;
	}
}
