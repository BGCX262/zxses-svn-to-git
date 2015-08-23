package com.zx.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Administrator
 *
 */
public class SysMenu implements java.io.Serializable {
	
	private Long id;
	private SysMenu sysMenu;
	private String systemFlag;
	private String code;
	private String name;
	private String image;
	private String url;
	private String level;
	private Integer isEnable;
	private String description;
	private Date createTime;
	private Date updateTime;
	private Integer recordOrder;
	private Integer recordStatus;
	private Set sysMenuPermissions = new HashSet(0);
	private Set sysMenus = new HashSet(0);
	private Set sysRoleMenus = new HashSet(0);
	
	private String recordOrderStr;

	private String navigationBar;
	
	public String getNavigationBar() {
		return navigationBar;
	}

	public void setNavigationBar(String navigationBar) {
		this.navigationBar = navigationBar;
	}

	/** default constructor */
	public SysMenu() {
	}

	/** minimal constructor */
	public SysMenu(String code, String name, String url, String level,
			Integer isEnable, Date createTime, Integer recordStatus) {
		this.code = code;
		this.name = name;
		this.url = url;
		this.level = level;
		this.isEnable = isEnable;
		this.createTime = createTime;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public SysMenu(SysMenu sysMenu, String systemFlag, String code,
			String name, String image, String url, String level,
			Integer isEnable, String description, Date createTime,
			Date updateTime, Integer recordOrder, Integer recordStatus,
			Set sysMenuPermissions, Set sysMenus, Set sysRoleMenus) {
		this.sysMenu = sysMenu;
		this.systemFlag = systemFlag;
		this.code = code;
		this.name = name;
		this.image = image;
		this.url = url;
		this.level = level;
		this.isEnable = isEnable;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.recordOrder = recordOrder;
		this.recordStatus = recordStatus;
		this.sysMenuPermissions = sysMenuPermissions;
		this.sysMenus = sysMenus;
		this.sysRoleMenus = sysRoleMenus;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysMenu getSysMenu() {
		return this.sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public String getSystemFlag() {
		return this.systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getRecordOrder() {
		return recordOrder;
	}

	public void setRecordOrder(Integer recordOrder) {
		this.recordOrder = recordOrder;
	}

	public Integer getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Set getSysMenuPermissions() {
		return this.sysMenuPermissions;
	}

	public void setSysMenuPermissions(Set sysMenuPermissions) {
		this.sysMenuPermissions = sysMenuPermissions;
	}

	public Set getSysMenus() {
		return this.sysMenus;
	}

	public void setSysMenus(Set sysMenus) {
		this.sysMenus = sysMenus;
	}

	public Set getSysRoleMenus() {
		return this.sysRoleMenus;
	}

	public void setSysRoleMenus(Set sysRoleMenus) {
		this.sysRoleMenus = sysRoleMenus;
	}

	public String getRecordOrderStr() {
		return recordOrderStr;
	}

	public void setRecordOrderStr(String recordOrderStr) {
		this.recordOrderStr = recordOrderStr;
	}

}