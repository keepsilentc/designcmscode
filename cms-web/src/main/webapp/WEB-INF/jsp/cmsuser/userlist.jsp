<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/cmsUserStr.js"></script>
	<script src="${ctx}/js/service/cmsRoleService.js"></script>
	<script src="${ctx}/js/service/cmsUserService.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
</head>
<body>
	
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="cmsUserStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">用户</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-xs-5 col-xs-offset-3">
					    <div class="input-group">
					      <span class="input-group-addon">
					        	角色
					      </span>
					      <select class="form-control" ng-model="searchVo.roleId">
							<option value="">全部</option>
							<option value="{{role.id}}" ng-repeat="role in allRole">{{role.name}}</option>
						  </select>
					      <span class="input-group-addon">
					        	CMS用户名称
					      </span>
					      <input type="text" ng-model="searchVo.cmsUserName" class="form-control" placeholder="CMS用户名称">
					      <span class="input-group-btn">
					        <button class="btn btn-info" ng-click="search()" id="search" data-loading-text="Loading..."  type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
					      </span>
					    </div>
		 			</div>
		 			<div class="col-xs-1">
					    <div class="input-group">
					      <span class="input-group-btn">
					        <button class="btn btn-info" data-toggle="modal" ng-click="openModal()" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
					      </span>
					    </div>
		 			</div>
			</div>
		</div>
		<hr>
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel"><label ng-if="!cmsUser.id">新增CMS用户</label><label ng-if="cmsUser.id">编辑CMS用户</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		CMS用户名称
							    </span>
							    <input type="text" ng-model="cmsUser.userName" class="form-control" placeholder="CMS用户名称">
		        			</div>
		        		</div>
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		年龄
						       </span>
						       <input type="number" ng-model="cmsUser.age" class="form-control" placeholder="年龄">
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		性别
						       </span>
						       <div class="form-control">
						       		<label class="radio-inline">
								    	<input type="radio"  value="1" ng-model="cmsUser.sex">男
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="2" ng-model="cmsUser.sex">女
									</label>
						       </div>		
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		邮箱
						       </span>
						       <input type="text" ng-model="cmsUser.email" class="form-control" placeholder="邮箱">
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="cmsUser.error" id="error">{{cmsUser.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" ng-if="!cmsUser.id" ng-click="add()" class="btn btn-primary">add</button>
		        <button type="button" ng-if="cmsUser.id" ng-click="update()" class="btn btn-primary">update</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- Modal -->
		<div class="modal fade" id="userRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel"><label>赋予角色</label></h4>
		      </div>
		      <div class="modal-body" >
        		  <div class="row">
        				<div class="col-xs-6">
	        			<div class="input-group">
	        				<span class="input-group-addon">
				        		cms用户名称
						    </span>
						    <input type="text" ng-model="userName" ng-readonly="true" class="form-control">
	        			</div>
	        		</div>
	        		<div class="clearfix"></div>
        			<div class="col-xs-5 bottom">
						<select class="form-control" ng-model="joins" multiple="multiple" style="height:200px;">
							<option value="{{item.id}}" ng-repeat="item in roleList" ng-show="!item.choose">{{item.name}}</option>
						</select>
					</div>
					<div class="col-xs-2 bottom">
						<div style="min-height:80px;padding:10px 0px;">
							<div class="center" style="height:60px;width:100%;">
								<button type="button" ng-click="join()" class="btn btn-primary" >加入<span class="glyphicon glyphicon-arrow-right"></span></button>
							</div>
						</div>
						<div style="min-height:80px;padding:10px 0px;">
							<div class="center" style="height:60px;width:100%;">
								<button type="button" ng-click="remove()" class="btn btn-warning">移除<span class="glyphicon glyphicon-arrow-left"></span></button>
							</div>
						</div>
					</div>
					<div class="col-xs-5 bottom">
						<select class="form-control" ng-model="removes" multiple="multiple" style="height:200px;">
							<option value="{{item.id}}" ng-repeat="item in roleList" ng-show="item.choose">{{item.name}}</option>
						</select>
					</div>
        		  </div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="cmsUser.error" id="error">{{cmsUser.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" ng-click="saveUserRoles()" id="saveUserRolesBtn" data-loading-text="Saving..." class="btn btn-primary">save</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="row">
				<div class="col-md-12">
					<div class="">
						<table id="tablelist" class="table table-striped table-bordered table-hover ">
							<thead>
								<tr>
									<th ></th>
									<th >CMS用户名称</th>
									<th >CMS用户密码</th>
									<th >真名</th>
									<th >手机号</th>
									<th >email</th>
									<th >角色</th>
									<th >状态</th>
									<th >注册时间</th>
									<th >操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in cmsUserList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.userName}}
								</td>
								<td >
									{{item.userPassword}}
								</td>
								<td>
									{{item.realName}}
								</td>
								<td>
									{{item.mobileNo}}
								</td>
								<td>
									{{item.email}}
								</td>
								<td>
									<label style="display:inline-block;width:180px;overflow:hidden;text-overflow:ellipsis;">{{item.roleNames}}</label>
									<Button ng-click="openUserRoleModal(item)" type="button" class="btn btn-primary">
										<span class="glyphicon glyphicon-pencil"></span>
									</Button>
								</td>
								<td>
									<label ng-if="item.status==1">正常</label>
								</td>
								<td>
									{{item.regTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td>
								<td >
									<Button ng-click="openModal(item)" type="button" class="btn btn-primary" >
										<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
									</Button>
									<Button ng-click="delCmsUser(item,$index)" type="button" class="btn btn-primary">
										<span class="glyphicon glyphicon-remove"></span>&nbsp;删除
									</Button>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>