package com.jian.website.service;

import java.util.List;
import java.util.Map;
import com.jian.website.dao.BaseDao;
/**
 * @author liujian
 * @Date  
 */
public interface BaseService<T> {

	public int add(T obj);
	
	public int add(List<T> objs);
	
	public T add2(T obj);
	
	public int modify(T obj);
	
	public int modify(List<T> objs);
	
	public int modify(Map<String, Object> values, Map<String, Object> condition);
	
	public int delete(Map<String, Object> condition);
	
	public int delete(String column, List<String> values);
	
	public List<T> findAll();
	
	public List<T>  findList(Map<String, Object> condition);
	
	public T findOne(Map<String, Object> condition);
	
	public List<T> findPage(Map<String, Object> condition, int start, int rows);
	
	public long size(Map<String, Object> condition);
	
	public BaseDao<T> getDao();
	
	public int  baseUpdate(String sql, Map<String, Object> condition);
	
	public <E> List<E> baseQuery(String sql, Map<String, Object> condition, Class<E> clzz);

	public <E> List<E> basePage(String sql, Map<String, Object> condition, Class<E> clzz, int start, int rows);
	
	public long baseSize(String sql, Map<String, Object> condition);
	
}
