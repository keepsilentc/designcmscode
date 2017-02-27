tcApp.service("payLoadService",function($http){
	this.send = function(param){
		return $http.post(ctx+"/payLoad/doUserDefined.do",param);
	};
});