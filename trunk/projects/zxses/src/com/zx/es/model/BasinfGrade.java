package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfGrade entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfGrade implements java.io.Serializable {

	// Fields

	private Integer id;
	private String gradeCode;
	private String gradeName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfClasses = new HashSet(0);
	private Set basinfSubjectTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfGrade() {
	}

	/** minimal constructor */
	public BasinfGrade(String gradeCode, String gradeName, Integer recordStatus) {
		this.gradeCode = gradeCode;
		this.gradeName = gradeName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfGrade(String gradeCode, String gradeName, Date updateTime,
			Integer recordStatus, Set basinfClasses, Set basinfSubjectTeachers) {
		this.gradeCode = gradeCode;
		this.gradeName = gradeName;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfClasses = basinfClasses;
		this.basinfSubjectTeachers = basinfSubjectTeachers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGradeCode() {
		return this.gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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

	public Set getBasinfClasses() {
		return this.basinfClasses;
	}

	public void setBasinfClasses(Set basinfClasses) {
		this.basinfClasses = basinfClasses;
	}

	public Set getBasinfSubjectTeachers() {
		return this.basinfSubjectTeachers;
	}

	public void setBasinfSubjectTeachers(Set basinfSubjectTeachers) {
		this.basinfSubjectTeachers = basinfSubjectTeachers;
	}

}