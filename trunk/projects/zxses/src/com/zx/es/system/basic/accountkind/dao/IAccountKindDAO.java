package com.zx.es.system.basic.accountkind.dao;

import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.base.dao.IBaseDAO;
import com.zx.es.model.BasinfAccountKind;

/*******************************************************************************
 * @Description 系统管理-基本代码-户口性质
 * @author 毛陆军
 * @date 2011-09-08
 * 
 */
public interface IAccountKindDAO extends IBaseDAO<BasinfAccountKind> {

	/***************************************************************************
	 * 查询出所有的户口性质信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BasinfAccountKind> findAccountKinds(Paginate paginate) throws Exception;

	/***************************************************************************
	 * 根据编号查询出户口性质信息
	 * 
	 * @param accountKindCode
	 * @param accountKindId
	 * @return
	 * @throws Exception
	 */
	public List<BasinfAccountKind> findAccountKind(String accountKindCode,
			Integer accountKindId) throws Exception;

	/***************************************************************************
	 * 批量删除户口性质信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteAccountKind(String ids) throws Exception;

}
