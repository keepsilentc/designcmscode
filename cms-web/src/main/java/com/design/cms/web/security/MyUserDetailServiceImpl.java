package com.design.cms.web.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.design.cms.dao.entity.CmsResources;
import com.design.cms.dao.entity.CmsUser;
import com.design.cms.dao.entity.dto.CmsRoleInfo;
import com.design.cms.dao.persist.CmsResourceMapper;
import com.design.cms.dao.persist.CmsRoleMapper;
import com.design.cms.dao.persist.CmsUserMapper;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {
	private static Logger log = LoggerFactory.getLogger(MyUserDetailServiceImpl.class);
	@Resource
	private CmsUserMapper cmsUserMapper;
	@Resource
	private CmsResourceMapper cmsResourceMapper;
	@Resource
	private CmsRoleMapper cmsRoleMapper;
	
	// 登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("执行userDetail***********loadUserByUsername");
		//取得用户的权限
		CmsUser t_cmsUser = cmsUserMapper.getUserByUserName(username);
		if  (t_cmsUser==null)  
            throw new UsernameNotFoundException(username+" not exist!");  
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(t_cmsUser);
		
		// 封装成spring security的user
		User userdetail = new User(
				t_cmsUser.getUserName(), 
				t_cmsUser.getUserPassword(),
				true, 
				true, 
				true,
				true, 
				grantedAuths	//用户的权限
			);
		return userdetail;
	}
	
	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(CmsUser cmsUser) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		boolean admin = false;
		List<CmsRoleInfo> cmsRoleInfoList = cmsRoleMapper.getUserRoles(cmsUser.getId());
		for(CmsRoleInfo tmp:cmsRoleInfoList){
			if(tmp.getId()==1L){
				admin = true;
				break;
			}
		}
		List<CmsResources> t_cmsResources = null;
		if(admin){
			t_cmsResources = cmsResourceMapper.getAllResources();
		}else{
			t_cmsResources = cmsResourceMapper.getUserResources(cmsUser.getId());
		}
		for (CmsResources res : t_cmsResources) {
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getResKey()));
		}
		return authSet;
	}
}