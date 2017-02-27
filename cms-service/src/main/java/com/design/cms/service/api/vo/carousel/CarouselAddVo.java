package com.design.cms.service.api.vo.carousel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarouselAddVo {
	private Integer type;
	private Long picture;
	private Integer orderBy;
	private Integer isEnable;
}
