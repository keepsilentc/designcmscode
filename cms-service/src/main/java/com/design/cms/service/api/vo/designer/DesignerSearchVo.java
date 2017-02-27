package com.design.cms.service.api.vo.designer;

import lombok.Getter;
import lombok.Setter;

import com.design.cms.service.api.PageVo;
@Getter
@Setter
public class DesignerSearchVo extends PageVo{
	private String countryId;
	private String brandId;
	private String designerName;
	private String isEnable;
}
