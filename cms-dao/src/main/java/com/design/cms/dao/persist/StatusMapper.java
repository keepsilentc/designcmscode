package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Status;
import com.design.cms.dao.entity.dto.StatusInfo;


public interface StatusMapper {

	int getCount(Map<String, Object> params);

	List<StatusInfo> getList(Map<String, Object> params);

	void insert(Status status);

	int update(Status status);

}
