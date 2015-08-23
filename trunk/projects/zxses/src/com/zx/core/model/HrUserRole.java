package com.zx.core.model;

/**
 * HrUserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HrUserRole implements java.io.Serializable {

	// Fields

	private Long id;
	private SysRole sysRole;
	private HrUser hrUser;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public HrUserRole() {
	}

	/** minimal constructor */
	public HrUserRole(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public HrUserRole(SysRole sysRole, HrUser hrUser, Integer recordStatus) {
		this.sysRole = sysRole;
		this.hrUser = hrUser;
		this.recordStatus = recordStatus;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public HrUser getHrUser() {
		return this.hrUser;
	}

	public void setHrUser(HrUser hrUser) {
		this.hrUser = hrUser;
	}

	public Integer getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

}