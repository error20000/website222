package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.BespeakType;
import com.jian.website.dao.BespeakTypeDao;
import com.jian.website.service.BespeakTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class BespeakTypeServiceImpl extends BaseServiceImpl<BespeakType> implements BespeakTypeService {

	@Autowired
	private BespeakTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
