package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.NewsType;
import com.jian.website.dao.NewsTypeDao;
import com.jian.website.service.NewsTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class NewsTypeServiceImpl extends BaseServiceImpl<NewsType> implements NewsTypeService {

	@Autowired
	private NewsTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
