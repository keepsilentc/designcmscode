package com.design.cms.service.api.vo.coupon;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;

@Getter
@Setter
@ToString
public class CouponAddVo {
	
	@NotBlank
	private String couponName;
	
	/**
	 * 优惠券分类
	 * 0,无限制
	 * 1,单用户使用一次
	 */
	@NotNull
	private Integer couponType;
	
	/**
	 * 优惠券策略,1-打折,2-满减
	 */
	@NotNull
	private Integer couponStrategy;
	
	@NotNull
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@NotNull
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	private Integer sumNum;
	
	@NotNull(groups=Discount.class)
	@DecimalMin(value="0.00")
	@DecimalMax(value="10.00")
	private BigDecimal couponRate;
	
	@NotNull(groups=FullMinus.class)
	private BigDecimal fullMoney;
	
	@NotNull(groups=FullMinus.class)
	private BigDecimal minusMoney;
	
	private String remark;
}
