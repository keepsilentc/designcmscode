package com.design.cms.service.api;

import java.util.List;

import com.design.cms.dao.entity.ReturnLog;
import com.design.cms.dao.entity.dto.ReturnInfo;
import com.design.cms.service.api.vo.returns.ReturnApproveVo;
import com.design.cms.service.api.vo.returns.ReturnSearchVo;

public interface IReturnService extends PageOperation<ReturnSearchVo,ReturnInfo> {

	List<ReturnLog> follow(String returnNo);

	ReturnInfo getReturnInfo(String returnNo);

	void returnApprove(ReturnApproveVo vo);

}
