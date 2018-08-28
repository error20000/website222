package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.ShareType;
import com.jian.website.dao.ShareTypeDao;
import com.jian.website.service.ShareTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ShareTypeServiceImpl extends BaseServiceImpl<ShareType> implements ShareTypeService {

	@Autowired
	private ShareTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
