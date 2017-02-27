package com.design.cms.common.utils;

import java.util.Random;
import java.util.UUID;

public class NoGenerator {
	/**
	 * 生成随机4位字母
	 * @return
	 */
	public static String random4Char(){
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<4;i++){
			int temp = random.nextInt(2)<<1==0?65:97;
			builder.append((char)(random.nextInt(26)+temp));
		}
		return builder.toString();
	}
	/**
	 * 生成客户号
	 * @return
	 */
	public static String generateUserNo(){
		StringBuilder builder = new StringBuilder();
		builder.append(random4Char());
		builder.append(DateUtil.getCurrent());
		builder.append(new Random(UUID.randomUUID().hashCode()).nextInt(899)+100);
		return builder.toString();
	}
	
	/**
	 * 生成订单号
	 * @return
	 */
	public static String generateOrderNo(){
		StringBuilder builder = new StringBuilder();
		builder.append(random4Char());
		builder.append(DateUtil.getCurrent());
		builder.append(new Random(UUID.randomUUID().hashCode()).nextInt(89999)+10000);
		return builder.toString();
	}
	
	/**
	 * 生成优惠券编号
	 * @return
	 */
	public static String generateCouponNo() {
		StringBuilder builder = new StringBuilder();
		builder.append(random4Char());
		builder.append(DateUtil.getCurrent());
		builder.append(new Random(UUID.randomUUID().hashCode()).nextInt(89999)+10000);
		return builder.toString();
	}
}
