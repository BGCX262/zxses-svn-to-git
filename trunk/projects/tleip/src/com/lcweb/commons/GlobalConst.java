package com.lcweb.commons;

import com.lcweb.base.util.StringUtil;

public class GlobalConst {
	public static final String UPLOAD_PATH = "client/upload/";
	//学校代码
	public static final String SchoolCode = "10000";
	public static final String SchoolName = "深圳市南山区塘朗小学";

	public static final String FirstPageMark = "FirstPage";
	public static final String IS_DISPLAY = "1";
	public static final String IS_NO_DISPLAY = "0";
	public static final String IS_ALL_DISPLAY = "2";

	public static final int IS_INDEX = 1;
	public static final int IS_NO_INDEX = 0;
	public static final int IS_ALL_INDEX = 2;

	//是否显示广告
	public static final String IS_HAVEAD = "1";
	public static final String IS_NO_HAVEAD = "0";
	public static final String IS_ALL_HAVEAD = "2";

	public static final String IS_CHECKED = "1";
	public static final String IS_NO_CHECHED = "0";

	public static final String NEWS_TEMPLATE_ONE = "1";
	public static final String NEWS_TEMPLATE_TWO = "2";
	public static final String NEWS_TEMPLATE_THREE = "3";

	//Set the html file encode
	public static final String ENCODE_UTF8 = "UTF-8";

	//首页部分
	public static final String HTML_ROLLIMAGE = "HTML_ROLLIMAGE"; // HTML_ROLLIMAGE
	public static final String HTML_INDEXBANNER = "HTML_INDEXBANNER"; // HTML_ROLLIMAGE
	public static final String HTML_LOGIN = "HTML_LOGIN"; // LOGIN
	public static final String HTML_LIB = "HTML_LIB"; // HTML_LIB
	public static final String HTML_TABS = "HTML_TABS"; // HTML_TABS
	public static final String HTML_TABSPIC = "HTML_TABSPIC"; // HTML_TABSPIC
	public static final String HTML_FRIENDLINK = "HTML_FRIENDLINK"; // HTML_FRIENDLINK
	public static final String HTML_SKYSTARS = "HTML_SKYSTARS"; // HTML_SKYSTARS
	public static final String HTML_HEADMASTERS = "HTML_HEADMASTERS"; // HTML_HEADMASTERS
	public static final String HTML_CONTACT = "HTML_CONTACT"; // HTML_CONTACT
	public static final String HTML_INDEXCONTACT = "HTML_INDEXCONTACT"; // HTML_INDEXCONTACT
	
	//公共部分
	public static final String HTML_MENU = "HTML_MENU"; //顶部菜单
	public static final String HTML_FOOT_INFO = "HTML_FOOT_INFO"; //页面底部
	
	//信息展示部分
	public static final String HTML_LEFT = "HTML_LEFT"; //左列菜单
	
	
	public static final String TIME_PATTERN = "yyyy-MM-dd";
	public static final String ADMIN = "admin";

	public static String getFirstPageMarkBySchoolCode(String xxdm) {
		xxdm = StringUtil.getNullString(xxdm);
		if (xxdm.equalsIgnoreCase(SchoolCode)) {
			return SchoolCode;
		}
		return null;
	}

	public static String getSchoolName(String xxdm) {
		xxdm = StringUtil.getNullString(xxdm);
		if (xxdm.equalsIgnoreCase(SchoolCode)) {
			return SchoolName;
		}
		return null;
	}

	public static String getSchoolSelect(String xxdm) {
		xxdm = StringUtil.getNullString(xxdm);
		StringBuffer sb = new StringBuffer();
		sb.append("<option value='0'>===全部===</option>\n");
		sb.append("<option value='" + SchoolCode + "' " + (xxdm.equalsIgnoreCase(SchoolCode) ? "selected" : "") + ">"
				+ SchoolName + "</option>\n");
		return sb.toString();
	}
}
