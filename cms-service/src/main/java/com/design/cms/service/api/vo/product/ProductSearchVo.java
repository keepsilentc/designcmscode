package com.design.cms.service.api.vo.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.design.cms.service.api.PageVo;
@Getter
@Setter
@ToString
public class ProductSearchVo extends PageVo {
	
	/**
	 * 设计师id
	 */
	private String designerId;
	/**
	 * 类别ID
	 */
	private String themeId;
	private String brandId;
	private String countryId;
	/**
	 * 设计师名称
	 */
	private String designerName;
	/**
	 * 产品编号
	 */
	private String productNo;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 是否启用0-否1-是
	 */
	private String IsEnable;
	/**
	 * 产品状态10-现货20-预售
	 */
	private String state;
	private String startTime;
	private String endTime;
	
	private String order;
	
}
