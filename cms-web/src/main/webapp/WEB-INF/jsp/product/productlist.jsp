<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/productStr.js"></script>
	<script src="${ctx}/js/controller/productAddStr.js"></script>
	<script src="${ctx}/js/controller/productDetailStr.js"></script>
	<script src="${ctx}/js/service/designerService.js"></script>
	<script src="${ctx}/js/service/themeService.js"></script>
	<script src="${ctx}/js/service/categoryService.js"></script>
	<script src="${ctx}/js/service/productService.js"></script>
	<script src="${ctx}/js/service/commonService.js"></script>
	<script src="${ctx}/js/directive/pagination.js"></script>
	<script src="${ctx}/js/filter/filters.js"></script>
</head>
<body>
	
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/left.jsp"%>

	<div id="right" ng-controller="productStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">产品</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<!-- Search -->
			<div class="row">
				<div class="col-xs-12">
					<div class="col-xs-10">
						<div class="col-xs-12">
							<div class="col-xs-3">
								<div class="input-group">
									<span class="input-group-addon">
							        	产品编号
								    </span>
								    <input type="text" ng-model="pageVo.productNo" class="form-control" placeholder="产品编号">
								</div>
							</div>
							<div class="col-xs-3">
								<div class="input-group">
									<span class="input-group-addon">
							        	产品名称
							        </span>
							        <input type="text" ng-model="pageVo.productName" class="form-control" placeholder="产品名称">
								</div>
							</div>
							<div class="col-xs-3">
								<div class="input-group">
									<span class="input-group-addon">
										启用状态
								    </span>
								    <select class="form-control" ng-model="pageVo.isEnable">
										<option value="">全部</option>
										<option value="1">启用</option>
										<option value="0">未启用</option>
									</select>
								</div>
							</div>
							<div class="col-xs-3">
								<div class="input-group">
									<span class="input-group-addon">
										产品状态
								    </span>
								    <select class="form-control" ng-model="pageVo.state">
										<option value="">全部</option>
										<option value="10">现货</option>
										<option value="20">预售</option>
									</select>
								</div>
							</div>
					    </div>
					    <div class="col-xs-12 bottom">
					    	<div class="col-xs-3">
					    		<div class="input-group ">
								      <span class="input-group-addon">
								        	国家
								      </span>
								      <select class="form-control" ng-change="changeCountry()" ng-model="pageVo.countryId">
										<option value="">全部</option>
										<option value="{{country.id}}" ng-repeat="country in countryList">{{country.countryName}}</option>
									  </select>
						    	</div>
					    	</div>
					    	<!-- <div class="col-xs-3">
					    		<div class="input-group ">
								      <span class="input-group-addon">
								        	品牌
								      </span>
								      <select class="form-control" ng-change="changeBrand()" ng-model="pageVo.brandId">
										<option value="">全部</option>
										<option value="{{brand.id}}" ng-repeat="brand in brandList">{{brand.name}}</option>
									  </select>
						    	</div>
					    	</div> -->
					    	<div class="col-xs-3">
					    		<div class="input-group">
								      <span class="input-group-addon">
								        	设计师
								      </span>
								      <select class="form-control" ng-change="changeDesigner()" ng-model="pageVo.designerId">
										<option value="">全部</option>
										<option value="{{designer.id}}" ng-repeat="designer in designerList">{{designer.designerName}}</option>
									  </select>
						    	</div>
					    	</div>
					    	
					    	<div class="col-xs-3">
					    		<div class="input-group ">
								      <span class="input-group-addon">
								        	系列
								      </span>
								      <select class="form-control" ng-model="pageVo.themeId">
										<option value="">全部</option>
										<option value="{{theme.id}}" ng-repeat="theme in themeList">{{theme.themeName}}</option>
									  </select>
						    	</div>
					    	</div>
					    	
					    	<div class="col-xs-3">
					    		<div class="input-group ">
								      <span class="input-group-addon">
								        	排序
								      </span>
								      <select class="form-control" ng-model="pageVo.order">
										<option value="1">添加时间倒序</option>
										<option value="2">添加时间正序</option>
										<option value="3">价格倒序</option>
										<option value="4">价格正序</option>
									  </select>
						    	</div>
					    	</div>
					    </div>
		 			</div>
		 			<div class="col-xs-2">
				        <button class="btn btn-info" ng-click="search()" id="search" data-loading-text="Loading..." type="button">查询&nbsp;<span class="glyphicon glyphicon-search"></span></button>
				        <sec:authorize ifAllGranted="ROLE_PRODUCT_ADD">
				        	<button class="btn btn-info" data-toggle="modal" ng-click="openModal(pageVo)" type="button">新增&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
				        </sec:authorize>
				        <sec:authorize ifAllGranted="ROLE_PRODUCT_EXPORT">
				        	<button class="btn btn-info" data-toggle="modal" ng-click="exports()"  id="export" data-loading-text="Exporting..."  type="button">导出&nbsp;<span class="glyphicon glyphicon-export"></span></button>
				        </sec:authorize>
				        <sec:authorize ifAllGranted="ROLE_PRODUCT_IMPORT_INDEX">
				        	<button class="btn btn-info bottom" ng-click="toimport()" type="button">导入&nbsp;<span class="glyphicon glyphicon-import"></span></button>
				        	<button class="btn btn-info bottom" ng-click="downloadTemplate()" type="button">下载模板&nbsp;<span class="glyphicon glyphicon-import"></span></button>
				        </sec:authorize>
		 			</div>
			</div>
		</div>
		<hr>
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ng-controller="productAddStr">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel"><label ng-if="!product.id">新增产品</label><label ng-if="product.id">编辑产品</label></h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row">
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 国家
						       </span>
						       <select class="form-control" ng-change="changeCountry()"  ng-model="product.countryId">
								 <option value="{{country.id}}" ng-repeat="country in countryList">{{country.countryName}}</option>
							   </select>
		        			</div>
		        		</div>
<!-- 		        		<div class="col-xs-6"> -->
<!-- 		        			<div class="input-group"> -->
<!-- 		        			   <span class="input-group-addon"> -->
<!-- 					        		 品牌 -->
<!-- 						       </span> -->
<!-- 						       <select class="form-control" ng-change="changeBrand()"  ng-model="product.brandId"> -->
<!-- 								 <option value="{{brand.id}}" ng-repeat="brand in brandList">{{brand.name}}</option> -->
<!-- 							   </select> -->
<!-- 		        			</div> -->
<!-- 		        		</div> -->
		        		<div class="col-xs-6">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 设计师
						       </span>
						       <select class="form-control" ng-change="changeDesigner()" id="designer_1"  ng-model="product.designerId">
								 <option value="{{designer.id}}" ng-repeat="designer in designerList">{{designer.designerName}}</option>
							   </select>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 系列
						       </span>
						       <select class="form-control"  ng-model="product.themeId">
								 <option value="{{theme.id}}" ng-repeat="theme in themeList">{{theme.themeName}}</option>
							   </select>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 类目
						       </span>
						       <input type="text" ng-model="product.categoryName" ng-readonly="true" class="form-control">
						       <input type="hidden" ng-value="product.cateGoryId">
						       <span class="input-group-btn">
					        		<button class="btn btn-info" ng-click="showcategory=!showcategory"  type="button">修改&nbsp;<span class="glyphicon glyphicon-pencil"></span></button>
						       </span>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom" ng-show="showcategory">
							<ol class="breadcrumb" style="height:50px">
							  <li ng-repeat="level in categorylvel" ng-click="index(level,$index)"><a href="#">{{level.name}}</a></li>
							</ol>
							<div class="input-group" ng-repeat="category in categoryList">
			        			   <span class="form-control" ng-click="changeCategory(category)">
						        		 {{category.categoryName}}
							       </span>
							       <span class="input-group-btn">
						        		<button class="btn btn-info" ng-click="selectCategory(category)" type="button">选择&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
							       </span>
	        				</div>
						</div>
		        		<div class="clearfix"></div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		产品名称
							    </span>
							    <input type="text" ng-model="product.productName" class="form-control" placeholder="产品名称">
		        			</div>
		        		</div>
		        		
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		产品编号
							    </span>
							    <input type="text" ng-model="product.productNo" ng-readonly="product.id" class="form-control" placeholder="产品编号">
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		产品状态
							    </span>
							    <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio"  value="10" ng-model="product.state">现货
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="20" ng-model="product.state">预售
									</label>
							    </div>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom" ng-show="product.state==20">
							<div class="input-group">
		        			   <span class="input-group-addon">
					        		 开始时间
						       </span>
						       <input type="text" class="form-control time" ng-readonly="true" ng-model="product.preSellStartTime" placeholder="开始时间" >
							</div>
						</div>
						<div class="col-xs-6 bottom" ng-show="product.state==20">
							<div class="input-group">
		        			   <span class="input-group-addon">
					        		 结束时间
						       </span>
						       <input type="text" class="form-control time" ng-readonly="true" ng-model="product.preSellEndTime" placeholder="结束" >
							</div>
						</div>
		        		<div class="col-xs-6 bottom" ng-if="product.id">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		是否启用
							    </span>
							    <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio"  value="1" ng-model="product.isEnable">是
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="0" ng-model="product.isEnable">否
									</label>
							    </div>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		是否为代表作
							    </span>
							    <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio"  value="1" ng-model="product.isRepresentative">是
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="0" ng-model="product.isRepresentative">否
									</label>
							    </div>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		是否新品
							    </span>
							    <div class="form-control">
							    	<label class="radio-inline">
								    	<input type="radio"  value="1" ng-model="product.isNew">是
									</label>
									<label class="radio-inline">
									    <input type="radio"  value="0" ng-model="product.isNew">否
									</label>
							    </div>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		原价
							    </span>
							    <input type="number" step="0.01" min="0.00" ng-model="product.originPrice" class="form-control" placeholder="原价">
							    <span class="input-group-addon">
					        		￥
							    </span>
		        			</div>
		        		</div>
		        		<div class="col-xs-6 bottom">
		        			<div class="input-group">
		        				<span class="input-group-addon">
					        		单价
							    </span>
							    <input type="number" step="0.01" min="0.00" ng-model="product.price" class="form-control" placeholder="单价">
							    <span class="input-group-addon">
					        		￥
							    </span>
		        			</div>
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 编辑笔记
						       </span>
						       <textarea class="form-control" rows="5" ng-model="product.productDescribe"></textarea>
		        			</div>
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		单品细节
						       </span>
						       <textarea class="form-control" rows="5" ng-model="product.productDetail"></textarea>
		        			</div>
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 尺码指导
						       </span>
						       <textarea class="form-control" rows="5" ng-model="product.sizeDescribe"></textarea>
		        			</div>
		        		</div>
		        		<div class="col-xs-12 bottom">
		        			<div class="input-group">
		        			   <span class="input-group-addon">
					        		 备注
						       </span>
						       <textarea class="form-control" rows="5" ng-model="product.remark"></textarea>
		        			</div>
		        		</div>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="product.error" id="error">{{product.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" ng-if="!product.id" ng-click="add()" id="save" data-loading-text="Saving..." class="btn btn-primary">add</button>
		        <button type="button" ng-if="product.id" ng-click="update()" id="save" data-loading-text="Saving..." class="btn btn-primary">update</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- Modal -->
		<div class="modal fade" id="myModal_1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ng-controller="productDetailStr">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">编辑图片</h4>
		      </div>
		      <div class="modal-body" >
		        	<div class="row" style="height:430px;overflow-y:scroll;">
		        		<div class="col-xs-4">
		        			<div class="thumbnail" style="height:200px;width:auto;">
						      <img ng-src="${ctx}/file/get.do?id={{master.picture}}" style="width:100%;height:80%;" alt="...">
						      <div class="caption center" style="padding:5px 0px;">
						      		<p class="center">主图</p>
						      </div>
						    </div>
		        		</div>
		        		<div class="col-xs-4" ng-repeat="item in imgList">
		        			<div class="thumbnail" style="height:200px;width:auto;position:relative;">
						      <img ng-src="${ctx}/file/get.do?id={{item.picture}}" ng-if="item.id" ngf-select="uploadPdImg($file,item)" style="position:relative;width:100%;height:80%;" alt="...">
						      <img ng-src="${ctx}/file/get.do?id={{item.picture}}" ng-if="!item.id" style="width:100%;height:80%;" alt="...">
						      <sec:authorize ifAllGranted="ROLE_PRODUCT_REMOVEPRODUCTDETAIL,ROLE_PRODUCT_UPDATEPRODUCTDETAIL">
							      <div style="height:20px;width:100%;position:absolute;padding:10px;top:0px;">
							      		<button style="background:#ccc;" ng-click="remove(item,$index)" class="glyphicon glyphicon-remove pull-right"></button>
							      </div>
						      </sec:authorize>
						      <div class="caption center" style="padding:5px 0px;">
						        	<div class="input-group">
						        		<input type="number" class="form-control" ng-model="item.orderBy" min="0">
						        		<sec:authorize ifAllGranted="ROLE_PRODUCT_UPDATE,ROLE_PRODUCT_UPDATEPRODUCTDETAIL">
						        			<span class="input-group-btn">
						        				<button type="button" ng-click="setMaster(item,$index)" class="btn btn-primary">选为主图</button>
						        			</span>
						        		</sec:authorize>
						        	</div>
						      </div>
						    </div>
		        		</div>
		        		<sec:authorize ifAllGranted="ROLE_PRODUCT_SAVEPRODUCTDETAILS">
		        			<div class="col-xs-4" ngf-select="upload($files)" multiple="multiple">
			        			<div class="col-xs-12" style="padding:40px">
									<span class="glyphicon glyphicon-plus" ></span>
								</div>
		        			</div>
		        		</sec:authorize>
		        	</div>
		      </div>
		      <div class="col-xs-12">
		      		<div class="alert alert-danger" role="alert" ng-show="product.error" id="error">{{product.error}}</div>
		      </div>
		      <div class="clearfix"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <sec:authorize ifAllGranted="ROLE_PRODUCT_SAVEPRODUCTDETAILS">
		       		 <button type="button" ng-click="save()" id="saveDetails" data-loading-text="Saving..." class="btn btn-primary">save</button>
		      	</sec:authorize>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- List -->
		<div class="row">
				<div class="col-md-12">
					<div class="" style="overflow-x:scroll;width:100%;">
						<table id="tablelist" style="overflow-x:scroll;min-width:1330px;width:auto;" class="table table-striped table-bordered table-hover ">
							<thead>
								<tr>
									<th width="40px"></th>
									<th width="140px">设计师名称</th>
									<th width="200px">系列名称</th>
									<th width="100px">类目</th>
									<th width="170px">产品编号</th>
									<th width="190px">产品名称</th>
									<th width="80px">原价</th>
									<th width="80px">价格</th>
									<th width="100px">产品状态</th>
									<th ng-if="showTime" width="200px">时间</th>
									<th width="100px">启用状态</th>
									<th width="150px">产品图片</th>
									<th width="150px">操作</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="item in productList">
								<td >
									{{$index+1}}
								</td>
								<td >
									{{item.designerName}}
								</td>
								<td >
									{{item.themeName}}
								</td>
								<td >
									{{item.categoryName}}
								</td>
								<td >
									{{item.productNo}}
								</td>
								<td >
									{{item.productName}}
								</td>
								<td >
									{{item.originPrice|currency:'￥'}}
								</td>
								<td >
									{{item.price|currency:'￥'}}
								</td>
								<td >
									<label ng-if="item.state==10">现货</label>
									<label ng-if="item.state==20">预售</label>
								</td>
								<th ng-if="showTime">
									<p>{{item.preSellStartTime|date:'yyyy-MM-dd HH:mm:ss'}}</p>
									<p>——</p>
									<p>{{item.preSellEndTime|date:'yyyy-MM-dd HH:mm:ss'}}</p>
								</th>
								<td >
									<label ng-if="item.isEnable==1"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;启用</label>
									<label ng-if="item.isEnable==0"><input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.isEnable" ng-change="changeItem(item)">&nbsp;&nbsp;未启用</label>
								</td >
								<td>
									<img ng-src="${ctx}/file/get.do?id={{item.picture}}" alt="..." class="img-thumbnail imgin">
									<button type="button" ng-click="openupdateImgModal(item)" class="btn btn-primary right" style="height:40px;"><span class="glyphicon glyphicon-pencil"></span></button>
								</td>
								<td >
									<sec:authorize ifAllGranted="ROLE_PRODUCTSIZECOLOR_INDEX">
										<Button ng-click="toProductSizeColor(item.productNo)" type="button" class="btn btn-primary">
											<span class="glyphicon glyphicon-plus"></span>
										</Button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_PRODUCT_UPDATE">
										<Button ng-click="openModal(item)" type="button" class="btn btn-primary">
											<span class="glyphicon glyphicon-edit"></span>
										</Button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_PRODUCT_DEL">
										<Button ng-click="delProduct(item)" type="button" class="btn btn-primary">
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
		<!-- 导出 -->
		<div ng-show="false">
			<form action="${ctx}/product/export.do" id="exportform" method="post">
				<input type="hidden" name="countryId" ng-value="pageVo.countryId">
				<input type="hidden" name="brandId" ng-value="pageVo.brandId">
				<input type="hidden" name="designerId" ng-value="pageVo.designerId">
				<input type="hidden" name="themeId" ng-value="pageVo.themeId">
				<input type="hidden" name="designerName" ng-value="pageVo.designerName">
				<input type="hidden" name="productName" ng-value="pageVo.productName">
				<input type="hidden" name="productNo" ng-value="pageVo.productNo">
				<input type="hidden" name="isEnable" ng-value="pageVo.isEnable">
				<input type="hidden" name="state" ng-value="pageVo.state">
				<input type="hidden" name="startTime" ng-value="pageVo.startTime">
				<input type="hidden" name="endTime" ng-value="pageVo.endTime">
			</form>
		</div>
	</div>
</div>
</body>
</html>