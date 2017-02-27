tcApp.controller("statusStr",function($scope,Upload,statusService,designerService,themeService){
	$scope.pageVo = {designerId:null};
	$scope.themeList = [];
	$scope.designerList = [];
	$scope.init = function(){
		designerService.getAllDesigner({}).then(function(data){
			if(data.data.success){
				$scope.designerList = data.data.result;
			}else{
				alert(data.data.respMessage);
			}
		});
		$scope.getTheme();
		$scope.pageVo.current = 1;
	}
	
	$scope.changeTheme = function(){
		if($scope.pageVo.themeId){
			angular.forEach($scope.themeList,function(data,index){
				if(data.id==$scope.pageVo.themeId){
					$scope.pageVo.designerId = data.designerId.toString();
				}
			});
		}
	}
	
	$scope.getTheme = function(){
		themeService.getAllTheme($scope.pageVo.designerId).then(function(data){
			if(data.data.success){
				$scope.pageVo.themeId = "";
				$scope.themeList = data.data.result;
			}else{
				alert(data.data.respMessage);
			}
		});
	}
	
	$scope.openModal = function(item){
		$scope.$broadcast("to_openModal",item);
	}
	
	$scope.search = function(){
		var $btn = angular.element("#search").button('loading');
		statusService.getStatuss($scope.pageVo).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.statusList = resp.result;
				$scope.pageVo = resp.pageVo;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	
	$scope.init();
	
	$scope.uploadPicture = function(file,item){
		Upload.upload({
            url: ctx+'/file/upload.do?classify=status',
            data: {file: file}
        }).then(function (resp) {
        	item.picture = resp.data.result.attachmentId;
        	statusService.updateStatus(item).then(function(data){
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
	
	$scope.changeItem = function(item){
		statusService.updateStatus(item).then(function(data){
			if(!data.data.success){
				alert(data.data.respMessage);
			}
		});
	}
	
	$scope.uploadInsidePicture = function(file,item){
		Upload.upload({
            url: ctx+'/file/upload.do?classify=status',
            data: {file: file}
        }).then(function (resp) {
        	item.insidePicture = resp.data.result.attachmentId;
        	statusService.updateStatus(item).then(function(data){
				if(!data.data.success){
					alert(data.data.respMessage);
				}
			});
        }, function (resp) {
//            console.log('Error status: ' + resp.status);
        }, function (evt) {
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
        });
	}
	
});