package com.design.cms.service.api;

import java.util.List;

import com.design.cms.dao.entity.User;
import com.design.cms.service.api.vo.coupon.CouponUserVo;

public interface ICouponUserService {
	
	List<User> getCouponNotAllocateUsers(String couponNo);

	void addCouponUsers(CouponUserVo vo);
}
