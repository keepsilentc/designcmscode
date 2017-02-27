package com.design.cms.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.dao.entity.Color;
import com.design.cms.service.api.IColorService;
import com.design.cms.web.dto.Response;
import com.google.common.base.Throwables;

@RequestMapping("/color")
@Controller
public class ColorController {
	
	private static Logger log = LoggerFactory.getLogger(ColorController.class);

	@Resource
	private IColorService colorServiceImpl;
	
	@RequestMapping("/index")
	public String index(){
		return "color/colorlist";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<Color>> search(String name){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<Color>> resp = new Response<List<Color>>();
		try{
			log.info("查询颜色接口执行...{}",name);
			List<Color> t_colors = colorServiceImpl.getAllColor(name);
			resp.setResult(t_colors);
		}catch(DesignException ex){
			log.info("查询颜色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询颜色接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Void> addColor(@RequestBody Color color){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("增加颜色接口执行...{}",color);
			colorServiceImpl.addColor(color);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("增加颜色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("增加颜色接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateColor(@RequestBody Color color){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("增加颜色接口执行...{}",color);
			colorServiceImpl.updateColor(color);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("增加颜色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("增加颜色接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<Integer> delColor(@RequestParam Long id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Integer> resp = new Response<Integer>();
		try{
			log.info("删除颜色接口执行...{}",id);
			Integer num = colorServiceImpl.delColor(id);
			resp.setResult(num);
		}catch(DesignException ex){
			log.info("删除颜色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("删除颜色接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
}
