package com.design.cms.service.api;

import java.util.List;

import com.design.cms.dao.entity.CmsUser;
import com.design.cms.dao.entity.CmsUserRole;
import com.design.cms.dao.entity.dto.CmsRoleInfo;
import com.design.cms.service.api.vo.user.CmsUserSearchVo;

public interface ICmsUserService {
	
	List<CmsUser> getAllUser(CmsUserSearchVo vo);

	void addUser(CmsUser cmsUser);

	void updateUser(CmsUser cmsUser);

	Integer delUser(Long id);
	
	String login(String userName, String passwd);

	List<CmsRoleInfo> getUserRoles(Long userId);

	void saveUserRoles(List<CmsUserRole> userRoles);

}
