package com.design.cms.dao.entity;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReturnLog extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5005876960552507668L;
	/**
	 * 日志跟踪号
	 */
	private String traceLogId;
	/**
	 * 用户号
	 */
	private String userNo;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 退换货no
	 */
	private String returnNo;
	/**
	 * 订单详细id
	 */
	private Long orderDetailId;
	/**
	 * 退换货个数
	 */
	private Integer productNum;
	/**
	 * 退换货类型
	 * 1、换货
	 * 2、退货
	 */
	private Integer returnType;
	/**
	 * 退款状态
	 */
	private Integer returnState;
	/**
	 * 失败原因
	 */
	private String failReason;
}
