tcApp.controller("sizeStr",function($scope,sizeService,commonService){
		$scope.searchVo = {};
		$scope.sizeList = [];
		$scope.sizeTypeList = [];
		$scope.sizeCountryList = [];
		$scope.size = {};
		$scope.init = function(){
			commonService.getAllSizeType().then(function(data){
				if(data.data.success){
					var resp = data.data;
					$scope.sizeTypeList = resp.result;
				}
			});
			commonService.getAllSizeCountry().then(function(data){
				if(data.data.success){
					var resp = data.data;
					$scope.sizeCountryList = resp.result;
				}
			});
			$scope.search();
		}
		
		$scope.add = function(){
			$scope.size.error = null;
			if(!$scope.size.sizeCountryId){
				$scope.size.error="尺寸国家不能为空";
			}
			if(!$scope.size.sizeTypeId){
				$scope.size.error="尺寸类型不能为空";
			}
			if(!$scope.size.sizeName){
				$scope.size.error="尺寸名称不能为空";
			}
			if(!$scope.size.orderBy){
				$scope.size.error="排序不能为空或不能小于0";
			}
			if(!$scope.size.error){
				sizeService.addSize($scope.size).then(function(data){
					if(data.data.success){
						angular.element("#myModal").modal("hide");
						$scope.search();
					}else{
						alert(data.data.respMessage);
					}
				});
			}
		}
		$scope.delSize = function(id,index){
			if(id){
				sizeService.delSize(id).then(function(data){
					if(data.data.success){
						$scope.sizeList.splice(index,1);
					}else{
						alert(data.data.respMessage);
					}
				});
			}
		}
		$scope.update = function(){
			$scope.size.error = null;
			if(!$scope.size.sizeName){
				$scope.size.error="尺寸名称不能为空";
			}
			if(!$scope.size.orderBy){
				$scope.size.error="排序不能为空或不能小于0";
			}
			if(!$scope.size.error){
				sizeService.updateSize($scope.size).then(function(data){
					if(data.data.success){
						angular.element("#myModal").modal("hide");
						$scope.search();
					}else{
						alert(data.data.respMessage);
					}
				});
			}
		}
		
		$scope.search = function(){
			var $btn = angular.element("#search").button('loading');
			sizeService.getAllSize($scope.searchVo).then(function(data){
				if(data.data.success){
					$scope.sizeList = data.data.result;
				}else{
					alert(data.data.respMessage);
				}
				$btn.button('reset');
			});
		}
		$scope.init();
		$scope.openModal = function(item){
			$scope.size = {};
			angular.element("#myModal").modal("show");
			if(item&&item.id){
				$scope.size = angular.copy(item);
				$scope.size.sizeCountryId = item.sizeCountryId.toString();
				$scope.size.sizeTypeId = item.sizeTypeId.toString();
			}else{
				$scope.size.orderBy = 1;
			}
		}
		$scope.exports = function(){
			angular.element("#exportform").submit();
		}
});