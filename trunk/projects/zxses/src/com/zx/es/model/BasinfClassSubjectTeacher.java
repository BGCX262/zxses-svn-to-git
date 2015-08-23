package com.zx.es.model;

import java.util.Date;

/**
 * BasinfClassSubjectTeacher entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfClassSubjectTeacher implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfTeacher basinfTeacher;
	private BasinfClass basinfClass;
	private BasinfSubject basinfSubject;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public BasinfClassSubjectTeacher() {
	}

	/** minimal constructor */
	public BasinfClassSubjectTeacher(BasinfTeacher basinfTeacher,
			BasinfClass basinfClass, BasinfSubject basinfSubject,
			Integer recordStatus) {
		this.basinfTeacher = basinfTeacher;
		this.basinfClass = basinfClass;
		this.basinfSubject = basinfSubject;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfClassSubjectTeacher(BasinfTeacher basinfTeacher,
			BasinfClass basinfClass, BasinfSubject basinfSubject,
			Date updateTime, Integer recordStatus) {
		this.basinfTeacher = basinfTeacher;
		this.basinfClass = basinfClass;
		this.basinfSubject = basinfSubject;
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

	public BasinfClass getBasinfClass() {
		return this.basinfClass;
	}

	public void setBasinfClass(BasinfClass basinfClass) {
		this.basinfClass = basinfClass;
	}

	public BasinfSubject getBasinfSubject() {
		return this.basinfSubject;
	}

	public void setBasinfSubject(BasinfSubject basinfSubject) {
		this.basinfSubject = basinfSubject;
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