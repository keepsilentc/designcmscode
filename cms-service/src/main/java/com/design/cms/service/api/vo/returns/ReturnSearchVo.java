package com.design.cms.service.api.vo.returns;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.design.cms.service.api.PageVo;

@Getter
@Setter
@ToString
public class ReturnSearchVo extends PageVo{
	private String returnNo;
	private String orderNo;
	private String returnType;
	private String returnState;
	private String startTime;
	private String endTime;
}
