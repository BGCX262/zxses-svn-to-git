package com.zx.es.system.basic.accountkind.action;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.zx.core.base.action.BaseAction;
import com.zx.core.exception.ManageException;
import com.zx.es.model.BasinfAccountKind;
import com.zx.es.system.basic.accountkind.service.IAccountKindService;

public class AccountKindAction extends BaseAction {

	private static final long serialVersionUID = -8350569792282662155L;
	private IAccountKindService accountKindService;

	private BasinfAccountKind accountKind;

	private List<BasinfAccountKind> accountKinds;

	public IAccountKindService getAccountKindService() {
		return accountKindService;
	}

	public void setAccountKindService(IAccountKindService accountKindService) {
		this.accountKindService = accountKindService;
	}

	public BasinfAccountKind getAccountKind() {
		return accountKind;
	}

	public void setAccountKind(BasinfAccountKind accountKind) {
		this.accountKind = accountKind;
	}

	public List<BasinfAccountKind> getAccountKinds() {
		return accountKinds;
	}

	public void setAccountKinds(List<BasinfAccountKind> accountKinds) {
		this.accountKinds = accountKinds;
	}

	/***************************************************************************
	 * 显示列表页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String list() {
		try {
			accountKinds = this.accountKindService
					.findAccountKinds(getPaginate());
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e);
			this.addMessageError("find.fail");
		}
		return "list";
	}

	/***************************************************************************
	 * 转跳到新增页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String entryAdd() {
		return "entryAdd";
	}

	/***************************************************************************
	 * 保存新增的信息
	 * 
	 * @return
	 */
	public String save() {
		try {
			if (this.accountKindService.isExistAccountKind(accountKind)) {
				this.addMessageError("code.isExist");
				return "entryAdd";
			}
			if (getFieldErrors().size() > 0) {
				return "entryAdd";
			}
			this.accountKindService.saveAccountKind(accountKind);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e);
			this.addMessageError("save.fail");
			return "entryAdd";
		}
		this.addMessageInfo("save.success");
		setAccountKind(null);
		return entryAdd();
	}

	/***************************************************************************
	 * 转跳到更新页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String entryUpdate() {
		try {
			setAccountKind(this.accountKindService.findAccountKind(accountKind
					.getId()));
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e);
			this.addMessageError("find.fail");
		}
		return "entryUpdate";
	}

	/***************************************************************************
	 * 保存修改之后的信息
	 * 
	 * @return
	 */
	public String update() {
		try {
			if (this.accountKindService.isExistAccountKind(accountKind)) {
				this.addMessageError("code.isExist");
				return "entryUpdate";
			}

			if (getFieldErrors().size() > 0) {
				return "entryUpdate";
			}
			this.accountKindService.updateAccountKind(accountKind);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e);
			this.addMessageError("update.fail");
			return "entryUpdate";
		}
		this.addMessageInfo("update.success");
		return "entryUpdate";
	}

	/***************************************************************************
	 * 删除
	 * 
	 * @return
	 */
	@SkipValidation
	public String delete() {
		try {
			this.accountKindService.deleteAccountKinds(checkIds);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e);
			this.addMessageError("delete.failure");
			return list();
		}
		this.addMessageInfo("delete.success");
		return list();
	}
}
