package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Business;
import com.jian.website.dao.BusinessDao;
import com.jian.website.service.BusinessService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class BusinessServiceImpl extends BaseServiceImpl<Business> implements BusinessService {

	@Autowired
	private BusinessDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
