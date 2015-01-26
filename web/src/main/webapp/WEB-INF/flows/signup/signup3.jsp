<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>注册完成</title>
<%@ include file="/WEB-INF/inc/head.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css">

</head>
<body>
	<div class="wrap">
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
                        <h4>注册成功</h4>
                        <p>谢谢您注册三桥帐号，确认信已经发送到${signupForm.user.email}</p>
					</div>
				</div>
				<!--col-sm-8-->
			</div>
		</div>
		<!-- main -->
	</div>
	<!--container-->
	<!--wrap end-->

	<!--footer start-->
	<%@include file="/WEB-INF/inc/globalfooter.jsp"%>
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script>
		// $.validate({
		//     form:'#contactform'
		// });

		$(document).ready(function() {

		});
	</script>

</body>
</html>