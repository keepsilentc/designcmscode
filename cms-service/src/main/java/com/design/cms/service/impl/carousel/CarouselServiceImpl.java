package com.design.cms.service.impl.carousel;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.design.cms.common.assist.Constant;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.Carousel;
import com.design.cms.dao.persist.CarouselMapper;
import com.design.cms.service.api.ICarouselService;
import com.design.cms.service.api.vo.carousel.CarouselReq;
import com.google.common.collect.Maps;
@Service
public class CarouselServiceImpl implements ICarouselService{
	
	private static Logger log = LoggerFactory.getLogger(CarouselServiceImpl.class);
	
	@Resource
	private CarouselMapper carouselMapper;
	
	@Override
	public List<Carousel> getAllCarousel(CarouselReq vo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(vo.getIsEnable())){
			param.put("isEnable", Integer.valueOf(vo.getIsEnable()));
		}
		if(StringUtils.isNotEmpty(vo.getType())){
			param.put("type", Integer.valueOf(vo.getType()));
		}
		return carouselMapper.getAllCarousel(param);
	}

	@Override
	public void addCarousel(Carousel carousel) {
		carousel.setIsEnable(Constant.UNENABLE);
		carousel.setCreateTime(DateUtil.getCurrentDate());
		carouselMapper.insert(carousel);
	}

	@Override
	public void updateCarousel(Carousel carousel) {
		carousel.setUpdateTime(DateUtil.getCurrentDate());
		carouselMapper.update(carousel);
	}

	@Override
	public void delCarousel(String id) {
		log.info("删除轮播图,{}",id);
		carouselMapper.del(Long.valueOf(id));
	}

}
