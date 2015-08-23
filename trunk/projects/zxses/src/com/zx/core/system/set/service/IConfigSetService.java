package com.zx.core.system.set.service;

import com.zx.core.base.service.IBaseService;
import com.zx.core.exception.ManageException;
import com.zx.core.vo.ConfigSetVO;

public interface IConfigSetService extends IBaseService {
	/**
	 * @return
	 * @Description: 查询配置
	 * @param
	 */
	public ConfigSetVO findConfigSet() throws ManageException;
	
	/**
	 * @return
	 * @Description: 修改配置
	 * @param
	 */
	public void updateConfigSet(ConfigSetVO configSetVO) throws ManageException;
}
