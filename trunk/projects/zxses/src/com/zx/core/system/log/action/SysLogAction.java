package com.zx.core.system.log.action;

import java.util.List;

import com.zx.core.base.action.BaseAction;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUserLog;
import com.zx.core.system.log.service.ISysLogService;
import com.zx.core.util.MyDate;

public class SysLogAction extends BaseAction {

	private static final long serialVersionUID = 2802334909175795608L;

	private ISysLogService sysLogService;

	private HrUserLog hrUserLog;

	private List<HrUserLog> hrUserLogs;
	
	private String startDate;
	private String endDate;

	/**   
	 * @return the startDate   
	 */
	public String getStartDate() {
		return startDate;
	}
	/**   
	 * @param startDate the startDate to set   
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**   
	 * @return the endDate   
	 */
	public String getEndDate() {
		return endDate;
	}
	/**   
	 * @param endDate the endDate to set   
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**   
	 * @return   
	 * @Description: 查询登录日志
	 * @param type日志类型 1登录，2操作，3错误
	 */
	public String findLoginLogs() {
		
		try {
			getRequest().setAttribute("selectUrl", "view/sysLog!findLoginLogs.action");
			hrUserLogs = sysLogService.findSysLogs(getPaginate(),"1");
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return "loginlist";
	}
	/**   
	 * @return   
	 * @Description: 查询登录日志by name
	 * @param name
	 */
	public String findLoginLogsByUser() {
		try {
			getRequest().setAttribute("selectUrl", "view/sysLog!findLoginLogsByUser.action");
			hrUserLogs = sysLogService.findLoginLogsByUser(getPaginate(),"1",getParameter("userName"));
			setAttribute("userName", getParameter("userName"));
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return "loginlist";
	}
	/**   
	 * @return   
	 * @Description: 查询操作日志
	 * @param type日志类型 1登录，2操作，3错误
	 */
	public String findOperateLogs() {
		
		try {
			hrUserLogs = sysLogService.findSysLogs(getPaginate(),"2");
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return LIST;
	}
	/**   
	 * @return   
	 * @Description: 查询错误日志
	 * @param type日志类型 1登录，2操作，3错误
	 */
	public String findErrorLogs() {
		
		try {
			startDate = MyDate.getNowTime();
			 endDate = MyDate.getNowTime();
			getRequest().setAttribute("errorLogUrl", "view/sysLog!findErrorLogs.action"); 
			hrUserLogs = sysLogService.findErrorLogsByDate(getPaginate(),"3",startDate,endDate);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return "errorlist";
	}
	
	/**   
	 * @return   
	 * @Description: 查询错误日志by Date
	 * @param type日志类型 1登录，2操作，3错误
	 */
	public String findErrorLogsByDate() {
		try {
			getRequest().setAttribute("errorLogUrl", "view/sysLog!findErrorLogsByDate.action"); 
			hrUserLogs = sysLogService.findErrorLogsByDate(getPaginate(),"3",startDate,endDate);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return "errorlist";
	}
	
	/**   
	 * @return   
	 * @Description: 查询错误日志by id
	 * @param  
	 */
	public String viewErrorLogs() throws ManageException {
		HrUserLog hul = sysLogService.findErrorLogsById(getParameter("logId"));
		setAttribute("hrUserLog", hul);
		return "errorview";
	}
	
	/** 
	 * 删除日志（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public String deleteSysLogs() {
		try {
			this.sysLogService.deleteSysLogs(checkIds);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			addMessageError("delete.failure");
			return findErrorLogs();
		}
		addMessageInfo("delete.success");
		return findErrorLogsByDate();
	}
	
	
	
	
	/**   
	 * @return the sysLogService   
	 */
	public ISysLogService getSysLogService() {
		return sysLogService;
	}

	/**   
	 * @param sysLogService the sysLogService to set   
	 */
	public void setSysLogService(ISysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}
	/**   
	 * @return the hrUserLog   
	 */
	public HrUserLog getHrUserLog() {
		return hrUserLog;
	}
	/**   
	 * @param hrUserLog the hrUserLog to set   
	 */
	public void setHrUserLog(HrUserLog hrUserLog) {
		this.hrUserLog = hrUserLog;
	}
	/**   
	 * @return the hrUserLogs   
	 */
	public List<HrUserLog> getHrUserLogs() {
		return hrUserLogs;
	}
	/**   
	 * @param hrUserLogs the hrUserLogs to set   
	 */
	public void setHrUserLogs(List<HrUserLog> hrUserLogs) {
		this.hrUserLogs = hrUserLogs;
	}

}
