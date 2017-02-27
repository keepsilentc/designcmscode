<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/couponUserStr.js"></script>
	<script src="${ctx}/js/service/couponUserService.js"></script>
	<script src="${ctx}/js/service/commonService.js"></script>
</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="couponUserStr">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="${ctx}/coupon/index.do">优惠券</a></li>
					  <li><a href="#">分发优惠券</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<input type="hidden" id="couponNo" value="${couponNo}" >
			<div class="row">
				<div class="col-xs-12">
					<div class="col-xs-10">
						
		 			</div>
		 			<div class="col-xs-2">
					    <button class="btn btn-info" ng-click="search()" ng-if="false" id="search" data-loading-text="Loading..."  type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
		 			</div>
			</div>
		</div>
		<hr>
		
		<div class="row">
			<div class="col-xs-12">
				<div class="col-xs-5">
					<select class="form-control" ng-model="couponUserL" multiple="multiple" style="height:400px;">
						<option value="{{item.userNo}}" ng-repeat="item in couponUsers" ng-show="!item.choose">{{item.nickName}}</option>
					</select>
				</div>
				<div class="col-xs-1">
					<div style="min-height:200px;padding:40px 0px;">
						<div class="center" style="height:60px;width:100%;">
							<button type="button" ng-click="join()" class="btn btn-primary" >加入<span class="glyphicon glyphicon-arrow-right"></span></button>
						</div>
					</div>
					<div style="min-height:200px;padding:40px 0px;">
						<div class="center" style="height:60px;width:100%;">
							<button type="button" ng-click="remove()" class="btn btn-warning">移除<span class="glyphicon glyphicon-arrow-left"></span></button>
						</div>
					</div>
				</div>
				<div class="col-xs-5">
					<select class="form-control" ng-model="couponUserR" multiple="multiple" style="height:400px;">
						<option value="{{item.userNo}}" ng-repeat="item in couponUsers" ng-show="item.choose">{{item.nickName}}</option>
					</select>
				</div>
				<div class="clearfix"></div>
				<sec:authorize ifAllGranted="ROLE_COUPONUSER_SAVE">
					<div class="col-xs-11 bottom">
						<button class="btn btn-info pull-right" ng-click="save()"  id="save" data-loading-text="Saving..."  type="button">保存&nbsp;<span class="glyphicon glyphicon-check"></span></button>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>
</div>
</body>
</html>