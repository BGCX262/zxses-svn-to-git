package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfWorkKind entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfWorkKind implements java.io.Serializable {

	// Fields

	private Integer id;
	private String workKindNo;
	private String workKindName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfWorkKind() {
	}

	/** minimal constructor */
	public BasinfWorkKind(String workKindNo, String workKindName,
			Integer recordStatus) {
		this.workKindNo = workKindNo;
		this.workKindName = workKindName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfWorkKind(String workKindNo, String workKindName,
			Date updateTime, Integer recordStatus, Set basinfTeachers) {
		this.workKindNo = workKindNo;
		this.workKindName = workKindName;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfTeachers = basinfTeachers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWorkKindNo() {
		return this.workKindNo;
	}

	public void setWorkKindNo(String workKindNo) {
		this.workKindNo = workKindNo;
	}

	public String getWorkKindName() {
		return this.workKindName;
	}

	public void setWorkKindName(String workKindName) {
		this.workKindName = workKindName;
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

}