package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Recommend;
import com.jian.website.dao.RecommendDao;
import com.jian.website.service.RecommendService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class RecommendServiceImpl extends BaseServiceImpl<Recommend> implements RecommendService {

	@Autowired
	private RecommendDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
