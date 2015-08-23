package com.lcweb.bean.pojo;

public class SysRolePerson implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private SysRole sysRole;
	private BasicPerson basicPerson;

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

	public BasicPerson getBasicPerson() {
		return this.basicPerson;
	}

	public void setBasicPerson(BasicPerson basicPerson) {
		this.basicPerson = basicPerson;
	}

}