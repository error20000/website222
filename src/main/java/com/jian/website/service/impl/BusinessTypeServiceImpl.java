package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.BusinessType;
import com.jian.website.dao.BusinessTypeDao;
import com.jian.website.service.BusinessTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class BusinessTypeServiceImpl extends BaseServiceImpl<BusinessType> implements BusinessTypeService {

	@Autowired
	private BusinessTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
