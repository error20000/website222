package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Interface;
import com.jian.website.dao.InterfaceDao;
import com.jian.website.service.InterfaceService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class InterfaceServiceImpl extends BaseServiceImpl<Interface> implements InterfaceService {

	@Autowired
	private InterfaceDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
