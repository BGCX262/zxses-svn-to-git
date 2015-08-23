package com.lcweb.service.newsmanage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.Monitor;
import com.lcweb.bean.pojo.NewsAdManage;
import com.lcweb.bean.pojo.NewsContentManage;
import com.lcweb.bean.pojo.NewsItemBig;
import com.lcweb.bean.pojo.NewsItemSmall;
import com.lcweb.bean.pojo.SysModule;
import com.lcweb.service.base.BaseService;

public interface NewsManageService extends BaseService {

	// -----------------------news item big start ----------------------
	public String addNewsItemBig(NewsItemBig nib);

	public String AmendNewsItemBig(NewsItemBig nib, SysModule module);

	@SuppressWarnings("unchecked")
	public List queryAllNewsItemBig(Map hmPara);

	public NewsItemBig getNewsItemBigByClassId(String classId);

	public String createPropertyTree(String xxdm, String yxdm, String basePath);
	public String createZTree(String yxdm, String basePath);

	// public List queryNewsItemBigByYxdm(String yxdm);
	@SuppressWarnings("unchecked")
	public List queryNewsItemBigByYxdm(String yxdm, String ifDisplay, int ifIndex);

	public boolean checkUseedForNewsItemsBig(NewsItemBig nib);

	public boolean isExistsNewsItemBi(String classId);

	public String getNewsItemBigSelectForOrderByYxdm(String yxdm, String ifDisplay, int ifIndex, String value);

	public NewsItemBig getDefaultNewsItemBigByClassId(String yxdm, String classId, BasicPerson basicPerson);

	public NewsItemBig getDefaultNewsItemBigByClassId(String yxdm, String classId);

	/*
	 * get news item big for index page
	 */
	@SuppressWarnings("unchecked")
	public List queryNewsItemBigForFirstPage(String yxdm);

	/*
	 * get news item big for other page
	 */
	@SuppressWarnings("unchecked")
	public List queryNewsItemBigForNotFirstPage(String yxdm);

	/*
	 * get news item big selected list string by colleage code and selected
	 * value
	 */
	public String getNewsItemBigSelectByYxdm(String yxdm, String ifDisplay, int ifIndex, String value, String preString);

	public String getNewsItemBigSelectByYxdm(String yxdm, String ifDisplay, int ifIndex, String value);

	public String getNewsItemBigSelectByYxdms(String yxdm, String ifDisplay, String value);

	// -----------------------news item big end ----------------------

	// -----------------------news item small start ----------------------
	@SuppressWarnings("unchecked")
	public List queryAllNewsItemSmall(Map paraMap);

	/*
	 * get news item small object by ID
	 */
	public NewsItemSmall getNewsItemSmallByTypeId(int typeId);

	@SuppressWarnings("unchecked")
	public List queryNewsItemSmallByClassId(String classId, String ifDisplay);

	public boolean checkUseedForNewsItemSmall(NewsItemSmall nis);

	public String AddNewsItemSmall(NewsItemSmall nis);

	public String AmendNewsItemSmall(NewsItemSmall nis, SysModule module);

	public boolean isExistsNewsItemSmall(String classId, String typeName);

	public String getNewsItemSmallSelectByClassId(String classId, String ifDisplay, String value, String preString);

	public String getNewsItemSmallSelectByClassId(String classId, String ifDisplay, String value);

	public String getNewsItemSmallSelectByClassId(String classId, String ifDisplay);

	public String getNewsItemSmallSelectForOrderByClassId(String classId, String value);

	// -----------------------news item small end ----------------------

	// -----------------------news ad type start ----------------------
	@SuppressWarnings("unchecked")
	public List queryAllNewsAdType(Map paraMap);

	@SuppressWarnings("unchecked")
	public List queryAllNewsAdTypeByClassId(String classId);

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsAdType(Map paraMap);

	public boolean isExistsNewsAdTypeByName(String adTypeName);

	public String getNewsAdTypeSelectByClassId(String classId, String value, String preString);

	public String getNewsAdTypeSelectByClassId(String classId, String value);

	public String getNewsAdTypeSelectByClassId(String classId);

	// -----------------------news ad type end ----------------------

	// -----------------------news ad manage start ----------------------
	@SuppressWarnings("unchecked")
	public List queryAllNewsAdManage(Map paraMap);

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsAdManage(Map paraMap);

	public boolean isExistsNewsAdManageByName(String adName);

	// -----------------------news ad manage end ----------------------
	// -----------------------News Content Manage start --------------------
	@SuppressWarnings("unchecked")
	public List queryAllNewsContentManage(Map paraMap);

	@SuppressWarnings("unchecked")
	public List queryNewsContentManageByTypeId(int typeId);

	@SuppressWarnings("unchecked")
	public List queryNewsContentManageByTypeIdAndCheckFlag(int typeId, String checkFlag);

	@SuppressWarnings("unchecked")
	public List queryNewsContentManageByNewsId(int newsId);

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsContentManage(Map paraMap);

	public boolean isExistsNewsContentManageByTitle(String newsTitle);

	public boolean isExistsNewsContentManageByKeyword(String newsKeyword);

	// -----------------------News Content Manage end --------------------
	// -----------------------News Content Manage start --------------------
	@SuppressWarnings("unchecked")
	public List queryAllNewsNavigation(Map paraMap);

	@SuppressWarnings("unchecked")
	public List queryAllNewsItemNavigationByClassId(String classId);

	public String getNewsNavigationSelectForOrderByClassId(String classId, String value);

	// -----------------------News Content Manage end --------------------

	/*
	 * get selected date format string
	 */
	public String getDateFormatString(String value);

	/*
	 * get max order id by table name
	 */
	@SuppressWarnings("unchecked")
	public int getMaxOrderIdByTableName(Map paraMap);

	/**
	 * Create the tree by permission
	 * 
	 * @param schoolCode
	 *            The school 's code
	 * @param collegeCode
	 *            The College's code
	 * @return The Tree's Structure
	 */
	public String createPropertyTreeByModule(String schoolCode, String collegeCode, BasicPerson person);

	public String createPropertyTreeByModule(String schoolCode, String collegeCode, HttpServletRequest request);

	@SuppressWarnings("unchecked")
	public String querySysModule(Map param);

	public String addSmallItem(String className, NewsItemSmall nis, String classId, boolean isFirst, String yxdm);

	public String addSecondBig(NewsItemBig nib, String yxModuleId);

	public void deleteSmallAndModule(NewsItemSmall nis);

	public void deleteBigAndModule(NewsItemBig nib);

	public void updateSmallAndModuel(NewsItemSmall nis);

	public String updateBigAndModuel(NewsItemBig nib);

	@SuppressWarnings("unchecked")
	public List queryModule(Map map);

	@SuppressWarnings("unchecked")
	public List queryNewsContentManagerByYxdmForPage(String classId, int ifIndex);

	public void saveAdManage(NewsAdManage adManage, String yxdm);

	public void updateAdMange(NewsAdManage adManage, String yxdm);

	public String getNewsItemBigSelectByYxdm(String yxdm, String ifHaveAd, String value);

	public void saveNewsContent(NewsContentManage news);
	
	public void saveMonitor(Monitor monitor);

	@SuppressWarnings("unchecked")
	public List queryNewsItemBigByYxdms(String yxdm, String ifDisplay);

	public String getSpecialDepartBigSelectByYxdm(String yxdm, String classId);

	/* 
	 *  根据yxdm查询新闻
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List queryNewsContentManageByYxdm(String yxdm);
}
