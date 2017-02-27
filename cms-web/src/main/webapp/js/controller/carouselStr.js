tcApp.controller("carouselStr",function($scope,Upload,carouselService){
		$scope.searchVo = {};
		$scope.carouselList = [];
		$scope.carousel = {};
		$scope.init = function(){
			$scope.search();
		}
		
		$scope.add = function(){
			$scope.carousel.error = null;
			if(!$scope.carousel.type){
				$scope.carousel.error="轮播图类型不能为空";
			}
			if(!$scope.carousel.orderBy){
				$scope.carousel.error="排序不能为空或不能小于0";
			}
			if(!$scope.carousel.error){
				carouselService.addCarousel($scope.carousel).then(function(data){
					if(data.data.success){
						angular.element("#myModal").modal("hide");
						$scope.search();
					}else{
						alert(data.data.respMessage);
					}
				});
			}
		}
		$scope.delCarousel = function(id,index){
			if(id){
				carouselService.delCarousel(id).then(function(data){
					if(data.data.success){
						$scope.carouselList.splice(index,1);
					}else{
						alert(data.data.respMessage);
					}
				});
			}
		}
		
		$scope.update = function(){
			$scope.carousel.error = null;
			if(!$scope.carousel.type){
				$scope.carousel.error="轮播图类型不能为空";
			}
			if(!$scope.carousel.orderBy){
				$scope.carousel.error="排序不能为空或不能小于0";
			}
			if(!$scope.carousel.error){
				carouselService.updateCarousel($scope.carousel).then(function(data){
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
			carouselService.getAllCarousel($scope.searchVo).then(function(data){
				if(data.data.success){
					$scope.carouselList = data.data.result;
				}else{
					alert(data.data.respMessage);
				}
				$btn.button('reset');
			});
		}
		$scope.init();
		
		$scope.upload = function(file,item){
			Upload.upload({
	            url: ctx+'/file/upload.do?classify=carousel',
	            data: {file: file}
	        }).then(function (resp) {
	        	item.picture = resp.data.result.attachmentId;
	        	carouselService.updateCarousel(item).then(function(data){
	        		if(!data.data.success){
						alert(data.data.respMessage);
					}
	        	});
	        }, function (resp) {
	            console.log('Error status: ' + resp.status);
	        }, function (evt) {
//	            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//	            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
	        });
		}
		
		$scope.openModal = function(item){
			$scope.carousel = {};
			angular.element("#myModal").modal("show");
			if(item&&item.id){
				$scope.carousel = angular.copy(item);
				$scope.carousel.type=item.type.toString();
			}else{
				$scope.carousel.orderBy = 1;
				$scope.carousel.type= "10";
			}
		}
		
		$scope.changeItem = function(item){
			carouselService.updateCarousel(item).then(function(data){
				if(!data.data.success){
					alert(data.data.respMessage);
				}
			});
		}
});