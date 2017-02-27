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
import com.design.cms.dao.entity.Designer;
import com.design.cms.dao.entity.dto.DesignerInfo;
import com.design.cms.service.api.IDesignerService;
import com.design.cms.service.api.vo.designer.DesignerAddVo;
import com.design.cms.service.api.vo.designer.DesignerReq;
import com.design.cms.service.api.vo.designer.DesignerSearchVo;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.ImportAndExportUtil;
import com.design.cms.web.utils.PageAssist;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

@RequestMapping("/designer")
@Controller
public class DesignerController {
	
	private static Logger log = LoggerFactory.getLogger(DesignerController.class);
	
	@Resource
	private IDesignerService designerServiceImpl;
	
	@Resource
	private PageAssist pageAssist;
	
	@RequestMapping("/getAllDesigner")
	@ResponseBody
	public Response<List<DesignerInfo>> getAllDesigner(@RequestBody(required=false) DesignerReq req){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<DesignerInfo>> resp = new Response<List<DesignerInfo>>();
		try{
			log.info("查询所有设计师接口执行...{}",req);
			List<DesignerInfo> t_designers = designerServiceImpl.getAllDesigner(req);
			resp.setResult(t_designers);
		}catch(DesignException ex){
			log.info("查询所有设计师接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询所有设计师接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/index")
	public String index(){
		return "designer/designerlist";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Long> addDesigner(@RequestBody DesignerAddVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Long> resp = new Response<Long>();
		try{
			log.info("新增设计师接口开始执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			Designer designer = DozerUtils.transfer(vo, Designer.class);
			Long id = designerServiceImpl.addDesigner(designer);
			resp.setResult(id);
		}catch(DesignException ex){
			log.info("新增设计师接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("新增设计师接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateDesigner(@RequestBody Designer designer){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑设计师接口执行...{}",designer);
			designerServiceImpl.updateDesigner(designer);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑设计师接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑设计师接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<DesignerInfo>> search(@RequestBody DesignerSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<DesignerInfo>> resp = new Response<List<DesignerInfo>>();
		try{
			log.info("查询设计师接口执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			pageAssist.pageQuery(designerServiceImpl,vo,resp);
		}catch(DesignException ex){
			log.info("查询设计师接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询设计师接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<Integer> delDesigner(@RequestBody Long id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Integer> resp = new Response<Integer>();
		try{
			log.info("删除设计师接口执行...{}",id);
			Integer num = designerServiceImpl.delDesigner(id);
			resp.setResult(num);
		}catch(DesignException ex){
			log.info("删除设计师接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("删除设计师接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/export")
	@ResponseBody
	public void export(DesignerSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		try{
			log.info("导出设计师接口执行...{}",vo);
			LinkedHashMap<String,String> head = Maps.newLinkedHashMap();
			head.put("CountryName", "国家");
			head.put("BrandName", "品牌");
			head.put("DesignerName", "设计师名称");
			head.put("Described", "描述");
			head.put("Remark", "备注");
			head.put("IsEnable", "启用状态");
			head.put("CreateTime", "创建时间");
			List<Map<String,Object>> data = designerServiceImpl.getExportData(vo);
			ImportAndExportUtil.export("designer", "设计师", head, data);
		}catch(DesignException ex){
			log.info("导出设计师接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
		}catch(Exception e){
			log.info("导出设计师接口执行异常,{}",Throwables.getStackTraceAsString(e));
		}
	}
}
