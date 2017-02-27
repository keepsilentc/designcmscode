<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/refundStr.js"></script>
	<script src="${ctx}/js/service/refundService.js"></script>
	<script src="${ctx}/js/service/orderService.js"></script>
	<script src="${ctx}/js/service/userService.js"></script>
	<script src="${ctx}/js/directive/orderdetail.js"></script>
	<script src="${ctx}/js/directive/userInfo.js"></script>
	<script src="${ctx}/js/directive/follow.js"></script>
	<script src="${ctx}/js/filter/filters.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right" ng-controller="refundStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">退款</a></li>
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
						        	退款请求号
						      </span>
						      <input type="text" class="form-control" ng-model="pageVo.refundNo">
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
						<div class="col-xs-3">
							<div class="input-group">
								   <span class="input-group-addon">
						        		退款方式
							       </span>
							       <select class="form-control" ng-model="pageVo.refundPayTypeId">
										<option value="">全部</option>
										<option value="ALIPAY">支付宝</option>
										<option value="WEIXIN">微信</option>
										<option value="PAYPAL">PAYPAL</option>
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
						<div class="col-xs-3 bottom">
							<div class="input-group">
							   	  <span class="input-group-addon">
							        	退款类型
							      </span>
							      <select class="form-control" ng-model="pageVo.refundType">
										<option value="">全部</option>
										<option value="1">退款</option>
										<option value="2">退货</option>
							  	  </select>
						    </div>
						</div>
						<div class="col-xs-3 bottom">
							<div class="input-group">
								   <span class="input-group-addon">
						        		退款状态
							       </span>
							       <select class="form-control" ng-model="pageVo.refundState">
										<option value="">全部</option>
										<option value="-1">退款审核中</option>
										<option value="1">退款审核通过</option>
										<option value="0">退款审核失败</option>
										<option value="10">未退款</option>
										<option value="20">退款成功</option>
										<option value="40">退款失败</option>
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
		        <h4 class="modal-title" id="myModalLabel">退款跟踪</h4>
		      </div>
		      <div class="modal-body">
		        	<follow refund_no="refundNo"></follow>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
									<th width="">退款请求号</th>
									<th width="">订单编号</th>
									<th width="">用户号</th>
									<th width="">退款个数</th>
									<th width="">退款金额</th>
									<th >退款状态</th>
									<th >退款方式</th>
									<th >退款类型</th>
									<th >创建时间</th>
									<th width="">操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in refundList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.refundNo}}
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
									{{item.refundNum}}
								</td>
								<td >
									{{item.refundMoney}}
								</td>
								<td >
									<label ng-if="item.refundState==-1">退款审核中</label>
									<label ng-if="item.refundState==1">退款审核通过</label>
									<label ng-if="item.refundState==0">退款审核失败</label>
									<label ng-if="item.refundState==10">未退款</label>
									<label ng-if="item.refundState==20">退款成功</label>
									<label ng-if="item.refundState==40">退款失败</label>
								</td>
								<td >
									{{item.refundPayTypeId}}
								</td >
								<td >
									<label ng-if="item.refundType==1">退款</label>
									<label ng-if="item.refundType==2">退货</label>
								</td >
								<td >
									{{item.createTime|date:'yyyy-MM-dd HH:mm:ss'}}
								</td >
								<td >
									<sec:authorize ifAllGranted="ROLE_REFUND_APPROVE_INDEX">
										<Button type="button" ng-click="toApprove(item.refundNo)" ng-if="item.refundState==-1" class="btn btn-primary">
											审批&nbsp;&nbsp;<span class="glyphicon glyphicon-hand-down"></span>
										</Button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_REFUND_DO">
										<Button type="button" ng-click="doRefund(item.refundNo)" ng-if="item.refundState==1" class="btn btn-primary">
											退款&nbsp;&nbsp;<span class="glyphicon glyphicon-bookmark"></span>
										</Button>
									</sec:authorize>
									
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