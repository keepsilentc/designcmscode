tcApp.service("statusService",function($http){
		this.getStatuss = function(vo){
			return $http.post(ctx+"/status/search.do",vo);
		};
		this.addStatus = function(status){
			return $http.post(ctx+"/status/add.do",status);
		};
		this.updateStatus = function(status){
			return $http.post(ctx+"/status/update.do",status);
		};
		this.getStatusDetailTxt = function(url){
			return $http.post(ctx+"/status/getStatusDetailTxt.do",url);
		}
});