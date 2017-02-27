tcApp.directive("userinfo",function(userService){
	return {
		restrict:'E',
		templateUrl:ctx+'/template/userInfo.html',
		replace:true,
		scope: {
			userNo:'='
		}, 
        link:function(scope,element,attrs){
        	scope.ctx = ctx;
        	scope.getData = function(){
        		userService.getUserByUserNo(scope.userNo).then(function(data){
        			var resp = data.data;
        			if(resp.success){
        				scope.userInfo = resp.result;
        			}else{
        				alert(data.data.respMessage);
        			}
        		});
        	};
        	
        	scope.$watch('userNo', function(newValue,oldValue, scope) {
        		if(newValue){
        			scope.getData();
        		}
            });
        	
        }
	};
});