package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Server;
import com.jian.website.dao.ServerDao;
import com.jian.website.service.ServerService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ServerServiceImpl extends BaseServiceImpl<Server> implements ServerService {

	@Autowired
	private ServerDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
