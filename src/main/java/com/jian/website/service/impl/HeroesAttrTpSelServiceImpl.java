package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.HeroesAttrTpSel;
import com.jian.website.dao.HeroesAttrTpSelDao;
import com.jian.website.service.HeroesAttrTpSelService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class HeroesAttrTpSelServiceImpl extends BaseServiceImpl<HeroesAttrTpSel> implements HeroesAttrTpSelService {

	@Autowired
	private HeroesAttrTpSelDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
