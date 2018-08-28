package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.ActiveConfigAttrTp;
import com.jian.website.dao.ActiveConfigAttrTpDao;
import com.jian.website.service.ActiveConfigAttrTpService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ActiveConfigAttrTpServiceImpl extends BaseServiceImpl<ActiveConfigAttrTp> implements ActiveConfigAttrTpService {

	@Autowired
	private ActiveConfigAttrTpDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
