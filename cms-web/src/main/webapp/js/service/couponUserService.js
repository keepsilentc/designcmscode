tcApp.service("couponUserService",function($http){
	this.getCouponNotAllocateUsers = function(vo){
		return $http.post(ctx+"/couponUser/getCouponNotAllocateUsers.do",vo);
	};
	this.save = function(vo){
		return $http.post(ctx+"/couponUser/save.do",vo);
	};
});