<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/cmsRoleStr.js"></script>
	<script src="${ctx}/js/service/cmsRoleService.js"></script>
	<script src="${ctx}/js/service/cmsResourceService.js"></script>
</head>
<body>
	
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="cmsRoleStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">角色</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
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
		        <h4 class="modal-title" id="myModalLabel"><label ng-if="!cmsRole.id">新增CMS用户角色</label><label ng-if="cmsRole.id">编辑CMS用户角色</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		角色名称
							    </span>
							    <input type="text" ng-model="cmsRole.name" class="form-control" placeholder="角色名称">
		        			</div>
		        		</div>
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		状态
							    </span>
							    <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio"  value="1" ng-model="cmsRole.status">启用
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="0" ng-model="cmsRole.status">不启用
									</label>
							    </div>
		        			</div>
		        			
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		描述
						       </span>
						       <textarea class="form-control" row="3" ng-model="cmsRole.description"></textarea>
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="cmsRole.error" id="error">{{cmsRole.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" ng-if="!cmsRole.id" ng-click="add()" class="btn btn-primary">add</button>
		        <button type="button" ng-if="cmsRole.id" ng-click="update()" class="btn btn-primary">update</button>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="privilegeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title"><label>赋予权限</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		角色名称
							    </span>
							    <input type="text" ng-model="roleName" class="form-control" ng-readonly="true">
		        			</div>
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="row">
		        				<div class="col-xs-12" >
		        					<div class="col-xs-12" style="border:1px solid #ccc" ng-repeat="(key, value) in cmsResources">
		        						<div class="col-xs-12">
		        							<div class="checkbox">
											  <label>
											    <input type="checkbox" ng-change="chooseAll(value,$index)" ng-model="$parent.conf[$index]">
											    	全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模块：{{key}}
											  </label>
											</div>
		        						</div>
										<div class="col-xs-6" ng-repeat="item in value">
				        					<div class="checkbox">
											  <label>
											    <input type="checkbox" ng-model="item.choose">
											    {{item.name}}
											  </label>
											</div>
				        				</div>
		        					</div>
		        				</div>
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="cmsRole.error" id="error">{{cmsRole.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" id="saveRoleResourcesBtn" data-loading-text="Saving..."  ng-click="saveRoleResources()" class="btn btn-primary">save</button>
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
									<th >CMS角色名称</th>
									<th >CMS角色描述</th>
									<th >状态</th>
									<th >创建时间</th>
									<th >操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in cmsRoleList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.name}}
								</td>
								<td >
									{{item.description}}
								</td>
								<td>
									<label ng-if="item.status==1">启用</label>
									<label ng-if="item.status==0">未启用</label>
								</td>
								<td>
									{{item.createTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td>
								<td >
									<Button ng-click="openModal(item)" type="button" class="btn btn-primary" >
										<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
									</Button>
									<Button ng-click="openPrivilegeModal(item)" ng-if="item.id!=1" type="button" class="btn btn-primary" >
										<span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;赋予权限
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