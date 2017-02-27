package com.design.cms.service.api.vo.product;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryAddVo {
	/**
	 * 上级类目id
	 */
	private Long parentId;
	/**
	 * 类目名称
	 */
	@NotBlank
	private String categoryName;
	/**
	 * 类目图片
	 */
	private Long picture;
	/**
	 * 排序
	 */
	@NotNull
	private Integer orderBy;
	/**
	 * 是否启用
	 * 0-否
	 * 1-是
	 */
	@NotNull
	private Integer isEnable;
}
