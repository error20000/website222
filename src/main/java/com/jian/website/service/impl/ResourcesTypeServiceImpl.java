package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.ResourcesType;
import com.jian.website.dao.ResourcesTypeDao;
import com.jian.website.service.ResourcesTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ResourcesTypeServiceImpl extends BaseServiceImpl<ResourcesType> implements ResourcesTypeService {

	@Autowired
	private ResourcesTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
