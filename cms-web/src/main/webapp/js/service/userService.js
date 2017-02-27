tcApp.service("userService",function($http){
		this.search = function(vo){
			return $http.post(ctx+"/user/search.do",vo);
		};
		this.getUserByUserNo = function(userNo){
			return $http.post(ctx+"/user/getUserByUserNo.do",userNo);
		};
});