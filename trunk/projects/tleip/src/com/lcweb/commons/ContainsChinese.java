package com.lcweb.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContainsChinese {
	 static String regEx = "[\u4e00-\u9fa5]";    
	 static Pattern pat = Pattern.compile(regEx);
	 
     public static boolean isContainsChinese(String str) {    
        Matcher matcher = pat.matcher(str);    
        boolean flg = false;    
        if (matcher.find()){    
            flg = true;    
        }    
        return flg;    
    }    
}
