package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfFamilyRelation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfFamilyRelation implements java.io.Serializable {

	// Fields

	private Integer id;
	private String famRelationCode;
	private String famRelationName;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfStudentFamilies = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfFamilyRelation() {
	}

	/** minimal constructor */
	public BasinfFamilyRelation(String famRelationCode, String famRelationName,
			Integer recordStatus) {
		this.famRelationCode = famRelationCode;
		this.famRelationName = famRelationName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfFamilyRelation(String famRelationCode, String famRelationName,
			Date updateTime, Integer recordStatus, Set basinfStudentFamilies) {
		this.famRelationCode = famRelationCode;
		this.famRelationName = famRelationName;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfStudentFamilies = basinfStudentFamilies;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFamRelationCode() {
		return this.famRelationCode;
	}

	public void setFamRelationCode(String famRelationCode) {
		this.famRelationCode = famRelationCode;
	}

	public String getFamRelationName() {
		return this.famRelationName;
	}

	public void setFamRelationName(String famRelationName) {
		this.famRelationName = famRelationName;
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

	public Set getBasinfStudentFamilies() {
		return this.basinfStudentFamilies;
	}

	public void setBasinfStudentFamilies(Set basinfStudentFamilies) {
		this.basinfStudentFamilies = basinfStudentFamilies;
	}

}