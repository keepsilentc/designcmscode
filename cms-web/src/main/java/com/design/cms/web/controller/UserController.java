package com.design.cms.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.dao.entity.dto.UserInfo;
import com.design.cms.service.api.IUserService;
import com.design.cms.service.api.vo.user.UserSearchVo;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.PageAssist;
import com.google.common.base.Throwables;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private IUserService userServiceImpl;
	
	@Resource
	private PageAssist pageAssist;
	
	@RequestMapping("/index")
	public String index(){
		return "user/userlist";
	}
	
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<UserInfo>> search(@RequestBody UserSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<UserInfo>> resp = new Response<List<UserInfo>>();
		try{
			log.info("查询尺寸用户列表接口执行...{}",vo);
			pageAssist.pageQuery(userServiceImpl,vo,resp);
		}catch(DesignException ex){
			log.info("查询尺寸用户列表接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询尺寸用户列表接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getUserByUserNo")
	@ResponseBody
	public Response<UserInfo> getUserByUserNo(@RequestBody String userNo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<UserInfo> resp = new Response<UserInfo>();
		try{
			log.info("查询尺寸用户接口执行...{}",userNo);
			UserInfo t_UserInfo = userServiceImpl.getUserByUserNo(userNo);
			resp.setResult(t_UserInfo);
		}catch(DesignException ex){
			log.info("查询尺寸用户接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询尺寸用户接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
}
