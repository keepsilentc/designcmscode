tcApp.service("cmsUserService",function($http){
		this.getCmsUsers = function(vo){
			return $http.post(ctx+"/cmsuser/search.do",vo);
		};
		this.addCmsUser = function(cmsUser){
			return $http.post(ctx+"/cmsuser/add.do",cmsUser);
		};
		this.updateCmsUser = function(cmsUser){
			return $http.post(ctx+"/cmsuser/update.do",cmsUser);
		};
		this.delCmsUser = function(id){
			return $http.post(ctx+"/cmsuser/del.do",id);
		};
		this.getUserRoles = function(id){
			return $http.post(ctx+"/cmsuser/getUserRoles.do",id);
		};
		this.saveUserRoles = function(data){
			return $http.post(ctx+"/cmsuser/saveUserRoles.do",data);
		};
});