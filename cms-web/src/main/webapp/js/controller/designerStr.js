tcApp.controller("designerStr",function($scope,Upload,designerService,commonService){
	$scope.pageVo = {};
	$scope.designer = {};
	$scope.designerList = [];
	$scope.countryList = [];
	$scope.brandList = [];
	
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
		$scope.pageVo.current = 1;
	}
	
	$scope.search = function(){
		var $btn = angular.element("#search").button('loading');
		designerService.getDesigners($scope.pageVo).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.designerList = resp.result;
				$scope.pageVo = resp.pageVo;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	
	$scope.upload = function(file,item){
		Upload.upload({
            url: ctx+'/file/upload.do?classify=designer',
            data: {file: file}
        }).then(function (resp) {
        	item.photo = resp.data.result.attachmentId;
        	designerService.updateDesigner(item).then(function(data){
        		if(!data.data.success){
					alert(data.data.respMessage);
				}
        	});
        }, function (resp) {
            console.log('Error status: ' + resp.status);
        }, function (evt) {
//            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
        });
	}
	
	$scope.openModal = function(item){
		$scope.designer = {};
		angular.element("#myModal").modal("show");
		if(item){
			$scope.designer = angular.copy(item);
			$scope.designer.countryId = item.countryId.toString();
			$scope.designer.brandId = item.brandId.toString();
		}else{
			$scope.designer.countryId = $scope.pageVo.countryId;
			$scope.designer.brandId = $scope.pageVo.brandId;
		}
	}
	
	$scope.update = function(){
		$scope.designer.error = null;
		/*if(!$scope.designer.brandId){
			$scope.designer.error="品牌不能为空";
		}*/
		if(!$scope.designer.countryId){
			$scope.designer.error="国家不能为空";
		}
		if(!$scope.designer.described){
			$scope.designer.error="设计师描述不能为空";
		}
		if(!$scope.designer.designerName){
			$scope.designer.error="设计师名称不能为空";
		}
		if(!$scope.designer.error){
			designerService.updateDesigner($scope.designer).then(function(data){
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
		$scope.designer.error = null;
		/*if(!$scope.designer.brandId){
			$scope.designer.error="品牌不能为空";
		}*/
		if(!$scope.designer.countryId){
			$scope.designer.error="国家不能为空";
		}
		if(!$scope.designer.described){
			$scope.designer.error="设计师描述不能为空";
		}
		if(!$scope.designer.designerName){
			$scope.designer.error="设计师名称不能为空";
		}
		if(!$scope.designer.error){
			designerService.addDesigner($scope.designer).then(function(data){
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
	$scope.delDesigner = function(item){
		if(item.id){
			designerService.delDesigner(item.id).then(function(data){
				if(data.data.success){
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.changeItem = function(item){
		designerService.updateDesigner(item).then(function(data){
			if(!data.data.success){
				alert(data.data.respMessage);
			}
		});
	}
	
	$scope.exports = function(){
		angular.element("#exportform").submit();
	}
});