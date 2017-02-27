package com.design.cms.dao.persist;

import java.util.List;

import com.design.cms.dao.entity.CmsRoleResources;


public interface CmsRoleResourceMapper {
	
	void batchinsert(List<CmsRoleResources> cmsRoleResources);
	
	void delteByRoleId(Long roleId);

}
