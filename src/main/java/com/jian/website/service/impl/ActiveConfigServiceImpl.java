package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.ActiveConfig;
import com.jian.website.dao.ActiveConfigDao;
import com.jian.website.service.ActiveConfigService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ActiveConfigServiceImpl extends BaseServiceImpl<ActiveConfig> implements ActiveConfigService {

	@Autowired
	private ActiveConfigDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
