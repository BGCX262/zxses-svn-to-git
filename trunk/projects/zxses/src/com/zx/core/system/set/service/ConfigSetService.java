package com.zx.core.system.set.service;

import java.util.List;

import com.zx.core.base.service.BaseService;
import com.zx.core.exception.ManageException;
import com.zx.core.model.SysParameter;
import com.zx.core.system.set.dao.ConfigSetDAO;
import com.zx.core.util.SysLoggerUtil;
import com.zx.core.vo.ConfigSetVO;

public class ConfigSetService extends BaseService implements IConfigSetService {

	static SysLoggerUtil logger = SysLoggerUtil.getSysLogger(ConfigSetService.class.getName());
	private ConfigSetDAO configSetDAO;
	private ConfigSetVO configSetVO;

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
	 * @return the configSetDAO
	 */
	public ConfigSetDAO getConfigSetDAO() {
		return configSetDAO;
	}

	/**
	 * @param configSetDAO
	 *            the configSetDAO to set
	 */
	public void setConfigSetDAO(ConfigSetDAO configSetDAO) {
		this.configSetDAO = configSetDAO;
	}

	/**
	 * @return
	 * @Description: 查询配置
	 * @param
	 */
	public ConfigSetVO findConfigSet() throws ManageException {
		try {
			List<SysParameter> list = configSetDAO.findSysParameters();
			SysParameter sp = new SysParameter();
			ConfigSetVO configSetVO = new ConfigSetVO();
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					sp = list.get(i);
					if ("system_name".equals(sp.getCode())) {
						configSetVO.setSystemName(sp.getContent());
					}
					if ("copyright".equals(sp.getCode())) {
						configSetVO.setCopyRight(sp.getContent());
					}
					if ("logo_image".equals(sp.getCode())) {
						configSetVO.setLogoImage(sp.getContent());
					}
					if ("check_code".equals(sp.getCode())) {
						configSetVO.setCheckCode(sp.getContent().equals("1")?true:false);
					}
					if ("fail_lock".equals(sp.getCode())) {
						configSetVO.setFailLock(sp.getContent().equals("1")?true:false);
					}
					if ("ip_control".equals(sp.getCode())) {
						configSetVO.setIpControl(sp.getContent().equals("1")?true:false);
					}
					if ("length_limit".equals(sp.getCode())) {
						configSetVO.setLengthLimit(sp.getContent().equals("1")?true:false);
					}
					if ("mix_number".equals(sp.getCode())) {
						configSetVO.setMixNumber(sp.getContent().equals("1")?true:false);
					}
					if ("check_password".equals(sp.getCode())) {
						configSetVO.setCheckPassword(sp.getContent().equals("1")?true:false);
					}
				}
			}
			return configSetVO;
		} catch (Exception e) {
			throw new ManageException("查询系统配置信息出错", e);
		}
	}
	
	/**
	 * @return
	 * @Description: 修改配置
	 * @param
	 */
	public void updateConfigSet(ConfigSetVO configSetVO) throws ManageException {
		try {
			List<SysParameter> list = configSetDAO.findSysParameters();
			SysParameter sp = new SysParameter();
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					sp = list.get(i);
					if ("system_name".equals(sp.getCode())) {
						configSetDAO.updateSysParameters("system_name",configSetVO.getSystemName());
					}
					if ("copyright".equals(sp.getCode())) {
						configSetDAO.updateSysParameters("copyright",configSetVO.getCopyRight());
					}
					if ("logo_image".equals(sp.getCode())) {
						configSetDAO.updateSysParameters("logo_image",configSetVO.getLogoImage());
					}
					if ("check_code".equals(sp.getCode())) {
						configSetDAO.updateSysParameters("check_code",configSetVO.getCheckCode()?"1":"0");
					}
					if ("fail_lock".equals(sp.getCode())) {
						configSetDAO.updateSysParameters("fail_lock",configSetVO.getFailLock()?"1":"0");
					}
					if ("ip_control".equals(sp.getCode())) {
						configSetDAO.updateSysParameters("ip_control",configSetVO.getIpControl()?"1":"0");
					}
					if ("length_limit".equals(sp.getCode())) {
						configSetDAO.updateSysParameters("length_limit",configSetVO.getLengthLimit()?"1":"0");
					}
					if ("mix_number".equals(sp.getCode())) {
						configSetDAO.updateSysParameters("mix_number",configSetVO.getMixNumber()?"1":"0");
					}
					if ("check_password".equals(sp.getCode())) {
						configSetDAO.updateSysParameters("check_password",configSetVO.getCheckPassword()?"1":"0");
					}
				}
			}
		} catch (Exception e) {
			throw new ManageException("修改系统配置信息出错", e);
		}
	}
}
