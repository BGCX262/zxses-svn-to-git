package com.agilefly.commons.web;

import org.apache.commons.beanutils.Converter;

import com.agilefly.bean.SysPrivilegeId;

/**
 * @author boleyn_renlei
 * @date May 17, 2012 12:27:30 AM
 * 定义权限主键转换器
 */
public class SysPrivilegeIdConverter implements Converter{
	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object value) {
		if(value==null || "".equals((String)value)) return null;
		if(value instanceof SysPrivilegeId) return value;
		try{
			String param =(String) value;
			String[] arr = param.split(",");
			if(arr.length==2){
				return new SysPrivilegeId(arr[0], arr[1]);
			}
		}catch (Exception e) {}
		return null;
	}
}