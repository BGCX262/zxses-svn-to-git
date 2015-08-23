package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfTerm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfTerm implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfYear basinfYear;
	private String termCode;
	private String termName;
	private Date termBegin;
	private Date termEnd;
	private Integer termIsCurr;
	private Integer termIsShow;
	private Date updateTime;
	private Integer recordStatus;
	private Set subjectiveBasises = new HashSet(0);
	private Set appraiseMarkBasises = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfTerm() {
	}

	/** minimal constructor */
	public BasinfTerm(BasinfYear basinfYear, String termCode,
			Integer termIsCurr, Integer termIsShow, Integer recordStatus) {
		this.basinfYear = basinfYear;
		this.termCode = termCode;
		this.termIsCurr = termIsCurr;
		this.termIsShow = termIsShow;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfTerm(BasinfYear basinfYear, String termCode, String termName,
			Date termBegin, Date termEnd, Integer termIsCurr,
			Integer termIsShow, Date updateTime, Integer recordStatus,
			Set subjectiveBasises, Set appraiseMarkBasises) {
		this.basinfYear = basinfYear;
		this.termCode = termCode;
		this.termName = termName;
		this.termBegin = termBegin;
		this.termEnd = termEnd;
		this.termIsCurr = termIsCurr;
		this.termIsShow = termIsShow;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.subjectiveBasises = subjectiveBasises;
		this.appraiseMarkBasises = appraiseMarkBasises;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BasinfYear getBasinfYear() {
		return this.basinfYear;
	}

	public void setBasinfYear(BasinfYear basinfYear) {
		this.basinfYear = basinfYear;
	}

	public String getTermCode() {
		return this.termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public String getTermName() {
		return this.termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Date getTermBegin() {
		return this.termBegin;
	}

	public void setTermBegin(Date termBegin) {
		this.termBegin = termBegin;
	}

	public Date getTermEnd() {
		return this.termEnd;
	}

	public void setTermEnd(Date termEnd) {
		this.termEnd = termEnd;
	}

	public Integer getTermIsCurr() {
		return this.termIsCurr;
	}

	public void setTermIsCurr(Integer termIsCurr) {
		this.termIsCurr = termIsCurr;
	}

	public Integer getTermIsShow() {
		return this.termIsShow;
	}

	public void setTermIsShow(Integer termIsShow) {
		this.termIsShow = termIsShow;
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

	public Set getSubjectiveBasises() {
		return this.subjectiveBasises;
	}

	public void setSubjectiveBasises(Set subjectiveBasises) {
		this.subjectiveBasises = subjectiveBasises;
	}

	public Set getAppraiseMarkBasises() {
		return this.appraiseMarkBasises;
	}

	public void setAppraiseMarkBasises(Set appraiseMarkBasises) {
		this.appraiseMarkBasises = appraiseMarkBasises;
	}

}