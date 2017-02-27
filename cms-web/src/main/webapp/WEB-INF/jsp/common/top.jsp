<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="top">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<span class="glyphicon glyphicon-leaf"></span>
							CMS管理平台
						</small>
					</a>
				</div>
	
				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav navbar-nav" >
						<li class="light-blue">
							<a data-toggle="dropdown" href="#"  data-toggle="dropdown" class="dropdown-toggle">
								<span class="glyphicon glyphicon-user">
									<small>欢迎光临,</small>
									<sec:authentication property="principal.username"/>
								</span>
								<i class="icon-caret-down"></i>
							</a>
							<ul class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="icon-cog"></i>
										设置
									</a>
								</li>
								<li>
									<a href="#">
										<i class="icon-user"></i>
										个人资料
									</a>
								</li>
	
								<li class="divider"></li>
	
								<li>
									<a href="${ctx}/logout.do">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
	</div>
</div>