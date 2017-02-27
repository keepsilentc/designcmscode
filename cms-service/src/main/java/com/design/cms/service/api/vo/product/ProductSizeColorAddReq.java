package com.design.cms.service.api.vo.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductSizeColorAddReq {

	/**
	 * 产品编号
	 */
	@NotBlank
	private String productNo;
	/**
	 * 尺寸id
	 */
	@NotNull
	private Long sizeId;
	/**
	 * 颜色id
	 */
	@NotNull
	private Long colorId;
	/**
	 * 库存数量
	 */
	@NotNull
	@Min(value=0)
	private Integer inventory;

}
