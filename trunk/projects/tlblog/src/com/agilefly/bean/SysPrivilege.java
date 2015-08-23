package com.agilefly.bean;


/**
 * SysPrivilege entity.
 * @author MyEclipse Persistence Tools
 */

public class SysPrivilege implements java.io.Serializable {
	private SysPrivilegeId id;
	/*权限中文名*/
	private String name;
	/*权限显示排序顺序*/
	private Integer orderNo;
	// Constructors

	/** default constructor */
	public SysPrivilege() {
	}

	/** minimal constructor */
	public SysPrivilege(SysPrivilegeId id) {
		this.id = id;
	}
	
	public SysPrivilege(String model, String privilegeValue) {
		this.id = new SysPrivilegeId(model, privilegeValue);
	}
	
	public SysPrivilege(String model, String privilegeValue, String name) {
		this.id = new SysPrivilegeId(model, privilegeValue);
		this.name = name;
	}
	
	public SysPrivilege(String model, String privilegeValue, String name, Integer orderNo) {
		super();
		this.id = new SysPrivilegeId(model,privilegeValue);
		this.name = name;
		this.orderNo = orderNo;
	}
	
	// Property accessors

	public SysPrivilegeId getId() {
		return this.id;
	}

	public void setId(SysPrivilegeId id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SysPrivilege other = (SysPrivilege) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}