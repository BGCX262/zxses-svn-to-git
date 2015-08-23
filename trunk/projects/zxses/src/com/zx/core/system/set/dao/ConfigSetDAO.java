package com.zx.core.system.set.dao;

import java.util.List;

import com.zx.core.base.dao.BaseDAO;
import com.zx.core.model.SysLog;
import com.zx.core.model.SysParameter;

@SuppressWarnings("unchecked")
public class ConfigSetDAO extends BaseDAO<SysLog> implements IConfigSetDAO {

	/**
	 * @return
	 * @Description: 查询配置
	 * @param
	 */
	public List<SysParameter> findSysParameters() throws Exception {
		String hql = "from SysParameter as sp where sp.recordStatus = 1 ";
		return this.getSession().createQuery(hql).list();
	}

	/**
	 * @return
	 * @Description: 修改配置
	 * @param
	 */
	public void updateSysParameters(String code, String content) throws Exception {
		String hql = " update SysParameter sp set sp.content = '" + content + "' where sp.code= '" + code
				+ "' and sp.recordStatus = 1 ";
		this.getSession().createQuery(hql).executeUpdate();
	}
}
