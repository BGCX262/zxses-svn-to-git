package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfTeacherPhoto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfTeacherPhoto implements java.io.Serializable {

	// Fields

	private Integer id;
	private String fileName;
	private String fileExt;
	private Integer fileLength;
	private String fileContentType;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfTeacherPhoto() {
	}

	/** minimal constructor */
	public BasinfTeacherPhoto(String fileName, Integer recordStatus) {
		this.fileName = fileName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfTeacherPhoto(String fileName, String fileExt,
			Integer fileLength, String fileContentType, Date updateTime,
			Integer recordStatus, Set basinfTeachers) {
		this.fileName = fileName;
		this.fileExt = fileExt;
		this.fileLength = fileLength;
		this.fileContentType = fileContentType;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfTeachers = basinfTeachers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return this.fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Integer getFileLength() {
		return this.fileLength;
	}

	public void setFileLength(Integer fileLength) {
		this.fileLength = fileLength;
	}

	public String getFileContentType() {
		return this.fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
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

	public Set getBasinfTeachers() {
		return this.basinfTeachers;
	}

	public void setBasinfTeachers(Set basinfTeachers) {
		this.basinfTeachers = basinfTeachers;
	}

}