package com.design.cms.web.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.alibaba.fastjson.JSON;
import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.DozerUtils;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.common.utils.ValidateUtils;
import com.design.cms.dao.entity.Product;
import com.design.cms.dao.entity.ProductDetail;
import com.design.cms.dao.entity.dto.ProductInfo;
import com.design.cms.service.api.IProductService;
import com.design.cms.service.api.vo.common.ImportError;
import com.design.cms.service.api.vo.product.ProductAddReq;
import com.design.cms.service.api.vo.product.ProductSearchVo;
import com.design.cms.service.api.vo.product.ProductSearchVo2;
import com.design.cms.web.dto.Response;
import com.design.cms.web.utils.ImportAndExportUtil;
import com.design.cms.web.utils.PageAssist;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

@RequestMapping("/product")
@Controller
public class ProductController {
	private static Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Resource
	private IProductService productServiceImpl;
	
	@Resource
	private PageAssist pageAssist;
	
	@RequestMapping("/index")
	public String index(){
		return "product/productlist";
	}
	
	@RequestMapping("/import/index")
	public String productimport(){
		return "product/productimport";
	}
	
	@RequestMapping("/import")
	@ResponseBody
	public Response<List<ImportError>> productimport(@RequestParam(value="file")MultipartFile file){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<ImportError>> resp = new Response<List<ImportError>>();
		try{
			log.info("导入产品接口开始...");
			List<ImportError> errorList = productServiceImpl.productimport(file);
			resp.setResult(errorList);
		}catch(DesignException ex){
			log.info("导入产品接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("导入产品接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<Long> addProduct(@RequestBody ProductAddReq req){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Long> resp = new Response<Long>();
		try{
			log.info("新增产品接口开始...{}",req);
			ValidateUtils.validateEntiryThrows(req);
			Product product = DozerUtils.transfer(req, Product.class);
			Long id = productServiceImpl.addProduct(product);
			resp.setResult(id);
		}catch(DesignException ex){
			log.info("新增产品接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("新增产品接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Response<Void> updateProduct(@RequestBody Product product){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑产品接口执行...{}",product);
			productServiceImpl.updateProduct(product);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑产品接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑产品接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response<List<ProductInfo>> search(@RequestBody ProductSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<ProductInfo>> resp = new Response<List<ProductInfo>>();
		try{
			log.info("查询产品接口执行...{}",vo);
			ValidateUtils.validateEntiryThrows(vo);
			pageAssist.pageQuery(productServiceImpl,vo,resp);
		}catch(DesignException ex){
			log.info("查询产品接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询产品接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/getProductList")
	@ResponseBody
	public Response<List<ProductInfo>> getProductList(@RequestBody ProductSearchVo2 vo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<ProductInfo>> resp = new Response<List<ProductInfo>>();
		try{
			log.info("查询全部产品接口执行...",vo);
			ValidateUtils.validateEntiryThrows(vo);
			List<ProductInfo> productInfoList = productServiceImpl.getProductList(vo);
			resp.setResult(productInfoList);
		}catch(DesignException ex){
			log.info("查询全部产品接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("查询全部产品接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<Void> delProduct(@RequestBody String id){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("删除产品接口执行...{}",id);
			productServiceImpl.delProduct(Long.valueOf(id));
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("删除产品接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("删除产品接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}

	@RequestMapping("/getImages.do")
	@ResponseBody
	public Response<List<ProductDetail>> getImages(@RequestBody String productNo){
		TraceLogIdUtils.setTraceLogId(null);
		Response<List<ProductDetail>> resp = new Response<List<ProductDetail>>();
		try{
			log.info("获取产品图片接口执行,productNo:{}",productNo);
			List<ProductDetail> productImgsVo = productServiceImpl.getImages(productNo);
			resp.setResult(productImgsVo);
		}catch(DesignException ex){
			log.info("获取产品图片接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("获取产品图片接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
	@RequestMapping("/updateProductDetail")
	@ResponseBody
	public Response<Void> updateProductDetail(@RequestBody ProductDetail req){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑产品详细图片接口执行...{}",req);
			ValidateUtils.validateEntiryThrows(req);
			productServiceImpl.updateProductDetail(req);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑产品详细图片接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑产品详细图片接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/saveProductDetails")
	@ResponseBody
	public Response<Void> saveOrUpdateProductDetails(@RequestBody String req){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("编辑产品详细图片组接口执行,req:{}",req);
			List<ProductDetail> detaillist = JSON.parseArray(req, ProductDetail.class);
			for(ProductDetail tmp:detaillist){
				ValidateUtils.validateEntiryThrows(tmp);
			}
			productServiceImpl.saveOrUpdateProductDetails(detaillist);
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("编辑产品详细图片组接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("编辑产品详细图片组接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	@RequestMapping("/removeProductDetails")
	@ResponseBody
	public Response<Void> removeProductDetails(@RequestBody String ids){
		TraceLogIdUtils.setTraceLogId(null);
		Response<Void> resp = new Response<Void>();
		try{
			log.info("删除产品详细图片组接口执行,req:{}",ids);
			if(StringUtils.isNotEmpty(ids)){
				List<Long> idList = JSON.parseArray(ids, Long.class);
				productServiceImpl.removeProductDetails(idList);
			}
			resp.setResult(null);
		}catch(DesignException ex){
			log.info("删除产品详细图片组接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("删除产品详细图片组接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
	
	
	@RequestMapping("/downloadTemplate")
	@ResponseBody
	public void downloadTemplate(HttpServletResponse response){
		try{
			log.info("下载产品模板接口开始");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+"productTemplate.xlsx");
			IOUtils.copy(this.getClass().getResourceAsStream("/productTemplate.xlsx"), response.getOutputStream());
		}catch(DesignException ex){
			log.info("下载产品模板接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
		}catch(Exception e){
			log.info("下载产品模板接口执行异常,{}",Throwables.getStackTraceAsString(e));
		}
	}
	
	@RequestMapping("/export")
	@ResponseBody
	public void export(ProductSearchVo vo){
		TraceLogIdUtils.setTraceLogId(null);
		try{
			log.info("导出产品接口执行...{}",vo);
			LinkedHashMap<String,String> head = Maps.newLinkedHashMap();
			head.put("ProductNo","产品编号");
			head.put("ProductName","产品名称");
			head.put("CategoryName","产品类目");
			head.put("State","产品状态");
			head.put("ThemeName","产品系列");
			head.put("DesignerName","设计师");
			head.put("IsRepresentative","是否为代表作");
			head.put("Price","产品价格");
			head.put("ProductDescribed","产品描述");
			head.put("SizeDescribed","尺码描述");
			head.put("Remark","备注信息");
			List<Map<String,Object>> data = productServiceImpl.getExportData(vo);
			ImportAndExportUtil.export("product","product", head, data);
		}catch(DesignException ex){
			log.info("导出产品接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
		}catch(Exception e){
			log.info("导出产品接口执行异常,{}",Throwables.getStackTraceAsString(e));
		}
	}
}
