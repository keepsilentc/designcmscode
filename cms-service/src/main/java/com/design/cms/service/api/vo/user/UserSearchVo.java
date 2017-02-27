package com.design.cms.service.api.vo.user;

import lombok.Getter;
import lombok.Setter;

import com.design.cms.service.api.PageVo;
@Getter
@Setter
public class UserSearchVo extends PageVo{
	private String userNo;
	private String nickName;
}
