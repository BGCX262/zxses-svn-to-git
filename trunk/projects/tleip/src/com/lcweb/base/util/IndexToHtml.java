/** 
 *@Company: 深圳市龙创软件有限公司
 *@Copyright: Copyright (c) lcsoft 2009-2011
 *
 */
package com.lcweb.base.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lcweb.bean.pojo.HtmlFileParameter;
import com.lcweb.commons.GlobalConst;

/**
 * 
 * @Title: ProIndexToHtml.java
 * @Description:
 * @Author: feng
 * @Time: Mar 9, 2011
 */
public class IndexToHtml {

	/**
	 * 
	 * @Description:环境路径
	 * 
	 */
	public static String getBasePath(HttpServletRequest request) {
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
		return basePath;
	}

	/**
	 * 
	 * @Description:工程路径
	 * 
	 */
	public static String getWorkPath() {
		HtmlFileParameter htmlFileParameter = new HtmlFileParameter();
		String workPath = htmlFileParameter.getOutputPath();
		return workPath;
	}

	/**
	 * 
	 * @Description:首页静态页面
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void createIndexHtml(HttpServletRequest request) {
		String workPath = request.getSession().getServletContext().getRealPath("/");
		String basePath = getBasePath(request);
		Map<String, Object> paraMap = new HashMap();
		
		// HTML_ROLLIMAGE
//		paraMap.put(GlobalConst.HTML_ROLLIMAGE, basePath + "client/newsClient.do?method=queryRollImage");
		// HTML_INDEXBANNER
		paraMap.put(GlobalConst.HTML_INDEXBANNER, basePath + "client/newsClient.do?method=queryIndexBanner");
		// HTML_LOGIN
		paraMap.put(GlobalConst.HTML_LOGIN, basePath + "client/newsClient.do?method=queryLoginForStatic");
		// HTML_TABS
		paraMap.put(GlobalConst.HTML_TABS, basePath + "client/newsClient.do?method=queryTabs");
		// HTML_TABSPIC
		paraMap.put(GlobalConst.HTML_TABSPIC, basePath + "client/newsClient.do?method=queryTabspic");
		// HTML_FRIENDLINK
		paraMap.put(GlobalConst.HTML_FRIENDLINK, basePath + "client/newsClient.do?method=queryFriendLink");
		// HTML_SKYSTARS
		paraMap.put(GlobalConst.HTML_SKYSTARS, basePath + "client/newsClient.do?method=querySkyStars");
		// HTML_HEADMASTERS
		paraMap.put(GlobalConst.HTML_HEADMASTERS, basePath + "client/newsClient.do?method=queryHeadmasters");
		// HTML_LIB
		paraMap.put(GlobalConst.HTML_LIB, basePath + "client/newsClient.do?method=queryLib");
		// HTML_LIB
//		paraMap.put(GlobalConst.HTML_CONTACT, basePath + "client/newsClient.do?method=queryContact");
		// HTML_LIB
		paraMap.put(GlobalConst.HTML_INDEXCONTACT, basePath + "client/newsClient.do?method=queryIndexContact");
		
		// 顶部菜单
		paraMap.put(GlobalConst.HTML_MENU, basePath + "client/newsClient.do?method=queryMenu");
		
		// 底部
		paraMap.put(GlobalConst.HTML_FOOT_INFO, basePath + "client/newsClient.do?method=queryFootInfo");

		// 创建首页
		String indexOutputPath = workPath + "/";
		String indexTemplateFilePath = workPath + ("template/index_template.html");
		HtmlFileParameter indexFileParameter = new HtmlFileParameter();
		indexFileParameter.setOutputPath(indexOutputPath);
		indexFileParameter.setTemplateFilePath(indexTemplateFilePath);
		indexFileParameter.setBasePath(basePath);
		indexFileParameter.setAction(paraMap);
		try {
			HtmlFile.createIndexHtmfile(indexFileParameter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description:createIndexHtmlByYxdm
	 * 
	 */
	public static void createIndexHtmlByYxdm(HttpServletRequest request, String yxdm) {
//		if (yxdm.equalsIgnoreCase(GlobalConst.SchoolCode)) {
			IndexToHtml.createIndexHtml(request);
//		}
	}
}
