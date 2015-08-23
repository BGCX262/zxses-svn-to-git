package com.zx.core.model;

/**
 * SysRolePermission entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRolePermission implements java.io.Serializable {

	// Fields

	private Long id;
	private SysPermission sysPermission;
	private SysRole sysRole;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public SysRolePermission() {
	}

	/** minimal constructor */
	public SysRolePermission(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SysRolePermission(SysPermission sysPermission, SysRole sysRole,
			Integer recordStatus) {
		this.sysPermission = sysPermission;
		this.sysRole = sysRole;
		this.recordStatus = recordStatus;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysPermission getSysPermission() {
		return this.sysPermission;
	}

	public void setSysPermission(SysPermission sysPermission) {
		this.sysPermission = sysPermission;
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public Integer getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

}