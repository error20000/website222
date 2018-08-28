package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Active;
import com.jian.website.dao.ActiveDao;
import com.jian.website.service.ActiveService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ActiveServiceImpl extends BaseServiceImpl<Active> implements ActiveService {

	@Autowired
	private ActiveDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
