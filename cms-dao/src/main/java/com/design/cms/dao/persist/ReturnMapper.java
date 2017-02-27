package com.design.cms.dao.persist;
import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Returns;
import com.design.cms.dao.entity.dto.ReturnInfo;


public interface ReturnMapper {
	
	void insert(Returns returns);
	
	void update(Returns returns);

	List<Returns> getReturnsList(Map<String, Object> param);

	Returns getReturnById(Long returnId);

	int getCount(Map<String, Object> params);

	List<ReturnInfo> getList(Map<String, Object> params);
	
}
