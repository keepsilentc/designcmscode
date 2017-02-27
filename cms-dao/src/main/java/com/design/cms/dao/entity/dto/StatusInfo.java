package com.design.cms.dao.entity.dto;

import lombok.Getter;
import lombok.Setter;

import com.design.cms.dao.entity.Status;
@Getter
@Setter
public class StatusInfo extends Status {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8909032100239292039L;
	
	private String themeName;
	
	private String statusTypeName;
	
	private String designerName;
	
}
