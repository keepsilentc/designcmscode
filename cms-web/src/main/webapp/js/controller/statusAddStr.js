tcApp.controller("statusAddStr",function($scope,statusService,designerService,themeService){
	$scope.status = {};
	$scope.init = function(){
		KindEditor.ready(function(K) {
			window.editor = K.create('#test',{
				uploadJson : ctx+'/file/kindeditorupload.do',
                allowFileManager : false,
                items:[
                       'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
                       'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                       'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                       'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                       'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                       'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
                       'insertfile', 'table', 'hr', 'baidumap', 'pagebreak',
                       'link', 'unlink'
               ]
                });
		});
		themeService.getAllTheme(null).then(function(data){
			if(data.data.success){
				$scope.themeList = data.data.result;
			}else{
				alert(data.data.respMessage);
			}
		});
	}
	$scope.init();
	
	$scope.$on("to_openModal",function(event,data){
		$scope.openModal(data);
	});
	
	$scope.openModal = function(item){
		$scope.status = {};
		editor.html("");
		angular.element("#myModal").modal("show");
		if(item){
			$scope.status = angular.copy(item);
			statusService.getStatusDetailTxt(item.statusDetailUrl).then(function(data){
				if(data.data.success){
					editor.html(data.data.result);
				}else{
					alert(data.data.respMessage);
				}
			});
			
		}else{
			$scope.status.statusTypeId = 1;
		}
	}
	
	$scope.update = function(){
		$scope.status.error = null;
		if(!$scope.status.themeId){
			$scope.status.error = "请选择系列";
		}
		if(!$scope.status.statusTypeId){
			$scope.status.error = "动态类型不能为空";
		}
		if(!$scope.status.statusName){
			$scope.status.error = "动态名称不能为空";
		}
		if(!$scope.status.error){
			editor.sync();
			$scope.status.statusDetail = angular.element("#test").val();
			statusService.updateStatus($scope.status).then(function(data){
				if(data.data.success){
					$scope.status = {};
					angular.element("#myModal").modal("hide");
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.add = function(){
		$scope.status.error = null;
		if(!$scope.status.themeId){
			$scope.status.error = "请选择系列";
		}
		if(!$scope.status.statusTypeId){
			$scope.status.error = "动态类型不能为空";
		}
		if(!$scope.status.statusName){
			$scope.status.error = "动态名称不能为空";
		}
		if(!$scope.status.error){
			editor.sync();
			$scope.status.statusDetail = angular.element("#test").val();
			statusService.addStatus($scope.status).then(function(data){
				if(data.data.success){
					$scope.status = {};
					angular.element("#myModal").modal("hide");
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
});