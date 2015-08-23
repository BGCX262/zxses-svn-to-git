package com.zx.es.model;

import java.util.Date;

/**
 * SubjectiveTeacherMark entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SubjectiveTeacherMark implements java.io.Serializable {

	// Fields

	private Integer id;
	private SubjectiveMark subjectiveMark;
	private SubjectiveTeacher subjectiveTeacher;
	private String remark;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public SubjectiveTeacherMark() {
	}

	/** minimal constructor */
	public SubjectiveTeacherMark(SubjectiveMark subjectiveMark,
			SubjectiveTeacher subjectiveTeacher, Integer recordStatus) {
		this.subjectiveMark = subjectiveMark;
		this.subjectiveTeacher = subjectiveTeacher;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SubjectiveTeacherMark(SubjectiveMark subjectiveMark,
			SubjectiveTeacher subjectiveTeacher, String remark,
			Date updateTime, Integer recordStatus) {
		this.subjectiveMark = subjectiveMark;
		this.subjectiveTeacher = subjectiveTeacher;
		this.remark = remark;
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

	public SubjectiveMark getSubjectiveMark() {
		return this.subjectiveMark;
	}

	public void setSubjectiveMark(SubjectiveMark subjectiveMark) {
		this.subjectiveMark = subjectiveMark;
	}

	public SubjectiveTeacher getSubjectiveTeacher() {
		return this.subjectiveTeacher;
	}

	public void setSubjectiveTeacher(SubjectiveTeacher subjectiveTeacher) {
		this.subjectiveTeacher = subjectiveTeacher;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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