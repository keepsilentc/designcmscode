package com.design.cms.service.impl.refund;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.design.cms.common.assist.Constant;
import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.enums.OrderDetailState;
import com.design.cms.common.enums.RecordType;
import com.design.cms.common.enums.RefundState;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.common.utils.DozerUtils;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.dao.entity.OrderDetail;
import com.design.cms.dao.entity.Refund;
import com.design.cms.dao.entity.RefundLog;
import com.design.cms.dao.entity.dto.RefundInfo;
import com.design.cms.dao.persist.RefundLogMapper;
import com.design.cms.dao.persist.RefundMapper;
import com.design.cms.service.api.IRefundService;
import com.design.cms.service.api.vo.refund.RefundApproveVo;
import com.design.cms.service.api.vo.refund.RefundSearchVo;
import com.design.cms.service.impl.order.OrderServiceImpl;
import com.google.common.collect.Maps;

@Service
public class RefundServiceImpl implements IRefundService{
	
	private static Logger log = LoggerFactory.getLogger(RefundServiceImpl.class);
	
	@Resource
	private OrderServiceImpl orderServiceImpl;
	@Resource
	private RefundMapper refundMapper;
	@Resource
	private RefundLogMapper refundLogMapper;
	
	@Override
	public int getCount(RefundSearchVo vo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(vo.getRefundNo())){
			param.put("refundNo", vo.getRefundNo());
		}
		if(StringUtils.isNotEmpty(vo.getOrderNo())){
			param.put("orderNo", vo.getOrderNo());
		}
		if(StringUtils.isNotEmpty(vo.getUserNo())){
			param.put("userNo", vo.getUserNo());
		}
		if(StringUtils.isNotEmpty(vo.getRefundType())){
			param.put("refundType", Integer.valueOf(vo.getRefundType()));
		}
		if(StringUtils.isNotEmpty(vo.getRefundState())){
			param.put("refundState", Integer.valueOf(vo.getRefundState()));
		}
		if(StringUtils.isNotEmpty(vo.getRefundPayTypeId())){
			param.put("refundPayTypeId", vo.getRefundPayTypeId());
		}
		if(StringUtils.isNotEmpty(vo.getStartTime())){
			param.put("startTime", DateUtil.parse(vo.getStartTime()+" 00:00:00", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(vo.getEndTime())){
			param.put("endTime", DateUtil.parse(vo.getEndTime()+" 11:59:59", DateUtil.allPattern));
		}
		return refundMapper.getCount(param);
	}

	@Override
	public List<RefundInfo> getList(RefundSearchVo vo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(vo.getRefundNo())){
			param.put("refundNo", vo.getRefundNo());
		}
		if(StringUtils.isNotEmpty(vo.getOrderNo())){
			param.put("orderNo", vo.getOrderNo());
		}
		if(StringUtils.isNotEmpty(vo.getUserNo())){
			param.put("userNo", vo.getUserNo());
		}
		if(StringUtils.isNotEmpty(vo.getRefundType())){
			param.put("refundType", Integer.valueOf(vo.getRefundType()));
		}
		if(StringUtils.isNotEmpty(vo.getRefundState())){
			param.put("refundState", Integer.valueOf(vo.getRefundState()));
		}
		if(StringUtils.isNotEmpty(vo.getRefundPayTypeId())){
			param.put("refundPayTypeId", vo.getRefundPayTypeId());
		}
		if(StringUtils.isNotEmpty(vo.getStartTime())){
			param.put("startTime", DateUtil.parse(vo.getStartTime()+" 00:00:00", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(vo.getEndTime())){
			param.put("endTime", DateUtil.parse(vo.getEndTime()+" 11:59:59", DateUtil.allPattern));
		}
		param.put("begin", vo.getBegin());
		param.put("end", vo.getEnd());
		List<Refund> refundList = refundMapper.getList(param);
		return DozerUtils.transferList(refundList, RefundInfo.class);
	}

	@Override
	public RefundInfo getRefundInfo(String refundNo) {
		Refund refund = getRefundByRefundNo(refundNo);
		RefundInfo refundInfo = DozerUtils.transfer(refund, RefundInfo.class);
		return refundInfo;
	}
	
	public Refund getRefundByRefundNo(String refundNo){
		Refund refund = refundMapper.getRefundByRefundNo(refundNo);
		if(refund==null){
			log.info("退款记录不存在,退款号：{}",refundNo);
			throw new DesignException(DesignEx.REFUND_NOT_EXISTS);
		}
		return refund;
	}

	@Override
	public List<RefundLog> follow(String refundNo) {
		getRefundByRefundNo(refundNo);
		Map<String,Object> params = Maps.newHashMap();
		params.put("refundNo",refundNo);
		List<RefundLog> refundLogList =  refundLogMapper.getRefundLogList(params);
		return refundLogList;
	}

	@Override
	@Transactional
	public void refundApprove(RefundApproveVo vo) {
		Refund refund = getRefundByRefundNo(vo.getRefundNo());
		RefundLog refundLog = new RefundLog();
		refundLog.setTraceLogId(TraceLogIdUtils.getTraceLogId());
		refundLog.setOrderNo(refund.getOrderNo());
		refundLog.setRefundNum(refund.getRefundNum());
		refundLog.setRefundNo(refund.getRefundNo());
		refundLog.setRefundType(refund.getRefundType());
		refundLog.setPayTypeId(refund.getPayTypeId());
		refundLog.setOrderDetailId(refund.getOrderDetailId());
		refundLog.setCreateTime(DateUtil.getCurrentDate());
		refundLog.setRecordType(RecordType.APPROVE.getTypeCode());
		if(RefundState.REFUND_APPROVE_PASS.getStateCode().equals(vo.getAgree())){
			refundLog.setRefundState(RefundState.REFUND_APPROVE_PASS.getStateCode());
			refund.setRefundState(RefundState.REFUND_APPROVE_PASS.getStateCode());
		}else if(RefundState.REFUND_APPROVE_FAIL.getStateCode().equals(vo.getAgree())){
			refundLog.setRefundState(RefundState.REFUND_APPROVE_FAIL.getStateCode());
			refundLog.setReason(vo.getFailReason());
			refund.setRefundState(RefundState.REFUND_APPROVE_FAIL.getStateCode());
			int i = Constant.TRYTIMES;
			do{
				i--;
				OrderDetail t_orderDetail = orderServiceImpl.getOrderDetailById(refund.getOrderDetailId());
				t_orderDetail.setOrderDetailState(OrderDetailState.REFUNDFAIL.getStateCode());
				int num = orderServiceImpl.updateOrderDetailAtApprove(t_orderDetail);
				if(num==1){
					break;
				}
			}while(i>0);
			if(i==0){
				log.info("编辑订单详细异常");
				throw new DesignException(DesignEx.INTERNAL_ERROR);
			}
		}
		refund.setUpdateBy("cms");
		refund.setUpdateTime(DateUtil.getCurrentDate());
		refundLogMapper.insert(refundLog);
		refundMapper.update(refund);
	}

	public List<Refund> getRefundList(Map<String, Object> param) {
		return refundMapper.getRefundList(param);
	}

}
