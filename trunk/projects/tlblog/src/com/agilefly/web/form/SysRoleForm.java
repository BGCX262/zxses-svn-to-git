package com.agilefly.web.form;

import org.apache.struts.action.ActionForm;
import com.agilefly.bean.SysPrivilegeId;

/**
 * @author boleyn_renlei
 * @date May 18, 2012 10:40:12 PM
 */
public class SysRoleForm extends ActionForm {
	private Integer id;
	private String roleName;
	private SysPrivilegeId[] sysPrivileges;
	
	//private Set<SysUser> sysUsers = new HashSet<SysUser>();
	//private Set<SysPrivilege> sysPrivileges = new HashSet<SysPrivilege>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public SysPrivilegeId[] getSysPrivileges() {
		return sysPrivileges;
	}
	public void setSysPrivileges(SysPrivilegeId[] sysPrivileges) {
		this.sysPrivileges = sysPrivileges;
	}
}
