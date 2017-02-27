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
import com.design.cms.common.utils.DozerUtils;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.common.utils.ValidateUtils;
import com.design.cms.dao.entity.Category;
import com.design.cms.service.api.ICategoryService;
import com.design.cms.service.api.vo.product.CategoryAddVo;
import com.design.cms.web.dto.Response;
import com.google.common.base.Throwables;

@RequestMapping("/category")
@Controller
public class CateGoryController{
	
	private static Logger log = LoggerFactory.getLogger(CateGoryController.class);
	
	@Resource
	private ICategoryService categoryServiceImpl;
	
	@RequestMapping("/index")
	public String index(){
		return "category/categorylist";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<Category>> search(@RequestBody Long parentId){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<Category>> resp = new Response<List<Category>>();
		try{
			log.info("查询分类接口执行...parentId:{}",parentId);
			List<Category> t_categorys = categoryServiceImpl.getCategorys(parentId);
			resp.setResult(t_categorys);
		}catch(DesignException ex){
			log.info("查询分类接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询分类接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Long> addCategory(@RequestBody CategoryAddVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Long> resp = new Response<Long>();
		try{
			log.info("新增分类接口开始...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			Category category = DozerUtils.transfer(vo, Category.class);
			Long id = categoryServiceImpl.addCategory(category);
			resp.setResult(id);
		}catch(DesignException ex){
			log.info("新增分类接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("新增分类接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateCategory(@RequestBody Category category){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑分类接口执行..{}",category);
			categoryServiceImpl.updateCategory(category);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑分类接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑分类接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<Void> delCategory(@RequestBody String id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("删除系列接口执行....{}",id);
			categoryServiceImpl.delCategory(Long.valueOf(id));
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("删除系列接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("删除系列接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
}
