package com.design.cms.service.api.vo.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SizeReq {
	private String sizeCountryId;
	private String sizeTypeId;
	private String sizeName;
}
