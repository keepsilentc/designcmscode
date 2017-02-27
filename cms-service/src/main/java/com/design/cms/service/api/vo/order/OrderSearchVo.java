package com.design.cms.service.api.vo.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.design.cms.service.api.PageVo;

@Getter
@Setter
@ToString
public class OrderSearchVo extends PageVo {
	private String userNo;
	private String orderNo;
	private String orderType;
	private String orderState;
	private String startTime;
	private String endTime;
}
