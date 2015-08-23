package com.agilefly.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * SysType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysType implements java.io.Serializable {

	// Fields

	private Integer id;
	/*类型标识*/
	private String typeSymbol;
	/*类型名称*/
	private String typeName;
	/*类型描述*/
	private String typeDes;
	/*类型编码*/
	private String typeCode;
	/*类型排序号*/
	private Integer typeOrder;
	/*是否可用*/
	private Byte available;
	/*扩展标识1*/
	private String extFirst;
	/*扩张表示2*/
	private String extSecond;

	// Constructors

	/** default constructor */
	public SysType() {
	}

	/** full constructor */
	public SysType(String typeName, String typeDes, String typeCode) {
		this.typeName = typeName;
		this.typeDes = typeDes;
		this.typeCode = typeCode;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDes() {
		return this.typeDes;
	}

	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}

	public String getTypeCode() {
		return this.typeCode;
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

	public Byte getAvailable() {
		return available;
	}

	public void setAvailable(Byte available) {
		this.available = available;
	}

	public String getTypeSymbol() {
		return typeSymbol;
	}

	public void setTypeSymbol(String typeSymbol) {
		this.typeSymbol = typeSymbol;
	}

}