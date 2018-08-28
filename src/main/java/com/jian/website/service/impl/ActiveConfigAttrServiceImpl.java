package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.ActiveConfigAttr;
import com.jian.website.dao.ActiveConfigAttrDao;
import com.jian.website.service.ActiveConfigAttrService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ActiveConfigAttrServiceImpl extends BaseServiceImpl<ActiveConfigAttr> implements ActiveConfigAttrService {

	@Autowired
	private ActiveConfigAttrDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
