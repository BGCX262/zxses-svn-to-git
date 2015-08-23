package com.zx.core.system.user.dao;

import java.util.List;
import java.util.Map;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.base.dao.IBaseDAO;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserRole;
import com.zx.core.model.HrUserType;
import com.zx.core.model.SysRole;

public interface IHrUserDAO extends IBaseDAO<HrUser> {

	/***************************************************************************
	 * 查询出所有的用户类型信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrUserType> findHrUserTypes() throws Exception;

	/***************************************************************************
	 * 根据用户类型、用户账号、用户姓名查询出所有的用户信息
	 * 
	 * @param userTypeId
	 * @param hrUser
	 * @param paginate
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(Long userTypeId, HrUser hrUser,
			Paginate paginate) throws Exception;

	/***************************************************************************
	 * 根据用户账号查询出用户信息
	 * 
	 * @param account
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUser(String account, Long userId)
			throws Exception;

	/***************************************************************************
	 * 查询出导出的用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findExportUsers() throws Exception;

	/***************************************************************************
	 * 批量删除角色信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteHrUsers(String ids) throws Exception;

	/***************************************************************************
	 * 重置用户密码
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void updateResetPassWord(String ids) throws Exception;

	/***************************************************************************
	 * 导入用户数据时,批量新增用户信息
	 * 
	 * @param insertListMap
	 * @return
	 * @throws Exception
	 */
	public void saveBatchHrUserList(List<Map<String, String>> insertListMap)
			throws Exception;

	/***************************************************************************
	 * 导入用户数据时,批量修改用户信息
	 * 
	 * @param updateListMap
	 * @return
	 * @throws Exception
	 */
	public void updateBatchHrUserList(List<Map<String, String>> updateListMap)
			throws Exception;

	/***************************************************************************
	 * 查询出不属于该用户的所有角色
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findUserNoSysRole(Long userId) throws Exception;

	/***************************************************************************
	 * 查询出用户所拥有的所有角色
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findUserSysRole(Long userId) throws Exception;

	/***************************************************************************
	 * 删除用户的所有角色
	 * 
	 * @param userId
	 * @throws Exception
	 */
	public void deleteUserSysRole(Long userId) throws Exception;

	/***************************************************************************
	 * 保存人员角色信息
	 * 
	 * @param hrUserRoles
	 * @throws Exception
	 */
	public void saveUserSysRole(List<HrUserRole> hrUserRoles) throws Exception;

}
