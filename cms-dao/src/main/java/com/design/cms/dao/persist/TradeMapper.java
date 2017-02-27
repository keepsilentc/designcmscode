package com.design.cms.dao.persist;

import java.util.Map;

import com.design.cms.dao.entity.Trade;


public interface TradeMapper {

	Trade getTradeByParam(Map<String, Object> params);

}
