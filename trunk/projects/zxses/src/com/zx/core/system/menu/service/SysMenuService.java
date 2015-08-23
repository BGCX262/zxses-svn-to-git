package com.zx.core.system.menu.service;   

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.zx.core.base.service.BaseService;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrDept;
import com.zx.core.model.SysMenu;
import com.zx.core.system.menu.dao.ISysMenuDAO;
import com.zx.core.system.user.service.HrUserService;
import com.zx.core.util.SysLoggerUtil;
  
public class SysMenuService extends BaseService implements ISysMenuService{
    
    private ISysMenuDAO sysMenuDAO;
    
    /**
	 * 查询出所有的菜单
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> findSysMenus() throws ManageException {
		try {
			return this.sysMenuDAO.findSysMenus();
		} catch (Exception e) {
			throw new ManageException("查询菜单出错");
		}
	}
	/**
	 * 判断是否存在相同的Code
	 * @param sysMenu
	 * @return
	 * @throws ManageException
	 */
	public boolean findSysMenubyCode(SysMenu sysMenu) throws ManageException {
		boolean isExist = false;
		List<SysMenu> sysMenuList = null;
		try {
			sysMenuList = this.sysMenuDAO.findSysMenuByCode(sysMenu.getCode(), sysMenu.getId());
		} catch (Exception e) {
			throw new ManageException("根据Code查询菜单信息出错");
		}
		if (sysMenuList != null && sysMenuList.size() > 0) {
			isExist = true;
		}
		return isExist;
	}
	
	/**
	 * 根据Id查询出菜单信息
	 * @param sysMenuId
	 * @return
	 * @throws ManageException
	 */
	public SysMenu findSysMenuById(Long sysMenuId) throws ManageException {
		try {
			return (SysMenu) this.sysMenuDAO.load(SysMenu.class, sysMenuId);
		} catch (Exception e) {
			throw new ManageException("根据ID查询菜单信息出错");
		}
	}

	
	
	/**
	 * 新增菜单
	 * @param sysMenu
	 * @throws ManageException
	 */
	public void saveSysMenu(SysMenu sysMenu) throws ManageException {
		sysMenu.setCreateTime(new Date());
		sysMenu.setUpdateTime(new Date());
		
		sysMenu.setRecordStatus(1);
		sysMenu.setIsEnable(1);
		
		try {
			if(sysMenu.getSysMenu().getId()!=null){
				SysMenu sysMenutemp = (SysMenu)sysMenuDAO.load(SysMenu.class, sysMenu.getSysMenu().getId());
				sysMenu.setLevel(sysMenutemp.getLevel());
			}else{
				sysMenu.setSysMenu(null);
				sysMenu.setLevel("1");
			}
			this.sysMenuDAO.save(sysMenu);
			if(sysMenu.getSysMenu()!=null){
				sysMenu.setLevel(sysMenu.getLevel()+"_"+sysMenu.getId());
				this.sysMenuDAO.update(sysMenu);
			}else{ 
				sysMenu.setLevel(sysMenu.getId().toString());
				this.sysMenuDAO.update(sysMenu);
			}
		} catch (Exception e) {
			throw new ManageException("保存菜单出错");
		}
	}
	
	/**
	 * 修改部门信息
	 * @param sysMenu
	 * @throws ManageException
	 */
	public void updateSysMenu(SysMenu sysMenu) throws ManageException {
		try {
			SysMenu updateSysMenu = (SysMenu) this.sysMenuDAO.load(SysMenu.class,sysMenu.getId());
			updateSysMenu.setCode(sysMenu.getCode());
			updateSysMenu.setName(sysMenu.getName());
			updateSysMenu.setUrl(sysMenu.getUrl());
			updateSysMenu.setIsEnable(sysMenu.getIsEnable());
			updateSysMenu.setRecordOrder(Integer.valueOf(sysMenu.getRecordOrderStr()));
			updateSysMenu.setDescription(sysMenu.getDescription());
			updateSysMenu.setUpdateTime(new Date());
			this.sysMenuDAO.update(updateSysMenu);
		} catch (Exception e) {
			throw new ManageException("修改菜单出错");
		}
	}
	/**
	 * 修改菜单的导航字段
	 * @updateSysMenuLevel
	 * @Description: 
	 * @void
	 * @Author: zhoupk
	 * @Time: Dec 19, 2011
	 */
	public void updateSysMenuLevel(SysMenu sysMenu) throws ManageException {
		try {
			if(sysMenu.getSysMenu().getId()!=null){
				sysMenu.setLevel(sysMenu.getLevel()+"_"+sysMenu.getId());
				this.sysMenuDAO.update(sysMenu);
			}else{ 
				sysMenu.setLevel(sysMenu.getId().toString());
				this.sysMenuDAO.update(sysMenu);
			}
		} catch (Exception e) {
			throw new ManageException("保存菜单出错");
		}
	}
	
	/**
	 * 批量删除系统信息（逻辑删除）
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteSysMenus(String id) throws ManageException {
		try {
			this.sysMenuDAO.deleteSysMenus(id);
		} catch (Exception e) {
			throw new ManageException("删除菜单出错");
		}
	}

	/**
	 * 拖拽部门树
	 * 
	 * @param dragType
	 * @param sourceNodeId
	 * @param targetNodeId
	 * @throws ManageException
	 */
	public void updateDragSysMenus(String dragType, Long sourceNodeId,
			Long targetNodeId) throws ManageException {
		try {
			SysMenu sourceNode = (SysMenu) this.sysMenuDAO.load(SysMenu.class,
					sourceNodeId);
			SysMenu targetNode = (SysMenu) this.sysMenuDAO.load(SysMenu.class,
					targetNodeId);
			Long targetNodeParentId = null;
			// 拖拽前的源节点
			Long dragOldSourceParentId = null;
			if (targetNode.getSysMenu() != null
					&& targetNode.getSysMenu().getId() != null) {
				targetNodeParentId = targetNode.getSysMenu().getId();
			}
			if (sourceNode.getSysMenu() != null
					&& sourceNode.getSysMenu().getId() != null) {
				dragOldSourceParentId = sourceNode.getSysMenu().getId();
			}

			// 需要替换的level字符串
			String sourceLevel = sourceNode.getLevel();
			String targetLevel = "";

			if ("inner".equals(dragType)) {
				sourceNode.setSysMenu(targetNode);
				Integer deptOrder = this.sysMenuDAO
						.findSysMenuMaxOrder(targetNode.getSysMenu() == null ? null
								: targetNode.getSysMenu().getId()) + 1;
				sourceNode.setRecordOrder(deptOrder);
				targetLevel = targetNode.getLevel();
			} else if ("prev".equals(dragType)) {
				sourceNode.setSysMenu(targetNode.getSysMenu());
				this.sysMenuDAO.updateDragPrevSysMenu(targetNodeParentId,
						targetNode.getRecordOrder());
				sourceNode.setRecordOrder(targetNode.getRecordOrder() - 1);
				if (targetNode.getSysMenu() != null) {
					targetLevel = targetNode.getSysMenu().getLevel();
				}
			} else if ("next".equals(dragType)) {
				sourceNode.setSysMenu(targetNode.getSysMenu());
				this.sysMenuDAO.updateDragNextSysMenu(targetNodeParentId,
						targetNode.getRecordOrder());
				sourceNode.setRecordOrder(targetNode.getRecordOrder() + 1);
				if (targetNode.getSysMenu() != null) {
					targetLevel = targetNode.getSysMenu().getLevel();
				}
			}
			this.sysMenuDAO.update(sourceNode);
			this.sysMenuDAO.updateDragLevelSysMenu(sourceLevel, targetLevel,
					sourceNode.getId());
		} catch (Exception e) {
			throw new ManageException("移动菜单出错");
		}
	}

	public ISysMenuDAO getSysMenuDAO() {
		return sysMenuDAO;
	}

	public void setSysMenuDAO(ISysMenuDAO sysMenuDAO) {
		this.sysMenuDAO = sysMenuDAO;
	}

}
