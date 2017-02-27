tcApp.controller("couponAddStr",function($scope,$filter,couponService){
	$scope.coupon = {};
	
	$scope.init = function(){
	}
	
	$scope.$on("to_openModal",function(event,data){
		$scope.openModal(data);
	});
	
	$scope.openModal = function(item){
		$scope.coupon = {};
		angular.element("#myModal").modal("show");
		if(item&&item.id){
			$scope.coupon = angular.copy(item);
			$scope.coupon.couponType = $scope.coupon.couponType.toString();
			$scope.coupon.startTime = $filter('date')(item.startTime,'yyyy-MM-dd HH:mm:ss');
			$scope.coupon.endTime = $filter('date')(item.endTime,'yyyy-MM-dd HH:mm:ss');
		}else{
			$scope.coupon.couponType = "0";
			$scope.coupon.couponStrategy = 1;
		}
	}
	
	$scope.update = function(){
		$scope.coupon.error = null;
		$scope.coupon.error = null;
		if(!$scope.coupon.endTime){
			$scope.coupon.error = "结束时间不能为空";
		}
		if(!$scope.coupon.startTime){
			$scope.coupon.error = "开始时间不能为空";
		}
		if($scope.coupon.couponStrategy==2&&(!$scope.coupon.fullMoney||!$scope.coupon.minusMoney)){
			$scope.coupon.error = "满减金额不能为空";
		}
		if($scope.coupon.couponStrategy==1){
			if(!$scope.coupon.couponRate){
				$scope.coupon.error = "折扣率不能为空";
			}else if($scope.coupon.couponRate>10){
				$scope.coupon.error = "折扣率不能大于10";
			}
		}
		if(!$scope.coupon.couponName){
			$scope.coupon.error = "优惠券名称不能为空";
		}
		if(!$scope.coupon.error){
			couponService.updateCoupon($scope.coupon).then(function(data){
				if(data.data.success){
					angular.element("#myModal").modal("hide");
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.add = function(){
		$scope.coupon.error = null;
		if(!$scope.coupon.endTime){
			$scope.coupon.error = "结束时间不能为空";
		}
		if(!$scope.coupon.startTime){
			$scope.coupon.error = "开始时间不能为空";
		}
		if($scope.coupon.couponStrategy==2&&(!$scope.coupon.fullMoney||!$scope.coupon.minusMoney)){
			$scope.coupon.error = "满减金额不能为空";
		}
		if($scope.coupon.couponStrategy==1){
			if(!$scope.coupon.couponRate){
				$scope.coupon.error = "折扣率不能为空";
			}else if($scope.coupon.couponRate>10){
				$scope.coupon.error = "折扣率不能大于10";
			}
		}
		if(!$scope.coupon.couponName){
			$scope.coupon.error = "优惠券名称不能为空";
		}
		if(!$scope.coupon.error){
			couponService.addCoupon($scope.coupon).then(function(data){
				if(data.data.success){
					angular.element("#myModal").modal("hide");
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.init();
	
});