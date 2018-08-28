package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.EmailLogs;
import com.jian.website.dao.EmailLogsDao;
import com.jian.website.service.EmailLogsService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class EmailLogsServiceImpl extends BaseServiceImpl<EmailLogs> implements EmailLogsService {

	@Autowired
	private EmailLogsDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
