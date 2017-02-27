package com.design.cms.dao.entity.dto;

import lombok.Getter;
import lombok.Setter;

import com.design.cms.dao.entity.Product;
@Getter
@Setter
public class ProductInfo extends Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6611800137120360714L;
	
	private String themeName;
	
	private String countryName;
	
	private String categoryName;
	
	private String countryId;
	
	private String brandName;
	
	private String designerName;
}
