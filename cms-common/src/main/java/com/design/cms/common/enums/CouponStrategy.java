package com.design.cms.common.enums;

public enum CouponStrategy {
	DISCOUNT(1,"打折"),
	FULL_REDUCTION(2,"满减");
	
	private Integer strategyCode;
	private String strategyDes;
	
	private CouponStrategy(Integer strategyCode, String strategyDes) {
		this.strategyCode = strategyCode;
		this.strategyDes = strategyDes;
	}

	public Integer getStrategyCode() {
		return strategyCode;
	}

	public String getStrategyDes() {
		return strategyDes;
	}
	
}
