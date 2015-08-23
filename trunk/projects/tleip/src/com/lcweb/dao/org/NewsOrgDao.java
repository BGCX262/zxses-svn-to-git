package com.lcweb.dao.org;

import java.util.List;
import java.util.Map;

import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.dao.base.BaseDao;

public interface NewsOrgDao extends BaseDao {
	/*
	 * get colleage department list by map parameter
	 */
	public List getSchCollegeDepartment(Map paraMap);

	public List getSchCollegeDepartment(String xxdm, String value, BasicPerson person);

	public List getSpecialDepartment(BasicPerson basicPerson);

	public List findRoleByPersonId(BasicPerson basicPerson);
}
