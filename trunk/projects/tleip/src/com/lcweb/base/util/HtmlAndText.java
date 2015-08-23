package com.lcweb.base.util;

import java.util.Vector;

public class HtmlAndText {
	
	public  HtmlAndText(){
    }

    public static String ArticleToDB(String strSource)
    {
    	strSource = StringUtil.getNullString(strSource);
        String strDest = "";
        strDest = Replace(strSource, "'", "''");
        strDest = Replace(strSource, "\n", "<BR>");
        return strDest;
    }

    public static String HtmlToText(String strSource)
    {
    	strSource = StringUtil.getNullString(strSource);
    	String strDest = "";
        strDest = Replace(strSource, "&apos;", "'");
        strDest = Replace(strDest, "&quot;", "\"");
        strDest = Replace(strDest, "&lt;", "<");
        strDest = Replace(strDest, "&gt;", ">");
        strDest = Replace(strDest, "&amp;", "&");
        return strDest;
    }

    public static String HtmlToText_Title(String strSource)
    {
    	strSource = StringUtil.getNullString(strSource);
    	String strDest = "";
        strDest = Replace(strSource, "\"", "&quot;");
        return strDest;
    }

    public static String Replace(String strSource, String strFrom, String strTo)
    {
    	strSource = StringUtil.getNullString(strSource);
    	String strDest = "";
        int intFromLen = strFrom.length();
        int i;
        while((i = strSource.indexOf(strFrom)) != -1) 
        {
            strDest = strDest + strSource.substring(0, i);
            strDest = strDest + strTo;
            strSource = strSource.substring(i + intFromLen);
        }
        strDest = strDest + strSource;
        return strDest;
    }

    public static String TextToHtml(String strSource)
    {
    	strSource = StringUtil.getNullString(strSource);
    	String strDest = "";
        strDest = Replace(strSource, "&", "&amp;");
        strDest = Replace(strDest, "&amp;amp;", "&amp;");
        strDest = Replace(strDest, "'", "&apos;");
        strDest = Replace(strDest, "&amp;apos;", "&apos;");
        strDest = Replace(strDest, "&amp;quot;", "&quot;");
        strDest = Replace(strDest, "\"", "&quot;");
        strDest = Replace(strDest, "&amp;lt;", "&lt;");
        strDest = Replace(strDest, "<", "&lt;");
        strDest = Replace(strDest, "&amp;gt;", "&gt;");
        strDest = Replace(strDest, ">", "&gt;");
        strDest = Replace(strDest, "&amp;nbsp;", "&nbsp;");
        return strDest;
    }

    public static String TextToHtml2(String strSource)
    {
    	strSource = StringUtil.getNullString(strSource);
    	String strDest = "";
        strDest = Replace(strSource, "'", "''");
        return strDest;
    }

    @SuppressWarnings("unchecked")
	public static Vector getSubString(String str, char c)
    {
    	str = StringUtil.getNullString(str);
    	Vector v = new Vector();
        int thePre = 0;
        int theNext = str.indexOf(c);
        String tempEmp = "";
        for(; theNext >= 0; theNext = str.indexOf(c, thePre))
        {
            tempEmp = str.substring(thePre, theNext);
            v.add(tempEmp);
            thePre = theNext + 1;
        }

        if(theNext < 0)
        {
            tempEmp = str.substring(thePre, str.length());
            v.add(tempEmp);
        }
        return v;
    }
    
}