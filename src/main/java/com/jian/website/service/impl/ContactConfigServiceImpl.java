package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.ContactConfig;
import com.jian.website.dao.ContactConfigDao;
import com.jian.website.service.ContactConfigService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ContactConfigServiceImpl extends BaseServiceImpl<ContactConfig> implements ContactConfigService {

	@Autowired
	private ContactConfigDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
