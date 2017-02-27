package com.design.cms.service.api.vo.product;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.design.cms.service.api.PageVo;
@Getter
@Setter
@ToString
public class ProductSizeColorSearchVo extends PageVo {
	private String colorId;
	private String sizeId;
	@NotBlank
	private String productNo;
}
