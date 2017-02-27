package com.design.cms.service.api;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Size;
import com.design.cms.dao.entity.SizeCountry;
import com.design.cms.dao.entity.dto.SizeInfo;
import com.design.cms.service.api.vo.product.SizeReq;

public interface ISizeService {
	
	Long addSize(Size size);
	
	int updateSize(Size size);
	
	int delSize(Long id);

	List<SizeInfo> getAllSize(SizeReq vo);

	List<Map<String, Object>> getExportData(SizeReq vo);

	List<SizeCountry> getAllSizeCountry();
}
