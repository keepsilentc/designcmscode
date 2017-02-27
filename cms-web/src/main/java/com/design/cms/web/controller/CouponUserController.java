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
import com.design.cms.dao.entity.User;
import com.design.cms.service.api.ICouponUserService;
import com.design.cms.service.api.vo.coupon.CouponUserVo;
import com.design.cms.web.dto.Response;
import com.google.common.base.Throwables;

@RequestMapping("/couponUser")
@Controller
public class CouponUserController {
	
	private static Logger log = LoggerFactory.getLogger(CouponUserController.class);
	
	@Resource
	private ICouponUserService couponUserServiceImpl;
	
	@RequestMapping("/getCouponNotAllocateUsers")
	@ResponseBody
	public Response<List<User>> getCouponNotAllocateUsers(@RequestBody String couponNo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<User>> resp = new Response<List<User>>();
		try{
			log.info("新增优惠券用户接口开始...{}",couponNo);
			List<User> userList = couponUserServiceImpl.getCouponNotAllocateUsers(couponNo);
			resp.setResult(userList);
		}catch(DesignException ex){
			log.info("新增优惠券用户接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("新增优惠券用户接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Response<Void> addCouponUsers(@RequestBody CouponUserVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("新增优惠券用户接口开始...{}",vo);
			couponUserServiceImpl.addCouponUsers(vo);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("新增优惠券用户接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("新增优惠券用户接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
}
