package com.design.cms.service.api.vo.designer;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DesignerAddVo {
	/**
	 * 设计师id
	 */
	@NotBlank
	private String countryId;
	/**
	 * 设计师名称
	 */
	@NotBlank
	private String designerName;
//	/**
//	 * 品牌id
//	 */
//	@NotBlank
//	private String brandId;
	/**
	 * 设计师描述
	 */
	private String described;
	/**
	 * 备注
	 */
	private String remark;
}
