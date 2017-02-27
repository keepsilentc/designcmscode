package com.design.cms.dao.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.design.cms.dao.entity.Category;

public interface CategoryMapper {

	List<Category> getAllCateGorys();

	List<Category> getCategorys(@Param(value = "parentId")Long parentId);

	void insert(Category category);

	void update(Category category);

	int del(@Param(value = "id") Long id);

}
