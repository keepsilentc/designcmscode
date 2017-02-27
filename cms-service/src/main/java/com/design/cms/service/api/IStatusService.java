package com.design.cms.service.api;

import com.design.cms.dao.entity.Status;
import com.design.cms.dao.entity.dto.StatusInfo;
import com.design.cms.service.api.vo.designer.StatusSearchVo;


public interface IStatusService extends PageOperation<StatusSearchVo, StatusInfo>  {

	Long addStatus(Status status);

	void updateStatus(Status status);

	String getStatusDetailTxt(String url);

}
