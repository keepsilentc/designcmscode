tcApp.controller("couponStr",function($scope,$filter,couponService){
	$scope.pageVo = {};
	$scope.couponList = [];
	
	$scope.init = function(){
		angular.element(".time").datetimepicker({
			format: 'yyyy-mm-dd hh:ii:00',
			autoclose: true,	
			todayBtn: true,
			todayHighligh:true
			});
		$scope.pageVo.current = 1;
	}
	
	$scope.search = function(){
		var $btn = angular.element("#search").button('loading');
		couponService.getCoupons($scope.pageVo).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.couponList = resp.result;
				$scope.pageVo = resp.pageVo;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	
	
	$scope.openModal = function(item){
		$scope.$broadcast("to_openModal",item);
	}
	
	$scope.delCoupon = function(item,index){
		if(item.id){
			couponService.delCoupon(item.id).then(function(data){
				if(data.data.success){
					$scope.couponList.splice(index,1);
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	$scope.init();
	
	$scope.toUserCoupon = function(couponNo){
		if(!couponNo){
			alert("fail");
		}else{
			var form = document.createElement("form");      
			form.action = ctx+"/coupon/allocate/index.do"; 
			form.method = "post";        
			form.style.display = "none";
		    var input = document.createElement("input");
		    input.type= "hidden";
		    input.name="couponNo";
		    input.value=couponNo;
		    form.appendChild(input);
		    document.body.appendChild(form);
		    form.submit();
		}
	}
});