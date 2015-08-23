package com.zx.core.system.role.action;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.zx.core.base.action.BaseAction;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserRole;
import com.zx.core.model.HrUserType;
import com.zx.core.model.SysMenu;
import com.zx.core.model.SysRole;
import com.zx.core.system.menu.service.ISysMenuService;
import com.zx.core.system.role.service.ISysRoleService;
import com.zx.core.system.user.service.IHrUserService;

public class SysRoleAction extends BaseAction {

	private static final long serialVersionUID = -8350569792282662155L;
	private ISysRoleService sysRoleService;

	private ISysMenuService sysMenuService;

	private IHrUserService hrUserService;

	private SysRole sysRole;
	private List<SysRole> sysRoles;

	private String checkIds;

	private List<SysMenu> menuList;

	private HrUserType hrUserType;

	private List<HrUserType> hrUserTypes;

	private HrUser hrUserOp;

	private List<HrUser> hrUsers;

	private List<HrUserRole> hrUserRoles;

	private String roleId;

	// 角色权限菜单
	private String checkNodeIds;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<HrUserType> getHrUserTypes() {
		return hrUserTypes;
	}

	public void setHrUserTypes(List<HrUserType> hrUserTypes) {
		this.hrUserTypes = hrUserTypes;
	}

	public List<SysMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}

	public String getCheckIds() {
		return checkIds;
	}

	public void setCheckIds(String checkIds) {
		this.checkIds = checkIds;
	}

	public ISysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public List<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public String getCheckNodeIds() {
		return checkNodeIds;
	}

	public void setCheckNodeIds(String checkNodeIds) {
		this.checkNodeIds = checkNodeIds;
	}

	/***************************************************************************
	 * 显示角色信息的列表页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String listSysRole() {
		try {
			sysRoles = sysRoleService.findSysRoles(getPaginate());
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionError("find.failure");
		}
		return "list";
	}

	/***************************************************************************
	 * 转跳到新增角色页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String entryAdd() {
		return "entryAdd";
	}

	/***************************************************************************
	 * 保存角色信息
	 * 
	 * @return
	 */
	public String saveSysRole() {
		try {
			if (this.sysRoleService.isExistSysRole(sysRole)) {
				this.addFieldError("sysRole.code", this
						.getText("sysRole.code.isExist"));
				return "entryAdd";
			}
			sysRoleService.saveSysRole(sysRole);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionError("save.failure");
		}
		this.addMessageInfo("save.success");
		setSysRole(null);
		return "entryAdd";
	}

	/***************************************************************************
	 * 转跳到修改页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String entryUpdate() {
		try {
			setSysRole(sysRoleService.findSysRole(sysRole.getId()));
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionError("save.failure");
		}
		return "entryUpdate";
	}

	/***************************************************************************
	 * 保存修改之后的角色信息
	 * 
	 * @return
	 */
	public String updateSysRole() {
		try {
			if (this.sysRoleService.isExistSysRole(sysRole)) {
				this.addFieldError("sysRole.code", this
						.getText("sysRole.code.isExist"));
				return "entryUpdate";
			}
			this.sysRoleService.updateSysRole(sysRole);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionError("update.failure");
			return "entryUpdate";
		}
		this.addMessageInfo("update.success");
		return "entryUpdate";
	}

	/***************************************************************************
	 * 删除角色信息
	 * 
	 * @return
	 */
	@SkipValidation
	public String deleteSysRole() {
		try {
			this.sysRoleService.deleteSysRoles(checkIds);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("delete.failure");
			return listSysRole();
		}
		this.addMessageInfo("delete.success");
		return listSysRole();
	}

	/**
	 * @main 角色main 方法
	 * @Description: 该方法跳转到角色的main页面，main页面包含角色树形页面和主页面
	 * @Author: zhoupk
	 * @Time: Dec 16, 2011
	 */
	@SkipValidation
	public String main() {
		try {
			sysRoles = sysRoleService.findSysRoles();
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.failure");
		}
		return "main";
	}

	/**
	 * @main 菜单right 方法
	 * @Description: 该方法跳转到菜单的main页面，main页面包含菜单树形页面和主页面
	 * @Author: zhoupk
	 * @Time: Dec 16, 2011
	 */
	@SkipValidation
	public String right() {
		try {
			List<SysMenu> checkSysMenu = this.sysRoleService
					.findSysMenus(roleId);
			checkNodeIds = ",";
			for (SysMenu tmpSysMenu : checkSysMenu) {
				checkNodeIds += tmpSysMenu.getId() + ",";
			}
			menuList = this.sysMenuService.findSysMenus();
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.failure");
		}
		return "right";
	}

	/***************************************************************************
	 * 保存角色权限菜单
	 * 
	 * @return
	 */
	@SkipValidation
	public String saveUserMenu() {
		try {
			this.sysRoleService.saveUserMenu(roleId, checkNodeIds);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("update.failure");
			return right();
		}
		this.addMessageInfo("update.success");
		return right();
	}

	/***************************************************************************
	 * 显示角色对应的用户信息的列表页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String roleUsers() {
		try {
			this.setRoleId(roleId);
			hrUserRoles = this.sysRoleService.findRoleUsers(roleId);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.failure");
		}
		return "roleUsers";
	}

	/***************************************************************************
	 * 显示为授权的用户信息的列表页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String listUsers() {
		try {
			this.setRoleId(roleId);
			hrUserTypes = this.hrUserService.findHrUserTypes();
			hrUsers = this.sysRoleService.findHrUsers(hrUserType, hrUserOp,
					roleId);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.failure");
		}
		return "listUsers";
	}

	/***************************************************************************
	 * 显示为授权的用户信息的列表页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String saveRoleUsers() {
		try {
			this.sysRoleService.saveRoleUsers(roleId, checkIds);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("save.failure");
			return roleUsers();
		}
		this.addMessageInfo("save.success");
		this.setCheckIds(roleId);
		return roleUsers();
	}

	/***************************************************************************
	 * 删除角色信息
	 * 
	 * @return
	 */
	@SkipValidation
	public String deleteRoleUsers() {
		try {
			this.setRoleId(roleId);
			this.sysRoleService.deleteRoleUsers(checkIds);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("delete.failure");
			return roleUsers();
		}
		this.addMessageInfo("delete.success");
		return roleUsers();
	}

	public ISysMenuService getSysMenuService() {
		return sysMenuService;
	}

	public void setSysMenuService(ISysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}

	public IHrUserService getHrUserService() {
		return hrUserService;
	}

	public void setHrUserService(IHrUserService hrUserService) {
		this.hrUserService = hrUserService;
	}

	public HrUserType getHrUserType() {
		return hrUserType;
	}

	public void setHrUserType(HrUserType hrUserType) {
		this.hrUserType = hrUserType;
	}

	public List<HrUser> getHrUsers() {
		return hrUsers;
	}

	public void setHrUsers(List<HrUser> hrUsers) {
		this.hrUsers = hrUsers;
	}

	public HrUser getHrUserOp() {
		return hrUserOp;
	}

	public void setHrUserOp(HrUser hrUserOp) {
		this.hrUserOp = hrUserOp;
	}

	public List<HrUserRole> getHrUserRoles() {
		return hrUserRoles;
	}

	public void setHrUserRoles(List<HrUserRole> hrUserRoles) {
		this.hrUserRoles = hrUserRoles;
	}

}
