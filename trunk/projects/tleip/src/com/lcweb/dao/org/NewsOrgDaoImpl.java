package com.lcweb.dao.org;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.dao.base.BaseDaoImpl;

public class NewsOrgDaoImpl extends BaseDaoImpl implements NewsOrgDao {
	/*
	 * get college list by parameter map
	 * 
	 * @see com.lcweb.dao.org.NewsOrgDao#getSchCollegeDepartment(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List getSchCollegeDepartment(Map hmPara) {
		return findListByHSQLId("getSchCollegeDepartment", hmPara);
	}

	@SuppressWarnings("unchecked")
	public List getSchCollegeDepartment(String xxdm, String value, BasicPerson person) {
		String querySql = "select DISTINCT (scd.yxmc),scd.yxdm from sch_college_department scd "
				+ " inner join basic_person bp on bp.dept_id = scd.yxdm where " + "bp.person_account=? ";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createSQLQuery(querySql);
		query.setString(0, person.getPersonAccount());
		return query.list();
	}

	/**
	 * 
	 * @Description:PersonId查询SchCollegeDepartment
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List getSpecialDepartment(BasicPerson basicPerson) {
		String hql = "select scd from SchCollegeDepartment scd , BasicPerson bp where bp.basicDepartment.deptId = scd.deptId"
				+ " and bp.personId= " + basicPerson.getPersonId();
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 
	 * @Description:PersonId查询Role
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List findRoleByPersonId(BasicPerson basicPerson) {
		String hql = "from BasicPerson bp where bp.personId= " + basicPerson.getPersonId();
		return this.getHibernateTemplate().find(hql);
	}
}
