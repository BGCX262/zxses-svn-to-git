package com.lcweb.service.rightmanage;

import java.util.List;

import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.service.base.BaseService;

public interface RightManageService extends BaseService {
	public void giveRight(String[]funs ,String roleId);
	public void assignRight(String[] roleIds, String personId);
	public void addBatchAssignUserRight(String[] personIds, String roleId);
	
	/**
	 * 将人员从角色中删除（批量分配权限）
	 */
	public void deleteFromRole(String roleId, String[] ids);
	
	/**
	 * 将查出后，选中的人员，添加到对应的部门里
	 */
	public void addPerToDep(Long deptId, String[] ids);
	
	/**
	 * 将人员从部门中移除
	 */
	public void deletePerFromDep(String id);
	
	/**
	 * 
	 * 增加新闻模块的权限，存取了首页小类的模块ID和院系大类的模块ID
	 * 加小类的ID
	 * @author steven lee
	 * @param parentModuleIds  首页小类ID或院系大类的ID
	 * @param operatorModuleId  每个操作的ID
	 * @param roleId  角色ID
	 */
	public void giveRight(String[] parentModuleIds, String[] operatorModuleId,
			String roleId,String yxdm) ;
	
	public List queryPersonRole(BasicPerson person);
}
