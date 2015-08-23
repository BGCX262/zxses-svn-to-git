package com.zx.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * HrUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HrUser implements java.io.Serializable {

	// Fields

	private Long id;
	private HrUserType hrUserType;
	private Long originalId;
	private String account;
	private String name;
	private String password;
	private String originalPassword;
	private String email;
	private Integer isEnable;
	private Integer isOnline;
	private Date createTime;
	private Date updateTime;
	private Integer recordStatus;
	private Set hrUserLogs = new HashSet(0);
	private Set hrUserRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public HrUser() {
	}

	/** minimal constructor */
	public HrUser(String account, String name, String password,
			String originalPassword, Integer isEnable, Integer isOnline,
			Date createTime, Integer recordStatus) {
		this.account = account;
		this.name = name;
		this.password = password;
		this.originalPassword = originalPassword;
		this.isEnable = isEnable;
		this.isOnline = isOnline;
		this.createTime = createTime;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public HrUser(HrUserType hrUserType, Long originalId, String account,
			String name, String password, String originalPassword,
			String email, Integer isEnable, Integer isOnline, Date createTime,
			Date updateTime, Integer recordStatus, Set hrUserLogs,
			Set hrUserRoles) {
		this.hrUserType = hrUserType;
		this.originalId = originalId;
		this.account = account;
		this.name = name;
		this.password = password;
		this.originalPassword = originalPassword;
		this.email = email;
		this.isEnable = isEnable;
		this.isOnline = isOnline;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.hrUserLogs = hrUserLogs;
		this.hrUserRoles = hrUserRoles;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HrUserType getHrUserType() {
		return this.hrUserType;
	}

	public void setHrUserType(HrUserType hrUserType) {
		this.hrUserType = hrUserType;
	}

	public Long getOriginalId() {
		return this.originalId;
	}

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOriginalPassword() {
		return this.originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
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

	public Set getHrUserLogs() {
		return this.hrUserLogs;
	}

	public void setHrUserLogs(Set hrUserLogs) {
		this.hrUserLogs = hrUserLogs;
	}

	public Set getHrUserRoles() {
		return this.hrUserRoles;
	}

	public void setHrUserRoles(Set hrUserRoles) {
		this.hrUserRoles = hrUserRoles;
	}

}