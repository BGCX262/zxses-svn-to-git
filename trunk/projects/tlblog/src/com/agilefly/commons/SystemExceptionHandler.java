package com.agilefly.commons;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

public class SystemExceptionHandler extends ExceptionHandler {
	private static Log logger = LogFactory.getLog(SystemExceptionHandler.class);
	
	/**
	 * 处理SystemException异常
	 */
	@Override
	public ActionForward execute(Exception ex, ExceptionConfig exConfig,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		ActionForward forward = null;
		if(exConfig.getPath() != null){
			forward = new ActionForward(exConfig.getPath());
		}else {
			forward = mapping.getInputForward();
		}
		
		logger.debug("出现异常!",ex);
		
		if(ex instanceof SystemException){
			SystemException se = (SystemException)ex;
			//取出key值
			String key = se.getKey();
			
			ActionMessage error = null;
			if(key == null){
				//带参数错误信息 使用默认的key errors.detail={0}
				error = new ActionMessage(exConfig.getKey(),se.getMessage());
			}else {
				//获得填充属性文件的值
				if(se.getValues() != null){
					error = new ActionMessage(key,se.getValues());
				}else{
					error = new ActionMessage(key);
				}
			}
			this.storeException(request, key, error, forward, exConfig.getScope());
			return forward;
		}
		
		return super.execute(ex, exConfig, mapping, form, request, response);
	}
}
