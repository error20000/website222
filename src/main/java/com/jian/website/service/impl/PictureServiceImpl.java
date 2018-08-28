package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Picture;
import com.jian.website.dao.PictureDao;
import com.jian.website.service.PictureService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class PictureServiceImpl extends BaseServiceImpl<Picture> implements PictureService {

	@Autowired
	private PictureDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
