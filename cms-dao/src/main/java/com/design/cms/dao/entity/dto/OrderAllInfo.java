package com.design.cms.dao.entity.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderAllInfo extends  OrderInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7699957381883634542L;
	private List<OrderDetailInfo> orderDetails;
}
