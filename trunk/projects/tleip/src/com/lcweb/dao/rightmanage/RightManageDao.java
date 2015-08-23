package com.lcweb.dao.rightmanage;

import java.util.List;

import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.dao.base.BaseDao;

public interface RightManageDao extends BaseDao {
	public void addRight(String[] funs, String roleId);

	public void addAssignRight(String[] roleIds, String personId);

	public void addBatchAssignUserRight(String[] personIds, String roleId);

	public void deleteFromRole(String roleId, String[] ids);

	public void addPerToDep(Long deptId, String[] ids);

	public void deletePerFromDep(String id);

	/**
	 * 
	 * 增加新闻模块的权限，存取了首页小类的模块ID和院系大类的模块ID 加小类的ID
	 * 
	 * @author steven lee
	 * @param parentModuleIds
	 *            首页小类ID或院系大类的ID
	 * @param operatorModuleId
	 *            每个操作的ID
	 * @param roleId
	 *            角色ID
	 */
	public void addRight(String[] parentModuleIds, String[] operatorModuleId, String roleId, String yxdm);

	public List queryPersonRole(BasicPerson person);
}
