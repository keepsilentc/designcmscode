package com.design.cms.dao.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8452579176319337724L;
	
	private Long id;
	/**
	 * 客户号
	 */
	private String userNo;
	/**
	 * 优惠券编号
	 */
	private String couponNo;
	/**
	 * 是否可用
	 * 1,可用
	 * 0,不可用
	 */
	private Integer isEnable;
	/**
	 * 用户领取优惠券状态
	 */
	private Integer userCouponState;
	/**
	 * 领取时间
	 */
	private Date receiveTime;
}
