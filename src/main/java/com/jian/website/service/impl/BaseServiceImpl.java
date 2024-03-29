package com.jian.website.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.jian.annotation.Excel;
import com.jian.annotation.PrimaryKey;
import com.jian.tools.core.DateTools;
import com.jian.tools.core.Tools;

import com.jian.website.dao.BaseDao;
import com.jian.website.service.BaseService;
import com.jian.website.config.Config;
import com.jian.website.util.Utils;

/**
 * @author liujian
 * @Date  
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected BaseDao<T> baseDao;
	
	public abstract void initDao();
	
	
	public void init() {
		initDao();
	}
	
	public int add(T obj){
		init();
		fillPrimaryKey(obj);
		fillDate(Config.autoFillDateForAdd, obj);
		return baseDao.add(obj);
	}
	
	public int add(List<T> objs){
		init();
		for (T obj : objs) {
			fillPrimaryKey(obj);
			fillDate(Config.autoFillDateForAdd, obj);
		}
		return baseDao.batchAdd(objs);
	}
	
	public T add2(T obj){
		init();
		fillPrimaryKey(obj);
		fillDate(Config.autoFillDateForAdd, obj);
		return baseDao.add2(obj);
	}
	
	public int modify(T obj){
		init();
		fillDate(Config.autoFillDateForModify, obj);
		return baseDao.modify(obj);
	}
	
	public int modify(List<T> objs){
		init();
		for (T obj : objs) {
			fillDate(Config.autoFillDateForModify, obj);
		}
		return baseDao.batchModify(objs);
	}
	
	public int modify(Map<String, Object> values, Map<String, Object> condition){
		init();
		fillDate(Config.autoFillDateForModify, values);
		return baseDao.modify(values, condition);
	}
	
	public int delete(Map<String, Object> condition){
		init();
		return baseDao.delete(condition);
	}
	
	public int delete(String column, List<String> values){
		init();
		return baseDao.batchDelete(column, values);
	}
	
	public List<T> findAll(){
		init();
		return baseDao.findList();
	}
	
	public List<T>  findList(Map<String, Object> condition){
		init();
		return baseDao.findList(condition);
	}
	
	public T findOne(Map<String, Object> condition){
		init();
		return baseDao.findObject(condition);
	}
	
	public List<T> findPage(Map<String, Object> condition, int start, int rows){
		init();
		return baseDao.findList(condition, start, rows);
	}
	
	public long size(Map<String, Object> condition){
		init();
		return baseDao.size(condition);
	}
	
	
	public BaseDao<T> getDao(){
		init();
		return baseDao;
	}
	
	
	public int  baseUpdate(String sql, Map<String, Object> condition){
		init();
		return baseDao.baseUpdate(sql, condition);
	}
	
	public <E> List<E> baseQuery(String sql, Map<String, Object> condition, Class<E> clzz){
		init();
		return baseDao.baseQuery(sql, condition, clzz);
	}

	public <E> List<E> basePage(String sql, Map<String, Object> condition, Class<E> clzz, int start, int rows){
		init();
		return baseDao.basePage(sql, condition, clzz, start, rows);
	}
	
	public long baseSize(String sql, Map<String, Object> condition){
		init();
		return baseDao.baseSize(sql, condition);
	}
	
	
	private Class<T> getObejctClass(){
		return Utils.getObejctClass(getClass());
	}
	
	/**
	 * 填充日期
	 * @param fillCondition  格式 “,xxxx,xxxx,xxxx,”
	 * @param obj 对象
	 */
	private void fillDate(String fillCondition, T obj){
		if(Tools.isNullOrEmpty(fillCondition)){
			return;
		}
		Field[] fields = Tools.getFields(obj.getClass());
		for (Field f : fields) {
			if(f.isAnnotationPresent(Excel.class)){
				Excel excel = f.getAnnotation(Excel.class);
				//日期格式化
				String[] name = excel.name().replace("：", " ").replace(":", " ").replace("\t", " ").replace("\n", " ").split(" ");
				String dateName = name[0];
				String dateType = name.length < 2 ? "yyyy-MM-dd HH:mm:ss" : name[1];
				//匹配  如：日期
				if(fillCondition.toLowerCase().indexOf(","+dateName.toLowerCase()+",") != -1 ){
					try {
						String getName = "get"+f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
						String setName = "set"+f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
						Method getm = Tools.findMethod(obj.getClass(), getName);
						Method setm = Tools.findMethod(obj.getClass(), setName, f.getType());
						Object value =  getm.invoke(obj);
						//如果不为空，不填充
						if(value == null){
							setm.invoke(obj, DateTools.formatDate(dateType));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	/**
	 * 填充日期
	 * @param fillCondition  格式 “,xxxx,xxxx,xxxx,”
	 * @param obj 对象
	 */
	private void fillDate(String fillCondition, Map<String, Object> valus){
		if(Tools.isNullOrEmpty(fillCondition)){
			return;
		}
		Field[] fields = Tools.getFields(getObejctClass());
		for (Field f : fields) {
			if(f.isAnnotationPresent(Excel.class)){
				Excel excel = f.getAnnotation(Excel.class);
				//日期格式化
				String[] name = excel.name().replace("：", " ").replace(":", " ").replace("\t", " ").replace("\n", " ").split(" ");
				String dateName = name[0];
				String dateType = name.length < 2 ? "yyyy-MM-dd HH:mm:ss" : name[1];
				//匹配  如：日期
				if(fillCondition.toLowerCase().indexOf(","+dateName.toLowerCase()+",") != -1 ){
					try {
						Object value =  valus.get(f.getName());
						//如果不为空，不填充
						if(value == null){
							valus.put(f.getName(), DateTools.formatDate(dateType));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	/**
	 * 填充主键
	 * @param obj 对象
	 */
	private void fillPrimaryKey(T obj){
		Field[] fields = Tools.getFields(obj.getClass());
		for (Field f : fields) {
			if(f.isAnnotationPresent(PrimaryKey.class)){
				PrimaryKey key = f.getAnnotation(PrimaryKey.class);
				switch (key.type()) {
				case UUID:
					//处理主键为uuid、并且String类型的
					if("String".equals(f.getType().getSimpleName())){
						try {
							String getName = "get"+f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
							String setName = "set"+f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
							Method getm = Tools.findMethod(obj.getClass(), getName);
							Method setm = Tools.findMethod(obj.getClass(), setName, f.getType());
							Object value =  getm.invoke(obj);
							//如果主键为空,自动创建一个md5的uuid
							if(value == null){
								setm.invoke(obj, Utils.newId());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;
					
				case AUTO_INCREMENT:
					
					break;

				default:
					break;
				}
			}
		}
	}
	
}
