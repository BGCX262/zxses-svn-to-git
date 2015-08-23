package com.zx.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SysRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRole implements java.io.Serializable {

	// Fields

	private Long id;
	private String code;
	private String name;
	private String description;
	private Date createTime;
	private Date updateTime;
	private Integer recordStatus;
	private Set sysRolePermissions = new HashSet(0);
	private Set sysRoleMenus = new HashSet(0);
	private Set hrUserRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** minimal constructor */
	public SysRole(String code, String name, Date createTime,
			Integer recordStatus) {
		this.code = code;
		this.name = name;
		this.createTime = createTime;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SysRole(String code, String name, String description,
			Date createTime, Date updateTime, Integer recordStatus,
			Set sysRolePermissions, Set sysRoleMenus, Set hrUserRoles) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.sysRolePermissions = sysRolePermissions;
		this.sysRoleMenus = sysRoleMenus;
		this.hrUserRoles = hrUserRoles;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Set getSysRolePermissions() {
		return this.sysRolePermissions;
	}

	public void setSysRolePermissions(Set sysRolePermissions) {
		this.sysRolePermissions = sysRolePermissions;
	}

	public Set getSysRoleMenus() {
		return this.sysRoleMenus;
	}

	public void setSysRoleMenus(Set sysRoleMenus) {
		this.sysRoleMenus = sysRoleMenus;
	}

	public Set getHrUserRoles() {
		return this.hrUserRoles;
	}

	public void setHrUserRoles(Set hrUserRoles) {
		this.hrUserRoles = hrUserRoles;
	}

}