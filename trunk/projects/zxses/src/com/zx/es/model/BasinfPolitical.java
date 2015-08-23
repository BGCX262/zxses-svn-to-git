package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfPolitical entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfPolitical implements java.io.Serializable {

	// Fields

	private Integer id;
	private String politicalCode;
	private String politicalName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfStudents = new HashSet(0);
	private Set basinfTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfPolitical() {
	}

	/** minimal constructor */
	public BasinfPolitical(String politicalCode, String politicalName,
			Integer recordStatus) {
		this.politicalCode = politicalCode;
		this.politicalName = politicalName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfPolitical(String politicalCode, String politicalName,
			Date updateTime, Integer recordStatus, Set basinfStudents,
			Set basinfTeachers) {
		this.politicalCode = politicalCode;
		this.politicalName = politicalName;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfStudents = basinfStudents;
		this.basinfTeachers = basinfTeachers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPoliticalCode() {
		return this.politicalCode;
	}

	public void setPoliticalCode(String politicalCode) {
		this.politicalCode = politicalCode;
	}

	public String getPoliticalName() {
		return this.politicalName;
	}

	public void setPoliticalName(String politicalName) {
		this.politicalName = politicalName;
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

	public Set getBasinfStudents() {
		return this.basinfStudents;
	}

	public void setBasinfStudents(Set basinfStudents) {
		this.basinfStudents = basinfStudents;
	}

	public Set getBasinfTeachers() {
		return this.basinfTeachers;
	}

	public void setBasinfTeachers(Set basinfTeachers) {
		this.basinfTeachers = basinfTeachers;
	}

}