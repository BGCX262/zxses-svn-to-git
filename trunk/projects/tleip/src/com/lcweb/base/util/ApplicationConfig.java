package com.lcweb.base.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * 读取应用程序配置文件"application.properties"工具类
 * @author Sam
 *
 */
public class ApplicationConfig {
	private static Properties configFile = null;
	static {		
		load();
	}
	
	/**
	 * 检测配置文件是否为空。
	 */
	private static void checkConfigFile() {
		if (configFile == null) {
			throw new RuntimeException("file 'application.properties' is not exists!");
		}
	}
	
	/**
	 * 获取配置文件的某个属性值
	 * @param key 配置属性
	 * @return
	 */
	public static String get(String key) {
		checkConfigFile();
		return configFile.getProperty(key);
	}
	
	/**
	 * 获取配置文件的某个属性值，如果属性值为空，则返回指定的默认值
	 * @param key 配置属性
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String get(String key,String defaultValue) {
		return StringUtils.defaultIfEmpty(get(key), defaultValue);
	}
	
	private static void load(){
		Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            new RuntimeException(e);
        }
        configFile = prop;
	}
}