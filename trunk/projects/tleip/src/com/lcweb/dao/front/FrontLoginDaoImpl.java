package com.lcweb.dao.front;

import java.util.List;

import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.dao.base.BaseDaoImpl;

public class FrontLoginDaoImpl extends BaseDaoImpl implements FrontLoginDao {
	@SuppressWarnings("unchecked")
	public FrontUser findUserByUnamePassType(String userAccount, String password, String userType) {
		List<FrontUser> bplist = this.getHibernateTemplate().find(
				"from FrontUser fu where fu.userAccount=? and fu.password=? and fu.userType=?", new String[] { userAccount, password, userType });
		if (bplist.size() > 0) {
			return bplist.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public FrontUser findUserByAccount(String userAccount){
		List<FrontUser> bplist = this.getHibernateTemplate().find(
				"from FrontUser fu where fu.userAccount=?", new String[] { userAccount});
		if (bplist.size() > 0) {
			return bplist.get(0);
		}
		return null;
	}
}
