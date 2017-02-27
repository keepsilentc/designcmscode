package com.design.cms.common.enums;

public enum RefundState {
	REFUND_APPROVEING(-1,"退款审核中"),
	REFUND_APPROVE_FAIL(0,"退款审核失败"),
	REFUND_APPROVE_PASS(1,"退款审核通过"),
	
	NOTREFUND(10,"未退款"),
	REFUND_SUCCESS(20,"退款成功"),
	REFUND_FAIL(40,"退款失败");
	private Integer stateCode;
	private String stateDesc;
	private RefundState(Integer stateCode, String stateDesc) {
		this.stateCode = stateCode;
		this.stateDesc = stateDesc;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public String getStateDesc() {
		return stateDesc;
	}
}
