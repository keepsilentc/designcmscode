tcApp.controller("deliverStr",function($scope,orderService){
	$scope.deliver = {};
	
	$scope.$on("to_deliver",function(event,data){
		$scope.deliver.orderNo = data;
		angular.element("#deliver_Modal").modal("show");
	});
	
	$scope.dodeliver = function(){
		$scope.deliver.error = null;
		if(!$scope.deliver.deliverNo){
			$scope.deliver.error = "未填写物流单号";
		}
		if(!$scope.deliver.deliverName){
			$scope.deliver.error = "未填写物流名称";
		}
		if(!$scope.deliver.error){
			var $btn = angular.element("#deliverBtn").button('loading');
			orderService.deliver($scope.deliver).then(function(data){
				var resp = data.data;
				if(resp.success){
					alert("success");
					angular.element("#deliver_Modal").modal("hide");
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
				$btn.button('reset');
			});
		}
		
	};
	
});