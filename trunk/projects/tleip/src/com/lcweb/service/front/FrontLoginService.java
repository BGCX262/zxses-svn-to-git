package com.lcweb.service.front;

import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.service.base.BaseService;

public interface FrontLoginService extends BaseService{
	public FrontUser findUserByUnamePassType(String userAccount, String password, String userType);
	
	public FrontUser findUserByAccount(String userAccount);
}
