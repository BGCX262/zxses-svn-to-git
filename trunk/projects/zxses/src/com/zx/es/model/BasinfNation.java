package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfNation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfNation implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nationCode;
	private String nationName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfStudents = new HashSet(0);
	private Set basinfTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfNation() {
	}

	/** minimal constructor */
	public BasinfNation(String nationCode, String nationName,
			Integer recordStatus) {
		this.nationCode = nationCode;
		this.nationName = nationName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfNation(String nationCode, String nationName, Date updateTime,
			Integer recordStatus, Set basinfStudents, Set basinfTeachers) {
		this.nationCode = nationCode;
		this.nationName = nationName;
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

	public String getNationCode() {
		return this.nationCode;
	}

	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}

	public String getNationName() {
		return this.nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
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