package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.Player;
import com.jian.website.dao.PlayerDao;
import com.jian.website.service.PlayerService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class PlayerServiceImpl extends BaseServiceImpl<Player> implements PlayerService {

	@Autowired
	private PlayerDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
