tcApp.controller("refundApproveStr",function($scope,refundService){
	$scope.refund = {};
	$scope.orderNo = null;
	$scope.approve = {};
	$scope.init = function(){
		var refundNo = angular.element("#refundNo").val();
		refundService.getRefundInfo(refundNo).then(function(data){
			var resp = data.data;
			if(resp.success){
				$scope.refund = resp.result;
				$scope.orderNo = resp.result.orderNo;
			}else{
				alert(data.data.respMessage);
			}
		});
	}
	
	$scope.init();
	
	$scope.refundApprove = function(){
		$scope.approve.error= null;
		$scope.approve.refundNo = angular.element("#refundNo").val();
		if(!$scope.approve.agree&&$scope.approve.agree!=0){
			$scope.approve.error="退款是否同意";
		}
		if($scope.approve.agree==0&&!$scope.approve.failReason){
			$scope.approve.error="未填写不同意理由";
		}
		if(!$scope.approve.error){
			refundService.refundApprove($scope.approve).then(function(data){
				var resp = data.data;
				if(resp.success){
					alert("审核成功");
					window.location.href=ctx+"/refund/index.do"
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
});