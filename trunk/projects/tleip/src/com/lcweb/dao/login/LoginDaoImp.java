package com.lcweb.dao.login;

import java.util.ArrayList;
import java.util.List;

import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.commons.CheckRight;
import com.lcweb.dao.base.BaseDaoImpl;

public class LoginDaoImp extends BaseDaoImpl implements LoginDao {
	private CheckRight checkRight;

	@SuppressWarnings("unchecked")
	public List findUserByUnamePass(String loginname, String password) {
		List list = new ArrayList();
		List bplist = this.getHibernateTemplate().find(
				"from BasicPerson bp where bp.personAccount=? and bp.password=?", new String[] { loginname, password });
		if (bplist.size() > 0) {
			BasicPerson bp = (BasicPerson) bplist.get(0);
			list = this.getHibernateTemplate().find(
					"from SysRolePerson srp where srp.basicPerson.personId=" + bp.getPersonId());
			if (list.size() > 0) {
				list = bplist;
			}
		}
		return bplist;
	}

	@SuppressWarnings("unchecked")
	public List findAdminByUnamePass(String loginname, String password) {
		List list = new ArrayList();
		list = this.getHibernateTemplate().find("from SysAdmin a where a.adminName=? and a.password=?",
				new String[] { loginname, password });
		return list;
	}

	public CheckRight getCheckRight() {
		return checkRight;
	}

	public void setCheckRight(CheckRight checkRight) {
		this.checkRight = checkRight;
	}

}
