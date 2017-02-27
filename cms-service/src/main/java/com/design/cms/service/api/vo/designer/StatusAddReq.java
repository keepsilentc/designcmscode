package com.design.cms.service.api.vo.designer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StatusAddReq {
	/**
	 * 动态类型id
	 */
	private Long statusTypeId;
	/**
	 * 系列id
	 */
	private String themeId;
	/**
	 * 动态详情
	 */
	private String statusDetail;
	/**
	 * 动态名称
	 */
	private String statusName;
	/**
	 * 动态描述
	 */
	private String describe;
}
