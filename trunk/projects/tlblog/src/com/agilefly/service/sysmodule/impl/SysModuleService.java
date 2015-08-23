package com.agilefly.service.sysmodule.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agilefly.bean.SysModule;
import com.agilefly.commons.QueryResult;
import com.agilefly.service.base.BaseDaoImpl;
import com.agilefly.service.sysmodule.ISysModuleService;

/**
 * @author boleyn_renlei
 * @date May 18, 2012 10:38:43 PM
 */
@Service
@Transactional
public class SysModuleService extends BaseDaoImpl<SysModule> implements ISysModuleService {
	public void addSysModule(SysModule module, int parentId) {
		if (parentId != 0) {
			module.setParent(find(parentId));
		}
		save(module);
	}

	public void delSysModule(int moduleId) {
		SysModule module = (SysModule) find(moduleId);

		if (module.getChildren().size() > 0) {
			throw new RuntimeException("存在子模块，不允许删除!");
		}
		getHibernateTemplate().delete(module);
	}

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<SysModule> searchSysModules(int parentId) {
		return getScrollData(-1, -1, parentId == 0 ? "o.parent is null" : "o.parent.id = " + parentId, null);
	}

	public void updateSysModule(SysModule module, int parentId) {
		if (parentId != 0) {
			module.setParent(find(parentId));
		}
		update(module);
	}
}
