package com.design.cms.dao.entity;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SubScribe extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5212111581906719103L;
	private String userNo;
	private String email;
	/**
	 * 是否订阅
	 * 0-否
	 * 1-是
	 */
	private String isEnable;
}
