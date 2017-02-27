package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Product;
import com.design.cms.dao.entity.dto.ProductInfo;


public interface ProductMapper {

	Product getProductByProductNo(String productNo);

	int getCount(Map<String, Object> param);

	List<ProductInfo> getList(Map<String, Object> param);

	void insert(Product product);

	void update(Product product);

	List<ProductInfo> getProductList(Map<String, Object> param);

	List<Map<String, Object>> getExportData(Map<String, Object> param);

	void batchInsert(List<Product> data);

	int del(Long id);
	
}
