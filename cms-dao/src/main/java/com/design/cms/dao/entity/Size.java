package com.design.cms.dao.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Size implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2272376792874673497L;
	private Long id;
	private Long sizeCountryId;
	private Long sizeTypeId;
	private String sizeName;
	private int orderBy;
	
}
