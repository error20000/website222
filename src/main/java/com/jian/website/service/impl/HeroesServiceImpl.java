package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Heroes;
import com.jian.website.dao.HeroesDao;
import com.jian.website.service.HeroesService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class HeroesServiceImpl extends BaseServiceImpl<Heroes> implements HeroesService {

	@Autowired
	private HeroesDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
