package com.design.cms.service.impl.cmsuser;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.design.cms.common.utils.CollectionUtils;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.CmsUser;
import com.design.cms.dao.entity.CmsUserRole;
import com.design.cms.dao.entity.dto.CmsRoleInfo;
import com.design.cms.dao.persist.CmsRoleMapper;
import com.design.cms.dao.persist.CmsUserMapper;
import com.design.cms.dao.persist.CmsUserRoleMapper;
import com.design.cms.service.api.ICmsUserService;
import com.design.cms.service.api.vo.user.CmsUserSearchVo;
import com.google.common.collect.Maps;
@Service
public class CmsUserServiceImpl implements ICmsUserService{
	@Resource
	private CmsUserMapper cmsUserMapper;
	@Resource
	private CmsRoleMapper cmsRoleMapper;
	@Resource
	private CmsUserRoleMapper cmsUserRoleMapper;
	@Override
	public String login(String userName, String passwd) {
		CmsUser t_cms_user = cmsUserMapper.getUserByUserName(userName);
		String error = "success";
		if(t_cms_user==null){
			error = "无效的用户名";
		}else if(!t_cms_user.getUserPassword().equals(passwd)){
			error = "密码错误";
		}
		return error;
		
	}
	
	@Override
	public List<CmsUser> getAllUser(CmsUserSearchVo vo) {
		Map<String,Object> params = Maps.newHashMap();
		if(StringUtils.isNotEmpty(vo.getRoleId())){
			params.put("roleId",Long.valueOf(vo.getRoleId()));
		}
		if(StringUtils.isNotEmpty(vo.getCmsUserName())){
			params.put("userName", vo.getCmsUserName());
		}
		return cmsUserMapper.getAllUser(params);
	}

	@Override
	public void addUser(CmsUser cmsUser) {
		cmsUser.setUserPassword("123456");
		cmsUser.setStatus(1);
		cmsUser.setRegTime(DateUtil.getCurrentDate());
		cmsUser.setCreateTime(DateUtil.getCurrentDate());
		cmsUserMapper.insert(cmsUser);
	}

	@Override
	public void updateUser(CmsUser cmsUser) {
		cmsUser.setUpdateTime(DateUtil.getCurrentDate());
		cmsUserMapper.update(cmsUser);
	}

	@Override
	public Integer delUser(Long id) {
		return cmsUserMapper.del(id);
	}

	@Override
	public List<CmsRoleInfo> getUserRoles(Long userId) {
		return cmsRoleMapper.getUserRoles(userId);
	}

	@Override
	@Transactional
	public void saveUserRoles(List<CmsUserRole> userRoles) {
		if(CollectionUtils.isNotEmpty(userRoles)){
			cmsUserRoleMapper.delteByUserId(userRoles.get(0).getUserId());
			cmsUserRoleMapper.batchinsert(userRoles);
		}
		
	}

}
