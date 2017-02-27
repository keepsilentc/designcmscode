package com.design.cms.service.impl.product;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.dao.persist.BrandMapper;
import com.design.cms.service.api.IBrandService;
@Service
public class BrandServiceImpl implements IBrandService {
	@Resource
	private BrandMapper brandMapper;

}
