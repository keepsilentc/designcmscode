package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.design.cms.dao.entity.Order;
import com.design.cms.dao.entity.dto.OrderInfo;


public interface OrderMapper {


	int getCount(Map<String, Object> param);

	List<OrderInfo> getList(Map<String, Object> param);

	int deliverUpdate(Order order);

	OrderInfo getOrderInfoByOrderNo(@Param(value = "orderNo")String orderNo);

}
