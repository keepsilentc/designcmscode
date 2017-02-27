<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/returnStr.js"></script>
	<script src="${ctx}/js/service/returnService.js"></script>
	<script src="${ctx}/js/service/orderService.js"></script>
	<script src="${ctx}/js/directive/orderdetail.js"></script>
	<script src="${ctx}/js/filter/filters.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="returnStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">退换货</a></li>
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
						        	退换货号
						      </span>
						      <input type="text" class="form-control" ng-model="pageVo.returnNo">
						    </div>
						</div>
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
						<div class="clearfix"></div>
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
						<div class="col-xs-3 bottom">
							<div class="input-group">
								   <span class="input-group-addon">
						        		退换货类型
							       </span>
							       <select class="form-control" ng-model="pageVo.returnType">
										<option value="">全部</option>
										<option value="1">退货</option>
										<option value="2">换货</option>
							  	  </select>
							</div>
						</div>
						<div class="col-xs-3 bottom">
							<div class="input-group">
								   <span class="input-group-addon">
						        		退换货状态
							       </span>
							       <select class="form-control" ng-model="pageVo.refundState">
										<option value="">全部</option>
										<option value="-1">退换货申请中</option>
										<option value="1">审批通过</option>
										<option value="0">审批不通过</option>
										
										<option value="11" ng-if="pageVo.returnType==2">换货中</option>
										<option value="12" ng-if="pageVo.returnType==2">换货成功</option>
										<option value="14" ng-if="pageVo.returnType==2">换货失败</option>
										
										<option value="21" ng-if="pageVo.returnType==1">退货中</option>
										<option value="22" ng-if="pageVo.returnType==1">退货成功</option>
										<option value="24" ng-if="pageVo.returnType==1">退货失败</option>
							  	  </select>
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
		
		<div class="modal fade" id="follow_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">退换货跟踪</h4>
		      </div>
		      <div class="modal-body">
		        	<div class="row">
		        		<div class="col-xs-12">
			        		<ul class="list-group">
							  <li class="list-group-item" ng-repeat="item in refundLogList">
							  		<div class="col-xs-4">
							  			{{item.createTime|date:'yyyy-MM-dd HH:mm:ss'}}
							  		</div>
							  		<div class="col-xs-4">
							  			退款个数：{{item.productNum}}
							  		</div>
							  		
							  		<div class="col-xs-4">
							  		</div>
							  		<div class="col-xs-12">
							  		</div>
							  		
							  		<div class="clearfix"></div>
							  </li>
							</ul>
		        		</div>
		        	</div>
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
									<th width="">退换货号</th>
									<th width="">订单编号</th>
									<th width="">用户号</th>
									<th width="">退换货个数</th>
									<th width="">退换货类型</th>
									<th >退换货状态</th>
									<th >物流名称</th>
									<th >物流单号</th>
									<th >创建时间</th>
									<th width="">操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in returnList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.returnNo}}
								</td>
								<td >
									<a href="" ng-click="showDetail(item.orderNo)">
										{{item.orderNo}}
									</a>
								</td>
								<td >
									{{item.userNo}}
								</td>
								<td >
									{{item.productNum}}
								</td>
								<td >
									{{item.returnType}}
								</td>
								<td >
									{{item.refundState}}
								</td>
								<td >
									{{item.deliverName}}
								</td >
								<td >
									{{item.deliverNo}}
								</td >
								<td >
									{{item.createTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td >
								<td >
									<Button type="button" ng-click="toApprove(item.refundNo)" ng-if="item.refundState==-1" class="btn btn-primary">
										审批&nbsp;&nbsp;<span class="glyphicon glyphicon-hand-down"></span>
									</Button>
									<Button type="button" ng-click="follow(item.refundNo)" class="btn btn-primary">
										跟踪&nbsp;&nbsp;<span class="glyphicon glyphicon-eye-open"></span>
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