package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Contact;
import com.jian.website.dao.ContactDao;
import com.jian.website.service.ContactService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ContactServiceImpl extends BaseServiceImpl<Contact> implements ContactService {

	@Autowired
	private ContactDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
