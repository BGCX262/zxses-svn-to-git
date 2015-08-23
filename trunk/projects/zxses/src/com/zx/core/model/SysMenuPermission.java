package com.zx.core.model;

/**
 * SysMenuPermission entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysMenuPermission implements java.io.Serializable {

	// Fields

	private Long id;
	private SysPermission sysPermission;
	private SysMenu sysMenu;
	private Integer recordStatus;

	// Constructors

	/** default constructor */
	public SysMenuPermission() {
	}

	/** minimal constructor */
	public SysMenuPermission(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SysMenuPermission(SysPermission sysPermission, SysMenu sysMenu,
			Integer recordStatus) {
		this.sysPermission = sysPermission;
		this.sysMenu = sysMenu;
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

	public SysMenu getSysMenu() {
		return this.sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public Integer getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

}