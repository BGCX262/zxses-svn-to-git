package com.zx.core.system.dept.service;

import java.util.List;

import com.zx.core.exception.ManageException;
import com.zx.core.model.HrDept;

public interface IHrDeptService {

	/***************************************************************************
	 * 查询出所有的部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrDept> findHrDepts() throws ManageException;

	/***************************************************************************
	 * 根据Id查询出部门信息
	 * 
	 * @param deptId
	 * @return
	 * @throws ManageException
	 */
	public HrDept findHrDept(Long deptId) throws ManageException;

	/***************************************************************************
	 * 判断是否存在相同的Code
	 * 
	 * @param hrDept
	 * @return
	 * @throws ManageException
	 */
	public boolean isExistHrDeptCode(HrDept hrDept) throws ManageException;

	/***************************************************************************
	 * 新增部门信息
	 * 
	 * @param hrDept
	 * @throws ManageException
	 */
	public void saveHrDept(HrDept hrDept) throws ManageException;

	/***************************************************************************
	 * 修改部门信息
	 * 
	 * @param hrDept
	 * @throws ManageException
	 */
	public void updateHrDept(HrDept hrDept) throws ManageException;

	/***************************************************************************
	 * 批量删除系统信息（逻辑删除）
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteHrDepts(String id) throws ManageException;

	/***************************************************************************
	 * 拖拽部门树
	 * 
	 * @param dragType
	 * @param sourceNodeId
	 * @param targetNodeId
	 * @throws ManageException
	 */
	public void updateDragHrDepts(String dragType, Long sourceNodeId,
			Long targetNodeId) throws ManageException;

}
