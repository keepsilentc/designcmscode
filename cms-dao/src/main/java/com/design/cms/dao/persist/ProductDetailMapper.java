package com.design.cms.dao.persist;

import java.util.List;

import com.design.cms.dao.entity.ProductDetail;


public interface ProductDetailMapper {
	
	List<ProductDetail> getProductDetailByProductNo(String productNo);

	void update(ProductDetail productDetail);

	void insert(ProductDetail tmp);

	void batchDel(List<Long> idList);
	
}
