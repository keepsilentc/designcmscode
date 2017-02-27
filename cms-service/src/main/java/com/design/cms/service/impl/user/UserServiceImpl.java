package com.design.cms.service.impl.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.User;
import com.design.cms.dao.entity.dto.UserInfo;
import com.design.cms.dao.persist.UserMapper;
import com.design.cms.service.api.IUserService;
import com.design.cms.service.api.vo.user.UserSearchVo;
import com.google.common.collect.Maps;

@Service
public class UserServiceImpl implements IUserService{
	
	@Resource
	private UserMapper userMapper;
	
	@Override
	public List<String> getTokensByCondition(Map<String, Object> param) {
		return userMapper.getTokensByCondition(param);
	}

	@Override
	public List<User> getCouponNotAllocateUsers(Map<String, Object> param) {
		return userMapper.getCouponNotAllocateUsers(param);
	}

	@Override
	public List<String> getAllUserDeviceTokens() {
		return userMapper.getAllUserDeviceTokens();
	}

	@Override
	public UserInfo getUserByUserNo(String userNo) {
		return userMapper.getUserByUserNo(userNo);
	}

	@Override
	public int getCount(UserSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getUserNo())){
			param.put("userNo", searchVo.getUserNo());
		}
		if(StringUtils.isNotEmpty(searchVo.getNickName())){
			param.put("nickName", searchVo.getNickName());
		}
		return userMapper.getCount(param);
	}

	@Override
	public List<UserInfo> getList(UserSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getUserNo())){
			param.put("userNo", searchVo.getUserNo());
		}
		if(StringUtils.isNotEmpty(searchVo.getNickName())){
			param.put("nickName", searchVo.getNickName());
		}
		param.put("begin", searchVo.getBegin());
		param.put("end", searchVo.getEnd());
		return userMapper.getList(param);
	}

}
