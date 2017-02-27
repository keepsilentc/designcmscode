package com.design.cms.service.api.vo.designer;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@ToString
public class ThemeAddVo {
	@NotNull
	private Long designerId;
	@NotBlank
	private String themeName;
	private String described;
}
