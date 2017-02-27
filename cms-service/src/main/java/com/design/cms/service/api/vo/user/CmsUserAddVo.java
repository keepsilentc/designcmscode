package com.design.cms.service.api.vo.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CmsUserAddVo {
	@NotBlank
	private String userName;
	@NotNull
	private Integer age;
	@NotNull
	private Integer sex;
	@NotBlank
	private String email;

}
