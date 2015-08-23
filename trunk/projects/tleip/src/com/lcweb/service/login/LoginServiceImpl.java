package com.lcweb.service.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.SysAdmin;
import com.lcweb.dao.login.LoginDao;
import com.lcweb.service.base.BaseServiceImpl;

public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

	private LoginDao loginDao;
	public boolean findByUnamePass(HttpServletRequest request,String loginname, String password) {
		
		List adminList = loginDao.findAdminByUnamePass(loginname, password);
		if(adminList.size()==0){
			List list = loginDao.findUserByUnamePass(loginname, password);
			if(list.size()==0){
				return false;
			}else{
				request.getSession().setAttribute("logininfo", list.get(0));
				return true;
			}
		}else{
			SysAdmin admin = (SysAdmin)adminList.get(0);
			BasicPerson person = new BasicPerson();
			person.setPersonAccount(admin.getAdminName());
			request.getSession().setAttribute("logininfo",person);
			return true;
		}
		
		
	}
	public BasicPerson getLoginerBySSOUser(String userName){
		String hqlteacher="from BasicPerson  bp where bp.personAccount= '"+userName+"'";
		String hqlAdmin="from SysAdmin  ad where ad.adminName= '"+userName+"'";
		BasicPerson loginer = null;
		List<BasicPerson> teacherList = queryObjectList(hqlteacher);
		
		if((teacherList!=null)&&(teacherList.size()!=0)){
			loginer = teacherList.get(0);
		}else{
			List<SysAdmin> adminList = queryObjectList(hqlAdmin);
			if((adminList!=null) && (adminList.size()!=0)){
				SysAdmin admin = adminList.get(0);
				loginer = new BasicPerson();
				loginer.setPersonAccount(admin.getAdminName());
			}
			 
		}
		return loginer;
	}
	public LoginDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	

}
