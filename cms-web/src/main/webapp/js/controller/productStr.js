tcApp.controller("productStr",function($scope,$window,$cookies,Upload,productService,commonService,designerService,themeService,categoryService,$filter){
			$scope.pageVo = {designerId:"",themeId:"",categoryId:"",order:"1"};
			$scope.countryList = [];
			$scope.brandList = [];
			$scope.designerList = [];
			$scope.themeList = [];
			$scope.productList = [];
			$scope.showTime = false;
			
			$scope.init = function(){
				
				angular.element(".time").datetimepicker({
					format: 'yyyy-mm-dd hh:ii:00 ',
					autoclose: true,
					todayBtn: true,
					todayHighligh:true
				});
				commonService.getAllCountry().then(function(data){
					if(data.data.success){
						var resp = data.data;
						$scope.countryList = resp.result;
					}
				});
				designerService.getAllDesigner(null).then(function(data){
					if(data.data.success){
						var resp = data.data;
						$scope.designerList = resp.result;
					}else{
						alert(data.data.respMessage);
					}
				});
//				commonService.getAllBrand().then(function(data){
//					if(data.data.success){
//						var resp = data.data;
//						$scope.brandList = resp.result;
//					}
//				});
//				if($cookies.getObject("productStr.jsp")&&$cookies.getObject("productStr.jsp").show){
//					var tmp = $cookies.getObject("productStr.jsp");
//					tmp.show = false;
//					$scope.pageVo = tmp;
//					$scope.search();
//					$cookies.putObject("productStr.jsp",tmp,{path:'/design/cms'});
//				}else{
//					$scope.pageVo.current = 1;
//				}
				
				var tmp = angular.fromJson(sessionStorage.getItem("productStr.jsp"));
				if(tmp&&tmp.show){
					$scope.pageVo = tmp;
					tmp.show = false;
					sessionStorage.setItem("productStr.jsp",angular.toJson(tmp));
					$scope.search();
				}else{
					$scope.pageVo.current = 1;
				}; 
			}
			
			$scope.changeCountry = function(){
				$scope.pageVo.designerId = null;
				$scope.pageVo.themeId = null;
				if(!$scope.pageVo.countryId&&!$scope.pageVo.brandId){
					$scope.designerList = [];
					$scope.themeList = [];
				}else{
					designerService.getAllDesigner({brandId:$scope.pageVo.brandId,countryId:$scope.pageVo.countryId}).then(function(data){
						if(data.data.success){
							var resp = data.data;
							$scope.designerList = resp.result;
						}else{
							alert(data.data.respMessage);
						}
					});
				}
			}
			
//			$scope.changeBrand = function(){
//				$scope.pageVo.designerId = null;
//				$scope.pageVo.themeId = null;
//				if(!$scope.pageVo.countryId&&!$scope.pageVo.brandId){
//					$scope.designerList = [];
//					$scope.themeList = [];
//				}else{
//					designerService.getAllDesigner({brandId:$scope.pageVo.brandId,countryId:$scope.pageVo.countryId}).then(function(data){
//						if(data.data.success){
//							var resp = data.data;
//							$scope.designerList = resp.result;
//							$scope.themeList = [];
//						}else{
//							alert(data.data.respMessage);
//						}
//					});
//				}
//			}
			
			$scope.changeDesigner = function(){
				$scope.pageVo.themeId = null;
				if(!$scope.pageVo.designerId){
					$scope.themeList = [];
				}else{
					themeService.getAllTheme($scope.pageVo.designerId).then(function(data){
						if(data.data.success){
							var resp = data.data;
							$scope.themeList = resp.result;
						}else{
							alert(data.data.respMessage);
						}
					});
				}
				
			}
			
			$scope.search = function(){
//				$cookies.putObject("productStr.jsp",$scope.pageVo,{path:'/design/cms'});
				sessionStorage.setItem("productStr.jsp",angular.toJson($scope.pageVo)); 
				var $btn = angular.element("#search").button('loading');
				productService.getProducts($scope.pageVo).then(function(data){
					var resp = data.data;
					if(resp.success){
						$scope.productList = resp.result;
						$scope.pageVo = resp.pageVo;
						if($scope.pageVo.state==20){
							$scope.showTime = true;
						}else{
							$scope.showTime = false;
						}
					}else{
						alert(data.data.respMessage);
					}
					$btn.button('reset');
				});
			}
			$scope.init();
			
			$scope.openModal = function(item){
				$scope.$broadcast("to_openModal",item);
			}
			
			$scope.openupdateImgModal = function(item){
				$scope.$broadcast("to_openupdateImgModal",item);
			}
			
			$scope.delProduct = function(item){
				if(window.confirm("确认删除吗？")){
					productService.delProduct(item.id).then(function(data){
						var resp = data.data;
						if(resp.success){
							$scope.search();
						}else{
							alert(data.data.respMessage);
						}
					});
				}
			}
			
			$scope.toProductSizeColor = function(productNo){
				if(!productNo){
					alert("fail");
				}else{
					var form = document.createElement("form");        
					form.action = ctx+"/productSizeColor/index.do";        
					form.method = "post";        
					form.style.display = "none";
				    var input = document.createElement("input");
				    input.type= "hidden";
				    input.name="productNo";
				    input.value=productNo;
				    form.appendChild(input);
				    document.body.appendChild(form);
				    form.submit();
				}
			}
			
			$scope.changeItem = function(item){
				productService.updateProduct(item).then(function(data){
					if(!data.data.success){
						alert(data.data.respMessage);
					}
				});
			}
			
			$scope.exports = function(){
				angular.element("#exportform").submit();
			}
			
			$scope.downloadTemplate = function(){
				var form = document.createElement("form");        
				form.action = ctx+"/product/downloadTemplate.do";        
				form.method = "post";        
				form.style.display = "none";
			    document.body.appendChild(form);
			    form.submit();
			}
			
			$scope.toimport = function(){
				var newWindowRef = $window.open(ctx+"/product/import/index.do", "New Window", "width=1000px,height=800px,top=150px,left=180px,toolbar=no,menubar=no,location=no,resizable=no");
			}
		});