package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfAccountKind entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfAccountKind implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accountKindCode;
	private String accountKindName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfStudents = new HashSet(0);
	private Set basinfTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfAccountKind() {
	}

	/** minimal constructor */
	public BasinfAccountKind(String accountKindCode, String accountKindName,
			Integer recordStatus) {
		this.accountKindCode = accountKindCode;
		this.accountKindName = accountKindName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfAccountKind(String accountKindCode, String accountKindName,
			Date updateTime, Integer recordStatus, Set basinfStudents,
			Set basinfTeachers) {
		this.accountKindCode = accountKindCode;
		this.accountKindName = accountKindName;
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

	public String getAccountKindCode() {
		return this.accountKindCode;
	}

	public void setAccountKindCode(String accountKindCode) {
		this.accountKindCode = accountKindCode;
	}

	public String getAccountKindName() {
		return this.accountKindName;
	}

	public void setAccountKindName(String accountKindName) {
		this.accountKindName = accountKindName;
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