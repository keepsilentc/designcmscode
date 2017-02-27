tcApp.controller("productSizeColorAddStr",function($scope,productSizeColorService,sizeService,colorService){
			$scope.productSizeColor = {};
			$scope.$on("to_openModal",function(event,data){
				$scope.openModal(data);
			});
			$scope.openModal = function(item){
				$scope.productSizeColor = {};
				angular.element("#myModal").modal("show");
				var productNo = angular.element("#productNo").val();
				$scope.productSizeColor.productNo = productNo;
				if(item&&item.id){
					var temp = {};
					temp.sizeCountryId = item.sizeCountryId;
					temp.sizeTypeId = item.sizeTypeId;
					sizeService.getAllSize({}).then(function(data){
						var resp = data.data;
						if(resp.success){
							$scope.sizeList = resp.result;
						}else{
							alert(data.data.respMessage);
						}
					});
					
					$scope.productSizeColor = angular.copy(item);
					$scope.productSizeColor.sizeId = item.sizeId.toString();
					$scope.productSizeColor.colorId = item.colorId.toString();
				}else{
					
				}
			}
			$scope.add = function(){
				$scope.productSizeColor.error = null;
				if(!$scope.productSizeColor.sizeId){
					$scope.productSizeColor.error="尺寸不能为空";
				}
				if(!$scope.productSizeColor.colorId){
					$scope.productSizeColor.error="颜色不能为空";
				}
				if(!$scope.productSizeColor.inventory&&$scope.productSizeColor.inventory!=0){
					$scope.productSizeColor.error="库存不能为空";
				}
				if(!$scope.productSizeColor.error){
					productSizeColorService.addProductSizeColor($scope.productSizeColor).then(function(data){
						if(data.data.success){
							angular.element("#myModal").modal("hide");
							$scope.search();
						}else{
							alert(data.data.respMessage);
						}
					});
				}
				
			}
			
			$scope.changeSizeCountry = function(){
				var temp = {};
				temp.sizeCountryId = $scope.productSizeColor.sizeCountryId;
				temp.sizeTypeId = $scope.productSizeColor.sizeTypeId;
				sizeService.getAllSize(temp).then(function(data){
					var resp = data.data;
					if(resp.success){
						$scope.sizeList = resp.result;
					}else{
						alert(data.data.respMessage);
					}
				});
			}
			
			$scope.changeSizeType = function(){
				var temp = {};
				temp.sizeCountryId = $scope.productSizeColor.sizeCountryId;
				temp.sizeTypeId = $scope.productSizeColor.sizeTypeId;
				sizeService.getAllSize(temp).then(function(data){
					var resp = data.data;
					if(resp.success){
						$scope.sizeList = resp.result;
					}else{
						alert(data.data.respMessage);
					}
				});
			}
			
			$scope.update = function(){
				$scope.productSizeColor.error = null;
				if(!$scope.productSizeColor.sizeId){
					$scope.productSizeColor.error="尺寸不能为空";
				}
				if(!$scope.productSizeColor.colorId){
					$scope.productSizeColor.error="颜色不能为空";
				}
				if(!$scope.productSizeColor.inventory&&$scope.productSizeColor.inventory!=0){
					$scope.productSizeColor.error="库存不能为空";
				}
				if(!$scope.productSizeColor.error){
					productSizeColorService.updateProductSizeColor($scope.productSizeColor).then(function(data){
						if(data.data.success){
							angular.element("#myModal").modal("hide");
							$scope.search();
						}else{
							alert(data.data.respMessage);
						}
					});
				}
			}
		});