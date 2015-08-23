package com.zx.es.model;

import java.util.Date;

/**
 * BasinfPeriod entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfPeriod implements java.io.Serializable {

	// Fields

	private Integer id;
	private String periodNo;
	private String periodName;
	private Integer isAfternoon;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public BasinfPeriod() {
	}

	/** minimal constructor */
	public BasinfPeriod(String periodNo, String periodName,
			Integer isAfternoon, Integer recordStatus) {
		this.periodNo = periodNo;
		this.periodName = periodName;
		this.isAfternoon = isAfternoon;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfPeriod(String periodNo, String periodName,
			Integer isAfternoon, Date updateTime, Integer recordStatus) {
		this.periodNo = periodNo;
		this.periodName = periodName;
		this.isAfternoon = isAfternoon;
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

	public String getPeriodNo() {
		return this.periodNo;
	}

	public void setPeriodNo(String periodNo) {
		this.periodNo = periodNo;
	}

	public String getPeriodName() {
		return this.periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public Integer getIsAfternoon() {
		return this.isAfternoon;
	}

	public void setIsAfternoon(Integer isAfternoon) {
		this.isAfternoon = isAfternoon;
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