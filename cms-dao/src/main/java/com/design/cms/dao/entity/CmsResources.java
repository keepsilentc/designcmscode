package com.design.cms.dao.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsResources extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1372425588603562477L;
	private String name;
	private Long parentId; //父类Id
	private String parentName;
	private String resKey;//这个权限KEY是唯一的，新增时要注意，
	private String resUrl;//URL地址．例如：/videoType/query　　不需要项目名和http://xxx:8080
	private Integer level;
	private String type;//权限类型，0．表示目录　1，表示菜单．2，表示按扭．．在spring security3安全权限中，涉及精确到按扭控制
	private String description;
}
