package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.GiftType;
import com.jian.website.dao.GiftTypeDao;
import com.jian.website.service.GiftTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class GiftTypeServiceImpl extends BaseServiceImpl<GiftType> implements GiftTypeService {

	@Autowired
	private GiftTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
