package com.design.cms.common.enums;

public enum DesignEx {
	//系统相关60开头
	INTERNAL_ERROR("6000","内部错误"),
	CONCURRENCY_ERROR("6001","并发异常"),
	UNKOWN_TYPE("6002","未知的类型"),
	//用户相关61开头
	TOKEN_ERROR("6100","token失效"),
	USER_STATUS_ERROR("6101","用户状态异常"),
	ALREADY_REGISTERED("6102","该邮箱或手机已被注册"),
	UNREGISTERED("6103","登陆失败,该用户未注册"),
	USERPASSWORDERROR("6104","登陆失败,密码错误"),
	EMAILEMPTY("6105","登陆失败,email为空"),
	REGISTERCODE_ERROR("6106","注册失败,验证码错误"),
	//设计师相关70开头
	DESIGNERNOTFIND("7000","设计师不存在"),
	DESIGNEUNABLE("7001","设计师未启用"),
	//文章相关71开头
	STATUSNOTFIND("7100","文章不存在"),
	STATUSEUABLE("7101","文章未启用"),
	//系列相关72
	THEMENOTFIND("7200","系列不存在"),
	//商品相关63开头
	PRODUCTNOTFIND("6300","商品不存在"),
	PRODUCTUNABLE("6301","商品未启用"),
	//商品库相关64开头
	WAREHOUSENOTHISPRODUCT("6400","库存中未发现此商品"),
	WAREHOUSE_PRODUCT_UNABLE("6401","库存中此商品未启用"),
	SHORTINVENTORY("6402","库存不足"),
	SHORTSALE("6403","销量不足"),
	//购物车相关65开头
	PRODUCTNOTSAME("6500","商品号不一致"), 
	//订单相关68开通
	ORDER_NOCARTIDS("6800","订单购物车id不能为空"),
	CART_IN_ORDER_NOT_IN_CART("6801","订单中的购物车id在购物车列表中不存在"),
	UNKNOWN_ORDER_TYPE("6802","未知订单类型"),
	ORDER_NOT_EXISTS("6803","订单不存在"),
	ORDER_STEATE_REFUSED("6804","订单状态不支持此操作"),
	ORDER_PREMONEY_NOTSAME("6805","订单折前总金额不相等"),
	ORDER_MONEY_NOTSAME("6806","订单总金额不相等"),
	ORDER_CANOTCLOSE("6809","订单不能关闭"),
	ORDER_NOTDELIVERED("6810","订单未发货"),
	//支付相关70开头
	ALIPAY_SIGN_FAIL("7000","支付宝签名失败"),
	ALIPAY_ERROR("7001","调用支付宝异常"),
	//退款相关75开头
	REFUND_NOT_APPROVE("7500","退款状态异常,需要为审批通过"),
	ERROR_REFUND_NUM("7501","退款个数错误"),
	CANNOT_REFUND("7502","不能申请退款"),
	REFUND_NOT_EXISTS("7503","退款不存在"),
	REFUND_DONE("7504","退款已完成,不能重复退款"),
	//退换货相关76开头
	ERROR_RETURN_NUM("7600","退换货个数错误"),
	CANNOT_RETURN("7601","存在未退换货记录,不能退换货"),
	RETURN_NOT_EXISTS("7602","退货不存在"),
	//发货相关77相关
	CANOT_DELIVER("7700","不能发货"),
	
	//优惠券相关80开头
	COUPON_NOTEXIST("8000","优惠券不存在"),
	COUPON_NOTENABLE("8001","优惠券未启用"),
	COUPON_NOTSTART("8002","优惠券时间未开始"),
	COUPON_STOPPED("8003","优惠券已过期"),
	//文件相关90开头
	ATTACHMENET_NOTFUND("9000","未发现附件"),
	FILE_NOTEXISTS("9001","文件不存在")
	;
	private String errCode;
	private String errMsg;
	private DesignEx(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	public String getErrCode() {
		return errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
}
