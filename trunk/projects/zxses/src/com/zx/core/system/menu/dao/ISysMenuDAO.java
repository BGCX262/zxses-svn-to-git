package com.zx.core.system.menu.dao;   

import java.util.List;

import com.zx.core.base.dao.IBaseDAO;
import com.zx.core.model.SysMenu;
  
public interface ISysMenuDAO extends IBaseDAO<SysMenu> {

	/**
	 * 查询出所有的菜单信息
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> findSysMenus() throws Exception ;
	/**
	 * 根据菜单编号查询出菜单信息
	 * @param sysMenuCode
	 * @param sysMenuId
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> findSysMenuByCode(String sysMenuCode, Long sysMenuId)throws Exception ;
	/**
	 * 批量删除菜单信息（逻辑删除）
	 * @param id
	 * @throws Exception
	 */
	public void deleteSysMenus(String id) throws Exception ;
	/**
	 * 将源树拖拽到目标树的前面
	 * @param parentId
	 * @param order
	 * @throws Exception
	 */
	public void updateDragPrevSysMenu(Long parentId, Integer order)throws Exception ;
	/**
	 * 将源树拖拽到目标树的后面
	 * @param parentId
	 * @param order
	 * @throws Exception
	 */
	public void updateDragNextSysMenu(Long parentId, Integer order)
			throws Exception;
	/**
	 * 拖拽后,需要修改所有子类的level属性
	 * @param sourceLevel:源节点,也就是需要修改的level信息
	 * @param targetLevel:目标节点,也就是修改为目标的level信息
	 * @param sourceId:源节点的Id
	 */
	public void updateDragLevelSysMenu(String sourceLevel, String targetLevel,
			Long sourceId) ;
	/**
	 * 查询出当前父节点下的所有节点最大的排序号
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public Integer findSysMenuMaxOrder(Long parentId) throws Exception;
	
	
	
}
