tcApp.service("productSizeColorService",function($http){
		this.getProductSizeColors = function(vo){
			return $http.post(ctx+"/productSizeColor/search.do",vo);
		};
		this.addProductSizeColor = function(productSizeColor){
			return $http.post(ctx+"/productSizeColor/add.do",productSizeColor);
		};
		this.updateProductSizeColor = function(productSizeColor){
			return $http.post(ctx+"/productSizeColor/update.do",productSizeColor);
		};
		this.delProductSizeColor = function(productSizeColorId){
			return $http.post(ctx+"/productSizeColor/del.do",productSizeColorId);
		};
		this.quickAdd = function(vo){
			return $http.post(ctx+"/productSizeColor/quickAdd.do",vo);
		};
		
});