package com.design.cms.service.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.design.cms.dao.entity.ProductSizeColor;
import com.design.cms.dao.entity.dto.ProductSizeColorInfo;
import com.design.cms.service.api.vo.common.ImportError;
import com.design.cms.service.api.vo.product.ProductSizeColorSearchVo;

public interface IProductSizeColorService extends PageOperation<ProductSizeColorSearchVo, ProductSizeColorInfo>  {

	Long addProductSizeColor(ProductSizeColor productSizeColor);

	void updateProductSizeColor(ProductSizeColor productSizeColor);

	void delProductSizeColor(Long id);

	List<ImportError> productimport(MultipartFile file);

	List<Map<String, Object>> getExportData(ProductSizeColorSearchVo vo);

	ProductSizeColorInfo quickAdd(ProductSizeColorInfo vo);


}
