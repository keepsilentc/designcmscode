package com.design.cms.web.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.design.cms.dao.entity.Size;
import com.design.cms.dao.entity.SizeCountry;
import com.design.cms.dao.entity.dto.SizeInfo;
import com.design.cms.service.api.ISizeService;
import com.design.cms.service.api.vo.product.SizeAddVo;
import com.design.cms.service.api.vo.product.SizeReq;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.ImportAndExportUtil;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
@RequestMapping("/size")
@Controller
public class SizeController {

	private static Logger log = LoggerFactory.getLogger(SizeController.class);

	@Resource
	private ISizeService sizeServiceImpl;
	
	@RequestMapping("/index")
	public String index(){
		return "size/sizelist";
	}
	
	
	
	@RequestMapping("/getAllSizeCountry")
	@ResponseBody
	public Response<List<SizeCountry>> getAllSizeCountry(){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<SizeCountry>> resp = new Response<List<SizeCountry>>();
		try{
			log.info("查询尺寸国家接口执行...");
			List<SizeCountry> t_sizeCountrys = sizeServiceImpl.getAllSizeCountry();
			resp.setResult(t_sizeCountrys);
		}catch(DesignException ex){
			log.info("查询尺寸国家接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询尺寸国家接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<SizeInfo>> search(@RequestBody SizeReq vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<SizeInfo>> resp = new Response<List<SizeInfo>>();
		try{
			log.info("查询尺寸接口执行...{}",vo);
			List<SizeInfo> t_sizes = sizeServiceImpl.getAllSize(vo);
			resp.setResult(t_sizes);
		}catch(DesignException ex){
			log.info("查询尺寸接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询尺寸接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Void> addSize(@RequestBody SizeAddVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("新增尺寸接口开始执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			Size size = DozerUtils.transfer(vo, Size.class);
			sizeServiceImpl.addSize(size);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("增加尺寸接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("增加尺寸接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateSize(@RequestBody Size size){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑尺寸接口执行...{}",size);
			sizeServiceImpl.updateSize(size);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑尺寸接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑尺寸接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<Integer> delSize(@RequestBody Long id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Integer> resp = new Response<Integer>();
		try{
			log.info("删除尺寸接口执行...{}",id);
			if (id!=null) {
				Integer num = sizeServiceImpl.delSize(id);
				resp.setResult(num);
			}
		}catch(DesignException ex){
			log.info("删除尺寸接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("删除尺寸接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/export")
	@ResponseBody
	public void export(SizeReq vo){
		TraceLogIdUtils.setTraceLogId(null);
		try{
			log.info("导出尺寸接口执行...{}",vo);
			LinkedHashMap<String,String> head = Maps.newLinkedHashMap();
			head.put("sizeCountryName", "尺寸国家");
			head.put("SizeTypeName", "尺寸类型名称");
			head.put("SizeName", "尺寸名称");
			head.put("OrderBy", "排序");
			List<Map<String,Object>> data = sizeServiceImpl.getExportData(vo);
			ImportAndExportUtil.export("size", "size", head, data);
		}catch(DesignException ex){
			log.info("导出尺寸接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
		}catch(Exception e){
			log.info("导出尺寸接口执行异常,{}",Throwables.getStackTraceAsString(e));
		}
	}
}
