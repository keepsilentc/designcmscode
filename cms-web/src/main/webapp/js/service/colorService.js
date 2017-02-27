tcApp.service("colorService",function($http){
		this.getAllColor = function(name){
			return $http.get(ctx+"/color/search.do",{params:{name:name}});
		};
		this.addColor = function(color){
			return $http.post(ctx+"/color/add.do",color);
		};
		this.updateColor = function(color){
			return $http.post(ctx+"/color/update.do",color);
		};
});