package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SubjectiveBasis entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SubjectiveBasis implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfTerm basinfTerm;
	private String subjectiveNo;
	private String subjectiveName;
	private Integer subjectiveType;
	private Integer isActivate;
	private Integer isShow;
	private Integer isFinish;
	private String remark;
	private Date updateTime;
	private Integer recordStatus;
	private Set subjectiveMarks = new HashSet(0);
	private Set subjectiveTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public SubjectiveBasis() {
	}

	/** minimal constructor */
	public SubjectiveBasis(BasinfTerm basinfTerm, String subjectiveNo,
			String subjectiveName, Integer subjectiveType, Integer recordStatus) {
		this.basinfTerm = basinfTerm;
		this.subjectiveNo = subjectiveNo;
		this.subjectiveName = subjectiveName;
		this.subjectiveType = subjectiveType;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SubjectiveBasis(BasinfTerm basinfTerm, String subjectiveNo,
			String subjectiveName, Integer subjectiveType, Integer isActivate,
			Integer isShow, Integer isFinish, String remark, Date updateTime,
			Integer recordStatus, Set subjectiveMarks, Set subjectiveTeachers) {
		this.basinfTerm = basinfTerm;
		this.subjectiveNo = subjectiveNo;
		this.subjectiveName = subjectiveName;
		this.subjectiveType = subjectiveType;
		this.isActivate = isActivate;
		this.isShow = isShow;
		this.isFinish = isFinish;
		this.remark = remark;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.subjectiveMarks = subjectiveMarks;
		this.subjectiveTeachers = subjectiveTeachers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BasinfTerm getBasinfTerm() {
		return this.basinfTerm;
	}

	public void setBasinfTerm(BasinfTerm basinfTerm) {
		this.basinfTerm = basinfTerm;
	}

	public String getSubjectiveNo() {
		return this.subjectiveNo;
	}

	public void setSubjectiveNo(String subjectiveNo) {
		this.subjectiveNo = subjectiveNo;
	}

	public String getSubjectiveName() {
		return this.subjectiveName;
	}

	public void setSubjectiveName(String subjectiveName) {
		this.subjectiveName = subjectiveName;
	}

	public Integer getSubjectiveType() {
		return this.subjectiveType;
	}

	public void setSubjectiveType(Integer subjectiveType) {
		this.subjectiveType = subjectiveType;
	}

	public Integer getIsActivate() {
		return this.isActivate;
	}

	public void setIsActivate(Integer isActivate) {
		this.isActivate = isActivate;
	}

	public Integer getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getIsFinish() {
		return this.isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Set getSubjectiveMarks() {
		return this.subjectiveMarks;
	}

	public void setSubjectiveMarks(Set subjectiveMarks) {
		this.subjectiveMarks = subjectiveMarks;
	}

	public Set getSubjectiveTeachers() {
		return this.subjectiveTeachers;
	}

	public void setSubjectiveTeachers(Set subjectiveTeachers) {
		this.subjectiveTeachers = subjectiveTeachers;
	}

}