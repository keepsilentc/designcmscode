package com.design.cms.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.common.utils.ValidateUtils;
import com.design.cms.service.api.vo.payload.PayLoad;
import com.design.cms.service.impl.payload.ManualPayLoadServiceImpl;
import com.design.cms.web.dto.Response;
import com.google.common.base.Throwables;


@RequestMapping("/payLoad")
@Controller
public class PayLoadController {
	
	private static Logger log = LoggerFactory.getLogger(PayLoadController.class);
	
	@Resource
	private ManualPayLoadServiceImpl manualPayLoadServiceImpl;
	
	@RequestMapping("/index")
	public String index(){
		return "payload/payloadlist";
	}
	
	@RequestMapping("/add/index")
	public String addIndex(){
		return "payload/addpayload";
	}
	
	@RequestMapping("/doUserDefined")
	@ResponseBody
	public Response<Void> doUserDefined(@RequestBody PayLoad vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("执行推送接口执行...",vo);
			ValidateUtils.validateEntiryThrows(vo);
			manualPayLoadServiceImpl.doUserDefined(vo);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("执行推送接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("执行推送接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
}
