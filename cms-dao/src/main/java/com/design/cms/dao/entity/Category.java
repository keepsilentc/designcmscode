package com.design.cms.dao.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3814098217419373404L;
	private Long id;
	/**
	 * 上级类目id
	 */
	private Long parentId;
	/**
	 * 类目名称
	 */
	private String categoryName;
	/**
	 * 类目图片
	 */
	private Long picture;
	/**
	 * 层级
	 */
	private Integer rank;
	/**
	 * 排序
	 */
	private Integer orderBy;
	/**
	 * 是否启用
	 * 0-否
	 * 1-是
	 */
	private Integer isEnable;
	
}
