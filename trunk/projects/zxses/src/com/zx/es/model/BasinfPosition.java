package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfPosition entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfPosition implements java.io.Serializable {

	// Fields

	private Integer id;
	private String positionNo;
	private String positionName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfPosition() {
	}

	/** minimal constructor */
	public BasinfPosition(String positionNo, String positionName,
			Integer recordStatus) {
		this.positionNo = positionNo;
		this.positionName = positionName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfPosition(String positionNo, String positionName,
			Date updateTime, Integer recordStatus, Set basinfTeachers) {
		this.positionNo = positionNo;
		this.positionName = positionName;
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

	public String getPositionNo() {
		return this.positionNo;
	}

	public void setPositionNo(String positionNo) {
		this.positionNo = positionNo;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
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