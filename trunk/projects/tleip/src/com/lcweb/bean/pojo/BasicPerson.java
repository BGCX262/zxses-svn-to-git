package com.lcweb.bean.pojo;

import java.util.HashSet;
import java.util.Set;

 

public class BasicPerson implements java.io.Serializable {

	 
 
	private static final long serialVersionUID = 1L;
	private Long personId;
	private BasicDepartment basicDepartment = new BasicDepartment();
	private NewsContentManage newsContentManage = new NewsContentManage();
	//��Ա���
	private String personCode;
	//��Ա����
	private String personName;
	//��¼�ʺ�
	private String personAccount;
	//��¼����
	private String password;
	//��Ա���
	private String personType;
	private Set newsRights = new HashSet(0);
	private Set sysRolePersons = new HashSet(0);
	private Set newsContentManages = new HashSet(0);
	//�Ƿ�ѡ��
	private String isChecked;

 

	public Long getPersonId() {
		return this.personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public BasicDepartment getBasicDepartment() {
		return this.basicDepartment;
	}

	public void setBasicDepartment(BasicDepartment basicDepartment) {
		this.basicDepartment = basicDepartment;
	}

	public String getPersonCode() {
		return this.personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonAccount() {
		return this.personAccount;
	}

	public void setPersonAccount(String personAccount) {
		this.personAccount = personAccount;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonType() {
		return this.personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public Set getNewsRights() {
		return this.newsRights;
	}

	public void setNewsRights(Set newsRights) {
		this.newsRights = newsRights;
	}

	public Set getSysRolePersons() {
		return this.sysRolePersons;
	}

	public void setSysRolePersons(Set sysRolePersons) {
		this.sysRolePersons = sysRolePersons;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	/**   
	 * @return the newsContentManages   
	 */
	public Set getNewsContentManages() {
		return newsContentManages;
	}

	/**   
	 * @param newsContentManages the newsContentManages to set   
	 */
	public void setNewsContentManages(Set newsContentManages) {
		this.newsContentManages = newsContentManages;
	}

	/**   
	 * @return the newsContentManage   
	 */
	public NewsContentManage getNewsContentManage() {
		return newsContentManage;
	}

	/**   
	 * @param newsContentManage the newsContentManage to set   
	 */
	public void setNewsContentManage(NewsContentManage newsContentManage) {
		this.newsContentManage = newsContentManage;
	}

	

}