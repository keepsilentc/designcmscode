tcApp.controller("productDetailStr",function($scope,Upload,productService){
		$scope.master = {};
		$scope.imgList = [];
		$scope.imgRemoveList = [];
		$scope.$on("to_openupdateImgModal",function(event,item){
			$scope.imgRemoveList = [];
			angular.element("#myModal_1").modal("show");
			if(item){
				$scope.master = item;
				productService.getImages(item.productNo).then(function(data){
					if(data.data.success){
						$scope.imgList = data.data.result;
					}else{
						alert(data.data.respMessage);
					}
				});
			}
		});
		$scope.save = function(){
			if($scope.imgRemoveList.length>0){
				productService.removeProductDetails($scope.imgRemoveList).then(function(data){
					if(!data.data.success){
						alert(data.data.respMessage);
					}
				});
			}
			if($scope.imgList.length>0){
				var $btn = angular.element("#saveDetails").button('loading');
				productService.saveProductDetails($scope.imgList).then(function(data){
					if(data.data.success){
						angular.element("#myModal_1").modal("hide");
					}else{
						alert(data.data.respMessage);
					}
					$btn.button("reset");
				});
			}
		}
		$scope.setMaster = function(item,index){
			if(!item.picture){
				alert("主图不可设置为空图片");
				return;
			}
			var tmp ;
			tmp = item.picture;
			var masterPicture = $scope.master.picture;
			if(masterPicture){
				item.picture = masterPicture;
			}else{
				$scope.imgList.splice(index,1);
			}
			$scope.master.picture = tmp;
			productService.updateProduct($scope.master).then(function(data){
				if(data.data.success){
					if(item.id){
						return productService.updateProductDetail(item);
					}
				}else{
					alert(data.data.respMessage);
				}
			}).then(function(data){
				if(data){
					if(!data.data.success){
						alert("fail");
					}
				}
				
			});
		}
		$scope.remove = function(item,index){
			var tmp = $scope.imgList.splice(index,1);
			if(item.id){
				$scope.imgRemoveList.push(item.id);
			}
		}
		$scope.uploadPdImg = function(file,item){
			Upload.upload({
	            url: ctx+'/file/upload.do?classify=productDetail&temp=true',
	            data: {file: file}
	        }).then(function (resp) {
	        	if(resp.data.success){
	        		item.picture = resp.data.result.attachmentId;
	        	}else{
					alert(data.data.respMessage);
				}
	        }, function (resp) {
	            console.log('Error status: ' + resp.status);
	        }, function (evt) {
	            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//	            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
	        });
		}
		$scope.upload = function(files){
			if (files && files.length) {
		        for (var i = 0; i < files.length; i++) {
		        	Upload.upload({
			            url: ctx+'/file/upload.do?classify=productDetail&temp=true',
			            data: {file: files[i]}
			        }).then(function (resp) {
			        	if(resp.data.success){
			        		$scope.imgList.push({picture:resp.data.result.attachmentId,orderBy:1,productNo:$scope.master.productNo});
			        	}else{
							alert(data.data.respMessage);
						}
			        }, function (resp) {
			            console.log('Error status: ' + resp.status);
			        }, function (evt) {
			            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//			            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
			        });
		        }
			}
		}
});