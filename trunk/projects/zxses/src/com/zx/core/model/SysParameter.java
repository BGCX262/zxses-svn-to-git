package com.zx.core.model;

import java.util.Date;

/**
 * SysParameter entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysParameter implements java.io.Serializable {

	// Fields

	private Long id;
	private String code;
	private String name;
	private String content;
	private Date createTime;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public SysParameter() {
	}

	/** minimal constructor */
	public SysParameter(String code, String name, String content,
			Date createTime, Integer recordStatus) {
		this.code = code;
		this.name = name;
		this.content = content;
		this.createTime = createTime;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SysParameter(String code, String name, String content,
			Date createTime, Date updateTime, Integer recordStatus) {
		this.code = code;
		this.name = name;
		this.content = content;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

}