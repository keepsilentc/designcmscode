package com.design.cms.service.impl.product;

import java.util.List;
import java.util.Map;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.enums.ProductState;
import com.design.cms.common.utils.ChicunMoney;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.common.utils.PoiUtil;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.Category;
import com.design.cms.dao.entity.Product;
import com.design.cms.dao.entity.ProductDetail;
import com.design.cms.dao.entity.Theme;
import com.design.cms.dao.entity.dto.DesignerInfo;
import com.design.cms.dao.entity.dto.ProductInfo;
import com.design.cms.dao.entity.dto.ProductSizeColorInfo;
import com.design.cms.dao.persist.ProductDetailMapper;
import com.design.cms.dao.persist.ProductMapper;
import com.design.cms.dao.persist.ProductSizeColorMapper;
import com.design.cms.service.api.IProductService;
import com.design.cms.service.api.vo.common.ImportError;
import com.design.cms.service.api.vo.product.ProductSearchVo;
import com.design.cms.service.api.vo.product.ProductSearchVo2;
import com.design.cms.service.impl.cmsuser.UploadServiceImpl;
import com.design.cms.service.impl.designer.DesignerServiceImpl;
import com.design.cms.service.impl.designer.ThemeServiceImpl;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class ProductServiceImpl implements IProductService{
	
	private static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Resource
	private ProductMapper productMapper;
	@Resource
	private ProductSizeColorMapper productSizeColorMapper;
	@Resource
	private ProductDetailMapper productDetailMapper;
	@Resource
	private UploadServiceImpl uploadServiceImpl;
	@Resource
	private CategoryServiceImpl categoryServiceImpl;
	@Resource
	private DesignerServiceImpl designerServiceImpl;
	@Resource
	private ThemeServiceImpl themeServiceImpl;
	
	@Override
	public int getCount(ProductSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getCountryId())){
			param.put("countryId",searchVo.getCountryId());
		}
		if(StringUtils.isNotEmpty(searchVo.getBrandId())){
			param.put("brandId",searchVo.getBrandId());
		}
		if(StringUtils.isNotEmpty(searchVo.getDesignerId())){
			param.put("designerId",Long.valueOf(searchVo.getDesignerId()));
		}
		if(StringUtils.isNotEmpty(searchVo.getThemeId())){
			param.put("themeId",Long.valueOf(searchVo.getThemeId()));
		}
		if(StringUtils.isNotEmpty(searchVo.getDesignerName())){
			param.put("designerName",searchVo.getDesignerName());
		}
		if(StringUtils.isNotEmpty(searchVo.getProductName())){
			param.put("productName", searchVo.getProductName());
		}
		if(StringUtils.isNotEmpty(searchVo.getProductNo())){
			param.put("productNo", searchVo.getProductNo());
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			param.put("isEnable", Integer.valueOf(searchVo.getIsEnable()));
		}
		if(StringUtils.isNotEmpty(searchVo.getState())){
			param.put("state",Integer.valueOf(searchVo.getState()));
		}
		if(StringUtils.isNotEmpty(searchVo.getStartTime())){
			param.put("startTime", DateUtil.parse(searchVo.getStartTime()+" 00:00:00", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(searchVo.getEndTime())){
			param.put("endTime", DateUtil.parse(searchVo.getEndTime()+" 11:59:59", DateUtil.allPattern));
		}
		return productMapper.getCount(param);
	}

	@Override
	public List<ProductInfo> getList(ProductSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getCountryId())){
			param.put("countryId",searchVo.getCountryId());
		}
		if(StringUtils.isNotEmpty(searchVo.getBrandId())){
			param.put("brandId",searchVo.getBrandId());
		}
		if(StringUtils.isNotEmpty(searchVo.getDesignerId())){
			param.put("designerId",Long.valueOf(searchVo.getDesignerId()));
		}
		if(StringUtils.isNotEmpty(searchVo.getThemeId())){
			param.put("themeId",Long.valueOf(searchVo.getThemeId()));
		}
		if(StringUtils.isNotEmpty(searchVo.getDesignerName())){
			param.put("designerName",searchVo.getDesignerName());
		}
		if(StringUtils.isNotEmpty(searchVo.getProductName())){
			param.put("productName", searchVo.getProductName());
		}
		if(StringUtils.isNotEmpty(searchVo.getProductNo())){
			param.put("productNo", searchVo.getProductNo());
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			param.put("isEnable", Integer.valueOf(searchVo.getIsEnable()));
		}
		if(StringUtils.isNotEmpty(searchVo.getState())){
			param.put("state",Integer.valueOf(searchVo.getState()));
		}
		if(StringUtils.isNotEmpty(searchVo.getStartTime())){
			param.put("startTime", DateUtil.parse(searchVo.getStartTime()+" 00:00:00", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(searchVo.getEndTime())){
			param.put("endTime", DateUtil.parse(searchVo.getEndTime()+" 11:59:59", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(searchVo.getOrder())){
			param.put("orderBy", Integer.valueOf(searchVo.getOrder()));
		}
		param.put("begin", searchVo.getBegin());
		param.put("end", searchVo.getEnd());
		
		return productMapper.getList(param);
	}

	@Override
	public Long addProduct(Product product) {
		if(product.getOriginPrice()==null){
			product.setOriginPrice(product.getPrice());
		}
		product.setBrandId("BRAND0000");
		product.setCreateTime(DateUtil.getCurrentDate());
		try{
			productMapper.insert(product);
		}catch(DuplicateKeyException e){
			throw new DesignException("productNo 已存在");
		}
		return product.getId();
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void updateProduct(Product product) {
		uploadServiceImpl.moveTempFileToFormal(product.getPicture());
		product.setUpdateTime(DateUtil.getCurrentDate());
		productSizeColorMapper.updateIsEnable(product.getProductNo(), product.getIsEnable());
		productMapper.update(product);
	}
	
	

	@Override
	public void delProduct(Long id) {
		productMapper.del(id);
	}

	@Override
	public List<ProductDetail> getImages(String productNo) {
		return productDetailMapper.getProductDetailByProductNo(productNo);
	}


	@Override
	public void updateProductDetail(ProductDetail productDetail) {
		productDetailMapper.update(productDetail);
	}

	@Override
	@Transactional
	public void saveOrUpdateProductDetails(List<ProductDetail> productDetails) {
		for(ProductDetail tmp:productDetails){
			uploadServiceImpl.moveTempFileToFormal(tmp.getPicture());
			if(tmp.getId()==null){
				tmp.setCreateTime(DateUtil.getCurrentDate());
				productDetailMapper.insert(tmp);
			}else{
				tmp.setUpdateTime(DateUtil.getCurrentDate());
				productDetailMapper.update(tmp);
			}
		}
	}

	@Override
	public List<ProductInfo> getProductList(ProductSearchVo2 searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(null!=searchVo){
			if(StringUtils.isNotEmpty(searchVo.getCountryId())){
				param.put("countryId",searchVo.getCountryId());
			}
			if(StringUtils.isNotEmpty(searchVo.getBrandId())){
				param.put("brandId",searchVo.getBrandId());
			}
			if(StringUtils.isNotEmpty(searchVo.getDesignerId())){
				param.put("designerId",Long.valueOf(searchVo.getDesignerId()));
			}
			if(StringUtils.isNotEmpty(searchVo.getThemeId())){
				param.put("themeId",Long.valueOf(searchVo.getThemeId()));
			}
			if(StringUtils.isNotEmpty(searchVo.getProductNo())){
				param.put("productNo", searchVo.getProductNo());
			}
		}
		return productMapper.getProductList(param);
	}

	@Override
	public List<Map<String, Object>> getExportData(ProductSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getCountryId())){
			param.put("countryId",searchVo.getCountryId());
		}
		if(StringUtils.isNotEmpty(searchVo.getBrandId())){
			param.put("brandId",searchVo.getBrandId());
		}
		if(StringUtils.isNotEmpty(searchVo.getDesignerId())){
			param.put("designerId",Long.valueOf(searchVo.getDesignerId()));
		}
		if(StringUtils.isNotEmpty(searchVo.getThemeId())){
			param.put("themeId",Long.valueOf(searchVo.getThemeId()));
		}
		if(StringUtils.isNotEmpty(searchVo.getDesignerName())){
			param.put("designerName",searchVo.getDesignerName());
		}
		if(StringUtils.isNotEmpty(searchVo.getProductName())){
			param.put("productName", searchVo.getProductName());
		}
		if(StringUtils.isNotEmpty(searchVo.getProductNo())){
			param.put("productNo", searchVo.getProductNo());
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			param.put("isEnable", Integer.valueOf(searchVo.getIsEnable()));
		}
		if(StringUtils.isNotEmpty(searchVo.getState())){
			param.put("state",Integer.valueOf(searchVo.getState()));
		}
		if(StringUtils.isNotEmpty(searchVo.getStartTime())){
			param.put("startTime", DateUtil.parse(searchVo.getStartTime()+" 00:00:00", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(searchVo.getEndTime())){
			param.put("endTime", DateUtil.parse(searchVo.getEndTime()+" 11:59:59", DateUtil.allPattern));
		}
		return productMapper.getExportData(param);
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
			List<Product> data = Lists.newArrayList();
			List<Category> cateGoryList = categoryServiceImpl.getAllCategory();
			List<DesignerInfo> designerList = designerServiceImpl.getAllDesigner(null);
			List<Theme> themeList = themeServiceImpl.getAllTheme(null);
			Map<String,Category> cateGoryMap = Maps.newHashMap();
			for(Category cateGory:cateGoryList){
				cateGoryMap.put(cateGory.getCategoryName(), cateGory);
			}
			Map<String,DesignerInfo> designerMap = Maps.newHashMap();
			for(DesignerInfo designerInfo:designerList){
				designerMap.put(designerInfo.getDesignerName(),designerInfo);
			}
			Map<String,Theme> themeMap = Maps.newHashMap();
			for(Theme theme:themeList){
				themeMap.put(theme.getThemeName(),theme);
			}
			Sheet sheet = wb.getSheetAt(0);
			int rowSize = sheet.getLastRowNum()+1;
			if(rowSize>500){
				
			}
			for(int j=0;j<rowSize;j++){
				Row row = sheet.getRow(j);
//				int cellSize = row.getLastCellNum();
				if(j==0||j==1){
					
				}else{
					if(row.getCell(0)==null){
						continue;
					}
					try{
						generateProduct(row,errorList,data,cateGoryMap,designerMap,themeMap);
					}catch(Exception e){
						ImportError error = new ImportError();
						error.setLineNo(row.getRowNum());
						error.setDesc("数据错误");
						errorList.add(error);
						errorList.add(new ImportError());
					}
					
					
				}
			}
			if(errorList.size()==0&&data.size()>0){
				productMapper.batchInsert(data);
			}
			
		}catch(DuplicateKeyException e){
			throw new DesignException(Throwables.getStackTraceAsString(e));
		} catch (DesignException e) {
			throw e;
		}catch(Exception e){
			log.info(Throwables.getStackTraceAsString(e));
			throw new DesignException(DesignEx.INTERNAL_ERROR);
		}
		return errorList;
	}
	private void generateProduct(Row row,List<ImportError> errorList,List<Product> data,
			Map<String,Category> cateGoryMap,Map<String,DesignerInfo> designerMap,Map<String,Theme> themeMap) {
		Product product = new Product();
		String productNo = (String) PoiUtil.getCellData(row.getCell(0));
		String productName = (String) PoiUtil.getCellData(row.getCell(1));
		String cateGoryName = (String) PoiUtil.getCellData(row.getCell(2));
		Integer state = Integer.valueOf(((String) PoiUtil.getCellData(row.getCell(3))));
		String themeName = (String) PoiUtil.getCellData(row.getCell(4));
		String designerName = (String) PoiUtil.getCellData(row.getCell(5));
		Integer isRepresentative = Integer.valueOf(((String) PoiUtil.getCellData(row.getCell(6))));
		String orginPrice = (String) PoiUtil.getCellData(row.getCell(7));
		String price = (String) PoiUtil.getCellData(row.getCell(8));
		String productDescribe = (String) PoiUtil.getCellData(row.getCell(9));
		String productDetail = (String) PoiUtil.getCellData(row.getCell(10));
		String sizeDescribe = (String) PoiUtil.getCellData(row.getCell(11));
		String remark = (String) PoiUtil.getCellData(row.getCell(12));
		Category category = cateGoryMap.get(cateGoryName);
		DesignerInfo designerInfo = designerMap.get(designerName);
		Theme theme = themeMap.get(themeName);
		StringBuilder builder = new StringBuilder();
		if(StringUtils.isEmpty(productNo)){
			builder.append("产品编号为空,");
		}
		if(StringUtils.isEmpty(productName)){
			builder.append("产品名称,");
		}
		if(category==null){
			builder.append("产品类目错误,");
		}
		if(designerInfo==null){
			builder.append("设计师错误,");
		}
		if(theme==null){
			builder.append("系列错误,");
		}else if(!theme.getDesignerId().equals(designerInfo.getId())){
			builder.append("设计师与系列不对应,");
		}
		if(!ProductState.Valid(state)){
			builder.append("产品状态错误,");
		}
		
		if(builder.length()>0){
			builder.deleteCharAt(builder.length()-1);
			ImportError error = new ImportError();
			error.setLineNo(row.getRowNum());
			error.setDesc(builder.toString());
			errorList.add(error);
			data.clear();
		}else if(data.size()==row.getRowNum()-2){
			product.setProductNo(productNo);
			product.setProductName(productName);
			product.setCateGoryId(category.getId());
			product.setDesignerId(designerInfo.getId());
			product.setBrandId(designerInfo.getBrandId());
			product.setThemeId(theme.getId());
			product.setState(state);
			product.setIsRepresentative(isRepresentative);
			product.setIsNew(0);;
			product.setOriginPrice(new ChicunMoney(orginPrice).getMoney());
			product.setPrice(new ChicunMoney(price).getMoney());
			product.setProductDescribe(productDescribe);
			product.setProductDetail(productDetail);
			product.setSizeDescribe(sizeDescribe);
			product.setRemark(remark);
			product.setCreateTime(DateUtil.getCurrentDate());
			data.add(product);
		}
	}


	public ProductSizeColorInfo getProductColorSizeById(Long id) {
		ProductSizeColorInfo t_productSizeColor = productSizeColorMapper.getProductColorSizeById(id);
		if(t_productSizeColor==null){
			log.info("商品库中无此具体属性的商品...");
			throw new DesignException(DesignEx.WAREHOUSENOTHISPRODUCT);
		}
		return t_productSizeColor;
	}

	@Override
	public void removeProductDetails(List<Long> idList) {
		productDetailMapper.batchDel(idList);
		
	}

}
