package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfOrganization entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfOrganization implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfOrganization basinfOrganization;
	private Integer organizationNo;
	private String organizationCode;
	private String organizationName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfOrganizations = new HashSet(0);
	private Set basinfOrganizationTearchers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfOrganization() {
	}

	/** minimal constructor */
	public BasinfOrganization(String organizationCode, String organizationName,
			Integer recordStatus) {
		this.organizationCode = organizationCode;
		this.organizationName = organizationName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfOrganization(BasinfOrganization basinfOrganization,
			Integer organizationNo, String organizationCode,
			String organizationName, Date updateTime, Integer recordStatus,
			Set basinfOrganizations, Set basinfOrganizationTearchers) {
		this.basinfOrganization = basinfOrganization;
		this.organizationNo = organizationNo;
		this.organizationCode = organizationCode;
		this.organizationName = organizationName;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfOrganizations = basinfOrganizations;
		this.basinfOrganizationTearchers = basinfOrganizationTearchers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BasinfOrganization getBasinfOrganization() {
		return this.basinfOrganization;
	}

	public void setBasinfOrganization(BasinfOrganization basinfOrganization) {
		this.basinfOrganization = basinfOrganization;
	}

	public Integer getOrganizationNo() {
		return this.organizationNo;
	}

	public void setOrganizationNo(Integer organizationNo) {
		this.organizationNo = organizationNo;
	}

	public String getOrganizationCode() {
		return this.organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationName() {
		return this.organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
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

	public Set getBasinfOrganizations() {
		return this.basinfOrganizations;
	}

	public void setBasinfOrganizations(Set basinfOrganizations) {
		this.basinfOrganizations = basinfOrganizations;
	}

	public Set getBasinfOrganizationTearchers() {
		return this.basinfOrganizationTearchers;
	}

	public void setBasinfOrganizationTearchers(Set basinfOrganizationTearchers) {
		this.basinfOrganizationTearchers = basinfOrganizationTearchers;
	}

}