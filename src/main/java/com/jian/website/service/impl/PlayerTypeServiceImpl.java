package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.PlayerType;
import com.jian.website.dao.PlayerTypeDao;
import com.jian.website.service.PlayerTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class PlayerTypeServiceImpl extends BaseServiceImpl<PlayerType> implements PlayerTypeService {

	@Autowired
	private PlayerTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
