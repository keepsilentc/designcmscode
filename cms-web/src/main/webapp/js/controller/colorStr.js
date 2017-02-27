tcApp.controller("colorStr",function($scope,Upload,colorService){
			$scope.colorName = null;
			$scope.colorList = [];
			$scope.addColor = function(){
				$scope.colorList.push({});
			}
			$scope.save = function(obj){
				if(obj.id){
					colorService.updateColor(obj).then(function(data){
						if(!data.data.success){
							alert(data.data.respMessage);
						}
					});
				}else if(obj.name&&obj.picture){
					colorService.addColor(obj).then(function(data){
						if(!data.data.success){
							alert(data.data.respMessage);
						}
					});
				}
			}
			$scope.search = function(){
				var $btn = angular.element("#search").button('loading');
				colorService.getAllColor($scope.colorName).then(function(data){
					if(data.data.success){
						$scope.colorList = data.data.result;
					}else{
						alert(data.data.respMessage);
					}
					$btn.button("reset");
				});
			}
			$scope.search();
			$scope.upload = function(file,color){
				Upload.upload({
		            url: ctx+'/file/upload.do?classify=color&temp=true',
		            data: {file: file}
		        }).then(function (resp) {
		        	color.picture = resp.data.result.attachmentId;
		        }, function (resp) {
		            console.log('Error status: ' + resp.status);
		        }, function (evt) {
//		            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//		            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
		        });
			}
		});