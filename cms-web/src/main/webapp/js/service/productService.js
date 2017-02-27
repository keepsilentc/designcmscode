tcApp.service("productService",function($http){
		this.getProductList = function(vo){
			return $http.post(ctx+"/product/getProductList.do",vo);
		};
		this.getProducts = function(vo){
			return $http.post(ctx+"/product/search.do",vo);
		};
		this.addProduct = function(product){
			return $http.post(ctx+"/product/add.do",product);
		};
		this.updateProduct = function(product){
			return $http.post(ctx+"/product/update.do",product);
		};
		this.delProduct = function(productId){
			return $http.post(ctx+"/product/del.do",productId);
		};
		this.getImages = function(productId){
			return $http.post(ctx+"/product/getImages.do",productId);
		};
		this.updateProductDetail = function(productDetail){
			return $http.post(ctx+"/product/updateProductDetail.do",productDetail);
		};
		this.saveProductDetails = function(productDetailList){
			return $http.post(ctx+"/product/saveProductDetails.do",productDetailList);
		};
		this.removeProductDetails = function(ids){
			return $http.post(ctx+"/product/removeProductDetails.do",ids);
		};
});