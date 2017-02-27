tcApp.service("commonService",function($http){
	this.getAllCountry = function(){
		return $http.get(ctx+"/common/getAllCountry.do");
	};
	this.getAllBrand = function(){
		return $http.get(ctx+"/common/getAllBarnd.do");
	};
	this.getAllSizeType = function(){
		return $http.get(ctx+"/common/getAllSizeType.do");
	};
	this.getAllSizeCountry = function(){
		return $http.get(ctx+"/common/getAllSizeCountry.do");
	};
});