package com.design.cms.web.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.dao.entity.Brand;
import com.design.cms.dao.entity.Country;
import com.design.cms.dao.entity.SizeCountry;
import com.design.cms.dao.entity.SizeType;
import com.design.cms.dao.persist.BrandMapper;
import com.design.cms.dao.persist.CountryMapper;
import com.design.cms.dao.persist.SizeCountryMapper;
import com.design.cms.dao.persist.SizeTypeMapper;

@Service
public class CacheAssist {
	private Map<String,Object> cache = new ConcurrentHashMap<String,Object>() ;
	
	@Resource
	private CountryMapper countryMapper;
	@Resource
	private BrandMapper brandMapper;
	@Resource
	private SizeCountryMapper sizeCountryMapper;
	@Resource
	private SizeTypeMapper sizeTypeMapper;
	
	@SuppressWarnings("unchecked")
	public List<Country> getCountryList(){
		List<Country> cache_countryList =  (List<Country>) cache.get("countryList");
		if(cache_countryList==null){
			cache_countryList = countryMapper.getCountrys();
			cache.put("countryList", cache_countryList);
		}
		return cache_countryList;
	}

	@SuppressWarnings("unchecked")
	public List<Brand> getBrandList() {
		List<Brand> cache_brandList =  (List<Brand>) cache.get("brandList");
		if(cache_brandList==null){
			cache_brandList = brandMapper.getBrands();
			cache.put("brandList",cache_brandList);
		}
		return cache_brandList;
	}

	@SuppressWarnings("unchecked")
	public List<SizeType> getSizeTypeList() {
		List<SizeType> cache_sizeTypeList =  (List<SizeType>) cache.get("sizeTypeList");
		if(cache_sizeTypeList==null){
			cache_sizeTypeList = sizeTypeMapper.getSizeTypes();
			cache.put("sizeTypeList",cache_sizeTypeList);
		}
		return cache_sizeTypeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<SizeCountry> getSizeCountryList() {
		List<SizeCountry> cache_sizeCountryList =  (List<SizeCountry>) cache.get("sizeCountryList");
		if(cache_sizeCountryList==null){
			cache_sizeCountryList = sizeCountryMapper.getSizeCountrys();
			cache.put("sizeCountryList",cache_sizeCountryList);
		}
		return cache_sizeCountryList;
	}
	
	
	
	
}
