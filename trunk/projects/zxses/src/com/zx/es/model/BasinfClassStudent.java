package com.zx.es.model;

import java.util.Date;

/**
 * BasinfClassStudent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfClassStudent implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfClass basinfClass;
	private BasinfStudent basinfStudent;
	private Integer setNo;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public BasinfClassStudent() {
	}

	/** minimal constructor */
	public BasinfClassStudent(BasinfClass basinfClass,
			BasinfStudent basinfStudent, Integer recordStatus) {
		this.basinfClass = basinfClass;
		this.basinfStudent = basinfStudent;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfClassStudent(BasinfClass basinfClass,
			BasinfStudent basinfStudent, Integer setNo, Date updateTime,
			Integer recordStatus) {
		this.basinfClass = basinfClass;
		this.basinfStudent = basinfStudent;
		this.setNo = setNo;
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

	public BasinfClass getBasinfClass() {
		return this.basinfClass;
	}

	public void setBasinfClass(BasinfClass basinfClass) {
		this.basinfClass = basinfClass;
	}

	public BasinfStudent getBasinfStudent() {
		return this.basinfStudent;
	}

	public void setBasinfStudent(BasinfStudent basinfStudent) {
		this.basinfStudent = basinfStudent;
	}

	public Integer getSetNo() {
		return this.setNo;
	}

	public void setSetNo(Integer setNo) {
		this.setNo = setNo;
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