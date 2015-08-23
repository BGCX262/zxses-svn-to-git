package com.zx.es.model;

import java.util.Date;

/**
 * AppraiseMarkResult entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppraiseMarkResult implements java.io.Serializable {

	// Fields

	private Integer id;
	private AppraiseMarkTeacher appraiseMarkTeacher;
	private AppraiseTeacher appraiseTeacher;
	private Double appraiseMarkValue;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public AppraiseMarkResult() {
	}

	/** minimal constructor */
	public AppraiseMarkResult(AppraiseMarkTeacher appraiseMarkTeacher,
			AppraiseTeacher appraiseTeacher, Integer recordStatus) {
		this.appraiseMarkTeacher = appraiseMarkTeacher;
		this.appraiseTeacher = appraiseTeacher;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public AppraiseMarkResult(AppraiseMarkTeacher appraiseMarkTeacher,
			AppraiseTeacher appraiseTeacher, Double appraiseMarkValue,
			Date updateTime, Integer recordStatus) {
		this.appraiseMarkTeacher = appraiseMarkTeacher;
		this.appraiseTeacher = appraiseTeacher;
		this.appraiseMarkValue = appraiseMarkValue;
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

	public AppraiseMarkTeacher getAppraiseMarkTeacher() {
		return this.appraiseMarkTeacher;
	}

	public void setAppraiseMarkTeacher(AppraiseMarkTeacher appraiseMarkTeacher) {
		this.appraiseMarkTeacher = appraiseMarkTeacher;
	}

	public AppraiseTeacher getAppraiseTeacher() {
		return this.appraiseTeacher;
	}

	public void setAppraiseTeacher(AppraiseTeacher appraiseTeacher) {
		this.appraiseTeacher = appraiseTeacher;
	}

	public Double getAppraiseMarkValue() {
		return this.appraiseMarkValue;
	}

	public void setAppraiseMarkValue(Double appraiseMarkValue) {
		this.appraiseMarkValue = appraiseMarkValue;
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