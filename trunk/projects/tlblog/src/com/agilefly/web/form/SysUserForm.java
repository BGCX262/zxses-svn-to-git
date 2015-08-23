package com.agilefly.web.form;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * @author boleyn_renlei
 * @date May 18, 2012 10:40:16 PM
 */
public class SysUserForm extends ActionForm {
	private int id;
	private String username;
	private String password;
	private String userType;
	private String realname;
	private String gender;
	private String userPenname;
	private String userBirthday;
	private String userQq;
	private String userBlogname;
	private String userBlogdes;
	private String userLoginip;
	private Date userRegtime;
	private Date userLogintime;
	private FormFile userHeadpic;
	private String userEmail;
	private String sinaWeibo;
	private String tenWeibo;
	private String phone;
	
	private Integer[] roleIds;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserPenname() {
		return userPenname;
	}
	public void setUserPenname(String userPenname) {
		this.userPenname = userPenname;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserQq() {
		return userQq;
	}
	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}
	public String getUserBlogname() {
		return userBlogname;
	}
	public void setUserBlogname(String userBlogname) {
		this.userBlogname = userBlogname;
	}
	public String getUserBlogdes() {
		return userBlogdes;
	}
	public void setUserBlogdes(String userBlogdes) {
		this.userBlogdes = userBlogdes;
	}
	public String getUserLoginip() {
		return userLoginip;
	}
	public void setUserLoginip(String userLoginip) {
		this.userLoginip = userLoginip;
	}
	public Date getUserRegtime() {
		return userRegtime;
	}
	public void setUserRegtime(Date userRegtime) {
		this.userRegtime = userRegtime;
	}
	public Date getUserLogintime() {
		return userLogintime;
	}
	public void setUserLogintime(Date userLogintime) {
		this.userLogintime = userLogintime;
	}
	public FormFile getUserHeadpic() {
		return userHeadpic;
	}
	public void setUserHeadpic(FormFile userHeadpic) {
		this.userHeadpic = userHeadpic;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getSinaWeibo() {
		return sinaWeibo;
	}
	public void setSinaWeibo(String sinaWeibo) {
		this.sinaWeibo = sinaWeibo;
	}
	public String getTenWeibo() {
		return tenWeibo;
	}
	public void setTenWeibo(String tenWeibo) {
		this.tenWeibo = tenWeibo;
	}
	public Integer[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}
	
	/**
	 * 验证上传文件类型是否属于图片格式
	 * @return
	 */
	public static boolean validateImageFileType(FormFile formfile){
		if(formfile!=null && formfile.getFileSize()>0){
			List<String> arrowType = Arrays.asList("image/bmp","image/png","image/gif","image/jpg","image/jpeg","image/pjpeg");
			List<String> arrowExtension = Arrays.asList("gif","jpg","bmp","png");
			String ext = getExt(formfile);
			return arrowType.contains(formfile.getContentType().toLowerCase()) && arrowExtension.contains(ext);
		}
		return true;
	}
	
	public static String getExt(FormFile formfile){
		return formfile.getFileName().substring(formfile.getFileName().lastIndexOf('.')+1).toLowerCase();
	}
	
	/**
	 * 保存文件
	 * @param savedir 存放目录
	 * @param fileName 文件名称
	 * @param data 保存的内容
	 * @return 保存的文件
	 * @throws Exception
	 */
	public static File saveFile(File savedir, String fileName, byte[] data) throws Exception{
		if(!savedir.exists()) savedir.mkdirs();//如果目录不存在就创建
		File file = new File(savedir, fileName);
		FileOutputStream fileoutstream = new FileOutputStream(file);
		fileoutstream.write(data);
		fileoutstream.close();
		return file;
	}
}
