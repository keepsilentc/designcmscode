package com.design.cms.dao.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsRoleResources implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7020376705375659962L;
	
	private Long resourceId;
	private Long roleId;
	
}
