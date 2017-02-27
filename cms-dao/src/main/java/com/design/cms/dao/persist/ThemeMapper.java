package com.design.cms.dao.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.design.cms.dao.entity.Theme;
import com.design.cms.dao.entity.dto.ThemeInfo;


public interface ThemeMapper {

	void insert(Theme theme);

	int getCount(Map<String, Object> param);

	List<ThemeInfo> getList(Map<String, Object> param);

	int delTheme(@Param(value = "id")Long id);

	int update(Theme theme);

	List<Theme> getAllTheme(@Param(value = "designerId") Long designerId);

	List<Map<String, Object>> getExportData(Map<String, Object> param);

	ThemeInfo getThemeById(Long themeId);
	
}
