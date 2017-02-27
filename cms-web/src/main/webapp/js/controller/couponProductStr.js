tcApp.controller("couponProductStr",function($scope,couponProductService,commonService,productService,designerService,themeService){
	$scope.searchVo = {};
	$scope.productList = [];
	$scope.couponProductList = [];
	$scope.countryList = [];
	$scope.brandList = [];
	$scope.designerList = [];
	$scope.themeList = [];

	$scope.init = function(){
		commonService.getAllCountry().then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.countryList = resp.result;
			}
		});
		commonService.getAllBrand().then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.brandList = resp.result;
			}
		});
		$scope.couponProductsearch();
	}
	
	$scope.couponProductsearch = function(){
		$scope.searchVo.couponNo = angular.element("#couponNo").val();
		var $btn = angular.element("#couponProductSearch").button('loading');
		couponProductService.getCouponProducts($scope.searchVo).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.couponProductList = resp.result;
				if($scope.productList.length){
					var list = $scope.couponProductList;
					angular.forEach($scope.productList,function(data){
						for(var i=list.length-1;i>-1;i--){
							if(list[i].productNo==data.productNo){
								list.splice(i,1);
							}
						}
					});
				}
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	
	$scope.productsearch = function(){
		$scope.searchVo.couponNo = angular.element("#couponNo").val();
		var $btn = angular.element("#productsearch").button('loading');
		productService.getProductList($scope.searchVo).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.productList = resp.result;
				if($scope.couponProductList.length){
					var list = $scope.productList;
					angular.forEach($scope.couponProductList,function(data){
						for(var i=list.length-1;i>-1;i--){
							if(list[i].productNo==data.productNo){
								list.splice(i,1);
							}
						}
					});
				}
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	
	$scope.changeCountry = function(){
		$scope.searchVo.designerId = null;
		$scope.searchVo.themeId = null;
		if(!$scope.searchVo.countryId&&!$scope.searchVo.brandId){
			$scope.designerList = [];
			$scope.themeList = [];
		}else{
			designerService.getAllDesigner({brandId:$scope.searchVo.brandId,countryId:$scope.searchVo.countryId}).then(function(data){
				if(data.data.success){
					var resp = data.data;
					$scope.designerList = resp.result;
					$scope.themeList = [];
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.changeBrand = function(){
		$scope.searchVo.designerId = null;
		$scope.searchVo.themeId = null;
		if(!$scope.searchVo.countryId&&!$scope.searchVo.brandId){
			$scope.designerList = [];
			$scope.themeList = [];
		}else{
			designerService.getAllDesigner({brandId:$scope.searchVo.brandId,countryId:$scope.searchVo.countryId}).then(function(data){
				if(data.data.success){
					var resp = data.data;
					$scope.designerList = resp.result;
					$scope.themeList = [];
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.changeDesigner = function(){
		$scope.searchVo.themeId = null;
		if(!$scope.searchVo.designerId){
			$scope.themeList = [];
		}else{
			themeService.getAllTheme($scope.searchVo.designerId).then(function(data){
				if(data.data.success){
					var resp = data.data;
					$scope.themeList = resp.result;
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.join = function(){
		var couponNo = angular.element("#couponNo").val();
		var list = $scope.productList;
		angular.forEach($scope.productNos,function(productNo){
			for(var i=list.length-1;i>-1;i--){
				if(list[i].productNo==productNo){
					$scope.couponProductList.push({couponNo:couponNo,productNo:productNo});
					$scope.productList.splice(i,1);
				}
				
			}
		});
	}
	$scope.remove = function(){
		var couponNo = angular.element("#couponNo").val();
		var list = $scope.couponProductList;
		angular.forEach($scope.couponProductNos,function(productNo){
			for(var i=list.length-1;i>-1;i--){
				if(list[i].productNo==productNo){
					$scope.productList.push({productNo:productNo});
					$scope.couponProductList.splice(i,1);
				}
			}
		});
	}
	
	$scope.save = function(){
		var $btn = angular.element("#save").button('loading');
		couponProductService.save($scope.couponProductList).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.couponProductList = resp.result;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	$scope.init();
});