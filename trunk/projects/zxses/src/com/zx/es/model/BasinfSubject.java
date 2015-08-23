package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfSubject entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfSubject implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer subjectId;
	private String subjectNo;
	private String subjectName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfSubjectTeachers = new HashSet(0);
	private Set basinfClassSubjectTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfSubject() {
	}

	/** minimal constructor */
	public BasinfSubject(Integer subjectId, String subjectNo,
			String subjectName, Integer recordStatus) {
		this.subjectId = subjectId;
		this.subjectNo = subjectNo;
		this.subjectName = subjectName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfSubject(Integer subjectId, String subjectNo,
			String subjectName, Date updateTime, Integer recordStatus,
			Set basinfSubjectTeachers, Set basinfClassSubjectTeachers) {
		this.subjectId = subjectId;
		this.subjectNo = subjectNo;
		this.subjectName = subjectName;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfSubjectTeachers = basinfSubjectTeachers;
		this.basinfClassSubjectTeachers = basinfClassSubjectTeachers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectNo() {
		return this.subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public Set getBasinfSubjectTeachers() {
		return this.basinfSubjectTeachers;
	}

	public void setBasinfSubjectTeachers(Set basinfSubjectTeachers) {
		this.basinfSubjectTeachers = basinfSubjectTeachers;
	}

	public Set getBasinfClassSubjectTeachers() {
		return this.basinfClassSubjectTeachers;
	}

	public void setBasinfClassSubjectTeachers(Set basinfClassSubjectTeachers) {
		this.basinfClassSubjectTeachers = basinfClassSubjectTeachers;
	}

}