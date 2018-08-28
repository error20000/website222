package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.PictureType;
import com.jian.website.dao.PictureTypeDao;
import com.jian.website.service.PictureTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class PictureTypeServiceImpl extends BaseServiceImpl<PictureType> implements PictureTypeService {

	@Autowired
	private PictureTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
