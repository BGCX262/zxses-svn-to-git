package com.zx.core.system.log.dao;   

import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.base.dao.IBaseDAO;
import com.zx.core.model.HrUserLog;
import com.zx.core.model.SysLog;
  
public interface ISysLogDAO extends IBaseDAO<SysLog> {
	/**   
	 * @return   
	 * @Description: 查询日志
	 * @param type日志类型 1登录，2操作，3错误
	 */
    public List<HrUserLog> findSysLogs(Paginate paginate,String type) throws Exception;
    /**   
	 * @return   
	 * @Description: 查询登录日志by name
	 * @param name
	 */
	public List<HrUserLog> findLoginLogsByUser(Paginate paginate,String type,String name) throws Exception;
	/** 
	 * 批量删除日志（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteSysLogs(String ids) throws Exception;
	
	/**   
	 * @return   
	 * @Description: 查询错误日志by date
	 * @param name
	 */
	public List<HrUserLog> findErrorLogsByDate(Paginate paginate,String type,String startDate,String endDate) throws Exception;
	/**   
	 * @return   
	 * @Description: 查询错误日志by id
	 * @param name
	 */
	public HrUserLog findErrorLogsById(String logId)throws Exception;
}
