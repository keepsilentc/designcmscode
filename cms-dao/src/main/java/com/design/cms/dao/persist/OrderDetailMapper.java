package com.design.cms.dao.persist;

import java.util.List;

import com.design.cms.dao.entity.OrderDetail;


public interface OrderDetailMapper {

	void insertOrderDetail(OrderDetail detail);

	List<OrderDetail> getOrderDetailList(String orderNO);

	int updateOrderDetailAtnotify(OrderDetail orderDetail);

	OrderDetail getOrderDetailById(Long orderDetailId);

}
