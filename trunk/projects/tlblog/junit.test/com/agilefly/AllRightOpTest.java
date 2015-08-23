package com.agilefly;

/**
 * 所有有关权限的测试类，便于项目开发中功能新增手动添加数据到数据库
 */
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agilefly.bean.SysModule;
import com.agilefly.bean.SysPrivilege;
import com.agilefly.service.sysmodule.ISysModuleService;
import com.agilefly.service.sysprivilege.ISysPrivilegeService;
import com.agilefly.service.sysrole.ISysRoleService;
import com.agilefly.service.systemuser.ISysUserService;

public class AllRightOpTest {
	private static ApplicationContext cxt;
	private static ISysPrivilegeService sysPrivilegeService;
	private static ISysModuleService sysModuleService;
	private static ISysRoleService sysRoleService;
	private static ISysUserService sysUserService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("application*.xml");
			sysPrivilegeService = (ISysPrivilegeService)cxt.getBean("sysPrivilegeService");
			sysModuleService = (ISysModuleService)cxt.getBean("sysModuleService");
			sysRoleService = (ISysRoleService)cxt.getBean("sysRoleService");
			sysUserService = (ISysUserService)cxt.getBean("sysUserService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/********************************添加系统模块测试类方法***********************/
	@Test
	public void testSaveSysModule1(){
		//博客管理子模块
		SysModule sysRoleManage = new SysModule();
		sysRoleManage.setName("类型管理");
		sysRoleManage.setSn("sysBlogTypeManage");
		sysRoleManage.setUrl("view/sysblogtype.do");
		sysRoleManage.setOrderNo(1);
		SysModule parent = sysModuleService.findByCondition("o.sn=?", new Object[]{"sysWebSiteManage"}).get(0);
		
		sysModuleService.addSysModule(sysRoleManage, parent.getId());
	}
	
	/********************************添加系统权限值测试类方法***********************/
	@Test
	public void testSaveSysPrivilege1(){
		List<SysPrivilege> sysPrivileges = new ArrayList<SysPrivilege>();
		
		//博客类型管理操作权限
		sysPrivileges.add(new SysPrivilege("sysBlogTypeManage","add","新增"));
		sysPrivileges.add(new SysPrivilege("sysBlogTypeManage","delete","删除"));
		sysPrivileges.add(new SysPrivilege("sysBlogTypeManage","update","修改"));
		sysPrivileges.add(new SysPrivilege("sysBlogTypeManage","view","查看"));
		
		sysPrivilegeService.batchSave(sysPrivileges);
	}
	
	@Test
	public void testSaveSysPrivilege2(){
		List<SysPrivilege> sysPrivileges = new ArrayList<SysPrivilege>();
		
		//系统设置管理操作权限
		sysPrivileges.add(new SysPrivilege("sysConfigManage","update","修改"));
		sysPrivileges.add(new SysPrivilege("sysConfigManage","view","查看"));
		
		sysPrivilegeService.batchSave(sysPrivileges);
	}
	
	@Test
	public void testSaveSysPrivilege3(){
		List<SysPrivilege> sysPrivileges = new ArrayList<SysPrivilege>();
		
		//系统用户管理操作权限
		sysPrivileges.add(new SysPrivilege("sysUserManage","approve","审核"));
		sysPrivileges.add(new SysPrivilege("sysUserManage","unApprove","反审核"));
		
		sysPrivilegeService.batchSave(sysPrivileges);
	}

}
