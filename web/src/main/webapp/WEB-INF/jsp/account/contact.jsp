<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>个人信息管理</title>
<!--#include file="header.shtml" -->
<%@ include file="/WEB-INF/inc/head.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css" />

</head>
<body>
	<div class="wrap">
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/globalnav.jsp"%>
		<!--globalnav end-->

		<div class="container tbscontainer">
			<div class="main">
				<div class="row">
					<div class="col-sm-2">
                       <%@ include file="/WEB-INF/inc/leftbar.jsp"%>
                    </div>
					<div class="col-sm-10">
						<form:form method="get" action="${actionurl}" class="form-horizontal" role="form">
							<div class="row">
								<div class="col-sm-12">
									<ol class="breadcrumb mybreadcrumb">
									  <li class="active">我的服务宝</li>
									  <li class="active">个人信息管理</li>
									  <li class="active">联系方式</li>
									</ol>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<h2 class="yonghu" style="text-align: left; padding: 10px">
											联系电话&nbsp;<a href="phone.html"
												class="openEditPage"><span
												class="glyphicon glyphicon-plus-sign"></span></a>
										</h2>
									</div>
								</div>
							</div>

							<c:forEach items="${phoneContactList}" var="var" varStatus="rowCounter">
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label class="col-sm-2" style="text-align:right;">
											   <c:set var="type_code" value="${var.type_code}" />
											   ${contactTypeMap[type_code]}：
											</label>
											<div class="col-sm-10">
												<span class="tanchu2">${var.contact_value}</span>&nbsp;&nbsp;
												<a href="phone.html?id=${var.id}" class="openEditPage">
												<span class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
												<a href="deletecontact/${var.id}.html" class="openEditPage">
												<span class="glyphicon glyphicon-minus-sign"></span></a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<h2 class="yonghu" style="text-align: left; padding: 10px">
											联系邮箱&nbsp;<a href="email.html"
									class="openEditPage"><span
												class="glyphicon glyphicon-plus-sign"></span></a>
										</h2>
									</div>
								</div>
							</div>
							<c:forEach items="${emailContactList}" var="var"
								varStatus="rowCounter">
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label class="col-sm-2" style="text-align:right;">
											  <c:set var="type_code" value="${var.type_code}" />${contactTypeMap[type_code]}：
											</label>
											<div class="col-sm-10">
												<span class="tanchu2">${var.contact_value}</span>&nbsp;&nbsp;<a
													href="email.html?id=${var.id}"
													class="openEditPage"> <span
													class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;<a
													href="deletecontact/${var.id}.html" class="openEditPage"> <span
													class="glyphicon glyphicon-minus-sign"></span></a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<h2 class="yonghu" style="text-align: left; padding: 10px">
											社交网络账号&nbsp;<a href="social.html"
									class="openEditPage"><span
												class="glyphicon glyphicon-plus-sign"></span></a>
										</h2>
									</div>
								</div>
							</div>
							<c:forEach items="${socialContactList}" var="var"
								varStatus="rowCounter">
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label class="col-sm-2" style="text-align:right;">
											  <c:set var="type_code" value="${var.type_code}" />
											  ${contactTypeMap[type_code]}：
											</label>
											<div class="col-sm-10">
												<span class="tanchu2">${var.contact_value}</span>&nbsp;&nbsp;<a
													href="social.html?id=${var.id}"
													class="openEditPage"> <span
													class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;<a
													href="deletecontact/${var.id}.html" class="openEditPage"> <span
													class="glyphicon glyphicon-minus-sign"></span></a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>

							
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<h2 class="yonghu" style="text-align: left; padding: 10px">
											联系地址&nbsp;<a href="address.html"
									class="openEditPage"><span
												class="glyphicon glyphicon-plus-sign"></span></a>
										</h2>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label class="col-sm-2" style="text-align:right;">地址:</label>

										<div class="col-xs-12 col-sm-8 col-md-8">
											<c:forEach items="${addressList}" var="var"
												varStatus="rowCounter">
												<div class="row">
													<div class="col-md-12 ">
														<span class="tanchu2">${var.address} </span> <span>${var.district}</span>
														<span class="tanchu2"><c:set var="city_code"
																value="${var.city_code}" />
															${applicationScope.cityMap[var.city_code]}</span>&nbsp;&nbsp;<span class="tanchu2">${var.postal_code}</span>
														&nbsp;&nbsp;<a
															href="address.html?address_id=${var.address_id}"
															class="openEditPage"> <span
															class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;<a
															href="deleteaddress/${var.address_id}.html" class="openEditPage"> <span
															class="glyphicon glyphicon-minus-sign"></span></a>
													</div>
												</div>

											</c:forEach>

										</div>

									</div>
								</div>
							</div>
							<!-- 
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<div class="col-sm-10" id="queding" style="text-align: center">
											<button type="button" class="btn btn-default btn-lg">确定</button>
										</div>
									</div>
								</div>
							</div>
							 -->
						</form:form>
						<!------------------表单------------------>








					</div>
				</div>
			</div>
			<!--container-->
		</div>
		<!--wrap end-->
		<!--#include file="footer.shtml" -->
		<%@include file="/WEB-INF/inc/globalfooter.jsp"%>
		<%@include file="/WEB-INF/inc/loadjs.jsp"%>
		<script src="/js/ui/jquery.ui.core.js"></script>
		<script src="/js/ui/jquery.ui.widget.js"></script>
		<script src="/js/ui/jquery.ui.datepicker.js"></script>
		<script>
			$(document).ready(function() {

				$.validate();

				var framewidth="50%";
				var browserwidth=($(window).width());
				if(browserwidth<768){
					framewidth="100%";
				}				
				
				$(".openEditPage").colorbox({
					iframe : true,
					fixed:true,
					width : framewidth,
					height : "40%",
					onClosed : function() {
						document.forms[0].submit();
					}
				});

				$(".openViewPage").colorbox({
					iframe : true,
					fixed:true,
					width : framewidth,
					height : "40%",
					onClosed : function() {
					}
				});

			});
		</script>
</body>
</html>