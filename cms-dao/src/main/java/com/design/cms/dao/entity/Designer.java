package com.design.cms.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Designer extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3577034616425476548L;
	/**
	 * 设计师id
	 */
	private String countryId;
	/**
	 * 设计师名称
	 */
	private String designerName;
	/**
	 * 品牌id
	 */
	private String brandId;
	/**
	 * 照片
	 */
	private Long photo;
	/**
	 * 设计师描述
	 */
	private String described;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否启用
	 */
	private Integer isEnable;
}
