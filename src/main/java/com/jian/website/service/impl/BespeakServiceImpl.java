package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Bespeak;
import com.jian.website.dao.BespeakDao;
import com.jian.website.service.BespeakService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class BespeakServiceImpl extends BaseServiceImpl<Bespeak> implements BespeakService {

	@Autowired
	private BespeakDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
