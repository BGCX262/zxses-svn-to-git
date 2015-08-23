package com.zx.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SysPermission entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysPermission implements java.io.Serializable {

	// Fields

	private Long id;
	private String code;
	private String name;
	private String image;
	private Integer isEnable;
	private String description;
	private Date createTime;
	private Date updateTime;
	private Integer recordOrder;
	private Integer recordStatus;
	private Set sysRolePermissions = new HashSet(0);
	private Set sysMenuPermissions = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysPermission() {
	}

	/** minimal constructor */
	public SysPermission(String code, String name, Integer isEnable,
			Date createTime, Integer recordOrder, Integer recordStatus) {
		this.code = code;
		this.name = name;
		this.isEnable = isEnable;
		this.createTime = createTime;
		this.recordOrder = recordOrder;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SysPermission(String code, String name, String image,
			Integer isEnable, String description, Date createTime,
			Date updateTime, Integer recordOrder, Integer recordStatus,
			Set sysRolePermissions, Set sysMenuPermissions) {
		this.code = code;
		this.name = name;
		this.image = image;
		this.isEnable = isEnable;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.recordOrder = recordOrder;
		this.recordStatus = recordStatus;
		this.sysRolePermissions = sysRolePermissions;
		this.sysMenuPermissions = sysMenuPermissions;
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

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
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

	public Set getSysRolePermissions() {
		return this.sysRolePermissions;
	}

	public void setSysRolePermissions(Set sysRolePermissions) {
		this.sysRolePermissions = sysRolePermissions;
	}

	public Set getSysMenuPermissions() {
		return this.sysMenuPermissions;
	}

	public void setSysMenuPermissions(Set sysMenuPermissions) {
		this.sysMenuPermissions = sysMenuPermissions;
	}

}