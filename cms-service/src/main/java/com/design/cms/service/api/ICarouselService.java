package com.design.cms.service.api;

import java.util.List;

import com.design.cms.dao.entity.Carousel;
import com.design.cms.service.api.vo.carousel.CarouselReq;

public interface ICarouselService {

	List<Carousel> getAllCarousel(CarouselReq vo);

	void addCarousel(Carousel carousel);

	void updateCarousel(Carousel carousel);

	void delCarousel(String id);

}
