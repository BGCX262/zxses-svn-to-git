package com.zx.core.system.menu.dao;   

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.zx.core.base.dao.BaseDAO;
import com.zx.core.model.SysMenu;
  
public class SysMenuDAO extends BaseDAO<SysMenu> implements ISysMenuDAO {

    /**
	 * 查询出所有的菜单信息
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> findSysMenus() throws Exception {
		return this.find("from SysMenu where recordStatus=1 order by recordOrder");
	}
	
	/**
	 * 根据菜单编号查询出菜单信息
	 * @param sysMenuCode
	 * @param sysMenuId
	 * @return
	 * @throws Exception
	 */
	public List<SysMenu> findSysMenuByCode(String sysMenuCode, Long sysMenuId)throws Exception { 
		String hql = "from SysMenu where recordStatus=1 and code='"
				+ sysMenuCode.trim() + "' and id!=" + sysMenuId;
		return this.find(hql);
	}
	/**
	 * 批量删除菜单信息（逻辑删除）
	 * @param id
	 * @throws Exception
	 */
	public void deleteSysMenus(String id) throws Exception {
		String hql = "update SysMenu sm set sm.recordStatus=0 where sm.id=" + id+" or sm.sysMenu.id="+id;
		this.getSession().createQuery(hql).executeUpdate();
	}
	
	/**
	 * 将源树拖拽到目标树的前面
	 * @param parentId
	 * @param order
	 * @throws Exception
	 */
	public void updateDragPrevSysMenu(Long parentId, Integer order)	throws Exception {
		String hql = "update SysMenu sm set sm.recordOrder=sm.recordOrder-1 where";
		if (parentId == null) {
			hql += " sm.sysMenu is null";
		} else {
			hql += " sm.sysMenu.id=" + parentId;
		}
		hql += " and sm.recordOrder<" + order;
		this.getSession().createQuery(hql).executeUpdate();
	}

	/**
	 * 将源树拖拽到目标树的后面
	 * @param parentId
	 * @param order
	 * @throws Exception
	 */
	public void updateDragNextSysMenu(Long parentId, Integer order)
			throws Exception {
		String hql = "update SysMenu sm set sm.recordOrder=sm.recordOrder+1 where";
		if (parentId == null) {
			hql += " sm.sysMenu is null";
		} else {
			hql += " sm.sysMenu.id=" + parentId;
		}
		hql += " and sm.recordOrder>" + order;
		this.getSession().createQuery(hql).executeUpdate();
	}

	/**
	 * 拖拽后,需要修改所有子类的level属性
	 * @param sourceLevel:源节点,也就是需要修改的level信息
	 * @param targetLevel:目标节点,也就是修改为目标的level信息
	 * @param sourceId:源节点的Id
	 */
	public void updateDragLevelSysMenu(String sourceLevel, String targetLevel,
			Long sourceId) {
		String hql = "";
		// 当为空时,则源节点为根节点,直接将目标节点的level替换成跟sourceId相等
		if (StringUtils.isEmpty(targetLevel)) {
			hql = "update SysMenu sm  set hd.level=replace(sm.level,'"
					+ sourceLevel + "','," + sourceId
					+ ",') where sm.level like '" + sourceLevel + "%'";
		} else {
			hql = "update SysMenu sm set sm.level=replace(sm.level,'"
					+ sourceLevel + "','" + targetLevel+"_" + sourceId
					+ "') where sm.level like '" + sourceLevel + "%'";
		}
		this.getSession().createQuery(hql).executeUpdate();
	}
	
	/**
	 * 查询出当前父节点下的所有节点最大的排序号
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public Integer findSysMenuMaxOrder(Long parentId) throws Exception {
		String hql = "select max(recordOrder) from SysMenu where";
		if (parentId == null) {
			hql += " sysMenu is null";
		} else {
			hql += " sysMenu.id=" + parentId;
		}
		if (this.findUniqueResult(hql) == null) { 
			return 1;
		}
		return (Integer) this.findUniqueResult(hql);
	}
	
}
 