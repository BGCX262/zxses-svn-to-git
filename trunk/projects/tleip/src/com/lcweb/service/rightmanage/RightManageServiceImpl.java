package com.lcweb.service.rightmanage;

import java.util.List;

import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.dao.rightmanage.RightManageDao;
import com.lcweb.service.base.BaseServiceImpl;

public class RightManageServiceImpl extends BaseServiceImpl implements RightManageService {

	private RightManageDao rightManageDao;

	public RightManageDao getRightManageDao() {
		return rightManageDao;
	}

	public void setRightManageDao(RightManageDao rightManageDao) {
		this.rightManageDao = rightManageDao;
	}

	public void giveRight(String[] funs, String roleId) {
		this.rightManageDao.addRight(funs, roleId);
	}

	public void assignRight(String[] roleIds, String personId) {
		this.rightManageDao.addAssignRight(roleIds, personId);
	}

	public void addBatchAssignUserRight(String[] personIds, String roleId) {

		this.rightManageDao.addBatchAssignUserRight(personIds, roleId);
	}

	public void deleteFromRole(String roleId, String[] ids) {
		this.rightManageDao.deleteFromRole(roleId, ids);
	}

	public void addPerToDep(Long deptId, String[] ids) {
		this.rightManageDao.addPerToDep(deptId, ids);
	}

	public void deletePerFromDep(String id) {
		this.rightManageDao.deletePerFromDep(id);
	}

	public void giveRight(String[] parentModuleIds, String[] operatorModuleId, String roleId, String yxdm) {
		this.rightManageDao.addRight(parentModuleIds, operatorModuleId, roleId, yxdm);
	}

	public List queryPersonRole(BasicPerson person) {
		return this.rightManageDao.queryPersonRole(person);
	}
}
