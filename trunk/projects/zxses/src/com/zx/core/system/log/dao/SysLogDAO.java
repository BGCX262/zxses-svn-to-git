package com.zx.core.system.log.dao;

import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.lcsoft.control.tag.page.util.PaginationUtils;
import com.zx.core.base.dao.BaseDAO;
import com.zx.core.model.HrUserLog;
import com.zx.core.model.SysLog;

@SuppressWarnings("unchecked")
public class SysLogDAO extends BaseDAO<SysLog> implements ISysLogDAO {
	/**   
	 * @return   
	 * @Description: 查询日志
	 * @param type日志类型 1登录，2操作，3错误
	 */
	public List<HrUserLog> findSysLogs(Paginate paginate,String type) throws Exception {
		String hql = "from HrUserLog as hul where hul.hrUserLogType = "+type+" and hul.recordStatus = 1 order by hul.updateTime desc ";
		PaginationUtils.getPaginationUtils().searchPaginate(hql, paginate);
		return paginate.getDatas();
	}
	/**   
	 * @return   
	 * @Description: 查询登录日志by name
	 * @param name
	 */
	public List<HrUserLog> findLoginLogsByUser(Paginate paginate,String type,String name) throws Exception {
		String hql = "from HrUserLog as hul where hul.hrUser.name like '%"+name+"%' and hul.hrUserLogType = "+type+" and hul.recordStatus = 1 order by hul.updateTime desc ";
		PaginationUtils.getPaginationUtils().searchPaginate(hql, paginate);
		return paginate.getDatas();
	}
	/** 
	 * 批量删除日志（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteSysLogs(String ids) throws Exception {
		String hql = "update HrUserLog set recordStatus=0 where id in (" + ids
				+ ")";
		this.getSession().createQuery(hql).executeUpdate();
	}
	
	/**   
	 * @return   
	 * @Description: 查询错误日志by date
	 * @param name
	 */
	public List<HrUserLog> findErrorLogsByDate(Paginate paginate,String type,String startDate,String endDate) throws Exception {
		startDate = startDate + " 00:00:00 ";
		endDate = endDate + " 23:59:59 ";
		String hql = "from HrUserLog as hul where hul.updateTime between '"+ startDate + "' and '" + endDate +"' and hul.hrUserLogType = "+type+ " and hul.recordStatus = 1 order by hul.updateTime desc ";
		PaginationUtils.getPaginationUtils().searchPaginate(hql, paginate);
		return paginate.getDatas();
	}
	/**   
	 * @return   
	 * @Description: 查询错误日志by id
	 * @param name
	 */
	public HrUserLog findErrorLogsById(String logId)throws Exception {
		 
		String hql = "from HrUserLog as hul where hul.id = "+logId+" and hul.recordStatus = 1 ";
		return (HrUserLog) this.getSession().createQuery(hql).uniqueResult();
		
	}
}
