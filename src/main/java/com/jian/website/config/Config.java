package com.jian.website.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
	
	//自动填充主键
	public static String autoFillPrimaryKey; //自动填充主键
	//日期自动填充配置
	public static String autoFillDateForAdd; //新增日期类型自动填充
	public static String autoFillDateForModify; //修改日期类型自动填充
	//静态资源
	public static String upload_path; //文件上传地址
	public static String logs_path; //日志地址
	
	//登录session
	public static String login_session_key="login_user";

	//登录sso
	public static String sso_url;
	public static String sso_module;
	

	@Value("${sso_url}")
	public void setSso_url(String sso_url) {
		Config.sso_url = sso_url;
	}
	@Value("${sso_module}")
	public void setSso_module(String sso_module) {
		Config.sso_module = sso_module;
	}
	
	@Value("${login_session_key}")
	public void setLogin_session_key(String login_session_key) {
		Config.login_session_key = login_session_key;
	}
	
	@Value("${upload_path}")
	public void setUpload_path(String upload_path) {
		Config.upload_path = upload_path;
	}
	
	@Value("${logs_path}")
	public void setLogs_path(String logs_path) {
		Config.logs_path = logs_path;
	}
	
	@Value("${auto_fill_primary_key}")
	public void setAutoFillPrimaryKey(String autoFillPrimaryKey) {
		System.out.println("auto_fill_primary_key: "+autoFillPrimaryKey);
		Config.autoFillPrimaryKey = autoFillPrimaryKey;
	}
	@Value("${auto_fill_date_for_add}")
	public void setAutoFillDateForAdd(String autoFillDateForAdd) {
		System.out.println("auto_fill_date_for_add: "+autoFillDateForAdd);
		Config.autoFillDateForAdd = autoFillDateForAdd;
	}
	@Value("${auto_fill_date_for_modify}")
	public void setAutoFillDateForModify(String autoFillDateForModify) {
		System.out.println("auto_fill_date_for_modify: "+autoFillDateForModify);
		Config.autoFillDateForModify = autoFillDateForModify;
	}
	
	
}
