package com.design.cms.dao.entity.dto;

import com.design.cms.dao.entity.Cart;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CartInfo extends Cart{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5035612009034164642L;
	private Long sizeId;
	private String sizeName;
	private Long colorId;
	private Long picture;
	private Integer inventory;
}
