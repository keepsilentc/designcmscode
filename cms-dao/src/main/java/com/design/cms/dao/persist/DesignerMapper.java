package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Designer;
import com.design.cms.dao.entity.dto.DesignerInfo;

public interface DesignerMapper {

	List<DesignerInfo> getDesignersByCondition(Map<String, String> param);

	void insert(Designer designer);

	int getCount(Map<String, Object> param);

	List<DesignerInfo> getList(Map<String, Object> param);

	int delDesignerById(Long id);

	void update(Designer designer);

	List<Map<String, Object>> getExportData(Map<String, Object> param);

}
