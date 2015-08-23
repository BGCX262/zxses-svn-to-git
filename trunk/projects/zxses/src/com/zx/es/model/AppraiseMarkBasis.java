package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AppraiseMarkBasis entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppraiseMarkBasis implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfTerm basinfTerm;
	private String appraiseNo;
	private String appraiseName;
	private Integer isActivate;
	private Integer isShow;
	private Double minMark;
	private Double maxMark;
	private Integer isFinish;
	private String remark;
	private Date updateTime;
	private Integer recordStatus;
	private Set appraiseMarkTeachers = new HashSet(0);
	private Set appraiseTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public AppraiseMarkBasis() {
	}

	/** minimal constructor */
	public AppraiseMarkBasis(BasinfTerm basinfTerm, String appraiseNo,
			String appraiseName, Integer recordStatus) {
		this.basinfTerm = basinfTerm;
		this.appraiseNo = appraiseNo;
		this.appraiseName = appraiseName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public AppraiseMarkBasis(BasinfTerm basinfTerm, String appraiseNo,
			String appraiseName, Integer isActivate, Integer isShow,
			Double minMark, Double maxMark, Integer isFinish, String remark,
			Date updateTime, Integer recordStatus, Set appraiseMarkTeachers,
			Set appraiseTeachers) {
		this.basinfTerm = basinfTerm;
		this.appraiseNo = appraiseNo;
		this.appraiseName = appraiseName;
		this.isActivate = isActivate;
		this.isShow = isShow;
		this.minMark = minMark;
		this.maxMark = maxMark;
		this.isFinish = isFinish;
		this.remark = remark;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.appraiseMarkTeachers = appraiseMarkTeachers;
		this.appraiseTeachers = appraiseTeachers;
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

	public String getAppraiseNo() {
		return this.appraiseNo;
	}

	public void setAppraiseNo(String appraiseNo) {
		this.appraiseNo = appraiseNo;
	}

	public String getAppraiseName() {
		return this.appraiseName;
	}

	public void setAppraiseName(String appraiseName) {
		this.appraiseName = appraiseName;
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

	public Double getMinMark() {
		return this.minMark;
	}

	public void setMinMark(Double minMark) {
		this.minMark = minMark;
	}

	public Double getMaxMark() {
		return this.maxMark;
	}

	public void setMaxMark(Double maxMark) {
		this.maxMark = maxMark;
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

	public Set getAppraiseMarkTeachers() {
		return this.appraiseMarkTeachers;
	}

	public void setAppraiseMarkTeachers(Set appraiseMarkTeachers) {
		this.appraiseMarkTeachers = appraiseMarkTeachers;
	}

	public Set getAppraiseTeachers() {
		return this.appraiseTeachers;
	}

	public void setAppraiseTeachers(Set appraiseTeachers) {
		this.appraiseTeachers = appraiseTeachers;
	}

}