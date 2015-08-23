package com.lcweb.base.util;

public class JsTree {
	//id
	private int id;
	//¸¸id
	private int parentId;
	//ÏÔÊ¾Ãû³Æ
	private String name;
	//url
	private String url="";
	//ÏÔÊ¾Í¼Æ¬
	private String image;

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
