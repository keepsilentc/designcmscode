tcApp.service("couponProductService",function($http){
	this.getCouponProducts = function(vo){
		return $http.post(ctx+"/couponProduct/search.do",vo);
	};
	this.save = function(list){
		return $http.post(ctx+"/couponProduct/save.do",list);
	}
});