package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SubjectiveMark entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SubjectiveMark implements java.io.Serializable {

	// Fields

	private Integer id;
	private SubjectiveBasis subjectiveBasis;
	private String markNo;
	private String markName;
	private Double markValue;
	private Date updateTime;
	private Integer recordStatus;
	private Set subjectiveTeacherMarks = new HashSet(0);

	// Constructors

	/** default constructor */
	public SubjectiveMark() {
	}

	/** minimal constructor */
	public SubjectiveMark(SubjectiveBasis subjectiveBasis, String markNo,
			String markName, Integer recordStatus) {
		this.subjectiveBasis = subjectiveBasis;
		this.markNo = markNo;
		this.markName = markName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SubjectiveMark(SubjectiveBasis subjectiveBasis, String markNo,
			String markName, Double markValue, Date updateTime,
			Integer recordStatus, Set subjectiveTeacherMarks) {
		this.subjectiveBasis = subjectiveBasis;
		this.markNo = markNo;
		this.markName = markName;
		this.markValue = markValue;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.subjectiveTeacherMarks = subjectiveTeacherMarks;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SubjectiveBasis getSubjectiveBasis() {
		return this.subjectiveBasis;
	}

	public void setSubjectiveBasis(SubjectiveBasis subjectiveBasis) {
		this.subjectiveBasis = subjectiveBasis;
	}

	public String getMarkNo() {
		return this.markNo;
	}

	public void setMarkNo(String markNo) {
		this.markNo = markNo;
	}

	public String getMarkName() {
		return this.markName;
	}

	public void setMarkName(String markName) {
		this.markName = markName;
	}

	public Double getMarkValue() {
		return this.markValue;
	}

	public void setMarkValue(Double markValue) {
		this.markValue = markValue;
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

	public Set getSubjectiveTeacherMarks() {
		return this.subjectiveTeacherMarks;
	}

	public void setSubjectiveTeacherMarks(Set subjectiveTeacherMarks) {
		this.subjectiveTeacherMarks = subjectiveTeacherMarks;
	}

}