<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/refundApproveStr.js"></script>
	<script src="${ctx}/js/service/refundService.js"></script>
	<script src="${ctx}/js/service/orderService.js"></script>
	<script src="${ctx}/js/directive/orderdetail.js"></script>
	<script src="${ctx}/js/directive/follow.js"></script>
	<script src="${ctx}/js/filter/filters.js"></script>
</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="refundApproveStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">退款审核</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<input type="hidden" id="refundNo" value="${refundNo}">
			<div class="row">
				<div class="col-xs-6">
					<orderdetail orderno="orderNo"></orderdetail>
				</div>
				
				<div class="col-xs-6">
					<follow refund_no="refund.refundNo"></follow>
				</div>
				
				<div class="col-xs-6">
					<div class="col-xs-12">
						<div class="input-group">
							<label class="input-group-addon">是否同意退款</label>
							<div class="form-control">
								<div class="radio-inline">
									  <label>
									    <input type="radio" ng-model="approve.agree"  value="1" style="">
									 		   同意
									  </label>
								</div>
								<div class="radio-inline">
									  <label>
									    <input type="radio" ng-model="approve.agree" value="0">
									   		 不同意
									  </label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-xs-12 bottom" ng-if="approve.agree==0">
						<div class="input-group">
							<label class="input-group-addon">不同意原因</label>
							<textarea class="form-control" ng-model="approve.failReason" rows="4">
								
							</textarea>
						</div>
					</div>
					
					<div class="col-xs-12 bottom">
			      		<div class="alert alert-danger" role="alert" ng-show="approve.error" id="error">{{approve.error}}</div>
			      	</div>
					<div class="col-xs-12 bottom">
						<button class="btn btn-info" ng-click="refundApprove()"  id="save" data-loading-text="Saving..."  type="button">保存&nbsp;<span class="glyphicon glyphicon-floppy-save"></span></button>
					</div>
				</div>
			</div>
	</div>
</body>
</html>