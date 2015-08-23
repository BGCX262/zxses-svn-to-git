package com.zx.core.system.oplog.action;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.zx.core.base.action.BaseAction;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserLog;
import com.zx.core.system.oplog.service.IOpLogService;

public class OpLogAction extends BaseAction {

	private static final long serialVersionUID = -8350569792282662155L;
	private IOpLogService opLogService;

	private String staDate;
	private String endDate;
	private HrUser hrUserOp;

	private List<HrUserLog> hrUserLogs;

	public IOpLogService getOpLogService() {
		return opLogService;
	}

	public void setOpLogService(IOpLogService opLogService) {
		this.opLogService = opLogService;
	}

	public String getStaDate() {
		return staDate;
	}

	public void setStaDate(String staDate) {
		this.staDate = staDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public HrUser getHrUserOp() {
		return hrUserOp;
	}

	public void setHrUserOp(HrUser hrUserOp) {
		this.hrUserOp = hrUserOp;
	}

	public List<HrUserLog> getHrUserLogs() {
		return hrUserLogs;
	}

	public void setHrUserLogs(List<HrUserLog> hrUserLogs) {
		this.hrUserLogs = hrUserLogs;
	}

	/***************************************************************************
	 * 显示用户操作日志信息的列表页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String list() {
		try {
			hrUserLogs = this.opLogService.findHrUserLogs(hrUserOp, staDate,
					endDate, getPaginate());
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e,
					getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return "list";
	}
}
