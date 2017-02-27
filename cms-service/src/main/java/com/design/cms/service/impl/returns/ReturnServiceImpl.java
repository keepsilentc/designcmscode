package com.design.cms.service.impl.returns;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.ReturnLog;
import com.design.cms.dao.entity.dto.ReturnInfo;
import com.design.cms.dao.persist.ReturnLogMapper;
import com.design.cms.dao.persist.ReturnMapper;
import com.design.cms.service.api.IReturnService;
import com.design.cms.service.api.vo.returns.ReturnApproveVo;
import com.design.cms.service.api.vo.returns.ReturnSearchVo;
import com.google.common.collect.Maps;

@Service
public class ReturnServiceImpl implements IReturnService {
	
	@Resource
	private ReturnMapper returnMapper;
	@Resource
	private ReturnLogMapper returnLogMapper;
	
	@Override
	public int getCount(ReturnSearchVo searchVo) {
		Map<String,Object> params = Maps.newHashMap();
		if (StringUtils.isNotEmpty(searchVo.getReturnNo())) {
			params.put("returnNo", searchVo.getReturnNo());
		}
		if (StringUtils.isNotEmpty(searchVo.getOrderNo())) {
			params.put("orderNo", searchVo.getOrderNo());
		}
		if (StringUtils.isNotEmpty(searchVo.getReturnType())) {
			params.put("returnType", searchVo.getReturnType());
		}
		if (StringUtils.isNotEmpty(searchVo.getReturnState())) {
			params.put("returnState", searchVo.getReturnState());
		}
		if (StringUtils.isNotEmpty(searchVo.getStartTime())) {
			params.put("startTime", searchVo.getStartTime());
		}
		if (StringUtils.isNotEmpty(searchVo.getEndTime())) {
			params.put("endTime", searchVo.getEndTime());
		}
		
		return returnMapper.getCount(params);
	}

	@Override
	public List<ReturnInfo> getList(ReturnSearchVo searchVo) {
		Map<String,Object> params = Maps.newHashMap();
		if (StringUtils.isNotEmpty(searchVo.getReturnNo())) {
			params.put("returnNo", searchVo.getReturnNo());
		}
		if (StringUtils.isNotEmpty(searchVo.getOrderNo())) {
			params.put("orderNo", searchVo.getOrderNo());
		}
		if (StringUtils.isNotEmpty(searchVo.getReturnType())) {
			params.put("returnType", searchVo.getReturnType());
		}
		if (StringUtils.isNotEmpty(searchVo.getReturnState())) {
			params.put("returnState", searchVo.getReturnState());
		}
		if (StringUtils.isNotEmpty(searchVo.getStartTime())) {
			params.put("startTime", searchVo.getStartTime());
		}
		if (StringUtils.isNotEmpty(searchVo.getEndTime())) {
			params.put("endTime", searchVo.getEndTime());
		}
		params.put("begin", searchVo.getBegin());
		params.put("end", searchVo.getEnd());
		return returnMapper.getList(params);
	}

	@Override
	public List<ReturnLog> follow(String returnNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnInfo getReturnInfo(String returnNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void returnApprove(ReturnApproveVo vo) {
		// TODO Auto-generated method stub
		
	}

}
