package com.zx.core.system.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserRole;
import com.zx.core.model.HrUserType;
import com.zx.core.model.SysRole;
import com.zx.core.system.user.dao.IHrUserDAO;
import com.zx.core.util.DESUtil;
import com.zx.core.util.DateUtil;
import com.zx.core.util.MD5Util;
import com.zx.core.util.SysLoggerUtil;
import com.zx.core.util.SystemConst;

public class HrUserService implements IHrUserService {

	private IHrUserDAO hrUserDAO;

	public IHrUserDAO getHrUserDAO() {
		return hrUserDAO;
	}

	public void setHrUserDAO(IHrUserDAO hrUserDAO) {
		this.hrUserDAO = hrUserDAO;
	}

	/***************************************************************************
	 * 查询出所有的用户类型信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrUserType> findHrUserTypes() throws ManageException {
		try {
			return this.hrUserDAO.findHrUserTypes();
		} catch (Exception e) {
			throw new ManageException("查询用户类型列表出错");
		}
	}

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
			Paginate paginate) throws ManageException {
		try {
			Long userTypeId = null;
			if (userType != null) {
				userTypeId = userType.getId();
			}
			return this.hrUserDAO.findHrUsers(userTypeId, hrUser, paginate);
		} catch (Exception e) {
			throw new ManageException("查询用户列表信息出错", e);
		}
	}

	/***************************************************************************
	 * 根据Id查询出用户信息
	 * 
	 * @param userId
	 * @return
	 * @throws ManageException
	 */
	public HrUser findHrUser(Long userId) throws ManageException {
		try {
			return (HrUser) this.hrUserDAO.load(HrUser.class, userId);
		} catch (Exception e) {
			throw new ManageException("查询用户信息出错", e);
		}
	}

	/***************************************************************************
	 * 判断是否存在相同的Code
	 * 
	 * @param hrUser
	 * @return
	 * @throws ManageException
	 */
	public boolean isExistHrUser(HrUser hrUser) throws ManageException {
		boolean isExist = false;
		List<HrUser> hrUserList = null;
		try {
			hrUserList = this.hrUserDAO.findHrUser(hrUser.getAccount(), hrUser
					.getId());
		} catch (Exception e) {
			throw new ManageException("根据账号查询用户信息出错", e);
		}
		if (hrUserList != null && hrUserList.size() > 0) {
			isExist = true;
		}
		return isExist;
	}

	/***************************************************************************
	 * 添加用户信息
	 * 
	 * @param hrUser
	 * @throws ManageException
	 */
	public void saveHrUser(HrUser hrUser) throws ManageException {
		try {
			hrUser.setUpdateTime(new Date());
			hrUser.setCreateTime(new Date());
			hrUser.setRecordStatus(1);
			hrUser.setIsOnline(0);
			hrUser.setPassword(MD5Util
					.generatePassword(SystemConst.originalPassword));
			DESUtil desPlus = new DESUtil();
			hrUser.setOriginalPassword(desPlus
					.encrypt(SystemConst.originalPassword));
			this.hrUserDAO.save(hrUser);
		} catch (Exception e) {
			throw new ManageException("添加用户信息出错", e);
		}
	}

	/***************************************************************************
	 * 修改用户信息
	 * 
	 * @param hrUser
	 * @throws ManageException
	 */
	public void updateHrUser(HrUser hrUser) throws ManageException {
		HrUser updateHrUser;
		try {
			updateHrUser = (HrUser) this.hrUserDAO.load(HrUser.class, hrUser
					.getId());
			updateHrUser.setAccount(hrUser.getAccount());
			updateHrUser.setName(hrUser.getName());
			updateHrUser.setEmail(hrUser.getEmail());
			updateHrUser.setIsEnable(hrUser.getIsEnable());
			updateHrUser.setHrUserType(hrUser.getHrUserType());
			updateHrUser.setUpdateTime(new Date());
			updateHrUser.setRecordStatus(1);
			this.hrUserDAO.update(updateHrUser);
		} catch (Exception e) {
			throw new ManageException("修改用户信息出错", e);
		}
	}

	/***************************************************************************
	 * 批量删除用户信息（逻辑删除）
	 * 
	 * @param ids
	 * @throws ManageException
	 */
	public void deleteHrUsers(String ids) throws ManageException {
		try {
			this.hrUserDAO.deleteHrUsers(ids);
		} catch (Exception e) {
			throw new ManageException("删除用户信息出错", e);
		}
	}

	/***************************************************************************
	 * 重置用户密码
	 * 
	 * @param ids
	 * @throws ManageException
	 */
	public void updateResetPassword(String ids) throws ManageException {
		try {
			this.hrUserDAO.updateResetPassWord(ids);
		} catch (Exception e) {
			throw new ManageException("重置用户密码出错", e);
		}
	}

	/***************************************************************************
	 * 查询出导出的用户信息
	 * 
	 * @return
	 * @throws ManageException
	 */
	public List<HrUser> findExportUsers() throws ManageException {
		try {
			return this.hrUserDAO.findExportUsers();
		} catch (Exception e) {
			throw new ManageException("导出用户信息出错", e);
		}
	}

	/***************************************************************************
	 * 导入用户信息，新增和修改用户信息
	 * 
	 * @param insertListMap
	 * @param updateListMap
	 * @throws ManageException
	 */
	public void saveOrUpdateBatchHrUsers(
			List<Map<String, String>> insertListMap,
			List<Map<String, String>> updateListMap) throws ManageException {
		for (Map<String, String> map : insertListMap) {
			map.put("PASSWORD", MD5Util.generatePassword("888888"));
			map.put("ORIGINAL_PASSWORD", MD5Util.generatePassword("888888"));
			map.put("IS_ONLINE", "0");
			map.put("RECORD_STATUS", "1");
			map.put("UPDATE_TIME", DateUtil.getInstance().getSystemTimeStr());
		}

		try {
			this.hrUserDAO.saveBatchHrUserList(insertListMap);
		} catch (Exception e) {
			throw new ManageException("导入新的用户信息出错", e);
		}

		for (Map<String, String> map2 : updateListMap) {
			map2.put("UPDATE_TIME", DateUtil.getInstance().getSystemTimeStr());
		}

		try {
			this.hrUserDAO.updateBatchHrUserList(updateListMap);
		} catch (Exception e) {
			throw new ManageException("导入修改用户信息出错", e);
		}
	}

	/***************************************************************************
	 * 查询出不属于该用户的所有角色
	 * 
	 * @param hrUser
	 * @return
	 * @throws ManageException
	 */
	public List<SysRole> findUserNoSysRole(HrUser hrUser)
			throws ManageException {
		try {
			return this.hrUserDAO.findUserNoSysRole(hrUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManageException("查询用户角色列表出错",e);
		}
	}

	/***************************************************************************
	 * 查询出用户所拥有的所有角色
	 * 
	 * @param hrUser
	 * @return
	 * @throws ManageException
	 */
	public List<SysRole> findUserSysRole(HrUser hrUser) throws ManageException {
		try {
			return this.hrUserDAO.findUserSysRole(hrUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManageException("查询用户角色列表出错",e);
		}
	}

	/***************************************************************************
	 * 保存人员角色信息
	 * 
	 * @param hrUser
	 * @param selectSysRole
	 * @throws ManageException
	 */
	public void saveUserSysRole(HrUser hrUser, List<SysRole> selectSysRole)
			throws ManageException {
		try {
			this.hrUserDAO.deleteUserSysRole(hrUser.getId());
			if (selectSysRole != null && selectSysRole.size() > 0) {
				List<HrUserRole> hrUserRoles = new ArrayList<HrUserRole>();
				HrUserRole tmpHrUserRole;
				for (SysRole tmpSysRole : selectSysRole) {
					tmpHrUserRole = new HrUserRole();
					tmpHrUserRole.setHrUser(hrUser);
					tmpHrUserRole.setSysRole(tmpSysRole);
					tmpHrUserRole.setRecordStatus(1);
					hrUserRoles.add(tmpHrUserRole);
				}
				this.hrUserDAO.saveUserSysRole(hrUserRoles);
			}
		} catch (Exception e) {
			throw new ManageException("保存人员角色信息出错",e);
		}
	}
}
