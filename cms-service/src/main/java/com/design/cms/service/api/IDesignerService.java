package com.design.cms.service.api;

import java.util.List;
import java.util.Map;

import com.design.cms.dao.entity.Designer;
import com.design.cms.dao.entity.dto.DesignerInfo;
import com.design.cms.service.api.vo.designer.DesignerReq;
import com.design.cms.service.api.vo.designer.DesignerSearchVo;

public interface IDesignerService extends PageOperation<DesignerSearchVo, DesignerInfo>{

	Long addDesigner(Designer designer);

	int delDesigner(Long id);

	List<DesignerInfo> getAllDesigner(DesignerReq req);

	void updateDesigner(Designer designer);

	List<Map<String, Object>> getExportData(DesignerSearchVo vo);

}
