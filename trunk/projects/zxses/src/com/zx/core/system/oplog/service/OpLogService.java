package com.zx.core.system.oplog.service;

import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserLog;
import com.zx.core.system.oplog.dao.IOpLogDAO;

public class OpLogService implements IOpLogService {

	private IOpLogDAO opLogDAO;

	public IOpLogDAO getOpLogDAO() {
		return opLogDAO;
	}

	public void setOpLogDAO(IOpLogDAO opLogDAO) {
		this.opLogDAO = opLogDAO;
	}

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
			String endDate, Paginate paginate) throws ManageException {
		try {
			return this.opLogDAO.findHrUserLogs(hrUserOp, staDate, endDate,
					paginate);
		} catch (Exception e) {
			throw new ManageException("查询用户操作日志信息出错", e);
		}
	}
}
