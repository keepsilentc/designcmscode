tcApp.controller("cmsRoleStr",function($scope,Upload,cmsRoleService){
	$scope.cmsRole = {};
	$scope.cmsRoleList= [];
	$scope.cmsResources = {};
	$scope.cmsRoleId = null;
	$scope.conf = [];
	$scope.roleName = null;
	$scope.init = function(){
		$scope.search();
	}
	
	$scope.openModal = function(item){
		$scope.cmsRole = {status:0};
		angular.element("#myModal").modal("show");
		if(item){
			$scope.cmsRole = angular.copy(item);
		}
	}
	
	$scope.openPrivilegeModal = function(item){
		angular.element("#privilegeModal").modal("show");
		$scope.conf = [];
		$scope.cmsResources = {};
		$scope.cmsRoleId = item.id;
		$scope.roleName = item.name;
		cmsRoleService.getRoleResources(item.id).then(function(data){
			if(data.data.success){
				$scope.cmsResources = data.data.result;
			}else{
				alert(data.data.respMessage);
			}
		});
	}
	
	$scope.saveRoleResources = function(){
		var data = [];
		for(var key in $scope.cmsResources){
			angular.forEach($scope.cmsResources[key],function(it){
				if(it.choose){
					data.push({resourceId:it.id,roleId:$scope.cmsRoleId});
				}
			});
		}
		if(data.length>0){
			$btn = angular.element("#saveRoleResourcesBtn").button('loading');
			cmsRoleService.saveRoleResources(data).then(function(resp){
				if(!resp.data.success){
					alert(resp.data.respMessage);
				}else{
					angular.element("#privilegeModal").modal("hide");
				}
				$btn.button('reset');
				
			});
		}
	}
	$scope.chooseAll = function(value,index){
		if($scope.conf[index]){
			angular.forEach(value,function(data){
				data.choose = true;
			});
		}else{
			angular.forEach(value,function(data){
				data.choose = false;
			});
		}
	}
	
	$scope.update = function(){
		$scope.cmsRole.error = null;
		if(!$scope.cmsRole.name){
			$scope.cmsRole.error = "角色名称不能为空";
		}
		if(!$scope.cmsRole.error){
			cmsRoleService.updateCmsRole($scope.cmsRole).then(function(data){
				if(data.data.success){
					$scope.cmsRole = {};
					angular.element("#myModal").modal("hide");
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.add = function(){
		$scope.cmsRole.error = null;
		if(!$scope.cmsRole.name){
			$scope.cmsRole.error = "角色名称不能为空";
		}
		if(!$scope.cmsRole.error){
			cmsRoleService.addCmsRole($scope.cmsRole).then(function(data){
				if(data.data.success){
					$scope.cmsRole = {};
					angular.element("#myModal").modal("hide");
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	
	$scope.search = function(){
		var $btn = angular.element("#search").button('loading');
		cmsRoleService.getCmsRoles().then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.cmsRoleList = resp.result;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	$scope.delCmsRole = function(item,index){
		if(item.id){
			cmsRoleService.delCmsRole(item.id).then(function(data){
				if(data.data.success){
					$scope.cmsRoleList.splice(index,1);
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
		
	}
	$scope.init();
});