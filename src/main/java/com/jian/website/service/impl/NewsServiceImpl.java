package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.News;
import com.jian.website.dao.NewsDao;
import com.jian.website.service.NewsService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {

	@Autowired
	private NewsDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
