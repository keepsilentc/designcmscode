package com.design.cms.service.api;

import java.util.List;

import com.design.cms.dao.entity.Category;

public interface ICategoryService{

	List<Category> getCategorys(Long parentId);

	Long addCategory(Category category);

	void updateCategory(Category category);

	int delCategory(Long valueOf);
	
}
