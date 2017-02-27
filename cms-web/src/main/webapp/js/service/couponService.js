tcApp.service("couponService",function($http){
	this.getCoupons = function(vo){
		return $http.post(ctx+"/coupon/search.do",vo);
	};
	this.addCoupon = function(coupon){
		return $http.post(ctx+"/coupon/add.do",coupon);
	};
	this.updateCoupon = function(coupon){
		return $http.post(ctx+"/coupon/update.do",coupon);
	};
	this.delCoupon = function(id){
		return $http.post(ctx+"/coupon/del.do",id);
	};
	this.getCouponInfo = function(id){
		return $http.post(ctx+"/coupon/getCouponInfo.do",id);
	};
});