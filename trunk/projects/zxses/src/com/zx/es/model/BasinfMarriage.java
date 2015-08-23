package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfMarriage entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfMarriage implements java.io.Serializable {

	// Fields

	private Integer id;
	private String marriageNo;
	private String marriageName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfMarriage() {
	}

	/** minimal constructor */
	public BasinfMarriage(String marriageNo, String marriageName,
			Integer recordStatus) {
		this.marriageNo = marriageNo;
		this.marriageName = marriageName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfMarriage(String marriageNo, String marriageName,
			Date updateTime, Integer recordStatus, Set basinfTeachers) {
		this.marriageNo = marriageNo;
		this.marriageName = marriageName;
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

	public String getMarriageNo() {
		return this.marriageNo;
	}

	public void setMarriageNo(String marriageNo) {
		this.marriageNo = marriageNo;
	}

	public String getMarriageName() {
		return this.marriageName;
	}

	public void setMarriageName(String marriageName) {
		this.marriageName = marriageName;
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