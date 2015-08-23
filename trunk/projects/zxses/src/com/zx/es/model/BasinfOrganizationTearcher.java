package com.zx.es.model;

import java.util.Date;

/**
 * BasinfOrganizationTearcher entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfOrganizationTearcher implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfTeacher basinfTeacher;
	private BasinfOrganization basinfOrganization;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public BasinfOrganizationTearcher() {
	}

	/** minimal constructor */
	public BasinfOrganizationTearcher(BasinfTeacher basinfTeacher,
			BasinfOrganization basinfOrganization, Integer recordStatus) {
		this.basinfTeacher = basinfTeacher;
		this.basinfOrganization = basinfOrganization;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfOrganizationTearcher(BasinfTeacher basinfTeacher,
			BasinfOrganization basinfOrganization, Date updateTime,
			Integer recordStatus) {
		this.basinfTeacher = basinfTeacher;
		this.basinfOrganization = basinfOrganization;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
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

	public BasinfOrganization getBasinfOrganization() {
		return this.basinfOrganization;
	}

	public void setBasinfOrganization(BasinfOrganization basinfOrganization) {
		this.basinfOrganization = basinfOrganization;
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

}