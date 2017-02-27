package com.design.cms.service.api;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Theme;
import com.design.cms.dao.entity.dto.ThemeInfo;
import com.design.cms.service.api.vo.designer.ThemeSearchVo;

public interface IThemeService extends PageOperation<ThemeSearchVo, ThemeInfo> {

	Long addTheme(Theme theme);

	void delTheme(Long id);

	int updateTheme(Theme theme);

	List<Theme> getAllTheme(Long designerId);

	List<Map<String, Object>> getExportData(ThemeSearchVo vo);

	ThemeInfo getThemeById(Long themeId);

}
