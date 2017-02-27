package com.design.cms.service.impl.coupon;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.dao.entity.User;
import com.design.cms.dao.persist.CouponUserMapper;
import com.design.cms.service.api.ICouponUserService;
import com.design.cms.service.api.IUserService;
import com.design.cms.service.api.vo.coupon.CouponUserVo;
import com.google.common.collect.Maps;

@Service
public class CouponUserServiceImpl implements ICouponUserService {
	
	@Resource
	private IUserService userServiceImpl;
	
	@Resource
	private CouponUserMapper couponUserMapper;

	@Override
	public List<User> getCouponNotAllocateUsers(String couponNo) {
		Map<String,Object> params = Maps.newHashMap();
		params.put("couponNo", couponNo);
		return userServiceImpl.getCouponNotAllocateUsers(params);
	}

	@Override
	public void addCouponUsers(CouponUserVo vo) {
		Map<String,Object> params = Maps.newHashMap();
		params.put("couponNo", vo.getCouponNo());
		params.put("userNoList", vo.getUserNoList());
		couponUserMapper.batchInsert(params);
	}
	
}
