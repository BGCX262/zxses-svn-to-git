package com.zx.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * HrUserLogType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HrUserLogType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String code;
	private Date createTime;
	private Date updateTime;
	private Integer recordStatus;
	private Set hrUserLogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public HrUserLogType() {
	}

	/** minimal constructor */
	public HrUserLogType(String name, String code, Date createTime,
			Integer recordStatus) {
		this.name = name;
		this.code = code;
		this.createTime = createTime;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public HrUserLogType(String name, String code, Date createTime,
			Date updateTime, Integer recordStatus, Set hrUserLogs) {
		this.name = name;
		this.code = code;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.hrUserLogs = hrUserLogs;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Set getHrUserLogs() {
		return this.hrUserLogs;
	}

	public void setHrUserLogs(Set hrUserLogs) {
		this.hrUserLogs = hrUserLogs;
	}

}