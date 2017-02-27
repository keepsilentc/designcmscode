package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Refund;


public interface RefundMapper {

	int getCount(Map<String, Object> param);

	List<Refund> getList(Map<String, Object> param);

	Refund getRefundByRefundNo(String refundNo);

	List<Refund> getRefundList(Map<String, Object> param);
	
	int update(Refund refund);

}
