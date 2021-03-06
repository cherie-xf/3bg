<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>重置密码</title>
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
						<img src="/images/1.jpg" class="img-responsive hidden-xs" alt="Responsive image">
					</div>
					<div class="col-sm-8">
					    <form:form modelAttribute="resetForm" method="post" action="resetpassword.html" cssClass="form-horizontal">
							<div class="row">
								<div class="col-sm-12">
									<h4 class="yonghu">重置密码</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group" id="mima">
										<label for="password_confirmation" class="col-sm-2 control-label">新密码</label>
										<div class="col-sm-8">
											<form:password path="password_confirmation" data-validation="strength" data-validation-strength="1" class="form-control" placeholder="new password"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">确认密码</label>
										<div class="col-sm-8">
											<form:password path="password" data-validation="confirmation" class="form-control" placeholder="Confirm password"/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-10" style="text-align:center;">
								   <button type="submit" class="btn btn-success"> 重置密码  </button>
								</div>
							</div>
						</form:form>
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
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script>
		$(document).ready(function() {
			$.validate({
				 modules:'security',
				 onModulesLoaded : function() {
				   $('input[name="password_confirmation"]').displayPasswordStrength();
				 }
			});			
		});
	</script>

</body>
</html>