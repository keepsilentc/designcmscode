package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Size;
import com.design.cms.dao.entity.dto.SizeInfo;


public interface SizeMapper {

	List<SizeInfo> getAllSize(Map<String,Object> params);

	void insert(Size size);

	int del(Long id);

	int update(Size size);

	List<Map<String, Object>> getExportSize(Map<String, Object> params);

	SizeInfo getSizeById(Long sizeId);

}
