package com.design.cms.dao.persist;

import com.design.cms.dao.entity.OrderLog;


public interface OrderLogMapper {

	void insertOrderLog(OrderLog log);

	int updateOrderLog(OrderLog orderLog);


}
