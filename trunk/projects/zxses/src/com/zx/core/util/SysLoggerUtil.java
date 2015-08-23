package com.zx.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;

import com.zx.core.base.jdbc.SQL.DBConnection;
import com.zx.core.vo.Loginer;

public class SysLoggerUtil {

	private static Logger logger;

	protected SysLoggerUtil(String name) {

	}

	public static SysLoggerUtil getSysLogger(String classname) {
		SysLoggerUtil sysLoggerUtil = new SysLoggerUtil(classname);
		logger = Logger.getLogger(classname);
		return sysLoggerUtil;
	}

	/***************************************************************************
	 * 记录为错误日志
	 * 
	 * @param message
	 * @param e
	 */
	public void saveErrorLog(Object message, Exception e) {
		this.saveSysLog(message, e, 3l, new Loginer());
	}

	/***************************************************************************
	 * 记录为用户操作时发生的错误日志
	 * 
	 * @param message
	 * @param e
	 */
	public void saveErrorLog(Object message, Exception e, Loginer currLoginer) {
		this.saveSysLog(message, e, 3l, currLoginer);
	}

	/***************************************************************************
	 * 记录为用户操作的日志
	 * 
	 * @param message
	 * @param userLogType
	 * @param currLoginer
	 */
	public void saveOperLog(Object message, Loginer currLoginer) {
		this.saveSysLog(message, null, 2l, currLoginer);
	}

	/***************************************************************************
	 * 友好性的提示消息
	 * 
	 * @param message
	 *            用户定义的消息
	 * @param e
	 *            系统抛出的异常信息
	 * @param userLogType
	 *            日志类型 1、登录日志 2、操作日志 3、错误日志
	 * @param currLoginer
	 *            当前登录用户
	 */
	public void saveSysLog(Object message, Exception e, Long userLogType,
			Loginer currLoginer) {
		if(currLoginer==null){
			currLoginer=new Loginer();
		}
		String exceptionStr = "";
		if (e != null) {
			exceptionStr = getStackException(e);
			logger.error(exceptionStr);
		}
		String sql = "insert into hr_user_log (HR_USER_LOG_ID,HR_USER_ID,TITLE,CONTENT,IP,CREATE_TIME,UPDATE_TIME,RECORD_STATUS)"
				+ " values (?,?,?,?,?,?,?,1)";
		Properties prop = PropertiesUtil.getInstance().load(
				"config/database/config.properties");
		String outputConsoleException = prop
				.getProperty("outpout.console.exception");
		if (outputConsoleException == null) {
			outputConsoleException = "false";
		}
		if ("true".equals(outputConsoleException)) {
			System.out.println(message + ":");
			System.out.println(exceptionStr);
		}
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = DBConnection.getInstance().getConnect();
			String msg = message.toString();
			if (msg.length() > 2000) {
				msg = msg.substring(0, 1800);
			}
			if (exceptionStr.length() > 2000) {
				exceptionStr = exceptionStr.substring(0, 1800);
			}
			pstat = conn.prepareStatement(sql);
			pstat.setLong(1, userLogType);
			pstat.setObject(2, currLoginer.getId());
			pstat.setString(3, msg);
			pstat.setString(4, exceptionStr);
			if (StringUtils.isEmpty(currLoginer.getCurrentLoginIp())) {
				pstat.setString(5, InetAddress.getLocalHost().getHostAddress());
			} else {
				pstat.setString(5, currLoginer.getCurrentLoginIp());
			}
			pstat.setTimestamp(6, new java.sql.Timestamp(new java.util.Date()
					.getTime()));
			pstat.setTimestamp(7, new java.sql.Timestamp(new java.util.Date()
					.getTime()));
			pstat.execute();
			conn.commit();
		} catch (Exception e1) {
			logger.error(getStackException(e1));
			if ("true".equals(outputConsoleException)) {
				System.out.println(getStackException(e1));
			}
		} finally {
			if (pstat != null) {
				try {
					pstat.close();
				} catch (SQLException e1) {
					logger.error(getStackException(e1));
					if ("true".equals(outputConsoleException)) {
						System.out.println(getStackException(e1));
					}
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					logger.error(getStackException(e1));
					if ("true".equals(outputConsoleException)) {
						System.out.println(getStackException(e1));
					}
				}
			}
		}
	}

	/**
	 * @Description:获取异常堆栈信息
	 */
	public String getStackException(Exception e) {
		StringWriter exceptionStr = new StringWriter();
		PrintWriter pw = new PrintWriter(exceptionStr);
		e.printStackTrace(pw);
		return exceptionStr.toString();
	}

}