package com.design.cms.dao.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.design.cms.dao.entity.Color;


public interface ColorMapper {

	List<Color> getAllColor(@Param(value = "colorName")String colorName);

	void insert(Color color);

	int del(Long id);

	int update(Color color);

}
