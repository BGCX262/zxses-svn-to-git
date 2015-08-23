package com.zx.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * HrDept entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HrDept implements java.io.Serializable {

	// Fields

	private Long id;
	private HrDept hrDept;
	private String code;
	private String name;
	private String level;
	private String comment;
	private Date createTime; 
	private Date updateTime;
	private Integer recordOrder;
	private Integer recordStatus;
	private Set hrDepts = new HashSet(0);

	// Constructors

	/** default constructor */
	public HrDept() {
	}

	/** minimal constructor */
	public HrDept(String name, String level, Date createTime,
			Integer recordOrder, Integer recordStatus) {
		this.name = name;
		this.level = level;
		this.createTime = createTime;
		this.recordOrder = recordOrder;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public HrDept(HrDept hrDept, String name, String level, String comment,
			Date createTime, Date updateTime, Integer recordOrder,
			Integer recordStatus, Set hrDepts) {
		this.hrDept = hrDept;
		this.name = name;
		this.level = level;
		this.comment = comment;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.recordOrder = recordOrder;
		this.recordStatus = recordStatus;
		this.hrDepts = hrDepts;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HrDept getHrDept() {
		return this.hrDept;
	}

	public void setHrDept(HrDept hrDept) {
		this.hrDept = hrDept;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRecordOrder() {
		return this.recordOrder;
	}

	public void setRecordOrder(Integer recordOrder) {
		this.recordOrder = recordOrder;
	}

	public Integer getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Set getHrDepts() {
		return this.hrDepts;
	}

	public void setHrDepts(Set hrDepts) {
		this.hrDepts = hrDepts;
	}
}