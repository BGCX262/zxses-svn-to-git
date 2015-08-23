package com.zx.core.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
    
    static Logger logger = Logger.getLogger(PropertiesUtil.class.getName());

    private static PropertiesUtil instance = null;

    public static synchronized PropertiesUtil getInstance() {
        if (instance == null)
            instance = new PropertiesUtil();
        return instance;
    }
    
    public Properties load(String path) {
        Properties prop = new Properties();
        InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
        try {
            prop.load(is);
            is.close();
        } catch (IOException e) {

        }
        return prop;
    }

    public String readValue(String filePath, String key) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            String value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public HashMap<String, String> readProperties(String filePath) {
        HashMap<String, String> pros = new HashMap<String, String>();
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(
                    filePath));
            props.load(in);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String value = props.getProperty(key);
                pros.put(key, value);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return pros;
    }


    public void writeProperties(String filePath, String parameterName,
            String parameterValue) {
        Properties prop = new Properties();
        try {
            InputStream fis = new FileInputStream(filePath);
            // 从输入流中读取属性列表（键和元素对）
            prop.load(fis);
            // 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName, parameterValue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            // prop.store(fos, "Update '" + parameterName + "' value");
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public void updateProperties(String filePath, String parameterName,
            String parameterValue) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(filePath));
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName, parameterValue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            prop.store(fos, "Update '" + parameterName + "' value");
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
