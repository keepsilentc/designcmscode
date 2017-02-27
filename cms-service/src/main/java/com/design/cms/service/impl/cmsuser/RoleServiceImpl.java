package com.design.cms.service.impl.cmsuser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.design.cms.common.utils.CollectionUtils;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.dao.entity.CmsRole;
import com.design.cms.dao.entity.CmsRoleResources;
import com.design.cms.dao.entity.dto.CmsResourcesInfo;
import com.design.cms.dao.persist.CmsResourceMapper;
import com.design.cms.dao.persist.CmsRoleMapper;
import com.design.cms.dao.persist.CmsRoleResourceMapper;
import com.design.cms.service.api.IRoleService;
import com.google.common.collect.Maps;

@Service
public class RoleServiceImpl implements IRoleService{
	
	@Resource
	private CmsRoleMapper cmsRoleMapper;
	@Resource
	private CmsResourceMapper cmsResourceMapper;
	@Resource
	private CmsRoleResourceMapper cmsRoleResourceMapper;
	
	@Override
	public List<CmsRole> getAllRole() {
		return cmsRoleMapper.getAllRole();
	}

	@Override
	public void addRole(CmsRole cmsRole) {
		cmsRole.setCreateTime(DateUtil.getCurrentDate());
		cmsRoleMapper.insert(cmsRole);
		
	}

	@Override
	public void updateRole(CmsRole cmsRole) {
		cmsRole.setUpdateTime(DateUtil.getCurrentDate());
		cmsRoleMapper.update(cmsRole);
	}

	@Override
	public Map<String, List<CmsResourcesInfo>> getRoleResources(Long roleId) {
		List<CmsResourcesInfo> t_resources = cmsResourceMapper.getRoleResources(roleId);
		Map<String, List<CmsResourcesInfo>> result = Maps.newHashMap();
		List<CmsResourcesInfo> currentList = null;
		String currentType = null;
		for(CmsResourcesInfo tmp:t_resources){
			if(tmp.getType().equals(currentType)){
				currentList.add(tmp);
			}else{
				currentType = tmp.getType();
				currentList = new ArrayList<CmsResourcesInfo>();
				currentList.add(tmp);
				result.put(currentType, currentList);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public void saveRoleResources(List<CmsRoleResources> roleResources) {
		if(CollectionUtils.isNotEmpty(roleResources)){
			cmsRoleResourceMapper.delteByRoleId(roleResources.get(0).getRoleId());
			cmsRoleResourceMapper.batchinsert(roleResources);
		}
	}
}
