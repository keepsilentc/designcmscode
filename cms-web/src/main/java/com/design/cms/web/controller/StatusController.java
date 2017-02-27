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
import com.design.cms.dao.entity.Status;
import com.design.cms.dao.entity.dto.StatusInfo;
import com.design.cms.service.api.IStatusService;
import com.design.cms.service.api.vo.designer.StatusAddReq;
import com.design.cms.service.api.vo.designer.StatusSearchVo;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.PageAssist;
import com.google.common.base.Throwables;

@RequestMapping("/status")
@Controller
public class StatusController {
	
	private static Logger log = LoggerFactory.getLogger(StatusController.class);
	
	@Resource
	private IStatusService statusServiceImpl;
	@Resource
	private PageAssist pageAssist;
	
	@RequestMapping("/index")
	public String index(){
		return "status/statuslist";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Long> addStatus(@RequestBody StatusAddReq req){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Long> resp = new Response<Long>();
		try{
			log.info("新增文章接口开始...{}",req);
			ValidateUtils.validateEntiryThrows(req);
			Status status = DozerUtils.transfer(req, Status.class);
			Long id = statusServiceImpl.addStatus(status);
			resp.setResult(id);
		}catch(DesignException ex){
			log.info("新增文章接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("新增文章接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateStatus(@RequestBody Status status){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑文章接口执行...{}",status);
			statusServiceImpl.updateStatus(status);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑文章接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑文章接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<StatusInfo>> search(@RequestBody StatusSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<StatusInfo>> resp = new Response<List<StatusInfo>>();
		try{
			log.info("查询文章接口执行...{}",vo);
			pageAssist.pageQuery(statusServiceImpl,vo,resp);
		}catch(DesignException ex){
			log.info("查询文章接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询文章接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getStatusDetailTxt")
	@ResponseBody
	public Response<String> getStatusDetailTxt(@RequestBody String url){
		TraceLogIdUtils.setTraceLogId(null);
		Response<String> resp = new Response<String>();
		try{
			log.info("查询文章接口执行...{}",url);
			String content = statusServiceImpl.getStatusDetailTxt(url);
			resp.setResult(content);
		}catch(DesignException ex){
			log.info("查询文章接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询文章接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
}
