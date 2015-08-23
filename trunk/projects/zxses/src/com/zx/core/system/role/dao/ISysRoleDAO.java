package com.zx.core.system.role.dao;

import java.util.List;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.base.dao.IBaseDAO;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserRole;
import com.zx.core.model.SysMenu;
import com.zx.core.model.SysRole;

public interface ISysRoleDAO extends IBaseDAO<SysRole> {

	/***************************************************************************
	 * 查询出所有的角色信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findSysRoles(Paginate paginate) throws Exception;

	/***************************************************************************
	 * 根据角色编号查询出角色信息
	 * 
	 * @param roleCode
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findSysRole(String roleCode, Long roleId)
			throws Exception;

	/***************************************************************************
	 * 批量删除角色信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteSysRoles(String ids) throws Exception;

	/***************************************************************************
	 * 查询出所有的角色信息（不分页）
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findSysRoles() throws Exception;

	/***************************************************************************
	 * 根据用户类型、用户账号、用户姓名查询出所有的用户信息(不用分页功能)
	 * 
	 * @param userTypeId
	 * @param hrUser
	 * @param paginate
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(Long userTypeId, HrUser hrUser,
			String userIds) throws Exception;

	/***************************************************************************
	 * 根据用户角色ID查询的用户信息(不用分页功能)
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(String roleId) throws Exception;

	/***************************************************************************
	 * 根据用户角色ID查询的角色用户信息(不用分页功能)
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<HrUserRole> findRoleUsers(String roleId) throws Exception;

	/**
	 * @findHrUsersByIds
	 * @Description:
	 * @return List<HrUser>
	 * @Author: zhoupk
	 * @Time: Dec 28, 2011
	 */
	public List<HrUser> findHrUsersByIds(String userIds) throws Exception;

	/***************************************************************************
	 * 批量删除角色用户信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteRoleUsers(String ids) throws Exception;

	/***************************************************************************
	 * 根据角色查询出角色的权限菜单
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> findSysMenus(String roleId) throws Exception;

	/***************************************************************************
	 * 删除角色所有的权限
	 * 
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteUserMenu(String roleId) throws Exception;

	/***************************************************************************
	 * 保存角色的权限菜单
	 * 
	 * @param roleId
	 * @param menuIds
	 * @throws Exception
	 */
	public void saveUserMenu(String roleId, String menuIds) throws Exception;
}
