package com.lcweb.service.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcweb.base.util.StringUtil;
import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.SchCollegeDepartment;
import com.lcweb.commons.GlobalConst;
import com.lcweb.dao.org.NewsOrgDao;
import com.lcweb.service.base.BaseServiceImpl;

public class NewsOrgServiceImpl extends BaseServiceImpl implements NewsOrgService {
	private NewsOrgDao newsOrgDao;

	public void setNewsOrgDao(NewsOrgDao newsOrgDao) {
		this.newsOrgDao = newsOrgDao;
	}

	// the college method
	// ----------------------------start----------------------------------
	public String getSchoolSelect(String xxdm) {
		return GlobalConst.getSchoolSelect(xxdm);
	}

	/*
	 * get colleage department list by map parameter , that is the common method
	 */
	@SuppressWarnings("unchecked")
	public List getSchCollegeDepartment(Map hmPara) {
		return this.newsOrgDao.getSchCollegeDepartment(hmPara);
	}

	/*
	 * get colleage department object by colleage code
	 */
	@SuppressWarnings("unchecked")
	public List getSchCollegeDepartmentByXxdm(String xxdm) {
		Map hmPara = new HashMap();
		hmPara.put("xxdm", xxdm);
		return getSchCollegeDepartment(hmPara);
	}

	/*
	 * get colleage department object by colleage code
	 */
	@SuppressWarnings("unchecked")
	public SchCollegeDepartment getSchCollegeDepartment(String yxdm) {
		SchCollegeDepartment schCollDept = new SchCollegeDepartment();
		String schName = GlobalConst.getSchoolName(yxdm);
		if (schName != null) {
			schCollDept.setYxdm(yxdm);
			schCollDept.setYxmc(schName);
			return schCollDept;
		}
		Map hmPara = new HashMap();
		hmPara.put("yxdm", yxdm);
		List l = getSchCollegeDepartment(hmPara);
		if (l.size() > 0) {
			schCollDept = (SchCollegeDepartment) l.get(0);
		}
		return schCollDept;
	}

	/*
	 * get colleage department selected list string by school code ,colleage
	 * code and selected value
	 */
	@SuppressWarnings("unchecked")
	public String getSchCollegeDeptSelectBySchoolCode(String xxdm, String value, String preString) {
		Map hmPara = new HashMap();
		hmPara.put("xxdm", xxdm);
		// hmPara.put("IsNoFirstPage", GlobalConst.DongGuanFirstPageYxdm);
		value = StringUtil.getNullString(value);
		List l = getSchCollegeDepartment(hmPara);
		StringBuffer sb = new StringBuffer();
		if (xxdm.equalsIgnoreCase(GlobalConst.SchoolCode)) {
			sb.append("<option value='" + GlobalConst.SchoolCode + "' "
					+ (value.equalsIgnoreCase(GlobalConst.SchoolCode) == true ? " selected='selected' " : " ")
					+ ">" + GlobalConst.SchoolName + "</option>\n");
		}
		if ((preString == null) || preString.trim().equalsIgnoreCase("")) {
			preString = "----";
		}
		for (int i = 0; i < l.size(); i++) {
			SchCollegeDepartment temp = (SchCollegeDepartment) l.get(i);
			sb.append("<option value='" + temp.getYxdm() + "'");
			if (temp.getYxdm().equalsIgnoreCase(value)) {
				sb.append(" selected='selected' ");
			}
			sb.append(">");
			sb.append(preString + temp.getYxmc() + "</option>\n");
		}
		return sb.toString();
	}

	public String getSchCollegeDeptSelectBySchoolCode(String xxdm, String value) {
		return getSchCollegeDeptSelectBySchoolCode(xxdm, value, "");
	}

	public String getSchCollegeDeptSelectBySchoolCode(String xxdm) {
		return getSchCollegeDeptSelectBySchoolCode(xxdm, "", "");
	}

	public boolean checkExistForCollage(SchCollegeDepartment scd) {
		SchCollegeDepartment temp = (SchCollegeDepartment) queryObjectById(new SchCollegeDepartment(), scd.getYxdm());
		return temp != null;
	}

	@SuppressWarnings("unchecked")
	public boolean checkExistFirstPageForCollage(SchCollegeDepartment scd) {
		Map hmPara = new HashMap();
		hmPara.put("yxdm", "First");
		hmPara.put("xxdm", scd.getXxdm());
		List l = getSchCollegeDepartment(hmPara);
		return l.size() > 0;
	}

	// the college method
	// ----------------------------end----------------------------------
	@SuppressWarnings("unchecked")
	public String getSchCollegeDeptSelectBySchoolCode(String xxdm, String value, BasicPerson person) {
		List list = newsOrgDao.getSchCollegeDepartment(xxdm, value, person);
		String preString = "----";
		StringBuffer buffer = new StringBuffer();
		if (xxdm.equalsIgnoreCase(GlobalConst.SchoolCode)) {
			buffer.append("<option value='" + GlobalConst.SchoolCode + "' "
					+ (value.equalsIgnoreCase(GlobalConst.SchoolCode) == true ? " selected='selected' " : " ")
					+ ">" + GlobalConst.SchoolName + "</option>\n");
		}
		for (int i = 0; i < list.size(); i++) {
			SchCollegeDepartment department = (SchCollegeDepartment) list.get(i);
			buffer.append("<option value='" + department.getYxdm() + "'");
			if (department.getYxdm().equalsIgnoreCase(value)) {
				buffer.append(" selected='selected' ");
			}
			buffer.append(">");
			buffer.append(preString + department.getYxmc() + "</option>\n");
		}
		return buffer.toString();
	}

	/**
	 * 
	 * @Description:信息发布页下拉框
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getAllSchoolDepart(String yxdm, BasicPerson basicPerson) {
		List list = new ArrayList();
		Map paraMap = new HashMap();
		String preString = " ";
		StringBuffer buffer = new StringBuffer();
		list = findSpecialDepartByPersonId(basicPerson);
		if (list == null) {
			list = newsOrgDao.getSchCollegeDepartment(paraMap);
			buffer.append("<option value='" + GlobalConst.SchoolCode + "'" + " selected='selected'" + ">"
					+ GlobalConst.SchoolName + "</option>\n");
		}
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SchCollegeDepartment department = (SchCollegeDepartment) list.get(i);
				buffer.append("<option value='" + department.getYxdm() + "'");
				if (department.getYxdm().equalsIgnoreCase(yxdm)) {
					buffer.append(" selected='selected' ");
				}
				buffer.append(">");
				buffer.append(preString + department.getYxmc() + "</option>\n");
			}
		}
		return buffer.toString();
	}

	/**
	 * 
	 * @Description:大类管理页下拉框
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getAllSchCollegeDepart(String yxdm, BasicPerson basicPerson) {
		List list = new ArrayList();
		Map paraMap = new HashMap();
		String preString = " ";
		StringBuffer buffer = new StringBuffer();
		list = newsOrgDao.getSchCollegeDepartment(paraMap);
		buffer.append("<option value='" + GlobalConst.SchoolCode + "'" + " selected='selected'" + ">"
				+ GlobalConst.SchoolName + "</option>\n");
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SchCollegeDepartment department = (SchCollegeDepartment) list.get(i);
				buffer.append("<option value='" + department.getYxdm() + "'");
				if (department.getYxdm().equalsIgnoreCase(yxdm)) {
					buffer.append(" selected='selected' ");
				}
				buffer.append(">");
				buffer.append(preString + department.getYxmc() + "</option>\n");
			}
		}
		return buffer.toString();
	}

	/**
	 * 
	 * @Description:专业部大类下拉框
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getSpecialDepartSelectByCode(String yxdm, BasicPerson basicPerson) {
		List list = new ArrayList();
		Map paraMap = new HashMap();
		String preString = " ";
		StringBuffer buffer = new StringBuffer();
		list = findSpecialDepartByPersonId(basicPerson);
		if (list == null) {
			list = newsOrgDao.getSchCollegeDepartment(paraMap);
			buffer.append("<option value='0' selected='selected'" + ">" + "=== 全部 ===" + "</option>\n");
		}
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SchCollegeDepartment department = (SchCollegeDepartment) list.get(i);
				buffer.append("<option value='" + department.getYxdm() + "'");
				if (department.getYxdm().equalsIgnoreCase(yxdm)) {
					buffer.append(" selected='selected' ");
				}
				buffer.append(">");
				buffer.append(preString + department.getYxmc() + "</option>\n");
			}
		}
		return buffer.toString();
	}

	@SuppressWarnings("unchecked")
	public String getFirstSpecialDepart() {
		String yxdm = "";
		Map paraMap = new HashMap();
		List list = newsOrgDao.getSchCollegeDepartment(paraMap);
		if (list.size() > 0) {
			SchCollegeDepartment sd = (SchCollegeDepartment) list.get(0);
			yxdm = sd.getYxdm();
		}
		return yxdm;
	}

	/**
	 * 
	 * @Description:判断人员是否在专业部
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List findSpecialDepartByPersonId(BasicPerson basicPerson) {
		List list = new ArrayList();
		list = newsOrgDao.getSpecialDepartment(basicPerson);
		if (list.size() > 0) {
			return list;
		} else
			return null;
	}
}
