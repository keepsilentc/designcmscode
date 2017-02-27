tcApp.controller("userStr",function($scope,userService){
	$scope.pageVo = {};
	$scope.userList = [];
	$scope.userNo = null;
	
	$scope.init = function(){
		$scope.pageVo.current = 1;
	}
	
	$scope.showDetail = function(userNo){
		$scope.userNo = userNo;
		angular.element("#userInfo_Modal").modal("show");
	}
	
	$scope.search = function(){
		var $btn = angular.element("#search").button('loading');
		userService.search($scope.pageVo).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.userList = resp.result;
				$scope.pageVo = resp.pageVo;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	
	$scope.init();
});