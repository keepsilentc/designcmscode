package com.design.cms.dao.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.design.cms.dao.entity.Order;

@Getter
@Setter
@ToString
public class OrderInfo extends Order {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8210584315835007467L;
	
	private Integer canSend;
	
	private String currencySign;
	
	private String couponDesc;
}
