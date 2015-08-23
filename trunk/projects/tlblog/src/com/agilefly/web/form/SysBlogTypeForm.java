package com.agilefly.web.form;

import org.apache.struts.action.ActionForm;

public class SysBlogTypeForm extends ActionForm {
	private Integer id;
	private String typeName;
	private String typeDes;
	private String typeCode;
	private Integer typeOrder;
	private String extFirst;
	private String extSecond;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDes() {
		return typeDes;
	}
	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getExtFirst() {
		return extFirst;
	}
	public void setExtFirst(String extFirst) {
		this.extFirst = extFirst;
	}
	public String getExtSecond() {
		return extSecond;
	}
	public void setExtSecond(String extSecond) {
		this.extSecond = extSecond;
	}
	public Integer getTypeOrder() {
		return typeOrder;
	}
	public void setTypeOrder(Integer typeOrder) {
		this.typeOrder = typeOrder;
	}
}
