package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Video;
import com.jian.website.dao.VideoDao;
import com.jian.website.service.VideoService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class VideoServiceImpl extends BaseServiceImpl<Video> implements VideoService {

	@Autowired
	private VideoDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
