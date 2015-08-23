package com.lcweb.dao.newsmanage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Query;

import com.lcweb.bean.pojo.BlogUsersVO;
import com.lcweb.bean.pojo.NewsContentManage;
import com.lcweb.bean.pojo.NewsItemBig;
import com.lcweb.bean.pojo.NewsItemSmall;
import com.lcweb.dao.base.BaseDaoImpl;

public class NewsManageDaoImpl extends BaseDaoImpl implements NewsManageDao {
	private NewsManageDao newsManageDao;

	public NewsManageDao getNewsManageDao() {
		return newsManageDao;
	}

	public void setNewsManageDao(NewsManageDao newsManageDao) {
		this.newsManageDao = newsManageDao;
	}

	public List queryAllNewsItemBig(Map hmPara) {
		return findListByHSQLId("queryAllNewsItemBig", hmPara);
	}

	public String getNewsItemBigForSelect(List list, String class_id) {
		StringBuffer sb = new StringBuffer();
		if (list == null) {
			return "";
		}
		for (int i = 0; i < list.size(); i++) {
			NewsItemBig nib = (NewsItemBig) list.get(i);
			sb.append("<option value='" + nib.getClassId() + "'");
			if (nib.getClassId().equalsIgnoreCase(class_id)) {
				sb.append("selected='selected'");
			}
			sb.append(">" + nib.getClassName() + "</option>");
		}
		return sb.toString();
	}

	public List queryAllNewsItemSmall(Map hmPara) {
		return findListByHSQLId("queryAllNewsItemSmall", hmPara);
	}

	public String getNewsItemSmallForSelect(List list, int id) {
		StringBuffer sb = new StringBuffer();
		if (list == null) {
			return "";
		}
		Integer intID = new Integer(id);
		for (int i = 0; i < list.size(); i++) {
			NewsItemSmall nis = (NewsItemSmall) list.get(i);
			sb.append("<option value='" + nis.getTypeId() + "'");
			if (nis.getTypeId().equals(intID)) {
				sb.append("selected='selected'");
			}
			sb.append(">" + nis.getTypeName() + "</option>");
		}
		return sb.toString();
	}

	/*
	 * get news Navigation list by big item
	 */
	public List queryAllNewsItemNavigation(Map hmPara) {
		return findListByHSQLId("queryAllNewsNavigation", hmPara);
	}

	/*
	 * get Navigation list by big item
	 */
	public List queryAllNewsItemNavigationByClassId(String classId) {
		Map hmPara = new HashMap();
		hmPara.put("classId", classId);
		return queryAllNewsItemNavigation(hmPara);
	}

	public List getSchCollegeDepartment(Map hmPara) {
		return findListByHSQLId("getSchCollegeDepartment", hmPara);
	}

	/*
	 * get NewsContentManage Object by Id
	 */
	public NewsContentManage getNewsContentManageByNewsId(long newsId) {
		Map hmPara = new HashMap();
		hmPara.put("newsId", newsId);
		List l = queryNewsContentManage(hmPara);
		if (l.size() > 0) {
			return (NewsContentManage) l.get(0);
		}
		return null;
	}

	/*
	 * get NewsContentManage list
	 */
	public List queryNewsContentManage(Map hmPara) {
		return findListByHSQLId("queryAllNewsContentManage", hmPara);
	}

	/*
	 * get max order id by table name
	 */
	public int getMaxOrderIdByTableName(Map paraMap) {
		List l = findListByHSQLId("getMaxOrderIdByTableName", paraMap);
		if (l.size() > 0) {
			if (l.get(0) != null) {
				return Integer.parseInt(l.get(0).toString()) + 1;
			} else {
				return 1;
			}
		}
		return 0;
	}

	public String querySysModule(Map paraMap) {
		List list = findListByHSQLId("queryAllSysModule", paraMap);
		if (list.size() > 0) {
			if (list.get(0) != null) {
				return list.get(0).toString();
			}
		}
		return null;
	}

	public List queryAllSysModule(Map paraMap) {
		return findListByHSQLId("queryAllSysModule", paraMap);
	}

	public List queryModule(Map map) {
		List list = findListByHSQLId("querySysModule", map);
		if ((list.size() > 0) && (null != list)) {
			return list;
		}
		return null;
	}

	public List getSpecialDepartBigByYxdm(String yxdm) {
		Map map = new HashMap();
		map.put("yxdm", yxdm);
		return findListByHSQLId("queryAllNewsItemBig", map);
	}

	public List getSpecialDepartBigs() {
		String hql = "select c from NewsItemBig c,SchCollegeDepartment scd  where c.yxdm = scd.yxdm";
		return getHibernateTemplate().find(hql);

	}

	public List getBasicDepartments() {
		String hql = "select bd from BasicDepartment bd";
		return getHibernateTemplate().find(hql);

	}

	@SuppressWarnings("unchecked")
	public String getContentCountByDeptId(Long deptId) {
		String hql = "select count(ncm.announcePerson) from NewsContentManage ncm,BasicPerson bp"
				+ " where bp.personAccount = ncm.announcePerson and bp.basicDepartment.deptId="
				+ deptId;
		List list = getHibernateTemplate().find(hql);
		if (list.size() > 0) {
			if (list.get(0) != null) {
				return list.get(0).toString();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getContentCountByDeptIdTime(Long deptId, String time) {
		String starttime = "";
		String endtime = "";
		StringBuffer sb = new StringBuffer();
		sb.append("select count(ncm.newsId) from NewsContentManage ncm");
		sb.append(" where ncm.department.deptId =" + deptId);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if ((time != null) && !"".equals(time) && !"all".equals(time)) {
			if ("week".equals(time)) {
				Calendar calendar3 = Calendar.getInstance();
				calendar3.set(Calendar.DAY_OF_WEEK, 2);
				starttime = sdf.format(calendar3.getTime());
				calendar3.add(Calendar.WEEK_OF_YEAR, 1);
				calendar3.set(Calendar.DAY_OF_WEEK, 1);
				endtime = sdf.format(calendar3.getTime());
			}
			if ("month".equals(time)) {
				Calendar calendar1 = Calendar.getInstance();
				calendar1.set(Calendar.DATE, 1);
				starttime = sdf.format(calendar1.getTime());
				calendar1.add(Calendar.MONTH, 1);
				calendar1.add(Calendar.DATE, -1);
				endtime = sdf.format(calendar1.getTime());
			}
			if ("halfyear".equals(time)) {
				Calendar calendar4 = Calendar.getInstance();
				Integer nowMonth = calendar4.get(Calendar.MONTH) + 1;
				if (nowMonth > 6) {
					calendar4.set(Calendar.MONTH, 6);
					calendar4.set(Calendar.DATE, 1);
					starttime = sdf.format(calendar4.getTime());
					calendar4.set(Calendar.MONTH, 12);
					calendar4.add(Calendar.DATE, -1);
					endtime = sdf.format(calendar4.getTime());
				}

				else {
					calendar4.set(Calendar.MONTH, 0);
					calendar4.set(Calendar.DATE, 1);
					starttime = sdf.format(calendar4.getTime());
					calendar4.add(Calendar.MONTH, 6);
					calendar4.add(Calendar.DATE, -1);
					endtime = sdf.format(calendar4.getTime());
				}
			}
			if ("year".equals(time)) {

				Calendar calendar2 = Calendar.getInstance();
				calendar2.set(Calendar.MONDAY, 0);
				calendar2.set(Calendar.DATE, 1);
				starttime = sdf.format(calendar2.getTime());
				calendar2.set(Calendar.MONDAY, 11);
				calendar2.set(Calendar.DATE, 31);
				endtime = sdf.format(calendar2.getTime());

			}
			sb.append(" and ncm.newsDate between '" + starttime + "' and '"
					+ endtime + "'");
		}
		List list = getHibernateTemplate().find(sb.toString());
		if (list.size() > 0) {
			if (list.get(0) != null) {
				return list.get(0).toString();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List getHotArticle(Integer count, Long click) {
		String hql = "from NewsContentManage ncm where ncm.announceType=0 order by ncm.visitCount desc";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(count);
		return query.list();
	}

	public List<BlogUsersVO> getBlogUsers(String displayCount) {
		Properties properties = new Properties();
		String url = null;
		String userName = null;
		String password = null;
		String basePath = null;
		try {
			properties.load(NewsManageDaoImpl.class
					.getResourceAsStream("/jdbc.properties"));// 获得属性文件
			String driverClassName = properties.get(
					"jdbc.mysql.driverClassName").toString();
			Class.forName(driverClassName).newInstance();
			url = properties.get("jdbc.mysql.url").toString();
			userName = properties.get("jdbc.mysql.username").toString();
			password = properties.get("jdbc.mysql.password").toString();
			basePath = properties.get("blog.basepath").toString();
		} catch (Exception e) {
			System.out.println("get dbc.mysql.driverClassName is error!!");
		}
		StringBuffer findUserSql = new StringBuffer();
		findUserSql.append("SELECT u.`id`, u.`full_name`, g.`file_name` ");
		findUserSql.append("FROM `lt_users` u left join `lt_gallery_resources` g on u.`resource_picture_id` = g.`id` ");
		findUserSql.append("LIMIT ");
		findUserSql.append(displayCount);
		StringBuffer blogSite = new StringBuffer(); 
		StringBuffer userPhotoPath = new StringBuffer(); 
		List<BlogUsersVO> blogUsers = new ArrayList<BlogUsersVO>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = java.sql.DriverManager.getConnection(url,
					userName, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(findUserSql.toString());
			while (resultSet.next()) {
				BlogUsersVO user = new BlogUsersVO();
				blogSite.append(basePath);
				blogSite.append("/index.php?blogId=");
				blogSite.append(resultSet.getString("id"));
				
				if(resultSet.getString("file_name")!=null){
					userPhotoPath.append(basePath);
					userPhotoPath.append("/gallery/");
					userPhotoPath.append(resultSet.getString("id"));
					userPhotoPath.append("/previews/");
					userPhotoPath.append(resultSet.getString("file_name"));
				}
				
				user.setBlogSite(blogSite.toString());
				user.setUserName(resultSet.getString("full_name"));
				user.setUserPhotoPath(userPhotoPath.toString());
				blogSite.delete(0, blogSite.length());
				userPhotoPath.delete(0, userPhotoPath.length());
				blogUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if((connection!=null) 
						&& (statement!=null) 
						&& (resultSet!=null)){
					// 关闭连接、释放资源
					resultSet.close();
					statement.close();
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return blogUsers;
	}
}
