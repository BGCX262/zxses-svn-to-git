package com.lcweb.dao.newsmanage;

import java.util.List;
import java.util.Map;

import com.lcweb.bean.pojo.BlogUsersVO;
import com.lcweb.bean.pojo.NewsContentManage;
import com.lcweb.dao.base.BaseDao;

public interface NewsManageDao extends BaseDao {
	public List queryAllNewsItemBig(Map paraMap);

	public String getNewsItemBigForSelect(List list, String class_id);

	public List queryAllNewsItemSmall(Map paraMap);

	public String getNewsItemSmallForSelect(List list, int id);

	/*
	 * get all news Navigation list
	 */
	public List queryAllNewsItemNavigation(Map paraMap);

	/*
	 * get news Navigation list by big item
	 */
	public List queryAllNewsItemNavigationByClassId(String classId);

	public List getSchCollegeDepartment(Map paraMap);

	/*
	 * get NewsContentManage Object by Id
	 */
	public NewsContentManage getNewsContentManageByNewsId(long newsId);

	/*
	 * get NewsContentManage list
	 */
	public List queryNewsContentManage(Map paraMap);
	
	public int getMaxOrderIdByTableName(Map paraMap);

	public String querySysModule(Map param);

	public List queryAllSysModule(Map paraMap);

	public List queryModule(Map map);

	public List getSpecialDepartBigByYxdm(String yxdm);

	public List getSpecialDepartBigs();

	public List getBasicDepartments();

	public String getContentCountByDeptId(Long deptId);

	public String getContentCountByDeptIdTime(Long deptId, String time);

	public List getHotArticle(Integer count, Long click);
	
	public List<BlogUsersVO> getBlogUsers(String displayCount);
}
