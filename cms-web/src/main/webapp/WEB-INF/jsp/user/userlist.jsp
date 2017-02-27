<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/userStr.js"></script>
	<script src="${ctx}/js/service/userService.js"></script>
	<script src="${ctx}/js/directive/userInfo.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="userStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">尺寸用户</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-xs-10">
						<div class="col-xs-3">
							<div class="input-group">
							    <span class="input-group-addon">
					        		昵称
						        </span>
						        <input type="text" ng-model="pageVo.nickName" class="form-control" placeholder="昵称">
							</div>
						</div>
						<div class="col-xs-3">
							<div class="input-group">
							    <span class="input-group-addon">
					        		用户号
						        </span>
						        <input type="text" ng-model="pageVo.userNo" class="form-control" placeholder="用户号">
							</div>
						</div>
		 			</div>
		 			<div class="col-xs-2">
				        <button class="btn btn-info" ng-click="search()"  id="search" data-loading-text="Loading..."  type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
		 			</div>
			</div>
		</div>
		<hr>
		
		<div class="modal fade" id="userInfo_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">用户信息</h4>
		      </div>
		      <div class="modal-body">
		        	<userinfo user_no="userNo"></userinfo>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="row">
				<div class="col-md-12">
						<table id="tablelist" class="table table-striped table-bordered table-hover ">
							<thead>
								<tr>
									<th ></th>
									<th >用户号</th>
									<th >昵称</th>
									<th >登陆类型</th>
									<th >登陆号</th>
									<th >登陆时间</th>
									<th >创建时间</th>
									<th width="15%">操作</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in userList">
								<td >
									{{$index+1}}
								</td>
								<td >
									<a href="" ng-click="showDetail(item.userNo)">
										{{item.userNo}}
									</a>
								</td>
								<td >
									{{item.nickName}}
								</td>
								<td >
									<span ng-if="item.mobileNo!=null">手机号</span>
									<span ng-if="item.email!=null">邮箱</span>
									<span ng-if="item.platformType!=null">{{item.platformType}}</span>
								</td>
								<td>
									<span ng-if="item.mobileNo!=null">{{item.mobileNo}}</span>
									<span ng-if="item.email!=null">{{item.email}}</span>
									<span ng-if="item.platformType!=null">{{item.platformId}}</span>
								</td>
								<td >
									{{item.loginTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td >
								<td >
									{{item.createTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td >
								<td >
								</td>
							</tr>
						</tbody>
				</table>
				<pager page-count="pageVo.sumPage" total="pageVo.total" current-page="pageVo.current" on-page-change="search()"></pager>
			</div>
		</div>
	</div>
</div>
</body>
</html>