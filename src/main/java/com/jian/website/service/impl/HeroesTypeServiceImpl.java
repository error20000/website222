package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.HeroesType;
import com.jian.website.dao.HeroesTypeDao;
import com.jian.website.service.HeroesTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class HeroesTypeServiceImpl extends BaseServiceImpl<HeroesType> implements HeroesTypeService {

	@Autowired
	private HeroesTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
