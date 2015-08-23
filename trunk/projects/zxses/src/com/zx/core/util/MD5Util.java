package com.zx.core.util;

import java.security.MessageDigest;

/**
 * 
 * @description:MD5密码加密算法
 * @author： zhangke
 * @date：Nov 17, 2009
 */
public class MD5Util {
    
    //十六进制下数字到字符的映射数组     
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};     
    
    /**   
     * @param inputString
     * @return   
     * @Description: 返回MD5密码   
     */
    public static String generatePassword(String inputString){
        return encodeByMD5(inputString);     
    }
    
    /**   
     * @param password MD5值
     * @param inputString 比较对象
     * @return 
     * @Description: 比较MD5值是否相等
     */
    public static boolean validatePassword(String password, String inputString){
        if(password.equals(encodeByMD5(inputString))){
            return true;     
        } else{     
            return false;     
        }     
    }
    
    /**   
     * @param originString 需要加密的字符串
     * @return MD5加密后的字符串
     * @Description: 采用MD5加密
     */
    private static String encodeByMD5(String originString){
        if (originString != null){     
            try{     
                //创建具有指定算法名称的信息摘要     
                MessageDigest md = MessageDigest.getInstance("MD5");     
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算     
                byte[] results = md.digest(originString.getBytes());     
                //将得到的字节数组变成字符串返回     
                String resultString = byteArrayToHexString(results);     
                return resultString.toUpperCase();     
            } catch(Exception ex){     
                ex.printStackTrace();     
            }     
        }     
        return null;     
    }     
       
    /**   
     * @param b
     * @return   
     * @Description:  
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();     
        for (int i = 0; i < b.length; i++){     
            resultSb.append(byteToHexString(b[i]));     
        }     
        return resultSb.toString();     
    }     
         
    /**   
     * @param b
     * @return   
     * @Description: 
     */
    private static String byteToHexString(byte b){
        int n = b;     
        if (n < 0)     
            n = 256 + n;     
        int d1 = n / 16;     
        int d2 = n % 16;     
        return hexDigits[d1] + hexDigits[d2];     
    }     
    
    public static void main(String[] args) {     
        String pwd="888888";                
        System.out.println("加密后的密码:"+MD5Util.generatePassword(pwd));     
    }     
}
