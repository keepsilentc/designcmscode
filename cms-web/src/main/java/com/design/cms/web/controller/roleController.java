package com.design.cms.web.controller;

import java.util.List;
import java.util.Map;

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
import com.design.cms.dao.entity.CmsRole;
import com.design.cms.dao.entity.CmsRoleResources;
import com.design.cms.dao.entity.dto.CmsResourcesInfo;
import com.design.cms.service.api.IRoleService;
import com.design.cms.service.api.vo.user.CmsRoleAddVo;
import com.design.cms.web.dto.Response;
import com.google.common.base.Throwables;

@RequestMapping("/role")
@Controller
public class roleController {
	
	private static Logger log = LoggerFactory.getLogger(roleController.class);
	
	@Resource
	private IRoleService roleServiceImpl;
	
	@RequestMapping("/index")
	public String index(){
		return "role/rolelist";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<CmsRole>> search(){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<CmsRole>> resp = new Response<List<CmsRole>>();
		try{
			log.info("查询cms用户角色接口执行...");
			List<CmsRole> t_Roles = roleServiceImpl.getAllRole();
			resp.setResult(t_Roles);
		}catch(DesignException ex){
			log.info("查询cms用户角色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询cms用户角色接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Void> addRole(@RequestBody CmsRoleAddVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("增加cms用户角色接口执行...{}",vo);
			CmsRole cmsRole = DozerUtils.transfer(vo, CmsRole.class);
			roleServiceImpl.addRole(cmsRole);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("增加cms用户角色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("增加cms用户角色接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateRole(@RequestBody CmsRole cmsRole){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("增加cms用户角色接口执行...{}",cmsRole);
			roleServiceImpl.updateRole(cmsRole);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("增加cms用户角色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("增加cms用户角色接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getRoleResources")
	@ResponseBody
	public Response<Map<String,List<CmsResourcesInfo>>> getRoleResources(@RequestBody Long roleId){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Map<String,List<CmsResourcesInfo>>> resp = new Response<Map<String,List<CmsResourcesInfo>>>();
		try{
			log.info("查询资源接口执行...");
			Map<String,List<CmsResourcesInfo>>	resources = roleServiceImpl.getRoleResources(roleId);
			resp.setResult(resources);
		}catch(DesignException ex){
			log.info("查询资源接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询资源接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/saveRoleResources")
	@ResponseBody
	public Response<Void> saveRoleResources(@RequestBody String data){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("修改角色资源接口执行...{}",data);
			List<CmsRoleResources> roleResources = JSON.parseArray(data, CmsRoleResources.class);
			roleServiceImpl.saveRoleResources(roleResources);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("修改角色资源接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("修改角色资源接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getAllRole")
	@ResponseBody
	public Response<List<CmsRole>> getAllRole(){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<CmsRole>> resp = new Response<List<CmsRole>>();
		try{
			log.info("获取所有角色接口执行...");
			List<CmsRole> cmsRoles = roleServiceImpl.getAllRole();
			resp.setResult(cmsRoles);
		}catch(DesignException ex){
			log.info("获取所有角色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("获取所有角色接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
}
