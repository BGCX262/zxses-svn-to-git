package com.zx.es.model;

import java.util.Date;

/**
 * BasinfStudentFamily entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfStudentFamily implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfStudent basinfStudent;
	private BasinfFamilyRelation basinfFamilyRelation;
	private String familyName;
	private String unitName;
	private String positionName;
	private String mobile;
	private Integer isSendSms;
	private Date updateTime;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public BasinfStudentFamily() {
	}

	/** minimal constructor */
	public BasinfStudentFamily(BasinfStudent basinfStudent,
			BasinfFamilyRelation basinfFamilyRelation, Integer recordStatus) {
		this.basinfStudent = basinfStudent;
		this.basinfFamilyRelation = basinfFamilyRelation;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfStudentFamily(BasinfStudent basinfStudent,
			BasinfFamilyRelation basinfFamilyRelation, String familyName,
			String unitName, String positionName, String mobile,
			Integer isSendSms, Date updateTime, Integer recordStatus) {
		this.basinfStudent = basinfStudent;
		this.basinfFamilyRelation = basinfFamilyRelation;
		this.familyName = familyName;
		this.unitName = unitName;
		this.positionName = positionName;
		this.mobile = mobile;
		this.isSendSms = isSendSms;
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

	public BasinfStudent getBasinfStudent() {
		return this.basinfStudent;
	}

	public void setBasinfStudent(BasinfStudent basinfStudent) {
		this.basinfStudent = basinfStudent;
	}

	public BasinfFamilyRelation getBasinfFamilyRelation() {
		return this.basinfFamilyRelation;
	}

	public void setBasinfFamilyRelation(
			BasinfFamilyRelation basinfFamilyRelation) {
		this.basinfFamilyRelation = basinfFamilyRelation;
	}

	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getIsSendSms() {
		return this.isSendSms;
	}

	public void setIsSendSms(Integer isSendSms) {
		this.isSendSms = isSendSms;
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