package com.design.cms.service.api.vo.refund;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.design.cms.service.api.PageVo;

@Getter
@Setter
@ToString
public class RefundSearchVo extends PageVo{
	
	private String refundNo;
	private String orderNo;
	private String userNo;
	private String refundType;
	private String refundState;
	private String refundPayTypeId;
	private String startTime;
	private String endTime;
}
