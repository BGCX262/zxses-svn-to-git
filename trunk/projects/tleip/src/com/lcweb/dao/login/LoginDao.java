package com.lcweb.dao.login;

import java.util.List;

import com.lcweb.dao.base.BaseDao;

public interface LoginDao extends BaseDao {
	public List findUserByUnamePass(String username, String password);

	public List findAdminByUnamePass(String username, String password);
}
