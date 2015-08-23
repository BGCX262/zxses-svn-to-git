package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfStudent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfStudent implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfSex basinfSex;
	private BasinfStudentPhoto basinfStudentPhoto;
	private BasinfPolitical basinfPolitical;
	private BasinfAccountKind basinfAccountKind;
	private BasinfNation basinfNation;
	private String stuCode;
	private String stuName;
	private String cardNo;
	private Date birthday;
	private Integer isLocalAccount;
	private String location;
	private String homeAddress;
	private String housKindName;
	private String homePhone;
	private Integer isFeeLearn;
	private Integer isLock;
	private Integer status;
	private String remark;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfClassStudents = new HashSet(0);
	private Set basinfStudentFamilies = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfStudent() {
	}

	/** minimal constructor */
	public BasinfStudent(String stuCode, String stuName, Integer recordStatus) {
		this.stuCode = stuCode;
		this.stuName = stuName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfStudent(BasinfSex basinfSex,
			BasinfStudentPhoto basinfStudentPhoto,
			BasinfPolitical basinfPolitical,
			BasinfAccountKind basinfAccountKind, BasinfNation basinfNation,
			String stuCode, String stuName, String cardNo, Date birthday,
			Integer isLocalAccount, String location, String homeAddress,
			String housKindName, String homePhone, Integer isFeeLearn,
			Integer isLock, Integer status, String remark, Date updateTime,
			Integer recordStatus, Set basinfClassStudents,
			Set basinfStudentFamilies) {
		this.basinfSex = basinfSex;
		this.basinfStudentPhoto = basinfStudentPhoto;
		this.basinfPolitical = basinfPolitical;
		this.basinfAccountKind = basinfAccountKind;
		this.basinfNation = basinfNation;
		this.stuCode = stuCode;
		this.stuName = stuName;
		this.cardNo = cardNo;
		this.birthday = birthday;
		this.isLocalAccount = isLocalAccount;
		this.location = location;
		this.homeAddress = homeAddress;
		this.housKindName = housKindName;
		this.homePhone = homePhone;
		this.isFeeLearn = isFeeLearn;
		this.isLock = isLock;
		this.status = status;
		this.remark = remark;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfClassStudents = basinfClassStudents;
		this.basinfStudentFamilies = basinfStudentFamilies;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BasinfSex getBasinfSex() {
		return this.basinfSex;
	}

	public void setBasinfSex(BasinfSex basinfSex) {
		this.basinfSex = basinfSex;
	}

	public BasinfStudentPhoto getBasinfStudentPhoto() {
		return this.basinfStudentPhoto;
	}

	public void setBasinfStudentPhoto(BasinfStudentPhoto basinfStudentPhoto) {
		this.basinfStudentPhoto = basinfStudentPhoto;
	}

	public BasinfPolitical getBasinfPolitical() {
		return this.basinfPolitical;
	}

	public void setBasinfPolitical(BasinfPolitical basinfPolitical) {
		this.basinfPolitical = basinfPolitical;
	}

	public BasinfAccountKind getBasinfAccountKind() {
		return this.basinfAccountKind;
	}

	public void setBasinfAccountKind(BasinfAccountKind basinfAccountKind) {
		this.basinfAccountKind = basinfAccountKind;
	}

	public BasinfNation getBasinfNation() {
		return this.basinfNation;
	}

	public void setBasinfNation(BasinfNation basinfNation) {
		this.basinfNation = basinfNation;
	}

	public String getStuCode() {
		return this.stuCode;
	}

	public void setStuCode(String stuCode) {
		this.stuCode = stuCode;
	}

	public String getStuName() {
		return this.stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getIsLocalAccount() {
		return this.isLocalAccount;
	}

	public void setIsLocalAccount(Integer isLocalAccount) {
		this.isLocalAccount = isLocalAccount;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHousKindName() {
		return this.housKindName;
	}

	public void setHousKindName(String housKindName) {
		this.housKindName = housKindName;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public Integer getIsFeeLearn() {
		return this.isFeeLearn;
	}

	public void setIsFeeLearn(Integer isFeeLearn) {
		this.isFeeLearn = isFeeLearn;
	}

	public Integer getIsLock() {
		return this.isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Set getBasinfClassStudents() {
		return this.basinfClassStudents;
	}

	public void setBasinfClassStudents(Set basinfClassStudents) {
		this.basinfClassStudents = basinfClassStudents;
	}

	public Set getBasinfStudentFamilies() {
		return this.basinfStudentFamilies;
	}

	public void setBasinfStudentFamilies(Set basinfStudentFamilies) {
		this.basinfStudentFamilies = basinfStudentFamilies;
	}

}