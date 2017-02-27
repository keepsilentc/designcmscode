package com.design.cms.service.impl.designer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.common.assist.Constant;
import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.Theme;
import com.design.cms.dao.entity.dto.ThemeInfo;
import com.design.cms.dao.persist.ThemeMapper;
import com.design.cms.service.api.IThemeService;
import com.design.cms.service.api.vo.designer.ThemeSearchVo;
import com.design.cms.service.impl.cmsuser.UploadServiceImpl;
import com.google.common.collect.Maps;
@Service
public class ThemeServiceImpl implements IThemeService{
	@Resource
	private ThemeMapper themeMapper; 
	
	@Resource
	private UploadServiceImpl uploadServiceImpl;
	
	@Override
	public int getCount(ThemeSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getDesignerId())){
			param.put("designerId", Long.valueOf(searchVo.getDesignerId()));
		}
		if(StringUtils.isNotEmpty(searchVo.getThemeName())){
			param.put("themeName", searchVo.getThemeName());
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			param.put("isEnable", Integer.valueOf(searchVo.getIsEnable()));
		}
		return themeMapper.getCount(param);
	}

	@Override
	public List<ThemeInfo> getList(ThemeSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getDesignerId())){
			param.put("designerId",Long.valueOf(searchVo.getDesignerId()));
		}
		if(StringUtils.isNotEmpty(searchVo.getThemeName())){
			param.put("themeName", searchVo.getThemeName());
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			param.put("isEnable", Integer.valueOf(searchVo.getIsEnable()));
		}
		param.put("begin", searchVo.getBegin());
		param.put("end", searchVo.getEnd());
		return themeMapper.getList(param);
	}

	@Override
	public void delTheme(Long id) {
		themeMapper.delTheme(id);
	}

	@Override
	public Long addTheme(Theme theme) {
		theme.setCreateTime(DateUtil.getCurrentDate());
		theme.setIsEnable(Constant.UNENABLE);
		themeMapper.insert(theme);
		return theme.getId();
	}

	@Override
	public int updateTheme(Theme theme) {
		theme.setUpdateTime(DateUtil.getCurrentDate());
		return themeMapper.update(theme);
	}

	@Override
	public List<Theme> getAllTheme(Long designerId) {
		return themeMapper.getAllTheme(designerId);
	}

	@Override
	public List<Map<String, Object>> getExportData(ThemeSearchVo vo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(vo.getDesignerId())){
			param.put("designerId",Long.valueOf(vo.getDesignerId()));
		}
		if(StringUtils.isNotEmpty(vo.getThemeName())){
			param.put("themeName", vo.getThemeName());
		}
		if(StringUtils.isNotEmpty(vo.getIsEnable())){
			param.put("isEnable", Integer.valueOf(vo.getIsEnable()));
		}
		return themeMapper.getExportData(param);
	}

	@Override
	public ThemeInfo getThemeById(Long themeId) {
		ThemeInfo t_themeInfo = themeMapper.getThemeById(themeId);
		if(t_themeInfo==null){
			throw new DesignException(DesignEx.THEMENOTFIND);
		}
		return t_themeInfo;
	}


}
