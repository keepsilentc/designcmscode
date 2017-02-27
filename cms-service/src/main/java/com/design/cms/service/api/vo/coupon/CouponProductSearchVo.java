package com.design.cms.service.api.vo.coupon;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class CouponProductSearchVo{
	private String designerId;
	private String themeId;
	private String cateGoryId;
	private String productNo;
	private String couponNo;
	private String isEnable;
}
