package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.User;
import com.jian.website.dao.UserDao;
import com.jian.website.service.UserService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
