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
import com.design.cms.dao.entity.Carousel;
import com.design.cms.service.api.ICarouselService;
import com.design.cms.service.api.vo.carousel.CarouselAddVo;
import com.design.cms.service.api.vo.carousel.CarouselReq;
import com.design.cms.web.dto.Response;
import com.google.common.base.Throwables;

@RequestMapping("/carousel")
@Controller
public class CarouselController {
	
	private static Logger log = LoggerFactory.getLogger(CommonController.class);
	
	@Resource
	private ICarouselService carouselServiceImpl;
	
	@RequestMapping("/index")
	public String index(){
		return "carousel/carousellist";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<Carousel>> search(@RequestBody CarouselReq vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<Carousel>> resp = new Response<List<Carousel>>();
		try{
			log.info("查询轮播图接口执行...{}",vo);
			List<Carousel> t_carousels = carouselServiceImpl.getAllCarousel(vo);
			resp.setResult(t_carousels);
		}catch(DesignException ex){
			log.info("查询轮播图接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询轮播图接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Void> addCarousel(@RequestBody CarouselAddVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("新增轮播图接口开始执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			Carousel carousel = DozerUtils.transfer(vo, Carousel.class);
			carouselServiceImpl.addCarousel(carousel);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("增加轮播图接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("增加轮播图接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateCarousel(@RequestBody Carousel carousel){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑轮播图接口执行...{}",carousel);
			carouselServiceImpl.updateCarousel(carousel);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑轮播图接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑轮播图接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<Void> delCarousel(@RequestBody String id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("删除轮播图接口执行...{}",id);
			carouselServiceImpl.delCarousel(id);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("删除轮播图接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("删除轮播图接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
}
