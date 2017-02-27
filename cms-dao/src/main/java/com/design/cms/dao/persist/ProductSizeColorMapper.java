package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.design.cms.dao.entity.ProductSizeColor;
import com.design.cms.dao.entity.dto.ProductSizeColorInfo;

public interface ProductSizeColorMapper {
	
	int getCount(Map<String, Object> params);

	List<ProductSizeColorInfo> getList(Map<String, Object> params);

	void insert(ProductSizeColor productSizeColor);

	void update(ProductSizeColor productSizeColor);

	int del(Long id);

	void batchInsert(List<ProductSizeColor> data);

	List<Map<String, Object>> getExportData(Map<String, Object> params);

	ProductSizeColorInfo getProductColorSizeById(Long id);
	
	int updateIsEnable(@Param(value = "productNo")String productNo,@Param(value = "isEnable")Integer isEnable);

	ProductSizeColorInfo getproductColorSizeByParam(Map<String, Object> params);
}
