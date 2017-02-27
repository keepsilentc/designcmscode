<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
	<script src="${ctx}/js/controller/productImportStr.js"></script>
	<script src="${ctx}/js/service/productService.js"></script>
</head>
<body>

	<div ng-controller="productImportStr" ng-cloak>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12" >
					<ol class="breadcrumb" style="height:50px">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">产品导入</a></li>
					  <li class="active">Data</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-xs-10">
						<div class="col-xs-5">
							<p class="red">
								注：上传格式为.xls或.xlsx
							</p>
						</div>
						<div class="col-xs-8 pull-right">
							<div class="input-group">
								<input type="text" ng-model="fileName" ng-readonly="true" class="form-control">
								<div class="input-group-btn">
									<button class="btn btn-info" ngf-select="upload($file)" ngf-accept="'application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'"  ngf-max-size="10MB" id="upload" data-loading-text="Uploading..." type="button">
										上传&nbsp;<span class="glyphicon glyphicon-plane"></span>
									</button>
								</div>
							</div>
		 				</div>
		 			</div>
				</div>
			</div>
			<hr>
		<div class="row">
			<div class="col-xs-12">
				<table id="tablelist" class="table table-striped table-bordered table-hover ">
					<thead>
						<tr>
							<th>#</th>
							<th>行号</th>
							<th>错误描述</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in errorList">
							<td >
								{{$index+1}}
							</td>
							
							<td >
								{{item.lineNo}}
							</td>
							
							<td >
								{{item.desc}}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>