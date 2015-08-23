package com.agilefly.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.agilefly.bean.SysConfig;
import com.agilefly.bean.SysModule;
import com.agilefly.bean.SysPrivilege;
import com.agilefly.bean.SysRole;
import com.agilefly.bean.SysType;
import com.agilefly.bean.SysUser;
import com.agilefly.commons.SysConstant;
import com.agilefly.service.sysconfig.ISysConfigService;
import com.agilefly.service.sysmodule.ISysModuleService;
import com.agilefly.service.sysprivilege.ISysPrivilegeService;
import com.agilefly.service.sysrole.ISysRoleService;
import com.agilefly.service.systemuser.ISysUserService;
import com.agilefly.service.systype.ISysTypeService;
import com.agilefly.utils.CipherUtil;

/**
 * @author boleyn_renlei
 * @date May 18, 2012 10:39:46 PM
 */
@Controller("/sys/systeminit")
public class InitAction extends Action{
	@Resource ISysPrivilegeService sysPrivilegeService;
	@Resource ISysModuleService sysModuleService;
	@Resource ISysRoleService sysRoleService;
	@Resource ISysUserService sysUserService;
	@Resource ISysConfigService sysConfigService;
	@Resource ISysTypeService sysTypeService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		initSysModule();
		initSysPrivilege();
		initSysUserRole();
		initSysConfig();
		initSysType();
		//request.setAttribute("showMsg", SysObj.SystemInit());
		request.setAttribute("message", "系统初始化成功");
		request.setAttribute("urladdress", "sys/login.do?method=toLogin");
		return mapping.findForward("message");
	}
	
	/**
	 * 添加系统模块
	 */
	private void initSysModule(){
		if(sysModuleService.getCount() == 0){
			SysModule systemManage = new SysModule();
			systemManage.setName("系统管理");
			systemManage.setSn("systemManage");
			systemManage.setOrderNo(1);
			sysModuleService.addSysModule(systemManage, 0);
			
			SysModule sysWebSiteManage = new SysModule();
			sysWebSiteManage.setName("博客管理");
			sysWebSiteManage.setSn("sysWebSiteManage");
			sysWebSiteManage.setOrderNo(2);
			sysModuleService.addSysModule(sysWebSiteManage, 0);
			
			//系统管理子模块
			SysModule sysModuleManage = new SysModule();
			sysModuleManage.setName("模块管理");
			sysModuleManage.setSn("sysModuleManage");
			sysModuleManage.setUrl("view/sysmodule.do");
			sysModuleManage.setOrderNo(1);
			sysModuleService.addSysModule(sysModuleManage, systemManage.getId());
			
			SysModule sysUserManage = new SysModule();
			sysUserManage.setName("用户管理");
			sysUserManage.setSn("sysUserManage");
			sysUserManage.setUrl("view/sysuser.do");
			sysUserManage.setOrderNo(2);
			sysModuleService.addSysModule(sysUserManage, systemManage.getId());
			
			SysModule sysRoleManage = new SysModule();
			sysRoleManage.setName("角色管理");
			sysRoleManage.setSn("sysRoleManage");
			sysRoleManage.setUrl("view/sysrole.do");
			sysRoleManage.setOrderNo(3);
			sysModuleService.addSysModule(sysRoleManage, systemManage.getId());
			
			SysModule sysConfigManage = new SysModule();
			sysConfigManage.setName("系统配置");
			sysConfigManage.setSn("sysConfigManage");
			sysConfigManage.setUrl("view/sysconfig.do");
			sysConfigManage.setOrderNo(4);
			sysModuleService.addSysModule(sysConfigManage, systemManage.getId());
			
			//博客管理子模块
			SysModule sysBlogTypeManage = new SysModule();
			sysBlogTypeManage.setName("类型管理");
			sysBlogTypeManage.setSn("sysBlogTypeManage");
			sysBlogTypeManage.setUrl("view/sysblogtype.do");
			sysBlogTypeManage.setOrderNo(1);
			sysModuleService.addSysModule(sysBlogTypeManage, sysWebSiteManage.getId());
		}
	}
	
	/**
	 * 添加系统权限
	 */
	private void initSysPrivilege(){
		if(sysPrivilegeService.getCount() == 0){
			List<SysPrivilege> sysPrivileges = new ArrayList<SysPrivilege>();
			//模块操作权限
			sysPrivileges.add(new SysPrivilege("sysModuleManage","view","查看",1));
			sysPrivileges.add(new SysPrivilege("sysModuleManage","add","新增",2));
			sysPrivileges.add(new SysPrivilege("sysModuleManage","update","修改",3));
			sysPrivileges.add(new SysPrivilege("sysModuleManage","delete","删除",4));
			
			//用户管理操作权限
			sysPrivileges.add(new SysPrivilege("sysUserManage","view","查看",1));
			sysPrivileges.add(new SysPrivilege("sysUserManage","add","新增",2));
			sysPrivileges.add(new SysPrivilege("sysUserManage","update","修改",3));
			sysPrivileges.add(new SysPrivilege("sysUserManage","delete","删除",4));
			sysPrivileges.add(new SysPrivilege("sysUserManage","approve","审核",5));
			sysPrivileges.add(new SysPrivilege("sysUserManage","unApprove","反审核",6));
			sysPrivileges.add(new SysPrivilege("sysUserManage","assignRole","分配角色",7));
			
			
			//角色管理操作权限
			sysPrivileges.add(new SysPrivilege("sysRoleManage","view","查看",1));
			sysPrivileges.add(new SysPrivilege("sysRoleManage","add","新增",2));
			sysPrivileges.add(new SysPrivilege("sysRoleManage","update","修改",3));
			sysPrivileges.add(new SysPrivilege("sysRoleManage","delete","删除",4));
			sysPrivileges.add(new SysPrivilege("sysRoleManage","assignPrivilege","分配权限",5));
			
			//系统设置管理操作权限
			sysPrivileges.add(new SysPrivilege("sysConfigManage","view","查看",1));
			sysPrivileges.add(new SysPrivilege("sysConfigManage","update","修改",2));
			
			//博客类型管理操作权限
			sysPrivileges.add(new SysPrivilege("sysBlogTypeManage","view","查看",1));
			sysPrivileges.add(new SysPrivilege("sysBlogTypeManage","add","新增",2));
			sysPrivileges.add(new SysPrivilege("sysBlogTypeManage","update","修改",3));
			sysPrivileges.add(new SysPrivilege("sysBlogTypeManage","delete","删除",4));
			
			sysPrivilegeService.batchSave(sysPrivileges);
		}
	}
	
	/**
	 * 添加系统管理员角色和用户系统权限信息
	 */
	private void initSysUserRole(){
		if(sysRoleService.getCount() == 0 && sysUserService.getCount() == 0){
			SysRole sysRole = new SysRole();
			sysRole.setRoleName("系统管理员");
			
			//分配权限
			List<SysPrivilege> sysPrivileges = sysPrivilegeService.getScrollData().getResultlist();
			for (SysPrivilege sysPrivilege : sysPrivileges) {
				sysRole.addSysPrivilege(sysPrivilege);
			}
			
			sysRoleService.save(sysRole);
			
			SysUser sysUser = new SysUser();
			sysUser.setUsername("admin");
			sysUser.setRealname("系统管理员");
			sysUser.setPassword(CipherUtil.generatePassword("admin"));
			//不需要审批
			sysUser.setApproveStatus((byte)1);
			//默认启用
			sysUser.setAvailable((byte)1);
			//有效记录
			sysUser.setRecordStatus((byte)1);
			//设置为教师类型(后台只有教师类型才能登录)
			sysUser.setUserType(SysConstant.TEACHER);
			
			//分配角色
			sysUser.addSysRole(sysRole);
			sysUserService.save(sysUser);
		}
	}
	
	/**
	 * 添加系统配置信息
	 */
	private void initSysConfig(){
		if(sysConfigService.getCount() == 0 && sysUserService.getCount() == 0){
			SysConfig sysConfig = new SysConfig();
			
			sysConfig.setSysConfigCode("sysConfigManage");
			sysConfig.setSinaWeibo("http://blog.sina.com.cn/u/1660501074");
			sysConfig.setOnlineQq("http://wpa.qq.com/msgrd?v=3&amp;uin=541594150&amp;site=qq&amp;menu=yes");
			sysConfig.setPicQq("http://wpa.qq.com/pa?p=2:541594150:46");
			
			sysConfigService.save(sysConfig);
		}
	}
	
	/**
	 * 添加系统类型管理
	 */
	private void initSysType(){
		if(sysTypeService.getCount() == 0 && sysTypeService.getCount() == 0){
			//教师博客文章类型
			//文学新闻
			SysType wenxueNews = new SysType();
			wenxueNews.setTypeSymbol(SysConstant.ARTICLE_TYPE);
			wenxueNews.setTypeName("文学新闻");
			wenxueNews.setTypeDes("教师文学新闻博客文章类型");
			wenxueNews.setTypeCode("wenxueNews");
			wenxueNews.setExtFirst("teacher");
			wenxueNews.setTypeOrder(1);
			wenxueNews.setAvailable((byte)1);
			sysTypeService.save(wenxueNews);
			
			//写作指导
			SysType writeInstruct = new SysType();
			writeInstruct.setTypeSymbol(SysConstant.ARTICLE_TYPE);
			writeInstruct.setTypeName("写作指导");
			writeInstruct.setTypeDes("教师写作指导博客文章类型");
			writeInstruct.setTypeCode("writeInstruct");
			writeInstruct.setExtFirst("teacher");
			writeInstruct.setTypeOrder(2);
			writeInstruct.setAvailable((byte)1);
			sysTypeService.save(writeInstruct);
			
			//名著回廊
			SysType mingzhuCloister = new SysType();
			mingzhuCloister.setTypeSymbol(SysConstant.ARTICLE_TYPE);
			mingzhuCloister.setTypeName("名著回廊");
			mingzhuCloister.setTypeDes("教师名著回廊博客文章类型");
			mingzhuCloister.setTypeCode("mingzhuCloister");
			mingzhuCloister.setExtFirst("teacher");
			mingzhuCloister.setTypeOrder(3);
			mingzhuCloister.setAvailable((byte)1);
			sysTypeService.save(mingzhuCloister);
			
			//文学快车
			SysType wenxueKuaiche = new SysType();
			wenxueKuaiche.setTypeSymbol(SysConstant.ARTICLE_TYPE);
			wenxueKuaiche.setTypeName("文学快车");
			wenxueKuaiche.setTypeDes("教师文学快车博客文章类型");
			wenxueKuaiche.setTypeCode("wenxueKuaiche");
			wenxueKuaiche.setExtFirst("teacher");
			wenxueKuaiche.setTypeOrder(4);
			wenxueKuaiche.setAvailable((byte)1);
			sysTypeService.save(wenxueKuaiche);
			
			//网络教室
			SysType netClassroom = new SysType();
			netClassroom.setTypeSymbol(SysConstant.ARTICLE_TYPE);
			netClassroom.setTypeName("网络教室");
			netClassroom.setTypeDes("教师网络教室博客文章类型");
			netClassroom.setTypeCode("netClassroom");
			netClassroom.setExtFirst("teacher");
			netClassroom.setTypeOrder(5);
			netClassroom.setAvailable((byte)1);
			sysTypeService.save(netClassroom);
			
			//益智谜语
			SysType yizhiMiyu = new SysType();
			yizhiMiyu.setTypeSymbol(SysConstant.ARTICLE_TYPE);
			yizhiMiyu.setTypeName("益智谜语");
			yizhiMiyu.setTypeDes("教师益智谜语博客文章类型");
			yizhiMiyu.setTypeCode("yizhiMiyu");
			yizhiMiyu.setExtFirst("teacher");
			yizhiMiyu.setTypeOrder(6);
			yizhiMiyu.setAvailable((byte)1);
			sysTypeService.save(yizhiMiyu);
			
			//学生博客文章类型
			//作文
			SysType zuowen = new SysType();
			zuowen.setTypeSymbol(SysConstant.ARTICLE_TYPE);
			zuowen.setTypeName("作文");
			zuowen.setTypeDes("学生作文博客文章类型");
			zuowen.setTypeCode("zuowen");
			zuowen.setExtFirst("student");
			zuowen.setTypeOrder(1);
			zuowen.setAvailable((byte)1);
			sysTypeService.save(zuowen);
			
			//日记
			SysType diary = new SysType();
			diary.setTypeSymbol(SysConstant.ARTICLE_TYPE);
			diary.setTypeName("日记");
			diary.setTypeDes("学生日记博客文章类型");
			diary.setTypeCode("diary");
			diary.setExtFirst("student");
			diary.setTypeOrder(2);
			diary.setAvailable((byte)1);
			sysTypeService.save(diary);
		}
	}
}
