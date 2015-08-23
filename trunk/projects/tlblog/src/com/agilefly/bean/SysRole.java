package com.agilefly.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * SysRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private String roleName;
	private Set<SysUser> sysUsers = new HashSet<SysUser>();
	private Set<SysPrivilege> sysPrivileges = new HashSet<SysPrivilege>();

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	public SysRole(Integer id) {
		this.id = id;
	}
	
	/** full constructor */
	public SysRole(String roleName, Set sysUsers, Set sysPrivileges) {
		this.roleName = roleName;
		this.sysUsers = sysUsers;
		this.sysPrivileges = sysPrivileges;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	
	public Set<SysPrivilege> getSysPrivileges() {
		return sysPrivileges;
	}

	public void setSysPrivileges(Set<SysPrivilege> sysPrivileges) {
		this.sysPrivileges = sysPrivileges;
	}

	/**
	 * 添加权限
	 * @param sysPrivilege
	 */
	public void addSysPrivilege(SysPrivilege sysPrivilege) {
		this.sysPrivileges.add(sysPrivilege);
	}

}