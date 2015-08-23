package com.lcweb.commons;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.ServletContextAware;

import com.lcpf.framework.pojo.ModuleBean;
import com.lcpf.framework.pojo.ModuleCollection;
import com.lcpf.framework.pojo.SysModule;

public class CheckRightInterceptor implements MethodInterceptor, ServletContextAware {

	private ServletContext servletContext = null;

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		String methodName = method.getName();
		String loginName = invocation.getArguments()[0].toString();
		String parentModuleFlag = invocation.getArguments()[1].toString();
		String operationModuleFlag = invocation.getArguments()[2].toString();
		HttpSession session = (HttpSession) servletContext.getAttribute(loginName);
		boolean isHasPermission = false;
		ModuleCollection collection = (ModuleCollection) session.getAttribute("module");
		if (collection != null) {
			if (!"moduleOperationRightForNews".equals(methodName)) {
				isHasPermission = moduleOperationRight(loginName, parentModuleFlag, operationModuleFlag, collection);
			} else {
				isHasPermission = moduleOperationRightForNews(loginName, parentModuleFlag, operationModuleFlag,
						collection);
			}
			invocation.proceed();
		} else {
			isHasPermission = (Boolean) invocation.proceed();
		}
		return isHasPermission;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public boolean moduleOperationRight(String loginName, String parentModuleFlag, String operationModuleFlag,
			ModuleCollection collection) {
		if ((loginName == null) || "".equals(loginName)) {
			return false;
		}
		if ("admin".equals(loginName)) {
			return true;
		}
		boolean isHasPermission = false;
		List<ModuleBean> permissionList = collection.getModuleBeanList();
		for (Iterator<ModuleBean> iterator = permissionList.iterator(); iterator.hasNext();) {
			ModuleBean bean = iterator.next();
			List<Map> moduleList = (List) bean.getSysModuleList();
			for (Iterator moduleIteraor = moduleList.iterator(); moduleIteraor.hasNext();) {
				Map<String, List<SysModule>> values = (Map<String, List<SysModule>>) moduleIteraor.next();
				for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
					String key = iter.next() + "";
					List<SysModule> sysModueList = values.get(key);
					for (Iterator<SysModule> sysIterator = sysModueList.iterator(); sysIterator.hasNext();) {
						com.lcpf.framework.pojo.SysModule sysModule = sysIterator.next();
						if (parentModuleFlag.equalsIgnoreCase(sysModule.getModuleFlag())) {
							for (Iterator subModuleIterator = sysModule.getChildModules().iterator(); subModuleIterator
									.hasNext();) {
								SysModule subModule = (SysModule) subModuleIterator.next();
								if (operationModuleFlag.equalsIgnoreCase(subModule.getModuleFlag())) {
									isHasPermission = true;
									break;
								}
							}
							break;
						}
					}
				}
			}
		}
		return isHasPermission;
	}

	@SuppressWarnings("unchecked")
	public boolean moduleOperationRightForNews(String loginName, String parentModuleFlag, String operationModuleFlag,
			ModuleCollection collection) {
		boolean isHasPermission = false;
		List<ModuleBean> newsModuleList = collection.getNewsModuleList();
		for (Iterator<ModuleBean> iterator = newsModuleList.iterator(); iterator.hasNext();) {
			ModuleBean newsModuleBean = iterator.next();
			List<Map> moduleList = (List) newsModuleBean.getSysModuleList();
			for (Iterator moduleIteraor = moduleList.iterator(); moduleIteraor.hasNext();) {
				SysModule module = (SysModule) moduleIteraor.next();
				if (parentModuleFlag.equalsIgnoreCase(module.getModuleFlag())) {
					for (Iterator<SysModule> sysIterator = module.getChildModules().iterator(); sysIterator.hasNext();) {
						SysModule newsSmallItemModule = sysIterator.next();
						if (operationModuleFlag.equalsIgnoreCase(newsSmallItemModule.getModuleFlag())) {
							isHasPermission = true;
							break;
						}
					}
				} else {
					for (Iterator<SysModule> sysIterator = module.getChildModules().iterator(); sysIterator.hasNext();) {
						com.lcpf.framework.pojo.SysModule sysModule = sysIterator.next();
						if (parentModuleFlag.equalsIgnoreCase(sysModule.getModuleFlag())) {
							for (Iterator subModuleIterator = sysModule.getChildModules().iterator(); subModuleIterator
									.hasNext();) {
								SysModule subModule = (SysModule) subModuleIterator.next();
								if (operationModuleFlag.equalsIgnoreCase(subModule.getModuleFlag())) {
									isHasPermission = true;
									break;
								}
							}
							break;
						}
					}
				}
			}
		}
		return isHasPermission;
	}
}
