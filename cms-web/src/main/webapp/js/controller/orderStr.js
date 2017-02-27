tcApp.controller("orderStr",function($scope,orderService){
	$scope.pageVo = {};	
	$scope.orderList = [];
	$scope.orderNo = null;
	$scope.userNo = null;
	
	$scope.showDetail = function(orderNo){
		$scope.orderNo = orderNo;
		angular.element("#orderDetail_Modal").modal("show");
	}
	
	$scope.showUserInfo = function(userNo){
		$scope.userNo = userNo;
		angular.element("#userInfo_Modal").modal("show");
	}
	
	$scope.todeliver = function(orderNo){
		$scope.$broadcast("to_deliver",orderNo);
	}
	
	
	$scope.init = function(){
		angular.element(".time").datetimepicker({
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			todayHighligh:true,
			minView:'month',
	        maxView:'decade'
		});
		$scope.pageVo.current = 1;
	}
	
	$scope.search = function(){
		var $btn = angular.element("#search").button('loading');
		orderService.getOrders($scope.pageVo).then(function(data){
			var resp = data.data;
			if(resp.success){
				$scope.orderList = resp.result;
				$scope.pageVo = resp.pageVo;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button('reset');
		});
	}
	
	$scope.init();
});