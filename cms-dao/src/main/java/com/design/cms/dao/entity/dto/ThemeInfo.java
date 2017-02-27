package com.design.cms.dao.entity.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.design.cms.dao.entity.Theme;
@Getter
@Setter
public class ThemeInfo extends Theme {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4823452199336330329L;
	private String designerName;
	/**
	 * 预售开始时间
	 */
	private Date preSellStartTime;
	/**
	 * 预售结束时间
	 */
	private Date preSellEndTime;
}
