package com.design.cms.dao.persist;

import java.util.List;

import com.design.cms.dao.entity.CmsResources;
import com.design.cms.dao.entity.dto.CmsResourcesInfo;


public interface CmsResourceMapper {

	List<CmsResources> getAllResources();

	List<CmsResources> getUserResources(Long id);

	List<CmsResourcesInfo> getRoleResources(Long roleId);
	
}
