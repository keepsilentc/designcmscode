package com.design.cms.service.impl.coupon;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.common.enums.CouponType;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.common.utils.NoGenerator;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.Coupon;
import com.design.cms.dao.entity.dto.CouponInfo;
import com.design.cms.dao.persist.CouponMapper;
import com.design.cms.service.api.ICouponService;
import com.design.cms.service.api.vo.coupon.CouponSearchVo;
import com.google.common.collect.Maps;
@Service
public class CouponServiceImpl implements ICouponService{
	
	@Resource
	private CouponMapper couponMapper;
	
	@Override
	public int getCount(CouponSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getCouponName())){
			param.put("couponName", searchVo.getCouponName());
		}
		if(StringUtils.isNotEmpty(searchVo.getCouponType())){
			param.put("couponType", Integer.valueOf(searchVo.getCouponType()));
		}
		if(StringUtils.isNotEmpty(searchVo.getCouponStrategy())){
			param.put("couponStrategy", Integer.valueOf(searchVo.getCouponStrategy()));
		}
		if(StringUtils.isNotEmpty(searchVo.getStartTime())){
			param.put("startTime", DateUtil.parse(searchVo.getStartTime()+" 00:00:00", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(searchVo.getEndTime())){
			param.put("endTime", DateUtil.parse(searchVo.getEndTime()+" 11:59:59", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			param.put("IsEnable", Integer.valueOf(searchVo.getIsEnable()));
		}
		return couponMapper.getCount(param);
	}

	@Override
	public List<Coupon> getList(CouponSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getCouponName())){
			param.put("couponName", searchVo.getCouponName());
		}
		if(StringUtils.isNotEmpty(searchVo.getCouponType())){
			param.put("couponType", Integer.valueOf(searchVo.getCouponType()));
		}
		if(StringUtils.isNotEmpty(searchVo.getCouponStrategy())){
			param.put("couponStrategy", Integer.valueOf(searchVo.getCouponStrategy()));
		}
		if(StringUtils.isNotEmpty(searchVo.getStartTime())){
			param.put("startTime", DateUtil.parse(searchVo.getStartTime()+" 00:00:00", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(searchVo.getEndTime())){
			param.put("endTime", DateUtil.parse(searchVo.getEndTime()+" 11:59:59", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			param.put("IsEnable", Integer.valueOf(searchVo.getIsEnable()));
		}
		param.put("begin", searchVo.getBegin());
		param.put("end", searchVo.getEnd());
		return couponMapper.getList(param);
	}

	@Override
	public Long addCoupon(Coupon coupon) {
		if(CouponType.SINGLEUSER.getTypeCode().equals(coupon.getCouponType())){
			coupon.setSumNum(-1);
		}
		coupon.setCouponNo(NoGenerator.generateCouponNo());
		coupon.setCreateTime(DateUtil.getCurrentDate());
		couponMapper.insert(coupon);
		return coupon.getId();
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		coupon.setUpdateTime(DateUtil.getCurrentDate());
		couponMapper.update(coupon);
	}

	@Override
	public void delCoupon(Long id) {
		couponMapper.del(id);
	}

	@Override
	public CouponInfo getCouponInfo(Long id) {
		
		return couponMapper.getCouponInfo(id);
	}

	public CouponInfo getCouponInfoByNo(String couponNo) {
		return couponMapper.getCouponInfoByNo(couponNo);
	}

}
