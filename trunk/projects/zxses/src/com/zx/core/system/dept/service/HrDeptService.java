package com.zx.core.system.dept.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.zx.core.exception.ManageException;
import com.zx.core.model.HrDept;
import com.zx.core.system.dept.dao.IHrDeptDAO;

/*******************************************************************************
 * 部门管理
 * 
 * @author maolujun
 * 
 */
public class HrDeptService implements IHrDeptService {

	private IHrDeptDAO hrDeptDAO;

	public IHrDeptDAO getHrDeptDAO() {
		return hrDeptDAO;
	}

	public void setHrDeptDAO(IHrDeptDAO hrDeptDAO) {
		this.hrDeptDAO = hrDeptDAO;
	}

	/***************************************************************************
	 * 查询出所有的部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrDept> findHrDepts() throws ManageException {
		try {
			return this.hrDeptDAO.findHrDepts();
		} catch (Exception e) {
			throw new ManageException("查询部门信息失败！");
		}
	}

	/***************************************************************************
	 * 根据Id查询出部门信息
	 * 
	 * @param deptId
	 * @return
	 * @throws ManageException
	 */
	public HrDept findHrDept(Long deptId) throws ManageException {
		try {
			return (HrDept) this.hrDeptDAO.load(HrDept.class, deptId);
		} catch (Exception e) {
			throw new ManageException("查询部门信息出错", e);
		}
	}

	/***************************************************************************
	 * 判断是否存在相同的Code
	 * 
	 * @param hrDept
	 * @return
	 * @throws ManageException
	 */
	public boolean isExistHrDeptCode(HrDept hrDept) throws ManageException {
		boolean isExist = false;
		List<HrDept> hrDeptList = null;
		try {
			hrDeptList = this.hrDeptDAO.findHrDeptByCode(hrDept.getCode(),
					hrDept.getId());
		} catch (Exception e) {
			throw new ManageException("根据Code查询部门信息出错", e);
		}
		if (hrDeptList != null && hrDeptList.size() > 0) {
			isExist = true;
		}
		return isExist;
	}

	/***************************************************************************
	 * 新增部门信息
	 * 
	 * @param hrDept
	 * @throws ManageException
	 */
	public void saveHrDept(HrDept hrDept) throws ManageException {
		hrDept.setCreateTime(new Date());
		hrDept.setUpdateTime(new Date());
		HrDept parentHrDept = null;
		Integer deptOrder = 1;
		String deptLevel = "";
		try {
			deptOrder = this.hrDeptDAO.findHrDeptMaxOrder(hrDept.getHrDept()
					.getId()) + 1;
			if (hrDept.getHrDept().getId() != null) {
				parentHrDept = (HrDept) this.hrDeptDAO.load(HrDept.class,
						hrDept.getHrDept().getId());
				deptLevel = parentHrDept.getLevel();
			} else {
				hrDept.setHrDept(null);
			}
		} catch (Exception e) {
			throw new ManageException("查询部门信息错误");
		}
		hrDept.setRecordOrder(deptOrder);
		hrDept.setRecordStatus(1);
		try {
			this.hrDeptDAO.save(hrDept);
		} catch (Exception e) {
			throw new ManageException("保存部门信息失败");
		}
		// 当当前节点是父节点时,则当前Id作为level的值,否则level的值为"父节点的level,当前节点的Id"
		if (StringUtils.isEmpty(deptLevel)) {
			hrDept.setLevel("," + hrDept.getId().toString() + ",");
		} else {
			hrDept.setLevel(deptLevel + hrDept.getId().toString() + ",");
		}
	}

	/***************************************************************************
	 * 修改部门信息
	 * 
	 * @param hrDept
	 * @throws ManageException
	 */
	public void updateHrDept(HrDept hrDept) throws ManageException {
		try {
			HrDept updateHrDept = (HrDept) this.hrDeptDAO.load(HrDept.class,
					hrDept.getId());
			updateHrDept.setCode(hrDept.getCode());
			updateHrDept.setName(hrDept.getName());
			updateHrDept.setComment(hrDept.getComment());
			updateHrDept.setUpdateTime(new Date());
			this.hrDeptDAO.update(updateHrDept);
		} catch (Exception e) {
			throw new ManageException("修改部门信息失败");
		}
	}

	/***************************************************************************
	 * 批量删除系统信息（逻辑删除）
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteHrDepts(String id) throws ManageException {
		try {
			this.hrDeptDAO.deleteHrDepts(id);
		} catch (Exception e) {
			throw new ManageException("删除部门信息失败");
		}
	}

	/***************************************************************************
	 * 拖拽部门树
	 * 
	 * @param dragType
	 * @param sourceNodeId
	 * @param targetNodeId
	 * @throws ManageException
	 */
	public void updateDragHrDepts(String dragType, Long sourceNodeId,
			Long targetNodeId) throws ManageException {
		try {
			HrDept sourceNode = (HrDept) this.hrDeptDAO.load(HrDept.class,
					sourceNodeId);
			HrDept targetNode = (HrDept) this.hrDeptDAO.load(HrDept.class,
					targetNodeId);
			Long targetNodeParentId = null;
			// 拖拽前的源节点
			Long dragOldSourceParentId = null;
			if (targetNode.getHrDept() != null
					&& targetNode.getHrDept().getId() != null) {
				targetNodeParentId = targetNode.getHrDept().getId();
			}
			if (sourceNode.getHrDept() != null
					&& sourceNode.getHrDept().getId() != null) {
				dragOldSourceParentId = sourceNode.getHrDept().getId();
			}

			// 需要替换的level字符串
			String sourceLevel = sourceNode.getLevel();
			String targetLevel = "";

			if ("inner".equals(dragType)) {
				sourceNode.setHrDept(targetNode);
				Integer deptOrder = this.hrDeptDAO
						.findHrDeptMaxOrder(targetNode.getHrDept() == null ? null
								: targetNode.getHrDept().getId()) + 1;
				sourceNode.setRecordOrder(deptOrder);
				targetLevel = targetNode.getLevel();
			} else if ("prev".equals(dragType)) {
				sourceNode.setHrDept(targetNode.getHrDept());
				this.hrDeptDAO.updateDragPrevHrDept(targetNodeParentId,
						targetNode.getRecordOrder());
				sourceNode.setRecordOrder(targetNode.getRecordOrder() - 1);
				if (targetNode.getHrDept() != null) {
					targetLevel = targetNode.getHrDept().getLevel();
				}
			} else if ("next".equals(dragType)) {
				sourceNode.setHrDept(targetNode.getHrDept());
				this.hrDeptDAO.updateDragNextHrDept(targetNodeParentId,
						targetNode.getRecordOrder());
				sourceNode.setRecordOrder(targetNode.getRecordOrder() + 1);
				if (targetNode.getHrDept() != null) {
					targetLevel = targetNode.getHrDept().getLevel();
				}
			}
			this.hrDeptDAO.update(sourceNode);
			this.hrDeptDAO.updateDragLevelDept(sourceLevel, targetLevel,
					sourceNode.getId());
		} catch (Exception e) {
			throw new ManageException("移动部门信息失败");
		}
	}
}
