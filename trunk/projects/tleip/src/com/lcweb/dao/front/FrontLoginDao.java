package com.lcweb.dao.front;

import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.dao.base.BaseDao;

public interface FrontLoginDao extends BaseDao{
	public FrontUser findUserByUnamePassType(String userAccount, String password, String userType);
	public FrontUser findUserByAccount(String userAccount);
}
