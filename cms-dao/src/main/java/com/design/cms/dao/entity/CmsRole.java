package com.design.cms.dao.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsRole extends BaseEntity{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -7636412581388779793L;
	private String name;
	private String description;
	private Integer status;
}
