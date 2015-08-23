package com.zx.core.model;

import java.util.Date;

/**
 * HrUserLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HrUserLog implements java.io.Serializable {

	// Fields

	private Long id;
	private HrUserLogType hrUserLogType;
	private HrUser hrUser;
	private String title;
	private String content;
	private String ip;
	private Date createTime;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public HrUserLog() {
	}

	/** minimal constructor */
	public HrUserLog(String title, String content, String ip,
			Integer recordStatus) {
		this.title = title;
		this.content = content;
		this.ip = ip;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public HrUserLog(HrUserLogType hrUserLogType, HrUser hrUser, String title,
			String content, String ip, Date createTime, Date updateTime,
			Integer recordStatus) {
		this.hrUserLogType = hrUserLogType;
		this.hrUser = hrUser;
		this.title = title;
		this.content = content;
		this.ip = ip;
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

	public HrUserLogType getHrUserLogType() {
		return this.hrUserLogType;
	}

	public void setHrUserLogType(HrUserLogType hrUserLogType) {
		this.hrUserLogType = hrUserLogType;
	}

	public HrUser getHrUser() {
		return this.hrUser;
	}

	public void setHrUser(HrUser hrUser) {
		this.hrUser = hrUser;
	}

	public String getTitle() {
		return title;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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