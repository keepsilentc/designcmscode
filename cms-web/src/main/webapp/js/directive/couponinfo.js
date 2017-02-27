tcApp.directive("couponinfo",function(couponService){
	return {
		restrict:'E',
		templateUrl:ctx+'/template/couponInfo.html',
		replace:true,
		scope: {
			couponId:'='
		}, 
        link:function(scope,element,attrs){
        	
        	scope.getData = function(){
        		couponService.getCouponInfo(scope.couponId).then(function(data){
        			var resp = data.data;
        			if(resp.success){
        				scope.couponInfo = resp.result;
        			}else{
        				alert(data.data.respMessage);
        			}
        		});
        	};
        	
        	scope.$watch('couponId', function(newValue,oldValue, scope) {
        		if(newValue){
        			scope.getData();
        		}
            });
        	
        }
	};
});