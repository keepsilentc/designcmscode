package com.design.cms.service.api;

import java.util.List;

public interface PageOperation<T extends PageVo,U>{
	int getCount(T searchVo);
	List<U> getList(T searchVo);
}
