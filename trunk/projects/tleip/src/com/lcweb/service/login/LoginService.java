package com.lcweb.service.login;

import javax.servlet.http.HttpServletRequest;

import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.service.base.BaseService;


public interface LoginService extends BaseService{
   public boolean findByUnamePass(HttpServletRequest request, String username,String password);
   
   public BasicPerson getLoginerBySSOUser(String userName);
}
