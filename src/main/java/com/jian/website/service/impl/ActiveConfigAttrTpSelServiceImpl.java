package com.jian.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.website.entity.ActiveConfigAttrTpSel;
import com.jian.website.dao.ActiveConfigAttrTpSelDao;
import com.jian.website.service.ActiveConfigAttrTpSelService;

/**
 * @author liujian
 * @Date  
 */
@Service
public class ActiveConfigAttrTpSelServiceImpl extends BaseServiceImpl<ActiveConfigAttrTpSel> implements ActiveConfigAttrTpSelService {

	@Autowired
	private ActiveConfigAttrTpSelDao dao;
	
	@Override
	public void initDao() {
		super.baseDao = dao;
	}

}
