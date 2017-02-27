tcApp.service("sizeService",function($http){
		this.getAllSize = function(vo){
			return $http.post(ctx+"/size/search.do",vo);
		};
		this.addSize = function(size){
			return $http.post(ctx+"/size/add.do",size);
		};
		this.updateSize = function(size){
			return $http.post(ctx+"/size/update.do",size);
		};
		this.delSize = function(size){
			return $http.post(ctx+"/size/del.do",size);
		};
		this.getAllSizeCountry = function(){
			return $http.get(ctx+"/size/getAllSizeCountry.do");
		};
});