tcApp.controller("categoryStr",function($scope,Upload,categoryService){
			$scope.parentId = null;		
			$scope.category = {};
			$scope.categorylvel = [{id:null,name:"总分类"}];
			$scope.categoryList = [];
			$scope.search = function(parentId){
				categoryService.getCategorys(parentId).then(function(data){
					if(data.data.success){
						$scope.categoryList = data.data.result;
					}else{
						alert(data.data.respMessage);
					}
				});
			}
			$scope.search($scope.parentId);
			$scope.upload = function(file,category){
				Upload.upload({
		            url: ctx+'/file/upload.do?classify=category',
		            data: {file: file}
		        }).then(function (resp) {
		        	category.picture = resp.data.result.attachmentId;
		        	categoryService.updateCategory(category).then(function(data){
		        		if(!data.data.success){
							alert(data.data.respMessage);
						}
		        	});
		        }, function (resp) {
		            console.log('Error status: ' + resp.status);
		        }, function (evt) {
//		            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//		            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
		        });
			}
			$scope.look = function(category){
				$scope.parentId = category.id;
				$scope.categorylvel.push({id:category.id,name:category.categoryName});
				$scope.search(category.id);
			}
			$scope.index = function(level,index){
				$scope.categorylvel.splice(index+1,$scope.categorylvel.length-1-index);
				$scope.parentId = level.id;
				$scope.search(level.id);
			}
			$scope.openModal = function(item){
				$scope.category = {};
				angular.element("#myModal").modal("show");
				if(item){
					$scope.category = angular.copy(item);
				}else{
					$scope.category.isEnable = 1;
					$scope.category.parentId = $scope.parentId;
				}
			}
			$scope.add = function(){
				$scope.category.error = null;
				if(!$scope.category.categoryName){
					$scope.category.error="分类名称不能为空";
				}
				if(!$scope.category.orderBy){
					$scope.category.error="排序不能为空且不能小于1";
				}
				if(!$scope.category.error){
					categoryService.addCategory($scope.category).then(function(data){
						if(data.data.success){
							angular.element("#myModal").modal("hide");
							$scope.search($scope.parentId);
						}else{
							alert(data.data.respMessage);
						}
					});
				}
			}
			
			$scope.update = function(){
				$scope.category.error = null;
				if(!$scope.category.categoryName){
					$scope.category.error="分类名称不能为空";
				}
				if(!$scope.category.orderBy){
					$scope.category.error="排序不能为空且不能小于1";
				}
				if(!$scope.category.error){
					categoryService.updateCategory($scope.category).then(function(data){
						if(data.data.success){
							angular.element("#myModal").modal("hide");
							$scope.search($scope.parentId);
						}else{
							alert(data.data.respMessage);
						}
					});
				}
			}
			
			$scope.delCategory = function(item,index){
				/*if(item.id){
					categoryService.delCategory(item.id).then(function(data){
						if(data.data.success){
							$scope.categoryList.splice(index,1);
							$scope.search($scope.parentId);
						}else{
							alert(data.data.respMessage);
						}
					});
				}*/
			}
			
			$scope.changeItem = function(item){
				categoryService.updateCategory(item).then(function(data){
					if(!data.data.success){
						alert(data.data.respMessage);
					}
				});
			}
			
		});