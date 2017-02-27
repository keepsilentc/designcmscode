package com.design.cms.service.api.vo.refund;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RefundApproveVo{
	@NotNull
	@Range(min=0,max=1)
	private Integer agree;
	
	private String failReason;
	
	@NotBlank
	private String refundNo;
}
