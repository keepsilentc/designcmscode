package com.design.cms.service.api;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.CmsRole;
import com.design.cms.dao.entity.CmsRoleResources;
import com.design.cms.dao.entity.dto.CmsResourcesInfo;

public interface IRoleService {

	List<CmsRole> getAllRole();

	void addRole(CmsRole cmsRole);

	void updateRole(CmsRole cmsRole);

	Map<String, List<CmsResourcesInfo>> getRoleResources(Long roleId);

	void saveRoleResources(List<CmsRoleResources> roleResources);

}
