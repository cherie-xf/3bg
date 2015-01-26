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
		<%@ include file="/WEB-INF/inc/topnav.jsp"%>
		<!--globalnav end-->
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/globalnav.jsp"%>
		<!--globalnav end-->




		<!----------------左侧边栏---------------->
		<!--#include file="leftbar.html" -->

		<!----------------右侧边栏---------------->
		<!--<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main1">-->

		<div class="container tbscontainer">
			<div class="main">
				<div class="row">
				 <div class="col-sm-12 col-md-9">
				 </div>
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
			
			function showAlert(containerId, alertType, message) {
			    $("#" + containerId).append('<div class="alert alert-' + alertType + '" id="alert' + containerId + '">' + message + '</div>');
			    $("#" + containerId).alert();
			    window.setTimeout(function () { $("#" + containerId).alert('close'); }, 10000);
			}
			var message = $("#feedbackMessage").val();
			if(message != null&& message !="")
			{
				showAlert("alertMessageDiv", "info", message);
			}
			
			
		});
	</script>
</body>
</html>