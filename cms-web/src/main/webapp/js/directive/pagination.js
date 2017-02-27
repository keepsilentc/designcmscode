tcApp.directive("pager",function(){
	return {
		restrict:'E',
		templateUrl:ctx+'/template/tcpage.html',
		replace:false,
		scope: {
			pageCount:'=',
			currentPage: '=',
			onPageChange: '&',
			total:'='
		}, 
        link:function(scope,element,attrs){
        	scope.pagenums = [];
        	scope.pageChange = function(page) {
				if (page >= 1 && page <= scope.pageCount) {
					scope.currentPage = page;
				} else {
					scope.currentPage = 1;
				}
				scope.refresh = true;
			}
			scope.prev = function(){
				if(parseInt(scope.currentPage) <= 1){
					return;
				}
				scope.currentPage = parseInt(scope.currentPage) - 1;
				scope.refresh = true;
			}
			scope.next = function(){
				if(parseInt(scope.currentPage) >= scope.pageCount ){
					return;
				}
				scope.currentPage = parseInt(scope.currentPage) + 1;
				scope.refresh = true;
			}
        	scope.$watch('currentPage+pageCount', function(newValue,oldValue, scope) {
        		if(scope.currentPage){
        			build();
        			if(!scope.pageCount){
        				scope.onPageChange();
        				scope.refresh = false;
        			}else if(scope.refresh){
        				scope.onPageChange();
        				scope.refresh = false;
        			}
        		}
            });
        	function build() {
				var low,high,step;
				step = parseInt((scope.currentPage-1)/10);
				low = step*10+1;
				high = step*10+10;
				if(high>scope.pageCount){
					high = scope.pageCount;
				}
				if(scope.pagenums&&scope.currentPage>=scope.pagenums[0]&&scope.currentPage<=scope.pagenums[scope.pagenums.length-1]&&scope.pageCount>=scope.pagenums[scope.pagenums.length-1]){
					return;
				}
				scope.pagenums = [];
				for (; low <=high; low++) {
					scope.pagenums.push(low);
				}
			}
        }
	};
});