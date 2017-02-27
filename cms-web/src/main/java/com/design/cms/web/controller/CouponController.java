package com.design.cms.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.CouponStrategy;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.DozerUtils;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.common.utils.ValidateUtils;
import com.design.cms.dao.entity.Coupon;
import com.design.cms.dao.entity.dto.CouponInfo;
import com.design.cms.service.api.ICouponService;
import com.design.cms.service.api.vo.coupon.CouponAddVo;
import com.design.cms.service.api.vo.coupon.CouponSearchVo;
import com.design.cms.service.api.vo.coupon.CouponUpdateVo;
import com.design.cms.service.api.vo.coupon.Discount;
import com.design.cms.service.api.vo.coupon.FullMinus;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.PageAssist;
import com.google.common.base.Throwables;

@RequestMapping("/coupon")
@Controller
public class CouponController {
	
	private static Logger log = LoggerFactory.getLogger(CouponController.class);
	@Resource
	private ICouponService couponServiceImpl;
	
	@Resource
	private PageAssist pageAssist;
	
	@RequestMapping("/index")
	public String index(){
		return "coupon/couponlist";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Long> addCoupon(@RequestBody CouponAddVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Long> resp = new Response<Long>();
		try{
			log.info("新增优惠券接口开始...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			if(CouponStrategy.DISCOUNT.getStrategyCode().equals(vo.getCouponStrategy())){
				ValidateUtils.validateEntiryThrows(vo,Discount.class);
			}else if(CouponStrategy.FULL_REDUCTION.getStrategyCode().equals(vo.getCouponStrategy())){
				ValidateUtils.validateEntiryThrows(vo,FullMinus.class);
			}
			Coupon coupon = DozerUtils.transfer(vo, Coupon.class);
			Long id = couponServiceImpl.addCoupon(coupon);
			resp.setResult(id);
		}catch(DesignException ex){
			log.info("新增优惠券接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("新增优惠券接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateCoupon(@RequestBody CouponUpdateVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑优惠券接口执行...,{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			if(CouponStrategy.DISCOUNT.getStrategyCode().equals(vo.getCouponStrategy())){
				ValidateUtils.validateEntiryThrows(vo,Discount.class);
			}else if(CouponStrategy.FULL_REDUCTION.getStrategyCode().equals(vo.getCouponStrategy())){
				ValidateUtils.validateEntiryThrows(vo,FullMinus.class);
			}
			Coupon coupon = DozerUtils.transfer(vo, Coupon.class);
			couponServiceImpl.updateCoupon(coupon);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑优惠券接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑优惠券接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<Coupon>> search(@RequestBody CouponSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<Coupon>> resp = new Response<List<Coupon>>();
		try{
			log.info("查询优惠券接口执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			pageAssist.pageQuery(couponServiceImpl,vo,resp);
		}catch(DesignException ex){
			log.info("查询优惠券接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询优惠券接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<Void> delCoupon(@RequestBody String id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("删除优惠券接口执行...{}",id);
			couponServiceImpl.delCoupon(Long.valueOf(id));
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("删除优惠券接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("删除优惠券接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getCouponInfo")
	@ResponseBody
	public Response<CouponInfo> getCouponInfo(@RequestBody String id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<CouponInfo> resp = new Response<CouponInfo>();
		try{
			log.info("获取优惠券信息接口执行...{}",id);
			CouponInfo couponInfo = couponServiceImpl.getCouponInfo(Long.valueOf(id));
			resp.setResult(couponInfo);
		}catch(DesignException ex){
			log.info("获取优惠券信息接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("获取优惠券信息接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/allocate/index")
	public String allocateIndex(HttpServletRequest req,String couponNo){
		req.setAttribute("couponNo", couponNo);
		return "coupon/allocate";
	}
	
}
