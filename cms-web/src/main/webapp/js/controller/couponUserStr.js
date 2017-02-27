tcApp.controller("couponUserStr",function($scope,couponUserService){
	$scope.couponUsers = [];
	$scope.init = function(){
		$scope.couponNo = angular.element("#couponNo").val();
		couponUserService.getCouponNotAllocateUsers($scope.couponNo).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.couponUsers = resp.result;
			}else{
				alert(data.data.respMessage);
			}
		});
	}
	$scope.init();
	$scope.join = function(){
		angular.forEach($scope.couponUserL,function(item){
			angular.forEach($scope.couponUsers,function(data){
				if(data.userNo==item){
					data.choose = true;
				}
			});
		});
	}
	$scope.remove = function(){
		angular.forEach($scope.couponUserR,function(item){
			angular.forEach($scope.couponUsers,function(data){
				if(data.userNo==item){
					data.choose = false;
				}
			});
		});
	}
	$scope.save = function(){
		var saveCouponUsers = [];
		angular.forEach($scope.couponUsers,function(data){
			if(data.choose){
				saveCouponUsers.push(data.userNo);
			}
		});
		var $btn = angular.element("#save").button('loading');
		couponUserService.save({couponNo:$scope.couponNo,userNoList:saveCouponUsers}).then(function(data){
			if(data.data.success){
				alert("success");
				$scope.init();
			}else{
				alert(data.data.respMessage);
			}
			$btn.button('reset');
		});
	}
});