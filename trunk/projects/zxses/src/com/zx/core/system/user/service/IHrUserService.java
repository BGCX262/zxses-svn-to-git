package com.zx.core.system.user.service;

import java.util.List;
import java.util.Map;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserType;
import com.zx.core.model.SysRole;

public interface IHrUserService {

	/***************************************************************************
	 * 查询出所有的用户类型信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrUserType> findHrUserTypes() throws ManageException;

	/***************************************************************************
	 * 根据用户类型、用户账号、用户姓名查询出所有的用户信息
	 * 
	 * @param userType
	 * @param hrUser
	 * @param paginate
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(HrUserType userType, HrUser hrUser,
			Paginate paginate) throws ManageException;

	/***************************************************************************
	 * 根据Id查询出用户信息
	 * 
	 * @param userId
	 * @return
	 * @throws ManageException
	 */
	public HrUser findHrUser(Long userId) throws ManageException;

	/***************************************************************************
	 * 判断是否存在相同的Code
	 * 
	 * @param hrUser
	 * @return
	 * @throws ManageException
	 */
	public boolean isExistHrUser(HrUser hrUser) throws ManageException;

	/***************************************************************************
	 * 添加用户信息
	 * 
	 * @param hrUser
	 * @throws ManageException
	 */
	public void saveHrUser(HrUser hrUser) throws ManageException;

	/***************************************************************************
	 * 修改用户信息
	 * 
	 * @param hrUser
	 * @throws ManageException
	 */
	public void updateHrUser(HrUser hrUser) throws ManageException;

	/***************************************************************************
	 * 批量删除用户信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws ManageException
	 */
	public void deleteHrUsers(String ids) throws ManageException;

	/***************************************************************************
	 * 重置用户密码
	 * 
	 * @param ids
	 * @throws ManageException
	 */
	public void updateResetPassword(String ids) throws ManageException;

	/***************************************************************************
	 * 查询出导出的用户信息
	 * 
	 * @return
	 * @throws ManageException
	 */
	public List<HrUser> findExportUsers() throws ManageException;

	/***************************************************************************
	 * 导入用户信息，新增和修改用户信息
	 * 
	 * @param insertListMap
	 * @param updateListMap
	 * @throws ManageException
	 */
	public void saveOrUpdateBatchHrUsers(
			List<Map<String, String>> insertListMap,
			List<Map<String, String>> updateListMap) throws ManageException;

	/***************************************************************************
	 * 查询出不属于该用户的所有角色
	 * 
	 * @param hrUser
	 * @return
	 * @throws ManageException
	 */
	public List<SysRole> findUserNoSysRole(HrUser hrUser)
			throws ManageException;

	/***************************************************************************
	 * 查询出用户所拥有的所有角色
	 * 
	 * @param hrUser
	 * @return
	 * @throws ManageException
	 */
	public List<SysRole> findUserSysRole(HrUser hrUser) throws ManageException;

	/***************************************************************************
	 * 保存人员角色信息
	 * 
	 * @param hrUser
	 * @param selectSysRole
	 * @throws ManageException
	 */
	public void saveUserSysRole(HrUser hrUser, List<SysRole> selectSysRole)
			throws ManageException;

}
