package com.design.cms.service.api;

import com.design.cms.dao.entity.Coupon;
import com.design.cms.dao.entity.dto.CouponInfo;
import com.design.cms.service.api.vo.coupon.CouponSearchVo;

public interface ICouponService extends PageOperation<CouponSearchVo, Coupon> {

	Long addCoupon(Coupon coupon);

	void updateCoupon(Coupon coupon);

	void delCoupon(Long valueOf);

	CouponInfo getCouponInfo(Long valueOf);

}
