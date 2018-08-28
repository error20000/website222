package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Share;
import com.jian.website.dao.ShareDao;
import com.jian.website.service.ShareService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ShareServiceImpl extends BaseServiceImpl<Share> implements ShareService {

	@Autowired
	private ShareDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
