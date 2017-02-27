package com.design.cms.dao.persist;

import java.util.List;

import com.design.cms.dao.entity.CmsUserRole;


public interface CmsUserRoleMapper {
	
	void batchinsert(List<CmsUserRole> cmsUserRoles);
	
	void delteByUserId(Long userId);

}
