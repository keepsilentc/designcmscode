<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/sizeStr.js"></script>
	<script src="${ctx}/js/service/commonService.js"></script>
	<script src="${ctx}/js/service/sizeService.js"></script>
</head>
<body>
	
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="sizeStr">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" style="position:fixed;top:50px;z-index:2;">
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">尺寸</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
				<div class="col-xs-12" style="position:fixed;top:105px;z-index:2;">
					<div class="col-xs-2">
						<div class="input-group">
							<span class="input-group-addon">
					        	尺寸国家
						    </span>
						    <select class="form-control" ng-model="searchVo.sizeCountryId">
								<option value="">全部</option>
								<option value="{{sizeCountry.id}}" ng-repeat="sizeCountry in sizeCountryList">{{sizeCountry.sizeCountryName}}</option>
							</select>
						</div>
					</div>
					<div class="col-xs-2">
						<div class="input-group">
							<span class="input-group-addon">
					        	尺寸类型
					      	</span>
					      	<select class="form-control" ng-model="searchVo.sizeTypeId">
								<option value="">全部</option>
								<option value="{{sizeType.id}}" ng-repeat="sizeType in sizeTypeList">{{sizeType.name}}</option>
						  	</select>
						</div>
					</div>
					<div class="col-xs-3">
						<div class="input-group">
							<span class="input-group-addon">
					        	尺寸名称
					      	</span>
					      	<input type="text" ng-model="searchVo.sizeName" class="form-control" placeholder="尺寸名称">
					      	<span class="input-group-btn">
					        	
					     	</span>
						</div>
					</div>
		 			<div class="col-xs-2">
				      	<button class="btn btn-info" id="search" data-loading-text="Loading..." ng-click="search()" type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
				        <sec:authorize ifAllGranted="ROLE_SIZE_ADD">
				        	<button class="btn btn-info" data-toggle="modal" ng-click="openModal()" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
				        </sec:authorize>
				        <sec:authorize ifAllGranted="ROLE_SIZE_EXPORT">
				        	<button class="btn btn-info" ng-click="exports()" type="button">导出&nbsp;<span class="glyphicon glyphicon-export"></span></button>
				        </sec:authorize>
		 			</div>
				</div>
			</div>
				
		</div>
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel"><label ng-if="!size.id">新增尺寸</label><label ng-if="size.id">编辑尺寸</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		尺寸国家
							    </span>
							    <select class="form-control" ng-disabled="size.id" ng-model="size.sizeCountryId">	
									<option value="{{sizeCountry.id}}" ng-repeat="sizeCountry in sizeCountryList">{{sizeCountry.sizeCountryName}}</option>
								</select>
		        			</div>
		        		</div>
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        			    <span class="input-group-addon">
					        		尺寸类型
						      	</span>
						      	<select class="form-control" ng-disabled="size.id" ng-model="size.sizeTypeId">
									<option value="{{sizeType.id}}" ng-repeat="sizeType in sizeTypeList">{{sizeType.name}}</option>
							  	</select>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 尺寸名称
						       </span>
						       <input type="text" class="form-control" ng-model="size.sizeName">
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		排序
						       </span>
						       <input type="number" min="1" class="form-control" ng-model="size.orderBy">
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="size.error" id="error">{{size.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" ng-if="!size.id" ng-click="add()" class="btn btn-primary">add</button>
		        <button type="button" ng-if="size.id" ng-click="update()" class="btn btn-primary">update</button>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="row">
				<div class="col-md-12" style="position:absolute;top:120px;width:100%;left:5px;">
					<div class="">
						<table id="tablelist" class="table table-striped table-bordered table-hover ">
							<thead>
								<tr>
									<th></th>
									<th>尺寸国家</th>
									<th>尺寸类型</th>
									<th>尺寸名称</th>
									<th>排序</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in sizeList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.sizeCountryName}}
								</td>
								<td >
									{{item.sizeTypeName}}
								</td>
								<td >
									{{item.sizeName}}
								</td>
								<td >
									{{item.orderBy}}
								</td>
								<td >
									<sec:authorize ifAllGranted="ROLE_SIZE_UPDATE">
										<Button ng-click="openModal(item)" type="button" class="btn btn-primary" >
											<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
										</Button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_SIZE_DEL">
										<Button ng-click="delSize(item.id,$index)" type="button" class="btn btn-primary">
											<span class="glyphicon glyphicon-remove"></span>&nbsp;删除
										</Button>
									</sec:authorize>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
		</div>
		<div ng-show="false">
			<form action="${ctx}/size/export.do" id="exportform" method="post">
				<input type="hidden" name="sizeCountryId" ng-value="searchVo.sizeCountryId">
				<input type="hidden" name="sizeTypeId" ng-value="searchVo.sizeTypeId">
				<input type="hidden" name="sizeName" ng-value="searchVo.sizeName">
			</form>
		</div>
	</div>
</div>
</body>
</html>