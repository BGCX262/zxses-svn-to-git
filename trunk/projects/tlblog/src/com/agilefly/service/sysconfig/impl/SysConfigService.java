package com.agilefly.service.sysconfig.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agilefly.bean.SysConfig;
import com.agilefly.service.base.BaseDaoImpl;
import com.agilefly.service.sysconfig.ISysConfigService;

@Service
@Transactional
public class SysConfigService extends BaseDaoImpl<SysConfig> implements ISysConfigService {
}
