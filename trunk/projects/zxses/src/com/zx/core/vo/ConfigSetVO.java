package com.zx.core.vo;

public class ConfigSetVO {
	private String systemName;
	private String copyRight;
	private String logoImage;
	
	
	
	private Boolean checkCode;
	private Boolean failLock;
	private Boolean ipControl;
	private Boolean lengthLimit;
	private Boolean mixNumber;
	private Boolean checkPassword;

	/**   
	 * @return the mixNumber   
	 */
	public Boolean getMixNumber() {
		return mixNumber;
	}

	/**   
	 * @param mixNumber the mixNumber to set   
	 */
	public void setMixNumber(Boolean mixNumber) {
		this.mixNumber = mixNumber;
	}

	/**
	 * @return the systemName
	 */
	public String getSystemName() {
		return systemName;
	}

	/**
	 * @param systemName
	 *            the systemName to set
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	/**
	 * @return the copyRight
	 */
	public String getCopyRight() {
		return copyRight;
	}

	/**
	 * @param copyRight
	 *            the copyRight to set
	 */
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	/**
	 * @return the logoImage
	 */
	public String getLogoImage() {
		return logoImage;
	}

	/**
	 * @param logoImage
	 *            the logoImage to set
	 */
	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	/**   
	 * @return the checkCode   
	 */
	public Boolean getCheckCode() {
		return checkCode;
	}

	/**   
	 * @param checkCode the checkCode to set   
	 */
	public void setCheckCode(Boolean checkCode) {
		this.checkCode = checkCode;
	}

	/**   
	 * @return the failLock   
	 */
	public Boolean getFailLock() {
		return failLock;
	}

	/**   
	 * @param failLock the failLock to set   
	 */
	public void setFailLock(Boolean failLock) {
		this.failLock = failLock;
	}

	/**   
	 * @return the ipControl   
	 */
	public Boolean getIpControl() {
		return ipControl;
	}

	/**   
	 * @param ipControl the ipControl to set   
	 */
	public void setIpControl(Boolean ipControl) {
		this.ipControl = ipControl;
	}

	/**   
	 * @return the lengthLimit   
	 */
	public Boolean getLengthLimit() {
		return lengthLimit;
	}

	/**   
	 * @param lengthLimit the lengthLimit to set   
	 */
	public void setLengthLimit(Boolean lengthLimit) {
		this.lengthLimit = lengthLimit;
	}


	/**   
	 * @return the checkPassword   
	 */
	public Boolean getCheckPassword() {
		return checkPassword;
	}

	/**   
	 * @param checkPassword the checkPassword to set   
	 */
	public void setCheckPassword(Boolean checkPassword) {
		this.checkPassword = checkPassword;
	}


}
