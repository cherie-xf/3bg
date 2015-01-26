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
						<div class="row myrow">
							<div class="col-md-12 " id="alertMessageDiv"></div>
						</div>
						<!-----------表单---------------------->
						<form:form modelAttribute="account" method="post"
							action="other.html" class="form-horizontal" role="form">
							<form:hidden path="feedbackMessage" id="feedbackMessage" />
							<form:hidden path="id" />
							<div class="row">
								<div class="col-sm-12">
									<ol class="breadcrumb mybreadcrumb">
									  <li class="active">我的服务宝</li>
									  <li class="active">个人信息管理</li>
									  <li class="active">其他档案</li>
									</ol>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label class="col-xs-12 col-sm-12 col-md-12 control-label"
											style="text-align: left">职业信息：</label>
										<div class="col-xs-12 col-sm-12 col-md-12">
											
											<form:textarea path="profession"
										cssClass="form-control input-sm editbox" rows="6" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label class="col-xs-12 col-sm-12 col-md-12 control-label"
											style="text-align: left">健康信息：</label>
										<div class="col-xs-12 col-sm-12 col-md-12">
											<form:textarea path="health"
										cssClass="form-control input-sm editbox" rows="6" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label class="col-xs-12 col-sm-12 col-md-12 control-label"
											style="text-align: left">家庭成员信息：</label>
										<div class="col-xs-12 col-sm-12 col-md-12">
											<form:textarea path="familyInfo"
										cssClass="form-control input-sm editbox" rows="6" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label class="col-xs-12 col-sm-12 col-md-12 control-label"
											style="text-align: left">汽车信息：</label>
										<div class="col-xs-12 col-sm-12 col-md-12">
											<form:textarea path="carInfo"
										cssClass="form-control input-sm editbox" rows="6" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label class="col-xs-12 col-sm-12 col-md-12 control-label"
											style="text-align: left">宠物信息：</label>
										<div class="col-xs-12 col-sm-12 col-md-12">
											<form:textarea path="petInfo"
										cssClass="form-control input-sm editbox" rows="6" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label class="col-xs-12 col-sm-12 col-md-12 control-label"
											style="text-align: left">贵重物品：</label>
										<div class="col-xs-12 col-sm-12 col-md-12">
											<form:textarea path="propertyInfo"
										cssClass="form-control input-sm editbox" rows="6" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<div class="col-sm-10" id="queding" style="text-align: center">
											<button type="submit" class="btn btn-success btn-lg">确定</button>
										</div>
									</div>
								</div>
							</div>
						</form:form>
						<!------------------表单------------------>

					</div>


				</div>
				<div class="row">
					<div class="col-sm-12">
						<div id="insertHtml" style="display: none"></div>
						<div id="insertValue" style="display: none"></div>
						<!-- other description, such as the register rule -->
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
	<script type="text/javascript" src="/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymceinit_web.js"></script>
	<script>
		$(document).ready(
				function() {

					$.validate();

					function showAlert(containerId, alertType, message) {
						$("#" + containerId).append(
								'<div class="alert alert-' + alertType + '" id="alert' + containerId + '">'
										+ message + '</div>');
						$("#" + containerId).alert();
						window.setTimeout(function() {
							$("#" + containerId).alert('close');
						}, 10000);
					}
					var message = $("#feedbackMessage").val();
					if (message != null && message != "") {
						showAlert("alertMessageDiv", "info", message);
					}

				});
	</script>
</body>
</html>