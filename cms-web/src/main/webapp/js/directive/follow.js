tcApp.directive("follow",function(refundService){
	return {
		restrict:'E',
		templateUrl:ctx+'/template/follow.html',
		replace:true,
		scope: {
			refundNo:'='
		}, 
        link:function(scope,element,attrs){
        	scope.ctx = ctx;
        	scope.getData = function(){
        		refundService.follow(scope.refundNo).then(function(data){
        			var resp = data.data;
        			if(resp.success){
        				scope.refundLogList = resp.result;
        			}else{
        				alert(data.data.respMessage);
        			}
        		});
        	};
        	
        	scope.$watch('refundNo', function(newValue,oldValue, scope) {
        		if(newValue){
        			scope.getData();
        		}
            });
        	
        }
	};
});