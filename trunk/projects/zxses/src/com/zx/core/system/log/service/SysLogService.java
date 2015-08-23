package com.zx.core.system.log.service;

import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.base.service.BaseService;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUserLog;
import com.zx.core.system.log.dao.ISysLogDAO;
import com.zx.core.util.SysLoggerUtil;

public class SysLogService extends BaseService implements ISysLogService {

	private ISysLogDAO sysLogDAO;

	/**   
	 * @return   
	 * @Description: 查询日志
	 * @param type日志类型 1登录，2操作，3错误
	 */
	public List<HrUserLog> findSysLogs(Paginate paginate,String type) throws ManageException {
		try {
			return this.sysLogDAO.findSysLogs(paginate,type);
		} catch (Exception e) {
			throw new ManageException("查询日志列表出错", e);
		}
	}
	/**   
	 * @return   
	 * @Description: 查询登录日志by name
	 * @param name
	 */
	public List<HrUserLog> findLoginLogsByUser(Paginate paginate,String type,String name) throws ManageException {
		try {
			return this.sysLogDAO.findLoginLogsByUser(paginate,type,name);
		} catch (Exception e) {
			throw new ManageException("查询日志列表出错", e);
		}
	}
	
	/**   
	 * @return   
	 * @Description: 查询错误日志by Date
	 * @param name
	 */
	public List<HrUserLog> findErrorLogsByDate(Paginate paginate,String type,String startDate,String endDate) throws ManageException {
		try {
			return this.sysLogDAO.findErrorLogsByDate(paginate,type,startDate,endDate);
		} catch (Exception e) {
			throw new ManageException("查询日志列表出错", e);
		}
	}
	/**   
	 * @return   
	 * @Description: 查询错误日志by id
	 * @param name
	 */
	public HrUserLog findErrorLogsById(String logId) throws ManageException {
		try {
			return this.sysLogDAO.findErrorLogsById(logId);
		} catch (Exception e) {
			throw new ManageException("查询日志列表出错", e);
		}
	}
	
	/** 
	 * 批量删除日志（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteSysLogs(String ids) throws ManageException{
			try {
				this.sysLogDAO.deleteSysLogs(ids);
			} catch (Exception e) {
				throw new ManageException("删除日志信息出错", e);
			}
	}
	
	/**   
	 * @return the sysLogDAO   
	 */
	public ISysLogDAO getSysLogDAO() {
		return sysLogDAO;
	}

	/**   
	 * @param sysLogDAO the sysLogDAO to set   
	 */
	public void setSysLogDAO(ISysLogDAO sysLogDAO) {
		this.sysLogDAO = sysLogDAO;
	}

}
