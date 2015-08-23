package com.zx.core.system.dept.dao;

import java.util.List;

import com.zx.core.base.dao.IBaseDAO;
import com.zx.core.model.HrDept;

public interface IHrDeptDAO extends IBaseDAO<HrDept> {

	/**
	 * 查询出所有的部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrDept> findHrDepts() throws Exception;

	/***************************************************************************
	 * 查询出当前父节点下的所有节点最大的排序号
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public Integer findHrDeptMaxOrder(Long parentId) throws Exception;

	/***************************************************************************
	 * 根据角色编号查询出角色信息
	 * 
	 * @param hrDeptCode
	 * @param hrDeptId
	 * @return
	 * @throws Exception
	 */
	public List<HrDept> findHrDeptByCode(String hrDeptCode, Long hrDeptId)
			throws Exception;

	/***************************************************************************
	 * 批量删除系统信息（逻辑删除）
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteHrDepts(String id) throws Exception;

	/***************************************************************************
	 * 将源树拖拽到目标树的前面
	 * 
	 * @param parentId
	 * @param order
	 * @throws Exception
	 */
	public void updateDragPrevHrDept(Long parentId, Integer order)
			throws Exception;

	/***************************************************************************
	 * 将源树拖拽到目标树的后面
	 * 
	 * @param parentId
	 * @param order
	 * @throws Exception
	 */
	public void updateDragNextHrDept(Long parentId, Integer order)
			throws Exception;

	/***************************************************************************
	 * 拖拽后,需要修改所有子类的level属性
	 * 
	 * @param sourceLevel:源节点,也就是需要修改的level信息
	 * @param targetLevel:目标节点,也就是修改为目标的level信息
	 * @param sourceId:源节点的Id
	 */
	public void updateDragLevelDept(String sourceLevel, String targetLevel,
			Long sourceId);

}
