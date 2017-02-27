<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/carouselStr.js"></script>
	<script src="${ctx}/js/service/carouselService.js"></script>
</head>
<body>
	
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="carouselStr">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" style="position:fixed;top:50px;z-index:2;">
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">轮播图</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
				<div class="col-xs-12" style="position:fixed;top:105px;z-index:2;">
					<div class="col-xs-2">
						<div class="input-group">
							<span class="input-group-addon">
					        	类型
						    </span>
						    <select class="form-control" ng-model="searchVo.type">
								<option value="">全部</option>
								<option value="10">文章</option>
								<option value="20">系列</option>
								<option value="30">商品</option>
							</select>
						</div>
					</div>
					<div class="col-xs-2">
						<div class="input-group">
							<span class="input-group-addon">
					        	启用状态
					      	</span>
					      	<select class="form-control" ng-model="searchVo.isEnable">
								<option value="">全部</option>
								<option value="1">启用</option>
								<option value="0">不启用</option>
						  	</select>
						</div>
					</div>
		 			<div class="col-xs-2">
				      	<button class="btn btn-info" id="search" data-loading-text="Loading..." ng-click="search()" type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
				        <sec:authorize ifAllGranted="ROLE_CAROUSEL_ADD">
				        	<button class="btn btn-info" data-toggle="modal" ng-click="openModal()" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
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
		        <h4 class="modal-title" id="myModalLabel"><label ng-if="!carousel.id">新增尺寸</label><label ng-if="carousel.id">编辑尺寸</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		类型
							    </span>
							    <select class="form-control" ng-model="carousel.type">
									<option value="10">文章</option>
									<option value="20">系列</option>
									<option value="30">商品</option>
								</select>
		        			</div>
		        		</div>
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		排序
						       </span>
						       <input type="number" min="1" class="form-control" ng-model="carousel.orderBy">
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom" ng-if="carousel.id">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		是否启用
							    </span>
							    <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio"  value="1" ng-model="carousel.isEnable">是
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="0" ng-model="carousel.isEnable">否
									</label>
							    </div>
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="carousel.error" id="error">{{carousel.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" ng-if="!carousel.id" ng-click="add()" class="btn btn-primary">add</button>
		        <button type="button" ng-if="carousel.id" ng-click="update()" class="btn btn-primary">update</button>
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
									<th width="15%">轮播图类型</th>
									<th width="30%">轮播图图片</th>
									<th width="10%">排序</th>
									<th width="10%">启用状态</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in carouselList">
								<td >
									{{$index+1}}
								</td>
								<td >
									<label ng-if="item.type==10">文章</label>
									<label ng-if="item.type==20">系列</label>
									<label ng-if="item.type==30">商品</label>
								</td>
								<td >
									<img ng-src="${ctx}/file/get.do?id={{item.picture}}" alt="..." class="img-thumbnail imgin">
									<button type="button" ngf-select="upload($file,item)" class="btn btn-primary right" style="height:40px;"><span class="glyphicon glyphicon-pencil"></span></button>
								</td>
								<td >
									{{item.orderBy}}
								</td>
								<td >
									<label ng-if="item.isEnable==1"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;启用</label>
									<label ng-if="item.isEnable==0"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;未启用</label>
								</td >
								<td >
									<sec:authorize ifAllGranted="ROLE_CAROUSEL_UPDATE">
										<Button ng-click="openModal(item)" type="button" class="btn btn-primary" >
											<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
										</Button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_CAROUSEL_DEL">
										<Button ng-click="delCarousel(item.id,$index)" type="button" class="btn btn-primary">
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
	</div>
</div>
</body>
</html>