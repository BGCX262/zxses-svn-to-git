package com.agilefly.bean;

/**
 * SysPrivilegeId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysPrivilegeId implements java.io.Serializable {

	// Fields
	/*模块名*/
	private String model;
	/*权限值*/
	private String privilegeValue;

	// Constructors

	/** default constructor */
	public SysPrivilegeId() {
	}

	/** full constructor */
	public SysPrivilegeId(String model, String privilegeValue) {
		this.model = model;
		this.privilegeValue = privilegeValue;
	}

	// Property accessors

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrivilegeValue() {
		return this.privilegeValue;
	}

	public void setPrivilegeValue(String privilegeValue) {
		this.privilegeValue = privilegeValue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysPrivilegeId))
			return false;
		SysPrivilegeId castOther = (SysPrivilegeId) other;

		return ((this.getModel() == castOther.getModel()) || (this.getModel() != null && castOther.getModel() != null && this
				.getModel().equals(castOther.getModel())))
				&& ((this.getPrivilegeValue() == castOther.getPrivilegeValue()) || (this.getPrivilegeValue() != null
						&& castOther.getPrivilegeValue() != null && this.getPrivilegeValue()
						.equals(castOther.getPrivilegeValue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getModel() == null ? 0 : this.getModel().hashCode());
		result = 37 * result + (getPrivilegeValue() == null ? 0 : this.getPrivilegeValue().hashCode());
		return result;
	}

}