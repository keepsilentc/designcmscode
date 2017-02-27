package com.design.cms.dao.persist;

import java.util.List;

import com.design.cms.dao.entity.City;
import com.design.cms.dao.entity.Country;
import com.design.cms.dao.entity.Province;
import com.design.cms.dao.entity.Region;
import com.design.cms.dao.entity.dto.PcrInfo;

public interface AreaMapper {
	List<Country> queryAllCountrys();
	
	List<Province> queryAllProvince();
	
	List<City> queryAllCitysByProvinceId(Integer provinceId);
	
	List<Region> queryAllRegionsByCityId(Integer regionId);
	
	List<PcrInfo> queryAllPcr();
}
