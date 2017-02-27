tcApp.controller("payLoadStr",function($scope,payLoadService){
	
	$scope.to_add = function(){
		window.location.href = ctx + "/payLoad/add/index.do";
	}
	
});