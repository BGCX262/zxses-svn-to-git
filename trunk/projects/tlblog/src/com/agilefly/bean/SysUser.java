package com.agilefly.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author boleyn_renlei
 * @date May 15, 2012 4:27:05 PM
 */
public class SysUser implements java.io.Serializable {
	/* 用户id*/
	private Integer id;
	//用户登录名
	private String username;
	//用户登录密码
	private String password;
	//用户类型
	//private SysType sysType;
	private String userType;
	//用户真实名称
	private String realname;
	//用户性别
	private String gender;
	//用户笔名
	private String userPenname;
	//用户生日
	private String userBirthday;
	//用户qq
	private String userQq;
	//用户博客名称
	private String userBlogname;
	//博客描述
	private String userBlogdes;
	//用户登录ip
	private String userLoginip;
	//用户注册时间
	private Date userRegtime;
	//用户登录时间
	private Date userLogintime;
	//用户头像
	private String userHeadpic;
	//博客点击量
	private Integer userClick;
	//用户邮箱
	private String userEmail;
	//用户新浪微博
	private String sinaWeibo;
	//用户腾讯微博
	private String tenWeibo;
	//用户电话
	private String phone;
	//来自
	private String userFrom;
	//用户是否审批
	private Byte approveStatus;
	//用户是否可用
	private Byte available;
	//记录是否逻辑删除
	private Byte recordStatus;
	//权限
	private Set<SysRole> sysRoles = new HashSet<SysRole>();
	
	//是否审核
	private String approveStatusStr;
	//用户类型
	private String userTypeStr;
	
	public String getApproveStatusStr() {
		if(approveStatus == 1){
			return "<font color='green'>已审批</font>";
		}else{
			return "<font color='red'>未审批</font>";
		}
	}
	
	public String getUserTypeStr() {
		if("teacher".equals(userType)){
			return "教师";
		}else{
			return "学生";
		}
	}
	
	// Constructors
	/** default constructor */
	public SysUser() {
	}

	/** minimal constructor */
	public SysUser(String username, String password, String userBlogname) {
		this.username = username;
		this.password = password;
		this.userBlogname = userBlogname;
	}

	/** full constructor */
	public SysUser(String username, String password, String realname, String gender, String userPenname, String userBirthday,
			String userQq, String userBlogname, String userBlogdes, String userLoginip, Date userRegtime, Date userLogintime,
			String userHeadpic, Integer userClick, String userEmail, String sinaWeibo, String tenWeibo, String phone,
			Byte available, Byte recordStatus, Set<SysRole> sysRoles) {
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.gender = gender;
		this.userPenname = userPenname;
		this.userBirthday = userBirthday;
		this.userQq = userQq;
		this.userBlogname = userBlogname;
		this.userBlogdes = userBlogdes;
		this.userLoginip = userLoginip;
		this.userRegtime = userRegtime;
		this.userLogintime = userLogintime;
		this.userHeadpic = userHeadpic;
		this.userClick = userClick;
		this.userEmail = userEmail;
		this.sinaWeibo = sinaWeibo;
		this.tenWeibo = tenWeibo;
		this.phone = phone;
		this.available = available;
		this.recordStatus = recordStatus;
		this.sysRoles = sysRoles;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserPenname() {
		return this.userPenname;
	}

	public void setUserPenname(String userPenname) {
		this.userPenname = userPenname;
	}

	public String getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserQq() {
		return this.userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

	public String getUserBlogname() {
		return this.userBlogname;
	}

	public void setUserBlogname(String userBlogname) {
		this.userBlogname = userBlogname;
	}

	public String getUserBlogdes() {
		return this.userBlogdes;
	}

	public void setUserBlogdes(String userBlogdes) {
		this.userBlogdes = userBlogdes;
	}

	public String getUserLoginip() {
		return this.userLoginip;
	}

	public void setUserLoginip(String userLoginip) {
		this.userLoginip = userLoginip;
	}

	public Date getUserRegtime() {
		return this.userRegtime;
	}

	public void setUserRegtime(Date userRegtime) {
		this.userRegtime = userRegtime;
	}

	public Date getUserLogintime() {
		return this.userLogintime;
	}

	public void setUserLogintime(Date userLogintime) {
		this.userLogintime = userLogintime;
	}

	public String getUserHeadpic() {
		return this.userHeadpic;
	}

	public void setUserHeadpic(String userHeadpic) {
		this.userHeadpic = userHeadpic;
	}

	public Integer getUserClick() {
		return this.userClick;
	}

	public void setUserClick(Integer userClick) {
		this.userClick = userClick;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSinaWeibo() {
		return this.sinaWeibo;
	}

	public void setSinaWeibo(String sinaWeibo) {
		this.sinaWeibo = sinaWeibo;
	}

	public String getTenWeibo() {
		return this.tenWeibo;
	}

	public void setTenWeibo(String tenWeibo) {
		this.tenWeibo = tenWeibo;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Byte getAvailable() {
		return this.available;
	}

	public void setAvailable(Byte available) {
		this.available = available;
	}

	public Byte getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(Byte recordStatus) {
		this.recordStatus = recordStatus;
	}
	
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}
	
	public String getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}

	/**
	 * 添加角色
	 * @param group
	 */
	public void addSysRole(SysRole role){
		this.sysRoles.add(role);
	}

	public Byte getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Byte approveStatus) {
		this.approveStatus = approveStatus;
	}

	/*public SysType getSysType() {
		return sysType;
	}

	public void setSysType(SysType sysType) {
		this.sysType = sysType;
	}*/

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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
		final SysUser other = (SysUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}