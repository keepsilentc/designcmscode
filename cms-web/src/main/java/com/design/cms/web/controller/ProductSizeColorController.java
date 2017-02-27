package com.design.cms.web.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.DozerUtils;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.common.utils.ValidateUtils;
import com.design.cms.dao.entity.ProductSizeColor;
import com.design.cms.dao.entity.dto.ProductSizeColorInfo;
import com.design.cms.service.api.IProductSizeColorService;
import com.design.cms.service.api.vo.common.ImportError;
import com.design.cms.service.api.vo.product.ProductSizeColorAddReq;
import com.design.cms.service.api.vo.product.ProductSizeColorSearchVo;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.ImportAndExportUtil;
import com.design.cms.web.utils.PageAssist;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

@RequestMapping("/productSizeColor")
@Controller
public class ProductSizeColorController {
	private static Logger log = LoggerFactory.getLogger(ProductSizeColorController.class);
	
	@Resource
	private IProductSizeColorService productSizeColorServiceImpl;
	
	@Resource
	private PageAssist pageAssist;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest req,@RequestParam String productNo){
		if (StringUtils.isEmpty(productNo)) {
			throw new DesignException(DesignEx.INTERNAL_ERROR);
		}else{
			req.setAttribute("productNo", productNo);
		}
		return "product/productsizecolorlist";
	}
	
	@RequestMapping("/import/index")
	public String productimport(){
		return "product/productsizecolorimport";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Long> addProductSizeColor(@RequestBody ProductSizeColorAddReq req){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Long> resp = new Response<Long>();
		try{
			log.info("新增产品颜色尺码接口开始...{}",req);
			ValidateUtils.validateEntiryThrows(req);
			ProductSizeColor productSizeColor = DozerUtils.transfer(req, ProductSizeColor.class);
			Long id = productSizeColorServiceImpl.addProductSizeColor(productSizeColor);
			resp.setResult(id);
		}catch(DesignException ex){
			log.info("新增产品颜色尺码接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("新增产品颜色尺码接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/import")
	@ResponseBody
	public Response<List<ImportError>> productimport(@RequestParam(value="file")MultipartFile file){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<ImportError>> resp = new Response<List<ImportError>>();
		try{
			log.info("导入产品尺寸颜色接口开始...");
			List<ImportError> errorList = productSizeColorServiceImpl.productimport(file);
			resp.setResult(errorList);
		}catch(DesignException ex){
			log.info("导入产品尺寸颜色接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("导入尺寸颜色产品接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateProductSizeColor(@RequestBody ProductSizeColor productSizeColor){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑产品颜色尺码接口执行...{}",productSizeColor);
			productSizeColorServiceImpl.updateProductSizeColor(productSizeColor);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑产品颜色尺码接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑产品颜色尺码接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/quickAdd")
	@ResponseBody
	public Response<ProductSizeColorInfo> quickAdd(@RequestBody ProductSizeColorInfo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<ProductSizeColorInfo> resp = new Response<ProductSizeColorInfo>();
		try{
			log.info("快速添加产品颜色尺码接口执行...{}",vo);
			ProductSizeColorInfo result = productSizeColorServiceImpl.quickAdd(vo);
			resp.setResult(result);
		}catch(DesignException ex){
			log.info("快速添加产品颜色尺码接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("快速添加产品颜色尺码接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<ProductSizeColorInfo>> search(@RequestBody ProductSizeColorSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<ProductSizeColorInfo>> resp = new Response<List<ProductSizeColorInfo>>();
		try{
			log.info("查询产品颜色尺码接口执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			pageAssist.pageQuery(productSizeColorServiceImpl,vo,resp);
		}catch(DesignException ex){
			log.info("查询产品颜色尺码接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询产品颜色尺码接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<Void> delProductSizeColor(@RequestBody String id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("删除产品颜色尺码接口执行...{}",id);
			productSizeColorServiceImpl.delProductSizeColor(Long.valueOf(id));
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("删除产品颜色尺码接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("删除产品颜色尺码接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/export")
	@ResponseBody
	public void export(ProductSizeColorSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		try{
			log.info("导出产品颜色尺码接口执行...{}",vo);
			LinkedHashMap<String,String> head = Maps.newLinkedHashMap();
			head.put("ProductNo","产品编号");
			head.put("SizeName","尺码名称");
			head.put("colorName","颜色名称");
			head.put("Inventory","库存");
			head.put("Sale","销量");
			head.put("IsEnable","是否启用");
			List<Map<String,Object>> data = productSizeColorServiceImpl.getExportData(vo);
			ImportAndExportUtil.export("productSizeColor","产品尺寸颜色", head, data);
		}catch(DesignException ex){
			log.info("导出产品颜色尺码接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
		}catch(Exception e){
			log.info("导出产品颜色尺码接口执行异常,{}",Throwables.getStackTraceAsString(e));
		}
	}
	
	@RequestMapping("/downloadTemplate")
	@ResponseBody
	public void downloadTemplate(HttpServletResponse response){
		try{
			log.info("下载产品数量模板接口开始");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+"productSizeColorTemplate.xlsx");
			IOUtils.copy(this.getClass().getResourceAsStream("/productSizeColorTemplate.xlsx"), response.getOutputStream());
		}catch(DesignException ex){
			log.info("下载产品数量模板接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
		}catch(Exception e){
			log.info("下载产品数量模板接口执行异常,{}",Throwables.getStackTraceAsString(e));
		}
	}
	
}
