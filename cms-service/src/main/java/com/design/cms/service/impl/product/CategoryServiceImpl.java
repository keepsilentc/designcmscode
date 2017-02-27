package com.design.cms.service.impl.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.dao.entity.Category;
import com.design.cms.dao.persist.CategoryMapper;
import com.design.cms.service.api.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Resource
	private CategoryMapper cateGoryMapper;

	@Override
	/**
	 * 查询类目,parent为null时返回一级类目
	 */
	public List<Category> getCategorys(Long parentId) {
		return cateGoryMapper.getCategorys(parentId);
	}

	@Override
	public Long addCategory(Category category) {
		cateGoryMapper.insert(category);
		return category.getId();
	}

	@Override
	public void updateCategory(Category category) {
		cateGoryMapper.update(category);
	}

	@Override
	public int delCategory(Long id) {
		return cateGoryMapper.del(id);
	}

	public List<Category> getAllCategory() {
		return cateGoryMapper.getAllCateGorys();
	}


}
