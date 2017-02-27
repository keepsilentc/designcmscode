package com.design.cms.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.DozerUtils;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.dao.entity.CmsUser;
import com.design.cms.dao.entity.CmsUserRole;
import com.design.cms.dao.entity.dto.CmsRoleInfo;
import com.design.cms.service.api.ICmsUserService;
import com.design.cms.service.api.vo.user.CmsUserAddVo;
import com.design.cms.service.api.vo.user.CmsUserSearchVo;
import com.design.cms.web.dto.Response;
import com.google.common.base.Throwables;

@RequestMapping("/cmsuser")
@Controller
public class CmsUserController {
	
	private static Logger log = LoggerFactory.getLogger(CmsUserController.class);
	
	@Resource
	private ICmsUserService cmsUserServiceImpl;
	
	@RequestMapping("/index")
	public String index(){
		return "cmsuser/userlist";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<CmsUser>> search(@RequestBody CmsUserSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<CmsUser>> resp = new Response<List<CmsUser>>();
		try{
			log.info("查询cms用户接口执行...{}",vo);
			List<CmsUser> t_Users = cmsUserServiceImpl.getAllUser(vo);
			resp.setResult(t_Users);
		}catch(DesignException ex){
			log.info("查询cms用户接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询cms用户接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Void> addUser(@RequestBody CmsUserAddVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("增加cms用户接口执行...{}",vo);
			CmsUser cmsUser = DozerUtils.transfer(vo, CmsUser.class);
			cmsUserServiceImpl.addUser(cmsUser);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("增加cms用户接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("增加cms用户接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateUser(@RequestBody CmsUser cmsUser){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("增加cms用户接口执行...{}",cmsUser);
			cmsUserServiceImpl.updateUser(cmsUser);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("增加cms用户接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("增加cms用户接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<Integer> delUser(@RequestBody Long id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Integer> resp = new Response<Integer>();
		try{
			log.info("删除cms用户接口执行...{}",id);
			Integer num = cmsUserServiceImpl.delUser(id);
			resp.setResult(num);
		}catch(DesignException ex){
			log.info("删除cms用户接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("删除cms用户接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getUserRoles")
	@ResponseBody
	public Response<List<CmsRoleInfo>> getUserRoles(@RequestBody Long userId){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<CmsRoleInfo>> resp = new Response<List<CmsRoleInfo>>();
		try{
			log.info("获取用户角色接口执行...{}",userId);
			List<CmsRoleInfo> userRoles = cmsUserServiceImpl.getUserRoles(userId);
			resp.setResult(userRoles);
		}catch(DesignException ex){
			log.info("获取用户角色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("获取用户角色接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/saveUserRoles")
	@ResponseBody
	public Response<Void> saveUserRoles(@RequestBody String data){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("修改用户角色接口执行...{}",data);
			List<CmsUserRole> userRoles = JSON.parseArray(data, CmsUserRole.class);
			cmsUserServiceImpl.saveUserRoles(userRoles);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("修改用户角色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("修改用户角色接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
}
