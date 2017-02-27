<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="tc">
<head>
<title>尺寸cms管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${ctx}/js/app/app.js"></script>
</head>
<body>
	<div class="container-fluid" style="height:100%;background-color:#ccc">
		<div class="row">
			<div class="col-xs-10 col-xs-offset-1">
				<div>
					<div class="center">
						<h1>
							<span class="glyphicon glyphicon-leaf"></span>
							<span class="red">CMS</span>
							<span class="white">管理系统</span>
						</h1>
						<h4 class="blue">&copy; 尺寸</h4>
					</div>
					<div style="width:360px;margin:20px auto;padding:30px 20px;background-color:#FFF;" ng-cloak>
						<div class="center">
							<label>please enter your information</label>
							<p class="red" style="text-align:right;">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.getMessage()}</p>
							<hr>
						</div>
						<form action="${ctx}/login.do" name="loginform" role="form" method="post" novalidate>
							  <div class="form-group">
							    <label>用户名</label>
							    <span class="pull-right" ng-show="loginform.username.$error.required">用户名是必须的</span>
							    <div class="input-group">
							      <input class="form-control" name="username" ng-model="username" required type="text" placeholder="用户名">
							      <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="exampleInputPassword1">密码</label>
							    <span class="pull-right" ng-show="loginform.password.$error.required">密码是必须的</span>
							    <div class="input-group">
							      <input class="form-control" name="password" ng-model="password" required type="password" placeholder="密码">
							      <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
							    </div>
							  </div>
							  <button type="submit" class="btn btn-default pull-right">Login&nbsp;&nbsp;<span class="glyphicon glyphicon-send"></span></button>
						</form>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>