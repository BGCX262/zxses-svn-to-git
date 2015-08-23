package com.agilefly.service.base;


import java.util.LinkedHashMap;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agilefly.bean.SysUser;
import com.agilefly.commons.QueryResult;
import com.agilefly.service.systemuser.ISysUserService;

public class ISysUserServiceTest {
	private static ApplicationContext cxt;
	private static ISysUserService systemUserService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("application*.xml");
			systemUserService = (ISysUserService)cxt.getBean("sysUserService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBaseDaoSave(){
		SysUser user = new SysUser();
		for (int i = 10; i < 20; i++) {
			user.setUsername("test" + i);
			user.setPassword("test" + i);
			systemUserService.save(user);
			System.out.println(user.getId());
		}
	}
	
	@Test
	public void testBaseDaoUpdate(){
		SysUser user = systemUserService.find(2);
		user.setUsername("修改后的");
		systemUserService.update(user);
	}
	
	@Test
	public void testBaseDaoDelete(){
		systemUserService.delete(3);
	}
	
	@Test
	public void testBaseDaoGetScrollData(){
		//getScrollData(int firstindex, int maxresult
		//, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby)
		//排序测试
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		//orderby.put("id", "desc");
		//过滤测试
		String whereHql = "o.recordStatus= ? ";
		Object[] params = new Object[]{new Byte((byte)1)};
		QueryResult<SysUser> users = systemUserService.getScrollData(0, 6, whereHql, params,orderby);
		for (SysUser sysUser : users.getResultlist()) {
			System.out.println(sysUser.getUsername());
		}
	}

}
