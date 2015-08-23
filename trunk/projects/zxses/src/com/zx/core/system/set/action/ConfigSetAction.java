package com.zx.core.system.set.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.zx.core.base.action.BaseAction;
import com.zx.core.exception.ManageException;
import com.zx.core.system.set.service.IConfigSetService;
import com.zx.core.util.FileUtil;
import com.zx.core.vo.ConfigSetVO;

public class ConfigSetAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IConfigSetService configSetService;
	private ConfigSetVO configSetVO;
	private List<File> myFile = new ArrayList<File>();

	/**
	 * 
	 * @Description:系统配置列表
	 * @Author: feng
	 * 
	 */
	public String list() {
		try {
			configSetVO = configSetService.findConfigSet();
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("find.fail");
		}
		return LIST;
	}

	/**
	 * 
	 * @Description:保存配置
	 * @Author: feng
	 * 
	 */
	public String save() {
		try {
			if (myFile != null && myFile.size() > 0) {
				String newPath =ServletActionContext.getServletContext().getRealPath("") + "/theme/blue/images/"
				+ "system_name.jpg";
				if (myFile.get(0) != null) {
					FileUtil.copy(myFile.get(0).toString(), newPath);
				}
			}
			configSetService.updateConfigSet(configSetVO);
			addMessageInfo("update.success");
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addMessageError("update.fail");
		}
		return LIST;
	}

	/**   
	 * @return the configSetVO   
	 */
	public ConfigSetVO getConfigSetVO() {
		return configSetVO;
	}

	/**   
	 * @param configSetVO the configSetVO to set   
	 */
	public void setConfigSetVO(ConfigSetVO configSetVO) {
		this.configSetVO = configSetVO;
	}

	/**
	 * @return the configSetService
	 */
	public IConfigSetService getConfigSetService() {
		return configSetService;
	}

	/**
	 * @param configSetService
	 *            the configSetService to set
	 */
	public void setConfigSetService(IConfigSetService configSetService) {
		this.configSetService = configSetService;
	}

	/**   
	 * @return the myFile   
	 */
	public List<File> getMyFile() {
		return myFile;
	}

	/**   
	 * @param myFile the myFile to set   
	 */
	public void setMyFile(List<File> myFile) {
		this.myFile = myFile;
	}
}
