package com.zx.core.system.user.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.lcsoft.control.tag.page.util.PaginationUtils;
import com.zx.core.base.dao.BaseDAO;
import com.zx.core.base.jdbc.spring.JDBCTemplate;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserRole;
import com.zx.core.model.HrUserType;
import com.zx.core.model.SysRole;
import com.zx.core.util.DESUtil;
import com.zx.core.util.MD5Util;
import com.zx.core.util.SystemConst;

public class HrUserDAO extends BaseDAO<HrUser> implements IHrUserDAO {

	private JDBCTemplate jdbcTemplate;

	public JDBCTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JDBCTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/***************************************************************************
	 * 查询出所有的用户类型信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrUserType> findHrUserTypes() throws Exception {
		String hql = "from HrUserType where recordStatus=1";
		return this.find(hql);
	}

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
			Paginate paginate) throws Exception {
		String hql = "from HrUser where recordStatus=1";
		if (userTypeId != null && userTypeId != -1) {
			hql += " and hrUserType.id=" + userTypeId;
		}
		if (hrUser != null) {
			if (StringUtils.isNotEmpty(hrUser.getAccount())) {
				hql += " and account like '%" + hrUser.getAccount() + "%'";
			}
			if (StringUtils.isNotEmpty(hrUser.getName())) {
				hql += " and name like '%" + hrUser.getName() + "%'";
			}
		}
		hql += " order by createTime desc";
		PaginationUtils.getPaginationUtils().searchPaginate(hql, paginate);
		return paginate.getDatas();
	}

	/***************************************************************************
	 * 根据用户账号查询出用户信息
	 * 
	 * @param account
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUser(String account, Long userId)
			throws Exception {
		String hql = "from HrUser where recordStatus=1 and account='"
				+ account.trim() + "' and id!=" + userId;
		return this.find(hql);
	}

	/***************************************************************************
	 * 查询出导出的用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findExportUsers() throws Exception {
		String hql = " from HrUser where recordStatus=1 order by createTime desc";
		return this.find(hql);
	}

	/***************************************************************************
	 * 批量删除角色信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteHrUsers(String ids) throws Exception {
		String hql = "update HrUser set recordStatus=0 where id in (" + ids
				+ ")";
		this.getSession().createQuery(hql).executeUpdate();
	}

	/***************************************************************************
	 * 重置用户密码
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void updateResetPassWord(String ids) throws Exception {
		DESUtil desPlus = new DESUtil();
		String hql = "update HrUser set password='"
				+ MD5Util.generatePassword(SystemConst.originalPassword)
				+ "',originalPassword='"
				+ desPlus.encrypt(SystemConst.originalPassword)
				+ "' where id in (" + ids + ")";
		this.getSession().createQuery(hql).executeUpdate();
	}

	/***************************************************************************
	 * 导入用户数据时,批量新增用户信息
	 * 
	 * @param insertListMap
	 * @return
	 * @throws Exception
	 */
	public void saveBatchHrUserList(List<Map<String, String>> insertListMap)
			throws Exception {
		if (insertListMap == null || insertListMap.size() == 0) {
			return;
		}
		StringBuffer insertFields = new StringBuffer();
		StringBuffer insertData = new StringBuffer();
		Map<String, String> firstMap = insertListMap.get(0);
		if (firstMap == null) {
			return;
		}
		Iterator<String> fieldIT = firstMap.keySet().iterator();
		while (fieldIT.hasNext()) {
			insertFields.append((String) fieldIT.next() + ",");
		}

		for (Map<String, String> map : insertListMap) {
			Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
			insertData.append(" select ");
			while (it.hasNext()) {
				Map.Entry<String, String> tmpEntry = it.next();
				if (StringUtils.isEmpty(tmpEntry.getValue())) {
					insertData.append("NULL,");
				} else {
					insertData.append("'" + tmpEntry.getValue() + "',");
				}

			}
			insertData.deleteCharAt(insertData.length() - 1);
			insertData.append(" union");
		}
		String insertSQL = "insert into hr_user ("
				+ insertFields.substring(0, insertFields.length() - 1) + ") "
				+ insertData.substring(0, insertData.length() - 6);
		System.out.println("===========" + insertSQL);
		jdbcTemplate.executeSql(insertSQL);
	}

	/***************************************************************************
	 * 导入用户数据时,批量修改用户信息
	 * 
	 * @param updateListMap
	 * @return
	 * @throws Exception
	 */
	public void updateBatchHrUserList(List<Map<String, String>> updateListMap)
			throws Exception {
		if (updateListMap == null || updateListMap.size() == 0) {
			return;
		}
		StringBuffer updateFields = new StringBuffer();
		StringBuffer updateData = new StringBuffer();
		Map<String, String> firstMap = updateListMap.get(0);
		if (firstMap == null) {
			return;
		}
		Iterator<String> fieldIT = firstMap.keySet().iterator();
		while (fieldIT.hasNext()) {
			String fieldStr = fieldIT.next();
			if (!"Id".equals(fieldStr)) {
				updateFields.append("tab1." + fieldStr + "=tab2." + fieldStr
						+ ",");
			}
		}

		for (Map<String, String> map : updateListMap) {
			Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
			updateData.append(" select ");
			while (it.hasNext()) {
				Map.Entry<String, String> tmpEntry = it.next();

				if (StringUtils.isEmpty(tmpEntry.getValue())) {
					updateData.append("NULL " + tmpEntry.getKey() + ",");
				} else {
					updateData.append("'" + tmpEntry.getValue() + "' "
							+ tmpEntry.getKey() + ",");
				}
			}
			updateData.deleteCharAt(updateData.length() - 1);
			updateData.append(" union");
		}
		String updateSQL = "update hr_user tab1,("
				+ updateData.substring(0, updateData.length() - 6)
				+ " ) tab2 set "
				+ updateFields.substring(0, updateFields.length() - 1)
				+ " where tab1.account=tab2.account";
		System.out.println("===========" + updateSQL);
		jdbcTemplate.executeSql(updateSQL);
	}

	/***************************************************************************
	 * 查询出不属于该用户的所有角色
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findUserNoSysRole(Long userId) throws Exception {
		String hql = "from SysRole sr where sr.id not in ("
				+ "select hur.sysRole.id from HrUserRole hur where hur.recordStatus=1 and hur.hrUser.id="
				+ userId + ") and sr.recordStatus=1";
		return this.find(hql);
	}

	/***************************************************************************
	 * 查询出用户所拥有的所有角色
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findUserSysRole(Long userId) throws Exception {
		String hql = "select hur.sysRole from HrUserRole hur where hur.recordStatus=1 and hur.hrUser.id="
				+ userId;
		return this.find(hql);
	}

	/***************************************************************************
	 * 删除用户的所有角色
	 * 
	 * @param userId
	 * @throws Exception
	 */
	public void deleteUserSysRole(Long userId) throws Exception {
		String hql = "delete from HrUserRole hur where hur.hrUser.id=" + userId;
		this.getSession().createQuery(hql).executeUpdate();
	}

	/***************************************************************************
	 * 保存人员角色信息
	 * 
	 * @param hrUserRoles
	 * @throws Exception
	 */
	public void saveUserSysRole(List<HrUserRole> hrUserRoles) throws Exception {
		this.saveOrUpdate(hrUserRoles);
	}
}
