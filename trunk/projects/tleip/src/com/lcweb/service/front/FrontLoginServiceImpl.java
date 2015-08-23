package com.lcweb.service.front;

import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.dao.front.FrontLoginDao;
import com.lcweb.service.base.BaseServiceImpl;

public class FrontLoginServiceImpl extends BaseServiceImpl implements
		FrontLoginService {
	private FrontLoginDao frontLoginDao;
	
	
	public FrontUser findUserByUnamePassType(String userAccount, String password, String userType) {
		return frontLoginDao.findUserByUnamePassType(userAccount, password, userType);
	}
	
	public FrontUser findUserByAccount(String userAccount) {
		return frontLoginDao.findUserByAccount(userAccount);
	}
	
	public void setFrontLoginDao(FrontLoginDao frontLoginDao) {
		this.frontLoginDao = frontLoginDao;
	}
}
