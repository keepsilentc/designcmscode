package com.design.cms.dao.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.design.cms.dao.entity.OrderDetail;

@Getter
@Setter
@ToString
public class OrderDetailInfo extends OrderDetail{/**
	 * 
	 */
	private static final long serialVersionUID = 2119648235238120529L;
	private String colorName;
	private String sizeName;
	private String picture;
}
