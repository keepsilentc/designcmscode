package com.design.cms.service.api.vo.user;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CmsRoleAddVo {
	@NotBlank
	private String name;
	private String description;
	@NotNull
	private Integer status;
}
