<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/themeStr.js"></script>
	<script src="${ctx}/js/service/designerService.js"></script>
	<script src="${ctx}/js/service/themeService.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
</head>
<body>
	
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="themeStr">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">系列</a></li>
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
									系列名称
							    </span>
							    <input type="text" ng-model="pageVo.themeName" class="form-control" placeholder="系列名称">
							</div>
						</div>
						
					    <div class="col-xs-4">
							<div class="input-group">
								<span class="input-group-addon">
									设计师
							    </span>
							    <select class="form-control" ng-model="pageVo.designerId">
									<option value="">全部</option>
									<option value="{{designer.id}}" ng-repeat="designer in designerList">{{designer.designerName}}</option>
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
						
		 			</div>
		 			
		 			<div class="col-xs-2">
		 					<button class="btn btn-info" ng-click="search()" id="search" data-loading-text="Loading..."  type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
	 					<sec:authorize ifAllGranted="ROLE_THEME_ADD">
				     		 <button class="btn btn-info" data-toggle="modal" ng-click="openModal()" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
	 					</sec:authorize>
	 					<sec:authorize ifAllGranted="ROLE_THEME_EXPORT">
				        	<button class="btn btn-info" ng-click="exports()" type="button">导出&nbsp;<span class="glyphicon glyphicon-export"></span></button>
				       	</sec:authorize>
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
		        <h4 class="modal-title" id="myModalLabel"><label ng-if="!theme.id">新增系列</label><label ng-if="theme.id">编辑系列</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		系列名称
							    </span>
							    <input type="text" ng-model="theme.themeName" class="form-control" placeholder="系列名称">
		        			</div>
		        		</div>
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 设计师
						       </span>
						       <select class="form-control" id="designer-1"  ng-model="theme.designerId">
								 <option value="{{designer.id}}" ng-repeat="designer in designerList">{{designer.designerName}}</option>
							   </select>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom" ng-if="theme.id">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		是否启用
							    </span>
							    <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio"  value="1" ng-model="theme.isEnable">是
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="0" ng-model="theme.isEnable">否
									</label>
							    </div>
		        			</div>
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 描述
						       </span>
						       <textarea class="form-control" rows="5" ng-model="theme.describe"></textarea>
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="theme.error" id="error">{{theme.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" ng-if="!theme.id" ng-click="add()" class="btn btn-primary">add</button>
		        <button type="button" ng-if="theme.id" ng-click="update()" class="btn btn-primary">update</button>
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
									<th width="10%">设计师名称</th>
									<th width="15%">系列名称</th>
									<th width="15%">系列图片</th>
									<!-- <th width="8%">预售开始时间</th>
									<th width="8%">预售结束时间</th> -->
									<th width="6%">启用状态</th>
									<th >描述</th>
									<th width="15%">操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in themeList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.designerName}}
								</td>
								<td >
									{{item.themeName}}
								</td>
								<td>
									<img ng-src="${ctx}/file/get.do?id={{item.picture}}" alt="..." class="img-thumbnail imgin">
									<button type="button" ngf-select="upload($file,item)" class="btn btn-primary right" style="height:40px;"><span class="glyphicon glyphicon-pencil"></span></button>
								</td>
								<!-- <td >
									{{item.preSellStartTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td>
								<td >
									{{item.preSellEndTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td> -->
								<td >
									<label ng-if="item.isEnable==1"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;启用</label>
									<label ng-if="item.isEnable==0"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;未启用</label>
								</td >
								<td >
									<div title="{{item.describe}}" style="width:300px;white-space:nowrap;overflow:hidden; text-overflow:ellipsis;">
										{{item.describe}}
									</div>
								</td >
								<td >
									<sec:authorize ifAllGranted="ROLE_THEME_UPDATE">
										<Button ng-click="openModal(item)" type="button" class="btn btn-primary" >
											<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
										</Button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_THEME_DEL">
										<Button ng-click="delTheme(item,$index)" type="button" class="btn btn-primary">
											<span class="glyphicon glyphicon-remove"></span>&nbsp;删除
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
		<div ng-show="false">
			<form action="${ctx}/theme/export.do" id="exportform" method="post">
				<input type="hidden" name="designerId" ng-value="pageVo.designerId">
				<input type="hidden" name="themeName" ng-value="pageVo.themeName">
				<input type="hidden" name="isEnable" ng-value="pageVo.isEnable">
			</form>
		</div>
	</div>
</div>
</body>
</html>