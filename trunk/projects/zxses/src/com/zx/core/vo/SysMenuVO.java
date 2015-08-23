package com.zx.core.vo;   


import java.util.ArrayList;
import java.util.List;

  
public class SysMenuVO {

	    private Long id;
	    private String code;
	    private String name;
	    private String url;
	    private Long recordOrder;
	    private List childList = new ArrayList();
	    
	    private String imgSrc;
	    
	    private String height;
	    
		
		public String getHeight() {
			return height;
		}
		public void setHeight(String height) {
			this.height = height;
		}
		public String getImgSrc() {
			return imgSrc;
		}
		public void setImgSrc(String imgSrc) {
			this.imgSrc = imgSrc;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
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
		public Long getRecordOrder() {
			return recordOrder;
		}
		public void setRecordOrder(Long recordOrder) {
			this.recordOrder = recordOrder;
		}
		public List getChildList() {
			return childList;
		}
		public void setChildList(List childList) {
			this.childList = childList;
		}
}
