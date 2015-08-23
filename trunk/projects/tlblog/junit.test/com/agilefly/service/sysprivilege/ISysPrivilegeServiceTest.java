package com.agilefly.service.sysprivilege;


import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agilefly.bean.SysModule;
import com.agilefly.bean.SysPrivilege;

public class ISysPrivilegeServiceTest {
	private static ApplicationContext cxt;
	private static ISysPrivilegeService sysPrivilegeService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("application*.xml");
			sysPrivilegeService = (ISysPrivilegeService)cxt.getBean("sysPrivilegeService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveSysPrivilege(){
		List<SysPrivilege> sysPrivileges = new ArrayList<SysPrivilege>();
		sysPrivileges.add(new SysPrivilege("department","view","部门查看"));
		sysPrivileges.add(new SysPrivilege("department","add","部门新增"));
		sysPrivileges.add(new SysPrivilege("department","update","部门修改"));
		sysPrivileges.add(new SysPrivilege("department","delete","部门删除"));
		sysPrivilegeService.batchSave(sysPrivileges);
	}
	
	@Test
	public void testSaveSysPrivilege1(){
		List<SysPrivilege> sysPrivileges = new ArrayList<SysPrivilege>();
		sysPrivileges.add(new SysPrivilege("department","view","部门查看"));
		sysPrivileges.add(new SysPrivilege("department","add","部门新增"));
		sysPrivileges.add(new SysPrivilege("department","update","部门修改"));
		sysPrivileges.add(new SysPrivilege("department","delete","部门删除"));
		sysPrivilegeService.batchSave(sysPrivileges);
	}
}
