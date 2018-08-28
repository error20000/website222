package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.BtnClick;
import com.jian.website.dao.BtnClickDao;
import com.jian.website.service.BtnClickService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class BtnClickServiceImpl extends BaseServiceImpl<BtnClick> implements BtnClickService {

	@Autowired
	private BtnClickDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
