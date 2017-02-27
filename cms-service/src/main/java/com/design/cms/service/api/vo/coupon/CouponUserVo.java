package com.design.cms.service.api.vo.coupon;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponUserVo {
	/**
	 * 
	 */
	private String couponNo;
	
	private List<String> userNoList;
}
