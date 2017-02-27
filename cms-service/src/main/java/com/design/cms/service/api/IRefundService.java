package com.design.cms.service.api;

import java.util.List;

import com.design.cms.dao.entity.RefundLog;
import com.design.cms.dao.entity.dto.RefundInfo;
import com.design.cms.service.api.vo.refund.RefundApproveVo;
import com.design.cms.service.api.vo.refund.RefundSearchVo;

public interface IRefundService extends PageOperation<RefundSearchVo, RefundInfo>{

	RefundInfo getRefundInfo(String refundNo);

	List<RefundLog> follow(String refundNo);

	void refundApprove(RefundApproveVo vo);

}
