package com.design.cms.dao.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.design.cms.dao.entity.Coupon;

@Getter
@Setter
@ToString
public class CouponInfo extends Coupon{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6000741334329360237L;
	
	private Long remainNum;
	
}
