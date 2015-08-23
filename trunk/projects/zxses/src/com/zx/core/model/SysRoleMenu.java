package com.zx.core.model;

/**
 * SysRoleMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRoleMenu implements java.io.Serializable {

	// Fields

	private Long id;
	private SysMenu sysMenu;
	private SysRole sysRole;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public SysRoleMenu() {
	}

	/** minimal constructor */
	public SysRoleMenu(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SysRoleMenu(SysMenu sysMenu, SysRole sysRole, Integer recordStatus) {
		this.sysMenu = sysMenu;
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

	public SysMenu getSysMenu() {
		return this.sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
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