package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Menu;
import com.jian.website.dao.MenuDao;
import com.jian.website.service.MenuService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

	@Autowired
	private MenuDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
