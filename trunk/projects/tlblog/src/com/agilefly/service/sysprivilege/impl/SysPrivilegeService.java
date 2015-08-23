package com.agilefly.service.sysprivilege.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agilefly.bean.SysPrivilege;
import com.agilefly.service.base.BaseDaoImpl;
import com.agilefly.service.sysprivilege.ISysPrivilegeService;

/**
 * @author boleyn_renlei
 * @date May 18, 2012 10:38:56 PM
 */
@Service
@Transactional
public class SysPrivilegeService extends BaseDaoImpl<SysPrivilege> implements ISysPrivilegeService {
	@Override
	public void batchSave(List<SysPrivilege> privileges) {
		for (SysPrivilege sysPrivilege : privileges) {
			save(sysPrivilege);
		}
	}
}
