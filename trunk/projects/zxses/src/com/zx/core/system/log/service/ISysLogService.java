package com.zx.core.system.log.service;

import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.base.service.IBaseService;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUserLog;

public interface ISysLogService extends IBaseService {
	/**   
	 * @return   
	 * @Description: 查询日志
	 * @param type日志类型 1登录，2操作，3错误
	 */
	public List<HrUserLog> findSysLogs(Paginate paginate, String type) throws ManageException;

	/**   
	 * @return   
	 * @Description: 查询登录日志by name
	 * @param name
	 */
	public List<HrUserLog> findLoginLogsByUser(Paginate paginate, String type, String name) throws ManageException;

	/** 
	 * 批量删除日志（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteSysLogs(String ids) throws ManageException;
	/**   
	 * @return   
	 * @Description: 查询错误日志by Date
	 * @param name
	 */
	public List<HrUserLog> findErrorLogsByDate(Paginate paginate,String type,String startDate,String endDate) throws ManageException;
	/**   
	 * @return   
	 * @Description: 查询错误日志by id
	 * @param name
	 */
	public HrUserLog findErrorLogsById(String logId) throws ManageException;
}
