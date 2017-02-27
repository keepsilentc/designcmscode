<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
	<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/colorStr.js"></script>
	<script src="${ctx}/js/service/colorService.js"></script>
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
					  <li><a href="#">颜色</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<div class="row" ng-controller="colorStr" ng-cloak>
					<div class="col-xs-12 right" style="border:1px solid #ccc;padding:10px 10px;">
						<div class="col-xs-12">
							<div class="form-group">
								<label class="col-xs-1">颜色名称</label>
								<div class="col-xs-5">
									<div class="input-group">
								      <input type="text" class="form-control" ng-model="colorName"  placeholder="颜色名称">
								      <span class="input-group-btn">
								        <button class="btn btn-primary" ng-click="search()" id="search" data-loading-text="Loading..."  type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
								      </span>
								    </div>
								</div>
							</div>
						</div>
						<div style="min-height:500px;height:auto;">
							<div class="col-md-1 col-sm-2 col-xs-2 bottom" ng-repeat="color in colorList">
								<div style="border:1px solid #ccc;padding:5px;min-width:100px;">
									<a href="#" ngf-select="upload($file,color)" class="thumbnail" style="height:100px;width:auto;">
								      <img ng-src="${ctx}/file/get.do?id={{color.picture}}" style="width:100%;height:100%;" alt="...">
								    </a>
								    <div class="caption">
								    	<input type="text" class="form-control" placeholder="颜色名称" ng-model="color.name">
									    <sec:authorize ifAnyGranted="ROLE_COLOR_ADD,ROLE_COLOR_UPDATE">
									    	<button type="button" ng-click="save(color)" class="btn btn-primary bottom col-xs-12">保存</button>
									    </sec:authorize>
									    <div class="clearfix"></div>
								    </div>
								    
								</div>
							</div>
							<sec:authorize ifAllGranted="ROLE_COLOR_ADD">
								<div class="col-md-1 col-sm-2 col-xs-2" ng-click="addColor()">
									<div class="col-xs-12" style="padding:40px">
										<span class="glyphicon glyphicon-plus" ></span>
									</div>
								</div>
							</sec:authorize>
						</div>
						<div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>