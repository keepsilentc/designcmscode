package com.design.cms.service.impl.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.design.cms.dao.entity.Color;
import com.design.cms.dao.persist.ColorMapper;
import com.design.cms.service.api.IColorService;
import com.design.cms.service.impl.cmsuser.UploadServiceImpl;
@Service
public class ColorServiceImpl implements IColorService{
	
	@Resource
	private UploadServiceImpl uploadServiceImpl;
	
	@Resource
	private ColorMapper colorMapper;
	

	@Override
	@Transactional
	public Long addColor(Color color) {
		uploadServiceImpl.moveTempFileToFormal(color.getPicture());
		colorMapper.insert(color);
		return color.getId();
	}

	@Override
	public int delColor(Long id) {
		return colorMapper.del(id);
	}


	@Override
	public List<Color> getAllColor(String name) {
		return colorMapper.getAllColor(name);
	}

	@Override
	@Transactional
	public int updateColor(Color color) {
		uploadServiceImpl.moveTempFileToFormal(color.getPicture());
		return colorMapper.update(color);
	}

}
