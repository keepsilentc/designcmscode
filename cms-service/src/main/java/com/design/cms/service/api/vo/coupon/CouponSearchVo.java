package com.design.cms.service.api.vo.coupon;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.design.cms.service.api.PageVo;
@Getter
@Setter
@ToString
public class CouponSearchVo extends PageVo {
	private String couponName;
	private String couponType;
	private String couponStrategy;
	private String startTime;
	private String endTime;
	private String IsEnable;
}
