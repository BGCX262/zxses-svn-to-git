package com.zx.es.system.basic.accountkind.service;

import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.exception.ManageException;
import com.zx.es.model.BasinfAccountKind;

/*******************************************************************************
 * @Description 系统管理-基本代码-户口性质
 * @author 毛陆军
 * @date 2011-09-08
 * 
 */
public interface IAccountKindService {

	/***************************************************************************
	 * 查询出所有的户口性质信息
	 * 
	 * @return
	 * @throws ManageException
	 */
	public List<BasinfAccountKind> findAccountKinds(Paginate paginate)
			throws ManageException;

	/***************************************************************************
	 * 根据Id查询出户口性质信息
	 * 
	 * @param accountKindId
	 * @return
	 * @throws ManageException
	 */
	public BasinfAccountKind findAccountKind(Integer accountKindId)
			throws ManageException;

	/***************************************************************************
	 * 判断是否存在相同的Code
	 * 
	 * @param accountKind
	 * @return
	 * @throws ManageException
	 */
	public boolean isExistAccountKind(BasinfAccountKind accountKind)
			throws ManageException;

	/***************************************************************************
	 * 添加户口性质信息
	 * 
	 * @param accountKind
	 * @throws ManageException
	 */
	public void saveAccountKind(BasinfAccountKind accountKind)
			throws ManageException;

	/***************************************************************************
	 * 修改户口性质信息
	 * 
	 * @param accountKind
	 * @throws ManageException
	 */
	public void updateAccountKind(BasinfAccountKind accountKind)
			throws ManageException;

	/***************************************************************************
	 * 批量删除户口性质信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws ManageException
	 */
	public void deleteAccountKinds(String ids) throws ManageException;
}
