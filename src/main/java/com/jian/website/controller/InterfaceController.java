package com.jian.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jian.annotation.API;
import com.jian.annotation.ParamsInfo;
import com.jian.tools.core.ResultKey;

import com.jian.website.entity.Interface;
import com.jian.website.service.InterfaceService;

@Controller
@RequestMapping("/api/interface")
@API(info="", entity={Interface.class})
public class InterfaceController extends BaseController<Interface> {

	@Autowired
	private InterfaceService service;
	
	@Override
	public void initService() {
		super.service = service;
	}
	
	//TODO 基本方法
	
	@Override
	@RequestMapping("/add")
    @ResponseBody
	@API(name="新增", 
		info="需登录认证", 
		request={
				//add request
				@ParamsInfo(name="name", type="String", isNull=0,  info="名称"),
				@ParamsInfo(name="en", type="String", isNull=0,  info="英文名  调用接口时使用"),
				@ParamsInfo(name="url", type="String", isNull=0,  info="接口地址"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态    0：禁用，1：启用"),
				@ParamsInfo(name="reqType", type="int", isNull=0,  info="请求方式   0：POST，1：GET"),
				@ParamsInfo(name="reqStructure", type="int", isNull=0,  info="请求数据格式   0：无要求，1：JSON，2：XML"),
				@ParamsInfo(name="reqParams", type="String", isNull=0,  info="请求参数   多个逗号隔开"),
				@ParamsInfo(name="signType", type="int", isNull=0,  info="参数加密方式  0：不加密，1：MD5"),
				@ParamsInfo(name="signName", type="String", isNull=0,  info="密文参数名"),
				@ParamsInfo(name="signMode", type="int", isNull=0,  info="参数链接方式   0：key=value&...，1：keyvalue..."),
				@ParamsInfo(name="connector", type="String", isNull=0,  info="连接符"),
				@ParamsInfo(name="separator", type="String", isNull=0,  info="分隔符"),
				@ParamsInfo(name="keyType", type="int", isNull=0,  info="密钥方式  0：无密钥，1：服务器密钥，2：时间戳（毫秒），3：时间戳（秒），4：自定义密钥，5：固定密钥123456700"),
				@ParamsInfo(name="keyName", type="String", isNull=0,  info="密钥参数名"),
				@ParamsInfo(name="keyValue", type="String", isNull=0,  info="自定义密钥"),
				@ParamsInfo(name="respType", type="int", isNull=0,  info="响应方式   0：respones stream，1：respones body"),
				@ParamsInfo(name="respStructure", type="int", isNull=0,  info="响应数据类型    0：字符串，1：JSON，2：XML"),
				@ParamsInfo(name="success", type="int", isNull=0,  info="是否处理结果   0：否，1：是"),
				@ParamsInfo(name="sname", type="String", isNull=0,  info="结果的标识"),
				@ParamsInfo(name="scode", type="String", isNull=0,  info="成功结果的标识"),
				@ParamsInfo(name="tcode", type="int", isNull=0,  info="图形码验证  0：不验证，1：正确后失效，2：正确后不失效"),
				@ParamsInfo(name="vcode", type="int", isNull=0,  info="短信验证码验证   0：不验证，1：正确后失效，2：正确后不失效"),
				@ParamsInfo(name="sign", type="int", isNull=0,  info="其他验证   0：不验证，1：DH验证后失效，2：DH验证后不失效"),
				@ParamsInfo(name="limit", type="int", isNull=0,  info="IP限制  0：否，1：是"),
				@ParamsInfo(name="reSend", type="int", isNull=0,  info="失败重发  0：否，1：是"),
				@ParamsInfo(name="reCount", type="int", isNull=0,  info="重发次数"),
				@ParamsInfo(name="info", type="String", isNull=0,  info="接口文档说明"),
		}, 
		response={
				@ParamsInfo(name=ResultKey.CODE, type="int", info="返回码"),
				@ParamsInfo(name=ResultKey.MSG, type="String", info="状态描述"),
				@ParamsInfo(name=ResultKey.DATA, type="", info="数据集"),
		})
	public String add(HttpServletRequest req) {
		return super.add(req);
	}


	@Override
	@RequestMapping("/update")
    @ResponseBody
	@API(name="修改", 
		info="需登录认证", 
		request={
				//modify request
				@ParamsInfo(info="修改条件："),
				@ParamsInfo(name="pid", type="int", isNull=1,  info="自增主键"),
				@ParamsInfo(info="可修改字段："),
				@ParamsInfo(name="name", type="String", isNull=0,  info="名称"),
				@ParamsInfo(name="en", type="String", isNull=0,  info="英文名  调用接口时使用"),
				@ParamsInfo(name="url", type="String", isNull=0,  info="接口地址"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态    0：禁用，1：启用"),
				@ParamsInfo(name="reqType", type="int", isNull=0,  info="请求方式   0：POST，1：GET"),
				@ParamsInfo(name="reqStructure", type="int", isNull=0,  info="请求数据格式   0：无要求，1：JSON，2：XML"),
				@ParamsInfo(name="reqParams", type="String", isNull=0,  info="请求参数   多个逗号隔开"),
				@ParamsInfo(name="signType", type="int", isNull=0,  info="参数加密方式  0：不加密，1：MD5"),
				@ParamsInfo(name="signName", type="String", isNull=0,  info="密文参数名"),
				@ParamsInfo(name="signMode", type="int", isNull=0,  info="参数链接方式   0：key=value&...，1：keyvalue..."),
				@ParamsInfo(name="connector", type="String", isNull=0,  info="连接符"),
				@ParamsInfo(name="separator", type="String", isNull=0,  info="分隔符"),
				@ParamsInfo(name="keyType", type="int", isNull=0,  info="密钥方式  0：无密钥，1：服务器密钥，2：时间戳（毫秒），3：时间戳（秒），4：自定义密钥，5：固定密钥123456700"),
				@ParamsInfo(name="keyName", type="String", isNull=0,  info="密钥参数名"),
				@ParamsInfo(name="keyValue", type="String", isNull=0,  info="自定义密钥"),
				@ParamsInfo(name="respType", type="int", isNull=0,  info="响应方式   0：respones stream，1：respones body"),
				@ParamsInfo(name="respStructure", type="int", isNull=0,  info="响应数据类型    0：字符串，1：JSON，2：XML"),
				@ParamsInfo(name="success", type="int", isNull=0,  info="是否处理结果   0：否，1：是"),
				@ParamsInfo(name="sname", type="String", isNull=0,  info="结果的标识"),
				@ParamsInfo(name="scode", type="String", isNull=0,  info="成功结果的标识"),
				@ParamsInfo(name="tcode", type="int", isNull=0,  info="图形码验证  0：不验证，1：正确后失效，2：正确后不失效"),
				@ParamsInfo(name="vcode", type="int", isNull=0,  info="短信验证码验证   0：不验证，1：正确后失效，2：正确后不失效"),
				@ParamsInfo(name="sign", type="int", isNull=0,  info="其他验证   0：不验证，1：DH验证后失效，2：DH验证后不失效"),
				@ParamsInfo(name="limit", type="int", isNull=0,  info="IP限制  0：否，1：是"),
				@ParamsInfo(name="reSend", type="int", isNull=0,  info="失败重发  0：否，1：是"),
				@ParamsInfo(name="reCount", type="int", isNull=0,  info="重发次数"),
				@ParamsInfo(name="info", type="String", isNull=0,  info="接口文档说明"),
		}, 
		response={
				@ParamsInfo(name=ResultKey.CODE, type="int", info="返回码"),
				@ParamsInfo(name=ResultKey.MSG, type="String", info="状态描述"),
				@ParamsInfo(name=ResultKey.DATA, type="", info="数据集"),
		})
	public String update(HttpServletRequest req) {
		return super.update(req);
	}

	@Override
	@RequestMapping("/delete")
    @ResponseBody
	@API(name="删除", 
		info="需登录认证", 
		request={
				//delete request
				@ParamsInfo(name="pid", type="int", isNull=1,  info="自增主键"),
		}, 
		response={
				@ParamsInfo(name=ResultKey.CODE, type="int", info="返回码"),
				@ParamsInfo(name=ResultKey.MSG, type="String", info="状态描述"),
				@ParamsInfo(name=ResultKey.DATA, type="", info="数据集"),
		})
	public String delete(HttpServletRequest req) {
		return super.delete(req);
	}

	@Override
	@RequestMapping("/findPage")
    @ResponseBody
	@API(name="分页查询", 
		info="需登录认证", 
		request={
				@ParamsInfo(name="page", type="int", isNull=1, info="页码"),
				@ParamsInfo(name="rows", type="int", isNull=1, info="每页条数"),
				//findPage request
				@ParamsInfo(info="可选条件："),
				@ParamsInfo(name="pid", type="int", isNull=0,  info="自增主键"),
				@ParamsInfo(name="name", type="String", isNull=0,  info="名称"),
				@ParamsInfo(name="en", type="String", isNull=0,  info="英文名  调用接口时使用"),
				@ParamsInfo(name="url", type="String", isNull=0,  info="接口地址"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态    0：禁用，1：启用"),
				@ParamsInfo(name="reqType", type="int", isNull=0,  info="请求方式   0：POST，1：GET"),
				@ParamsInfo(name="reqStructure", type="int", isNull=0,  info="请求数据格式   0：无要求，1：JSON，2：XML"),
				@ParamsInfo(name="reqParams", type="String", isNull=0,  info="请求参数   多个逗号隔开"),
				@ParamsInfo(name="signType", type="int", isNull=0,  info="参数加密方式  0：不加密，1：MD5"),
				@ParamsInfo(name="signName", type="String", isNull=0,  info="密文参数名"),
				@ParamsInfo(name="signMode", type="int", isNull=0,  info="参数链接方式   0：key=value&...，1：keyvalue..."),
				@ParamsInfo(name="connector", type="String", isNull=0,  info="连接符"),
				@ParamsInfo(name="separator", type="String", isNull=0,  info="分隔符"),
				@ParamsInfo(name="keyType", type="int", isNull=0,  info="密钥方式  0：无密钥，1：服务器密钥，2：时间戳（毫秒），3：时间戳（秒），4：自定义密钥，5：固定密钥123456700"),
				@ParamsInfo(name="keyName", type="String", isNull=0,  info="密钥参数名"),
				@ParamsInfo(name="keyValue", type="String", isNull=0,  info="自定义密钥"),
				@ParamsInfo(name="respType", type="int", isNull=0,  info="响应方式   0：respones stream，1：respones body"),
				@ParamsInfo(name="respStructure", type="int", isNull=0,  info="响应数据类型    0：字符串，1：JSON，2：XML"),
				@ParamsInfo(name="success", type="int", isNull=0,  info="是否处理结果   0：否，1：是"),
				@ParamsInfo(name="sname", type="String", isNull=0,  info="结果的标识"),
				@ParamsInfo(name="scode", type="String", isNull=0,  info="成功结果的标识"),
				@ParamsInfo(name="tcode", type="int", isNull=0,  info="图形码验证  0：不验证，1：正确后失效，2：正确后不失效"),
				@ParamsInfo(name="vcode", type="int", isNull=0,  info="短信验证码验证   0：不验证，1：正确后失效，2：正确后不失效"),
				@ParamsInfo(name="sign", type="int", isNull=0,  info="其他验证   0：不验证，1：DH验证后失效，2：DH验证后不失效"),
				@ParamsInfo(name="limit", type="int", isNull=0,  info="IP限制  0：否，1：是"),
				@ParamsInfo(name="reSend", type="int", isNull=0,  info="失败重发  0：否，1：是"),
				@ParamsInfo(name="reCount", type="int", isNull=0,  info="重发次数"),
				@ParamsInfo(name="info", type="String", isNull=0,  info="接口文档说明"),
		}, 
		response={
				@ParamsInfo(name=ResultKey.CODE, type="int", info="返回码"),
				@ParamsInfo(name=ResultKey.MSG, type="String", info="状态描述"),
				@ParamsInfo(name=ResultKey.DATA, type="Array", info="数据集"),
				@ParamsInfo(name=ResultKey.TOTAL, type="int", info="总数"),
		})
	public String findPage(HttpServletRequest req) {
		return super.findPage(req);
	}

	@Override
	@RequestMapping("/findOne")
    @ResponseBody
	@API(name="查询一个", 
		info="需登录认证", 
		request={
				//findOne request
				@ParamsInfo(info="可选条件："),
				@ParamsInfo(name="pid", type="int", isNull=0,  info="自增主键"),
				@ParamsInfo(name="name", type="String", isNull=0,  info="名称"),
				@ParamsInfo(name="en", type="String", isNull=0,  info="英文名  调用接口时使用"),
				@ParamsInfo(name="url", type="String", isNull=0,  info="接口地址"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态    0：禁用，1：启用"),
				@ParamsInfo(name="reqType", type="int", isNull=0,  info="请求方式   0：POST，1：GET"),
				@ParamsInfo(name="reqStructure", type="int", isNull=0,  info="请求数据格式   0：无要求，1：JSON，2：XML"),
				@ParamsInfo(name="reqParams", type="String", isNull=0,  info="请求参数   多个逗号隔开"),
				@ParamsInfo(name="signType", type="int", isNull=0,  info="参数加密方式  0：不加密，1：MD5"),
				@ParamsInfo(name="signName", type="String", isNull=0,  info="密文参数名"),
				@ParamsInfo(name="signMode", type="int", isNull=0,  info="参数链接方式   0：key=value&...，1：keyvalue..."),
				@ParamsInfo(name="connector", type="String", isNull=0,  info="连接符"),
				@ParamsInfo(name="separator", type="String", isNull=0,  info="分隔符"),
				@ParamsInfo(name="keyType", type="int", isNull=0,  info="密钥方式  0：无密钥，1：服务器密钥，2：时间戳（毫秒），3：时间戳（秒），4：自定义密钥，5：固定密钥123456700"),
				@ParamsInfo(name="keyName", type="String", isNull=0,  info="密钥参数名"),
				@ParamsInfo(name="keyValue", type="String", isNull=0,  info="自定义密钥"),
				@ParamsInfo(name="respType", type="int", isNull=0,  info="响应方式   0：respones stream，1：respones body"),
				@ParamsInfo(name="respStructure", type="int", isNull=0,  info="响应数据类型    0：字符串，1：JSON，2：XML"),
				@ParamsInfo(name="success", type="int", isNull=0,  info="是否处理结果   0：否，1：是"),
				@ParamsInfo(name="sname", type="String", isNull=0,  info="结果的标识"),
				@ParamsInfo(name="scode", type="String", isNull=0,  info="成功结果的标识"),
				@ParamsInfo(name="tcode", type="int", isNull=0,  info="图形码验证  0：不验证，1：正确后失效，2：正确后不失效"),
				@ParamsInfo(name="vcode", type="int", isNull=0,  info="短信验证码验证   0：不验证，1：正确后失效，2：正确后不失效"),
				@ParamsInfo(name="sign", type="int", isNull=0,  info="其他验证   0：不验证，1：DH验证后失效，2：DH验证后不失效"),
				@ParamsInfo(name="limit", type="int", isNull=0,  info="IP限制  0：否，1：是"),
				@ParamsInfo(name="reSend", type="int", isNull=0,  info="失败重发  0：否，1：是"),
				@ParamsInfo(name="reCount", type="int", isNull=0,  info="重发次数"),
				@ParamsInfo(name="info", type="String", isNull=0,  info="接口文档说明"),
				@ParamsInfo(info="注意：以上条件不可同时为空。"),
		}, 
		response={
				@ParamsInfo(name=ResultKey.CODE, type="int", info="返回码"),
				@ParamsInfo(name=ResultKey.MSG, type="String", info="状态描述"),
				@ParamsInfo(name=ResultKey.DATA, type="Object", info="数据集"),
		})
	public String findOne(HttpServletRequest req) {
		return super.findOne(req);
	}

	@Override
	@RequestMapping("/findList")
    @ResponseBody
	@API(name="查询多个", 
		info="需登录认证", 
		request={
				//findList request
				@ParamsInfo(info="可选条件："),
				@ParamsInfo(name="pid", type="int", isNull=0,  info="自增主键"),
				@ParamsInfo(name="name", type="String", isNull=0,  info="名称"),
				@ParamsInfo(name="en", type="String", isNull=0,  info="英文名  调用接口时使用"),
				@ParamsInfo(name="url", type="String", isNull=0,  info="接口地址"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态    0：禁用，1：启用"),
				@ParamsInfo(name="reqType", type="int", isNull=0,  info="请求方式   0：POST，1：GET"),
				@ParamsInfo(name="reqStructure", type="int", isNull=0,  info="请求数据格式   0：无要求，1：JSON，2：XML"),
				@ParamsInfo(name="reqParams", type="String", isNull=0,  info="请求参数   多个逗号隔开"),
				@ParamsInfo(name="signType", type="int", isNull=0,  info="参数加密方式  0：不加密，1：MD5"),
				@ParamsInfo(name="signName", type="String", isNull=0,  info="密文参数名"),
				@ParamsInfo(name="signMode", type="int", isNull=0,  info="参数链接方式   0：key=value&...，1：keyvalue..."),
				@ParamsInfo(name="connector", type="String", isNull=0,  info="连接符"),
				@ParamsInfo(name="separator", type="String", isNull=0,  info="分隔符"),
				@ParamsInfo(name="keyType", type="int", isNull=0,  info="密钥方式  0：无密钥，1：服务器密钥，2：时间戳（毫秒），3：时间戳（秒），4：自定义密钥，5：固定密钥123456700"),
				@ParamsInfo(name="keyName", type="String", isNull=0,  info="密钥参数名"),
				@ParamsInfo(name="keyValue", type="String", isNull=0,  info="自定义密钥"),
				@ParamsInfo(name="respType", type="int", isNull=0,  info="响应方式   0：respones stream，1：respones body"),
				@ParamsInfo(name="respStructure", type="int", isNull=0,  info="响应数据类型    0：字符串，1：JSON，2：XML"),
				@ParamsInfo(name="success", type="int", isNull=0,  info="是否处理结果   0：否，1：是"),
				@ParamsInfo(name="sname", type="String", isNull=0,  info="结果的标识"),
				@ParamsInfo(name="scode", type="String", isNull=0,  info="成功结果的标识"),
				@ParamsInfo(name="tcode", type="int", isNull=0,  info="图形码验证  0：不验证，1：正确后失效，2：正确后不失效"),
				@ParamsInfo(name="vcode", type="int", isNull=0,  info="短信验证码验证   0：不验证，1：正确后失效，2：正确后不失效"),
				@ParamsInfo(name="sign", type="int", isNull=0,  info="其他验证   0：不验证，1：DH验证后失效，2：DH验证后不失效"),
				@ParamsInfo(name="limit", type="int", isNull=0,  info="IP限制  0：否，1：是"),
				@ParamsInfo(name="reSend", type="int", isNull=0,  info="失败重发  0：否，1：是"),
				@ParamsInfo(name="reCount", type="int", isNull=0,  info="重发次数"),
				@ParamsInfo(name="info", type="String", isNull=0,  info="接口文档说明"),
				@ParamsInfo(info="注意：以上条件不可同时为空。"),
		}, 
		response={
				@ParamsInfo(name=ResultKey.CODE, type="int", info="返回码"),
				@ParamsInfo(name=ResultKey.MSG, type="String", info="状态描述"),
				@ParamsInfo(name=ResultKey.DATA, type="Array", info="数据集"),
		})
	public String findList(HttpServletRequest req) {
		return super.findList(req);
	}
	
	//TODO 自定义方法
	
}
