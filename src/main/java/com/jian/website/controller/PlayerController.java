package com.jian.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jian.annotation.API;
import com.jian.annotation.ParamsInfo;
import com.jian.tools.core.ResultKey;

import com.jian.website.entity.Player;
import com.jian.website.service.PlayerService;

@Controller
@RequestMapping("/api/player")
@API(info="", entity={Player.class})
public class PlayerController extends BaseController<Player> {

	@Autowired
	private PlayerService service;
	
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
				@ParamsInfo(name="ip", type="String", isNull=0,  info="IP"),
				@ParamsInfo(name="type", type="int", isNull=0,  info="分类"),
				@ParamsInfo(name="group", type="String", isNull=0,  info="分组"),
				@ParamsInfo(name="player", type="String", isNull=0,  info="参与者"),
				@ParamsInfo(name="icon", type="String", isNull=0,  info="头像"),
				@ParamsInfo(name="content", type="String", isNull=0,  info="文章"),
				@ParamsInfo(name="pic", type="String", isNull=0,  info="图片"),
				@ParamsInfo(name="video", type="String", isNull=0,  info="视频"),
				@ParamsInfo(name="audio", type="String", isNull=0,  info="音频"),
				@ParamsInfo(name="title", type="String", isNull=0,  info="标题"),
				@ParamsInfo(name="desc", type="String", isNull=0,  info="描述"),
				@ParamsInfo(name="info", type="String", isNull=0,  info="附加信息"),
				@ParamsInfo(name="view", type="int", isNull=0,  info="浏览量"),
				@ParamsInfo(name="good", type="int", isNull=0,  info="点赞量"),
				@ParamsInfo(name="offset", type="int", isNull=0,  info="偏移量"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态：0 -- 待审核，1 -- 通过，2 -- 未通过"),
				@ParamsInfo(name="auther", type="String", isNull=0,  info="审核人"),
				@ParamsInfo(name="adate", type="String", isNull=0,  info="审核时间"),
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
				@ParamsInfo(name="ip", type="String", isNull=0,  info="IP"),
				@ParamsInfo(name="type", type="int", isNull=0,  info="分类"),
				@ParamsInfo(name="group", type="String", isNull=0,  info="分组"),
				@ParamsInfo(name="player", type="String", isNull=0,  info="参与者"),
				@ParamsInfo(name="icon", type="String", isNull=0,  info="头像"),
				@ParamsInfo(name="content", type="String", isNull=0,  info="文章"),
				@ParamsInfo(name="pic", type="String", isNull=0,  info="图片"),
				@ParamsInfo(name="video", type="String", isNull=0,  info="视频"),
				@ParamsInfo(name="audio", type="String", isNull=0,  info="音频"),
				@ParamsInfo(name="title", type="String", isNull=0,  info="标题"),
				@ParamsInfo(name="desc", type="String", isNull=0,  info="描述"),
				@ParamsInfo(name="info", type="String", isNull=0,  info="附加信息"),
				@ParamsInfo(name="view", type="int", isNull=0,  info="浏览量"),
				@ParamsInfo(name="good", type="int", isNull=0,  info="点赞量"),
				@ParamsInfo(name="offset", type="int", isNull=0,  info="偏移量"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态：0 -- 待审核，1 -- 通过，2 -- 未通过"),
				@ParamsInfo(name="auther", type="String", isNull=0,  info="审核人"),
				@ParamsInfo(name="adate", type="String", isNull=0,  info="审核时间"),
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
				@ParamsInfo(name="ip", type="String", isNull=0,  info="IP"),
				@ParamsInfo(name="date", type="String", isNull=0,  info="日期"),
				@ParamsInfo(name="type", type="int", isNull=0,  info="分类"),
				@ParamsInfo(name="group", type="String", isNull=0,  info="分组"),
				@ParamsInfo(name="player", type="String", isNull=0,  info="参与者"),
				@ParamsInfo(name="icon", type="String", isNull=0,  info="头像"),
				@ParamsInfo(name="content", type="String", isNull=0,  info="文章"),
				@ParamsInfo(name="pic", type="String", isNull=0,  info="图片"),
				@ParamsInfo(name="video", type="String", isNull=0,  info="视频"),
				@ParamsInfo(name="audio", type="String", isNull=0,  info="音频"),
				@ParamsInfo(name="title", type="String", isNull=0,  info="标题"),
				@ParamsInfo(name="desc", type="String", isNull=0,  info="描述"),
				@ParamsInfo(name="info", type="String", isNull=0,  info="附加信息"),
				@ParamsInfo(name="info1", type="String", isNull=0,  info=""),
				@ParamsInfo(name="info2", type="String", isNull=0,  info=""),
				@ParamsInfo(name="info3", type="String", isNull=0,  info=""),
				@ParamsInfo(name="info4", type="String", isNull=0,  info=""),
				@ParamsInfo(name="view", type="int", isNull=0,  info="浏览量"),
				@ParamsInfo(name="good", type="int", isNull=0,  info="点赞量"),
				@ParamsInfo(name="offset", type="int", isNull=0,  info="偏移量"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态：0 -- 待审核，1 -- 通过，2 -- 未通过"),
				@ParamsInfo(name="auther", type="String", isNull=0,  info="审核人"),
				@ParamsInfo(name="adate", type="String", isNull=0,  info="审核时间"),
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
				@ParamsInfo(name="ip", type="String", isNull=0,  info="IP"),
				@ParamsInfo(name="date", type="String", isNull=0,  info="日期"),
				@ParamsInfo(name="type", type="int", isNull=0,  info="分类"),
				@ParamsInfo(name="group", type="String", isNull=0,  info="分组"),
				@ParamsInfo(name="player", type="String", isNull=0,  info="参与者"),
				@ParamsInfo(name="icon", type="String", isNull=0,  info="头像"),
				@ParamsInfo(name="content", type="String", isNull=0,  info="文章"),
				@ParamsInfo(name="pic", type="String", isNull=0,  info="图片"),
				@ParamsInfo(name="video", type="String", isNull=0,  info="视频"),
				@ParamsInfo(name="audio", type="String", isNull=0,  info="音频"),
				@ParamsInfo(name="title", type="String", isNull=0,  info="标题"),
				@ParamsInfo(name="desc", type="String", isNull=0,  info="描述"),
				@ParamsInfo(name="info", type="String", isNull=0,  info="附加信息"),
				@ParamsInfo(name="info1", type="String", isNull=0,  info=""),
				@ParamsInfo(name="info2", type="String", isNull=0,  info=""),
				@ParamsInfo(name="info3", type="String", isNull=0,  info=""),
				@ParamsInfo(name="info4", type="String", isNull=0,  info=""),
				@ParamsInfo(name="view", type="int", isNull=0,  info="浏览量"),
				@ParamsInfo(name="good", type="int", isNull=0,  info="点赞量"),
				@ParamsInfo(name="offset", type="int", isNull=0,  info="偏移量"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态：0 -- 待审核，1 -- 通过，2 -- 未通过"),
				@ParamsInfo(name="auther", type="String", isNull=0,  info="审核人"),
				@ParamsInfo(name="adate", type="String", isNull=0,  info="审核时间"),
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
				@ParamsInfo(name="ip", type="String", isNull=0,  info="IP"),
				@ParamsInfo(name="date", type="String", isNull=0,  info="日期"),
				@ParamsInfo(name="type", type="int", isNull=0,  info="分类"),
				@ParamsInfo(name="group", type="String", isNull=0,  info="分组"),
				@ParamsInfo(name="player", type="String", isNull=0,  info="参与者"),
				@ParamsInfo(name="icon", type="String", isNull=0,  info="头像"),
				@ParamsInfo(name="content", type="String", isNull=0,  info="文章"),
				@ParamsInfo(name="pic", type="String", isNull=0,  info="图片"),
				@ParamsInfo(name="video", type="String", isNull=0,  info="视频"),
				@ParamsInfo(name="audio", type="String", isNull=0,  info="音频"),
				@ParamsInfo(name="title", type="String", isNull=0,  info="标题"),
				@ParamsInfo(name="desc", type="String", isNull=0,  info="描述"),
				@ParamsInfo(name="info", type="String", isNull=0,  info="附加信息"),
				@ParamsInfo(name="info1", type="String", isNull=0,  info=""),
				@ParamsInfo(name="info2", type="String", isNull=0,  info=""),
				@ParamsInfo(name="info3", type="String", isNull=0,  info=""),
				@ParamsInfo(name="info4", type="String", isNull=0,  info=""),
				@ParamsInfo(name="view", type="int", isNull=0,  info="浏览量"),
				@ParamsInfo(name="good", type="int", isNull=0,  info="点赞量"),
				@ParamsInfo(name="offset", type="int", isNull=0,  info="偏移量"),
				@ParamsInfo(name="status", type="int", isNull=0,  info="状态：0 -- 待审核，1 -- 通过，2 -- 未通过"),
				@ParamsInfo(name="auther", type="String", isNull=0,  info="审核人"),
				@ParamsInfo(name="adate", type="String", isNull=0,  info="审核时间"),
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
