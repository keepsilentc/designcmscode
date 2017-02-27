tcApp.service("cmsResourceService",function($http){
		this.getAllResource = function(){
			return $http.get(ctx+"/resource/getAllResource.do");
		};
});