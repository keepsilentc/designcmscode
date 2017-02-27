<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/payloadAddstr.js"></script>
	<script src="${ctx}/js/service/payLoadService.js"></script>
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
				        	<button class="btn btn-info" data-toggle="modal" ng-click="openModal()" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
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
		        <h4 class="modal-title" id="myModalLabel">发送推送</h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		标题
							    </span>
							    <input type="text" ng-model="payLoad.title" class="form-control" placeholder="标题">
		        			</div>
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 内容
						       </span>
						       <textarea class="form-control" rows="5" ng-model="payLoad.body"></textarea>
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="payLoad.error" id="error">{{payLoad.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" id="send" ng-click="send()" data-loading-text="Sending..." class="btn btn-primary">send</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<ul class="nav nav-tabs">
				  <li role="presentation" class="active"><a href="#">动态推送</a></li>
				  <li role="presentation"><a href="#">商品上架推送</a></li>
				  <li role="presentation"><a href="#">Messages</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
</body>
</html>