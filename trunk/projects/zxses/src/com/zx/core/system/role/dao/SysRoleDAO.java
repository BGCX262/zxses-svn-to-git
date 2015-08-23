package com.zx.core.system.role.dao;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.lcsoft.control.tag.page.util.PaginationUtils;
import com.zx.core.base.dao.BaseDAO;
import com.zx.core.base.jdbc.spring.JDBCTemplate;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserRole;
import com.zx.core.model.SysMenu;
import com.zx.core.model.SysRole;

public class SysRoleDAO extends BaseDAO<SysRole> implements ISysRoleDAO {

	private JDBCTemplate jdbcTemplate;

	public JDBCTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JDBCTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/***************************************************************************
	 * 查询出所有的角色信息（分页）
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findSysRoles(Paginate paginate) throws Exception {
		String hql = "from SysRole where recordStatus=1 order by createTime desc";
		PaginationUtils.getPaginationUtils().searchPaginate(hql, paginate);
		return paginate.getDatas();
	}

	/***************************************************************************
	 * 查询出所有的角色信息（不分页）
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findSysRoles() throws Exception {
		String hql = "from SysRole where recordStatus=1 order by createTime desc";
		List<SysRole> list = this.find(hql);
		return list;
	}

	/***************************************************************************
	 * 根据角色编号查询出角色信息
	 * 
	 * @param roleCode
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findSysRole(String roleCode, Long roleId)
			throws Exception {
		String hql = "from SysRole where recordStatus=1 and code='"
				+ roleCode.trim() + "' and id!=" + roleId;
		return this.find(hql);
	}

	/***************************************************************************
	 * 批量删除角色信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteSysRoles(String ids) throws Exception {
		String hql = "update SysRole set recordStatus=0 where id in (" + ids
				+ ")";
		this.getSession().createQuery(hql).executeUpdate();
	}

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
			String userIds) throws Exception {
		String hql = "from HrUser where recordStatus=1";
		if (userTypeId != null && userTypeId != -1) {
			hql += " and hrUserType.id=" + userTypeId;
		}
		if (hrUser != null) {
			if (StringUtils.isNotEmpty(hrUser.getAccount())) {
				hql += " and account like '" + hrUser.getAccount() + "%'";
			}
			if (StringUtils.isNotEmpty(hrUser.getName())) {
				hql += " and name like '" + hrUser.getName() + "%'";
			}
		}
		hql += " and id not in (" + userIds + ")";
		return this.find(hql);
	}

	/***************************************************************************
	 * 根据用户角色ID查询的用户信息(不用分页功能)
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(String roleId) throws Exception {
		String hql = "select hrUser from HrUserRole hur where hur.sysRole.id="
				+ roleId + "  and  hur.recordStatus=1";
		return this.find(hql);
	}

	/***************************************************************************
	 * 根据用户角色ID查询的角色用户信息(不用分页功能)
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<HrUserRole> findRoleUsers(String roleId) throws Exception {
		String hql = "from HrUserRole hur where hur.sysRole.id=" + roleId
				+ "  and  hur.recordStatus=1";
		return this.find(hql);
	}

	/**
	 * @findHrUsersByIds
	 * @Description:
	 * @return List<HrUser>
	 * @Author: zhoupk
	 * @Time: Dec 28, 2011
	 */
	public List<HrUser> findHrUsersByIds(String userIds) throws Exception {
		String hql = "from HrUser where recordStatus=1 and id in (" + userIds
				+ ")";
		return this.find(hql);
	}

	/***************************************************************************
	 * 批量删除角色用户信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteRoleUsers(String ids) throws Exception {
		String hql = "update HrUserRole set recordStatus=0 where id in (" + ids
				+ ")";
		this.getSession().createQuery(hql).executeUpdate();
	}

	/***************************************************************************
	 * 根据角色查询出角色的权限菜单
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> findSysMenus(String roleId) throws Exception {
		String hql = "select srm.sysMenu from SysRoleMenu srm where srm.recordStatus=1 and srm.sysRole.id="
				+ roleId + " and srm.sysMenu.recordStatus=1";
		return this.find(hql);
	}

	/***************************************************************************
	 * 删除角色所有的权限
	 * 
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteUserMenu(String roleId) throws Exception {
		String hql = "delete from SysRoleMenu srm where srm.sysRole.id="
				+ roleId;
		this.getSession().createQuery(hql).executeUpdate();
	}

	/***************************************************************************
	 * 保存角色的权限菜单
	 * 
	 * @param roleId
	 * @param menuIds
	 * @throws Exception
	 */
	public void saveUserMenu(String roleId, String menuIds) throws Exception {

		StringBuffer userMenuStr = new StringBuffer();
		String[] menuIdsArr = menuIds.split(",");
		for (String s : menuIdsArr) {
			userMenuStr.append("select " + s + ", " + roleId + ",1");
			userMenuStr.append(" union ");
		}
		String sql = "insert into sys_role_menu(SYS_MENU_ID,SYS_ROLE_ID,RECORD_STATUS) "
				+ userMenuStr.substring(0, userMenuStr.length() - 6);
		this.jdbcTemplate.executeSql(sql);
	}

}
