package com.lcweb.service.newsmanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import com.lcpf.framework.pojo.ModuleBean;
import com.lcpf.framework.pojo.ModuleCollection;
import com.lcweb.base.util.StringUtil;
import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.Monitor;
import com.lcweb.bean.pojo.NewsAdManage;
import com.lcweb.bean.pojo.NewsAdType;
import com.lcweb.bean.pojo.NewsContentManage;
import com.lcweb.bean.pojo.NewsItemBig;
import com.lcweb.bean.pojo.NewsItemNavigation;
import com.lcweb.bean.pojo.NewsItemSmall;
import com.lcweb.bean.pojo.SysModule;
import com.lcweb.commons.GlobalConst;
import com.lcweb.dao.newsmanage.NewsManageDao;
import com.lcweb.service.base.BaseServiceImpl;
import com.lcweb.service.org.NewsOrgService;

public class NewsManageServiceImpl extends BaseServiceImpl implements NewsManageService {

	private static final String TREE_SEPARATOR = "|";
	private NewsManageDao newsManageDao;
	private NewsItemBig newsItemBig;
	private NewsOrgService newsOrgService;

	public NewsItemBig getNewsItemBig() {
		return newsItemBig;
	}

	public NewsOrgService getNewsOrgService() {
		return newsOrgService;
	}

	public void setNewsOrgService(NewsOrgService newsOrgService) {
		this.newsOrgService = newsOrgService;
	}

	public static int getItemIndex() {
		return itemIndex;
	}

	public static void setItemIndex(int itemIndex) {
		NewsManageServiceImpl.itemIndex = itemIndex;
	}

	public void setNewsItemBig(NewsItemBig newsItemBig) {
		this.newsItemBig = newsItemBig;
	}

	public void setNewsManageDao(NewsManageDao newsManageDao) {
		this.newsManageDao = newsManageDao;
	}

	public String getNewsItemBigSelectByYxdm(String yxdm, String ifDisplay, int ifIndex, String value) {
		return getNewsItemBigSelectByYxdm(yxdm, ifDisplay, ifIndex, value, "");
	}

	public String getNewsItemBigSelectByYxdms(String yxdm, String ifDisplay, String value) {
		return getNewsItemBigSelectByYxdms(yxdm, ifDisplay, value, "");
	}

	public void saveAdManage(NewsAdManage adManage, String yxdm) {
		saveObject(adManage);
		updateDisplayAdManage(adManage, yxdm);
	}

	public void updateAdMange(NewsAdManage adManage, String yxdm) {
		updateObject(adManage);
		updateDisplayAdManage(adManage, yxdm);
	}

	@SuppressWarnings("unchecked")
	public void saveNewsContent(NewsContentManage news) {
		newsManageDao.saveObject(news);
	}
	@SuppressWarnings("unchecked")
	public void saveMonitor(Monitor monitor) {
		newsManageDao.saveObject(monitor);
	}

	public void setNewsItemBigByModule(SysModule module) {
		String url = module.getUrl();
		String elements[] = url.split("&");
		String classId = null;
		for (String value : elements) {
			if (value.startsWith("classId")) {
				String entry[] = value.split("=");
				for (String key : entry) {
					if (key != "classId") {
						classId = key;
					}
				}
			}
		}
		this.newsItemBig = getNewsItemBigByClassId(classId);
	}

	// -----------------------NewsItemBig end ----------------------
	@SuppressWarnings("unchecked")
	public List queryAllNewsItemBig(Map hmPara) {
		return this.newsManageDao.queryAllNewsItemBig(hmPara);
	}

	@SuppressWarnings("unchecked")
	public NewsItemBig getNewsItemBigByClassId(String classId) {
		Map hmPara = new HashMap();
		hmPara.put("classId", classId);
		List<NewsItemBig> itemBigList = queryAllNewsItemBig(hmPara);
		if (itemBigList.size() > 0) {
			return (itemBigList.get(0));
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public NewsItemBig getDefaultNewsItemBigByClassId(String collegeCode, String classId, BasicPerson basicPerson) {
		if (!StringUtil.getNullString(classId).equalsIgnoreCase("")) {
			return getNewsItemBigByClassId(classId);
		} else {
			List moduleList = getModuleList(basicPerson, collegeCode);
			if (moduleList.size() > 0) {
				SysModule module = (SysModule) moduleList.get(0);
				for (Iterator subIterator = module.getChildModules().iterator(); subIterator.hasNext();) {
					SysModule subModule = (SysModule) subIterator.next();
					if (subModule.getChildModules().size() > 0) {
						setNewsItemBigByModule(subModule);
						break;
					}
				}
			}
			return getNewsItemBig();
		}
	}

	@SuppressWarnings("unchecked")
	public NewsItemBig getDefaultNewsItemBigByClassId(String yxdm, String classId) {
		if (!StringUtil.getNullString(classId).equalsIgnoreCase("")) {
			return getNewsItemBigByClassId(classId);
		} else {
			Map hmPara = new HashMap();
			hmPara.put("ifIndex", GlobalConst.IS_INDEX);// 当单击新闻管理菜单时。
			hmPara.put("yxdm", yxdm);
			List l = this.newsManageDao.findPageByHSQLId("queryAllNewsItemBig", hmPara, 0, 1);
			if (l.size() > 0) {
				return (NewsItemBig) l.get(0);
			} else
				return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List queryNewsItemBigByYxdm(String yxdm, String ifDisplay, int ifIndex) {
		Map hmPara = new HashMap();
		hmPara.put("yxdm", yxdm);
		if (ifIndex < 2) {
			hmPara.put("ifIndex", ifIndex);
		}
		if (StringUtil.getNullInt(ifDisplay) < 2) {
			hmPara.put("ifDisplay", ifDisplay);
		}
		return queryAllNewsItemBig(hmPara);
	}

	@SuppressWarnings("unchecked")
	public List queryNewsItemBigByYxdms(String yxdm, String ifDisplay) {
		Map hmPara = new HashMap();
		hmPara.put("yxdm", yxdm);
		if (StringUtil.getNullInt(ifDisplay) < 2) {
			hmPara.put("ifDisplay", ifDisplay);
		}
		return queryAllNewsItemBig(hmPara);
	}

	/*
	 * get news item big for index page
	 */
	@SuppressWarnings("unchecked")
	public List queryNewsItemBigForFirstPage(String yxdm) {
		Map hmPara = new HashMap();
		hmPara.put("isFirstPage", yxdm);
		hmPara.put("ifIndex", GlobalConst.IS_INDEX);
		return queryAllNewsItemBig(hmPara);
	}

	/*
	 * get news item big for other page
	 */
	@SuppressWarnings("unchecked")
	public List queryNewsItemBigForNotFirstPage(String yxdm) {
		Map hmPara = new HashMap();
		hmPara.put("isNotFirstPage", yxdm);
		hmPara.put("ifIndex", GlobalConst.IS_NO_INDEX);
		return queryAllNewsItemBig(hmPara);
	}

	/*
	 * get news item big selected list string by colleage code and selected
	 * value
	 */
	@SuppressWarnings("unchecked")
	public String getNewsItemBigSelectByYxdm(String yxdm, String ifDisplay, int ifIndex, String value, String preString) {
		List newsBigItems = queryNewsItemBigByYxdms(yxdm, ifDisplay);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='0'>==== 全 部 ====</option>\n");
		for (Iterator bigItemIterator = newsBigItems.iterator(); bigItemIterator.hasNext();) {
			NewsItemBig bigItem = (NewsItemBig) bigItemIterator.next();
			buffer.append("<option value='" + bigItem.getClassId() + "'");
			if (value != null && bigItem.getClassId().equalsIgnoreCase(value)) {
				buffer.append(" selected='selected' ");
			}
			buffer.append(">");
			buffer.append(preString + bigItem.getClassName() + "</option>\n");
		}
		return buffer.toString();
	}

	/*
	 * getSpecialDepartBigSelectByYxdm
	 */
	@SuppressWarnings("unchecked")
	public String getSpecialDepartBigSelectByYxdm(String yxdm, String classId) {
		List newsBigItems = new ArrayList();
		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("") || yxdm.trim().equalsIgnoreCase("0")) {
			newsBigItems = newsManageDao.getSpecialDepartBigs();
		} else {
			newsBigItems = newsManageDao.getSpecialDepartBigByYxdm(yxdm);
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='0'>==== 全 部 ====</option>\n");
		for (Iterator bigItemIterator = newsBigItems.iterator(); bigItemIterator.hasNext();) {
			NewsItemBig bigItem = (NewsItemBig) bigItemIterator.next();
			buffer.append("<option value='" + bigItem.getClassId() + "'");
			if (yxdm != null && bigItem.getClassId().equalsIgnoreCase(classId)) {
				buffer.append(" selected='selected' ");
			}
			buffer.append(">");
			buffer.append(bigItem.getClassName() + "</option>\n");
		}
		return buffer.toString();
	}

	@SuppressWarnings("unchecked")
	public String getNewsItemBigSelectByYxdms(String yxdm, String ifDisplay, String value, String preString) {
		List newsBigItems = queryNewsItemBigByYxdms(yxdm, ifDisplay);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='0'>==== 全 部 ====</option>\n");

		for (Iterator bigItemIterator = newsBigItems.iterator(); bigItemIterator.hasNext();) {
			NewsItemBig bigItem = (NewsItemBig) bigItemIterator.next();

			buffer.append("<option value='" + bigItem.getClassId() + "'");
			if (value != null && bigItem.getClassId().equalsIgnoreCase(value)) {
				buffer.append(" selected='selected' ");
			}
			buffer.append(">");
			buffer.append(preString + bigItem.getClassName() + "</option>\n");
		}
		return buffer.toString();
	}

	@SuppressWarnings("unchecked")
	public String getNewsItemBigSelectForOrderByYxdm(String yxdm, String ifDisplay, int ifIndex, String value) {
		List l = queryNewsItemBigByYxdm(yxdm, ifDisplay, ifIndex);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < l.size(); i++) {
			NewsItemBig temp = (NewsItemBig) l.get(i);
			sb.append("<option value='" + temp.getClassId() + "'");
			if (temp.getClassId().equalsIgnoreCase(value)) {
				sb.append(" selected='selected' ");
			}
			sb.append(">");
			sb.append(temp.getClassName() + "</option>\n");
		}
		return sb.toString();
	}

	public String addNewsItemBig(NewsItemBig nib) {
		String returnValue = "success";
		if (isExistsNewsItemBi(nib.getClassId())) {
			returnValue = "isExists";
			return returnValue;
		}
		this.newsManageDao.saveObject(nib);// 保存首页大类
		return returnValue;
	}

	public String addSecondBig(NewsItemBig nib, String yxModuleId) {
		String str = "success";
		if (isExistsNewsItemBi(nib.getClassId())) {
			str = "isExists";
			return str;
		}
		this.newsManageDao.saveObject(nib);
		return str;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsItemBi(String classId) {
		Map hmPara = new HashMap();
		hmPara.put("classId", classId);
		List l = queryAllNewsItemBig(hmPara);
		return l.size() > 0;
	}

	public boolean checkUseedForNewsItemsBig(NewsItemBig nib) {
		if (nib.getNewsItemSmalls().size() > 0) {
			return true;
		}
		if (nib.getNewsItemNavigations().size() > 0) {
			return true;
		}
		if (nib.getNewsAdTypes().size() > 0) {
			return true;
		}
		return false;
	}

	public String AmendNewsItemBig(NewsItemBig nib, SysModule mondule) {
		String str = "success";
		try {
			this.newsManageDao.saveOrUpdate(nib);
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
		}
		return str;
	}

	// -----------------------NewsItemBig end ----------------------

	// -----------------------NewsItemSmall start ----------------------
	@SuppressWarnings("unchecked")
	public boolean checkUseedForNewsItemSmall(NewsItemSmall nis) {
		List<NewsContentManage> ncms = queryNewsContentManageByTypeId(nis.getTypeId());
		if (ncms.size() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List queryAllNewsItemSmall(Map hmPara) {
		return this.newsManageDao.queryAllNewsItemSmall(hmPara);
	}

	@SuppressWarnings("unchecked")
	public List queryNewsItemSmallByClassId(String classId, String ifDisplay) {
		Map hmPara = new HashMap();
		hmPara.put("classId", classId);
		hmPara.put("ifDisplay", ifDisplay);
		return queryAllNewsItemSmall(hmPara);
	}

	/*
	 * get news item small object by ID
	 */
	@SuppressWarnings("unchecked")
	public NewsItemSmall getNewsItemSmallByTypeId(int typeId) {
		Map paraMap = new HashMap();
		paraMap.put("typeId", typeId);
		List<NewsItemSmall> itemSmallList = queryAllNewsItemSmall(paraMap);
		if (itemSmallList.size() > 0) {
			return itemSmallList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List queryNewsContentManagerByYxdmForPage(String classId, int ifIndex) {
		Map hmPara = new HashMap();
		hmPara.put("classId", classId);
		hmPara.put("ifDisplay", ifIndex);
		return queryAllNewsItemBig(hmPara);
	}

	/*
	 * get news item big selected list string by colleage code and selected
	 * value
	 */
	@SuppressWarnings("unchecked")
	public String getNewsItemSmallSelectByClassId(String classId, String ifDisplay, String value, String preString) {
		List l = queryNewsItemSmallByClassId(classId, ifDisplay);
		StringBuffer sb = new StringBuffer();
		sb.append("<option value='0'>==== 全 部 ====</option>\n");
		for (int i = 0; i < l.size(); i++) {
			NewsItemSmall temp = (NewsItemSmall) l.get(i);
			sb.append("<option value='" + temp.getTypeId() + "'");
			if ((temp.getTypeId() + "").equalsIgnoreCase(value)) {
				sb.append(" selected='selected' ");
			}
			sb.append(">");
			sb.append(preString + temp.getTypeName() + "</option>\n");
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public String getNewsItemSmallSelectForOrderByClassId(String classId, String value) {
		List l = queryNewsItemSmallByClassId(classId, GlobalConst.IS_DISPLAY);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < l.size(); i++) {
			NewsItemSmall temp = (NewsItemSmall) l.get(i);
			sb.append("<option value='" + temp.getTypeId() + "'");
			if ((temp.getTypeId() + "").equalsIgnoreCase(value)) {
				sb.append(" selected='selected' ");
			}
			sb.append(">");
			sb.append(temp.getTypeName() + "</option>\n");
		}
		return sb.toString();
	}

	public String getNewsItemSmallSelectByClassId(String classId, String ifDisplay, String value) {
		return getNewsItemSmallSelectByClassId(classId, ifDisplay, value, "");
	}

	public String getNewsItemSmallSelectByClassId(String classId, String ifDisplay) {
		return getNewsItemSmallSelectByClassId(classId, ifDisplay, "");
	}

	@SuppressWarnings("unchecked")
	public String getDateFormatString(String value) {
		if (value == null) {
			value = "";
		}
		ArrayList l = new ArrayList();
		l.add("yyyy-mm-dd");
		l.add("dd-mm-yyyy");
		String temp = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < l.size(); i++) {
			temp = (String) l.get(i);
			sb.append("<option value='" + temp + "'");
			if (temp.equalsIgnoreCase(value)) {
				sb.append(" selected='selected' ");
			}
			sb.append(">");
			sb.append(temp + "</option>\n");
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsItemSmall(String classId, String typeName) {
		Map hmPara = new HashMap();
		hmPara.put("typeName", typeName);
		hmPara.put("classId", classId);
		List l = queryAllNewsItemSmall(hmPara);
		return l.size() > 0;
	}

	public String AddNewsItemSmall(NewsItemSmall nis) {
		String str = "success";
		if (isExistsNewsItemSmall(nis.getNewsItemBig().getClassId(), nis.getTypeName())) {
			str = "isExists";
		} else {
			this.newsManageDao.saveOrUpdate(nis);

		}
		return str;
	}

	public String AmendNewsItemSmall(NewsItemSmall nis, SysModule mondel) {
		String str = "success";
		try {
			this.newsManageDao.saveOrUpdate(nis);
			this.newsManageDao.saveOrUpdate(mondel);
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
		}
		return str;
	}

	// -----------------------NewsItemSmall end ----------------------

	// -----------------------News Ad Type end ----------------------
	@SuppressWarnings("unchecked")
	public List queryAllNewsAdType(Map paraMap) {
		return this.newsManageDao.findListByHSQLId("queryAllNewsAdType", paraMap);
	}

	@SuppressWarnings("unchecked")
	public List queryAllNewsAdTypeByClassId(String classId) {
		Map paraMap = new HashMap();
		paraMap.put("classId", classId);
		return queryAllNewsAdType(paraMap);
	}

	@SuppressWarnings("unchecked")
	public String getNewsAdTypeSelectByClassId(String classId, String value, String preString) {
		List l = queryAllNewsAdTypeByClassId(classId);
		StringBuffer sb = new StringBuffer();
		sb.append("<option value='0'>==== 全 部 ====</option>\n");
		for (int i = 0; i < l.size(); i++) {
			NewsAdType temp = (NewsAdType) l.get(i);
			sb.append("<option value='" + temp.getAdTypeId() + "'");
			if ((temp.getAdTypeId() + "").equalsIgnoreCase(value)) {
				sb.append(" selected='selected' ");
			}
			sb.append(">");
			sb.append(preString + temp.getAdTypeName() + "</option>\n");
		}
		return sb.toString();
	}

	public String getNewsAdTypeSelectByClassId(String classId, String value) {
		return getNewsAdTypeSelectByClassId(classId, value, "");
	}

	public String getNewsAdTypeSelectByClassId(String classId) {
		return getNewsAdTypeSelectByClassId(classId, "");
	}

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsAdType(Map paraMap) {
		List l = queryAllNewsAdType(paraMap);
		return l.size() > 0;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsAdTypeByName(String adTypeName) {
		Map paraMap = new HashMap();
		paraMap.put("adTypeName", adTypeName);
		return isExistsNewsAdType(paraMap);
	}

	// -----------------------News Ad Type end ----------------------

	// -----------------------News Ad manage start ----------------------
	@SuppressWarnings("unchecked")
	public List queryAllNewsAdManage(Map paraMap) {
		return this.newsManageDao.findListByHSQLId("queryAllNewsAdManage", paraMap);
	}

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsAdManage(Map paraMap) {
		List l = queryAllNewsAdManage(paraMap);
		return l.size() > 0;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsAdManageByName(String adName) {
		Map paraMap = new HashMap();
		paraMap.put("adName", adName);
		return isExistsNewsAdManage(paraMap);
	}

	// -----------------------News Ad manage end ----------------------
	// -----------------------News Content Manage start --------------------
	@SuppressWarnings("unchecked")
	public List queryAllNewsContentManage(Map paraMap) {
		return this.newsManageDao.findListByHSQLId("queryAllNewsContentManage", paraMap);
	}

	@SuppressWarnings("unchecked")
	public List queryNewsContentManageByTypeId(int typeId) {
		Map paraMap = new HashMap();
		paraMap.put("typeId", typeId);
		paraMap.put("checkFlag", GlobalConst.IS_CHECKED);
		return queryAllNewsContentManage(paraMap);
	}

	@SuppressWarnings("unchecked")
	public List queryNewsContentManageByTypeIdAndCheckFlag(int typeId, String checkFlag) {
		Map paraMap = new HashMap();
		paraMap.put("typeId", typeId);
		paraMap.put("checkFlag", checkFlag);
		return queryAllNewsContentManage(paraMap);
	}

	@SuppressWarnings("unchecked")
	public List queryNewsContentManageByNewsId(int newsId) {
		Map paraMap = new HashMap();
		paraMap.put("newsId", newsId);
		return queryAllNewsContentManage(paraMap);
	}

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsContentManage(Map paraMap) {
		return queryAllNewsContentManage(paraMap).size() > 0;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsContentManageByTitle(String newsTitle) {
		Map paraMap = new HashMap();
		paraMap.put("newsTitle", newsTitle);
		return isExistsNewsContentManage(paraMap);
	}

	@SuppressWarnings("unchecked")
	public boolean isExistsNewsContentManageByKeyword(String newsKeyword) {
		Map paraMap = new HashMap();
		paraMap.put("newsKeyword", newsKeyword);
		return isExistsNewsContentManage(paraMap);
	}

	private static int itemIndex = 0;

	@SuppressWarnings("unchecked")
	public String createPropertyTreeByModule(String schoolCode, String collegeCode, BasicPerson person) {
		StringBuffer treeStructure = new StringBuffer();
		itemIndex = 0;
		String treeData = "";
		treeStructure.append("\n<script type=\"text/javascript\">\n");
		treeStructure.append("var Tree = new Array;\n");
		treeStructure.append(treeData);
		List<Object> moduleList = getModuleList(person, collegeCode);
		for (Iterator iterator = moduleList.iterator(); iterator.hasNext();) {
			SysModule rootModule = (SysModule) iterator.next();
			treeData = createTreeForSysModule(rootModule);
			treeStructure.append(treeData);
		}
		treeStructure.append("\n</script>\n");
		return treeStructure.toString();
	}

	@SuppressWarnings("unchecked")
	public String createPropertyTreeByModule(String schoolCode, String collegeCode, HttpServletRequest request) {
		StringBuffer treeStructure = new StringBuffer();
		itemIndex = 0;
		String treeData = "";
		treeStructure.append("\n<script type=\"text/javascript\">\n");
		treeStructure.append("var Tree = new Array;\n");
		treeStructure.append(treeData);
		ModuleCollection collection = (ModuleCollection) request.getSession().getAttribute("module");
		List<ModuleBean> moduleList = collection.getNewsModuleList();
		for (Iterator iterator = moduleList.iterator(); iterator.hasNext();) {
			ModuleBean moduleBean = (ModuleBean) iterator.next();
			treeData = createTreeForSysModule(moduleBean);
			treeStructure.append(treeData);
		}
		treeStructure.append("\n</script>\n");
		return treeStructure.toString();
	}

	@SuppressWarnings( { "unchecked", "unused" })
	private Set<?> getSetFromList(List<?> list) {
		Set<Object> set = new HashSet();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			set.add(iterator.next());
		}
		return set;
	}

	/**
	 * 
	 * @Description:信息发布树--ZTree
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String createZTree(String yxdm, String basePath) {
		List indexItemBigs = queryNewsItemBigByYxdm(yxdm, GlobalConst.IS_DISPLAY, GlobalConst.IS_INDEX);
		List itemBigs = queryNewsItemBigByYxdm(yxdm, GlobalConst.IS_DISPLAY, GlobalConst.IS_NO_INDEX);

		StringBuffer outZTree = new StringBuffer();
		outZTree.append("\n<script type=\"text/javascript\">\n");
		outZTree.append("<!--\n");
		outZTree.append("var zNodes = [\n");

		
		if(indexItemBigs.size()>0){
		// 首页节点
		outZTree.append("{ name:\"首页\", open:true,\n");
		outZTree.append(" nodes: [\n");
		
		Iterator it = indexItemBigs.iterator();
		while (it.hasNext()) {
			NewsItemBig nib = new NewsItemBig();
			nib = (NewsItemBig) it.next();
			String classId = nib.getClassId();
			outZTree.append("{ name:\"" + nib.getClassName() + "\",classId:\"" + classId + "\",\n");
			List itemSmalls = new ArrayList(nib.getNewsItemSmalls());
			Iterator it1 = itemSmalls.iterator();
			outZTree.append(" nodes: [\n");

			while (it1.hasNext()) {
				NewsItemSmall nis = new NewsItemSmall();
				nis = (NewsItemSmall) it1.next();
				outZTree.append("{ name:\"" + nis.getTypeName() + "\",classId:\"" + classId + "\"," + "typeId:\""
						+ nis.getTypeId() + "\"},");
			}
			if(!"".equals(outZTree.toString())){
				String tempTree = outZTree.toString().substring(0,outZTree.length()-1);
				outZTree.delete(0,outZTree.length());
				outZTree.append(tempTree);
			}
			outZTree.append("]\n},");
		}
		if(!"".equals(outZTree.toString())){
			String tempTree = outZTree.toString().substring(0,outZTree.length()-1);
			outZTree.delete(0,outZTree.length());
			outZTree.append(tempTree);
		}
		
		outZTree.append("]}\n");
		}
		
		if(itemBigs.size()>0){
		//非首页节点	
			outZTree.append(",");
			Iterator it = itemBigs.iterator();
			while (it.hasNext()) {
				NewsItemBig nib = new NewsItemBig();
				nib = (NewsItemBig) it.next();
				String classId = nib.getClassId();
				outZTree.append("{ name:\"" + nib.getClassName() + "\",classId:\"" + classId + "\",\n");
				List itemSmalls = new ArrayList(nib.getNewsItemSmalls());
				Iterator it1 = itemSmalls.iterator();
				outZTree.append(" nodes: [\n");

				while (it1.hasNext()) {
					NewsItemSmall nis = new NewsItemSmall();
					nis = (NewsItemSmall) it1.next();
					outZTree.append("{ name:\"" + nis.getTypeName() + "\",classId:\"" + classId + "\"," + "typeId:\""
							+ nis.getTypeId() + "\"},");
				}
				if(!"".equals(outZTree.toString())){
					String tempTree = outZTree.toString().substring(0,outZTree.length()-1);
					outZTree.delete(0,outZTree.length());
					outZTree.append(tempTree);
				}
				outZTree.append("]\n},");
			}
			if(!"".equals(outZTree.toString())){
				String tempTree = outZTree.toString().substring(0,outZTree.length()-1);
				outZTree.delete(0,outZTree.length());
				outZTree.append(tempTree);
			}
			outZTree.append("]\n");
		}
		else {
			outZTree.append("]\n");
		}
		
		outZTree.append("\n//-->\n");
		outZTree.append("\n</script>\n");
		return outZTree.toString();
	}

	/**
	 * 
	 * @Description:信息发布树
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String createPropertyTree(String xxdm, String yxdm, String basePath) {
		StringBuffer sbOutPut = new StringBuffer();
		String strTreeData = "";
		int items = 0;
		sbOutPut.append("\n<script type=\"text/javascript\">\n");
		sbOutPut.append("<!--\n");
		sbOutPut.append("var Tree = new Array();\n");
		strTreeData = "";
		// set the news item big for index page
		strTreeData = xxdm + "|0|首页|#|";
		strTreeData = "Tree[" + items + "] = " + "\"" + strTreeData + "\";\n";
		sbOutPut.append(strTreeData);
		if (yxdm.equalsIgnoreCase(xxdm)) {
			List lPage = this.queryNewsItemBigForFirstPage(xxdm);
			for (int i = 0; i < lPage.size(); i++) {
				strTreeData = "";
				NewsItemBig nib = (NewsItemBig) lPage.get(i);
				sbOutPut.append(createTreeForOneNewsItemBig(nib, ++items, xxdm, 1, basePath));
				items = items + nib.getNewsItemSmalls().size();
			}
		} else {
			List lPage = this.queryNewsItemBigByYxdm(yxdm, GlobalConst.IS_DISPLAY, GlobalConst.IS_INDEX);
			for (int i = 0; i < lPage.size(); i++) {
				strTreeData = "";
				NewsItemBig nib = (NewsItemBig) lPage.get(i);
				sbOutPut.append(createTreeForOneNewsItemBig(nib, ++items, xxdm, 1, basePath));
				items = items + nib.getNewsItemSmalls().size();
			}
		}
		List lOther = this.queryNewsItemBigByYxdm(yxdm, GlobalConst.IS_DISPLAY, GlobalConst.IS_NO_INDEX);
		for (int i = 0; i < lOther.size(); i++) {
			strTreeData = "";
			NewsItemBig nib = (NewsItemBig) lOther.get(i);
			sbOutPut.append(createTreeForOneNewsItemBig(nib, ++items, "0", 1, basePath));
			items = items + nib.getNewsItemSmalls().size();
		}
		sbOutPut.append("\n//-->\n");
		sbOutPut.append("\n</script>\n");
		return sbOutPut.toString();
	}

	/**
	 * 
	 * @Description:信息发布树
	 * 
	 */
	private String createTreeForOneNewsItemBig(NewsItemBig nib, int items, String parent, int childMark, String basePath) {
		StringBuffer sb = new StringBuffer();
		StringBuffer strTreeData = new StringBuffer();
		strTreeData.append(nib.getClassId());
		strTreeData.append(TREE_SEPARATOR);
		strTreeData.append(parent);
		strTreeData.append(TREE_SEPARATOR);
		strTreeData.append(nib.getClassName());
		strTreeData.append("|");
		strTreeData.append(basePath);
		strTreeData.append("view/newsmanage.do?method=findNewsContentManage&classId=");
		strTreeData.append(nib.getClassId());
		strTreeData.append("|target='newsList'|");
		StringBuffer treeItems = new StringBuffer();
		treeItems.append("Tree[" + (items) + "] = " + "\"" + strTreeData + "\";\n");
		sb.append(treeItems);
		if (childMark == 1) {// add news tiems small child
			Object objArray[] = nib.getNewsItemSmalls().toArray();
			for (int j = 0; j < objArray.length; j++) {
				NewsItemSmall nis = (NewsItemSmall) objArray[j];
				treeItems.delete(0, treeItems.length());
				treeItems.append(nis.getTypeId());
				treeItems.append(TREE_SEPARATOR);
				treeItems.append(nib.getClassId());
				treeItems.append(TREE_SEPARATOR);
				treeItems.append(nis.getTypeName());
				treeItems.append("|");
				treeItems.append(basePath);
				treeItems.append("view/newsmanage.do?method=findNewsContentManage&classId=");
				treeItems.append(nib.getClassId());
				treeItems.append("&typeId=");
				treeItems.append(nis.getTypeId());
				treeItems.append("|target='newsList'|");
				strTreeData.delete(0, strTreeData.length());
				strTreeData.append("Tree[" + (++items) + "] = " + "\"" + treeItems + "\";\n");
				sb.append(strTreeData.toString());
			}
		} else if (childMark == 2) {// admanage tree
			Object objArray[] = nib.getNewsAdTypes().toArray();
			for (int j = 0; j < objArray.length; j++) {
				NewsAdType nt = (NewsAdType) objArray[j];
				treeItems.delete(0, treeItems.length());
				strTreeData.delete(0, treeItems.length());
				treeItems.append(nt.getAdTypeId());
				treeItems.append(TREE_SEPARATOR);
				treeItems.append(nib.getClassId());
				treeItems.append(TREE_SEPARATOR);
				treeItems.append(nt.getAdTypeName());
				treeItems.append("|");
				treeItems.append(basePath);
				treeItems.append("view/newsmanage.do?method=findNewsContentManage&classId=");
				treeItems.append(nib.getClassId());
				treeItems.append("&adTypeId=");
				treeItems.append(nt.getAdTypeId());
				treeItems.append("|target='newsAdManageList'|");
				strTreeData.append("Tree[" + (++items) + "] = " + "\"" + treeItems + "\";\n");
				sb.append(strTreeData);
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private String createTreeForSysModule(ModuleBean moduleBean) {
		StringBuffer treeStructure = new StringBuffer();
		String treeItem = null;
		String treeItemData = null;
		if (!"129902000000".equals(moduleBean.getModuleId())) {
			treeItemData = moduleBean.getModuleId() + TREE_SEPARATOR + "0|" + moduleBean.getModuleName()
					+ TREE_SEPARATOR + "#" + TREE_SEPARATOR + "target='newsList'|";
			treeItem = "Tree[" + (itemIndex) + "] = " + "\"" + treeItemData + "\";\n";
			treeStructure.append(treeItem);
			itemIndex = ++itemIndex;
		}
		for (Iterator iterator = moduleBean.getSysModuleList().iterator(); iterator.hasNext();) {
			com.lcpf.framework.pojo.SysModule newsBigItemModule = (com.lcpf.framework.pojo.SysModule) iterator.next();
			if ("129902000000".equals(moduleBean.getModuleId())) {
				newsBigItemModule.setUpModule("0");
			}
			newsBigItemModule.setUrl("#");
			for (Iterator newsSmallIteMIterator = newsBigItemModule.getChildModules().iterator(); newsSmallIteMIterator
					.hasNext();) {
				com.lcpf.framework.pojo.SysModule newsSmallItemModule = (com.lcpf.framework.pojo.SysModule) newsSmallIteMIterator
						.next();
				treeItemData = newsSmallItemModule.getModuleId() + TREE_SEPARATOR + newsSmallItemModule.getUpModule()
						+ TREE_SEPARATOR + newsSmallItemModule.getModuleName() + TREE_SEPARATOR
						+ newsSmallItemModule.getUrl() + TREE_SEPARATOR + "target='newsList'|";
				treeItem = "Tree[" + (itemIndex) + "] = " + "\"" + treeItemData + "\";\n";
				itemIndex = ++itemIndex;
				treeStructure.append(treeItem);
			}
			if (newsBigItemModule.getChildModules().size() > 0) {
				treeItemData = newsBigItemModule.getModuleId() + TREE_SEPARATOR + newsBigItemModule.getUpModule()
						+ TREE_SEPARATOR + newsBigItemModule.getModuleName() + TREE_SEPARATOR
						+ newsBigItemModule.getUrl() + TREE_SEPARATOR + "target='newsList'|";
				treeItem = "Tree[" + (itemIndex) + "] = " + "\"" + treeItemData + "\";\n";
				itemIndex = ++itemIndex;
				treeStructure.append(treeItem);

			}
		}

		return treeStructure.toString();
	}

	@SuppressWarnings("unchecked")
	private List<Object> getModuleList(BasicPerson person, String collegeCode) {
		List childList = new ArrayList();
		if (GlobalConst.ADMIN.equals(person.getPersonAccount())) {
			childList = queryObjectList("from SysModule sm where sm.upModule='" + collegeCode
					+ "' order by sm.moduleId");
		} else {
			childList = queryObjectList("select distinct rm.sysModule.parent.parent  from SysRoleModule rm,SysRolePerson rp where "
					+ "  rm.sysRole.roleId=rp.sysRole.roleId and rm.sysModule.parent.parent.upModule='"
					+ collegeCode
					+ "' and rp.basicPerson.personId=" + person.getPersonId());
		}

		List<Object> moduleList = new ArrayList<Object>();
		for (Iterator iter = childList.iterator(); iter.hasNext();) {
			SysModule allModule = (SysModule) iter.next();
			Set<SysModule> subChildModules = new TreeSet<SysModule>();
			for (Iterator subModuleIterator = allModule.getChildModules().iterator(); subModuleIterator.hasNext();) {
				SysModule subModule = (SysModule) subModuleIterator.next();
				String sql;
				if (GlobalConst.ADMIN.equals(person.getPersonAccount())) {
					sql = "from SysModule sm where sm.upModule='" + subModule.getModuleId() + "' order by sm.moduleId";
				} else {
					sql = "select distinct rm.sysModule.parent from SysRoleModule rm,SysRolePerson rp where "
							+ "  rm.sysRole.roleId=rp.sysRole.roleId and rm.sysModule.parent.upModule='"
							+ subModule.getModuleId() + "' and rp.basicPerson.personId=" + person.getPersonId();
				}
				List<?> subModuleList = queryObjectList(sql);
				Set<SysModule> secondChildModules = new TreeSet<SysModule>();
				for (Iterator<?> iterator = subModuleList.iterator(); iterator.hasNext();) {
					SysModule module = (SysModule) iterator.next();
					Set childModules = new TreeSet();
					for (Iterator moduleIterator = subModuleList.iterator(); moduleIterator.hasNext();) {
						SysModule sysModule = (SysModule) moduleIterator.next();
						childModules.add(sysModule);
					}
					module.setChildModules(childModules);
					if (module.getChildModules().size() > 0) {
						secondChildModules.add(module);
					}
				}
				if (subModule.getChildModules().size() > 0) {
					subChildModules.add(subModule);
				}
				subModule.setChildModules(secondChildModules);
			}
			allModule.setChildModules(subChildModules);

			moduleList.add(allModule);

		}
		return moduleList;
	}

	@SuppressWarnings("unchecked")
	private String createTreeForSysModule(SysModule sysModule) {
		StringBuffer treeStructure = new StringBuffer();
		String treeItem = null;
		StringBuffer treeItemData = new StringBuffer();
		if ("1".equalsIgnoreCase(sysModule.getIfOpen())) {
			treeItemData.append(sysModule.getModuleId());
			treeItemData.append(TREE_SEPARATOR);
			treeItemData.append("0|");
			treeItemData.append(sysModule.getModuleName());
			treeItemData.append(TREE_SEPARATOR);
			treeItemData.append(sysModule.getUrl());
			treeItemData.append(TREE_SEPARATOR);
			treeItemData.append("target='newsList'|");
			treeItem = "Tree[" + (itemIndex) + "] = " + "\"" + treeItemData + "\";\n";
			if (sysModule.getChildModules().size() > 0) {
				treeStructure.append(treeItem);
				itemIndex = ++itemIndex;
			}
		}
		for (Iterator iterator = sysModule.getChildModules().iterator(); iterator.hasNext();) {
			Object object = iterator.next();
			SysModule newsBigItemModule = null;
			if (object instanceof Map) {
				Map values = (Map) object;
				for (Iterator<?> setIterator = values.keySet().iterator(); setIterator.hasNext();) {
					Object key = setIterator.next();
					List<SysModule> newsBigItemModuleList = (List<SysModule>) values.get(key);
					newsBigItemModule = newsBigItemModuleList.get(0);
				}
			} else {
				newsBigItemModule = (SysModule) object;
			}
			if (!"1".equalsIgnoreCase(sysModule.getIfOpen())) {
				newsBigItemModule.setUpModule("0");
			}
			for (Iterator newsSmallIteMIterator = newsBigItemModule.getChildModules().iterator(); newsSmallIteMIterator
					.hasNext();) {
				SysModule newsSmallItemModule = (SysModule) newsSmallIteMIterator.next();
				treeItemData.delete(0, treeItemData.length());
				treeItemData.append(newsSmallItemModule.getModuleId());
				treeItemData.append(TREE_SEPARATOR);
				treeItemData.append(newsSmallItemModule.getUpModule());
				treeItemData.append(TREE_SEPARATOR);
				treeItemData.append(newsSmallItemModule.getModuleName());
				treeItemData.append(TREE_SEPARATOR);
				treeItemData.append(newsSmallItemModule.getUrl());
				treeItemData.append(TREE_SEPARATOR);
				treeItemData.append("target='newsList'|");
				treeItem = "Tree[" + (itemIndex) + "] = " + "\"" + treeItemData + "\";\n";
				itemIndex = ++itemIndex;
				treeStructure.append(treeItem);
			}
			if (newsBigItemModule.getChildModules().size() > 0) {
				treeItemData.delete(0, treeItemData.length());
				treeItemData.append(newsBigItemModule.getModuleId());
				treeItemData.append(TREE_SEPARATOR);
				treeItemData.append(newsBigItemModule.getUpModule());
				treeItemData.append(TREE_SEPARATOR);
				treeItemData.append(newsBigItemModule.getModuleName());
				treeItemData.append(TREE_SEPARATOR);
				treeItemData.append(newsBigItemModule.getUrl());
				treeItemData.append(TREE_SEPARATOR);
				treeItemData.append("target='newsList'|");
				treeItem = "Tree[" + (itemIndex) + "] = " + "\"" + treeItemData + "\";\n";
				itemIndex = ++itemIndex;
				treeStructure.append(treeItem);
			}
		}

		return treeStructure.toString();
	}

	// private String createTreeForOneNewsItemBig(NewsItemBig nib, int items,
	// String parent) {
	// return createTreeForOneNewsItemBig(nib, items, parent, 0);
	// }

	// private String createTreeForOneNewsItemBig(NewsItemBig nib, int items,
	// String parent, int childMark,String basePath) {
	// return createTreeForOneNewsItemBig(nib, items, parent, childMark,
	// basePath);
	// }

	// -----------------------News Content Manage start --------------------
	@SuppressWarnings("unchecked")
	public List queryAllNewsNavigation(Map paraMap) {
		return this.newsManageDao.queryAllNewsItemNavigation(paraMap);
	}

	@SuppressWarnings("unchecked")
	public List queryAllNewsItemNavigationByClassId(String classId) {
		return this.newsManageDao.queryAllNewsItemNavigationByClassId(classId);
	}

	@SuppressWarnings("unchecked")
	public String getNewsNavigationSelectForOrderByClassId(String classId, String value) {
		List l = queryAllNewsItemNavigationByClassId(classId);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < l.size(); i++) {
			NewsItemNavigation temp = (NewsItemNavigation) l.get(i);
			sb.append("<option value='" + temp.getNavigationId() + "'");
			if ((temp.getNavigationId() + "").equalsIgnoreCase(value)) {
				sb.append(" selected='selected' ");
			}
			sb.append(">");
			sb.append(temp.getNavigationName() + "</option>\n");
		}
		return sb.toString();
	}

	// -----------------------News Content Manage end --------------------

	/*
	 * get max order id by table name
	 */
	@SuppressWarnings("unchecked")
	public int getMaxOrderIdByTableName(Map paraMap) {
		return this.newsManageDao.getMaxOrderIdByTableName(paraMap);
	}

	@SuppressWarnings("unchecked")
	public String querySysModule(Map param) {
		return newsManageDao.querySysModule(param);
	}

	public String addSmallItem(String className, NewsItemSmall nis, String classId, boolean isFirst, String yxdm) {
		String str = "success";
		if (isExistsNewsItemSmall(classId, nis.getTypeName())) {
			str = "isExists";
			return str;
		} else {
			this.newsManageDao.saveObject(nis);
		}
		return str;
	}

	@SuppressWarnings("unchecked")
	public void deleteSmallAndModule(NewsItemSmall nis) {

		List list = newsManageDao.queryObjectList("from SysModule where url like '%typeId=" + nis.getTypeId() + "%'");
		if ((list != null) && (list.size() > 0)) {
			SysModule module = (SysModule) list.get(0);
			newsManageDao.deleteObject(module);
			// ssoManage.deleteYxNewsItemBigModule(module);
		}
		newsManageDao.deleteObject(nis);
	}

	@SuppressWarnings("unchecked")
	public void deleteBigAndModule(NewsItemBig nib) {
		List list = newsManageDao.queryObjectList("from SysModule where url like '%classId=" + nib.getClassId()
				+ "%' and url not like '%typeId%'");
		if ((list != null) && (list.size() > 0)) {
			SysModule module = (SysModule) list.get(0);
			newsManageDao.deleteObject(module);
			// ssoManage.deleteYxNewsItemBigModule(module);
		}
		newsManageDao.deleteObject(nib);
	}

	@SuppressWarnings("unchecked")
	public void updateSmallAndModuel(NewsItemSmall nis) {
		List list = newsManageDao.queryObjectList("from SysModule where url like '%typeId=" + nis.getTypeId() + "%'");
		if ((list != null) && (list.size() > 0)) {
			SysModule itemSmall = (SysModule) list.get(0);
			itemSmall.setModuleName(nis.getTypeName());
			newsManageDao.updateObject(itemSmall);
			newsManageDao.saveOrUpdate(nis);
			// ssoManage.updateYxNewsItemBigModule(itemSmall);
		} else {
			newsManageDao.saveOrUpdate(nis);
		}
	}

	@SuppressWarnings("unchecked")
	public String updateBigAndModuel(NewsItemBig nib) {
		List list = newsManageDao.queryObjectList("from SysModule where url like '%classId=" + nib.getClassId()
				+ "%' and url not like '%typeId%'");
		String str = "success";
		try {
			if ((list != null) && (list.size() > 0)) {
				SysModule itemBig = (SysModule) list.get(0);
				itemBig.setModuleName(nib.getClassName());
				newsManageDao.updateObject(itemBig);
				newsManageDao.saveOrUpdate(nib);
				// ssoManage.updateYxNewsItemBigModule(itemBig);
			} else {
				newsManageDao.saveOrUpdate(nib);
			}
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
		}
		return str;
	}

	@SuppressWarnings("unchecked")
	public List queryModule(Map map) {
		return newsManageDao.queryModule(map);
	}

	/***************************************************************************
	 * 当一个广告选择是显示，那么本“系部”的其他广告应该设置为不显示(针对系部)
	 * 
	 * @param adManage
	 * @param yxdm
	 */
	@SuppressWarnings("unchecked")
	public void updateDisplayAdManage(NewsAdManage adManage, String yxdm) {
		if ((null != adManage.getIfDisplay()) && "1".equals(adManage.getIfDisplay())) {
			List newsAdManages = newsManageDao
					.queryObjectList("from NewsAdManage c where c.newsAdType.newsItemBig.yxdm='" + yxdm
							+ "' and adId!=" + adManage.getAdId());
			for (int i = 0; i < newsAdManages.size(); i++) {
				NewsAdManage ad = (NewsAdManage) newsAdManages.get(i);
				ad.setIfDisplay("0");
				newsManageDao.updateObject(ad);
			}
		}
	}

	/***************************************************************************
	 * 查询系部中的大类“是否显示广告”列表，拼凑select标签
	 * 
	 * @param yxdm
	 * @param ifHaveAd
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getNewsItemBigSelectByYxdm(String yxdm, String ifHaveAd, String value) {
		List l = queryNewsItemBigByYxdm(yxdm, ifHaveAd);
		StringBuffer sb = new StringBuffer();
		sb.append("<option value='0'>==== 全 部 ====</option>\n");
		for (int i = 0; i < l.size(); i++) {
			NewsItemBig temp = (NewsItemBig) l.get(i);
			sb.append("<option value='" + temp.getClassId() + "'");
			if (temp.getClassId().equalsIgnoreCase(value)) {
				sb.append(" selected='selected' ");
			}
			sb.append(">");
			sb.append(temp.getClassName() + "</option>\n");
		}
		return sb.toString();
	}

	/***************************************************************************
	 * 查询系部中的大类“是否显示广告”列表
	 * 
	 * @param yxdm
	 * @param ifHaveAd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryNewsItemBigByYxdm(String yxdm, String ifHaveAd) {
		Map hmPara = new HashMap();
		hmPara.put("yxdm", yxdm);
		if (StringUtil.getNullInt(ifHaveAd) < 2) {
			hmPara.put("ifHaveAd", ifHaveAd);
		}
		return queryAllNewsItemBig(hmPara);
	}

	/*
	 * 根据yxdm查询新闻
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List queryNewsContentManageByYxdm(String yxdm) {
		List list = new ArrayList();
		String hql = "";
		if (yxdm.equalsIgnoreCase(GlobalConst.SchoolCode)) {
			hql = "select ncm from NewsContentManage ncm,NewsItemSmall nis ,NewsItemBig nib "
					+ " where exists elements(nis.newsContentManages)" + " and  nis in  elements(ncm.newsItemSmalls) "
					+ " and nib.classId = nis.newsItemBig.classId and ncm.announceType='0' and nib.yxdm = '" + yxdm + "'";
		}
		list = newsManageDao.queryObjectList(hql);
		return list;
	}

}
