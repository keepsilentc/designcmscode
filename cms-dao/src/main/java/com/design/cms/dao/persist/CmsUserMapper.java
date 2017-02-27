package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.design.cms.dao.entity.CmsUser;

public interface CmsUserMapper {

	CmsUser getUserByUserName(@Param(value = "userName")String username);

	List<CmsUser> getAllUser(Map<String, Object> params);

	void insert(CmsUser cmsUser);

	void update(CmsUser cmsUser);

	Integer del(Long id);

	
}
