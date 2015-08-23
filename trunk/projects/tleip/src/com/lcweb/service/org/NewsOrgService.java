package com.lcweb.service.org;

import java.util.List;

import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.SchCollegeDepartment;
import com.lcweb.service.base.BaseService;

public interface NewsOrgService extends BaseService {
	/*
	 * get colleage department object by school code and colleage code
	 */
	public SchCollegeDepartment getSchCollegeDepartment(String yxdm);

	/*
	 * get colleage department selected list string by school code ,colleage
	 * code and selected value
	 */
	public String getSchCollegeDeptSelectBySchoolCode(String xxdm, String value, String preString);

	public String getSchCollegeDeptSelectBySchoolCode(String xxdm, String value);

	public String getSchCollegeDeptSelectBySchoolCode(String xxdm, String value, BasicPerson person);

	public String getSchCollegeDeptSelectBySchoolCode(String xxdm);

	public List getSchCollegeDepartmentByXxdm(String xxdm);

	public boolean checkExistForCollage(SchCollegeDepartment scd);

	public boolean checkExistFirstPageForCollage(SchCollegeDepartment scd);

	public String getSchoolSelect(String xxdm);

	public String getSpecialDepartSelectByCode(String yxdm, BasicPerson basicPerson);

	public String getFirstSpecialDepart();

	public String getAllSchoolDepart(String yxdm, BasicPerson basicPerson);
	
	public String getAllSchCollegeDepart(String yxdm, BasicPerson basicPerson);

	@SuppressWarnings("unchecked")
	public List findSpecialDepartByPersonId(BasicPerson basicPerson);
}
