package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Carousel;

public interface CarouselMapper {

	List<Carousel> getAllCarousel(Map<String, Object> param);

	void insert(Carousel carousel);

	void update(Carousel carousel);

	void del(Long valueOf);
}
