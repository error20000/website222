package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.SystemLogs;
import com.jian.website.dao.SystemLogsDao;
import com.jian.website.service.SystemLogsService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class SystemLogsServiceImpl extends BaseServiceImpl<SystemLogs> implements SystemLogsService {

	@Autowired
	private SystemLogsDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
