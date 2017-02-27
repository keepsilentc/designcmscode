package com.design.cms.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.common.utils.ValidateUtils;
import com.design.cms.dao.entity.ReturnLog;
import com.design.cms.dao.entity.dto.ReturnInfo;
import com.design.cms.service.api.IReturnService;
import com.design.cms.service.api.vo.returns.ReturnApproveVo;
import com.design.cms.service.api.vo.returns.ReturnSearchVo;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.PageAssist;
import com.google.common.base.Throwables;

@Controller
@RequestMapping("/return")
public class ReturnController {
	
	private static Logger log = LoggerFactory.getLogger(ReturnController.class);
	
	@Resource
	private IReturnService returnServiceImpl;
	@Resource
	private PageAssist pageAssist;
	
	@RequestMapping("/index")
	private String index(){
		return "return/returnlist";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	private Response<List<ReturnInfo>> search(@RequestBody ReturnSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<ReturnInfo>> resp = new Response<List<ReturnInfo>>();
		try{
			log.info("查询退换货列表接口执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			pageAssist.pageQuery(returnServiceImpl,vo,resp);
		}catch(DesignException ex){
			log.info("查询退换货列表接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询退换货列表接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
	@RequestMapping("/approve/index")
	public String approveIndex(HttpServletRequest req,@RequestParam String returnNo){
		if (StringUtils.isEmpty(returnNo)) {
			throw new DesignException(DesignEx.INTERNAL_ERROR);
		}else{
			req.setAttribute("returnNo", returnNo);
		}
		return "return/returnapprove";
	}
	
	@RequestMapping("/getReturnInfo")
	@ResponseBody
	private Response<ReturnInfo> getReturnInfo(@RequestBody String returnNo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<ReturnInfo> resp = new Response<ReturnInfo>();
		try{
			log.info("查询退换货详细信息接口执行...{}",returnNo);
			ReturnInfo returnInfo = returnServiceImpl.getReturnInfo(returnNo);
			resp.setResult(returnInfo);
		}catch(DesignException ex){
			log.info("查询退换货详细信息接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询退换货详细信息接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/follow")
	@ResponseBody
	private Response<List<ReturnLog>> follow(@RequestBody String returnNo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<ReturnLog>> resp = new Response<List<ReturnLog>>();
		try{
			log.info("跟踪退换货接口执行...{}",returnNo);
			List<ReturnLog> returnLogList = returnServiceImpl.follow(returnNo);
			resp.setResult(returnLogList);
		}catch(DesignException ex){
			log.info("跟踪退换货接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("跟踪退换货接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/returnApprove")
	@ResponseBody
	private Response<Void> returnApprove(@RequestBody ReturnApproveVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("退换货审批接口执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			returnServiceImpl.returnApprove(vo);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("退换货审批接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("退换货审批接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
}
