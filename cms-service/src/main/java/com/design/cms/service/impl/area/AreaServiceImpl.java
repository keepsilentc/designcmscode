package com.design.cms.service.impl.area;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.dao.persist.AreaMapper;
import com.design.cms.service.api.IAreaService;
@Service
public class AreaServiceImpl implements IAreaService{
//	private static Logger log = LoggerFactory.getLogger(AreaServiceImpl.class);
	@Resource
	private AreaMapper areaMapper;
	
	
}
