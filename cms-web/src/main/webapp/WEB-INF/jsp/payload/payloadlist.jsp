<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/payLoadStr.js"></script>
	<script src="${ctx}/js/service/payLoadService.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
</head>
<body>
	
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="payLoadStr">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">推送</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-xs-10">
						
		 			</div>
		 			
		 			<div class="col-xs-2">
				        	<button class="btn btn-info" data-toggle="modal" ng-click="to_add()" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
		 			</div>
			</div>
		</div>
		<hr>
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
									<th width="8%">启用状态</th>
									<th >描述</th>
									<th width="15%">操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in payLoadList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.designerName}}
								</td>
								<td >
									{{item.payLoadName}}
								</td>
								<td>
									<img ng-src="${ctx}/file/get.do?id={{item.picture}}" alt="..." class="img-thumbnail imgin">
									<button type="button" ngf-select="upload($file,item)" class="btn btn-primary right" style="height:40px;"><span class="glyphicon glyphicon-pencil"></span></button>
								</td>
								<td >
									<label ng-if="item.isEnable==1">启用</label>
									<label ng-if="item.isEnable==0">未启用</label>
								</td >
								<td >
									{{item.describe}}
								</td >
								<td >
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