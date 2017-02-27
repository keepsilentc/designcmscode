tcApp.service("categoryService",function($http){
		this.getCategorys = function(id){
			return $http.post(ctx+"/category/search.do",id);
		};
		this.addCategory = function(category){
			return $http.post(ctx+"/category/add.do",category);
		};
		this.updateCategory = function(category){
			return $http.post(ctx+"/category/update.do",category);
		};
		this.delCategory = function(id){
			return $http.post(ctx+"/category/del.do",id);
		}
});