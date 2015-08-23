package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfYear entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfYear implements java.io.Serializable {

	// Fields

	private Integer id;
	private String yearCode;
	private String yearName;
	private Integer yearIsCurr;
	private Integer yearIsShow;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfTerms = new HashSet(0);
	private Set basinfSubjectTeachers = new HashSet(0);
	private Set basinfClasses = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfYear() {
	}

	/** minimal constructor */
	public BasinfYear(String yearCode, Integer yearIsCurr, Integer yearIsShow,
			Integer recordStatus) {
		this.yearCode = yearCode;
		this.yearIsCurr = yearIsCurr;
		this.yearIsShow = yearIsShow;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfYear(String yearCode, String yearName, Integer yearIsCurr,
			Integer yearIsShow, Date updateTime, Integer recordStatus,
			Set basinfTerms, Set basinfSubjectTeachers, Set basinfClasses) {
		this.yearCode = yearCode;
		this.yearName = yearName;
		this.yearIsCurr = yearIsCurr;
		this.yearIsShow = yearIsShow;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfTerms = basinfTerms;
		this.basinfSubjectTeachers = basinfSubjectTeachers;
		this.basinfClasses = basinfClasses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYearCode() {
		return this.yearCode;
	}

	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}

	public String getYearName() {
		return this.yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public Integer getYearIsCurr() {
		return this.yearIsCurr;
	}

	public void setYearIsCurr(Integer yearIsCurr) {
		this.yearIsCurr = yearIsCurr;
	}

	public Integer getYearIsShow() {
		return this.yearIsShow;
	}

	public void setYearIsShow(Integer yearIsShow) {
		this.yearIsShow = yearIsShow;
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

	public Set getBasinfTerms() {
		return this.basinfTerms;
	}

	public void setBasinfTerms(Set basinfTerms) {
		this.basinfTerms = basinfTerms;
	}

	public Set getBasinfSubjectTeachers() {
		return this.basinfSubjectTeachers;
	}

	public void setBasinfSubjectTeachers(Set basinfSubjectTeachers) {
		this.basinfSubjectTeachers = basinfSubjectTeachers;
	}

	public Set getBasinfClasses() {
		return this.basinfClasses;
	}

	public void setBasinfClasses(Set basinfClasses) {
		this.basinfClasses = basinfClasses;
	}

}