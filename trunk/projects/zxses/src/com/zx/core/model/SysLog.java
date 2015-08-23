package com.zx.core.model;

import java.util.Date;

/**
 * SysLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysLog implements java.io.Serializable {

	// Fields

	private Long id;
	private String level;
	private String title;
	private String content;
	private Date createTime;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** minimal constructor */
	public SysLog(String level, String title, String content, Date createTime,
			Integer recordStatus) {
		this.level = level;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SysLog(String level, String title, String content, Date createTime,
			Date updateTime, Integer recordStatus) {
		this.level = level;
		this.title = title;
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

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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