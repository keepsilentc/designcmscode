package com.design.cms.service.impl.product;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.common.utils.DozerUtils;
import com.design.cms.common.utils.PoiUtil;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.Color;
import com.design.cms.dao.entity.ProductSizeColor;
import com.design.cms.dao.entity.Size;
import com.design.cms.dao.entity.dto.ProductInfo;
import com.design.cms.dao.entity.dto.ProductSizeColorInfo;
import com.design.cms.dao.entity.dto.SizeInfo;
import com.design.cms.dao.persist.ProductSizeColorMapper;
import com.design.cms.service.api.IProductSizeColorService;
import com.design.cms.service.api.vo.common.ImportError;
import com.design.cms.service.api.vo.product.ProductSizeColorSearchVo;
import com.design.cms.service.api.vo.product.SizeReq;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Service
public class ProductSizeColorServiceImpl implements IProductSizeColorService{
	
	private static Logger log = LoggerFactory.getLogger(ProductSizeColorServiceImpl.class);
	
	@Resource
	private ProductSizeColorMapper productSizeColorMapper;
	@Resource
	private SizeServiceImpl sizeServiceImpl;
	@Resource
	private ColorServiceImpl colorServiceImpl;
	@Resource
	private ProductServiceImpl productServiceImpl;
	
	@Override
	public int getCount(ProductSizeColorSearchVo searchVo) {
		Map<String,Object> params = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getSizeId())){
			params.put("sizeId", searchVo.getSizeId());
		}
		if(StringUtils.isNotEmpty(searchVo.getColorId())){
			params.put("colorId", searchVo.getColorId());
		}
		params.put("productNo", searchVo.getProductNo());
		return productSizeColorMapper.getCount(params);
	}
	
	@Override
	public List<ProductSizeColorInfo> getList(ProductSizeColorSearchVo searchVo) {
		Map<String,Object> params = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getSizeId())){
			params.put("sizeId", searchVo.getSizeId());
		}
		if(StringUtils.isNotEmpty(searchVo.getColorId())){
			params.put("colorId", searchVo.getColorId());
		}
		params.put("productNo", searchVo.getProductNo());
		params.put("begin", searchVo.getBegin());
		params.put("end", searchVo.getEnd());
		return productSizeColorMapper.getList(params);
	}
	
	@Override
	public Long addProductSizeColor(ProductSizeColor productSizeColor) {
		productSizeColor.setCreateTime(DateUtil.getCurrentDate());
		Map<String,Object> params = Maps.newHashMap();
		params.put("productNo", productSizeColor.getProductNo());
		params.put("sizeId", productSizeColor.getSizeId());
		params.put("colorId", productSizeColor.getColorId());
		if(productSizeColorMapper.getproductColorSizeByParam(params)!=null){
			throw new DesignException("该颜色尺码的商品已存在");
		}
		synchronized (this) {
			try {
				productSizeColorMapper.insert(productSizeColor);
			}catch(DuplicateKeyException e){
				throw new DesignException("该颜色尺码的商品已存在");
			}
			
		}
		
		return productSizeColor.getId();
	}
	
	@Override
	public void updateProductSizeColor(ProductSizeColor productSizeColor) {
		productSizeColor.setUpdateTime(DateUtil.getCurrentDate());
		productSizeColorMapper.update(productSizeColor);
	}
	
	@Override
	public void delProductSizeColor(Long id) {
		productSizeColorMapper.del(id);
	}

	@Override
	public List<ImportError> productimport(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		Workbook wb = null;
		List<ImportError> errorList = Lists.newArrayList();
		try {
			if(fileName.endsWith(".xls")){
				wb = new HSSFWorkbook(file.getInputStream());
			}else if(fileName.endsWith(".xlsx")){
				wb = new XSSFWorkbook(file.getInputStream());
			}
			List<ProductSizeColor> data = Lists.newArrayList();
			List<ProductInfo> productList = productServiceImpl.getProductList(null);
			List<Color> colorList = colorServiceImpl.getAllColor(null);
			List<SizeInfo> sizeList = sizeServiceImpl.getAllSize(null);
			Set<String> productSet = Sets.newHashSet();
			Map<String,Color> colorMap = Maps.newHashMap();
			Map<String,SizeInfo> sizeMap = Maps.newHashMap();
			for(ProductInfo productInfo:productList){
				productSet.add(productInfo.getProductNo());
			}
			for(Color color:colorList){
				colorMap.put(color.getName(), color);
			}
			for(SizeInfo sizeInfo:sizeList){
				sizeMap.put(sizeInfo.getSizeTypeName()+"#"+sizeInfo.getSizeName(), sizeInfo);
			}
			Sheet sheet = wb.getSheetAt(0);
			int rowSize = sheet.getLastRowNum()+1;
			if(rowSize>500){
				
			}
			for(int j=0;j<rowSize;j++){
				Row row = sheet.getRow(j);
				if(j==0||j==1){
					
				}else{
					if(row.getCell(0)==null){
						continue;
					}
					try{
						generateProduct(row,errorList,data,productSet,colorMap,sizeMap);
					}catch(Exception e){
						log.info(Throwables.getStackTraceAsString(e));
						ImportError error = new ImportError();
						error.setLineNo(row.getRowNum());
						error.setDesc("数据错误");
						errorList.add(error);
						errorList.add(new ImportError());
					}
				}
			}
			if(errorList.size()==0&&data.size()>0){
				productSizeColorMapper.batchInsert(data);
			}
			
		}catch(DuplicateKeyException e){
			throw e;
		} catch (DesignException e) {
			throw e;
		}catch(Exception e){
			log.info(Throwables.getStackTraceAsString(e));
			throw new DesignException(DesignEx.INTERNAL_ERROR);
		}
		return errorList;
	}
	private void generateProduct(Row row, List<ImportError> errorList,List<ProductSizeColor> data,
			Set<String> productSet,Map<String, Color> colorMap, Map<String, SizeInfo> sizeMap) {
		
		String productNo = (String) PoiUtil.getCellData(row.getCell(0));
		String colorName = (String) PoiUtil.getCellData(row.getCell(1));
		String sizeType = (String) PoiUtil.getCellData(row.getCell(2));
		String sizeNameStr = (String) PoiUtil.getCellData(row.getCell(3));
		Integer inventory = Integer.valueOf(((String) PoiUtil.getCellData(row.getCell(4))));
		Color color = colorMap.get(colorName);
		StringBuilder builder = new StringBuilder();
		if(StringUtils.isEmpty(productNo)){
			builder.append("产品编号为空,");
		}
		if(!productSet.contains(productNo)){
			builder.append("产品编号在产品表中不存在,");
		}
		if(null==color){
			builder.append("颜色不存在,");
		}
		if(inventory<0){
			builder.append("库存小于0,");
		}
		if(StringUtils.isNotEmpty(sizeNameStr)){
			String[] sizeNames = sizeNameStr.split(",");
			for(String sizeName:sizeNames){
				Size size = sizeMap.get(sizeType+"#"+sizeName);
				
				if(null==size){
					builder.append("尺寸");
					builder.append(sizeName);
					builder.append("不存在,");
				}
				
				if(builder.length()>0){
					builder.deleteCharAt(builder.length()-1);
					ImportError error = new ImportError();
					error.setLineNo(row.getRowNum());
					error.setDesc(builder.toString());
					errorList.add(error);
					data.clear();
				}else{
					ProductSizeColor ptsc = new ProductSizeColor();
					ptsc.setProductNo(productNo);
					ptsc.setColorId(color.getId());
					ptsc.setSizeId(size.getId());
					ptsc.setInventory(inventory);
					ptsc.setCreateTime(DateUtil.getCurrentDate());
					data.add(ptsc);
				}
			}
		}
		
		
	}

	@Override
	public List<Map<String, Object>> getExportData(ProductSizeColorSearchVo vo) {
		Map<String,Object> params = Maps.newHashMap();
		if(StringUtils.isNotEmpty(vo.getSizeId())){
			params.put("sizeId", vo.getSizeId());
		}
		if(StringUtils.isNotEmpty(vo.getColorId())){
			params.put("colorId", vo.getColorId());
		}
		params.put("productNo", vo.getProductNo());
		return productSizeColorMapper.getExportData(params);
	}

	@Override
	public ProductSizeColorInfo quickAdd(ProductSizeColorInfo vo) {
		final SizeInfo t_size = sizeServiceImpl.getSizeById(vo.getSizeId());
		
		List<SizeInfo> t_sizeList = sizeServiceImpl.getAllSize(new SizeReq(){{
			setSizeCountryId(t_size.getSizeCountryId()==null?null:t_size.getSizeCountryId().toString());
			setSizeTypeId(t_size.getSizeTypeId()==null?null:t_size.getSizeTypeId().toString());
		}});
		
		SizeInfo next = null;
		
		for(int i =0,len=t_sizeList.size();i<len;i++){
			SizeInfo sizeInfo = t_sizeList.get(i);
			if(vo.getSizeId().equals(sizeInfo.getId())&&i<len-1){
				next = t_sizeList.get(i+1);
			}
		}
		
		if(next==null){
			throw new DesignException("无可添加的尺码");
		}
		vo.setId(null);
		vo.setSizeId(next.getId());
		vo.setSizeName(next.getSizeName());
		vo.setCreateTime(DateUtil.getCurrentDate());
		Map<String,Object> params = Maps.newHashMap();
		params.put("productNo", vo.getProductNo());
		params.put("sizeId", vo.getSizeId());
		params.put("colorId", vo.getColorId());
		if(productSizeColorMapper.getproductColorSizeByParam(params)!=null){
			throw new DesignException("该颜色尺码的商品已存在");
		}
		synchronized (this) {
			try {
				ProductSizeColor t_next = DozerUtils.transfer(vo, ProductSizeColor.class);
				productSizeColorMapper.insert(t_next);
				vo.setId(t_next.getId());
			}catch(DuplicateKeyException e){
				throw new DesignException("该颜色尺码的商品已存在");
			}
			
		}
		return vo;
	}

}
