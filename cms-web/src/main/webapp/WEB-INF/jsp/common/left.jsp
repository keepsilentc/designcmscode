<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="left">
		<div class="row">
			<div class="col-xs-12" style="height:50px;">
				<div class="col-xs-12" style="height:50px;padding:8px 0px;background-color:#ccc;">
					<button type="button" class="col-xs-3 btn btn-success"><span class="glyphicon glyphicon-euro"></span></button>
					<button type="button" class="col-xs-3 btn btn-info"><span class="glyphicon glyphicon-music"></span></button>
					<button type="button" class="col-xs-3 btn btn-warning"><span class="glyphicon glyphicon-flag"></span></button>
					<button type="button" class="col-xs-3 btn btn-danger"><span class="glyphicon glyphicon-heart"></span></button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12" >
				<ul class="list-group">
				   <sec:authorize ifAllGranted="ROLE_PRODUCT_INDEX">
				   		<li class="list-group-item">
					  		<span class="glyphicon glyphicon-tasks pull-left"></span>
						  	<a href="${ctx}/product/index.do">
								商品
							</a>
				 		 </li>
				   </sec:authorize>
				   
				   <sec:authorize ifAllGranted="ROLE_STATUS_INDEX">
					   		<li class="list-group-item">
						  		<span class="glyphicon glyphicon-book pull-left"></span>
							  	<a href="${ctx}/status/index.do">
									文章
								</a>
					 		 </li>
				   </sec:authorize>
				   
				   <sec:authorize ifAllGranted="ROLE_DESIGNER_INDEX">
						  <li class="list-group-item">
						   	 	<span class="glyphicon glyphicon-link pull-left"></span>
							  	<a href="${ctx}/designer/index.do">
									设计师
								</a>
						  </li>
					</sec:authorize>
					
					<sec:authorize ifAllGranted="ROLE_THEME_INDEX">
						  <li class="list-group-item">
						  		<span class="glyphicon glyphicon-paperclip pull-left"></span>
							  	<a href="${ctx}/theme/index.do">
									系列
								</a>
						  </li>
					</sec:authorize>
				   
				   <sec:authorize ifAllGranted="ROLE_CATEGORY_INDEX">
						  <li class="list-group-item">
						  		<span class="glyphicon glyphicon-fire pull-left"></span>
							  	<a href="${ctx}/category/index.do">
									分类
								</a>
						  </li>
					</sec:authorize>
					
					<sec:authorize ifAllGranted="ROLE_COLOR_INDEX">
						  <li class="list-group-item">
						  		<span class="glyphicon glyphicon-adjust pull-left"></span>
							  	<a href="${ctx}/color/index.do">
									颜色
								</a>
						  </li>
					</sec:authorize>
				   
				   <sec:authorize ifAllGranted="ROLE_SIZE_INDEX">
						  <li class="list-group-item">
						  		<span class="glyphicon glyphicon-glass pull-left"></span>
							  	<a href="${ctx}/size/index.do">
									尺寸
								</a>
						  </li>
				  </sec:authorize>
				  
				  <sec:authorize ifAllGranted="ROLE_PAYLOAD_INDEX">
						  <li class="list-group-item">
						  		<span class="glyphicon glyphicon-text-width pull-left"></span>
							  	<a href="${ctx}/payLoad/index.do">
									推送
								</a>
						  </li>
				  </sec:authorize>
				   
				    <sec:authorize ifAllGranted="ROLE_CAROUSEL_INDEX">
						  <li class="list-group-item">
						  		<span class="glyphicon glyphicon-picture pull-left"></span>
							  	<a href="${ctx}/carousel/index.do">
									轮播图
								</a>
						  </li>
					</sec:authorize>
				   
				  <sec:authorize ifAllGranted="ROLE_COUPON_INDEX">
					  <li class="list-group-item">
					  		<span class="glyphicon glyphicon-arrow-down pull-left"></span>
						  	<a href="${ctx}/coupon/index.do">
								优惠券
							</a>
					  </li>
				  </sec:authorize>
				   
				   <sec:authorize ifAllGranted="ROLE_ORDER_INDEX">
				   		<li class="list-group-item">
					  		<span class="glyphicon glyphicon-heart pull-left"></span>
						  	<a href="${ctx}/order/index.do">
								订单
							</a>
				  		</li>
				   </sec:authorize>
				   
				   <sec:authorize ifAllGranted="ROLE_REFUND_INDEX">
				   		<li class="list-group-item">
					  		<span class="glyphicon glyphicon-book pull-left"></span>
						  	<a href="${ctx}/refund/index.do">
								退款
							</a>
				  		</li>
				   </sec:authorize>
				   
				   <sec:authorize ifAllGranted="ROLE_RETURN_INDEX">
				   		<li class="list-group-item">
					  		<span class="glyphicon glyphicon-wrench pull-left"></span>
						  	<a href="${ctx}/return/index.do">
								退换货
							</a>
				  		</li>
				   </sec:authorize>
				  
				  <sec:authorize ifAllGranted="ROLE_USER_INDEX">
				   		<li class="list-group-item">
					  		<span class="glyphicon glyphicon-user pull-left"></span>
						  	<a href="${ctx}/user/index.do">
								尺寸用户
							</a>
				  		</li>
				   </sec:authorize>
				  
				  <sec:authorize ifAllGranted="ROLE_CMSUSER_INDEX">
				   		<li class="list-group-item">
					  		<span class="glyphicon glyphicon-user pull-left"></span>
						  	<a href="${ctx}/cmsuser/index.do">
								CMS用户
							</a>
				  		</li>
				   </sec:authorize>
				   
				  <sec:authorize ifAllGranted="ROLE_ROLE_INDEX">
					  <li class="list-group-item">
					  		<span class="glyphicon glyphicon-th-large pull-left"></span>
						  	<a href="${ctx}/role/index.do">
								CMS角色
							</a>
					  </li>
				  </sec:authorize>
				  
				</ul>
			</div>
		</div>
</div>