tcApp.controller("productAddStr",function($scope,$filter,productService,commonService,designerService,themeService,categoryService){
			$scope.product = {};
			$scope.designerList = [];
			$scope.themeList = [];
			$scope.categoryList = [];
			$scope.categorylvel = [{id:null,name:"总分类"}];
			
			$scope.changeCountry = function(){
				$scope.product.designerId =null;
				$scope.product.themeId =null;
				$scope.getAllDesigner();
				$scope.themeList = [];
			}
			
			$scope.changeBrand = function(){
				$scope.product.designerId =null;
				$scope.product.themeId =null;
				$scope.getAllDesigner();
				$scope.themeList = [];
			}
			
			$scope.changeDesigner = function(){
				$scope.product.themeId =null;
				$scope.product.designerName = angular.element("#designer_1 option:selected").text();
				angular.forEach($scope.designerList,function(data,index){
					if(data.id==$scope.product.designerId){
						$scope.product.countryId = data.countryId;
						$scope.product.brandId = data.brandId;
					}
				});
				$scope.getAllTheme();
			}
			
			$scope.index = function(level,index){
				$scope.categorylvel.splice(index+1,$scope.categorylvel.length-1-index);
				$scope.searchCategory(level.id);
			}
			$scope.changeCategory = function(category){
				$scope.categorylvel.push({id:category.id,name:category.categoryName});
				$scope.searchCategory(category.id);
			}
			$scope.searchCategory = function(parentId){
				categoryService.getCategorys(parentId).then(function(data){
					if(data.data.success){
						$scope.categoryList = data.data.result;
					}else{
						alert(data.data.respMessage);
					}
				});
			}
			$scope.selectCategory = function(category){
				$scope.product.categoryName = category.categoryName;
				$scope.product.cateGoryId = category.id;
				$scope.showcategory = false;
			}
			
			$scope.$on("to_openModal",function(event,data){
				$scope.openModal(data);
			});
			
			$scope.openModal = function(item){
				$scope.categorylvel = [{id:null,name:"总分类"}];
				angular.element("#myModal").modal("show");
				$scope.product = {};
				$scope.designerList = [];
				$scope.themeList = [];
				categoryService.getCategorys(null).then(function(data){
					if(data.data.success){
						$scope.categoryList = data.data.result;
					}else{
						alert(data.data.respMessage);
					}
				});
				if(item&&item.id){
					$scope.product = angular.copy(item);
					$scope.product.designerId = item.designerId.toString();
					$scope.product.themeId = item.themeId.toString();
					console.log($scope.product);
					if($scope.product.state==20){
						$scope.product.preSellStartTime = $filter('date')(item.preSellStartTime,'yyyy-MM-dd HH:mm:ss');
						$scope.product.preSellEndTime = $filter('date')(item.preSellEndTime,'yyyy-MM-dd HH:mm:ss');
					}
					
					$scope.getAllDesigner();
					$scope.getAllTheme();
				}else{
					$scope.product.isEnable = 1;
					$scope.product.isRepresentative = 0;
					$scope.product.isNew = 1;
					$scope.product.state = 10;
					$scope.getAllDesigner();
				}
			}
			$scope.add = function(){
				$scope.product.error = null;
				if(!$scope.product.price){
					$scope.product.error="产品价格不能为空且不能小于0";
				}
				if(!$scope.product.productNo){
					$scope.product.error="产品编号不能为空";
				}
				if(!$scope.product.productName){
					$scope.product.error="产品名称不能为空";
				}
				if(!$scope.product.cateGoryId){
					$scope.product.error="分类不能为空";
				}
				if(!$scope.product.themeId){
					$scope.product.error="系列不能为空";
				}
				if(!$scope.product.designerId){
					$scope.product.error="设计师不能为空";
				}
				if(!$scope.product.brandId){
					$scope.product.error="品牌不能为空";
				}
				if(!$scope.product.countryId){
					$scope.product.error="国家不能为空";
				}
				
				if(!$scope.product.error){
					var $btn = angular.element("#save").button('loading');
					console.log($scope.product);
					productService.addProduct($scope.product).then(function(data){
						if(data.data.success){
							angular.element("#myModal").modal("hide");
							$scope.search();
						}else{
							alert(data.data.respMessage);
						}
						$btn.button("reset");
					});
				}
			}
			
			$scope.update = function(){
				$scope.product.error = null;
				if(!$scope.product.originPrice){
					$scope.product.error="产品原价不能为空且不能小于0";
				}
				if(!$scope.product.price){
					$scope.product.error="产品价格不能为空且不能小于0";
				}
				if(!$scope.product.productNo){
					$scope.product.error="产品编号不能为空";
				}
				if(!$scope.product.productName){
					$scope.product.error="产品名称不能为空";
				}
				if(!$scope.product.cateGoryId){
					$scope.product.error="分类不能为空";
				}
				if(!$scope.product.themeId){
					$scope.product.error="系列不能为空";
				}
				if(!$scope.product.designerId){
					$scope.product.error="设计师不能为空";
				}
				if(!$scope.product.brandId){
					$scope.product.error="品牌不能为空";
				}
				if(!$scope.product.countryId){
					$scope.product.error="国家不能为空";
				}
				if(!$scope.product.error){
					var $btn = angular.element("#save").button('loading');
					productService.updateProduct($scope.product).then(function(data){
						if(data.data.success){
							angular.element("#myModal").modal("hide");
							$scope.search();
						}else{
							alert(data.data.respMessage);
						}
						$btn.button("reset");
					});
				}
			}
			$scope.getAllDesigner = function(){
				designerService.getAllDesigner({brandId:$scope.product.brandId,countryId:$scope.product.countryId}).then(function(data){
					if(data.data.success){
						var resp = data.data;
						$scope.designerList = resp.result;
					}else{
						alert(data.data.respMessage);
					}
				});
			}
			$scope.getAllTheme = function(){
				themeService.getAllTheme($scope.product.designerId).then(function(data){
					if(data.data.success){
						var resp = data.data;
						$scope.themeList = resp.result;
					}else{
						alert(data.data.respMessage);
					}
				});
			}
		});