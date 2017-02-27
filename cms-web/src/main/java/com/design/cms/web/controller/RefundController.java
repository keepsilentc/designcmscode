package com.design.cms.web.controller;

import java.util.List;
import java.util.Map;

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
import com.design.cms.common.utils.HttpUtil;
import com.design.cms.common.utils.SignUtil;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.common.utils.ValidateUtils;
import com.design.cms.dao.entity.RefundLog;
import com.design.cms.dao.entity.dto.RefundInfo;
import com.design.cms.service.api.IRefundService;
import com.design.cms.service.api.vo.refund.DoRefundVo;
import com.design.cms.service.api.vo.refund.RefundApproveVo;
import com.design.cms.service.api.vo.refund.RefundSearchVo;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.PageAssist;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

@RequestMapping("/refund")
@Controller
public class RefundController {
	private static Logger log = LoggerFactory.getLogger(RefundController.class);
	@Resource
	private IRefundService refundServiceImpl;
	
	@Resource
	private PageAssist pageAssist;
	
	@RequestMapping("/index")
	private String index(){
		return "refund/refundlist";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	private Response<List<RefundInfo>> search(@RequestBody RefundSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<RefundInfo>> resp = new Response<List<RefundInfo>>();
		try{
			log.info("查询退款列表接口执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			pageAssist.pageQuery(refundServiceImpl,vo,resp);
		}catch(DesignException ex){
			log.info("查询退款列表接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询退款列表接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
	@RequestMapping("/approve/index")
	public String approveIndex(HttpServletRequest req,@RequestParam String refundNo){
		if (StringUtils.isEmpty(refundNo)) {
			throw new DesignException(DesignEx.INTERNAL_ERROR);
		}else{
			req.setAttribute("refundNo", refundNo);
		}
		return "refund/refundapprove";
	}
	
	@RequestMapping("/getRefundInfo")
	@ResponseBody
	private Response<RefundInfo> getRefundInfo(@RequestBody String refundNo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<RefundInfo> resp = new Response<RefundInfo>();
		try{
			log.info("查询退款详细信息接口执行...{}",refundNo);
			RefundInfo refundInfo = refundServiceImpl.getRefundInfo(refundNo);
			resp.setResult(refundInfo);
		}catch(DesignException ex){
			log.info("查询退款详细信息接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询退款详细信息接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/follow")
	@ResponseBody
	private Response<List<RefundLog>> follow(@RequestBody String refundNo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<RefundLog>> resp = new Response<List<RefundLog>>();
		try{
			log.info("跟踪退款接口执行...{}",refundNo);
			List<RefundLog> refundLogList = refundServiceImpl.follow(refundNo);
			resp.setResult(refundLogList);
		}catch(DesignException ex){
			log.info("跟踪退款接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("跟踪退款接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/refundApprove")
	@ResponseBody
	private Response<Void> refundApprove(@RequestBody RefundApproveVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("退款审批接口执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			refundServiceImpl.refundApprove(vo);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("退款审批接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("退款审批接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/doRefund")
	@ResponseBody
	private Response<Void> doRefund(@RequestBody DoRefundVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("退款接口执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			Map<String,String> params = Maps.newTreeMap();
			params.put("refundType","REFUND");
			params.put("refundNo", vo.getRefundNo());
			params.put("key", "@^DEsignZxkj");
			String sign = SignUtil.sign(params);
			params.put("sign", sign);
			HttpUtil.post("http://116.62.26.227/design-web/refund/refund.do", params);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("退款接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("退款接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
}
