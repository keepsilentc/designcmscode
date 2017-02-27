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
import com.design.cms.dao.entity.Theme;
import com.design.cms.dao.entity.dto.ThemeInfo;
import com.design.cms.service.api.IDesignerService;
import com.design.cms.service.api.IThemeService;
import com.design.cms.service.api.vo.designer.ThemeAddVo;
import com.design.cms.service.api.vo.designer.ThemeSearchVo;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.ImportAndExportUtil;
import com.design.cms.web.utils.PageAssist;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

@RequestMapping("/theme")
@Controller
public class ThemeController {
	private static Logger log = LoggerFactory.getLogger(ThemeController.class);
	@Resource
	private IDesignerService designerServiceImpl;
	@Resource
	private IThemeService themeServiceImpl;
	@Resource
	private PageAssist pageAssist;
	
	@RequestMapping("/index")
	public String index(){
		return "theme/themelist";
	}
	
	@RequestMapping("/getAllTheme")
	@ResponseBody
	public Response<List<Theme>> getAllTheme(@RequestBody(required=false) Long designerId){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<Theme>> resp = new Response<List<Theme>>();
		try{
			log.info("查询设计师系列接口执行...{}",designerId);
			List<Theme> t_themes = themeServiceImpl.getAllTheme(designerId);
			resp.setResult(t_themes);
		}catch(DesignException ex){
			log.info("查询设计师系列接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询设计师系列接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Long> addTheme(@RequestBody ThemeAddVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Long> resp = new Response<Long>();
		try{
			log.info("新增系列接口开始执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			Theme theme = DozerUtils.transfer(vo, Theme.class);
			Long id = themeServiceImpl.addTheme(theme);
			resp.setResult(id);
		}catch(DesignException ex){
			log.info("新增系列接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("新增系列接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateTheme(@RequestBody Theme theme){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑系列接口执行...{}",theme);
			themeServiceImpl.updateTheme(theme);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑系列接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑系列接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<ThemeInfo>> search(@RequestBody ThemeSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<ThemeInfo>> resp = new Response<List<ThemeInfo>>();
		try{
			log.info("查询系列接口执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			pageAssist.pageQuery(themeServiceImpl,vo,resp);
		}catch(DesignException ex){
			log.info("查询系列接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询系列接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<Void> delTheme(@RequestBody String id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("删除系列接口执行...{}",id);
			themeServiceImpl.delTheme(Long.valueOf(id));
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
	
	@RequestMapping("/export")
	@ResponseBody
	public void export(ThemeSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		try{
			log.info("导出系列接口执行...{}",vo);
			LinkedHashMap<String,String> head = Maps.newLinkedHashMap();
			head.put("DesignerName", "设计师名称");
			head.put("ThemeName", "系列名称");
			head.put("Described", "描述");
			head.put("IsEnable", "启用状态");
			List<Map<String,Object>> data = themeServiceImpl.getExportData(vo);
			ImportAndExportUtil.export("theme", "theme", head, data);
		}catch(DesignException ex){
			log.info("导出系列接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
		}catch(Exception e){
			log.info("导出系列接口执行异常,{}",Throwables.getStackTraceAsString(e));
		}
	}
}
