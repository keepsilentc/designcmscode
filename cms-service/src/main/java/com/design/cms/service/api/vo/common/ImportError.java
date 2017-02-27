package com.design.cms.service.api.vo.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImportError {
	private int lineNo;
	private String desc;
	
}
