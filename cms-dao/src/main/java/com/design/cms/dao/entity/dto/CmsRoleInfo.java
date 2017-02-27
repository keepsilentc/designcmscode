package com.design.cms.dao.entity.dto;

import lombok.Getter;
import lombok.Setter;

import com.design.cms.dao.entity.CmsRole;
@Getter
@Setter
public class CmsRoleInfo extends CmsRole {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3766343455756491400L;
	
	private Boolean choose;
}
