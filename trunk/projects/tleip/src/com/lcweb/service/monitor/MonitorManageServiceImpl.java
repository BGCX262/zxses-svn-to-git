package com.lcweb.service.monitor;

import java.util.Map;

import com.lcweb.base.util.PageList;
import com.lcweb.dao.monitor.MonitorManageDao;
import com.lcweb.service.base.BaseServiceImpl;

/**
 * 
 * @Title: MonitorManageServiceImpl.java
 * @Description:
 * @Author: feng
 * @Time: Jul 19, 2011
 */
public class MonitorManageServiceImpl extends BaseServiceImpl implements MonitorManageService {
	private MonitorManageDao monitorManageDao;

	public PageList find(Map<String, Object> conditionMap) throws Exception {
		String currentPage = null;
		String path = null;
		String pageSize = "10";
		StringBuffer sql = new StringBuffer();
		StringBuffer count = new StringBuffer();
		sql.append("from Monitor as d ");
		count.append("select count(id) " + sql);
		if (conditionMap.get("currentPage") != null) {
			currentPage = conditionMap.get("currentPage").toString();
		}
		if (conditionMap.get("path") != null) {
			path = conditionMap.get("path").toString();
		}
		if (conditionMap.get("pagesize") != null) {
			pageSize = conditionMap.get("pagesize").toString();
		}
		sql.append(" order by d.operateTime desc");
		String form = conditionMap.get("form") + "";
		return PageList.page(count.toString(), sql.toString(), currentPage, pageSize, path, this, form);
	}

	public PageList findByDate(Map<String, Object> conditionMap, String startDate, String endDate, String flag)
			throws Exception {
		String currentPage = null;
		String path = null;
		String pageSize = "10";
		StringBuffer sql = new StringBuffer();
		StringBuffer count = new StringBuffer();

		if ("1".equals(flag)) {
			startDate = startDate + " 00:00:00 ";
			endDate = endDate + " 23:59:59 ";
			sql.append("from Monitor as d ");
			sql.append(" where d.operateTime between '" + startDate + "' and '" + endDate + "'");
		} else {
			sql.append("from Monitor as d ");
		}

		count.append(" select count(id) " + sql);
		if (conditionMap.get("currentPage") != null) {
			currentPage = conditionMap.get("currentPage").toString();
		}
		if (conditionMap.get("path") != null) {
			path = conditionMap.get("path").toString();
		}
		if (conditionMap.get("pagesize") != null) {
			pageSize = conditionMap.get("pagesize").toString();
		}
		sql.append(" order by d.operateTime desc");
		String form = conditionMap.get("form") + "";
		return PageList.page(count.toString(), sql.toString(), currentPage, pageSize, path, this, form);
	}

	/**
	 * @return the monitorManageDao
	 */
	public MonitorManageDao getMonitorManageDao() {
		return monitorManageDao;
	}

	/**
	 * @param monitorManageDao
	 *            the monitorManageDao to set
	 */
	public void setMonitorManageDao(MonitorManageDao monitorManageDao) {
		this.monitorManageDao = monitorManageDao;
	}

}
