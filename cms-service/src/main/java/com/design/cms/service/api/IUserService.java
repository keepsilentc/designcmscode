package com.design.cms.service.api;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.User;
import com.design.cms.dao.entity.dto.UserInfo;
import com.design.cms.service.api.vo.user.UserSearchVo;

public interface IUserService extends PageOperation<UserSearchVo, UserInfo>{

	List<String> getTokensByCondition(Map<String, Object> param);
	
	List<User> getCouponNotAllocateUsers(Map<String, Object> param);

	List<String> getAllUserDeviceTokens();

	UserInfo getUserByUserNo(String userNo);
	
}
