package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Strategy;
import com.jian.website.dao.StrategyDao;
import com.jian.website.service.StrategyService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class StrategyServiceImpl extends BaseServiceImpl<Strategy> implements StrategyService {

	@Autowired
	private StrategyDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
