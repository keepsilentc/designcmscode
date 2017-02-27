tcApp.controller("productSizeColorStr",function($scope,$window,$cookies,Upload,productSizeColorService,colorService,sizeService,commonService){
			$scope.pageVo = {};
			$scope.productSizeColorList = [];
			$scope.colorList = [];
			$scope.sizeList = [];
			$scope.init = function(){
//				if($cookies.getObject("productStr.jsp")){
//					var tmp = $cookies.getObject("productStr.jsp");
//					tmp.show = true;
//					$cookies.putObject("productStr.jsp",tmp,{path:'/design/cms'});
//				}
				var tmp = angular.fromJson(sessionStorage.getItem("productStr.jsp"));
				if(tmp){
					tmp.show = true;
					sessionStorage.setItem("productStr.jsp",angular.toJson(tmp));
				}; 
				
				colorService.getAllColor(null).then(function(data){
					var resp = data.data;
					if(resp.success){
						$scope.colorList = resp.result;
					}else{
						alert(data.data.respMessage);
					}
				});
				
				commonService.getAllSizeType().then(function(data){
					if(data.data.success){
						var resp = data.data;
						$scope.sizeTypeList = resp.result;
					}
				});
				commonService.getAllSizeCountry().then(function(data){
					if(data.data.success){
						var resp = data.data;
						$scope.sizeCountryList = resp.result;
					}
				});
				
				sizeService.getAllSize({}).then(function(data){
					var resp = data.data;
					if(resp.success){
						$scope.sizeList = resp.result;
					}else{
						alert(data.data.respMessage);
					}
				});
				
				$scope.pageVo.productNo = angular.element("#productNo").val();
				$scope.pageVo.current = 1;
			}
			
			$scope.init();
			
			$scope.search = function(){
				var $btn = angular.element("#search").button('loading');
				productSizeColorService.getProductSizeColors($scope.pageVo).then(function(data){
					var resp = data.data;
					if(resp.success){
						$scope.productSizeColorList = resp.result;
						$scope.pageVo = resp.pageVo;
					}else{
						alert(data.data.respMessage);
					}
					$btn.button('reset');
				});
			}
			
			$scope.openModal = function(item){
				$scope.$broadcast("to_openModal",item);
			}
			
			$scope.upload = function(file,productSizeColor){
				Upload.upload({
		            url: ctx+'/file/upload.do?classify=productSizeColor',
		            data: {file: file}
		        }).then(function (resp) {
		        	productSizeColor.picture = resp.data.result.attachmentId;
		        	productSizeColorService.updateProductSizeColor(productSizeColor).then(function(data){
		        		if(!data.data.success){
							alert(data.data.respMessage);
						}
		        	});
		        }, function (resp) {
		            console.log('Error status: ' + resp.status);
		        }, function (evt) {
		            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//		            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
		        });
			}
			
			$scope.delProductSizeColor = function(item){
				productSizeColorService.delProductSizeColor(item.id).then(function(data){
					var resp = data.data;
					if(resp.success){
						$scope.search();
					}else{
						alert(data.data.respMessage);
					}
				});
			}
			
			$scope.addProductSizeColor = function(item,index){
				productSizeColorService.quickAdd(item).then(function(data){
					var resp = data.data;
					if(resp.success){
						$scope.productSizeColorList.splice(index,0,resp.result);
						while($scope.productSizeColorList.length>10){
							$scope.productSizeColorList.pop();
						}
//						console.log($scope.productSizeColorList.length);
					}else{
						alert(data.data.respMessage);
					}
				
				});
			}
			
			$scope.changeItem = function(item){
				productSizeColorService.updateProductSizeColor(item).then(function(data){
					if(!data.data.success){
						alert(data.data.respMessage);
					}
				});
			}
			
			$scope.exports = function(){
				$("#exportform :input[name='productNo']").val(angular.element("#productNo").val());
				angular.element("#exportform").submit();
			}
			
			$scope.toimport = function(){
				var newWindowRef = $window.open(ctx+"/productSizeColor/import/index.do", "New Window", "width=1000px,height=800px,top=150px,left=180px,toolbar=no,menubar=no,location=no,resizable=no");
			}
			
			$scope.downloadTemplate = function(){
				var form = document.createElement("form");        
				form.action = ctx+"/productSizeColor/downloadTemplate.do";        
				form.method = "post";        
				form.style.display = "none";
			    document.body.appendChild(form);
			    form.submit();
			}
		});