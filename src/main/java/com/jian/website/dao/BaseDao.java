package com.jian.website.dao;

import java.util.List;
import java.util.Map;

import com.jian.annotation.PrimaryKeyCondition;

/**
 * @author liujian
 * @Date  
 */
public interface BaseDao<T>  {
	
	public int add(T object);
	public int add(T object, String tableName);
	public int add(T object, List<PrimaryKeyCondition> pkeys, String tableName);
	public int batchAdd(List<T> objects);
	public int batchAdd(List<T> objects, String tableName);
	public int batchAdd(List<T> objects, List<PrimaryKeyCondition> pkeys, String tableName);
	public T add2(T object);
	public T add2(T object, String tableName);
	public T add2(T object, List<PrimaryKeyCondition> pkeys, String tableName);
	
	public int modify(T object);
	public int modify(T object, String tableName);
	public int modify(T object, List<PrimaryKeyCondition> pkeys, String tableName);
	public int batchModify(List<T> objects);
	public int batchModify(List<T> objects, String tableName);
	public int batchModify(List<T> objects, List<PrimaryKeyCondition> pkeys, String tableName);
	public int modify(Map<String, Object> updateValue, Map<String, Object> updateCondition);
	public int modify(Map<String, Object> updateValue, Map<String, Object> updateCondition, String tableName);
	
	public int delete(Map<String, Object> deleteCondition);
	public int delete(Map<String, Object> deleteCondition, String tableName);
	public int delete(String wsql, Map<String, Object> deleteCondition);
	public int delete(String wsql, Map<String, Object> deleteCondition, String tableName);
	public int batchDelete(String column, List<String> columnValues);
	public int batchDelete(String column, List<String> columnValues, String tableName);
	
	public List<T> findList();
	public List<T> findList(String tableName);
	public List<T> findList(int start, int rows);
	public List<T> findList(int start, int rows, String tableName);
	public List<T> findList(Map<String, Object> queryCondition);
	public List<T> findList(Map<String, Object> queryCondition, String tableName);
	public List<T> findList(Map<String, Object> queryCondition, int start, int rows);
	public List<T> findList(Map<String, Object> queryCondition, int start, int rows, String tableName);
	public List<T> findList(String wsql, Map<String, Object> queryCondition);
	public List<T> findList(String wsql, Map<String, Object> queryCondition, String tableName);
	public List<T> findList(String wsql, Map<String, Object> queryCondition, int start, int rows);
	public List<T> findList(String wsql, Map<String, Object> queryCondition, int start, int rows, String tableName);
	
	public T findObject(Map<String, Object> queryCondition);
	public T findObject(Map<String, Object> queryCondition, String tableName);
	public T findObject(String wsql, Map<String, Object> queryCondition);
	public T findObject(String wsql, Map<String, Object> queryCondition, String tableName);
	
	public List<Map<String, Object>> findMapList(List<String> columns, Map<String, Object> queryCondition);
	public List<Map<String, Object>> findMapList(List<String> columns, Map<String, Object> queryCondition, String tableName);
	public List<Map<String, Object>> findMapList(List<String> columns, Map<String, Object> queryCondition, int start, int rows);
	public List<Map<String, Object>> findMapList(List<String> columns, Map<String, Object> queryCondition, int start, int rows,  String tableName);
	public List<Map<String, Object>> findMapList(List<String> columns, String wsql, Map<String, Object> queryCondition);
	public List<Map<String, Object>> findMapList(List<String> columns, String wsql, Map<String, Object> queryCondition, String tableName);
	public List<Map<String, Object>> findMapList(List<String> columns, String wsql, Map<String, Object> queryCondition, int start, int rows);
	public List<Map<String, Object>> findMapList(List<String> columns, String wsql, Map<String, Object> queryCondition, int start, int rows,  String tableName);
	
	public Map<String, Object> findMap(List<String> columns, Map<String, Object> queryCondition);
	public Map<String, Object> findMap(List<String> columns, Map<String, Object> queryCondition, String tableName);
	public Map<String, Object> findMap(List<String> columns, String wsql, Map<String, Object> queryCondition);
	public Map<String, Object> findMap(List<String> columns, String wsql, Map<String, Object> queryCondition, String tableName);

	public long size();
	public long size(String tableName);
	public long size(Map<String, Object> queryCondition);
	public long size(Map<String, Object> queryCondition, String tableName);
	public long size(String wsql, Map<String, Object> queryCondition);
	public long size(String wsql, Map<String, Object> queryCondition, String tableName);

	//支持sql
	public int baseUpdate(String sql, Map<String, Object> condition);
	public <E> List<E> baseQuery(String sql, Map<String, Object> condition, Class<E> clzz);
	public List<Map<String, Object>> baseQuery(String sql, Map<String, Object> condition);
	public <E> List<E> basePage(String sql, Map<String, Object> condition, Class<E> clzz, int start, int rows);
	public List<Map<String, Object>> basePage(String sql, Map<String, Object> condition, int start, int rows);
	public long baseSize(String sql, Map<String, Object> condition);
	
}
