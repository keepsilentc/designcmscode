tcApp.controller("cmsUserStr",function($scope,Upload,cmsUserService,cmsRoleService){
	$scope.searchVo = {};
	$scope.cmsUser = {};
	$scope.roleList = [];
	$scope.allRole = [];
	$scope.userName = null;
	$scope.userId = null;
	$scope.init = function(){
		cmsRoleService.getAllRole().then(function(resp){
			if(resp.data.success){
				$scope.allRole = resp.data.result;
			}else{
				alert(resp.data.respMessage);
			}
		});
		$scope.search();
	}
	
	$scope.openModal = function(item){
		$scope.cmsUser = {age:18,sex:1};
		angular.element("#myModal").modal("show");
		if(item){
			$scope.cmsUser = angular.copy(item);
		}
	}
	
	$scope.openUserRoleModal = function(item){
		angular.element("#userRoleModal").modal("show");
		$scope.userName = item.userName;
		$scope.userId = item.id;
		cmsUserService.getUserRoles(item.id).then(function(data){
			if(data.data.success){
				$scope.roleList = data.data.result;
			}else{
				alert(data.data.respMessage);
			}
		});
		
	}
	
	
	$scope.update = function(){
		$scope.cmsUser.error = null;
		if(!$scope.cmsUser.email){
			$scope.cmsUser.error = "邮箱不能为空";
		}
		if(!$scope.cmsUser.userName){
			$scope.cmsUser.error = "CMS用户名称不能为空";
		}
		if(!$scope.cmsUser.error){
			cmsUserService.updateCmsUser($scope.cmsUser).then(function(data){
				if(data.data.success){
					$scope.cmsUser = {};
					angular.element("#myModal").modal("hide");
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.add = function(){
		$scope.cmsUser.error = null;
		if(!$scope.cmsUser.email){
			$scope.cmsUser.error = "邮箱不能为空";
		}
		if(!$scope.cmsUser.userName){
			$scope.cmsUser.error = "CMS用户名称不能为空";
		}
		if(!$scope.cmsUser.error){
			cmsUserService.addCmsUser($scope.cmsUser).then(function(data){
				if(data.data.success){
					$scope.cmsUser = {};
					angular.element("#myModal").modal("hide");
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
	}
	
	$scope.join = function(){
		if($scope.joins.length>0){
			angular.forEach($scope.joins,function(it){
				angular.forEach($scope.roleList,function(role){
					if(role.id==it){
						role.choose = true;
					}
				});
			});
		}
	}
	
	$scope.remove = function(){
		if($scope.removes.length>0){
			angular.forEach($scope.removes,function(it){
				angular.forEach($scope.roleList,function(role){
					if(role.id==it){
						role.choose = false;
					}
				});
			});
		}
	}
	
	$scope.saveUserRoles = function(){
		var data = [];
		angular.forEach($scope.roleList,function(role){
			if(role.choose){
				data.push({userId:$scope.userId,roleId:role.id});
			}
		});
		if(data.length>0){
			var $btn = angular.element("#saveUserRolesBtn").button('loading');
			cmsUserService.saveUserRoles(data).then(function(resp){
				if(!resp.data.success){
					alert(resp.data.respMessage);
				}else{
					angular.element("#userRoleModal").modal("hide");
					$scope.search();
				}
				$btn.button("reset");
			});
		}
	}
	
	$scope.search = function(){
		var $btn = angular.element("#search").button('loading');
		cmsUserService.getCmsUsers($scope.searchVo).then(function(data){
			if(data.data.success){
				var resp = data.data;
				$scope.cmsUserList = resp.result;
			}else{
				alert(data.data.respMessage);
			}
			$btn.button("reset");
		});
	}
	$scope.delCmsUser = function(item,index){
		if(item.id){
			cmsUserService.delCmsUser(item.id).then(function(data){
				if(data.data.success){
					$scope.cmsUserList.splice(index,1);
					$scope.search();
				}else{
					alert(data.data.respMessage);
				}
			});
		}
		
	}
	$scope.init();
});