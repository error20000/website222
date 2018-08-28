package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Pv;
import com.jian.website.dao.PvDao;
import com.jian.website.service.PvService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class PvServiceImpl extends BaseServiceImpl<Pv> implements PvService {

	@Autowired
	private PvDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
