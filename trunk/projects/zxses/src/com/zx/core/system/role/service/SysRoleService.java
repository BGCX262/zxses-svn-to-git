package com.zx.core.system.role.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserRole;
import com.zx.core.model.HrUserType;
import com.zx.core.model.SysMenu;
import com.zx.core.model.SysRole;
import com.zx.core.system.role.dao.ISysRoleDAO;
import com.zx.core.util.SysLoggerUtil;

public class SysRoleService implements ISysRoleService {

	private ISysRoleDAO sysRoleDAO;

	public ISysRoleDAO getSysRoleDAO() {
		return sysRoleDAO;
	}

	public void setSysRoleDAO(ISysRoleDAO sysRoleDAO) {
		this.sysRoleDAO = sysRoleDAO;
	}

	/***************************************************************************
	 * 查询出所有的角色信息
	 * 
	 * @return
	 * @throws ManageException
	 */
	public List<SysRole> findSysRoles(Paginate paginate) throws ManageException {
		try {
			return this.sysRoleDAO.findSysRoles(paginate);
		} catch (Exception e) {
			throw new ManageException("查询角色列表信息出错", e);
		}
	}

	/***************************************************************************
	 * 根据Id查询出角色信息
	 * 
	 * @param roleId
	 * @return
	 * @throws ManageException
	 */
	public SysRole findSysRole(Long roleId) throws ManageException {
		try {
			return (SysRole) this.sysRoleDAO.load(SysRole.class, roleId);
		} catch (Exception e) {
			throw new ManageException("查询角色信息出错", e);
		}
	}

	/***************************************************************************
	 * 判断是否存在相同的Code
	 * 
	 * @param sysRole
	 * @return
	 * @throws ManageException
	 */
	public boolean isExistSysRole(SysRole sysRole) throws ManageException {
		boolean isExist = false;
		List<SysRole> sysRoleList = null;
		try {
			sysRoleList = this.sysRoleDAO.findSysRole(sysRole.getCode(),
					sysRole.getId());
		} catch (Exception e) {
			throw new ManageException("根据Code查询角色信息出错", e);
		}
		if (sysRoleList != null && sysRoleList.size() > 0) {
			isExist = true;
		}
		return isExist;
	}

	/***************************************************************************
	 * 添加角色信息
	 * 
	 * @param sysRole
	 * @throws ManageException
	 */
	public void saveSysRole(SysRole sysRole) throws ManageException {
		try {
			sysRole.setUpdateTime(new Date());
			sysRole.setCreateTime(new Date());
			sysRole.setRecordStatus(1);
			this.sysRoleDAO.save(sysRole);
		} catch (Exception e) {
			throw new ManageException("添加角色信息出错", e);
		}
	}

	/***************************************************************************
	 * 修改角色信息
	 * 
	 * @param sysRole
	 * @throws ManageException
	 */
	public void updateSysRole(SysRole sysRole) throws ManageException {
		SysRole updateSysRole;
		try {
			updateSysRole = (SysRole) this.sysRoleDAO.load(SysRole.class,
					sysRole.getId());
			updateSysRole.setCode(sysRole.getCode());
			updateSysRole.setName(sysRole.getName());
			sysRole.setUpdateTime(new Date());
			sysRole.setRecordStatus(1);
			this.sysRoleDAO.update(updateSysRole);
		} catch (Exception e) {
			throw new ManageException("修改角色信息出错", e);
		}
	}

	/***************************************************************************
	 * 批量删除角色信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws ManageException
	 */
	public void deleteSysRoles(String ids) throws ManageException {
		try {
			this.sysRoleDAO.deleteSysRoles(ids);
		} catch (Exception e) {
			throw new ManageException("删除角色信息出错", e);
		}
	}

	/***************************************************************************
	 * 查询出所有的角色信息
	 * 
	 * @return
	 * @throws ManageException
	 */
	public List<SysRole> findSysRoles() throws ManageException {
		try {
			return this.sysRoleDAO.findSysRoles();
		} catch (Exception e) {
			throw new ManageException("查询角色列表信息出错", e);
		}
	}

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
			String roleId) throws ManageException {
		try {
			List<HrUser> listUsers = this.sysRoleDAO.findHrUsers(roleId);
			String userIds = "-1";
			if (listUsers != null && listUsers.size() != 0) {
				for (HrUser user : listUsers) {
					userIds = userIds + "," + user.getId();
				}
			}

			Long userTypeId = null;
			if (userType != null) {
				userTypeId = userType.getId();
			}
			return this.sysRoleDAO.findHrUsers(userTypeId, hrUser, userIds);
		} catch (Exception e) {
			throw new ManageException("查询用户列表信息出错", e);
		}
	}

	/***************************************************************************
	 * 根据用户角色查询出所有的用户信息（不分页）
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(String roleId) throws ManageException {
		try {
			return this.sysRoleDAO.findHrUsers(roleId);
		} catch (Exception e) {
			throw new ManageException("查询角色用户列表出错", e);
		}
	}

	/***************************************************************************
	 * 根据用户角色查询出角色用户信息（不分页）
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<HrUserRole> findRoleUsers(String roleId) throws ManageException {
		try {
			return this.sysRoleDAO.findRoleUsers(roleId);
		} catch (Exception e) {
			throw new ManageException("查询角色用户列表出错", e);
		}
	}

	/***************************************************************************
	 * 添加角色用户信息
	 * 
	 * @param sysRole
	 * @throws ManageException
	 */
	public void saveRoleUsers(String roleId, String userIds)
			throws ManageException {
		try {
			List<HrUser> listUsers = sysRoleDAO.findHrUsersByIds(userIds);
			SysRole sysRole = (SysRole) sysRoleDAO.load(SysRole.class, Long
					.valueOf(roleId));
			List<HrUserRole> listUserRole = new ArrayList<HrUserRole>();

			HrUserRole hrUserRole = null;

			for (HrUser hrUser : listUsers) {
				hrUserRole = new HrUserRole();
				hrUserRole.setHrUser(hrUser);
				hrUserRole.setSysRole(sysRole);
				hrUserRole.setRecordStatus(1);
				listUserRole.add(hrUserRole);
			}
			sysRoleDAO.saveOrUpdate(listUserRole);
		} catch (Exception e) {
			throw new ManageException("添加角色人员信息出错", e);
		}
	}

	/***************************************************************************
	 * 批量删除角色用户信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws ManageException
	 */
	public void deleteRoleUsers(String ids) throws ManageException {
		try {
			this.sysRoleDAO.deleteRoleUsers(ids);
		} catch (Exception e) {
			throw new ManageException("删除角色用户信息出错", e);
		}
	}

	/***************************************************************************
	 * 根据角色查询出角色的权限菜单
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> findSysMenus(String roleId) throws ManageException {
		try {
			if (StringUtils.isEmpty(roleId)) {
				return new ArrayList<SysMenu>();
			}
			return this.sysRoleDAO.findSysMenus(roleId);
		} catch (Exception e) {
			throw new ManageException("查询角色菜单列表信息出错", e);
		}
	}

	/***************************************************************************
	 * 保存角色的权限菜单
	 * 
	 * @param roleId
	 * @param menuIds
	 * @throws Exception
	 */
	public void saveUserMenu(String roleId, String menuIds)
			throws ManageException {
		try {
			this.sysRoleDAO.deleteUserMenu(roleId);
			menuIds = menuIds.substring(1, menuIds.length());
			if (StringUtils.isNotEmpty(menuIds)) {
				this.sysRoleDAO.saveUserMenu(roleId, menuIds);
			}
		} catch (Exception e) {
			throw new ManageException("角色授权出错", e);
		}
	}
}
