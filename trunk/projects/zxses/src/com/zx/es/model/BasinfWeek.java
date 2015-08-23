package com.zx.es.model;

import java.util.Date;

/**
 * BasinfWeek entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfWeek implements java.io.Serializable {

	// Fields

	private Integer id;
	private String weekNo;
	private String weekName;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public BasinfWeek() {
	}

	/** minimal constructor */
	public BasinfWeek(String weekNo, String weekName, Integer recordStatus) {
		this.weekNo = weekNo;
		this.weekName = weekName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfWeek(String weekNo, String weekName, Date updateTime,
			Integer recordStatus) {
		this.weekNo = weekNo;
		this.weekName = weekName;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWeekNo() {
		return this.weekNo;
	}

	public void setWeekNo(String weekNo) {
		this.weekNo = weekNo;
	}

	public String getWeekName() {
		return this.weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
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