package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.RecommendType;
import com.jian.website.dao.RecommendTypeDao;
import com.jian.website.service.RecommendTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class RecommendTypeServiceImpl extends BaseServiceImpl<RecommendType> implements RecommendTypeService {

	@Autowired
	private RecommendTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
