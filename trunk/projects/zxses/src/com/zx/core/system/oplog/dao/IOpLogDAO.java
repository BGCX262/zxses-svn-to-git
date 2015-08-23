package com.zx.core.system.oplog.dao;

import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.base.dao.IBaseDAO;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserLog;

public interface IOpLogDAO extends IBaseDAO<HrUser> {

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
			String endDate, Paginate paginate) throws Exception;

}
