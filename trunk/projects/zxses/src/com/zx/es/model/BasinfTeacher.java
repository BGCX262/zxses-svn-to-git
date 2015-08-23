package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfTeacher entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfTeacher implements java.io.Serializable {

	// Fields

	private Integer id;
	private BasinfPosition basinfPosition;
	private BasinfSex basinfSex;
	private BasinfTeacherPhoto basinfTeacherPhoto;
	private BasinfPolitical basinfPolitical;
	private BasinfAccountKind basinfAccountKind;
	private BasinfMarriage basinfMarriage;
	private BasinfWorkKind basinfWorkKind;
	private BasinfNation basinfNation;
	private String teacherNo;
	private String teacherName;
	private Date birthday;
	private String origin;
	private Date politicalDate;
	private Integer userTypeId;
	private String profession;
	private String cardNo;
	private Date joinWorkDate;
	private Date joinTeachDate;
	private Date justDate;
	private Date joinUnitDate;
	private String lastUnitName;
	private String onlineUnitName;
	private String homeAddress;
	private String postCode;
	private String email;
	private String telephone;
	private Date retireDate;
	private Integer isLock;
	private Integer status;
	private String remark;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfClassSubjectTeachers = new HashSet(0);
	private Set basinfClasses = new HashSet(0);
	private Set basinfOrganizationTearchers = new HashSet(0);
	private Set appraiseMarkTeachers = new HashSet(0);
	private Set appraiseTeachers = new HashSet(0);
	private Set basinfSubjectTeachers = new HashSet(0);
	private Set subjectiveTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfTeacher() {
	}

	/** minimal constructor */
	public BasinfTeacher(String teacherNo, String teacherName,
			Integer recordStatus) {
		this.teacherNo = teacherNo;
		this.teacherName = teacherName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfTeacher(BasinfPosition basinfPosition, BasinfSex basinfSex,
			BasinfTeacherPhoto basinfTeacherPhoto,
			BasinfPolitical basinfPolitical,
			BasinfAccountKind basinfAccountKind, BasinfMarriage basinfMarriage,
			BasinfWorkKind basinfWorkKind, BasinfNation basinfNation,
			String teacherNo, String teacherName, Date birthday, String origin,
			Date politicalDate, Integer userTypeId, String profession,
			String cardNo, Date joinWorkDate, Date joinTeachDate,
			Date justDate, Date joinUnitDate, String lastUnitName,
			String onlineUnitName, String homeAddress, String postCode,
			String email, String telephone, Date retireDate, Integer isLock,
			Integer status, String remark, Date updateTime,
			Integer recordStatus, Set basinfClassSubjectTeachers,
			Set basinfClasses, Set basinfOrganizationTearchers,
			Set appraiseMarkTeachers, Set appraiseTeachers,
			Set basinfSubjectTeachers, Set subjectiveTeachers) {
		this.basinfPosition = basinfPosition;
		this.basinfSex = basinfSex;
		this.basinfTeacherPhoto = basinfTeacherPhoto;
		this.basinfPolitical = basinfPolitical;
		this.basinfAccountKind = basinfAccountKind;
		this.basinfMarriage = basinfMarriage;
		this.basinfWorkKind = basinfWorkKind;
		this.basinfNation = basinfNation;
		this.teacherNo = teacherNo;
		this.teacherName = teacherName;
		this.birthday = birthday;
		this.origin = origin;
		this.politicalDate = politicalDate;
		this.userTypeId = userTypeId;
		this.profession = profession;
		this.cardNo = cardNo;
		this.joinWorkDate = joinWorkDate;
		this.joinTeachDate = joinTeachDate;
		this.justDate = justDate;
		this.joinUnitDate = joinUnitDate;
		this.lastUnitName = lastUnitName;
		this.onlineUnitName = onlineUnitName;
		this.homeAddress = homeAddress;
		this.postCode = postCode;
		this.email = email;
		this.telephone = telephone;
		this.retireDate = retireDate;
		this.isLock = isLock;
		this.status = status;
		this.remark = remark;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfClassSubjectTeachers = basinfClassSubjectTeachers;
		this.basinfClasses = basinfClasses;
		this.basinfOrganizationTearchers = basinfOrganizationTearchers;
		this.appraiseMarkTeachers = appraiseMarkTeachers;
		this.appraiseTeachers = appraiseTeachers;
		this.basinfSubjectTeachers = basinfSubjectTeachers;
		this.subjectiveTeachers = subjectiveTeachers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BasinfPosition getBasinfPosition() {
		return this.basinfPosition;
	}

	public void setBasinfPosition(BasinfPosition basinfPosition) {
		this.basinfPosition = basinfPosition;
	}

	public BasinfSex getBasinfSex() {
		return this.basinfSex;
	}

	public void setBasinfSex(BasinfSex basinfSex) {
		this.basinfSex = basinfSex;
	}

	public BasinfTeacherPhoto getBasinfTeacherPhoto() {
		return this.basinfTeacherPhoto;
	}

	public void setBasinfTeacherPhoto(BasinfTeacherPhoto basinfTeacherPhoto) {
		this.basinfTeacherPhoto = basinfTeacherPhoto;
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

	public BasinfMarriage getBasinfMarriage() {
		return this.basinfMarriage;
	}

	public void setBasinfMarriage(BasinfMarriage basinfMarriage) {
		this.basinfMarriage = basinfMarriage;
	}

	public BasinfWorkKind getBasinfWorkKind() {
		return this.basinfWorkKind;
	}

	public void setBasinfWorkKind(BasinfWorkKind basinfWorkKind) {
		this.basinfWorkKind = basinfWorkKind;
	}

	public BasinfNation getBasinfNation() {
		return this.basinfNation;
	}

	public void setBasinfNation(BasinfNation basinfNation) {
		this.basinfNation = basinfNation;
	}

	public String getTeacherNo() {
		return this.teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Date getPoliticalDate() {
		return this.politicalDate;
	}

	public void setPoliticalDate(Date politicalDate) {
		this.politicalDate = politicalDate;
	}

	public Integer getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getJoinWorkDate() {
		return this.joinWorkDate;
	}

	public void setJoinWorkDate(Date joinWorkDate) {
		this.joinWorkDate = joinWorkDate;
	}

	public Date getJoinTeachDate() {
		return this.joinTeachDate;
	}

	public void setJoinTeachDate(Date joinTeachDate) {
		this.joinTeachDate = joinTeachDate;
	}

	public Date getJustDate() {
		return this.justDate;
	}

	public void setJustDate(Date justDate) {
		this.justDate = justDate;
	}

	public Date getJoinUnitDate() {
		return this.joinUnitDate;
	}

	public void setJoinUnitDate(Date joinUnitDate) {
		this.joinUnitDate = joinUnitDate;
	}

	public String getLastUnitName() {
		return this.lastUnitName;
	}

	public void setLastUnitName(String lastUnitName) {
		this.lastUnitName = lastUnitName;
	}

	public String getOnlineUnitName() {
		return this.onlineUnitName;
	}

	public void setOnlineUnitName(String onlineUnitName) {
		this.onlineUnitName = onlineUnitName;
	}

	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getRetireDate() {
		return this.retireDate;
	}

	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
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

	public Set getBasinfClassSubjectTeachers() {
		return this.basinfClassSubjectTeachers;
	}

	public void setBasinfClassSubjectTeachers(Set basinfClassSubjectTeachers) {
		this.basinfClassSubjectTeachers = basinfClassSubjectTeachers;
	}

	public Set getBasinfClasses() {
		return this.basinfClasses;
	}

	public void setBasinfClasses(Set basinfClasses) {
		this.basinfClasses = basinfClasses;
	}

	public Set getBasinfOrganizationTearchers() {
		return this.basinfOrganizationTearchers;
	}

	public void setBasinfOrganizationTearchers(Set basinfOrganizationTearchers) {
		this.basinfOrganizationTearchers = basinfOrganizationTearchers;
	}

	public Set getAppraiseMarkTeachers() {
		return this.appraiseMarkTeachers;
	}

	public void setAppraiseMarkTeachers(Set appraiseMarkTeachers) {
		this.appraiseMarkTeachers = appraiseMarkTeachers;
	}

	public Set getAppraiseTeachers() {
		return this.appraiseTeachers;
	}

	public void setAppraiseTeachers(Set appraiseTeachers) {
		this.appraiseTeachers = appraiseTeachers;
	}

	public Set getBasinfSubjectTeachers() {
		return this.basinfSubjectTeachers;
	}

	public void setBasinfSubjectTeachers(Set basinfSubjectTeachers) {
		this.basinfSubjectTeachers = basinfSubjectTeachers;
	}

	public Set getSubjectiveTeachers() {
		return this.subjectiveTeachers;
	}

	public void setSubjectiveTeachers(Set subjectiveTeachers) {
		this.subjectiveTeachers = subjectiveTeachers;
	}

}