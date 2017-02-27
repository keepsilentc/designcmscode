package com.design.cms.dao.entity.dto;

import com.design.cms.dao.entity.Designer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DesignerInfo extends Designer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -551648532441906597L;
	private String countryName;
	private String brandName;
}
