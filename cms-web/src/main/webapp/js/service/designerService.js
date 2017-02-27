tcApp.service("designerService",function($http){
	this.getAllDesigner = function(param){
		return $http.post(ctx+"/designer/getAllDesigner.do",param);
	};
	this.getDesigners = function(vo){
		return $http.post(ctx+"/designer/search.do",vo);
	};
	this.addDesigner = function(designer){
		return $http.post(ctx+"/designer/add.do",designer);
	}
	this.updateDesigner = function(designer){
		return $http.post(ctx+"/designer/update.do",designer);
	}
	this.delDesigner = function(id){
		return $http.post(ctx+"/designer/del.do",id);
	}
});