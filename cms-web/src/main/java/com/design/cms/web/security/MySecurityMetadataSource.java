package com.design.cms.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.design.cms.dao.entity.CmsResources;
import com.design.cms.dao.persist.CmsResourceMapper;

/**
 * 加载资源与权限的对应关系
 * */
@Service
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static Logger log = LoggerFactory.getLogger(MySecurityMetadataSource.class);
	@Resource
	private CmsResourceMapper cmsResourceMapper;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	@PostConstruct
	private void loadResourceDefine() {
//		log.info("执行MySecurityMetadataSource***********loadResourceDefine");
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<CmsResources> resources = this.cmsResourceMapper.getAllResources();
			for (CmsResources resource : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + resource.getResKey());
				configAttributes.add(configAttribute);
				resourceMap.put(resource.getResUrl(), configAttributes);
			}
		}
	}
	
	//返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//		log.info("执行MySecurityMetadataSource***********getAttributes");
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if(resourceMap == null) {
			loadResourceDefine();
		}
		if(requestUrl.indexOf("?")>-1){
			requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
		}
		log.info("请求url,{}",requestUrl);
		Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
		if(configAttributes == null){
			Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
			log.info("无权限*******,{}",requestUrl);
			returnCollection.add(new SecurityConfig("ROLE_NO_USER")); 
			return returnCollection;
		}
		return configAttributes;
	}

}