package com.design.cms.service.impl.cache;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.common.utils.RedisClient;
import com.design.cms.dao.persist.AreaMapper;

@Service
public class Cache {
	@Resource
	private AreaMapper areaMapper;
	@Resource
	private RedisClient redisClient;
	
	
}
