package com.lcweb.bean.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * FrontUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FrontUser implements java.io.Serializable {

	// Fields

	private Long userId;
	private String userAccount;
	private String userName;
	private String password;
	private String sex;
	private String userType;
	private String birthday;
	private String address;
	private String phone;
	private String email;
	private String createTime;
	private String updateTime;
	private Short approveStatus;
	private Short available;
	private Short recordStatus;
	private Set frontMessages = new HashSet(0);
	
	private String approveStatusStr;
	private String availableStr;

	// Constructors

	public String getApproveStatusStr() {
		if(approveStatus == 1){
			return "<font color='green'>已审批</font>";
		}else{
			return "<font color='red'>未审批</font>";
		}
	}

	public String getAvailableStr() {
		if(available == 1){
			return "<font color='green'>启用</font>";
		}else{
			return "<font color='red'>禁用</font>";
		}
	}

	/** default constructor */
	public FrontUser() {
	}

	/** minimal constructor */
	public FrontUser(String userAccount, String userName, String password, String createTime, String updateTime,
			Short approveStatus, Short available, Short recordStatus) {
		this.userAccount = userAccount;
		this.userName = userName;
		this.password = password;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.approveStatus = approveStatus;
		this.available = available;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public FrontUser(String userAccount, String userName, String password, String sex, String userType, String birthday,
			String address, String phone, String email, String createTime, String updateTime, Short approveStatus,
			Short available, Short recordStatus, Set frontMessages) {
		this.userAccount = userAccount;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.userType = userType;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.approveStatus = approveStatus;
		this.available = available;
		this.recordStatus = recordStatus;
		this.frontMessages = frontMessages;
	}

	// Property accessors

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Short getApproveStatus() {
		return this.approveStatus;
	}

	public void setApproveStatus(Short approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Short getAvailable() {
		return this.available;
	}

	public void setAvailable(Short available) {
		this.available = available;
	}

	public Short getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(Short recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Set getFrontMessages() {
		return this.frontMessages;
	}

	public void setFrontMessages(Set frontMessages) {
		this.frontMessages = frontMessages;
	}

}