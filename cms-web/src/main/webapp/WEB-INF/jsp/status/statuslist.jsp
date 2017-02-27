<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/kindeditor/default.css" />
	<script src="${ctx}/kindeditor/kindeditor-min.js"></script>
	<script src="${ctx}/kindeditor/zh_CN.js"></script>
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/statusStr.js"></script>
	<script src="${ctx}/js/controller/statusAddStr.js"></script>
	<script src="${ctx}/js/service/designerService.js"></script>
	<script src="${ctx}/js/service/themeService.js"></script>
	<script src="${ctx}/js/service/statusService.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
</head>
<body>
	
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="statusStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">文章</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-xs-10">
						 <div class="col-xs-5">
							<div class="input-group">
								<span class="input-group-addon">
									动态名称
							    </span>
							    <input type="text" ng-model="pageVo.statusName" class="form-control" placeholder="动态名称">
							</div>
						</div>
						
						<div class="col-xs-3">
							<div class="input-group">
								<span class="input-group-addon">
									动态类型
							    </span>
							    <select class="form-control" ng-model="pageVo.statusTypeId">
									<option value="">全部</option>
									<option value="1">预售/销售</option>
									<option value="2">品牌介绍</option>
									<option value="3">搭配</option>
								</select>
							</div>
						</div>
						
						<div class="col-xs-3">
							<div class="input-group">
								<span class="input-group-addon">
									启用状态
							    </span>
							    <select class="form-control" ng-model="pageVo.isEnable">
									<option value="">全部</option>
									<option value="1">启用</option>
									<option value="0">未启用</option>
								</select>
							</div>
						</div>
						
					    <div class="col-xs-3  bottom">
							<div class="input-group">
								<span class="input-group-addon">
									设计师
							    </span>
							    <select class="form-control" ng-change="getTheme()" ng-model="pageVo.designerId">
									<option value="">全部</option>
									<option value="{{designer.id}}" ng-repeat="designer in designerList">{{designer.designerName}}</option>
								</select>
							</div>
						</div>
					    
					    <div class="col-xs-3  bottom">
							<div class="input-group">
								<span class="input-group-addon">
									系列
							    </span>
							    <select class="form-control" ng-change="changeTheme()" ng-model="pageVo.themeId">
									<option value="">全部</option>
									<option value="{{theme.id}}" ng-repeat="theme in themeList">{{theme.themeName}}</option>
								</select>
							</div>
						</div>
						
		 			</div>
		 			
		 			<div class="col-xs-2">
		 					<button class="btn btn-info" ng-click="search()" id="search" data-loading-text="Loading..."  type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
	 					<sec:authorize ifAllGranted="ROLE_STATUS_ADD">
				     		 <button class="btn btn-info" data-toggle="modal" ng-click="openModal()" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
	 					</sec:authorize>
		 			</div>
			</div>
		</div>
		<hr>
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ng-controller="statusAddStr">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel"><label ng-if="!status.id">新增动态</label><label ng-if="status.id">编辑动态</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		动态名称
							    </span>
							    <input type="text" ng-model="status.statusName" class="form-control" placeholder="动态名称">
		        			</div>
		        		</div>
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 动态类型
						       </span>
						       <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio"  value="1" ng-model="status.statusTypeId">预售/销售
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="2" ng-model="status.statusTypeId">品牌介绍
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="3" ng-model="status.statusTypeId">搭配
									</label>
							    </div>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 系列
						       </span>
						       <select class="form-control"  ng-model="status.themeId">
								 <option value="{{theme.id}}" ng-repeat="theme in themeList">{{theme.themeName}}</option>
							   </select>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom" ng-if="status.id">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		是否启用
							    </span>
							    <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio"  value="1" ng-model="status.isEnable">是
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="0" ng-model="status.isEnable">否
									</label>
							    </div>
		        			</div>
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 描述
						       </span>
						       <textarea class="form-control" ng-model="status.describe"></textarea>
		        			</div>
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 内容
						       </span>
						       <textarea class="form-control" id="test" cols="100" rows="8" style="width:100%;height:600px;"></textarea>
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="status.error" id="error">{{status.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" ng-if="!status.id" ng-click="add()" class="btn btn-primary">add</button>
		        <button type="button" ng-if="status.id" ng-click="update()" class="btn btn-primary">update</button>
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
									<th width="5%"></th>
									<th width="6%">动态类型</th>
									<th width="6%">设计师名称</th>
									<th width="12%">系列名称</th>
									<th width="18%">动态名称</th>
									<th width="12%">外部图片</th>
									<th width="12%">内部图片</th>
									<th width="6%">启用状态</th>
									<th width="4%">排序</th>
									<th >描述</th>
									<th width="8%">操作</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in statusList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.statusTypeName}}
								</td>
								<td >
									{{item.designerName}}
								</td>
								<td >
									{{item.themeName}}
								</td>
								<td >
									<div title="{{item.statusName}}" style="width:300px;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">
										{{item.statusName}}
									</div>
									
								</td>
								<td>
									<img ng-src="${ctx}/file/get.do?id={{item.picture}}" alt="..." class="img-thumbnail imgin">
									<button type="button" ngf-select="uploadPicture($file,item)" class="btn btn-primary right" style="height:40px;"><span class="glyphicon glyphicon-pencil"></span></button>
								</td>
								<td>
									<img ng-src="${ctx}/file/get.do?id={{item.insidePicture}}" alt="..." class="img-thumbnail imgin">
									<button type="button" ngf-select="uploadInsidePicture($file,item)" class="btn btn-primary right" style="height:40px;"><span class="glyphicon glyphicon-pencil"></span></button>
								</td>
								<td >
									<label ng-if="item.isEnable==1"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;启用</label>
									<label ng-if="item.isEnable==0"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;未启用</label>
								</td >
								<td >
									{{item.orderBy}}
								</td >
								<td >
									{{item.describe}}
								</td >
								<td >
									<sec:authorize ifAllGranted="ROLE_STATUS_UPDATE">
										<Button ng-click="openModal(item)" type="button" class="btn btn-primary" >
											<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
										</Button>
									</sec:authorize>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
				<pager page-count="pageVo.sumPage" total="pageVo.total" current-page="pageVo.current" on-page-change="search()"></pager>
			</div>
		</div>
	</div>
</div>
</body>
</html>