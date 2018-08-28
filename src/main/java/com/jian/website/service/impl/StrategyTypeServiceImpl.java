package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.StrategyType;
import com.jian.website.dao.StrategyTypeDao;
import com.jian.website.service.StrategyTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class StrategyTypeServiceImpl extends BaseServiceImpl<StrategyType> implements StrategyTypeService {

	@Autowired
	private StrategyTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
