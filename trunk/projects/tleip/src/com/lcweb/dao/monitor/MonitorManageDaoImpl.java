package com.lcweb.dao.monitor;

import com.lcweb.dao.base.BaseDaoImpl;

/**
 * 
 * @Title: MonitorManageDaoImpl.java
 * @Description: 
 * @Author: feng
 * @Time: Jul 19, 2011
 */
public class MonitorManageDaoImpl extends BaseDaoImpl implements MonitorManageDao {
	private MonitorManageDao monitorManageDao;

	/**   
	 * @return the monitorManageDao   
	 */
	public MonitorManageDao getMonitorManageDao() {
		return monitorManageDao;
	}

	/**   
	 * @param monitorManageDao the monitorManageDao to set   
	 */
	public void setMonitorManageDao(MonitorManageDao monitorManageDao) {
		this.monitorManageDao = monitorManageDao;
	}
}
