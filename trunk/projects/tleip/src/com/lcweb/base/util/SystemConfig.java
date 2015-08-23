package com.lcweb.base.util;

import java.util.*;
import java.io.*;

public class SystemConfig {
	private Properties perConst = new Properties();
	private Properties perDir = new Properties();
	private static SystemConfig m_Sys = new SystemConfig();

	private SystemConfig() {
		loadConfigInfo();
	}

	/**
	 * get a instance of this class, user MUST use this method to use it
	 * 
	 * @return an instance of this class
	 */
	public static SystemConfig getInstance() {
		if (m_Sys == null) {
			m_Sys = new SystemConfig();
		}
		return m_Sys;
	}

	private boolean loadConfigInfo() {
		try {
			InputStream fileConst = this.getClass().getResourceAsStream("constant.properties");
			perConst.load(fileConst);
			// FileInputStream fileSys = new
			// FileInputStream("constant.properties");
			// perConst.load(fileSys);

		} catch (Exception e) {
		}
		try {
			InputStream dirConst = this.getClass().getResourceAsStream("path.properties");
			perDir.load(dirConst);
		} catch (Exception e) {
		}
		return true;
	}

	public void reload() {
		loadConfigInfo();
	}

	public String getConstant(String strKey) {
		return StringUtil.getNullString(perConst.getProperty(strKey));
	}

	public String getDirConstant(String strKey) {
		return StringUtil.getNullString(perDir.getProperty(strKey));
	}

	public static void main(String args[]) {
		// System.out.print("user.home:"+System.getProperty("user.home")+"\n");
		// System.out.print("java.vendor.url:"+System.getProperty("java.vendor.url")+"\n");
		// System.out.print("java.home:"+System.getProperty("java.home")+"\n");
		// System.out.print("os.arch:"+System.getProperty("os.arch")+"\n");
		// System.out.print("user.dir:"+System.getProperty("user.dir")+"\n");
		String strKey = "path";
		System.out.print(SystemConfig.getInstance().getDirConstant(strKey));

	}
}
