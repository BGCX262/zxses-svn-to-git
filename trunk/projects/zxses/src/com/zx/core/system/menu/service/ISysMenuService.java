package com.zx.core.system.menu.service;   

import java.util.List;

import com.zx.core.base.service.IBaseService;
import com.zx.core.exception.ManageException;
import com.zx.core.model.SysMenu;
  
public interface ISysMenuService extends IBaseService {
    
	/**
	 * 查询出所有的菜单
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> findSysMenus() throws ManageException;
	/**
	 * 判断是否存在相同的Code
	 * @param sysMenu
	 * @return
	 * @throws ManageException
	 */
	public boolean findSysMenubyCode(SysMenu sysMenu) throws ManageException ;
	/**
	 * 新增菜单
	 * @param hrDept
	 * @throws ManageException
	 */
	public void saveSysMenu(SysMenu sysMenu) throws ManageException ;
	/**
	 * 根据Id查询出菜单信息
	 * @param sysMenuId
	 * @return
	 * @throws ManageException
	 */
	public SysMenu findSysMenuById(Long sysMenuId) throws ManageException;
	/**
	 * 修改菜单的导航字段
	 * @updateSysMenuLevel
	 * @Description: 
	 * @void
	 * @Author: zhoupk
	 * @Time: Dec 19, 2011
	 */
	public void updateSysMenuLevel(SysMenu sysMenu) throws ManageException;
	/**
	 * 修改部门信息
	 * @param sysMenu
	 * @throws ManageException
	 */
	public void updateSysMenu(SysMenu sysMenu) throws ManageException ;
	/**
	 * 批量删除系统信息（逻辑删除）
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteSysMenus(String id) throws ManageException ;
	/**
	 * 拖拽部门树
	 * 
	 * @param dragType
	 * @param sourceNodeId
	 * @param targetNodeId
	 * @throws ManageException
	 */
	public void updateDragSysMenus(String dragType, Long sourceNodeId,
			Long targetNodeId) throws ManageException;
	
	
}
