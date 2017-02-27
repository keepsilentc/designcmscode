package com.design.cms.service.api;

import java.util.List;

import com.design.cms.dao.entity.Color;

public interface IColorService{

	Long addColor(Color color);
	
	int updateColor(Color color);
	
	int delColor(Long id);

	List<Color> getAllColor(String name);

}
