package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.GiftCode;
import com.jian.website.dao.GiftCodeDao;
import com.jian.website.service.GiftCodeService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class GiftCodeServiceImpl extends BaseServiceImpl<GiftCode> implements GiftCodeService {

	@Autowired
	private GiftCodeDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
