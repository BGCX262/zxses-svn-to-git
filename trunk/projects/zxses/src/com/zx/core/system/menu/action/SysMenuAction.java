package com.zx.core.system.menu.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.zx.core.base.action.BaseAction;
import com.zx.core.exception.ManageException;
import com.zx.core.model.SysMenu;
import com.zx.core.system.menu.service.ISysMenuService;
import com.zx.core.util.StringUtil;

public class SysMenuAction extends BaseAction {

	private static final long serialVersionUID = 538896470908883792L;

	private ISysMenuService sysMenuService;

	private List<SysMenu> menuList;

	private SysMenu sysMenu;
	
	private SysMenu selectSysMenu;
	
	private String isEnable;
	
	// 新增成功之后,js用于添加父节点还是当前节点下面
	private String addType;
	// 拖拽
	private String dragType;// 拖拽类型
	private Long sourceNodeId;// 源节点
	private Long targetNodeId;// 目标节点
	
	public String getDragType() {
		return dragType;
	}

	public void setDragType(String dragType) {
		this.dragType = dragType;
	}

	public Long getSourceNodeId() {
		return sourceNodeId;
	}

	public void setSourceNodeId(Long sourceNodeId) {
		this.sourceNodeId = sourceNodeId;
	}

	public Long getTargetNodeId() {
		return targetNodeId;
	}

	public void setTargetNodeId(Long targetNodeId) {
		this.targetNodeId = targetNodeId;
	}

	public ISysMenuService getSysMenuService() {
		return sysMenuService;
	}

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	/**
	 * @main  菜单main 方法
	 * @Description: 该方法跳转到菜单的main页面，main页面包含菜单树形页面和主页面
	 * @Author: zhoupk
	 * @Time: Dec 16, 2011
	 */
	@SkipValidation
	public String main() {
		try {
			menuList = this.sysMenuService.findSysMenus();
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.failure");
		}
		return "main";
	}

	/**
	 * @menuRight 菜单右边 方法
	 * @Description: 跳转右边的页面
	 * @Author: zhoupk
	 * @Time: Dec 16, 2011
	 */
	@SkipValidation
	public String menuRight() {
		return "entryAdd";
	}
	
	
	
	/**
	 * 新增菜单信息
	 * 
	 * @return
	 */
	public String saveSysMenu() {
		try {
			boolean isExist = this.sysMenuService.findSysMenubyCode(sysMenu);
			
			if(!StringUtil.getInstance().isNum(sysMenu.getRecordOrderStr())){
				this.addFieldError("sysMenu.recordOrderStr", this.getText("recordOrder.isnum"));
				return "entryAdd";
			}
			if (isExist) {
				this.addFieldError("sysMenu.code", this.getText("code.isExist"));
				return "entryAdd";
			}
			this.sysMenu.setRecordOrder(Integer.valueOf(sysMenu.getRecordOrderStr()));
			this.sysMenuService.saveSysMenu(sysMenu);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionError("save.failure");
		}
		this.addMessageInfo("save.success");
		// 如果此值不为空则说明新增成功!
		setSelectSysMenu(sysMenu);
		setSysMenu(null);
		return "entryAdd";
	}
	
	/**
	 * 转跳到修改页面
	 * @return
	 */
	@SkipValidation
	public String entryUpdate() {
		try {
			sysMenu = this.sysMenuService.findSysMenuById(sysMenu.getId());
			sysMenu.setRecordOrderStr(sysMenu.getRecordOrder().toString());
			if("1".equals(sysMenu.getIsEnable().toString())){
				this.setIsEnable("true");
			}else{
				this.setIsEnable("false");
			}
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionError("find.failure");
		}
		return "entryUpdate";
	}

	
	/**
	 * 修改菜单信息
	 * @return
	 */
	public String updateSysMenu() {
		try {
			boolean isExist = this.sysMenuService.findSysMenubyCode(sysMenu);
			if (isExist) {
				this.addFieldError("sysMenu.code", this.getText("code.isExist"));
				return "entryUpdate";
			}
			
			if(!StringUtil.getInstance().isNum(sysMenu.getRecordOrderStr())){
				this.addFieldError("sysMenu.recordOrderStr", this.getText("recordOrder.isnum"));
				return "entryUpdate";
			}
			
			if("true".equals(isEnable)){
				sysMenu.setIsEnable(1);
			}else{
				sysMenu.setIsEnable(0);
			}
			
			this.sysMenuService.updateSysMenu(sysMenu);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionError("update.failure");
			return "entryUpdate";
		}
		addMessageInfo("update.success");
		// 如果此值不为空则说明修改成功!
		setSelectSysMenu(selectSysMenu);
		return "entryUpdate";
	}
	
	/***************************************************************************
	 * 删除菜单信息
	 * 
	 * @return
	 */
	@SkipValidation
	public String deleteSysMenu() {
		try {
			this.sysMenuService.deleteSysMenus(checkIds);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			try {
				getResponse().getWriter().write("fail");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			getResponse().getWriter().write("success");
		} catch (IOException e) {
		}
		return null;
	}
	
	/***************************************************************************
	 * 拖拽部门信息
	 * 
	 * @return
	 */
	@SkipValidation
	public String updateDragHrDept() {
		try {
			this.sysMenuService.updateDragSysMenus(dragType, sourceNodeId,targetNodeId);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			try {
				getResponse().getWriter().write("fail");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			getResponse().getWriter().write("success");
		} catch (IOException e) {
		}
		return null;
	}
	
	
	public List<SysMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}

	public void setSysMenuService(ISysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}

	public SysMenu getSelectSysMenu() {
		return selectSysMenu;
	}

	public void setSelectSysMenu(SysMenu selectSysMenu) {
		this.selectSysMenu = selectSysMenu;
	}

	 

}
