package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.InterfaceLogs;
import com.jian.website.dao.InterfaceLogsDao;
import com.jian.website.service.InterfaceLogsService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class InterfaceLogsServiceImpl extends BaseServiceImpl<InterfaceLogs> implements InterfaceLogsService {

	@Autowired
	private InterfaceLogsDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
