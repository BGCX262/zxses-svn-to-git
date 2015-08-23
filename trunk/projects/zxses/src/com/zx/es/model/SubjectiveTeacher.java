package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SubjectiveTeacher entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SubjectiveTeacher implements java.io.Serializable {

	// Fields

	private Integer id;
	private SubjectiveBasis subjectiveBasis;
	private BasinfTeacher basinfTeacher;
	private Date updateTime;
	private Integer recordStatus;
	private Set subjectiveTeacherMarks = new HashSet(0);

	// Constructors

	/** default constructor */
	public SubjectiveTeacher() {
	}

	/** minimal constructor */
	public SubjectiveTeacher(SubjectiveBasis subjectiveBasis,
			BasinfTeacher basinfTeacher, Integer recordStatus) {
		this.subjectiveBasis = subjectiveBasis;
		this.basinfTeacher = basinfTeacher;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SubjectiveTeacher(SubjectiveBasis subjectiveBasis,
			BasinfTeacher basinfTeacher, Date updateTime, Integer recordStatus,
			Set subjectiveTeacherMarks) {
		this.subjectiveBasis = subjectiveBasis;
		this.basinfTeacher = basinfTeacher;
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

	public BasinfTeacher getBasinfTeacher() {
		return this.basinfTeacher;
	}

	public void setBasinfTeacher(BasinfTeacher basinfTeacher) {
		this.basinfTeacher = basinfTeacher;
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