tcApp.controller("refundStr",function($scope,refundService){
	$scope.pageVo = {};	
	$scope.refundList = [];
	$scope.refundLogList = [];
	$scope.orderNo = null;
	$scope.refundNo = null;
	$scope.userNo = null;
	
	$scope.showDetail = function(orderNo){
		$scope.orderNo = orderNo;
		angular.element("#orderDetail_Modal").modal("show");
	}
	
	$scope.showUserInfo = function(userNo){
		$scope.userNo = userNo;
		angular.element("#userInfo_Modal").modal("show");
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
		refundService.getRefunds($scope.pageVo).then(function(data){
			var resp = data.data;
			if(resp.success){
				$scope.refundList = resp.result;
				$scope.pageVo = resp.pageVo;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button('reset');
		});
	}
	
	$scope.init();
	
	$scope.follow = function(refundNo){
		$scope.refundNo = refundNo;
		angular.element("#follow_Modal").modal("show");
	}
	
	$scope.toApprove = function(refundNo){
		if(!refundNo){
			alert("fail");
		}else{
			var form = document.createElement("form");        
			form.action = ctx+"/refund/approve/index.do";        
			form.method = "post";        
			form.style.display = "none";
		    var input = document.createElement("input");
		    input.type= "hidden";
		    input.name="refundNo";
		    input.value=refundNo;
		    form.appendChild(input);
		    document.body.appendChild(form);
		    form.submit();
		}
	}
	
	$scope.doRefund = function(refundNo){
		refundService.doRefund({"refundNo":refundNo}).then(function(data){
			var resp = data.data;
			if(resp.success){
				alert("退款成功");
				$scope.search();
			}else{
				alert(data.data.respMessage);
			}
		});
	}
});