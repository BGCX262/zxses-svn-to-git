package com.lcweb.service.newsclient;

import java.util.HashMap;
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
import com.lcweb.commons.GlobalConst;
import com.lcweb.dao.newsclient.NewsClientDao;
import com.lcweb.dao.newsmanage.NewsManageDao;
import com.lcweb.service.base.BaseServiceImpl;
import com.lcweb.service.newsmanage.NewsManageService;

public class NewsClientServiceImpl extends BaseServiceImpl implements NewsClientService {
	private NewsClientDao newsClientDao;
	private NewsManageDao newsManageDao;

	public void setNewsClientDao(NewsClientDao newsClientDao) {
		this.newsClientDao = newsClientDao;
	}

	public void setNewsManageDao(NewsManageDao newsManageDao) {
		this.newsManageDao = newsManageDao;
	}

	private NewsManageService newsManageService;

	public void setNewsManageService(NewsManageService newsManageService) {
		this.newsManageService = newsManageService;
	}

	/*
	 * public List<NewsItemNavigation> queryAllNewsItemNavigation() { return
	 * newsManageDao.queryAllNewsItemNavigation(new HashMap()); }
	 */
	/*
	 * the common method for get news item big list
	 */
	private List<NewsItemBig> queryAllNewsItemBig(Map para) {
		return newsManageDao.queryAllNewsItemBig(para);
	}

	/*
	 * get news item big object by classId
	 */
	public NewsItemBig getNewsItemBigByClassId(String classId) {
		Map para = new HashMap();
		para.put("classId", classId);
		List<NewsItemBig> itemBigList = queryAllNewsItemBig(para);
		if (itemBigList.size() > 0) {
			return itemBigList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public NewsItemBig getNewsItemBigByClassId(String classId, String ifDisplay) {
		Map hmPara = new HashMap();
		hmPara.put("classId", classId);
		hmPara.put("ifDisplay", GlobalConst.IS_DISPLAY);
		List<NewsItemBig> itemBigList = queryAllNewsItemBig(hmPara);
		if (itemBigList.size() > 0) {
			return (itemBigList.get(0));
		} else {
			return null;
		}
	}

	/*
	 * get list for news item big ex. get top 10 news item big
	 */
	public List<NewsItemBig> queryAllNewsItemBigForPage(Map paraMap, int frist, int max) {
		return newsClientDao.queryAllNewsItemBigForPage(paraMap, frist, max);
	}

	/*
	 * get list for news item big by yxdm(school code)
	 * 
	 */
	public List<NewsItemBig> queryAllNewsItemBigByYxdm(String yxdm, String ifDisplay, int ifIndex) {
		return newsManageService.queryNewsItemBigByYxdm(yxdm, ifDisplay, ifIndex);
	}

	/*
	 * get top records news for news item big
	 */
	public List<NewsContentManage> queryNewsContentManagerByClassIdForPage(String classId, int frist, int max) {
		Map paraMap = new HashMap();
		paraMap.put("classId", classId);
		paraMap.put("checkFlag", GlobalConst.IS_CHECKED);
		paraMap.put("ifDisplay", GlobalConst.IS_DISPLAY);
		return newsClientDao.queryAllNewsContentManagerForPage(paraMap, frist, max);
	}

	/*
	 * feng get small contents for page by typeId
	 */
	public List<NewsContentManage> queryNewsContentManagerByTypeIdForPage(Integer typeId, int frist, int max) {
		Map paraMap = new HashMap();
		paraMap.put("typeId", typeId);
		paraMap.put("checkFlag", GlobalConst.IS_CHECKED);
		paraMap.put("ifDisplay", GlobalConst.IS_DISPLAY);
		return newsClientDao.queryAllNewsContentManagerForPage(paraMap, frist, max);
	}

	/*
	 * get news item small object by ID
	 */
	@SuppressWarnings("unchecked")
	public NewsItemSmall getNewsItemSmallByTypeId(int typeId) {
		Map paraMap = new HashMap();
		paraMap.put("typeId", typeId);
		List<NewsItemSmall> itemSmallList = newsManageService.queryAllNewsItemSmall(paraMap);
		if (itemSmallList.size() > 0) {
			return itemSmallList.get(0);
		}
		return null;
	}

	public String queryAllNumber(){
		return newsClientDao.queryAllNumber();
	}
	
	/*
	 * get list news item small by class id
	 */

	public List queryNewsItemSmallByClassId(String classId, String ifDisplay) {
		return newsManageService.queryNewsItemSmallByClassId(classId, ifDisplay);
	}

	/*
	 * get page records for news item small by classId
	 */
	@SuppressWarnings("unchecked")
	public List queryNewsItemSmallPageByClassId(String classId, String ifDisplay, int frist, int max) {
		Map paraMap = new HashMap();
		paraMap.put("classId", classId);
		paraMap.put("ifDisplay", ifDisplay);
		return newsClientDao.queryAllNewsItemSmallForPage(paraMap, frist, max);
	}

	/*
	 * get news item config object by news item small object
	 */
	public NewsItemConfig getNewsItemConfigByNewsItemSmall(NewsItemSmall nis) {
		return (NewsItemConfig) (nis.getNewsContentManages().toArray()[0]);
	}

	/*
	 * get news content object by news id
	 */
	public NewsContentManage getNewsContentManageByNewsId(long newsId) {
		return newsManageDao.getNewsContentManageByNewsId(newsId);
	}

	/*
	 * get news content records by news item big id
	 */
	public List queryNewsContentManageByClassId(String classId) {
		Map paraMap = new HashMap();
		paraMap.put("classId", classId);
		paraMap.put("checkFlag", GlobalConst.IS_CHECKED);
		return newsManageService.queryAllNewsContentManage(paraMap);
	}

	/*
	 * get top news content records by news item big id
	 */
	public List queryNewsContentManageByClassIdForPage(String classId, int frist, int max) {
		Map paraMap = new HashMap();
		paraMap.put("classId", classId);
		paraMap.put("checkFlag", GlobalConst.IS_CHECKED);
		return newsClientDao.queryAllNewsContentManagerForPage(paraMap, frist, max);
	}

	/*
	 * get top news content records by news item small id
	 */
	public List queryNewsContentManageByTypeId(int typeId) {
		Map paraMap = new HashMap();
		paraMap.put("typeId", typeId);
		paraMap.put("checkFlag", GlobalConst.IS_CHECKED);
		return newsManageService.queryAllNewsContentManage(paraMap);
	}

	/*
	 * get list for news item navigation by classId
	 */
	public List<NewsItemNavigation> queryAllNewsItemNavigationByClassId(String classId) {
		Map paraMap = new HashMap();
		paraMap.put("classId", classId);
		return newsManageService.queryAllNewsNavigation(paraMap);
	}

	/*
	 * get school college department list
	 */
	public List getSchCollegeDepartment(Map paraMap) {
		return newsManageDao.getSchCollegeDepartment(paraMap);
	}

	/*
	 * get news ad manage object by class id and ad type tag
	 */
	@SuppressWarnings("unchecked")
	public NewsAdManage getNewsAdManageByClassIdAndAdTypeTag(String classId, String adTypeTag) {
		Map paraMap = new HashMap();
		paraMap.put("classId", classId);
		paraMap.put("adTypeTag", adTypeTag);
		List<NewsAdManage> newsAdManageList = newsManageService.queryAllNewsAdManage(paraMap);
		if (newsAdManageList.size() > 0) {
			return newsAdManageList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<NewsContentManage> getNewsManageByNewsTitle(String newsTitle, String classId) {
		Map map = new HashMap();
		map.put("newsTitle", newsTitle);
		map.put(classId, classId);
		return newsManageService.queryAllNewsContentManage(map);
	}

	@SuppressWarnings("unchecked")
	public List<NewsContentManage> getNewsContentManageByTypeIdForPage(int typeId, int first, int max) {
		Map paraMap = new HashMap();
		paraMap.put("typeId", typeId);
		paraMap.put("checkFlag", GlobalConst.IS_CHECKED);
		return newsClientDao.queryAllNewsContentManagerForPage(paraMap, first, max);
	}

	public List queryBasicDepartments() {
		return newsManageDao.getBasicDepartments();
	}

	public String getContentCountByDeptId(Long deptId) {
		return newsManageDao.getContentCountByDeptId(deptId);
	}
	
	public String getContentCountByDeptIdTime(Long deptId,String time) {
		return newsManageDao.getContentCountByDeptIdTime(deptId,time);
	}

	public List getHotArticle(Integer count, Long click) {
		return newsManageDao.getHotArticle(count, click);
	}
	
	public PageList find(Map<String, Object> conditionMap, String keyword) throws Exception {
		String currentPage = null;
		String path = null;
		String pageSize = "10";
		StringBuffer sql = new StringBuffer();
		StringBuffer count = new StringBuffer();
		sql.append("from NewsContentManage as ncm");
		sql.append(" where ncm.newsContent like '%" + keyword + "%'");
		count.append("select count(newsId) " + sql);
		if (conditionMap.get("currentPage") != null) {
			currentPage = conditionMap.get("currentPage").toString();
		}
		if (conditionMap.get("path") != null) {
			path = conditionMap.get("path").toString();
		}
		if (conditionMap.get("pagesize") != null) {
			pageSize = conditionMap.get("pagesize").toString();
		}
		sql.append(" order by ncm.newsDate desc ");
		String form = conditionMap.get("form") + "";
		return PageList.page(count.toString(), sql.toString(), currentPage, pageSize, path, this, form);
	}
	public PageList find(Map<String, Object> conditionMap) throws Exception {
		String currentPage = null;
		String path = null;
		String pageSize = "31";
		StringBuffer sql = new StringBuffer();
		StringBuffer count = new StringBuffer();
		sql.append("from Duty as d ");
		count.append("select count(id) " + sql);
		if (conditionMap.get("currentPage") != null) {
			currentPage = conditionMap.get("currentPage").toString();
		}
		if (conditionMap.get("path") != null) {
			path = conditionMap.get("path").toString();
		}
		if (conditionMap.get("pagesize") != null) {
			pageSize = conditionMap.get("pagesize").toString();
		}
		sql.append(" order by d.dutyDate ");
		String form = conditionMap.get("form") + "";
		return PageList.page(count.toString(), sql.toString(), currentPage, pageSize, path, this, form);
	}
	public List<BlogUsersVO> findBlogUsers(String displayCount) throws Exception {
		return newsManageDao.getBlogUsers(displayCount);
	}
}
