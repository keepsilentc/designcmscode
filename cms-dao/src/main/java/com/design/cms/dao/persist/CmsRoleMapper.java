package com.design.cms.dao.persist;

import java.util.List;

import com.design.cms.dao.entity.CmsRole;
import com.design.cms.dao.entity.dto.CmsRoleInfo;


public interface CmsRoleMapper {

	List<CmsRole> getAllRole();
	
	void insert(CmsRole cmsRole);

	void update(CmsRole cmsRole);

	List<CmsRoleInfo> getUserRoles(Long userId);

}
