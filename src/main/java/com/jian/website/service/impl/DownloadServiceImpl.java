package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Download;
import com.jian.website.dao.DownloadDao;
import com.jian.website.service.DownloadService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class DownloadServiceImpl extends BaseServiceImpl<Download> implements DownloadService {

	@Autowired
	private DownloadDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
