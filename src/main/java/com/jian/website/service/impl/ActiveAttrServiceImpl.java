package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.ActiveAttr;
import com.jian.website.dao.ActiveAttrDao;
import com.jian.website.service.ActiveAttrService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ActiveAttrServiceImpl extends BaseServiceImpl<ActiveAttr> implements ActiveAttrService {

	@Autowired
	private ActiveAttrDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
