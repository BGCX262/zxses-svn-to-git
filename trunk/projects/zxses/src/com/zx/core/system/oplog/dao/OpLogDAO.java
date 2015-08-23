package com.zx.core.system.oplog.dao;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.lcsoft.control.tag.page.util.PaginationUtils;
import com.zx.core.base.dao.BaseDAO;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserLog;

public class OpLogDAO extends BaseDAO<HrUser> implements IOpLogDAO {

	/***************************************************************************
	 * 根据用户名、开始日期、结束日期查询用户的操作信息
	 * 
	 * @param hrUserOp
	 * @param staDate
	 * @param endDate
	 * @param paginate
	 * @return
	 * @throws Exception
	 */
	public List<HrUserLog> findHrUserLogs(HrUser hrUserOp, String staDate,
			String endDate, Paginate paginate) throws Exception {
		String hql = "from HrUserLog where recordStatus=1 and hrUserLogType.code='operate_log'";
		if (hrUserOp != null && StringUtils.isNotEmpty(hrUserOp.getName())) {
			hql += " and hrUser.name like '%" + hrUserOp.getName() + "%'";
		}
		if (StringUtils.isNotEmpty(staDate)) {
			hql += " and createTime>='" + staDate + " 00:00:00'";
		}
		if (StringUtils.isNotEmpty(endDate)) {
			hql += " and createTime<='" + endDate + " 23:59:59'";
		}

		hql += " order by createTime desc";
		PaginationUtils.getPaginationUtils().searchPaginate(hql, paginate);
		return paginate.getDatas();
	}
}
