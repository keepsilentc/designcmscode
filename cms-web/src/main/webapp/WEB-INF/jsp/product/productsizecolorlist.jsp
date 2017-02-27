<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/productSizeColorStr.js"></script>
	<script src="${ctx}/js/controller/productSizeColorAddStr.js"></script>
	<script src="${ctx}/js/service/colorService.js"></script>
	<script src="${ctx}/js/service/sizeService.js"></script>
	<script src="${ctx}/js/service/commonService.js"></script>
	<script src="${ctx}/js/service/productSizeColorService.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
</head>
<body>
	
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>
	<div id="right">
		<div class="container-fluid" ng-controller="productSizeColorStr" ng-cloak>
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="${ctx}/product/index.do">产品</a></li>
					  <li><a href="#">产品颜色尺寸</a></li>
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
					        		尺寸
							    </span>
							    <select class="form-control" ng-model="pageVo.sizeId">
									 <option value="">全部</option>
									 <option value="{{size.id}}" ng-repeat="size in sizeList">{{size.sizeName}}</option>
							   </select>
		        			</div>
		        		</div>
		        		<div class="col-xs-3">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		颜色
						       </span>
						       <select class="form-control"  ng-model="pageVo.colorId">
							       <option value="">全部</option>
								   <option value="{{color.id}}" ng-repeat="color in colorList">{{color.name}}</option>
							   </select>
		        			</div>
		        		</div>
		        		<div class="col-xs-4">
					        <button class="btn btn-info" ng-click="search()" id="search" data-loading-text="Loading..."  type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
					        <sec:authorize ifAllGranted="ROLE_PRODUCTSIZECOLOR_ADD">
					        	<button class="btn btn-info" data-toggle="modal" ng-click="openModal()" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
					        </sec:authorize>
					        <sec:authorize ifAllGranted="ROLE_PRODUCTSIZECOLOR_EXPORT">
				        	<button class="btn btn-info" data-toggle="modal" ng-click="exports()"  id="export" data-loading-text="Exporting..."  type="button">导出&nbsp;<span class="glyphicon glyphicon-export"></span></button>
				        </sec:authorize>
				        <sec:authorize ifAllGranted="ROLE_PRODUCTSIZECOLOR_IMPORT_INDEX">
				        	<button class="btn btn-info" ng-click="toimport()" type="button">导入&nbsp;<span class="glyphicon glyphicon-import"></span></button>
				        	<button class="btn btn-info" ng-click="downloadTemplate()" type="button">下载模板&nbsp;<span class="glyphicon glyphicon-import"></span></button>
				        </sec:authorize>
		 				</div>
		 			</div>
		 			
			</div>
		</div>
		<hr>
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ng-controller="productSizeColorAddStr">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel"><label ng-if="!productSizeColor.id">新增产品尺寸颜色</label><label ng-if="productSizeColor.id">编辑产品尺寸颜色</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
						<div class="input-group">
							<span class="input-group-addon">
					        	尺寸国家
						    </span>
						    <select class="form-control" ng-change="changeSizeCountry()" ng-model="productSizeColor.sizeCountryId">
								<option value="">全部</option>
								<option value="{{sizeCountry.id}}" ng-repeat="sizeCountry in sizeCountryList">{{sizeCountry.sizeCountryName}}</option>
							</select>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="input-group">
							<span class="input-group-addon">
					        	尺寸类型
					      	</span>
					      	<select class="form-control" ng-change="changeSizeType()" ng-model="productSizeColor.sizeTypeId">
								<option value="">全部</option>
								<option value="{{sizeType.id}}" ng-repeat="sizeType in sizeTypeList">{{sizeType.name}}</option>
						  	</select>
						</div>
					</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		尺寸
							    </span>
							    <select class="form-control" ng-model="productSizeColor.sizeId">
								 	<option value="{{size.id}}" ng-repeat="size in sizeList">{{size.sizeName}}</option>
							   	</select>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		颜色
						       </span>
						       <select class="form-control"  ng-model="productSizeColor.colorId">
								 <option value="{{color.id}}" ng-repeat="color in colorList">{{color.name}}</option>
							   </select>
		        			</div>
		        		</div>
		        		
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 库存
						       </span>
						       <input type="number" ng-model="productSizeColor.inventory" min="0" class="form-control" placeholder="库存">
						       <input type="hidden" id="productNo" value="${productNo}">
		        			</div>
		        		</div>
		        		<div ng-if="productSizeColor.id">
		        			<div class="col-xs-6 bottom">
			        			<div class="input-group">
			        				<span class="input-group-addon">
						        		是否启用
								    </span>
								    <div class="form-control">
								    	<label class="radio-inline">
									    	<input type="radio"  value="1" ng-model="productSizeColor.isEnable">是
										</label>
										<label class="radio-inline">
										    <input type="radio"  value="0" ng-model="productSizeColor.isEnable">否
										</label>
								    </div>
		        			</div>
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="productSizeColor.error" id="error">{{productSizeColor.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" ng-if="!productSizeColor.id" ng-click="add()" class="btn btn-primary">add</button>
		        <button type="button" ng-if="productSizeColor.id" ng-click="update()" class="btn btn-primary">update</button>
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
									<th width="3%"></th>
									<th width="10%">产品编号</th>
									<th width="10%">尺寸</th>
									<th width="10%">颜色</th>
									<th width="10%">库存</th>
									<th width="10%">销量</th>
									<th >产品图片</th>
									<th width="10%">启用状态</th>
									<th width="15%">操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in productSizeColorList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.productNo}}
								</td>
								<td >
									{{item.sizeName}}
								</td>
								<td >
									{{item.colorName}}
								</td>
								<td >
									<label style="width:100%;" ng-show="!item.show" ng-click="item.show=!item.show">{{item.inventory}}</label>
									<input style="width:100%;" type="text" ng-focus="item.show" ng-show="item.show" ng-blur="changeItem(item);item.show=!item.show" ng-model="item.inventory">
								</td>
								<td >
									{{item.sale}}
								</td>
								<td>
									<img ng-src="${ctx}/file/get.do?id={{item.picture}}" alt="..." class="img-thumbnail imgin">
									<button type="button" ngf-select="upload($file,item)" class="btn btn-primary right" style="height:40px;"><span class="glyphicon glyphicon-pencil"></span></button>
								</td>
								<td >
									<label ng-if="item.isEnable==1"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;启用</label>
									<label ng-if="item.isEnable==0"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;未启用</label>
								</td >
								<td >
									<sec:authorize ifAllGranted="ROLE_PRODUCTSIZECOLOR_UPDATE">
										<Button ng-click="openModal(item)" type="button" class="btn btn-primary" >
											<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
										</Button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_PRODUCTSIZECOLOR_QUICKADD">
										<Button ng-click="addProductSizeColor(item,$index)" type="button" class="btn btn-primary">
											<span class="glyphicon glyphicon-plus"></span>&nbsp;新增
										</Button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_PRODUCTSIZECOLOR_DEL">
										<Button ng-click="delProductSizeColor(item,$index)" type="button" class="btn btn-primary">
											<span class="glyphicon glyphicon-remove"></span>&nbsp;删除
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
		
		<div ng-show="false">
			<form action="${ctx}/productSizeColor/export.do" id="exportform" method="post">
				<input type="hidden" name="colorId" ng-value="pageVo.colorId">
				<input type="hidden" name="sizeId" ng-value="pageVo.sizeId">
				<input type="hidden" name="productNo">
			</form>
		</div>
	</div>
</div>
</body>
</html>