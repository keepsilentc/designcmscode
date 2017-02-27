tcApp.service("orderService",function($http){
	this.getOrders = function(vo){
		return $http.post(ctx+"/order/search.do",vo);
	};
	this.getOrderInfo = function(orderNo){
		return $http.post(ctx+"/order/getOrderInfo.do",orderNo);
	};
	this.deliver = function(vo){
		return $http.post(ctx+"/order/deliver.do",vo);
	};
});