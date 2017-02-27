tcApp.controller("themeStr",function($scope,$filter,Upload,themeService,designerService){
	$scope.pageVo = {designerId:""};
	$scope.theme = {};
	$scope.themeList = [];
	$scope.init = function(){
		angular.element(".time").datetimepicker({
			format: 'yyyy-mm-dd hh:ii:00 ',
			autoclose: true,
			todayBtn: true,
			todayHighligh:true
		});
		designerService.getAllDesigner({}).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.designerList = resp.result;
			}else{
				$scope.designerList = [];
			}
		});
		$scope.pageVo.current = 1;
	}
	
	$scope.upload = function(file,item){
		Upload.upload({
            url: ctx+'/file/upload.do?classify=theme',
            data: {file: file}
        }).then(function (resp) {
        	item.picture = resp.data.result.attachmentId;
        	themeService.updateTheme(item).then(function(data){
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
		$scope.theme = {};
		angular.element("#myModal").modal("show");
		if(item){
			$scope.theme = angular.copy(item);
			$scope.theme.designerId = item.designerId.toString();
			if(item.preSellStartTime){
				$scope.theme.preSellStartTime = $filter('date')(item.preSellStartTime,'yyyy-MM-dd HH:mm:ss');
			}
			if(item.preSellEndTime){
				$scope.theme.preSellEndTime = $filter('date')(item.preSellEndTime,'yyyy-MM-dd HH:mm:ss');
			}
		}else{
			$scope.theme.designerId = $scope.pageVo.designerId;
		}
	}
	
	$scope.update = function(){
		$scope.theme.error = null;
		if(!$scope.theme.themeName){
			$scope.theme.error="系列名称不能为空";
		}
		if(!$scope.theme.designerId){
			$scope.theme.error="设计师不能为空";
		}
		if(!$scope.theme.error){
			themeService.updateTheme($scope.theme).then(function(data){
				if(data.data.success){
					$scope.theme = {};
					angular.element("#myModal").modal("hide");
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.add = function(){
		$scope.theme.error = null;
		if(!$scope.theme.themeName){
			$scope.theme.error="系列名称不能为空";
		}
		if(!$scope.theme.designerId){
			$scope.theme.error="设计师不能为空";
		}
		if(!$scope.theme.error){
			themeService.addTheme($scope.theme).then(function(data){
				if(data.data.success){
					$scope.theme = {};
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
		themeService.getThemes($scope.pageVo).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.themeList = resp.result;
				$scope.pageVo = resp.pageVo;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	$scope.delTheme = function(item,index){
		if(item.id){
			themeService.delTheme(item.id).then(function(data){
				if(data.data.success){
					$scope.themeList.splice(index,1);
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
		
	}
	$scope.init();
	
	$scope.changeItem = function(item){
		themeService.updateTheme(item).then(function(data){
			if(!data.data.success){
				alert(data.data.respMessage);
			}
		});
	}
	
	$scope.exports = function(){
		angular.element("#exportform").submit();
	}
});