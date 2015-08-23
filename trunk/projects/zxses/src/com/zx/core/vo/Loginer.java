package com.zx.core.vo;

/**
 * 登录对象:该对象用来存储登录人的相关信息
 * 
 * @author maolj
 * @date Jul 6, 2010
 * @Version 1.0
 */
public class Loginer {
	
	private Long id;//当前用户的ID

	private String userAccount; // 登陆名称

	private String userName;// 用户名

	private String lastLoginTime; // 最后登陆的时间

	private String lastLoginIp; // 最后登录IP

	private String currentLoginIp; // 最后登录IP

	private String userType;// 用户类型

	private String navigationBar; // 导航条 （当前位置）

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getCurrentLoginIp() {
		return currentLoginIp;
	}

	public void setCurrentLoginIp(String currentLoginIp) {
		this.currentLoginIp = currentLoginIp;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getNavigationBar() {
		return navigationBar;
	}

	public void setNavigationBar(String navigationBar) {
		this.navigationBar = navigationBar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
