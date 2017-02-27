tcApp.controller("productSizeColorImportStr",function($scope,Upload,productSizeColorService){
	$scope.errorList = [];
	$scope.errorMsg = null;
	$scope.upload = function(file){
		$scope.errorList = [];
		$scope.fileName = file.name;
		$btn = angular.element("#upload").button('loading');
		Upload.upload({
            url: ctx+'/productSizeColor/import.do',
            data: {file: file}
        }).then(function (resp) {
        	if(resp.data.success){
        		if(resp.data.result.length==0){
        			alert("上传成功");
        		}else{
        			$scope.errorList = resp.data.result;
        		}
        	}else{
        		alert(resp.data.respMessage);
        	}
        	$btn.button('reset');
        }, function (resp) {
            console.log('Error status: ' + resp.status);
        }, function (evt) {
//            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
        });
	}
});