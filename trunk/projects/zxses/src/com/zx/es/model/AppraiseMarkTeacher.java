package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AppraiseMarkTeacher entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppraiseMarkTeacher implements java.io.Serializable {

	// Fields

	private Integer id;
	private AppraiseMarkBasis appraiseMarkBasis;
	private BasinfTeacher basinfTeacher;
	private Date updateTime;
	private Integer recordStatus;
	private Set appraiseMarkResults = new HashSet(0);

	// Constructors

	/** default constructor */
	public AppraiseMarkTeacher() {
	}

	/** minimal constructor */
	public AppraiseMarkTeacher(AppraiseMarkBasis appraiseMarkBasis,
			BasinfTeacher basinfTeacher, Integer recordStatus) {
		this.appraiseMarkBasis = appraiseMarkBasis;
		this.basinfTeacher = basinfTeacher;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public AppraiseMarkTeacher(AppraiseMarkBasis appraiseMarkBasis,
			BasinfTeacher basinfTeacher, Date updateTime, Integer recordStatus,
			Set appraiseMarkResults) {
		this.appraiseMarkBasis = appraiseMarkBasis;
		this.basinfTeacher = basinfTeacher;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.appraiseMarkResults = appraiseMarkResults;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AppraiseMarkBasis getAppraiseMarkBasis() {
		return this.appraiseMarkBasis;
	}

	public void setAppraiseMarkBasis(AppraiseMarkBasis appraiseMarkBasis) {
		this.appraiseMarkBasis = appraiseMarkBasis;
	}

	public BasinfTeacher getBasinfTeacher() {
		return this.basinfTeacher;
	}

	public void setBasinfTeacher(BasinfTeacher basinfTeacher) {
		this.basinfTeacher = basinfTeacher;
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

	public Set getAppraiseMarkResults() {
		return this.appraiseMarkResults;
	}

	public void setAppraiseMarkResults(Set appraiseMarkResults) {
		this.appraiseMarkResults = appraiseMarkResults;
	}

}