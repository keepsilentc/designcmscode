package com.design.cms.service.impl.payload;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.service.api.IPayLoadService;
import com.design.cms.service.api.IPayLoadStrategy;
import com.design.cms.service.api.vo.payload.PayLoad;
@Service
public class PayLoadServiceImpl implements IPayLoadService {
	
	@Resource
	private PayLoadUtil payLoadUtil;
	
	@Override
	public void exec(IPayLoadStrategy strategy) {
		PayLoad payLoad = strategy.getPayLoad();
		payLoadUtil.send(payLoad);
	}
	
	
	
	
	
}
