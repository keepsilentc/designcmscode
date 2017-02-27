package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.User;
import com.design.cms.dao.entity.dto.UserInfo;


public interface UserMapper {

	List<String> getTokensByCondition(Map<String, Object> param);
	
	List<User> getCouponNotAllocateUsers(Map<String, Object> param);

	List<String> getAllUserDeviceTokens();

	UserInfo getUserByUserNo(String userNo);
	
	int getCount(Map<String, Object> param);

	List<UserInfo> getList(Map<String, Object> param);

	

} 
