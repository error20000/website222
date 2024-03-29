package com.jian.website.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jian.annotation.Excel;
import com.jian.tools.core.JsonTools;
import com.jian.tools.core.ResultKey;
import com.jian.tools.core.ResultTools;
import com.jian.tools.core.Tips;
import com.jian.tools.core.Tools;

import com.jian.website.service.BaseService;
import com.jian.website.config.VerifyConfig;
import com.jian.website.util.Utils;

public abstract class BaseController<T> {
	
	protected BaseService<T> service;
	
	public abstract void initService();

	//登录验证
	public Map<String, Object> verifyLogin(HttpServletRequest req){
		
		return VerifyConfig.verifyLogin(req);
	}
	
	//sign验证
	public Map<String, Object> verifySign(HttpServletRequest req){
		
		return VerifyConfig.verifySign(req);
	}
	
	//权限验证
	public Map<String, Object> verifyAuth(HttpServletRequest req){
		//TODO 权限验证
		
		return null;
	}
	
	//获取用户信息
	public String getUserInfo(HttpServletRequest req){
		return VerifyConfig.getUserInfo(req);
	}
	
	
	
    public String add(HttpServletRequest req) {
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		//保存
		try {
			T obj = Tools.getReqParamsToObject(req, getObejctClass().newInstance());
			int res = service.add(obj);
			if(res > 0){
				return ResultTools.custom(Tips.ERROR1).put(ResultKey.DATA, res).toJSONString();
			}else{
				return ResultTools.custom(Tips.ERROR0).toJSONString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultTools.custom(Tips.ERROR0).toJSONString();
    }
	
	
    public String add2(HttpServletRequest req) {
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		//保存
		try {
			T obj = Tools.getReqParamsToObject(req, getObejctClass().newInstance());
			T res = service.add2(obj);
			if(res != null){
				return ResultTools.custom(Tips.ERROR1).put(ResultKey.DATA, res).toJSONString();
			}else{
				return ResultTools.custom(Tips.ERROR0).toJSONString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultTools.custom(Tips.ERROR0).toJSONString();
    }
	
	
    public String update(HttpServletRequest req) {
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		//参数
		List<String> pkeys = Utils.getPrimaryKeys(getObejctClass());//获取主键
		if(pkeys == null || pkeys.isEmpty()){
			return ResultTools.custom(Tips.ERROR206).toJSONString();
		}
		Map<String, Object> condition = new HashMap<String, Object>();
		for (String str : pkeys) {
			String strv = Tools.getReqParamSafe(req, str);
			vMap = Tools.verifyParam(str, strv, 0, 0);
			if(vMap != null){
				return ResultTools.custom(Tips.ERROR206, str).toJSONString();
			}
			condition.put(str, strv);
		}
		Map<String, Object> setValues = Tools.getReqParamsToMap(req, getObejctClass());
		//保存
		int res = service.modify(setValues, condition);
		if(res > 0){
			return ResultTools.custom(Tips.ERROR1).toJSONString();
		}else{
			return ResultTools.custom(Tips.ERROR0).put(ResultKey.DATA, res).toJSONString();
		}
    }
	
	
	
    public String delete(HttpServletRequest req) {
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		//参数
		List<String> pkeys = Utils.getPrimaryKeys(getObejctClass());//获取主键
		if(pkeys == null || pkeys.isEmpty()){
			return ResultTools.custom(Tips.ERROR206).toJSONString();
		}
		Map<String, Object> condition = new HashMap<String, Object>();
		for (String str : pkeys) {
			String strv = Tools.getReqParamSafe(req, str);
			vMap = Tools.verifyParam(str, strv, 0, 0);
			if(vMap != null){
				return ResultTools.custom(Tips.ERROR206, str).toJSONString();
			}
			condition.put(str, strv);
		}
		//保存
		int res = service.delete(condition);
		if(res > 0){
			return ResultTools.custom(Tips.ERROR1).toJSONString();
		}else{
			return ResultTools.custom(Tips.ERROR0).put(ResultKey.DATA, res).toJSONString();
		}
    }
	
	
    public String deleteBy(HttpServletRequest req) {
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		//参数
		Map<String, Object> condition = Tools.getReqParamsToMap(req, getObejctClass());
		if(condition == null || condition.isEmpty()){
			return ResultTools.custom(Tips.ERROR211, "删除条件").toJSONString();
		}
		//保存
		int res = service.delete(condition);
		if(res > 0){
			return ResultTools.custom(Tips.ERROR1).toJSONString();
		}else{
			return ResultTools.custom(Tips.ERROR0).put(ResultKey.DATA, res).toJSONString();
		}
    }
	
	
    public String findAll(HttpServletRequest req) {
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		List<T> list = service.findAll();
        return ResultTools.custom(Tips.ERROR1).put(ResultKey.DATA, list).toJSONString();
    }
	
	
    public String findPage(HttpServletRequest req) {
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		//参数
		String page = Tools.getReqParamSafe(req, "page");
		String rows = Tools.getReqParamSafe(req, "rows");
		vMap = Tools.verifyParam("page", page, 0, 0, true);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		vMap = Tools.verifyParam("rows", rows, 0, 0, true);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		int start = Tools.parseInt(page) <= 1 ? 0 : (Tools.parseInt(page) - 1) * Tools.parseInt(rows);
		//参数
		Map<String, Object> condition = Tools.getReqParamsToMap(req, getObejctClass());
		
		List<T> list = service.findPage(condition, start, Tools.parseInt(rows));
		long total = service.size(condition);
        return ResultTools.custom(Tips.ERROR1).put(ResultKey.TOTAL, total).put(ResultKey.DATA, list).toJSONString();
    }
	
	
    public String findOne(HttpServletRequest req) {
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		//参数
		Map<String, Object> condition = Tools.getReqParamsToMap(req, getObejctClass());
		if(condition == null || condition.isEmpty()){
			return ResultTools.custom(Tips.ERROR211, "查询条件").toJSONString();
		}
		T res = service.findOne(condition);
        return ResultTools.custom(Tips.ERROR1).put(ResultKey.DATA, res).toJSONString();
    }
	
	
    public String findList(HttpServletRequest req) {
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		//参数
		Map<String, Object> condition = Tools.getReqParamsToMap(req, getObejctClass());
		if(condition == null || condition.isEmpty()){
			return ResultTools.custom(Tips.ERROR211, "查询条件").toJSONString();
		}
		List<T> list = service.findList(condition);
        return ResultTools.custom(Tips.ERROR1).put(ResultKey.DATA, list).toJSONString();
    }
    
    
    public String excelHeader(HttpServletRequest req){
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		//执行
		List<Map<String, Object>> excels = new ArrayList<Map<String, Object>>(); //获取导出字段
		Field[] fields = Tools.getFields(getObejctClass());
		for (Field f : fields) {
			if(f.isAnnotationPresent(Excel.class)){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sort", f.getAnnotation(Excel.class).sort());
				map.put("name", f.getAnnotation(Excel.class).name());
				map.put("field", f.getName());
				excels.add(map);
			}
		}
		//排序
		for (int i = 0; i < excels.size(); i++) {
			for (int j = i; j < excels.size(); j++) {
				if((Integer)excels.get(i).get("sort") > (Integer)excels.get(j).get("sort")){
					Map<String, Object> tmp = excels.get(i);
					excels.set(i, excels.get(j));
					excels.set(j, tmp);
				}
			}
		}
		return ResultTools.custom(Tips.ERROR1).put(ResultKey.DATA, excels).toJSONString();
	}
	
	public String excelFree(HttpServletRequest req, HttpServletResponse resp){
		initService();
		
		Map<String, Object> vMap = null;
		//登录
		vMap = verifyLogin(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//sign
		vMap = verifySign(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		//权限
		vMap = verifyAuth(req);
		if(vMap != null){
			return JsonTools.toJsonString(vMap);
		}
		
		//执行
		Map<String, Object> condition = Tools.getReqParamsToMap(req, getObejctClass());
		List<T> res = service.findList(condition);
		String name = getObejctClass().getSimpleName().toLowerCase();
		resp.addHeader("Content-Disposition","attachment;filename="+name+".csv");
		// response.addHeader("Content-Length", "" + JSONArray.fromObject(list).toString().getBytes().length);
		resp.setContentType("application/octet-stream;charset=utf-8");
		try {
			List<Map<String, Object>> excels = new ArrayList<Map<String, Object>>(); //获取导出字段
			OutputStream toClient = new BufferedOutputStream(resp.getOutputStream());
			//header
			Field[] fields = Tools.getFields(getObejctClass());
			for (Field f : fields) {
				if(f.isAnnotationPresent(Excel.class)){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("sort", f.getAnnotation(Excel.class).sort());
					map.put("name", f.getAnnotation(Excel.class).name());
					map.put("field", f.getName());
					excels.add(map);
				}
			}
			//排序
			for (int i = 0; i < excels.size(); i++) {
				for (int j = i; j < excels.size(); j++) {
					if((Integer)excels.get(i).get("sort") > (Integer)excels.get(j).get("sort")){
						Map<String, Object> tmp = excels.get(i);
						excels.set(i, excels.get(j));
						excels.set(j, tmp);
					}
				}
			}
			
			String headers = Tools.getReqParamSafe(req, "fields"); //可选导出项。
			
			String head = "";
			for (int i = 0; i < excels.size(); i++) {
				if(Tools.isNullOrEmpty(headers)){
					head += "," + "\"" + (excels.get(i).get("name") == null ? "" :excels.get(i).get("name").toString().replace("\"", "\"\""))+ "\"";
				}else{
					String[] hs = headers.replace("，", ",").split(",");
					for (String tmp : hs) {
						if(tmp.equals(excels.get(i).get("field"))){
							head += "," + "\"" + (excels.get(i).get("name") == null ? "" :excels.get(i).get("name").toString().replace("\"", "\"\""))+ "\"";
						}
					}
				}
			}
			head = Tools.isNullOrEmpty(head) ? "" : head.substring(1);
			head += "\n";
			toClient.write(head.getBytes("utf-8"));
			//遍历导出数据
			//可导项
			List<Map<String, Object>> temps = new ArrayList<Map<String, Object>>(); //获取导出字段
			if(!Tools.isNullOrEmpty(headers)){
				String[] hs = headers.replace("，", ",").split(",");
				for (String tmp : hs) {
					for (int i = 0; i < excels.size(); i++) {
						if(tmp.equals(excels.get(i).get("field"))){
							temps.add(excels.get(i));
							break;
						}
					}
				}
				excels = temps;
			}
			//可导数据
			for (T node : res) { //遍历导出数据
				String str = "";
				for (int j = 0; j < excels.size(); j++) { //遍历导出字段
					String excelField = excels.get(j).get("field").toString();
					String getMethodName = "get" + excelField.substring(0, 1).toUpperCase() + excelField.substring(1);
					Object value = null;
					try{
						Method method = Tools.findMethod(node.getClass(), getMethodName);
						value = method.invoke(node);
					} catch (Exception e) {
						e.printStackTrace();
					}
					//如果内容有逗号，将这个字段整体用双引号括起来；如果里面还有双引号就替换成两个双引号。
					str += "," + "\"" + (value == null ? "" : value.toString().replace("\"", "\"\""))+ "\"";
				}
				str = Tools.isNullOrEmpty(str) ? "" : str.substring(1);
				str +=  "\n";
				toClient.write(str.getBytes("utf-8"));
			}
			
			toClient.flush();
			toClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private Class<T> getObejctClass(){
		return Utils.getObejctClass(getClass());
	}
	
}
