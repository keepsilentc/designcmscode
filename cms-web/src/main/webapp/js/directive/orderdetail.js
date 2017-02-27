tcApp.directive("orderdetail",function(orderService){
	return {
		restrict:'E',
		templateUrl:ctx+'/template/orderdetail.html',
		replace:true,
		scope: {
			orderno:'='
		}, 
        link:function(scope,element,attrs){
        	scope.ctx = ctx;
        	scope.getData = function(){
        		orderService.getOrderInfo(scope.orderno).then(function(data){
        			var resp = data.data;
        			if(resp.success){
        				scope.orderInfo = resp.result;
        				scope.address = angular.fromJson(scope.orderInfo.address);
        			}else{
        				alert(data.data.respMessage);
        			}
        		});
        	};
        	
        	scope.$watch('orderno', function(newValue,oldValue, scope) {
        		if(newValue){
        			scope.getData();
        		}
            });
        	
        }
	};
});