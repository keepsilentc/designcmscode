package com.design.cms.service.api.vo.designer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.design.cms.service.api.PageVo;
@Getter
@Setter
@ToString
public class StatusSearchVo extends PageVo {
	
	private String statusTypeId;
	private String statusName;
	private String isEnable;
	private String designerId;
	private String themeId;
	
}
