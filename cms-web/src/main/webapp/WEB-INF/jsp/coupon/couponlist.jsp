<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/couponStr.js"></script>
	<script src="${ctx}/js/controller/couponAddStr.js"></script>
	<script src="${ctx}/js/service/couponService.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="couponStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">优惠券</a></li>
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
					        		 开始时间
						       </span>
						       <input type="text" class="form-control time" ng-model="pageVo.startTime" placeholder="开始时间" >
							</div>
						</div>
						<div class="col-xs-3">
							<div class="input-group">
		        			   <span class="input-group-addon">
					        		 结束时间
						       </span>
						       <input type="text" class="form-control time" ng-model="pageVo.endTime" placeholder="开始时间" >
							</div>
						</div>
						<div class="col-xs-3">
							<div class="input-group">
								   <span class="input-group-addon">
						        		 分类
							       </span>
							       <select class="form-control" ng-model="pageVo.couponType">
										<option value="">全部</option>
										<option value="0">无限制</option>
										<option value="1">单用户使用一次</option>
							  	  </select>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="input-group">
								   <span class="input-group-addon">
						        		 策略
							       </span>
							       <select class="form-control" ng-model="pageVo.couponStrategy">
										<option value="">全部</option>
										<option value="1">打折</option>
										<option value="2">满减</option>
							  	  </select>
							</div>
						</div>
						<div class="col-xs-3 bottom">
							<div class="input-group">
							   	  <span class="input-group-addon">
							        	启用状态
							      </span>
							      <select class="form-control" ng-model="pageVo.IsEnable">
										<option value="">全部</option>
										<option value="1">启用</option>
										<option value="0">未启用</option>
							  	  </select>
						    </div>
						</div>
						<div class="col-xs-3 bottom">
							<div class="input-group">
						   	  <span class="input-group-addon">
						        	名称
						      </span>
						      <input type="text" class="form-control" ng-model="pageVo.couponName">
						    </div>
						</div>
		 			</div>
		 			<div class="col-xs-2">
					    <div class="input-group">
					      <span class="input-group-btn">
					        	<button class="btn btn-info" ng-click="search()"  id="search" data-loading-text="Loading..."  type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
				          </span>
				          <sec:authorize ifAllGranted="ROLE_COUPON_ADD">
						      <span class="input-group-btn">
						        <button class="btn btn-info" data-toggle="modal" ng-click="openModal()" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
						      </span>
					      </sec:authorize>
					    </div>
		 			</div>
			</div>
		</div>
		<hr>
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ng-controller="couponAddStr">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel"><label ng-if="!coupon.id">新增优惠券</label><label ng-if="coupon.id">编辑优惠券</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		优惠券名称
							    </span>
							    <input type="text" ng-model="coupon.couponName" class="form-control" placeholder="优惠券名称">
		        			</div>
		        		</div>
		        		
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 分类
						       </span>
						       <select class="form-control" ng-model="coupon.couponType">
									<option value="0">无限制</option>
									<option value="1">单用户使用一次</option>
							   </select>
		        			</div>
		        		</div>
		        		
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 策略
						       </span>
						       <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio" ng-disabled="coupon.id" value="1" ng-model="coupon.couponStrategy">打折
									</label>
									<label class="radio-inline">
									    <input type="radio" ng-disabled="coupon.id" value="2" ng-model="coupon.couponStrategy">满减
									</label>
							    </div>
		        			</div>
		        		</div>
		        		
		        		<div class="col-xs-6 bottom" ng-if="coupon.couponStrategy==1">
		        			<div class="input-group" >
						       <input type="number" step="0.1" min="0" class="form-control" ng-model="coupon.couponRate">
		        				<span class="input-group-addon">
					        		 折
						       </span>
		        			</div>
		        		</div>
		        		<div class="clearfix"></div>
		        		<div class="col-xs-6 bottom" ng-if="coupon.couponStrategy==2">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 满
						       </span>
						       <input type="number" step="0.01" min="0.00" class="form-control" ng-model="coupon.fullMoney">
						       <span class="input-group-addon">
					        		 ￥
						       </span>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom" ng-if="coupon.couponStrategy==2">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 减
						       </span>
						       <input type="number" step="0.01" min="0.00" class="form-control" ng-model="coupon.minusMoney">
						       <span class="input-group-addon">
					        		 ￥
						       </span>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 开始时间
						       </span>
						       <input type="text" class="form-control time" ng-model="coupon.startTime" placeholder="开始时间" >
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 结束时间
						       </span>
						       <input type="text" class="form-control time" ng-model="coupon.endTime" placeholder="结束时间" >
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom" ng-if="coupon.couponType!=1">
		        			<div class="input-group" >
						       <span class="input-group-addon">
					        		 总数
						       </span>
						       <input type="number" step="1" min="0" class="form-control" ng-model="coupon.sumNum">
		        				
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom" ng-if="coupon.id">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 是否启用
						       </span>
						       <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio" value="1" ng-model="coupon.isEnable">是
									</label>
									<label class="radio-inline">
									    <input type="radio" value="0" ng-model="coupon.isEnable">否
									</label>
							    </div>
		        			</div>
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 备注
						       </span>
						       <textarea class="form-control" ng-model="coupon.remark"></textarea>
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="coupon.error" id="error">{{coupon.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" ng-if="!coupon.id" ng-click="add()" class="btn btn-primary">add</button>
		        <button type="button" ng-if="coupon.id" ng-click="update()" class="btn btn-primary">update</button>
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
									<th width="10%">优惠码名称</th>
									<th >总数</th>
									<th >优惠券分类</th>
									<th width="10%">优惠券策略</th>
									<th >策略明细</th>
									<th >启用状态</th>
									<th >开始时间</th>
									<th >结束时间</th>
									<th >备注</th>
									<th width="15%">操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in couponList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.couponName}}
								</td>
								<td>
									<span ng-if="item.couponType==1">不限</span>
									<span ng-if="item.couponType!=1">{{item.sumNum}}</span>
									
								</td>
								<td >
									<label ng-if="item.couponType==0">无限制</label>
									<label ng-if="item.couponType==1">单用户使用一次</label>
								</td >
								<td >
									<label ng-if="item.couponStrategy==1">打折</label>
									<label ng-if="item.couponStrategy==2">满减</label>
								</td >
								<td >
									<label ng-if="item.couponStrategy==1">{{item.couponRate}}折</label>
									<label ng-if="item.couponStrategy==2">满{{item.fullMoney|currency:'￥'}}减{{item.minusMoney|currency:'￥'}}</label>
								</td >
								<td >
									<label ng-if="item.isEnable==1">启用</label>
									<label ng-if="item.isEnable==0">未启用</label>
								</td >
								<td >
									{{item.startTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td >
								<td >
									{{item.endTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td >
								<td >
									{{item.remark}}
								</td >
								<td >
									<sec:authorize ifAllGranted="ROLE_COUPONUSER_INDEX">
										<Button ng-click="toUserCoupon(item.couponNo)" type="button" class="btn btn-primary">
											<span class="glyphicon glyphicon-plus"></span>
										</Button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_COUPON_UPDATE">
										<Button ng-click="openModal(item)" type="button" class="btn btn-primary" >
											<span class="glyphicon glyphicon-edit"></span>
										</Button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_COUPON_DEL">
										<Button ng-click="delCoupon(item,$index)" type="button" class="btn btn-primary">
											<span class="glyphicon glyphicon-remove"></span>
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