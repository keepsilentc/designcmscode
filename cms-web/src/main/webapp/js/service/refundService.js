tcApp.service("refundService",function($http){
	this.getRefunds = function(vo){
		return $http.post(ctx+"/refund/search.do",vo);
	};
	this.getRefundInfo = function(refundNo){
		return $http.post(ctx+"/refund/getRefundInfo.do",refundNo);
	};
	this.follow = function(refundNo){
		return $http.post(ctx+"/refund/follow.do",refundNo);
	};
	this.refundApprove = function(vo){
		return $http.post(ctx+"/refund/refundApprove.do",vo);
	};
	this.doRefund = function(vo){
		return $http.post(ctx+"/refund/doRefund.do",vo);
	};
	
});