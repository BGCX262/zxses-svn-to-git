package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfClass entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfClass implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfTeacher basinfTeacher;
	private BasinfGrade basinfGrade;
	private BasinfYear basinfYear;
	private String classCode;
	private String className;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfClassSubjectTeachers = new HashSet(0);
	private Set basinfClassStudents = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfClass() {
	}

	/** minimal constructor */
	public BasinfClass(BasinfGrade basinfGrade, BasinfYear basinfYear,
			String classCode, String className, Integer recordStatus) {
		this.basinfGrade = basinfGrade;
		this.basinfYear = basinfYear;
		this.classCode = classCode;
		this.className = className;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfClass(BasinfTeacher basinfTeacher, BasinfGrade basinfGrade,
			BasinfYear basinfYear, String classCode, String className,
			Date updateTime, Integer recordStatus,
			Set basinfClassSubjectTeachers, Set basinfClassStudents) {
		this.basinfTeacher = basinfTeacher;
		this.basinfGrade = basinfGrade;
		this.basinfYear = basinfYear;
		this.classCode = classCode;
		this.className = className;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfClassSubjectTeachers = basinfClassSubjectTeachers;
		this.basinfClassStudents = basinfClassStudents;
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

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public Set getBasinfClassSubjectTeachers() {
		return this.basinfClassSubjectTeachers;
	}

	public void setBasinfClassSubjectTeachers(Set basinfClassSubjectTeachers) {
		this.basinfClassSubjectTeachers = basinfClassSubjectTeachers;
	}

	public Set getBasinfClassStudents() {
		return this.basinfClassStudents;
	}

	public void setBasinfClassStudents(Set basinfClassStudents) {
		this.basinfClassStudents = basinfClassStudents;
	}

}