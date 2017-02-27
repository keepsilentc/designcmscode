package com.design.cms.dao.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Color implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6622665491099478187L;
	private Long id;
	private String name;
	private Long picture;
	
}
