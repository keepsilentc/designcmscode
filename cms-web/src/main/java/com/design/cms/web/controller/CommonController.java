package com.design.cms.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.dao.entity.Brand;
import com.design.cms.dao.entity.Country;
import com.design.cms.dao.entity.SizeCountry;
import com.design.cms.dao.entity.SizeType;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.CacheAssist;
import com.google.common.base.Throwables;

@RequestMapping("/common")
@Controller
public class CommonController {
	
	private static Logger log = LoggerFactory.getLogger(CommonController.class);
	
	@Resource
	private CacheAssist cacheAssist;
	
	@RequestMapping("/getAllCountry")
	@ResponseBody
	public Response<List<Country>> getAllcountry(){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<Country>> resp = new Response<List<Country>>();
		try{
			log.info("查询所有国家接口执行...");
			List<Country> t_countrys = cacheAssist.getCountryList();
			resp.setResult(t_countrys);
		}catch(DesignException ex){
			log.info("查询所有国家接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询所有国家接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getAllBarnd")
	@ResponseBody
	public Response<List<Brand>> getAllBarnd(){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<Brand>> resp = new Response<List<Brand>>();
		try{
			log.info("查询所有品牌接口执行...");
			List<Brand> t_countrys = cacheAssist.getBrandList();
			resp.setResult(t_countrys);
		}catch(DesignException ex){
			log.info("查询所有品牌接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询所有品牌接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getAllSizeType")
	@ResponseBody
	public Response<List<SizeType>> getAllSizeType(){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<SizeType>> resp = new Response<List<SizeType>>();
		try{
			log.info("查询所有尺寸类型接口执行...");
			List<SizeType> t_sizeTypes = cacheAssist.getSizeTypeList();
			resp.setResult(t_sizeTypes);
		}catch(DesignException ex){
			log.info("查询所有尺寸类型接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询所有尺寸类型接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getAllSizeCountry")
	@ResponseBody
	public Response<List<SizeCountry>> getAllSizeCountry(){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<SizeCountry>> resp = new Response<List<SizeCountry>>();
		try{
			log.info("查询所有尺寸国家接口执行...");
			List<SizeCountry> t_sizeCountrys = cacheAssist.getSizeCountryList();
			resp.setResult(t_sizeCountrys);
		}catch(DesignException ex){
			log.info("查询所有尺寸国家接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询所有尺寸国家接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
}
