package com.lcweb.service.monitor;

import java.util.Map;

import com.lcweb.base.util.PageList;
import com.lcweb.service.base.BaseService;

/**
 * 
 * @Title: MonitorManageService.java
 * @Description:
 * @Author: feng
 * @Time: Jul 19, 2011
 */
public interface MonitorManageService extends BaseService {
	@SuppressWarnings("unchecked")
	public PageList find(Map<String, Object> conditionMap) throws Exception;
	public PageList findByDate(Map<String, Object> conditionMap,String startDate,String endDate,String flag) throws Exception;
}
