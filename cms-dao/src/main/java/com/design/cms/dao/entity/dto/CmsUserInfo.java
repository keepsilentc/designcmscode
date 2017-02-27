package com.design.cms.dao.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.design.cms.dao.entity.CmsUser;
@Getter
@Setter
@ToString
public class CmsUserInfo extends CmsUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 861611084095083552L;
	private String roleNames;
}
