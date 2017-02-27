package com.design.cms.service.api;

import com.design.cms.dao.entity.dto.OrderAllInfo;
import com.design.cms.dao.entity.dto.OrderInfo;
import com.design.cms.service.api.vo.order.DeliverVo;
import com.design.cms.service.api.vo.order.OrderSearchVo;

public interface IOrderService extends PageOperation<OrderSearchVo, OrderInfo>{
	
	void deliver(DeliverVo vo);

	OrderAllInfo getOrderAllInfo(String orderNo);

	

}
