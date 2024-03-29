package com.jian.website.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jian.annotation.Column;
import com.jian.annotation.PrimaryKey;
import com.jian.annotation.PrimaryKeyCondition;
import com.jian.annotation.Table;
import com.jian.tools.core.DateTools;
import com.jian.tools.core.JsonTools;
import com.jian.tools.core.Tools;
import com.jian.website.dao.BaseDao;

/**
 * @author liujian
 * @Date  
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	private static Map<String, List<PrimaryKeyCondition>> pkArrays = new HashMap<String ,List<PrimaryKeyCondition>>();
	protected boolean log = false;
	
	//也可使用
//	NamedParameterJdbcTemplate test = new NamedParameterJdbcTemplate(jdbcTemplate);

	//TODO save
	/**
	 * 保存对象，返回int受影响条数。
	 * @param object 被保存对象
	 * @return int 1 成功
	 */
	@Override
	public int add(T object) {
		if(object == null){
			return 0;
		}
		String tableName = getTableName();
		return add(object, tableName);
	}
	
	/**
	 * 保存对象，返回int受影响条数。
	 * @param object 被保存对象。
	 * @param tableName 表名。
	 * @return int 1 成功
	 */
	@Override
	public int add(T object, String tableName) {
		if(object == null){
			return 0;
		}
		List<PrimaryKeyCondition> pkeys = getPrimaryKeys(object.getClass());
		return add(object, pkeys, tableName);
	}
	
	/**
	 * 保存对象，返回int受影响条数。
	 * @param object 被保存对象。
	 * @param pkeys 对象主键信息，list集合。
	 * @param tableName 表名。
	 * @return int 1 成功。
	 */
	@Override
	public int add(T object, List<PrimaryKeyCondition> pkeys, String tableName) {
		int res = 0;
		if(object == null){
			return res;
		}
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		String sql = parseInsert(object, tableName);
		//解析主键
		parsePrimateKey(object, pkeys);
		
		//DEBUG
		debug(tableName, "SAVE SQL", sql);
		
		try {
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			Map<String, Object> params = Tools.parseObjectToMap(object);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();  
			int temp = jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preState = con.prepareStatement(pSql, PreparedStatement.RETURN_GENERATED_KEYS);
					setPreparedParams(preState, sqlParams, params);
					return preState;
				}
				
			}, keyHolder);
			
			if(keyHolder.getKey() == null){
				res = temp;
			}else{
				res = keyHolder.getKey().intValue(); 
			}
			
			/*res = jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preState = con.prepareStatement(pSql);
					setPreparedParams(preState, sqlParams, params);
					return preState;
				}
				
			});*/
			
		} catch (Exception e) {
			e.printStackTrace();
			debug(tableName, "SAVE ERROR", e.getMessage());
		}
		return res;
	}
	
	private String parseInsert(T object, String tableName){
		String sql = "INSERT INTO `"+tableName+"` (SQLC) VALUES (SQLV)";
		String sqlC = "";
		String sqlV = "";
		Field[] fields = Tools.getFields(object.getClass());
		Method[] methods = Tools.getMethods(object.getClass());
		//遍历属性，只有有get/set方法的属性，才加入insert.
		for (Field f : fields) {
			String fname = f.getName();
			boolean get = false;
			boolean set = false;
			for (Method m : methods) {
				if(("get"+fname).equalsIgnoreCase(m.getName())){
					get = true;
				}
				if(("set"+fname).equalsIgnoreCase(m.getName())){
					set = true;
				}
			}
			if(get && set){
				String oname = getOtherName(f);
				sqlC += ",`"+oname+"`";
				sqlV += ",:"+fname;
			}
		}
		if(!Tools.isNullOrEmpty(sqlC)){
			sql = sql.replace("SQLC", sqlC.substring(1));
		}
		if(!Tools.isNullOrEmpty(sqlV)){
			sql = sql.replace("SQLV", sqlV.substring(1));
		}
		return sql;
	}
	
	/**
	 * 批量保存对象，返回int总受影响条数。
	 * @param objects 被保存对象，list集合。
	 * @return int 1 成功。
	 */
	@Override
	public int batchAdd(List<T> objects) {
		if(objects == null || objects.size() == 0){
			return 0;
		}
		String tableName = getTableName();
		return batchAdd(objects, tableName);
	}
	

	/**
	 * 批量保存对象，返回int总受影响条数。
	 * @param objects 被保存对象，list集合。
	 * @param tableName 表名。
	 * @return int 1 成功。
	 */
	@Override
	public int batchAdd(List<T> objects, String tableName) {
		if(objects == null || objects.size() == 0){
			return 0;
		}
		List<PrimaryKeyCondition> pkeys = getPrimaryKeys(objects.get(0).getClass());
		return batchAdd(objects, pkeys, tableName);
	}
	
	/**
	 * 批量保存对象，返回int总受影响条数。
	 * @param objects 被保存对象，list集合。
	 * @param pkeys 对象主键信息，list集合。
	 * @param tableName 表名。
	 * @return int 1 成功，0 失败。
	 */
	@Override
	public int batchAdd(List<T> objects, List<PrimaryKeyCondition> pkeys, String tableName) {
		int res = 0;
		if(objects == null || objects.size() == 0){
			return res;
		}
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		T object = objects.get(0);
		String sql = parseInsert(object, tableName);
		//解析主键
		for (T obj : objects) {
			parsePrimateKey(obj, pkeys);
		}
		
		//DEBUG
		debug(tableName, "BATCH SAVE SQL", sql);
		try {
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			
			int[] tmp = jdbcTemplate.batchUpdate(pSql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Map<String, Object> params = Tools.parseObjectToMap(objects.get(i));
					setPreparedParams(ps, sqlParams, params);
				}
				
				@Override
				public int getBatchSize() {
					return objects.size();
				}
			});
			
			for (int i : tmp) {
				res += i;
			}
		} catch (Exception e) {
			e.printStackTrace();
			debug(tableName, "BATCH SAVE ERROR", e.getMessage());
		}
		return res;
	}
	
	/**
	 * 保存对象，返回int受影响条数。
	 * @param object 被保存对象
	 * @return T
	 */
	@Override
	public T add2(T object) {
		if(object == null){
			return null;
		}
		String tableName = getTableName();
		return add2(object, tableName);
	}
	
	/**
	 * 保存对象，返回int受影响条数。
	 * @param object 被保存对象。
	 * @param tableName 表名。
	 * @return T
	 */
	@Override
	public T add2(T object, String tableName) {
		if(object == null){
			return null;
		}
		List<PrimaryKeyCondition> pkeys = getPrimaryKeys(object.getClass());
		return add2(object, pkeys, tableName);
	}
	
	/**
	 * 保存对象，返回int受影响条数。
	 * @param object 被保存对象。
	 * @param pkeys 对象主键信息，list集合。
	 * @param tableName 表名。
	 * @return T
	 */
	@Override
	public T add2(T object, List<PrimaryKeyCondition> pkeys, String tableName) {
		if(object == null){
			return null;
		}
		if(Tools.isNullOrEmpty(tableName)){
			return null;
		}
		String sql = parseInsert(object, tableName);
		//解析主键
		parsePrimateKey(object, pkeys);
		
		//DEBUG
		debug(tableName, "SAVE SQL", sql);
		
		try {
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			Map<String, Object> params = Tools.parseObjectToMap(object);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();  
			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preState = con.prepareStatement(pSql, PreparedStatement.RETURN_GENERATED_KEYS);
					setPreparedParams(preState, sqlParams, params);
					return preState;
				}
				
			}, keyHolder);
			
			for (PrimaryKeyCondition pkc : pkeys) {
				switch (pkc.getKeyType()) {
				case AUTO_INCREMENT:
					switch (pkc.getType()) {
					case "int":
						setFieldValue(object, pkc.getField(), keyHolder.getKey().intValue());
						break;
					case "Integer":
						setFieldValue(object, pkc.getField(), keyHolder.getKey().intValue());
						break;
					case "long":
						setFieldValue(object, pkc.getField(), keyHolder.getKey().longValue());
						break;
					case "Long":
						setFieldValue(object, pkc.getField(), keyHolder.getKey().longValue());
						break;
					case "double":
						setFieldValue(object, pkc.getField(), keyHolder.getKey().doubleValue());
						break;
					case "Double": 
						setFieldValue(object, pkc.getField(), keyHolder.getKey().doubleValue());
						break;

					default:
						break;
					}
					
					break;

				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			debug(tableName, "SAVE ERROR", e.getMessage());
		}
		return object;
	}
	

	//TODO modify
	/**
	 * 修改对象，返回int受影响条数。
	 * @param object 被修改对象。
	 * @return int 1 成功。
	 */
	@Override
	public int modify(T object) {
		if(object == null){
			return 0;
		}
		String tableName = getTableName();
		return modify(object, tableName);
	}
	
	/**
	 * 修改对象，返回int受影响条数。
	 * @param object 被修改对象。
	 * @param tableName 表名。
	 * @return int 1 成功。
	 */
	@Override
	public int modify(T object, String tableName) {
		if(object == null){
			return 0;
		}
		List<PrimaryKeyCondition> pkeys = getPrimaryKeys(object.getClass());
		return modify(object, pkeys, tableName);
	}

	/**
	 * 修改对象，返回int受影响条数。
	 * @param object 被修改对象。
	 * @param pkeys 对象主键信息，list集合。
	 * @param tableName 表名。
	 * @return int 1 成功。
	 */
	@Override
	public int modify(T object, List<PrimaryKeyCondition> pkeys, String tableName) {
		int res = 0;
		if(object == null){
			return res;
		}
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		String sql = parseUpdate(object, pkeys, tableName);
		//DEBUG
		debug(tableName, "MODIFY SQL", sql);
		try {
			
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			Map<String, Object> params = Tools.parseObjectToMap(object);
			
			res = jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preState = con.prepareStatement(pSql);
					setPreparedParams(preState, sqlParams, params);
					return preState;
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
			//DEBUG
			debug(tableName, "MODIFY ERROR",  e.getMessage());
		}
		return res;
	}
	
	private String parseUpdate(T object, List<PrimaryKeyCondition> pkeys, String tableName){
		String sql = "UPDATE `"+tableName+"` SET SQLS WHERE SQLW";
		String sqlS = "";
		String sqlW = "";
		Field[] fields = Tools.getFields(object.getClass());
		Method[] methods = Tools.getMethods(object.getClass());
		//遍历属性，只有有get/set方法的属性，才加入update.
		for (Field f : fields) {
			String fname = f.getName();
			boolean get = false;
			boolean set = false;
			for (Method m : methods) {
				if(("get"+fname).equalsIgnoreCase(m.getName())){
					get = true;
				}
				if(("set"+fname).equalsIgnoreCase(m.getName())){
					set = true;
				}
			}
			if(get && set){
				String oname = getOtherName(f);
				sqlS += ",`"+oname+"`=:"+fname;
			}
		}
		
		for (PrimaryKeyCondition condition : pkeys) {
			String fieldName = condition.getField();
			String otherName = getOtherName(fieldName);
			sqlW += " and `"+otherName+"`=:"+fieldName;
		}
		
		if(!Tools.isNullOrEmpty(sqlS)){
			sql = sql.replace("SQLS", sqlS.substring(1));
		}
		if(!Tools.isNullOrEmpty(sqlW)){
			sql = sql.replace("SQLW", sqlW.substring(" and ".length()));
		}else{
			sql = sql.replace("SQLW", "");
		}
		return sql;
	}
	
	/**
	 * 批量修改对象，返回int总受影响条数。
	 * @param objects 被修改对象，list集合
	 * @return int 1 成功。
	 */
	@Override
	public int batchModify(List<T> objects) {
		if(objects == null || objects.size() == 0){
			return 0;
		}
		String tableName = getTableName();
		return batchModify(objects, tableName);
	}
	
	/**
	 * 批量修改对象，返回int总受影响条数。
	 * @param objects 被修改对象，list集合。
	 * @param tableName 表名。
	 * @return int 1 成功。
	 */
	@Override
	public int batchModify(List<T> objects, String tableName) {
		if(objects == null || objects.size() == 0){
			return 0;
		}
		List<PrimaryKeyCondition> pkeys = getPrimaryKeys(objects.get(0).getClass());
		return batchModify(objects, pkeys, tableName);
	}
	
	/**
	 * 批量修改对象，返回int总受影响条数。
	 * @param objects 被修改对象，list集合。
	 * @param pkeys 对象主键信息，list集合。
	 * @param tableName 表名。
	 * @return int 1 成功。
	 */
	@Override
	public int batchModify(List<T> objects, List<PrimaryKeyCondition> pkeys, String tableName) {
		int res = 0;
		if(objects == null || objects.size() == 0){
			return res;
		}
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		T object = objects.get(0);
		String sql = parseUpdate(object, pkeys, tableName);
		//DEBUG
		debug(tableName, "BATCH MODIFY SQL", sql);
		try {
//			int[] tmp = jdbcOperate.batchObject(sql, objects);

			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			
			int[] tmp = jdbcTemplate.batchUpdate(pSql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Map<String, Object> params = Tools.parseObjectToMap(objects.get(i));
					setPreparedParams(ps, sqlParams, params);
				}
				
				@Override
				public int getBatchSize() {
					return objects.size();
				}
			});
			
			for (int i : tmp) {
				res += i;
			}
		} catch (Exception e) {
			e.printStackTrace();
			//DEBUG
			debug(tableName, "BATCH MODIFY ERROR", e.getMessage());
		}
		return res;
	}
	
	
	/**
	 * 条件修改，返回int受影响条数。
	 * @param updateValue 修改值
	 * @param updateCondition 修改条件
	 * @return int 1 成功.
	 */
	@Override
	public int modify(Map<String, Object> updateValue, Map<String, Object> updateCondition) {
		String tableName = getTableName();
		return modify(updateValue, updateCondition, tableName);
	}

	/**
	 * 条件修改，返回int受影响条数。
	 * @param updateValue 修改值
	 * @param updateCondition 修改条件
	 * @param tableName 表名
	 * @return int 1 成功.
	 */
	@Override
	public int modify(Map<String, Object> updateValue, Map<String, Object> updateCondition, String tableName) {
		int res = 0;
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		if(updateValue == null || updateValue.isEmpty()){
			return res;
		}
		String sql = "UPDATE `"+tableName+"` SET SQLS WHERE SQLW";
		String sqlS = "";
		String sqlW = "";
		Map<String, Object> params = new HashMap<String, Object>();
		
		for (String key : updateValue.keySet()) {
			sqlS += ",`"+key+"`=:"+key;
			params.put(key, updateValue.get(key));
		}
		if(updateCondition != null){
			//"#"+key 是为了兼容  UPDATE table SET column=:column WHERE column=:column
			for (String key : updateCondition.keySet()) {
				sqlW += " and `"+key+"`=:#"+key;
				params.put("#"+key, updateCondition.get(key));
			}
		}
		if(!Tools.isNullOrEmpty(sqlS)){
			sql = sql.replace("SQLS", sqlS.substring(1));
		}
		if(!Tools.isNullOrEmpty(sqlW)){
			sql = sql.replace("SQLW", sqlW.substring(" and ".length()));
		}else{
			sql = sql.replace("SQLW", " 1 = 1 ");
		}
		//DEBUG
		debug(tableName, "CONDITION MODIFY SQL", sql);
		try {
//			res = jdbcOperate.update(sql, params);
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			
			res = jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preState = con.prepareStatement(pSql);
					setPreparedParams(preState, sqlParams, params);
					return preState;
				}
				
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			//DEBUG
			debug(tableName, "CONDITION MODIFY ERROR", e.getMessage());
		}
		return res;
	}
	
	//TODO delete
	/**
	 * 条件删除，返回int受影响条数。
	 * @param deleteCondition 删除条件
	 * @return int 1 成功
	 */
	@Override
	public int delete(Map<String, Object> deleteCondition) {
		String tableName = getTableName();
		return delete(deleteCondition, tableName);
	}

	/**
	 * 条件删除，返回int受影响条数。
	 * @param deleteCondition 删除条件
	 * @param tableName 表名
	 * @return int 1 成功，0 失败
	 */
	@Override
	public int delete(Map<String, Object> deleteCondition, String tableName) {
		int res = 0;
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		if(deleteCondition == null || deleteCondition.isEmpty()){
			return res;
		}
		String sql = "DELETE FROM `"+tableName+"` WHERE SQLW";
		String sqlW = "";
		
		for (String key : deleteCondition.keySet()) {
			sqlW += " and `"+key+"`=:"+key;
		}
		if(!Tools.isNullOrEmpty(sqlW)){
			sql = sql.replace("SQLW", sqlW.substring(" and ".length()));
		}
		//DEBUG
		debug(tableName, "DELETE SQL", sql);
		try {
//			res = jdbcOperate.update(sql, deleteCondition);
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			
			res = jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preState = con.prepareStatement(pSql);
					setPreparedParams(preState, sqlParams, deleteCondition);
					return preState;
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
			//DEBUG
			debug(tableName, "DELETE ERROR", e.getMessage());
		}
		return res;
	}
	
	/**
	 * 条件删除，返回int受影响条数。
	 * @param wsql 删除条件的where语句，不包括where。如 id=:id
	 * @param deleteCondition 删除条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx
	 * @return int 1 成功
	 */
	@Override
	public int delete(String wsql, Map<String, Object> deleteCondition) {
		String tableName = getTableName();
		return delete(wsql, deleteCondition, tableName);
	}

	/**
	 * 条件删除，返回int受影响条数。
	 * @param wsql 删除条件的where语句，不包括where。如 id=:id
	 * @param deleteCondition 删除条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx
	 * @param tableName 表名。
	 * @return int 1 成功
	 */
	@Override
	public int delete(String wsql, Map<String, Object> deleteCondition, String tableName) {
		int res = 0;
		if(Tools.isNullOrEmpty(wsql)){
			return res;
		}
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		if(deleteCondition == null){
			return res;
		}
		String sql = "DELETE FROM `"+tableName+"` where " + wsql;
		
		//DEBUG
		debug(tableName, "WSQL DELETE SQL", sql);
		try {
//			res = jdbcOperate.update(sql, deleteCondition);
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			
			res = jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preState = con.prepareStatement(pSql);
					setPreparedParams(preState, sqlParams, deleteCondition);
					return preState;
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
			//DEBUG
			debug(tableName, "WSQL DELETE ERROR", e.getMessage());
		}
		return res;
	}
	

	/**
	 * 批量删除，返回int受影响条数。
	 * @param column 数据表列名。如 id
	 * @param columnValues 值，list集合。
	 * @return int 1 成功
	 */
	@Override
	public int batchDelete(String column, List<String> columnValues) {
		String tableName = getTableName();
		return batchDelete(column, columnValues, tableName);
	}

	/**
	 * 批量删除，返回int受影响条数。
	 * @param column 数据表列名。如 id
	 * @param columnValues 值，list集合。
	 * @param tableName 表名。
	 * @return int 1 成功
	 */
	@Override
	public int batchDelete(String column, List<String> columnValues, String tableName) {
		int res = 0;
		if(Tools.isNullOrEmpty(column)){
			return res;
		}
		if(columnValues == null || columnValues.size() == 0){
			return res;
		}
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		String sql = "DELETE FROM `"+tableName+"` WHERE `"+column+"`=:" + column;
		//DEBUG
		debug(tableName, "BATCH DELETE SQL", sql);
		try {
//			int[] tmp = jdbcOperate.batchBasicType(sql, columnValues);
			
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			
			int[] tmp = jdbcTemplate.batchUpdate(pSql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Map<String, Object> params = new HashMap<>();
					params.put(column, columnValues.get(i));
					setPreparedParams(ps, sqlParams, params);
				}
				
				@Override
				public int getBatchSize() {
					return columnValues.size();
				}
			});
			
			for (int i : tmp) {
				res += i;
			}
		} catch (Exception e) {
			e.printStackTrace();
			//DEBUG
			debug(tableName, "BATCH DELETE ERROR", e.getMessage());
		}
		return res;
	}
	

	//TODO findList
	/**
	 * 查询数量，返回对象list集合。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList() {
		String tableName = getTableName();
		return findList(tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param tableName 表名。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(String tableName) {
		return findList(0, -1, tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param start 起始条数。
	 * @param rows 查询条数。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(int start, int rows) {
		String tableName = getTableName();
		return findList(start, rows, tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param start 起始条数。
	 * @param rows 查询条数。
	 * @param tableName 表名。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(int start, int rows, String tableName) {
		return findList(new HashMap<String, Object>(), start, rows, tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param queryCondition 查询条件。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(Map<String, Object> queryCondition) {
		String tableName = getTableName();
		return findList(queryCondition, tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param queryCondition 查询条件。
	 * @param tableName 表名。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(Map<String, Object> queryCondition, String tableName) {
		return findList(queryCondition, 0, -1, tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param queryCondition 查询条件。
	 * @param start 起始条数。
	 * @param rows 查询条数。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(Map<String, Object> queryCondition, int start, int rows) {
		String tableName = getTableName();
		return findList(queryCondition, start, rows, tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param queryCondition 查询条件。
	 * @param start 起始条数。
	 * @param rows 查询条数。
	 * @param tableName 表名。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(Map<String, Object> queryCondition, int start, int rows, String tableName) {
		String wsql = " 1=1 ";
		for (String key : queryCondition.keySet()) {
			wsql += " and `"+key+"`=:"+key;
		}
		return findList(wsql, queryCondition, start, rows, tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(String wsql, Map<String, Object> queryCondition) {
		String tableName = getTableName();
		return  findList(wsql, queryCondition, tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx
	 * @param tableName 表名。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(String wsql, Map<String, Object> queryCondition, String tableName) {
		return findList(wsql, queryCondition, 0, -1, tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx
	 * @param start 起始条数。
	 * @param rows 查询条数。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(String wsql, Map<String, Object> queryCondition, int start, int rows) {
		String tableName = getTableName();
		return findList(wsql, queryCondition, start, rows, tableName);
	}

	/**
	 * 查询数量，返回对象list集合。
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx。
	 * @param start 起始条数。
	 * @param rows 查询条数。
	 * @param tableName 表名。
	 * @return list 对象集合。
	 */
	@Override
	public List<T> findList(String wsql, Map<String, Object> queryCondition, int start, int rows, String tableName) {
		List<T> res = new ArrayList<T>();
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		if(queryCondition == null){
			return res;
		}
		if(Tools.isNullOrEmpty(wsql)){
			return res;
		}
		String sql = "SELECT * FROM `"+tableName+"` WHERE " + wsql;
		//-1 不参与分页
		if(rows != -1){
			sql += " limit " + start + ", " + rows;
		}
		//DEBUG
		debug(tableName, "QUERY SQL", sql);
		try {
//			Type type = getClass().getGenericSuperclass();
//			Class<?>[] clsses = Tools.getGenericClass((ParameterizedType) type);
//			Class<?> clss = clsses[0];
//			res = (List<T>) jdbcOperateSecond.queryObjectList(sql, clss, queryCondition);
			res = query(sql, queryCondition);
		} catch (Exception e) {
			e.printStackTrace();
			//DEBUG
			debug(tableName, "QUERY ERROR", e.getMessage());
		}
		return res;
	}
	
	/**
	 * 查询对象，返回对象。
	 * @param queryCondition 查询条件。
	 * @return T 对象。
	 */
	@Override
	public T findObject(Map<String, Object> queryCondition) {
		String tableName = getTableName();
		return findObject(queryCondition, tableName);
	}
	
	/**
	 * 查询对象，返回对象。
	 * @param queryCondition 查询条件。
	 * @param tableName 表名。
	 * @return T 对象。
	 */
	@Override
	public T findObject(Map<String, Object> queryCondition, String tableName) {
		String wsql = " 1=1 ";
		for (String key : queryCondition.keySet()) {
			wsql += " and `"+getOtherName(key)+"`=:"+key;
		}
		return findObject(wsql, queryCondition, tableName);
	}
	
	/**
	 * 查询对象，返回对象。
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx
	 * @return T 对象。
	 */
	@Override
	public T findObject(String wsql, Map<String, Object> queryCondition) {
		String tableName = getTableName();
		return findObject(wsql, queryCondition, tableName);
	}

	/**
	 * 查询对象，返回对象。
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx
	 * @param tableName 表名。
	 * @return T 对象。
	 */
	@Override
	public T findObject(String wsql, Map<String, Object> queryCondition, String tableName) {
		if(Tools.isNullOrEmpty(tableName)){
			return null;
		}
		if(queryCondition == null){
			return null;
		}
		if(Tools.isNullOrEmpty(wsql)){
			return null;
		}
		String sql = "SELECT * FROM `"+tableName+"` WHERE " + wsql;
		
		//DEBUG
		debug(tableName, "QUERY SQL", sql);
		try {
//			Type type = getClass().getGenericSuperclass();
//			Class<?>[] clsses = Tools.getGenericClass((ParameterizedType) type);
//			Class<?> clss = clsses[0];
//			T res = (T) jdbcOperateSecond.queryObject(sql, clss, queryCondition);
			
			return queryObject(sql, queryCondition);
		} catch (Exception e) {
			e.printStackTrace();
			//DEBUG
			debug(tableName, "QUERY ERROR", e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * 查询数量，返回maplist集合。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param queryCondition 查询条件.
	 * @return list maplist集合。
	 */
	@Override
	public List<Map<String, Object>> findMapList(List<String> columns, Map<String, Object> queryCondition) {
		String tableName = getTableName();
		return findMapList(columns, queryCondition, tableName);
	}
	
	/**
	 * 查询数量，返回maplist集合。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param queryCondition 查询条件.
	 * @param tableName 表名。
	 * @return list maplist集合。
	 */
	@Override
	public List<Map<String, Object>> findMapList(List<String> columns, Map<String, Object> queryCondition, String tableName) {
		return findMapList(columns, queryCondition, 0, -1, tableName);
	}
	
	/**
	 * 查询数量，返回maplist集合。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param queryCondition 查询条件.
	 * @param start 起始条数。
	 * @param rows 查询条数。
	 * @return list maplist集合。
	 */
	@Override
	public List<Map<String, Object>> findMapList(List<String> columns, Map<String, Object> queryCondition, int start, int rows) {
		String tableName = getTableName();
		return findMapList(columns, queryCondition, start, rows, tableName);
	}

	/**
	 * 查询数量，返回maplist集合。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param queryCondition 查询条件.
	 * @param start 起始条数。
	 * @param rows 查询条数。
	 * @param tableName 表名。
	 * @return list maplist集合。
	 */
	@Override
	public List<Map<String, Object>> findMapList(List<String> columns, Map<String, Object> queryCondition, int start, int rows, String tableName) {
		String wsql = " 1=1 ";
		for (String key : queryCondition.keySet()) {
			wsql += " and `"+key+"`=:"+key;
		}
		return findMapList(columns, wsql, queryCondition, start, rows, tableName);
	}

	/**
	 * 查询数量，返回maplist集合。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx。
	 * @return list maplist集合。
	 */
	@Override
	public List<Map<String, Object>> findMapList(List<String> columns, String wsql, Map<String, Object> queryCondition) {
		String tableName = getTableName();
		return findMapList(columns, wsql, queryCondition, tableName);
	}

	/**
	 * 查询数量，返回maplist集合。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx。
	 * @param tableName 表名。
	 * @return list maplist集合。
	 */
	@Override
	public List<Map<String, Object>> findMapList(List<String> columns, String wsql, Map<String, Object> queryCondition, String tableName) {
		return findMapList(columns, wsql, queryCondition, 0, -1, tableName);
	}

	/**
	 * 查询数量，返回maplist集合。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx。
	 * @param start 起始条数。
	 * @param rows 查询条数。
	 * @return list maplist集合。
	 */
	@Override
	public List<Map<String, Object>> findMapList(List<String> columns, String wsql, Map<String, Object> queryCondition,int start, int rows) {
		String tableName = getTableName();
		return findMapList(columns, wsql, queryCondition, start, rows, tableName);
	}

	/**
	 * 查询数量，返回maplist集合。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx。
	 * @param start 起始条数。
	 * @param rows 查询条数。
	 * @param tableName 表名。
	 * @return list maplist集合。
	 */
	@Override
	public List<Map<String, Object>> findMapList(List<String> columns, String wsql, Map<String, Object> queryCondition, int start, int rows, String tableName) {
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		if(queryCondition == null){
			return res;
		}
		if(Tools.isNullOrEmpty(wsql)){
			return res;
		}
		String sql = "SELECT FIELDS FROM `"+tableName+"` WHERE " + wsql;
		String fields = "";
		if(columns != null){
			for (String str : columns) {
				fields += ","+str;
			}
		}
		if(!Tools.isNullOrEmpty(fields)){
			sql = sql.replace("FIELDS", fields.substring(1));
		}else{
			sql = sql.replace("FIELDS", "*");
		}
		//-1 不参与分页
		if(rows != -1){
			sql += " limit " + start + ", " + rows;
		}
		
		//DEBUG
		debug(tableName, "QUERY SQL", sql);
		try {
//			res = jdbcOperateSecond.queryMapList(sql, queryCondition);
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			
			res = jdbcTemplate.query(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preState = con.prepareStatement(pSql);
					setPreparedParams(preState, sqlParams, queryCondition);
					return preState;
				}
				
			}, new RowMapper<Map<String, Object>>(){
				@Override
	            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	try {
	            		return getOneRowWithMap(rs);
	            	} catch (ReflectiveOperationException e) {
	            		e.printStackTrace();
	            	}
					return null;
	            }

	        });
		} catch (Exception e) {
			e.printStackTrace();
			//DEBUG
			debug(tableName, "QUERY ERROR", e.getMessage());
		}
		return res;
	}
	
	/**
	 * 查询对象，返回map。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param queryCondition 查询条件。
	 * @return map map对象。
	 */
	@Override
	public Map<String, Object> findMap(List<String> columns, Map<String, Object> queryCondition) {
		String tableName = getTableName();
		return findMap(columns, queryCondition, tableName);
	}

	/**
	 * 查询对象，返回map。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param queryCondition 查询条件。
	 * @param tableName 表名。
	 * @return map map对象。
	 */
	@Override
	public Map<String, Object> findMap(List<String> columns, Map<String, Object> queryCondition, String tableName) {
		String wsql = " 1=1 ";
		for (String key : queryCondition.keySet()) {
			wsql += " and `"+key+"`=:"+key;
		}
		return findMap(columns, wsql, queryCondition, tableName);
	}
	
	/**
	 * 查询对象，返回map。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx。
	 * @return map map对象。
	 */
	@Override
	public Map<String, Object> findMap(List<String> columns, String wsql, Map<String, Object> queryCondition) {
		String tableName = getTableName();
		return findMap(columns, wsql, queryCondition, tableName);
	}

	/**
	 * 查询对象，返回map。
	 * @param columns 需要返回的列名，默认*。 如 id、name、count(1)
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx。
	 * @param tableName 表名。
	 * @return map map对象。
	 */
	@Override
	public Map<String, Object> findMap(List<String> columns, String wsql, Map<String, Object> queryCondition, String tableName) {
		if(Tools.isNullOrEmpty(tableName)){
			return null;
		}
		if(queryCondition == null){
			return null;
		}
		if(Tools.isNullOrEmpty(wsql)){
			return null;
		}
		String sql = "SELECT FIELDS FROM `" + tableName + "` WHERE " + wsql;
		String fields = "";
		if(columns != null){
			for (String str : columns) {
				fields += ","+str;
			}
		}
		if(!Tools.isNullOrEmpty(fields)){
			sql = sql.replace("FIELDS", fields.substring(1));
		}else{
			sql = sql.replace("FIELDS", "*");
		}
		
		//DEBUG
		debug(tableName, "QUERY SQL", sql);
		try {
//			Map<String, Object> res = jdbcOperateSecond.queryMap(sql, queryCondition);
//			return res;
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			
			return jdbcTemplate.query(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preState = con.prepareStatement(pSql);
					setPreparedParams(preState, sqlParams, queryCondition);
					return preState;
				}
				
			}, new ResultSetExtractor<Map<String, Object>>() {
				@Override
				public Map<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
					try {
						if(rs.next()){
			                return getOneRowWithMap(rs);
			            }else{
			                return null;
			            }
					} catch (ReflectiveOperationException e) {
						e.printStackTrace();
					}
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			//DEBUG
			debug(tableName, "QUERY ERROR", e.getMessage());
		}
		return null;
	}
	

	//TODO size
	/**
	 * 查询条数，返回long总条数。
	 * @return long 总条数。
	 */
	@Override
	public long size() {
		String tableName = getTableName();
		return size(tableName);
	}

	/**
	 * 查询条数，返回long总条数。
	 * @param tableName 表名。
	 * @return long 总条数。
	 */
	@Override
	public long size(String tableName) {
		return size(new HashMap<String, Object>(), tableName);
	}

	/**
	 * 查询条数，返回long总条数。
	 * @param queryCondition 查询条件。
	 * @return long 总条数。
	 */
	@Override
	public long size(Map<String, Object> queryCondition) {
		String tableName = getTableName();
		return size(queryCondition, tableName);
	}

	/**
	 * 查询条数，返回long总条数。
	 * @param queryCondition 查询条件。
	 * @param tableName 表名。
	 * @return long 总条数。
	 */
	@Override
	public long size(Map<String, Object> queryCondition, String tableName) {
		String wsql = " 1=1 ";
		for (String key : queryCondition.keySet()) {
			wsql += " and `"+key+"`=:"+key;
		}
		return size(wsql, queryCondition, tableName);
	}

	/**
	 * 查询条数，返回long总条数。
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx
	 * @return long 总条数。
	 */
	@Override
	public long size(String wsql, Map<String, Object> queryCondition) {
		String tableName = getTableName();
		return size(wsql, queryCondition, tableName);
	}

	/**
	 * 查询条数，返回long总条数。
	 * @param wsql 查询条件的where语句，不包括where。如 id=:id
	 * @param queryCondition 查询条件赋值，不参与拼接sql语句，不能为null。如 key：id，value：xxx
	 * @param tableName 表名
	 * @return long 总条数。
	 */
	@Override
	public long size(String wsql, Map<String, Object> queryCondition, String tableName) {
		long res = 0;
		if(Tools.isNullOrEmpty(tableName)){
			return res;
		}
		if(queryCondition == null){
			return res;
		}
		if(Tools.isNullOrEmpty(wsql)){
			return res;
		}
		String sql = "SELECT count(1) FROM `"+tableName+"` WHERE " + wsql;
		//DEBUG
		debug(tableName, "SIZE SQL", sql);
		try {
//			res = jdbcOperateSecond.queryObject(sql, Long.class, queryCondition);
			List<String> sqlParams = getSqlParams(sql);
			String pSql = preparedSql(sql);
			
			res = jdbcTemplate.query(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preState = con.prepareStatement(pSql);
					setPreparedParams(preState, sqlParams, queryCondition);
					return preState;
				}
				
			}, new ResultSetExtractor<Long>() {

				@Override
				public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
					try {
						if(rs.next()){
			                return getOneRowWithObject(rs, Long.class);
			            }else{
			                return null;
			            }
					} catch (ReflectiveOperationException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			debug(tableName, "SIZE ERROR", e.getMessage());
		}
		return res;
	}

	//TODO SQL
	
	public int baseUpdate(String sql, Map<String, Object> params){

		try {
			//验证SQL
			if(sql.trim().toLowerCase().startsWith("select ")){
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Query excution SQL Error! \n SQL is (not select): \n\t" + sql + "\n");
		}
		
		List<String> sqlParams = getSqlParams(sql);
		String pSql = preparedSql(sql);
		
		return jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preState = con.prepareStatement(pSql);
				setPreparedParams(preState, sqlParams, params);
				return preState;
			}
			
		});
	}
	
	public <E> List<E> baseQuery(String sql, Map<String, Object> params, Class<E> clzz){

		try {
			//验证SQL
			if(!sql.trim().toLowerCase().startsWith("select ")){
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Query excution SQL Error! \n SQL is (select ): \n\t" + sql + "\n");
		}
		
		List<String> sqlParams = getSqlParams(sql);
		String pSql = preparedSql(sql);
		
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preState = con.prepareStatement(pSql);
				setPreparedParams(preState, sqlParams, params);
				return preState;
			}
			
		}, new RowMapper<E>(){
			@Override
            public E mapRow(ResultSet rs, int rowNum) throws SQLException {
            	try {
            		return (E) getOneRowWithObject(rs, clzz);
            	} catch (ReflectiveOperationException e) {
            		e.printStackTrace();
            	} catch (ParseException e) {
            		e.printStackTrace();
            	}
				return null;
            }

        });
	}
	
	public List<Map<String, Object>> baseQuery(String sql, Map<String, Object> params){

		try {
			//验证SQL
			if(!sql.trim().toLowerCase().startsWith("select ")){
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Query excution SQL Error! \n SQL is (select ): \n\t" + sql + "\n");
		}
		
		List<String> sqlParams = getSqlParams(sql);
		String pSql = preparedSql(sql);
		
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preState = con.prepareStatement(pSql);
				setPreparedParams(preState, sqlParams, params);
				return preState;
			}
			
		}, new RowMapper<Map<String, Object>>(){
			@Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
            	try {
            		return getOneRowWithMap(rs);
            	} catch (ReflectiveOperationException e) {
            		e.printStackTrace();
            	}
				return null;
            }

        });
	}
	
	public <E> List<E> basePage(String sql, Map<String, Object> params, Class<E> clzz, int start, int rows){

		try {
			//验证SQL
			if(!sql.trim().toLowerCase().startsWith("select ")){
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Query excution SQL Error! \n SQL is (select ): \n\t" + sql + "\n");
		}
		
		sql = sql + " limit " + start + ", " + rows;
		List<String> sqlParams = getSqlParams(sql);
		String pSql = preparedSql(sql);
		
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preState = con.prepareStatement(pSql);
				setPreparedParams(preState, sqlParams, params);
				return preState;
			}
			
		}, new RowMapper<E>(){
			@Override
            public E mapRow(ResultSet rs, int rowNum) throws SQLException {
            	try {
            		return (E) getOneRowWithObject(rs, clzz);
            	} catch (ReflectiveOperationException e) {
            		e.printStackTrace();
            	} catch (ParseException e) {
            		e.printStackTrace();
            	}
				return null;
            }

        });
	}
	
	public List<Map<String, Object>> basePage(String sql, Map<String, Object> params, int start, int rows){

		try {
			//验证SQL
			if(!sql.trim().toLowerCase().startsWith("select ")){
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Query excution SQL Error! \n SQL is (select ): \n\t" + sql + "\n");
		}
		
		sql = sql + " limit " + start + ", " + rows;
		List<String> sqlParams = getSqlParams(sql);
		String pSql = preparedSql(sql);
		
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preState = con.prepareStatement(pSql);
				setPreparedParams(preState, sqlParams, params);
				return preState;
			}
			
		}, new RowMapper<Map<String, Object>>(){
			@Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
            	try {
            		return getOneRowWithMap(rs);
            	} catch (ReflectiveOperationException e) {
            		e.printStackTrace();
            	}
				return null;
            }

        });
	}
	
	public long baseSize(String sql, Map<String, Object> condition){
		
		try {
			//验证SQL
			if(!sql.trim().toLowerCase().startsWith("select count")){
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Query excution SQL Error! \n SQL is (select count): \n\t" + sql + "\n");
		}
		
		List<String> sqlParams = getSqlParams(sql);
		String pSql = preparedSql(sql);
		
        return jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preState = con.prepareStatement(pSql);
				setPreparedParams(preState, sqlParams, condition);
				return preState;
			}
			
		}, new ResultSetExtractor<Long>() {

			@Override
			public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
				try {
					if(rs.next()){
		                return getOneRowWithObject(rs, Long.class);
		            }else{
		                return null;
		            }
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
    }
	
	
	
	private List<T> query(String sql, Map<String, Object> params){
		List<String> sqlParams = getSqlParams(sql);
		String pSql = preparedSql(sql);
		
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preState = con.prepareStatement(pSql);
				setPreparedParams(preState, sqlParams, params);
				return preState;
			}
			
		}, new RowMapper<T>(){
			@Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            	try {
            		return getOneRowWithObject(rs, getObejctClass());
            	} catch (ReflectiveOperationException e) {
            		e.printStackTrace();
            	} catch (ParseException e) {
            		e.printStackTrace();
            	}
				return null;
            }

        });
	}
	
	private T queryObject(String sql, Map<String, Object> params){
		List<String> sqlParams = getSqlParams(sql);
		String pSql = preparedSql(sql);
		
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preState = con.prepareStatement(pSql);
				setPreparedParams(preState, sqlParams, params);
				return preState;
			}
			
		}, new ResultSetExtractor<T>() {
			@Override
			public T extractData(ResultSet rs) throws SQLException, DataAccessException {
				try {
					if(rs.next()){
		                return getOneRowWithObject(rs, getObejctClass());
		            }else{
		                return null;
		            }
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	
	
	
	public static List<String> getSqlParams(String sqlStr){
		List<String> params = Tools.parseRegEx(sqlStr, ":[^,\\s\\)%']+");
		ArrayList<String> sqlParams = new ArrayList<String>();
		for(String param : params){
			sqlParams.add(param);
		}
		return sqlParams;
	}
	
	public static String preparedSql(String sqlStr){
		return sqlStr.replaceAll(":[^,\\s\\)%']+", "?");
	}
	
	public static List<Object> getSqlValues(List<String> sqlParams, Map<String, Object> params){
		List<Object> sqllValues = new ArrayList<Object>();
		for(int i = 0; i < sqlParams.size(); i++){
			String paramName = sqlParams.get(i);
			paramName = paramName.substring(1, paramName.length());
			Object data = params.get(paramName);
			sqllValues.add(data);
		}
		return sqllValues;
	}
	
	/**
	 * 给preparedStatement对象设置参数
	 * 	
	 * @param preparedStatement  preparedStatement对象
	 * @param sqlParams			sql 参数表
	 * @param params			参数键值 Map
	 * @throws SQLException SQL 异常
	 */
	public static void setPreparedParams(PreparedStatement preparedStatement, List<String> sqlParams, Map<String, Object> params) throws SQLException{
		for(int i = 0; i < sqlParams.size(); i++){
			String paramName = sqlParams.get(i);
			//去掉前面:号
			paramName = paramName.substring(1,paramName.length());
			Object data = params.get(paramName);
			if(data == null) {
				preparedStatement.setObject(i + 1, params.get(paramName));
			}else if(Tools.isBasicType(data.getClass())) {
				preparedStatement.setObject(i + 1, params.get(paramName));
			}else{
				//复杂对象类型,无法直接保存进数据库,进行 JSON 转换后保存
				preparedStatement.setObject(i + 1, JsonTools.toJsonString(params.get(paramName)));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getObejctClass(){
		Type type = getClass().getGenericSuperclass();
		Class<?> clss = null;
		try {
			Class<?>[] clsses = Tools.getGenericClass((ParameterizedType) type);
			clss = clsses[0];
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (Class<T>) clss;
	}
	
	
	/**
	 * 包装resultSet中单行记录成Map
	 * @param resultset 查询结果集
	 * @return 转后的 Map 对象
	 * @throws SQLException  SQL 异常
	 * @throws ReflectiveOperationException  反射异常
	 */
    public static Map<String, Object> getOneRowWithMap(ResultSet resultset)
			throws SQLException, ReflectiveOperationException {
		 
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Integer> columns = new HashMap<String, Integer>();
		//遍历结果集字段信息
		ResultSetMetaData metaData = resultset.getMetaData();
		int columnCount = metaData.getColumnCount();
		for(int i = 1; i <= columnCount; i++){
			columns.put(metaData.getColumnLabel(i), metaData.getColumnType(i));
		}
		//组装Map
		for(Entry<String, Integer> columnEntry : columns.entrySet()){
			String methodName = getDataMethod(columnEntry.getValue());
			//获得取值方法参数参数是 String 类型的对应方法 (column label)
			Method method = Tools.findMethod(resultset.getClass(), methodName, new Class[]{String.class});
			Object value = method.invoke(resultset, columnEntry.getKey());
			resultMap.put(columnEntry.getKey(), value);
		}
		return resultMap;
	}
    
    /**
	 * 根据 SQL 类型判断 Result 该使用什么方法取值
	 * @param databaseType 数据库中的数据类型
	 * @return  方法名
	 */
	public static String getDataMethod(int databaseType){
		switch(databaseType){
			case Types.CHAR :
		         return  "getString";
			case Types.VARCHAR :
			         return "getString";
			case Types.LONGVARCHAR :
			         return "getString";
			case Types.NCHAR :
			         return "getString";
			case Types.LONGNVARCHAR :
			         return "getString";
			case Types.NUMERIC :
			         return  "getBigDecimal";
			case Types.DECIMAL :
			         return  "getBigDecimal";
			case Types.BIT :
			         return "getBoolean";
			case Types.BOOLEAN :
			         return  "getBoolean";
			case Types.TINYINT :
			         return  "getByte";
			case Types.SMALLINT :
			         return  "getShort";
			case Types.INTEGER :
			         return  "getInt";
			case Types.BIGINT :
			         return  "getLong";
			case Types.REAL :
			         return  "getFloat";
			case Types.FLOAT :
			         return  "getFloat";
			case Types.DOUBLE :
			         return  "getDouble";
			case Types.BINARY :
			         return  "getBytes";
			case Types.VARBINARY :
			         return  "getBytes";
			case Types.LONGVARBINARY :
			         return  "getBytes";
			case Types.DATE :
			         return  "getDate";
			case Types.TIME :
			         return  "getTime";
			case Types.TIMESTAMP :
			         return  "getTimestamp";
			case Types.CLOB :
			         return  "getClob";
			case Types.BLOB :
			         return  "getBlob";
			case Types.ARRAY :
			         return  "getArray";
			default:
					return "getString";
		}
	}
	
	/**
	 * 根据 JAVA 类型判断该使用什么 SQL 数据类型
	 * @param obj 对象
	 * @return 数据库中的数据类型
     */
	public static int getSqlTypes(Object obj){
		Class<?> objectClass = obj.getClass();
		if(char.class == objectClass){
	         return  Types.CHAR;
		}else if(String.class == objectClass){
			 return Types.VARCHAR ;
		}else if(BigDecimal.class == objectClass){
			 return Types.NUMERIC;
		}else if(Boolean.class == objectClass){
			 return Types.BIT;
		}else if(Byte.class == objectClass){
			 return Types.TINYINT;
		}else if(Short.class == objectClass){
			 return Types.SMALLINT;
		}else if(Integer.class == objectClass){
			 return Types.INTEGER;
		}else if(Long.class == objectClass){
			 return Types.BIGINT;
		}else if(Float.class == objectClass){
			 return Types.FLOAT;
		}else if(Double.class == objectClass){
			 return Types.DOUBLE;
		}else if(Byte[].class == objectClass){
			 return Types.BINARY;
		}else if(Date.class == objectClass){
			 return Types.DATE;
		}else if(Time.class == objectClass){
			 return Types.TIME;
		}else if(Timestamp.class == objectClass){
			 return Types.TIMESTAMP;
		}else if(Clob.class == objectClass){
			 return Types.CLOB;
		}else if(Blob.class == objectClass){
			 return Types.BLOB;
		}else if(Object[].class == objectClass){
			 return Types.ARRAY;
		}
		return 0;
	}
	
	/**
     * 包装resultSet中单行记录成指定对象
     * @param clazz 类对象
     * @param resultset 查询结果集
     * @return 转换后的对象
     * @throws ReflectiveOperationException 反射异常
     * @throws SQLException  SQL 异常
     * @throws ParseException  解析异常
     */
	@SuppressWarnings("unchecked")
    public static <T> T  getOneRowWithObject(ResultSet resultset, Class<T> clazz)
			throws SQLException, ReflectiveOperationException, ParseException {
    	Map<String, Object> rowMap = getOneRowWithMap(resultset);
    	if(Tools.isBasicType(clazz)){
    		Object object = null;
    		for (Entry<String, Object> item : rowMap.entrySet()) {
    			if (Byte.class == clazz) 
    				object = Byte.valueOf(item.getValue().toString());
    			else if (Short.class == clazz) 
    				object = Short.valueOf(item.getValue().toString());
    			else if (Integer.class == clazz) 
    				object = Integer.valueOf(item.getValue().toString());
    			else if (Long.class == clazz) 
    				object = Long.valueOf(item.getValue().toString());
    			else if (BigInteger.class == clazz) {
    				if (item.getValue() instanceof BigDecimal) {
    					object = ((BigDecimal) item.getValue()).toBigInteger();
    				}else {
    					object = BigInteger.valueOf(Long.valueOf(item.getValue().toString()));
    				}
    			}
    			else if (Float.class == clazz)
    				object = Float.valueOf(item.getValue().toString());
    			else if (Double.class == clazz) 
    				object = Double.valueOf(item.getValue().toString());
    			else if (BigDecimal.class == clazz) 
    				object =  new BigDecimal(item.getValue().toString());
    			else
    				object = item.getValue();
    			
			}
    		return (T) object;
    	}else{
    		Object object = clazz.newInstance();
    		Field[] fields = Tools.getFields(clazz);//clazz.getDeclaredFields();
    		Method[] methods = Tools.getMethods(clazz);//clazz.getDeclaredMethods();  
    		for (Field f : fields) {
    			String key = f.getName();
    			if(rowMap.get(key) != null){
    				for (Method method : methods) {
    					if(method.getName().startsWith("set") && method.getName().substring(3).equalsIgnoreCase(f.getName())){
    						try {
    							method.invoke(object, rowMap.get(key));
    						} catch (Exception e) {
    							e.printStackTrace();
    						}
    					}
    				}
    			}
    		}
    		return (T) object;
    	}
    	
    }
	
	//获取泛型注解的table name。
	private String getTableName(){
		String tableName = "";
		Class<?> clss = getObejctClass();
		if(clss != null){
			if(clss.isAnnotationPresent(Table.class)){
				tableName = clss.getAnnotation(Table.class).value();
			}
		}
		return tableName;
	}
	
	//获取泛型注解的字段别名。
	private String getOtherName(Field f){
		String value = "";
		if(f != null){
			value = f.getName();
			//获取字段别名，如果注解PrimaryKey、Column都有，取Column注解。
			if(f.isAnnotationPresent(Column.class)){
				value = f.getAnnotation(Column.class).value();
			}else if(f.isAnnotationPresent(PrimaryKey.class)){
				value = f.getAnnotation(PrimaryKey.class).value();
			}
			value = Tools.isNullOrEmpty(value) ? f.getName() : value;
		}
		return value;
	}
	
	//获取泛型注解的字段别名。
	private String getOtherName(String fieldName){
		String value = fieldName;
		try {
			Field f = Tools.findField(getObejctClass(), fieldName);
			if(f != null){
				value = getOtherName(f);
			}
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	//获取Entity类注解的PrimaryKey。
	private  List<PrimaryKeyCondition> getPrimaryKeys(Class<?> clss){
		String mark = clss.getCanonicalName();
		List<PrimaryKeyCondition> list = null;
		if(pkArrays.containsKey(mark)){
			list = pkArrays.get(mark);
		}else {
			list = new ArrayList<PrimaryKeyCondition>();
			Field[] fields = Tools.getFields(clss);
			for (Field f : fields) {
				if(f.isAnnotationPresent(PrimaryKey.class)){
					PrimaryKeyCondition node = new PrimaryKeyCondition();
					node.setField(f.getName());
					node.setType(f.getType().getSimpleName());
					node.setKeyType(f.getAnnotation(PrimaryKey.class).type());
					list.add(node);
				}
			}
			pkArrays.put(mark, list);
		}
		return list;
	}
	
	private void parsePrimateKey(T object, List<PrimaryKeyCondition> pkeys){
		if(pkeys == null || pkeys.isEmpty()){
			return;
		}
		for (PrimaryKeyCondition node : pkeys) {
			switch (node.getKeyType()) {
			case UUID:
				//处理主键为uuid、并且String类型的
				if("String".equals(node.getType())){
					String nameGet = "get" + node.getField().substring(0, 1).toUpperCase() + node.getField().substring(1);
					Method methdGet = null;
					Object valueGet = null;
					String nameSet = "set" + node.getField().substring(0, 1).toUpperCase() + node.getField().substring(1);
					Method methdSet = null;
					try {
						methdGet  = Tools.findMethod(object.getClass(), nameGet, 0)[0];
						valueGet = methdGet.invoke(object);
						methdSet  = Tools.findMethod(object.getClass(), nameSet, 1)[0];
						//如果主键为空自动创建一个md5的uuid
						if(Tools.isNullOrEmpty(valueGet)){
							methdSet.invoke(object, Tools.md5(UUID.randomUUID().toString()));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
				
			case AUTO_INCREMENT:
				
				break;
				
			case NORMAL:
				//处理主键为NORMAL、并且String类型的
				if("String".equals(node.getType())){
					String nameGet = "get" + node.getField().substring(0, 1).toUpperCase() + node.getField().substring(1);
					Method methdGet = null;
					Object valueGet = null;
					String nameSet = "set" + node.getField().substring(0, 1).toUpperCase() + node.getField().substring(1);
					Method methdSet = null;
					try {
						methdGet  = Tools.findMethod(object.getClass(), nameGet, 0)[0];
						valueGet = methdGet.invoke(object);
						methdSet  = Tools.findMethod(object.getClass(), nameSet, 1)[0];
						//如果主键为空自动创建一个主键
						if(Tools.isNullOrEmpty(valueGet)){
							int random = new Random().nextInt(10000);
							String str = random+"";
							for (int i = 0; i <  4 - str.length(); i++) {
								str = "0" + str;
							}
							String id = "P"+DateTools.formatDate("yyyyMMddHHmmssSSS")+str;
							methdSet.invoke(object, id);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;

			default:
				break;
			}
		}
	}
	
	private void setFieldValue(Object obj, String field, Object value){
		String nameSet = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
		Method methdSet = null;
		try {
			methdSet  = Tools.findMethod(obj.getClass(), nameSet, 1)[0];
			if(methdSet != null){
				methdSet.invoke(obj, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * DEBUG
	 * @param tableName
	 * @param name
	 * @param content
	 */
	private void debug(String tableName, String name, String content){
		if(log){
//			LogsTools.logSet("sql/"+tableName, "【"+name+"】  -- " + content);
			logger.debug("{} 【{}】 -- {}", tableName, name, content);
		}
	}

	
	public static void main(String[] args) {
		String sqlStr = "select * from table where   x=:x and   y!=:y and   z like '%:z%' and w in (:w) and u=':u'";
		System.out.println(sqlStr.replaceAll(":[^,\\s\\)%]+", "?"));
		List<String> params = Tools.parseRegEx(sqlStr, ":[^,\\s\\)%']+");
		System.out.println(JsonTools.toJsonString(params));
	}
	
}
