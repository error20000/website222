package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.DownloadType;
import com.jian.website.dao.DownloadTypeDao;
import com.jian.website.service.DownloadTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class DownloadTypeServiceImpl extends BaseServiceImpl<DownloadType> implements DownloadTypeService {

	@Autowired
	private DownloadTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
