package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.HeroesAttrTp;
import com.jian.website.dao.HeroesAttrTpDao;
import com.jian.website.service.HeroesAttrTpService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class HeroesAttrTpServiceImpl extends BaseServiceImpl<HeroesAttrTp> implements HeroesAttrTpService {

	@Autowired
	private HeroesAttrTpDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
