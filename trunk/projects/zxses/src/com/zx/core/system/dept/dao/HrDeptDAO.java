package com.zx.core.system.dept.dao;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.zx.core.base.dao.BaseDAO;
import com.zx.core.model.HrDept;

/*******************************************************************************
 * 部门模块
 * 
 * @author maolujun
 * 
 */
public class HrDeptDAO extends BaseDAO<HrDept> implements IHrDeptDAO {

	/**
	 * 查询出所有的部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrDept> findHrDepts() throws Exception {
		return this
				.find("from HrDept where recordStatus=1 order by recordOrder");
	}

	/***************************************************************************
	 * 查询出当前父节点下的所有节点最大的排序号
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public Integer findHrDeptMaxOrder(Long parentId) throws Exception {

		String hql = "select max(recordOrder) from HrDept where";
		if (parentId == null) {
			hql += " hrDept is null";
		} else {
			hql += " hrDept.id=" + parentId;
		}
		if (this.findUniqueResult(hql) == null) {
			return 1;
		}
		return (Integer) this.findUniqueResult(hql);
	}

	/***************************************************************************
	 * 根据角色编号查询出角色信息
	 * 
	 * @param hrDeptCode
	 * @param hrDeptId
	 * @return
	 * @throws Exception
	 */
	public List<HrDept> findHrDeptByCode(String hrDeptCode, Long hrDeptId)
			throws Exception {
		String hql = "from HrDept where recordStatus=1 and code='"
				+ hrDeptCode.trim() + "' and id!=" + hrDeptId;
		return this.find(hql);
	}

	/***************************************************************************
	 * 批量删除系统信息（逻辑删除）
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteHrDepts(String id) throws Exception {
		String hql = "update HrDept hd set hd.recordStatus=0 where hd.level like '%,"
				+ id + ",%'";
		this.getSession().createQuery(hql).executeUpdate();
	}

	/***************************************************************************
	 * 将源树拖拽到目标树的前面
	 * 
	 * @param parentId
	 * @param order
	 * @throws Exception
	 */
	public void updateDragPrevHrDept(Long parentId, Integer order)
			throws Exception {
		String hql = "update HrDept hd set hd.recordOrder=hd.recordOrder-1 where";
		if (parentId == null) {
			hql += " hd.hrDept is null";
		} else {
			hql += " hd.hrDept.id=" + parentId;
		}
		hql += " and hd.recordOrder<" + order;
		this.getSession().createQuery(hql).executeUpdate();
	}

	/***************************************************************************
	 * 将源树拖拽到目标树的后面
	 * 
	 * @param parentId
	 * @param order
	 * @throws Exception
	 */
	public void updateDragNextHrDept(Long parentId, Integer order)
			throws Exception {
		String hql = "update HrDept hd set hd.recordOrder=hd.recordOrder+1 where";
		if (parentId == null) {
			hql += " hd.hrDept is null";
		} else {
			hql += " hd.hrDept.id=" + parentId;
		}
		hql += " and hd.recordOrder>" + order;
		this.getSession().createQuery(hql).executeUpdate();
	}

	/***************************************************************************
	 * 拖拽后,需要修改所有子类的level属性
	 * 
	 * @param sourceLevel:源节点,也就是需要修改的level信息
	 * @param targetLevel:目标节点,也就是修改为目标的level信息
	 * @param sourceId:源节点的Id
	 */
	public void updateDragLevelDept(String sourceLevel, String targetLevel,
			Long sourceId) {
		String hql = "";
		// 当为空时,则源节点为根节点,直接将目标节点的level替换成跟sourceId相等
		if (StringUtils.isEmpty(targetLevel)) {
			hql = "update HrDept hd set hd.level=replace(hd.level,'"
					+ sourceLevel + "','," + sourceId
					+ ",') where hd.level like '" + sourceLevel + "%'";
		} else {
			hql = "update HrDept hd set hd.level=replace(hd.level,'"
					+ sourceLevel + "','" + targetLevel + sourceId
					+ ",') where hd.level like '" + sourceLevel + "%'";
		}
		this.getSession().createQuery(hql).executeUpdate();
	}
}
