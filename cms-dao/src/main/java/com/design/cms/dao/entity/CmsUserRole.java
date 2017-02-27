package com.design.cms.dao.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsUserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2225470910337665469L;

	private Long userId;
	private Long roleId;
	
	
}
