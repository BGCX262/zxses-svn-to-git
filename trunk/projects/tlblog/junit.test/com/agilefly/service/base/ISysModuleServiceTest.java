package com.agilefly.service.base;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agilefly.bean.SysModule;
import com.agilefly.service.sysmodule.ISysModuleService;

public class ISysModuleServiceTest {
	private static ApplicationContext cxt;
	private static ISysModuleService sysModuleService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("application*.xml");
			sysModuleService = (ISysModuleService)cxt.getBean("sysModuleService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveSysModule(){
		SysModule module = new SysModule();
		module.setName("系统管理");
		module.setSn("sysModuleManage");
		sysModuleService.addSysModule(module, 0);
		//module.setSn(module.getId());
	}
	
	@Test
	public void testSaveSysModule1(){
		for (int i = 0; i < 10; i++) {
			SysModule module = new SysModule();
			module.setName("博客管理" + i);
			module.setSn("sysWebSiteManage");
			sysModuleService.addSysModule(module, 0);
		}
		SysModule module = new SysModule();
		module.setName("博客管理子模块");
		module.setSn("sysWebSiteManage");
		sysModuleService.addSysModule(module, 1);
		SysModule module1 = new SysModule();
		module1.setName("博客管理子模块2");
		module1.setSn("sysWebSiteManage");
		sysModuleService.addSysModule(module1, 1);
		//module.setSn(module.getId());
	}
	
	@Test
	public void testSaveSysModule2(){
		SysModule sysConfigManage = new SysModule();
		sysConfigManage.setName("系统配置");
		sysConfigManage.setSn("sysConfigManage");
		sysConfigManage.setUrl("view/sysconfig.do");
		sysConfigManage.setOrderNo(4);
		sysModuleService.addSysModule(sysConfigManage, 2);
	}
}
