package com.zx.es.model;

import java.util.Date;

/**
 * BasinfSubjectTeacher entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfSubjectTeacher implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfTeacher basinfTeacher;
	private BasinfSubject basinfSubject;
	private BasinfGrade basinfGrade;
	private BasinfYear basinfYear;
	private Integer isMaster;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public BasinfSubjectTeacher() {
	}

	/** minimal constructor */
	public BasinfSubjectTeacher(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfSubjectTeacher(BasinfTeacher basinfTeacher,
			BasinfSubject basinfSubject, BasinfGrade basinfGrade,
			BasinfYear basinfYear, Integer isMaster, Date updateTime,
			Integer recordStatus) {
		this.basinfTeacher = basinfTeacher;
		this.basinfSubject = basinfSubject;
		this.basinfGrade = basinfGrade;
		this.basinfYear = basinfYear;
		this.isMaster = isMaster;
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

	public BasinfTeacher getBasinfTeacher() {
		return this.basinfTeacher;
	}

	public void setBasinfTeacher(BasinfTeacher basinfTeacher) {
		this.basinfTeacher = basinfTeacher;
	}

	public BasinfSubject getBasinfSubject() {
		return this.basinfSubject;
	}

	public void setBasinfSubject(BasinfSubject basinfSubject) {
		this.basinfSubject = basinfSubject;
	}

	public BasinfGrade getBasinfGrade() {
		return this.basinfGrade;
	}

	public void setBasinfGrade(BasinfGrade basinfGrade) {
		this.basinfGrade = basinfGrade;
	}

	public BasinfYear getBasinfYear() {
		return this.basinfYear;
	}

	public void setBasinfYear(BasinfYear basinfYear) {
		this.basinfYear = basinfYear;
	}

	public Integer getIsMaster() {
		return this.isMaster;
	}

	public void setIsMaster(Integer isMaster) {
		this.isMaster = isMaster;
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