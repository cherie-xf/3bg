<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>操作成功</title>
<%@ include file="/WEB-INF/inc/head.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css"/>

</head>
<body>
	<div class="wrap">
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/topnav.jsp"%>
		<!--globalnav end-->
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/globalnav.jsp"%>
		<!--globalnav end-->

		<!--container Start-->
		<div class="container tbscontainer">
			<div class="main">
				<div class="row">
					<div class="col-sm-4">
						<img src="/images/1.jpg" class="img-responsive hidden-xs"
							alt="Responsive image">
					</div>
					<div class="col-sm-8">
					    <div class="col-md-12">
							<c:if test="${not empty message}">
								<div class="alert alert-success">${message}</div>
							</c:if>
						</div>
					</div>
				</div>
				<!--col-sm-8-->
			</div>
			<!-- main -->
		</div>
		<!--container-->
	</div>
	<!--wrap end-->

	<!--footer start-->
	<%@include file="/WEB-INF/inc/globalfooter.jsp"%>
</body>
</html>