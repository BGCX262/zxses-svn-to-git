package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfSex entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfSex implements java.io.Serializable {

	// Fields

	private Integer id;
	private String sexCode;
	private String sexName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfTeachers = new HashSet(0);
	private Set basinfStudents = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfSex() {
	}

	/** minimal constructor */
	public BasinfSex(String sexCode, String sexName, Integer recordStatus) {
		this.sexCode = sexCode;
		this.sexName = sexName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfSex(String sexCode, String sexName, Date updateTime,
			Integer recordStatus, Set basinfTeachers, Set basinfStudents) {
		this.sexCode = sexCode;
		this.sexName = sexName;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfTeachers = basinfTeachers;
		this.basinfStudents = basinfStudents;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSexCode() {
		return this.sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getSexName() {
		return this.sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
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

	public Set getBasinfTeachers() {
		return this.basinfTeachers;
	}

	public void setBasinfTeachers(Set basinfTeachers) {
		this.basinfTeachers = basinfTeachers;
	}

	public Set getBasinfStudents() {
		return this.basinfStudents;
	}

	public void setBasinfStudents(Set basinfStudents) {
		this.basinfStudents = basinfStudents;
	}

}