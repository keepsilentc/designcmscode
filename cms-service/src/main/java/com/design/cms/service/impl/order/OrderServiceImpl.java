package com.design.cms.service.impl.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.CouponStrategy;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.enums.OrderState;
import com.design.cms.common.enums.RefundState;
import com.design.cms.common.utils.ChicunMoney;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.common.utils.DozerUtils;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.Order;
import com.design.cms.dao.entity.OrderDetail;
import com.design.cms.dao.entity.Refund;
import com.design.cms.dao.entity.dto.CouponInfo;
import com.design.cms.dao.entity.dto.OrderAllInfo;
import com.design.cms.dao.entity.dto.OrderDetailInfo;
import com.design.cms.dao.entity.dto.OrderInfo;
import com.design.cms.dao.entity.dto.ProductSizeColorInfo;
import com.design.cms.dao.persist.OrderDetailMapper;
import com.design.cms.dao.persist.OrderLogMapper;
import com.design.cms.dao.persist.OrderMapper;
import com.design.cms.service.api.IOrderService;
import com.design.cms.service.api.vo.order.DeliverVo;
import com.design.cms.service.api.vo.order.OrderSearchVo;
import com.design.cms.service.impl.coupon.CouponServiceImpl;
import com.design.cms.service.impl.product.ProductServiceImpl;
import com.design.cms.service.impl.refund.RefundServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
@Service
public class OrderServiceImpl implements IOrderService {
	
	@Resource
	private RefundServiceImpl refundServiceImpl;
	@Resource
	private CouponServiceImpl couponServiceImpl;
	@Resource
	private ProductServiceImpl productServiceImpl;
	@Resource
	private OrderDetailMapper orderDetailMapper;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderLogMapper orderLogMapper;
	
	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	public List<OrderDetail> getOrderDetailList(String orderNo){
		return orderDetailMapper.getOrderDetailList(orderNo);
	}
	

	@Override
	public int getCount(OrderSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getUserNo())){
			param.put("userNo", searchVo.getUserNo());
		}
		if(StringUtils.isNotEmpty(searchVo.getOrderNo())){
			param.put("orderNo", searchVo.getOrderNo());
		}
		if(StringUtils.isNotEmpty(searchVo.getOrderType())){
			param.put("orderType", Integer.valueOf(searchVo.getOrderType()));
		}
		if(StringUtils.isNotEmpty(searchVo.getOrderState())){
			param.put("orderState", Integer.valueOf(searchVo.getOrderState()));
		}
		if(StringUtils.isNotEmpty(searchVo.getStartTime())){
			param.put("startTime", DateUtil.parse(searchVo.getStartTime()+" 00:00:00", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(searchVo.getEndTime())){
			param.put("endTime", DateUtil.parse(searchVo.getEndTime()+" 11:59:59", DateUtil.allPattern));
		}
		return orderMapper.getCount(param);
	}


	@Override
	public List<OrderInfo> getList(OrderSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getUserNo())){
			param.put("userNo", searchVo.getUserNo());
		}
		if(StringUtils.isNotEmpty(searchVo.getOrderNo())){
			param.put("orderNo", searchVo.getOrderNo());
		}
		if(StringUtils.isNotEmpty(searchVo.getOrderType())){
			param.put("orderType", Integer.valueOf(searchVo.getOrderType()));
		}
		if(StringUtils.isNotEmpty(searchVo.getOrderState())){
			param.put("orderState", Integer.valueOf(searchVo.getOrderState()));
		}
		if(StringUtils.isNotEmpty(searchVo.getStartTime())){
			param.put("startTime", DateUtil.parse(searchVo.getStartTime()+" 00:00:00", DateUtil.allPattern));
		}
		if(StringUtils.isNotEmpty(searchVo.getEndTime())){
			param.put("endTime", DateUtil.parse(searchVo.getEndTime()+" 11:59:59", DateUtil.allPattern));
		}
		param.put("begin", searchVo.getBegin());
		param.put("end", searchVo.getEnd());
		
		List<OrderInfo> orderInfos = orderMapper.getList(param);
		
		for(OrderInfo orderInfo:orderInfos){
			if(StringUtils.isNotEmpty(orderInfo.getCouponNo())){
				StringBuilder builder = new StringBuilder();
				CouponInfo from = couponServiceImpl.getCouponInfoByNo(orderInfo.getCouponNo());
				if(CouponStrategy.DISCOUNT.getStrategyCode().equals(from.getCouponStrategy())){
					if(!ChicunMoney.isInteger(from.getCouponRate())){
						builder.append(new ChicunMoney(from.getCouponRate()).multiply(10).setScale(0));
					}else{
						builder.append(from.getCouponRate().setScale(0));
					}
					
					builder.append("折优惠");
					orderInfo.setCouponDesc(builder.toString());
				}
				if(CouponStrategy.FULL_REDUCTION.getStrategyCode().equals(from.getCouponStrategy())){
					builder.append("满");
					builder.append(from.getFullMoney());
					builder.append("减");
					builder.append(from.getMinusMoney());
					orderInfo.setCouponDesc(builder.toString());
				}
			}
		}
		return orderInfos;
	}


	@Override
	public OrderAllInfo getOrderAllInfo(String orderNo) {
		OrderInfo t_order = getOrderInfoByOrderNo(orderNo);
		List<OrderDetail> t_orderDetailList = getOrderDetailList(orderNo);
		List<OrderDetailInfo> orderDetailInfoList = Lists.newArrayList();
		OrderDetailInfo orderDetailInfo = null;
		for(OrderDetail tmp:t_orderDetailList){
			ProductSizeColorInfo t_ProductSizeColor =  productServiceImpl.getProductColorSizeById(tmp.getPtstId());
			orderDetailInfo = DozerUtils.transfer(tmp, OrderDetailInfo.class);
			orderDetailInfo.setColorName(t_ProductSizeColor.getColorName());
			orderDetailInfo.setSizeName(t_ProductSizeColor.getSizeName());
			orderDetailInfo.setPicture(String.valueOf(t_ProductSizeColor.getPicture()));
			orderDetailInfoList.add(orderDetailInfo);
		}
		
		OrderAllInfo orderAllInfo = DozerUtils.transfer(t_order, OrderAllInfo.class);
		orderAllInfo.setOrderDetails(orderDetailInfoList);
		return orderAllInfo;
	}
	
	/**
	 * 根据订单号获取订单(如果订单不存在,抛异常)
	 * @param orderNO
	 * @return
	 */
	public OrderInfo getOrderInfoByOrderNo(String orderNo) {
		OrderInfo t_order = orderMapper.getOrderInfoByOrderNo(orderNo);
		if(t_order==null){
			log.info("订单不存在,{}",orderNo);
			throw new DesignException(DesignEx.ORDER_NOT_EXISTS);
		}
		return t_order;
	}


	@Override
	public void deliver(DeliverVo vo) {
		String orderNo = vo.getOrderNo();
		
		OrderInfo order = getOrderInfoByOrderNo(orderNo);
		//验证订单是否可以发货
		validateCanDeliver(order);
		
		String deliverName = vo.getDeliverName();
		String deliverNo = vo.getDeliverNo();
		order.setDeliverNo(deliverNo);
		order.setDeliverName(deliverName);
		order.setOrderState(OrderState.DELIVERED.getStateCode());
		order.setUpdateBy("cms");
		order.setUpdateTime(DateUtil.getCurrentDate());
		if(orderMapper.deliverUpdate(order)<1){
			log.info("修改订单为发货状态异常");
			throw new DesignException(DesignEx.INTERNAL_ERROR);
		}
	}
	
	public void validateCanDeliver(Order order){
		
		if(!OrderState.PAY_SUCCESS.getStateCode().equals(order.getOrderState())){
			log.info("订单状态不是支付成功,不可发货,订单状态为:{}",order.getOrderState());
			throw new DesignException(DesignEx.CANOT_DELIVER);
		}
		
		Map<String,Object> param = Maps.newHashMap();
		param.put("orderNo", order.getOrderNo());
		List<Refund> refundList =  refundServiceImpl.getRefundList(param);
		for(Refund temp:refundList){
			if(!RefundState.REFUND_SUCCESS.getStateCode().equals(temp.getRefundState())){
				log.info("存在未退款成功的退款记录,不可发货,{}",temp);
				throw new DesignException(DesignEx.CANOT_DELIVER);
			}
		}
		
	}
	
	public int updateOrderDetailAtApprove(OrderDetail orderDetail) {
		return orderDetailMapper.updateOrderDetailAtnotify(orderDetail);
	}


	public OrderDetail getOrderDetailById(Long orderDetailId) {
		return orderDetailMapper.getOrderDetailById(orderDetailId);
	}

}
