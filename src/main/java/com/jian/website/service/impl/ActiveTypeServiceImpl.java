package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.ActiveType;
import com.jian.website.dao.ActiveTypeDao;
import com.jian.website.service.ActiveTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ActiveTypeServiceImpl extends BaseServiceImpl<ActiveType> implements ActiveTypeService {

	@Autowired
	private ActiveTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
