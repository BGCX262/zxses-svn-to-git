package com.lcweb.bean.pojo;

import java.util.Date;

public class Monitor {
	private long id;
	private String loginfo;
	private Date operateTime;
	private String status;
	
	private String className;
	private String typeName;
	
	private String operator;

	/**   
	 * @return the operator   
	 */
	public String getOperator() {
		return operator;
	}

	/**   
	 * @param operator the operator to set   
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	/**   
	 * @return the loginfo   
	 */
	public String getLoginfo() {
		return loginfo;
	}

	/**   
	 * @param loginfo the loginfo to set   
	 */
	public void setLoginfo(String loginfo) {
		this.loginfo = loginfo;
	}

	/**
	 * @return the operateTime
	 */
	public Date getOperateTime() {
		return operateTime;
	}

	/**
	 * @param operateTime
	 *            the operateTime to set
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	/**   
	 * @return the className   
	 */
	public String getClassName() {
		return className;
	}

	/**   
	 * @param className the className to set   
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**   
	 * @return the typeName   
	 */
	public String getTypeName() {
		return typeName;
	}

	/**   
	 * @param typeName the typeName to set   
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**   
	 * @return the status   
	 */
	public String getStatus() {
		return status;
	}

	/**   
	 * @param status the status to set   
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
