tcApp.service("returnService",function($http){
	this.getReturns = function(vo){
		return $http.post(ctx+"/return/search.do",vo);
	};
	this.getReturnInfo = function(returnNo){
		return $http.post(ctx+"/return/getReturnInfo.do",returnNo);
	};
	this.follow = function(returnNo){
		return $http.post(ctx+"/return/follow.do",returnNo);
	};
	this.returnApprove = function(vo){
		return $http.post(ctx+"/return/returnApprove.do",vo);
	};
});