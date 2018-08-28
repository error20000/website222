package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.ContactType;
import com.jian.website.dao.ContactTypeDao;
import com.jian.website.service.ContactTypeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ContactTypeServiceImpl extends BaseServiceImpl<ContactType> implements ContactTypeService {

	@Autowired
	private ContactTypeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
