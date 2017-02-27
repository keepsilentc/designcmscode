tcApp.controller("returnStr",function($scope,returnService){
	$scope.pageVo = {};	
	$scope.returnList = [];
	$scope.returnLogList = [];
	$scope.orderNo = null;
	
	$scope.showDetail = function(orderNo){
		$scope.orderNo = orderNo;
		angular.element("#orderDetail_Modal").modal("show");
	}
	
	$scope.init = function(){
		angular.element(".time").datetimepicker({
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			todayHighligh:true,
			minView:'month',
	        maxView:'decade'
		});
		$scope.pageVo.current = 1;
	}
	
	$scope.search = function(){
		var $btn = angular.element("#search").button('loading');
		returnService.getReturns($scope.pageVo).then(function(data){
			var resp = data.data;
			if(resp.success){
				$scope.returnList = resp.result;
				$scope.pageVo = resp.pageVo;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button('reset');
		});
	}
	
	$scope.init();
	
	$scope.follow = function(returnNo){
		if(returnNo==null){
			return ;
		}
		$scope.returnLogList = [];
		angular.element("#follow_Modal").modal("show");
		returnService.follow(returnNo).then(function(data){
			var resp = data.data;
			if(resp.success){
				$scope.returnLogList = resp.result;
				
			}else{
				alert(data.data.respMessage);
			}
		});
	}
	
	$scope.toApprove = function(returnNo){
		if(!returnNo){
			alert("fail");
		}else{
			var form = document.createElement("form");        
			form.action = ctx+"/return/approve/index.do";        
			form.method = "post";        
			form.style.display = "none";
		    var input = document.createElement("input");
		    input.type= "hidden";
		    input.name="returnNo";
		    input.value=returnNo;
		    form.appendChild(input);
		    document.body.appendChild(form);
		    form.submit();
		}
	}
});