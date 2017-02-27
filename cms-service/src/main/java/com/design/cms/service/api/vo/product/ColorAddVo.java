package com.design.cms.service.api.vo.product;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ColorAddVo {
	@NotBlank
	private String name;
	private MultipartFile picture;
}
