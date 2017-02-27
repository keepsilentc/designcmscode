package com.design.cms.common.assist;

public class Constant {
	
	/**
	 * 启用
	 */
	public static int ENABLE =1;
	/**
	 * 不启用
	 */
	public static int UNENABLE =0;
	/**
	 * 用户默认等级
	 */
	public static String LEVEL_ID_INIT = "LEVEL0000";
	
	public static String PLATFORM_TYPE_INIT="DESIGN";
	/**
	 * redis 用户邮箱
	 */
	public static String EMAIL="email";
	/**
	 * redis 第三方平台id
	 */
	public static String PLATEFORMID="plateformid";
	
	public static String REGISTERCODE = "registerCode";
	
	public static int REGISTEREXPIRE = 4*60;
	/**
	 * redis缓存时间
	 */
	public static final int KEY_EXPIRETIME = 60*60;
	public static final int MALE = 1;
	public static final int FEMALE = 2;
	public static final Object DEFAULT = "default";
	public static final int PAGESIZE = 20;
	public static final String SUCCESS = "200";
	public static final int AUTOCLOSETIME = 1;
	public static final Integer PULL = 1;
	public static final Integer PUSH = 0;
	public static final int TRYTIMES = 3;
	
}
