package com.lcweb.service.newsclient;

import java.util.List;
import java.util.Map;

import com.lcweb.base.util.PageList;
import com.lcweb.bean.pojo.BlogUsersVO;
import com.lcweb.bean.pojo.NewsAdManage;
import com.lcweb.bean.pojo.NewsContentManage;
import com.lcweb.bean.pojo.NewsItemBig;
import com.lcweb.bean.pojo.NewsItemConfig;
import com.lcweb.bean.pojo.NewsItemNavigation;
import com.lcweb.bean.pojo.NewsItemSmall;
import com.lcweb.service.base.BaseService;

public interface NewsClientService extends BaseService {
	/*
	 * get news item big object by classId
	 */
	public NewsItemBig getNewsItemBigByClassId(String classId);

	/*
	 * get news item big object by classId,ifdiplay
	 */
	public NewsItemBig getNewsItemBigByClassId(String classId, String ifDisplay);

	/*
	 * get list for news item big ex. get top 10 news item big
	 */
	public List<NewsItemBig> queryAllNewsItemBigForPage(Map paraMap, int frist, int max);

	/*
	 * get list for news item big by yxdm(school code)
	 * 
	 */

	/*
	 * get top records news for news item big
	 */
	public List<NewsContentManage> queryNewsContentManagerByClassIdForPage(String classId, int frist, int max);

	/*
	 * feng ------------------------
	 */
	public List<NewsContentManage> queryNewsContentManagerByTypeIdForPage(Integer typeId, int frist, int max);

	/*
	 * get news item small object by ID
	 */
	public NewsItemSmall getNewsItemSmallByTypeId(int typeId);

	/*
	 * get list news item small by class id
	 */

	public List queryNewsItemSmallByClassId(String classId, String ifDisplay);

	/*
	 * get page records for news item small by classId
	 */
	public List queryNewsItemSmallPageByClassId(String classId, String ifDisplay, int frist, int max);

	/*
	 * get news item config object by news item small object
	 */
	public NewsItemConfig getNewsItemConfigByNewsItemSmall(NewsItemSmall nis);

	/*
	 * get news content object by news id
	 */
	public NewsContentManage getNewsContentManageByNewsId(long newsId);

	/*
	 * get news content records by news item big id
	 */
	public List queryNewsContentManageByClassId(String classId);

	/*
	 * get top news content records by news item big id
	 */
	public List queryNewsContentManageByClassIdForPage(String classId, int frist, int max);

	/*
	 * get top news content records by news item small id
	 */
	public List queryNewsContentManageByTypeId(int typeId);

	/*
	 * get list for news item navigation by classId
	 */
	public List<NewsItemNavigation> queryAllNewsItemNavigationByClassId(String classId);

	/*
	 * get school college department list
	 */
	public List getSchCollegeDepartment(Map paraMap);

	/*
	 * get ad manage object by class id and ad type tag
	 */
	public NewsAdManage getNewsAdManageByClassIdAndAdTypeTag(String classId, String adTypeTag);

	public List<NewsContentManage> getNewsManageByNewsTitle(String newsTitle, String classId);

	//
	// public NewsContentManage getNewsContentManageById(NewsContentManage
	// newsContent);

	// public NewsItemConfig queryAllNewsItemConfig(Map map);

	/**
	 * Get the newsContent of the count first-max;
	 */
	public List<NewsContentManage> getNewsContentManageByTypeIdForPage(int typeId, int first, int max);

	public List<NewsItemBig> queryAllNewsItemBigByYxdm(String yxdm, String ifDisplay, int ifIndex);

	public List queryBasicDepartments();

	public String queryAllNumber();
	
	public String getContentCountByDeptId(Long deptId);

	public String getContentCountByDeptIdTime(Long deptId, String time);

	public List getHotArticle(Integer count, Long click);

	public PageList find(Map<String, Object> conditionMap, String kewword) throws Exception;

	public PageList find(Map<String, Object> conditionMap) throws Exception;
	
	public List<BlogUsersVO> findBlogUsers(String displayCount) throws Exception ;
}
