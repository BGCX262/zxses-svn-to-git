package com.zx.core.system.role.service;

import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserRole;
import com.zx.core.model.HrUserType;
import com.zx.core.model.SysMenu;
import com.zx.core.model.SysRole;

public interface ISysRoleService {

	/***************************************************************************
	 * 查询出所有的角色信息
	 * 
	 * @return
	 * @throws ManageException
	 */
	public List<SysRole> findSysRoles(Paginate paginat) throws ManageException;

	/***************************************************************************
	 * 根据Id查询出角色信息
	 * 
	 * @param roleId
	 * @return
	 * @throws ManageException
	 */
	public SysRole findSysRole(Long roleId) throws ManageException;

	/***************************************************************************
	 * 判断是否存在相同的Code
	 * 
	 * @param sysRole
	 * @return
	 * @throws ManageException
	 */
	public boolean isExistSysRole(SysRole sysRole) throws ManageException;

	/***************************************************************************
	 * 添加角色信息
	 * 
	 * @param sysRole
	 * @throws ManageException
	 */
	public void saveSysRole(SysRole sysRole) throws ManageException;

	/***************************************************************************
	 * 修改角色信息
	 * 
	 * @param sysRole
	 * @throws ManageException
	 */
	public void updateSysRole(SysRole sysRole) throws ManageException;

	/***************************************************************************
	 * 批量删除角色信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws ManageException
	 */
	public void deleteSysRoles(String ids) throws ManageException;

	/***************************************************************************
	 * 查询出所有的角色信息
	 * 
	 * @return
	 * @throws ManageException
	 */
	public List<SysRole> findSysRoles() throws ManageException;

	/***************************************************************************
	 * 根据用户类型、用户账号、用户姓名查询出所有的用户信息（不分页）
	 * 
	 * @param userType
	 * @param hrUser
	 * @param paginate
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(HrUserType userType, HrUser hrUser,
			String roleId) throws ManageException;

	/***************************************************************************
	 * 根据用户角色查询出所有的用户信息（不分页）
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(String roleId) throws ManageException;

	/***************************************************************************
	 * 根据用户角色查询出角色用户信息（不分页）
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<HrUserRole> findRoleUsers(String roleId) throws ManageException;

	/***************************************************************************
	 * 添加角色用户信息
	 * 
	 * @param sysRole
	 * @throws ManageException
	 */
	public void saveRoleUsers(String roleId, String userIds)
			throws ManageException;

	/***************************************************************************
	 * 批量删除角色用户信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws ManageException
	 */
	public void deleteRoleUsers(String ids) throws ManageException;

	/***************************************************************************
	 * 根据角色查询出角色的权限菜单
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> findSysMenus(String roleId) throws ManageException;

	/***************************************************************************
	 * 保存角色的权限菜单
	 * 
	 * @param roleId
	 * @param menuIds
	 * @throws Exception
	 */
	public void saveUserMenu(String roleId, String menuIds)
			throws ManageException;

}
