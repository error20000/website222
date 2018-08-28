package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Resources;
import com.jian.website.dao.ResourcesDao;
import com.jian.website.service.ResourcesService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements ResourcesService {

	@Autowired
	private ResourcesDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
