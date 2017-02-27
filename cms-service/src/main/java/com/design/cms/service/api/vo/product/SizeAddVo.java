package com.design.cms.service.api.vo.product;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SizeAddVo {
	@NotNull
	private Long sizeCountryId;
	@NotNull
	private Long sizeTypeId;
	@NotBlank
	private String sizeName;
	@NotNull
	private Integer orderBy;
}
