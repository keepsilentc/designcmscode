package com.design.cms.dao.entity.dto;

import lombok.Getter;
import lombok.Setter;

import com.design.cms.dao.entity.ProductSizeColor;
@Getter
@Setter
public class ProductSizeColorInfo extends ProductSizeColor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6591756092568423067L;
	private String sizeName;
	private String colorName;
	private Long colorPicture;
}
