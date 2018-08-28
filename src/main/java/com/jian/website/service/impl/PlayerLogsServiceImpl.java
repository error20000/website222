package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.PlayerLogs;
import com.jian.website.dao.PlayerLogsDao;
import com.jian.website.service.PlayerLogsService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class PlayerLogsServiceImpl extends BaseServiceImpl<PlayerLogs> implements PlayerLogsService {

	@Autowired
	private PlayerLogsDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
