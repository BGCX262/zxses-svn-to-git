package com.zx.es.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasinfStudentPhoto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasinfStudentPhoto implements java.io.Serializable {

	// Fields

	private Integer id;
	private String fileName;
	private String fileExt;
	private Integer fileLength;
	private String fileContentType;
	private Date updateTime;
	private Integer recordStatus;
	private Set basinfStudents = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasinfStudentPhoto() {
	}

	/** minimal constructor */
	public BasinfStudentPhoto(String fileName, Integer recordStatus) {
		this.fileName = fileName;
		this.recordStatus = recordStatus;
	}

	/** full constructor */
	public BasinfStudentPhoto(String fileName, String fileExt,
			Integer fileLength, String fileContentType, Date updateTime,
			Integer recordStatus, Set basinfStudents) {
		this.fileName = fileName;
		this.fileExt = fileExt;
		this.fileLength = fileLength;
		this.fileContentType = fileContentType;
		this.updateTime = updateTime;
		this.recordStatus = recordStatus;
		this.basinfStudents = basinfStudents;
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

	public Set getBasinfStudents() {
		return this.basinfStudents;
	}

	public void setBasinfStudents(Set basinfStudents) {
		this.basinfStudents = basinfStudents;
	}

}