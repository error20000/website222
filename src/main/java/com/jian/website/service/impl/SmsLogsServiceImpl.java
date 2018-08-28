package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.SmsLogs;
import com.jian.website.dao.SmsLogsDao;
import com.jian.website.service.SmsLogsService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class SmsLogsServiceImpl extends BaseServiceImpl<SmsLogs> implements SmsLogsService {

	@Autowired
	private SmsLogsDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
