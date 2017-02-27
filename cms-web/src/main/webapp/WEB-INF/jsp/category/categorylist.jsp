<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
	<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/categoryStr.js"></script>
	<script src="${ctx}/js/service/categoryService.js"></script>
</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">分类</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<div class="row" ng-controller="categoryStr" ng-cloak>
					<div class="col-xs-12 right" style="height:auto;min-height:600px;border:1px solid #ccc;padding:10px 10px;">
						<div class="col-xs-12" >
							<ol class="breadcrumb" style="height:50px">
							  <li ng-repeat="level in categorylvel" ng-click="index(level,$index)"><a href="#">{{level.name}}</a></li>
							</ol>
						</div>
						<sec:authorize ifAllGranted="ROLE_CATEGORY_ADD">
							<div class="col-xs-12">
								<div class="col-xs-2 pull-right">
									<div class="input-group">
									      <span class="input-group-btn">
									        <button class="btn btn-info" data-toggle="modal" ng-click="openModal()" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
									      </span>
				 					</div>
								</div>
							</div>
						</sec:authorize>
						<hr>
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel"><label ng-if="!category.id">新增分类</label><label ng-if="category.id">编辑分类</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		分类名称
							    </span>
							    <input type="text" ng-model="category.categoryName" class="form-control" placeholder="分类名称">
		        			</div>
		        		</div>
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		排序
							    </span>
							    <input type="number" min="1" ng-model="category.orderBy" class="form-control" placeholder="排序">
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		是否启用
							    </span>
							    <div class="form-control">
							    	<label class="checkbox-inline">
								    	<input type="radio"  value="1" ng-model="category.isEnable">启用
									</label>
									<label class="checkbox-inline">
									    <input type="radio"  value="0" ng-model="category.isEnable">不启用
									</label>
							    </div>
		        			</div>
		        		</div>
		        	</div>
			      </div>
			      <div class="col-xs-12">
			      		<div class="alert alert-danger" role="alert" ng-show="category.error" id="error">{{category.error}}</div>
			      </div>
			      <div class="clearfix"></div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <button type="button" ng-if="!category.id" ng-click="add()" class="btn btn-primary">add</button>
			        <button type="button" ng-if="category.id" ng-click="update()" class="btn btn-primary">update</button>
			      </div>
			    </div>
			  </div>
			</div>
						
						<div class="col-xs-12 bottom">
							<table id="tablelist" class="table table-striped table-bordered table-hover ">
								<thead>
									<tr>
										<th ></th>
										<th width="15%">上级类目名称</th>
										<th width="15%">类目名称</th>
										<th width="20%">类目图片</th>
										<th >是否启用</th>	
										<th >排序</th>
										<th >操作</th>
									</tr>
								</thead>
								
								<tbody>
									<tr ng-repeat="item in categoryList">
									<td >
										{{$index+1}}
									</td>
									<td >
										{{categorylvel[categorylvel.length-1].name}}
									</td>
									<td >
										{{item.categoryName}}
									</td>
									<td>
										<img ng-src="${ctx}/file/get.do?id={{item.picture}}" alt="..." class="img-thumbnail imgin" >
										<button type="button" ngf-select="upload($file,item)" class="btn btn-primary right" style="height:40px;"><span class="glyphicon glyphicon-pencil"></span></button>
									</td>
									<td >
										<label ng-if="item.isEnable==1"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;启用</label>
										<label ng-if="item.isEnable==0"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;未启用</label>
									</td >
									<td >
										{{item.orderBy}}
									</td >
									<td >
										<Button ng-click="look(item)" type="button" class="btn btn-primary" >
											<span class="glyphicon glyphicon-arrow-right"></span>&nbsp;查看
										</Button>
										<sec:authorize ifAllGranted="ROLE_CATEGORY_UPDATE">
											<Button ng-click="openModal(item)" type="button" class="btn btn-primary" >
												<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
											</Button>
										</sec:authorize>
										<%-- <sec:authorize ifAllGranted="ROLE_CATEGORY_DEL">
											<Button ng-click="delCategory(item,$index)" type="button" class="btn btn-primary">
												<span class="glyphicon glyphicon-remove"></span>&nbsp;删除
											</Button>
										</sec:authorize> --%>
									</td>
								</tr>
							</tbody>
							</table>
						</div>
						<div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>