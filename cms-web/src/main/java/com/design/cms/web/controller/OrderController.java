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
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.common.utils.ValidateUtils;
import com.design.cms.dao.entity.dto.OrderAllInfo;
import com.design.cms.dao.entity.dto.OrderInfo;
import com.design.cms.service.api.IOrderService;
import com.design.cms.service.api.vo.order.DeliverVo;
import com.design.cms.service.api.vo.order.OrderSearchVo;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.PageAssist;
import com.google.common.base.Throwables;

@RequestMapping("/order")
@Controller
public class OrderController {
	private static Logger log = LoggerFactory.getLogger(OrderController.class);
	@Resource
	private IOrderService orderServiceImpl;
	
	@Resource
	private PageAssist pageAssist;
	
	@RequestMapping("/index")
	private String index(){
		return "order/orderlist";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	private Response<List<OrderInfo>> search(@RequestBody OrderSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<OrderInfo>> resp = new Response<List<OrderInfo>>();
		try{
			log.info("查询订单列表接口执行...",vo);
			ValidateUtils.validateEntiryThrows(vo);
			pageAssist.pageQuery(orderServiceImpl,vo,resp);
		}catch(DesignException ex){
			log.info("查询订单列表接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询订单列表接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getOrderInfo")
	@ResponseBody
	private Response<OrderAllInfo> getOrderInfo(@RequestBody String orderNo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<OrderAllInfo> resp = new Response<OrderAllInfo>();
		try{
			log.info("查询订单详细接口执行...{}",orderNo);
			OrderAllInfo orderAllInfo = orderServiceImpl.getOrderAllInfo(orderNo);
			resp.setResult(orderAllInfo);
		}catch(DesignException ex){
			log.info("查询订单详细接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询订单详细接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/deliver")
	@ResponseBody
	private Response<Void> deliver(@RequestBody DeliverVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("发货接口执行...{}",vo);
			orderServiceImpl.deliver(vo);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("发货接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("发货接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
}
