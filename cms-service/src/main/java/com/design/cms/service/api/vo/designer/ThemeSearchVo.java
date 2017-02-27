package com.design.cms.service.api.vo.designer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.design.cms.service.api.PageVo;
@Getter
@Setter
@ToString
public class ThemeSearchVo extends PageVo {
	private String designerId;
	private String themeName;
	private String isEnable;
}
