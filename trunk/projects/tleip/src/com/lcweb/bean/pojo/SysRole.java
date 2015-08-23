package com.lcweb.bean.pojo;

import java.util.HashSet;
import java.util.Set;
 
public class SysRole implements java.io.Serializable {

 
	private static final long serialVersionUID = 1L;
	//角色代码��ɫ����
	private Long roleId;
	//名称���
	private String roleName;
	//是否选中��
	private String isChecked;
	private Set sysRoleModules = new HashSet(0);
	private Set sysRolePersons = new HashSet(0);
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set getSysRoleModules() {
		return sysRoleModules;
	}
	public void setSysRoleModules(Set sysRoleModules) {
		this.sysRoleModules = sysRoleModules;
	}
	public Set getSysRolePersons() {
		return sysRolePersons;
	}
	public void setSysRolePersons(Set sysRolePersons) {
		this.sysRolePersons = sysRolePersons;
	}
	 
	public String getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	 
}