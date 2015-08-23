package com.agilefly.commons.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.agilefly.bean.SysPrivilege;
import com.agilefly.bean.SysPrivilegeId;
import com.agilefly.bean.SysRole;
import com.agilefly.bean.SysUser;
import com.agilefly.service.systemuser.ISysUserService;


/**
 * @author boleyn_renlei
 * @date May 21, 2012 2:10:23 AM
 * jstl函数，主要功能是完成权限认证
 */
@Component
public class SecurityFunction {
	private static ISysUserService sysUserService;
	
	public static boolean hasPermission(int sysUserId, String model,
			String privilegeValue){
		SysUser sysUser = sysUserService.find(sysUserId);
		SysPrivilege privilege = new SysPrivilege(new SysPrivilegeId(model, privilegeValue));
		for(SysRole sr : sysUser.getSysRoles()){
			if(sr.getSysPrivileges().contains(privilege)) 
				return true;
		}
		return false;
	}

	//不能为static 否则spring无法注入
	@Resource
	public void setSysUserService(ISysUserService sysUserService) {
		SecurityFunction.sysUserService = sysUserService;
	}
}
