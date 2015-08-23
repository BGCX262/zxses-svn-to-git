package com.zx.core.system.user.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.zx.core.base.action.BaseAction;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserType;
import com.zx.core.model.SysRole;
import com.zx.core.system.user.service.IHrUserService;
import com.zx.core.util.DateUtil;

public class HrUserAction extends BaseAction {

	private static final long serialVersionUID = -8350569792282662155L;
	private IHrUserService hrUserService;

	private HrUserType hrUserType;
	private List<HrUserType> hrUserTypes;
	private HrUser hrUserOp;

	private HrUser hrUser;
	private List<HrUser> hrUsers;
	private Boolean isEnable;

	private InputStream downloadFile;// 下载文件的来源流
	private String dataFileName;// 下载的文件名
	private List<HrUser> exportList;

	private File importFile;// 导入的文件
	private String importFileFileName;// 导入的文件名
	// 下载文件的路劲（从根目录下开始）
	private String downFilePath;

	// 用户分配角色
	private List<SysRole> leftSysRole;
	private List<SysRole> rightSysRole;
	private List<SysRole> selectSysRole;

	public IHrUserService getHrUserService() {
		return hrUserService;
	}

	public void setHrUserService(IHrUserService hrUserService) {
		this.hrUserService = hrUserService;
	}

	public HrUserType getHrUserType() {
		return hrUserType;
	}

	public void setHrUserType(HrUserType hrUserType) {
		this.hrUserType = hrUserType;
	}

	public HrUser getHrUser() {
		return hrUser;
	}

	public void setHrUser(HrUser hrUser) {
		this.hrUser = hrUser;
	}

	public List<HrUser> getHrUsers() {
		return hrUsers;
	}

	public void setHrUsers(List<HrUser> hrUsers) {
		this.hrUsers = hrUsers;
	}

	public List<HrUserType> getHrUserTypes() {
		return hrUserTypes;
	}

	public void setHrUserTypes(List<HrUserType> hrUserTypes) {
		this.hrUserTypes = hrUserTypes;
	}

	public HrUser getHrUserOp() {
		return hrUserOp;
	}

	public void setHrUserOp(HrUser hrUserOp) {
		this.hrUserOp = hrUserOp;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getCheckIds() {
		return checkIds;
	}

	public void setCheckIds(String checkIds) {
		this.checkIds = checkIds;
	}

	public String getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public List<HrUser> getExportList() {
		return exportList;
	}

	public void setExportList(List<HrUser> exportList) {
		this.exportList = exportList;
	}

	public void setDownloadFile(InputStream downloadFile) {
		this.downloadFile = downloadFile;
	}

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
	}

	public String getImportFileFileName() {
		return importFileFileName;
	}

	public void setImportFileFileName(String importFileFileName) {
		this.importFileFileName = importFileFileName;
	}

	public String getDownFilePath() {
		return downFilePath;
	}

	public void setDownFilePath(String downFilePath) {
		this.downFilePath = downFilePath;
	}

	public List<SysRole> getLeftSysRole() {
		return leftSysRole;
	}

	public void setLeftSysRole(List<SysRole> leftSysRole) {
		this.leftSysRole = leftSysRole;
	}

	public List<SysRole> getRightSysRole() {
		return rightSysRole;
	}

	public void setRightSysRole(List<SysRole> rightSysRole) {
		this.rightSysRole = rightSysRole;
	}

	public List<SysRole> getSelectSysRole() {
		return selectSysRole;
	}

	public void setSelectSysRole(List<SysRole> selectSysRole) {
		this.selectSysRole = selectSysRole;
	}

	/***************************************************************************
	 * 显示用户信息的列表页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String list() {
		try {
			hrUserTypes = this.hrUserService.findHrUserTypes();
			hrUsers = this.hrUserService.findHrUsers(hrUserType, hrUserOp,
					getPaginate());
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return "list";
	}

	/***************************************************************************
	 * 转跳到新增用户页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String entryAdd() {
		try {
			hrUserTypes = this.hrUserService.findHrUserTypes();
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return "entryAdd";
	}

	public void validateSave() {
		try {
			hrUserTypes = this.hrUserService.findHrUserTypes();
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
	}

	/***************************************************************************
	 * 保存用户信息
	 * 
	 * @return
	 */
	public String save() {
		try {
			if (this.hrUserService.isExistHrUser(hrUser)) {
				this
						.addFieldError("hrUser.account",
								getText("account.isExist"));
			}
			if (getFieldErrors().size() > 0) {
				return "entryAdd";
			}
			hrUser.setIsEnable(getIsEnable() ? 1 : 0);
			hrUserService.saveHrUser(hrUser);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("save.fail");
		}
		this.addMessageInfo("save.success");
		setHrUser(null);
		return "entryAdd";
	}

	/***************************************************************************
	 * 转跳到修改页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String entryUpdate() {
		try {
			hrUserTypes = this.hrUserService.findHrUserTypes();
			setHrUser(this.hrUserService.findHrUser(hrUser.getId()));
			if (hrUser.getIsEnable() == 0) {
				setIsEnable(false);
			} else {
				setIsEnable(true);
			}
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return "entryUpdate";
	}

	public void validateUpdate() {
		try {
			hrUserTypes = this.hrUserService.findHrUserTypes();
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
	}

	/***************************************************************************
	 * 保存修改之后的用户信息
	 * 
	 * @return
	 */
	public String update() {
		try {
			if (this.hrUserService.isExistHrUser(hrUser)) {
				this
						.addFieldError("hrUser.account",
								getText("account.isExist"));
			}
			if (getFieldErrors().size() > 0) {
				return "entryUpdate";
			}
			hrUser.setIsEnable(getIsEnable() ? 1 : 0);
			this.hrUserService.updateHrUser(hrUser);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("update.failure");
			return "entryUpdate";
		}
		this.addMessageInfo("update.success");
		return "entryUpdate";
	}

	/***************************************************************************
	 * 删除用户信息
	 * 
	 * @return
	 */
	@SkipValidation
	public String delete() {
		try {
			this.hrUserService.deleteHrUsers(checkIds);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("delete.failure");
			return list();
		}
		this.addMessageInfo("delete.success");
		return list();
	}

	/***************************************************************************
	 * 重置用户密码
	 * 
	 * @return
	 */
	@SkipValidation
	public String resetPassword() {
		try {
			this.hrUserService.updateResetPassword(checkIds);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("operate.failure");
			return list();
		}
		this.addMessageInfo("operate.success");
		return list();
	}

	/***************************************************************************
	 * 下载用户信息
	 * 
	 * @return
	 */
	@SkipValidation
	public String exportHrUserData() {
		setDataFileName("用户基本信息数据.xls");
		try {
			exportList = this.hrUserService.findExportUsers();
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("export.failure");
			return list();
		}
		return "exportSuccess";
	}

	/***************************************************************************
	 * 获得文件名称
	 * 
	 * @return
	 */
	@SkipValidation
	public String getFileName() {
		try {
			dataFileName = new String(dataFileName.getBytes(), "iso-8859-1");
			if (StringUtils.isEmpty(dataFileName)) {
				dataFileName = "未命名.xls";
			}
		} catch (UnsupportedEncodingException e) {
			dataFileName = "未命名.xls";
		}
		return dataFileName;
	}

	/***************************************************************************
	 * 构造输出流
	 * 
	 * @return
	 */
	@SkipValidation
	public InputStream getDownloadFile() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(dataFileName.substring(0, dataFileName
				.indexOf(".")));
		int rowNum = 0; // 标题开始行
		int colNum = 0; // 标题开始列
		String[] excelHead = { "登录名（*）", "用户名（*）", "用户类型", "邮箱", "是否启用", "创建时间" };

		// 单元格head样式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFFont hssfFont = wb.createFont();
		hssfFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(hssfFont);

		HSSFCellStyle cellStyle2 = wb.createCellStyle();
		HSSFFont hssfFont2 = wb.createFont();
		hssfFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		hssfFont2.setColor(HSSFColor.RED.index);
		cellStyle2.setFont(hssfFont2);

		// HSSFRow excell的头
		HSSFRow row = sheet.createRow(rowNum++);
		// HSSFCell excell的格子单元
		HSSFCell cell = null;
		for (String s : excelHead) {
			cell = row.createCell((short) colNum++);
			cell.setCellValue(s);
			if ("登录名（*）".equals(s) || "用户名（*）".equals(s)) {
				cell.setCellStyle(cellStyle2);
			} else {
				cell.setCellStyle(cellStyle);
			}
		}

		Map tmpMap = null;
		for (HrUser tmpHrUser : exportList) {
			row = sheet.createRow(rowNum++);
			colNum = 0;
			cell = row.createCell((short) colNum++);
			cell.setCellValue(new HSSFRichTextString(tmpHrUser.getAccount()
					.toString()));
			cell = row.createCell((short) colNum++);
			cell.setCellValue(new HSSFRichTextString(tmpHrUser.getName()
					.toString()));
			cell = row.createCell((short) colNum++);
			if (tmpHrUser.getHrUserType() == null
					|| StringUtils.isEmpty(tmpHrUser.getHrUserType()
							.getContent())) {
				cell.setCellValue(new HSSFRichTextString(""));
			} else {
				cell.setCellValue(new HSSFRichTextString(tmpHrUser
						.getHrUserType().getContent()));
			}
			cell = row.createCell((short) colNum++);
			if (StringUtils.isEmpty(tmpHrUser.getEmail())) {
				cell.setCellValue(new HSSFRichTextString(""));
			} else {
				cell.setCellValue(new HSSFRichTextString(tmpHrUser.getEmail()
						.toString()));
			}
			cell = row.createCell((short) colNum++);
			if (tmpHrUser.getIsEnable() == null) {
				cell.setCellValue(new HSSFRichTextString(""));
			} else {
				cell.setCellValue(new HSSFRichTextString(1 == tmpHrUser
						.getIsEnable().intValue() ? "是" : "否"));
			}
			cell = row.createCell((short) colNum++);
			if (tmpHrUser.getCreateTime() != null) {
				cell.setCellValue(new HSSFRichTextString(sdf.format(tmpHrUser
						.getCreateTime())));
			} else {
				cell.setCellValue(new HSSFRichTextString(""));
			}

		}
		File file = new File("abc.xls");
		// 创建一个File 拿来当缓存用.也就是先将内存中的excel写入File中.然后再将File转换成输出流
		try {
			OutputStream out = new FileOutputStream(file);
			wb.write(out);// 写入File
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		InputStream in = null;

		try {
			in = new FileInputStream(file);// 将file转换成输入流
			// in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return in;
	}

	/***************************************************************************
	 * 输出下载模板的流
	 * 
	 * @return
	 */
	public InputStream getDownloadFile1() {
		File tmplateFile = new File(getRequest().getRealPath("/")
				+ downFilePath);
		InputStream in = null;

		try {
			in = new FileInputStream(tmplateFile);// 将file转换成输入流
			// in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}

	/***************************************************************************
	 * 下载用户基本信息表模板
	 * 
	 * @return
	 */
	@SkipValidation
	public String downHrUserExcel() {
		setDataFileName("用户基本信息表模板.xls");
		setDownFilePath("core\\system\\user\\template\\user_template.xls");
		return "downSuccess";
	}

	/***************************************************************************
	 * 判断用户选择了那些字段需要进行数据导入
	 * 
	 * @return：需要导入的字段的index
	 */
	public Map<String, String> importCheckField(String exportCheckFieldAllStr) {
		Map<String, String> returnIntList = new HashMap<String, String>();

		String[] exportAllArray = exportCheckFieldAllStr.split(",");
		Map<String, String> exportAllMap = new HashMap<String, String>();
		for (int i = 0; i < exportAllArray.length; i++) {
			exportAllMap.put(exportAllArray[i], "" + i);
		}
		return exportAllMap;
	}

	/***************************************************************************
	 * 将Excel中的内容加载到List中，首先List中的元素Map封装的列Index，然后将列Index，修改为列对应的值
	 * 
	 * @param importField
	 * @param insertListMap:存储新增的键值对
	 * @param updateListMap：存储修改的键值对
	 * @param list：提供给field字段进行匹配的集合，如，如果field的值，存在于数据库，则更新，否则插入数据库
	 * @param field：哪个字段需要判断重复
	 * @return
	 */
	public void importExcelMap(Map<String, String> importField,
			List<Map<String, String>> insertListMap,
			List<Map<String, String>> updateListMap, List<HrUser> hrUserList,
			String field) {
		HSSFWorkbook workbook;
		Map<String, String> tmpImportField;
		// 账号是否存在
		boolean isExtAccount = false;
		boolean isFKFieldValue = false;
		// tmpImportField.putAll(importField);
		// Excel中对应的值
		String fieldValue;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(importFile));
			HSSFSheet sheet = workbook.getSheetAt(0);
			String tmpActionMessage = "";
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				tmpActionMessage = "";
				HSSFRow row = sheet.getRow(i);
				tmpImportField = new HashMap<String, String>();
				tmpImportField.putAll(importField);
				isExtAccount = false;
				for (String key : tmpImportField.keySet()) {
					fieldValue = "";
					HSSFCell cel = row.getCell(Short.parseShort(tmpImportField
							.get(key)));

					if (cel != null) {
						try {
							fieldValue = cel.getRichStringCellValue()
									.getString();
						} catch (Exception e) {
							try {
								fieldValue = "" + cel.getNumericCellValue();
							} catch (Exception e1) {
								fieldValue = cel.getDateCellValue().toString();
							}
						}
					}

					HSSFCell headCell = sheet.getRow(0).getCell(
							Short.parseShort(tmpImportField.get(key)));
					HSSFFont tmpFont = workbook.getFontAt(headCell
							.getCellStyle().getFontIndex());
					if (tmpFont.getColor() == HSSFColor.RED.index
							&& StringUtils.isEmpty(fieldValue)) {
						tmpActionMessage += "【"
								+ headCell.getRichStringCellValue() + "】";

					}

					// 如果是用户类别
					if ("HR_USER_TYPE_ID".equals(key)) {
						// 如果为空就没必要进行循环了
						if (StringUtils.isEmpty(fieldValue)) {
							tmpImportField.put(key, null);
						} else {
							isFKFieldValue = false;
							for (HrUserType tmpHrUserType : hrUserTypes) {
								if (fieldValue.equals(tmpHrUserType
										.getContent())) {
									tmpImportField.put(key, tmpHrUserType
											.getId().toString());
									isFKFieldValue = true;
									break;
								}
							}
							// 当数据库中不存在此名称的外键，则设置为空
							if (!isFKFieldValue) {
								tmpImportField.put(key, null);
							}
						}
					}
					// 是否启用用户
					else if ("IS_ENABLE".equals(key)) {
						// 如果为空就没必要进行循环了
						if (StringUtils.isEmpty(fieldValue)) {
							tmpImportField.put(key, "1");
						} else {
							if (fieldValue.equals("是")) {
								tmpImportField.put(key, "1");
							} else {
								tmpImportField.put(key, "0");
							}
						}
					} else if ("CREATE_TIME".equals(key)) {
						if (StringUtils.isEmpty(fieldValue)) {
							tmpImportField.put(key, DateUtil.getInstance()
									.getSystemTimeStr());
						} else {
							tmpImportField.put(key, fieldValue);
						}
					} else {
						tmpImportField.put(key, fieldValue);
					}
				}

				if (StringUtils.isNotEmpty(tmpActionMessage)) {
					this.addActionMessage("第" + (i + 1) + "行,"
							+ tmpActionMessage + "为必填字段!");
				}

				// 由于界面已经将登录名、用户名设置为必填，所以通过登录名来确定登录名是否在数据库中存在
				for (HrUser tmpHrUser : hrUserList) {
					if (tmpImportField.get(field)
							.equals(tmpHrUser.getAccount())) {
						isExtAccount = true;
						break;
					}
				}
				if (isExtAccount) {
					updateListMap.add(tmpImportField);
				} else {
					insertListMap.add(tmpImportField);
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***************************************************************************
	 * 转跳到用户导入页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String importHrUserIndex() {
		return "import";
	}

	/***************************************************************************
	 * 导入用户信息
	 * 
	 * @return
	 */
	@SkipValidation
	public String importHrUserData() {
		try {
			if (StringUtils.isEmpty(importFileFileName)) {
				this
						.addFieldError("importFileFileName",
								getText("import.null"));
				return importHrUserIndex();
			}
			
			int suffixNameIndex=importFileFileName.lastIndexOf(".");
			if(suffixNameIndex<0 || !"xls".equals(importFileFileName.substring(suffixNameIndex+1))){
				this
						.addFieldError("importFileFileName",
								getText("import.format"));
				return importHrUserIndex();
			}

			hrUserTypes = this.hrUserService.findHrUserTypes();

			// 查询所有用户信息
			List<HrUser> hrUserList = this.hrUserService.findExportUsers();

			// 定义需要插入数据库的集合
			List<Map<String, String>> insertListMap = new ArrayList<Map<String, String>>();
			// 定义需要更新数据库的集合
			List<Map<String, String>> updateListMap = new ArrayList<Map<String, String>>();

			String exportAllStr = "ACCOUNT,NAME,HR_USER_TYPE_ID,EMAIL,IS_ENABLE,CREATE_TIME";
			importExcelMap(importCheckField(exportAllStr), insertListMap,
					updateListMap, hrUserList, "ACCOUNT");
			if (this.getActionMessages().size() > 0) {
				this.addMessageError("import.failure");
				return "import";
			}
			this.hrUserService.saveOrUpdateBatchHrUsers(insertListMap,
					updateListMap);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("import.failure");
			return importHrUserIndex();
		}
		this.addMessageInfo("import.success");
		return importHrUserIndex();
	}

	/***************************************************************************
	 * 转跳到人员角色页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String listUserRole() {
		try {
			leftSysRole = this.hrUserService.findUserNoSysRole(hrUser);
			rightSysRole = this.hrUserService.findUserSysRole(hrUser);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return "userRole";
	}

	/***************************************************************************
	 * 保存人员角色信息
	 * 
	 * @return
	 */
	@SkipValidation
	public String saveUserRole() {
		try {
			this.hrUserService.saveUserSysRole(hrUser, selectSysRole);
			this.addMessageInfo("save.success");
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("save.fail");
		}
		return listUserRole();
	}
}
