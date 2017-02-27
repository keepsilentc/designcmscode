<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/orderStr.js"></script>
	<script src="${ctx}/js/controller/deliverStr.js"></script>
	<script src="${ctx}/js/service/orderService.js"></script>
	<script src="${ctx}/js/service/userService.js"></script>
	<script src="${ctx}/js/directive/orderdetail.js"></script>
	<script src="${ctx}/js/directive/userInfo.js"></script>
	<script src="${ctx}/js/filter/filters.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="orderStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">订单</a></li>
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
						        	订单编号
						      </span>
						      <input type="text" class="form-control" ng-model="pageVo.orderNo">
						    </div>
						</div>
						<div class="col-xs-3">
							<div class="input-group">
						   	  <span class="input-group-addon">
						        	用户号
						      </span>
						      <input type="text" class="form-control" ng-model="pageVo.userNo">
						    </div>
						</div>
						<div class="col-xs-3">
							<div class="input-group">
								   <span class="input-group-addon">
						        		订单状态
							       </span>
							       <select class="form-control" ng-model="pageVo.orderState">
										<option value="">全部</option>
										<option value="10">未付款</option>
										<option value="13">确认中</option>
										<option value="21">已付款</option>
										<option value="22">已付定金</option>
										<option value="30">已发货</option>
										<option value="20">已完成</option>
										<option value="40">已关闭</option>
							  	  </select>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="input-group">
							   	  <span class="input-group-addon">
							        	订单类型
							      </span>
							      <select class="form-control" ng-model="pageVo.orderType">
										<option value="">全部</option>
										<option value="10">现货</option>
										<option value="20">预售</option>
										<option value="30">混合</option>
							  	  </select>
						    </div>
						</div>
						<div class="col-xs-3 bottom">
							<div class="input-group">
		        			   <span class="input-group-addon">
					        		 开始时间
						       </span>
						       <input type="text" class="form-control time" ng-model="pageVo.startTime" placeholder="开始时间" >
							</div>
						</div>
						<div class="col-xs-3 bottom">
							<div class="input-group">
		        			   <span class="input-group-addon">
					        		 结束时间
						       </span>
						       <input type="text" class="form-control time" ng-model="pageVo.endTime" placeholder="开始时间" >
							</div>
						</div>
		 			</div>
		 			<div class="col-xs-2">
					    <div class="input-group">
					      <span class="input-group-btn">
					        	<button class="btn btn-info" ng-click="search()"  id="search" data-loading-text="Loading..."  type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
				          </span>
					    </div>
		 			</div>
			</div>
		</div>
		<hr>
		<div class="modal fade" id="orderDetail_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">订单详细</h4>
		      </div>
		      <div class="modal-body">
		        	<orderdetail orderno="orderNo"></orderdetail>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="deliver_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ng-controller="deliverStr">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">发货</h4>
		      </div>
		      <div class="modal-body">
		      		<orderdetail orderno="deliver.orderNo"></orderdetail>
		      		<div class="row">
	      				<div class="col-xs-6">
	      					<div class="input-group">
								  <span class="input-group-addon">
								    	物流名称：
								  </span>
								  <input type="text" ng-model="deliver.deliverName" class="form-control">
							</div>
	      				</div>
	      				
						<div class="col-xs-6">
	      					<div class="input-group">
								  <span class="input-group-addon">
								    	物流单号：
								  </span>
								  <input type="text" ng-model="deliver.deliverNo" class="form-control">
							</div>
	      				</div>
	      				<div class="col-xs-12 bottom">
					      	<div class="alert alert-danger" role="alert" ng-show="deliver.error" >{{deliver.error}}</div>
					    </div>
	      			</div>
	      			
		      </div>
		      <div class="modal-footer">
		        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        	<button type="button" ng-click="dodeliver()" id="deliverBtn" data-loading-text="Doing..."  class="btn btn-primary">deliver</button>
		      </div>
		    </div>
		  </div>
		</div>
		
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
					<div class="">
						<table id="tablelist" class="table table-striped table-bordered table-hover ">
							<thead>
								<tr>
									<th width=""></th>
									<th width="">订单编号</th>
									<th width="">用户号</th>
									<th width="">订单类型</th>
									<th width="">订单应付金额</th>
									<td >运费</td>
									<td >优惠券</td>
									<td >折扣金额</td>
									<th >订单状态</th>
									<th >预售第一次付款使用的订单号</th>
									<th >创建时间</th>
									<th width="">操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in orderList">
								<td >
									{{$index+1}}
								</td>
								<td >
									<a href="" ng-click="showDetail(item.orderNo)">
										{{item.orderNo}}
									</a>
								</td>
								<td >
									<a href="" ng-click="showUserInfo(item.userNo)">
										{{item.userNo}}
									</a>
								</td>
								<td >
									<label ng-if="item.orderType==10">现货</label>
									<label ng-if="item.orderType==20">预售</label>
									<label ng-if="item.orderType==30">混合</label>
								</td>
								<td >
									{{item.orderMoney|currency:item.currencySign}}
								</td >
								<td >
									{{item.freight|currency:item.currencySign}}
								</td >
								<td >
									{{item.couponDesc}}
								</td >
								<td >
									{{item.discountMoney|currency:item.currencySign}}
								</td >
								<td >
									<label ng-if="item.orderState==10">待付款</label>
									<label ng-if="item.orderState==13">确认中</label>
									<label ng-if="item.orderState==20">已完成</label>
									<label ng-if="item.orderState==21">已付款</label>
									<label ng-if="item.orderState==22">已付定金</label>
									<label ng-if="item.orderState==30">已发货</label>
									<label ng-if="item.orderState==40">已关闭</label>
								</td >
								<td >
									{{item.PreOrderNo}}
								</td >
								<td >
									{{item.createTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td >
								<td >
									<Button type="button" ng-if="item.canSend==1&&item.orderState==21" ng-click="todeliver(item.orderNo)" class="btn btn-primary">
										发货&nbsp;&nbsp;<span class="glyphicon glyphicon-send"></span>
									</Button>
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