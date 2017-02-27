package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Coupon;
import com.design.cms.dao.entity.dto.CouponInfo;

public interface CouponMapper {

	int getCount(Map<String, Object> param);

	List<Coupon> getList(Map<String, Object> param);

	void insert(Coupon coupon);

	int update(Coupon coupon);

	int del(Long id);

	CouponInfo getCouponInfo(Long id);

	CouponInfo getCouponInfoByNo(String couponNo);

}
