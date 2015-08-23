package com.zx.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * HrUserType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HrUserType implements java.io.Serializable {

	// Fields

	private Long id;
	private String code;
	private String content;
	private Date createTime;
	private Date updateTime;
	private Integer recordStatus;
	private Set hrUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public HrUserType() {
	}

	/** minimal constructor */
	public HrUserType(String code, String content, Date createTime,
			Integer recordStatus) {
		this.code = code;
		this.content = content;
		this.createTime = createTime;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public HrUserType(String code, String content, Date createTime,
			Date updateTime, Integer recordStatus, Set hrUsers) {
		this.code = code;
		this.content = content;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.hrUsers = hrUsers;
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

	public Set getHrUsers() {
		return this.hrUsers;
	}

	public void setHrUsers(Set hrUsers) {
		this.hrUsers = hrUsers;
	}

}