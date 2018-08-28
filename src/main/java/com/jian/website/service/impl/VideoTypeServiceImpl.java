package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.VideoType;
import com.jian.website.dao.VideoTypeDao;
import com.jian.website.service.VideoTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class VideoTypeServiceImpl extends BaseServiceImpl<VideoType> implements VideoTypeService {

	@Autowired
	private VideoTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
