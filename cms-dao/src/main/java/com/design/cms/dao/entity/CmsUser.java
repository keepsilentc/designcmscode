package com.design.cms.dao.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CmsUser extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5642238762503364530L;

	private String userName;

	private String roleName;

	private String userPassword;

	private String nickName;

	private String realName;

	private Integer age;

	private Integer sex;

	private String address;

	private String mobileNo;

	private String email;

	private String qq;

	private Date regTime;

	private Date lastLogintime;
	
	private Integer status;
	
}
