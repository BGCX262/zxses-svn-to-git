package com.zx.es.system.basic.accountkind.service;

import java.util.Date;
import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.exception.ManageException;
import com.zx.es.model.BasinfAccountKind;
import com.zx.es.system.basic.accountkind.dao.IAccountKindDAO;

/*******************************************************************************
 * @Title: 系统管理-基本代码-户口性质
 * @Author:毛陆军
 * @Create:2011-09-08
 */
public class AccountKindService implements IAccountKindService {

	private IAccountKindDAO accountKindDAO;

	public IAccountKindDAO getAccountKindDAO() {
		return accountKindDAO;
	}

	public void setAccountKindDAO(IAccountKindDAO accountKindDAO) {
		this.accountKindDAO = accountKindDAO;
	}

	/***************************************************************************
	 * 查询出所有的户口性质别信息
	 * 
	 * @return
	 * @throws ManageException
	 */
	public List<BasinfAccountKind> findAccountKinds(Paginate paginate)
			throws ManageException {
		try {
			return accountKindDAO.findAccountKinds(paginate);
		} catch (Exception e) {
			throw new ManageException("查询户口性质列表信息出错", e);
		}
	}

	/***************************************************************************
	 * 根据Id查询出户口性质信息
	 * 
	 * @param accountKindId
	 * @return
	 * @throws ManageException
	 */
	public BasinfAccountKind findAccountKind(Integer accountKindId)
			throws ManageException {
		try {
			return (BasinfAccountKind) accountKindDAO.load(
					BasinfAccountKind.class, accountKindId);
		} catch (Exception e) {
			throw new ManageException("查询户口性质信息出错", e);
		}
	}

	/***************************************************************************
	 * 判断是否存在相同的Code
	 * 
	 * @param accountKind
	 * @return
	 * @throws ManageException
	 */
	public boolean isExistAccountKind(BasinfAccountKind accountKind)
			throws ManageException {
		boolean isExist = false;
		List<BasinfAccountKind> accountKindList = null;
		try {
			accountKindList = accountKindDAO.findAccountKind(accountKind
					.getAccountKindCode(), accountKind.getId());
		} catch (Exception e) {
			throw new ManageException("根据Code查询户口性质信息出错", e);
		}
		if (accountKindList != null && accountKindList.size() > 0) {
			isExist = true;
		}
		return isExist;
	}

	/***************************************************************************
	 * 添加户口性质信息
	 * 
	 * @param accountKind
	 * @throws ManageException
	 */
	public void saveAccountKind(BasinfAccountKind accountKind)
			throws ManageException {
		try {
			accountKind.setUpdateTime(new Date());
			accountKind.setRecordStatus(1);
			this.accountKindDAO.save(accountKind);
		} catch (Exception e) {
			throw new ManageException("添加户口性质信息出错", e);
		}
	}

	/***************************************************************************
	 * 修改户口性质信息
	 * 
	 * @param accountKind
	 * @throws ManageException
	 */
	public void updateAccountKind(BasinfAccountKind accountKind)
			throws ManageException {
		try {
			accountKind.setUpdateTime(new Date());
			accountKind.setRecordStatus(1);
			this.accountKindDAO.update(accountKind);
		} catch (Exception e) {
			throw new ManageException("修改户口性质信息息出错", e);
		}
	}

	/***************************************************************************
	 * 批量删除户口性质信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws ManageException
	 */
	public void deleteAccountKinds(String ids) throws ManageException {
		try {
			this.accountKindDAO.deleteAccountKind(ids);
		} catch (Exception e) {
			throw new ManageException("删除户口性质信息出错", e);
		}
	}
}
