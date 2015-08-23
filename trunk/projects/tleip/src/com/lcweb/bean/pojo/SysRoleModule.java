package com.lcweb.bean.pojo;

 
public class SysRoleModule implements java.io.Serializable {
 
	private static final long serialVersionUID = 1L;
	private Long id;
	private SysRole sysRole;
	private SysModule sysModule;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SysRole getSysRole() {
		return sysRole;
	}
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
	public SysModule getSysModule() {
		return sysModule;
	}
	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}
	
 
}