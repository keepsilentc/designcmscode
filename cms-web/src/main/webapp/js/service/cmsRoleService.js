tcApp.service("cmsRoleService",function($http){
		this.getCmsRoles = function(){
			return $http.get(ctx+"/role/search.do");
		};
		this.addCmsRole = function(cmsRole){
			return $http.post(ctx+"/role/add.do",cmsRole);
		};
		this.updateCmsRole = function(cmsRole){
			return $http.post(ctx+"/role/update.do",cmsRole);
		};
		this.getRoleResources = function(roleId){
			return $http.post(ctx+"/role/getRoleResources.do",roleId);
		};
		this.saveRoleResources = function(data){
			return $http.post(ctx+"/role/saveRoleResources.do",data);
		};
		this.getAllRole = function(){
			return $http.get(ctx+"/role/getAllRole.do");
		}
});