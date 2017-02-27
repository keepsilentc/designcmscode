tcApp.controller("payLoadAddStr",function($scope,payLoadService){
	$scope.payLoad = {};
	$scope.send = function(){
		var $btn = angular.element("#send").button('loading');
		payLoadService.send($scope.payLoad).then(function(data){
			if(data.data.success){
				angular.element("#myModal").modal("hide");
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	
	$scope.to_add = function(){
		window.location.href = ctx + "/payLoad/add/index.do";
	}
	
});