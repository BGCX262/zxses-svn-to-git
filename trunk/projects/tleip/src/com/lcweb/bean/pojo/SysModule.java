package com.lcweb.bean.pojo;

import java.util.HashSet;
import java.util.Set;

public class SysModule implements java.io.Serializable, Comparable {

	private static final long serialVersionUID = 1L;
	private String moduleId;
	private String moduleName;
	private String url;
	private String upModule;
	private String moduleFlag;
	private String modulePicture;
	private String ifOpen;

	private Set sysRoleModules = new HashSet(0);
	private Set childModules;
	private SysModule parent;
	private String isCheck;
	private String yxdm;

	public Set getChildModules() {
		return childModules;
	}

	public void setChildModules(Set childModules) {
		this.childModules = childModules;
	}

	public SysModule getParent() {
		return parent;
	}

	public void setParent(SysModule parent) {
		this.parent = parent;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUpModule() {
		return upModule;
	}

	public void setUpModule(String upModule) {
		this.upModule = upModule;
	}

	public String getModuleFlag() {
		return moduleFlag;
	}

	public void setModuleFlag(String moduleFlag) {
		this.moduleFlag = moduleFlag;
	}

	public String getModulePicture() {
		return modulePicture;
	}

	public void setModulePicture(String modulePicture) {
		this.modulePicture = modulePicture;
	}

	public String getIfOpen() {
		return ifOpen;
	}

	public void setIfOpen(String ifOpen) {
		this.ifOpen = ifOpen;
	}

	public Set getSysRoleModules() {
		return sysRoleModules;
	}

	public void setSysRoleModules(Set sysRoleModules) {
		this.sysRoleModules = sysRoleModules;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public int compareTo(Object sysModule) {
		int compare = 0;
		if (sysModule instanceof SysModule) {
			SysModule module = (SysModule) sysModule;
			if (Long.valueOf(this.moduleId) > Long.valueOf(module.getModuleId())) {
				compare = 1;
			} else if (Long.valueOf(this.moduleId) < Long.valueOf(module.getModuleId())) {
				compare = -1;
			}
		}
		return compare;
	}

	public String getYxdm() {
		return yxdm;
	}

	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}

}