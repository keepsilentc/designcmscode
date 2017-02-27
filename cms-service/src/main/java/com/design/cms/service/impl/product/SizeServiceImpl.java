package com.design.cms.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.Size;
import com.design.cms.dao.entity.SizeCountry;
import com.design.cms.dao.entity.dto.SizeInfo;
import com.design.cms.dao.persist.SizeCountryMapper;
import com.design.cms.dao.persist.SizeMapper;
import com.design.cms.service.api.ISizeService;
import com.design.cms.service.api.vo.product.SizeReq;
import com.google.common.collect.Maps;
@Service
public class SizeServiceImpl implements ISizeService{
	@Resource
	private SizeMapper sizeMapper;
	@Resource
	private SizeCountryMapper sizeCountryMapper;
	
	@Override
	public Long addSize(Size size) {
		sizeMapper.insert(size);
		return size.getId();
	}

	@Override
	public int updateSize(Size size) {
		return sizeMapper.update(size);
	}

	@Override
	public int delSize(Long id) {
		return sizeMapper.del(id);
	}


	@Override
	public List<SizeInfo> getAllSize(SizeReq vo) {
		Map<String,Object> params = Maps.newHashMap();
		if(null!=vo){
			if(StringUtils.isNotEmpty(vo.getSizeCountryId())){
				params.put("sizeCountryId", Long.valueOf(vo.getSizeCountryId()));
			}
			if(StringUtils.isNotEmpty(vo.getSizeTypeId())){
				params.put("sizeTypeId", Long.valueOf(vo.getSizeTypeId()));	
			}
			if(StringUtils.isNotEmpty(vo.getSizeName())){
				params.put("sizeName", vo.getSizeName());
			}
		}
		return sizeMapper.getAllSize(params);
	}

	@Override
	public List<Map<String, Object>> getExportData(SizeReq vo) {
		Map<String,Object> params = Maps.newHashMap();
		if(StringUtils.isNotEmpty(vo.getSizeCountryId())){
			params.put("sizeCountryId", Long.valueOf(vo.getSizeCountryId()));
		}
		if(StringUtils.isNotEmpty(vo.getSizeTypeId())){
			params.put("sizeTypeId", Long.valueOf(vo.getSizeTypeId()));	
		}
		if(StringUtils.isNotEmpty(vo.getSizeName())){
			params.put("sizeName", vo.getSizeName());
		}
		return sizeMapper.getExportSize(params);
	}

	@Override
	public List<SizeCountry> getAllSizeCountry() {
		return sizeCountryMapper.getSizeCountrys();
	}

	public SizeInfo getSizeById(Long sizeId) {
		SizeInfo t_size = sizeMapper.getSizeById(sizeId);
		if(t_size==null){
			throw new DesignException(DesignEx.CONCURRENCY_ERROR);
		}
		return t_size;
	}

}
