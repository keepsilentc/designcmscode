package com.design.cms.service.api.vo.refund;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DoRefundVo {
	
	@NotNull(message="退款请求号不能为空")
	private String refundNo;
}
