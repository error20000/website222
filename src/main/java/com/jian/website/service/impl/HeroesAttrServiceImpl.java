package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.HeroesAttr;
import com.jian.website.dao.HeroesAttrDao;
import com.jian.website.service.HeroesAttrService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class HeroesAttrServiceImpl extends BaseServiceImpl<HeroesAttr> implements HeroesAttrService {

	@Autowired
	private HeroesAttrDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
