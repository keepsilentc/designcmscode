package com.design.cms.dao.entity.dto;

import lombok.Getter;
import lombok.Setter;

import com.design.cms.dao.entity.Size;
@Getter
@Setter
public class SizeInfo extends Size {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2104213743189816546L;
	private String sizeCountryName;
	private String sizeTypeName;
	
}
