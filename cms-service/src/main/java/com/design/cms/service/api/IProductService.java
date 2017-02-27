package com.design.cms.service.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.design.cms.dao.entity.Product;
import com.design.cms.dao.entity.ProductDetail;
import com.design.cms.dao.entity.dto.ProductInfo;
import com.design.cms.service.api.vo.common.ImportError;
import com.design.cms.service.api.vo.product.ProductSearchVo;
import com.design.cms.service.api.vo.product.ProductSearchVo2;

public interface IProductService extends PageOperation<ProductSearchVo, ProductInfo>  {

	Long addProduct(Product product);

	void updateProduct(Product product);

	void delProduct(Long id);

	List<ProductDetail> getImages(String productNo);

	void updateProductDetail(ProductDetail req);

	void saveOrUpdateProductDetails(List<ProductDetail> req);

	List<ProductInfo> getProductList(ProductSearchVo2 vo);

	List<Map<String, Object>> getExportData(ProductSearchVo vo);

	List<ImportError> productimport(MultipartFile file);

	void removeProductDetails(List<Long> idList);

}
