package com.jian.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jian.annotation.API;
import com.jian.annotation.ParamsInfo;
import com.jian.tools.core.ResultKey;

import com.jian.website.entity.PlayerType;
import com.jian.website.service.PlayerTypeService;

@Controller
@RequestMapping("/api/playerType")
@API(info="", entity={PlayerType.class})
public class PlayerTypeController extends BaseController<PlayerType> {

	@Autowired
	private PlayerTypeService service;
	
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
				@ParamsInfo(name="en", type="String", isNull=0,  info="标识"),
				@ParamsInfo(name="start", type="String", isNull=0,  info="开始时间  yyyy-MM-dd HH:mm:ss"),
				@ParamsInfo(name="end", type="String", isNull=0,  info="结束时间  yyyy-MM-dd HH:mm:ss"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态   0 -- 关闭  1 --开启"),
				@ParamsInfo(name="count", type="int", isNull=0,  info="参加次数   小于0 --- 无限次"),
				@ParamsInfo(name="auth", type="int", isNull=0,  info="是否审核   0 -- 否  1 --是"),
				@ParamsInfo(name="sign", type="int", isNull=0,  info="是否验证  0 -- 否  1 --是"),
				@ParamsInfo(name="share", type="int", isNull=0,  info="是否分享  0 -- 否  1 --是"),
				@ParamsInfo(name="share_mode", type="int", isNull=0,  info="分享模式限制"),
				@ParamsInfo(name="share_count", type="int", isNull=0,  info="每次分享获得参与次数"),
				@ParamsInfo(name="share_limit", type="int", isNull=0,  info="分享次数   小于0 --- 无限次"),
				@ParamsInfo(name="poll", type="int", isNull=0,  info="是否投票  0 -- 否  1 --是"),
				@ParamsInfo(name="poll_mode", type="int", isNull=0,  info="投票模式限制"),
				@ParamsInfo(name="poll_count", type="int", isNull=0,  info="每次投票获得票数"),
				@ParamsInfo(name="poll_limit", type="int", isNull=0,  info="投票次数   小于0 --- 无限次"),
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
				@ParamsInfo(name="pid", type="int", isNull=1,  info="主键"),
				@ParamsInfo(info="可修改字段："),
				@ParamsInfo(name="name", type="String", isNull=0,  info="名称"),
				@ParamsInfo(name="en", type="String", isNull=0,  info="标识"),
				@ParamsInfo(name="start", type="String", isNull=0,  info="开始时间  yyyy-MM-dd HH:mm:ss"),
				@ParamsInfo(name="end", type="String", isNull=0,  info="结束时间  yyyy-MM-dd HH:mm:ss"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态   0 -- 关闭  1 --开启"),
				@ParamsInfo(name="count", type="int", isNull=0,  info="参加次数   小于0 --- 无限次"),
				@ParamsInfo(name="auth", type="int", isNull=0,  info="是否审核   0 -- 否  1 --是"),
				@ParamsInfo(name="sign", type="int", isNull=0,  info="是否验证  0 -- 否  1 --是"),
				@ParamsInfo(name="share", type="int", isNull=0,  info="是否分享  0 -- 否  1 --是"),
				@ParamsInfo(name="share_mode", type="int", isNull=0,  info="分享模式限制"),
				@ParamsInfo(name="share_count", type="int", isNull=0,  info="每次分享获得参与次数"),
				@ParamsInfo(name="share_limit", type="int", isNull=0,  info="分享次数   小于0 --- 无限次"),
				@ParamsInfo(name="poll", type="int", isNull=0,  info="是否投票  0 -- 否  1 --是"),
				@ParamsInfo(name="poll_mode", type="int", isNull=0,  info="投票模式限制"),
				@ParamsInfo(name="poll_count", type="int", isNull=0,  info="每次投票获得票数"),
				@ParamsInfo(name="poll_limit", type="int", isNull=0,  info="投票次数   小于0 --- 无限次"),
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
				@ParamsInfo(name="pid", type="int", isNull=1,  info="主键"),
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
				@ParamsInfo(name="pid", type="int", isNull=0,  info="主键"),
				@ParamsInfo(name="name", type="String", isNull=0,  info="名称"),
				@ParamsInfo(name="en", type="String", isNull=0,  info="标识"),
				@ParamsInfo(name="start", type="String", isNull=0,  info="开始时间  yyyy-MM-dd HH:mm:ss"),
				@ParamsInfo(name="end", type="String", isNull=0,  info="结束时间  yyyy-MM-dd HH:mm:ss"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态   0 -- 关闭  1 --开启"),
				@ParamsInfo(name="count", type="int", isNull=0,  info="参加次数   小于0 --- 无限次"),
				@ParamsInfo(name="auth", type="int", isNull=0,  info="是否审核   0 -- 否  1 --是"),
				@ParamsInfo(name="sign", type="int", isNull=0,  info="是否验证  0 -- 否  1 --是"),
				@ParamsInfo(name="share", type="int", isNull=0,  info="是否分享  0 -- 否  1 --是"),
				@ParamsInfo(name="share_mode", type="int", isNull=0,  info="分享模式限制"),
				@ParamsInfo(name="share_count", type="int", isNull=0,  info="每次分享获得参与次数"),
				@ParamsInfo(name="share_limit", type="int", isNull=0,  info="分享次数   小于0 --- 无限次"),
				@ParamsInfo(name="poll", type="int", isNull=0,  info="是否投票  0 -- 否  1 --是"),
				@ParamsInfo(name="poll_mode", type="int", isNull=0,  info="投票模式限制"),
				@ParamsInfo(name="poll_count", type="int", isNull=0,  info="每次投票获得票数"),
				@ParamsInfo(name="poll_limit", type="int", isNull=0,  info="投票次数   小于0 --- 无限次"),
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
				@ParamsInfo(name="pid", type="int", isNull=0,  info="主键"),
				@ParamsInfo(name="name", type="String", isNull=0,  info="名称"),
				@ParamsInfo(name="en", type="String", isNull=0,  info="标识"),
				@ParamsInfo(name="start", type="String", isNull=0,  info="开始时间  yyyy-MM-dd HH:mm:ss"),
				@ParamsInfo(name="end", type="String", isNull=0,  info="结束时间  yyyy-MM-dd HH:mm:ss"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态   0 -- 关闭  1 --开启"),
				@ParamsInfo(name="count", type="int", isNull=0,  info="参加次数   小于0 --- 无限次"),
				@ParamsInfo(name="auth", type="int", isNull=0,  info="是否审核   0 -- 否  1 --是"),
				@ParamsInfo(name="sign", type="int", isNull=0,  info="是否验证  0 -- 否  1 --是"),
				@ParamsInfo(name="share", type="int", isNull=0,  info="是否分享  0 -- 否  1 --是"),
				@ParamsInfo(name="share_mode", type="int", isNull=0,  info="分享模式限制"),
				@ParamsInfo(name="share_count", type="int", isNull=0,  info="每次分享获得参与次数"),
				@ParamsInfo(name="share_limit", type="int", isNull=0,  info="分享次数   小于0 --- 无限次"),
				@ParamsInfo(name="poll", type="int", isNull=0,  info="是否投票  0 -- 否  1 --是"),
				@ParamsInfo(name="poll_mode", type="int", isNull=0,  info="投票模式限制"),
				@ParamsInfo(name="poll_count", type="int", isNull=0,  info="每次投票获得票数"),
				@ParamsInfo(name="poll_limit", type="int", isNull=0,  info="投票次数   小于0 --- 无限次"),
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
				@ParamsInfo(name="pid", type="int", isNull=0,  info="主键"),
				@ParamsInfo(name="name", type="String", isNull=0,  info="名称"),
				@ParamsInfo(name="en", type="String", isNull=0,  info="标识"),
				@ParamsInfo(name="start", type="String", isNull=0,  info="开始时间  yyyy-MM-dd HH:mm:ss"),
				@ParamsInfo(name="end", type="String", isNull=0,  info="结束时间  yyyy-MM-dd HH:mm:ss"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态   0 -- 关闭  1 --开启"),
				@ParamsInfo(name="count", type="int", isNull=0,  info="参加次数   小于0 --- 无限次"),
				@ParamsInfo(name="auth", type="int", isNull=0,  info="是否审核   0 -- 否  1 --是"),
				@ParamsInfo(name="sign", type="int", isNull=0,  info="是否验证  0 -- 否  1 --是"),
				@ParamsInfo(name="share", type="int", isNull=0,  info="是否分享  0 -- 否  1 --是"),
				@ParamsInfo(name="share_mode", type="int", isNull=0,  info="分享模式限制"),
				@ParamsInfo(name="share_count", type="int", isNull=0,  info="每次分享获得参与次数"),
				@ParamsInfo(name="share_limit", type="int", isNull=0,  info="分享次数   小于0 --- 无限次"),
				@ParamsInfo(name="poll", type="int", isNull=0,  info="是否投票  0 -- 否  1 --是"),
				@ParamsInfo(name="poll_mode", type="int", isNull=0,  info="投票模式限制"),
				@ParamsInfo(name="poll_count", type="int", isNull=0,  info="每次投票获得票数"),
				@ParamsInfo(name="poll_limit", type="int", isNull=0,  info="投票次数   小于0 --- 无限次"),
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
