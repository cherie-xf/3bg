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
						<!-----------表单---------------------->
						<div class="row myrow">
							<div class="col-sm-12 " id="alertMessageDiv"></div>
						</div>
						<!-----------表单---------------------->
                            <form:form modelAttribute="updateUser" method="post"
							action="password.html" class="form-horizontal" role="form">
							<form:hidden path="feedbackMessage" id="feedbackMessage" />
								<div class="row">
									<div class="col-sm-12">
										<ol class="breadcrumb mybreadcrumb">
										  <li class="active">我的服务宝</li>
										  <li class="active">个人信息管理</li>
										  <li class="active">修改密码</li>
										</ol>
									</div>
								</div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group" id="mima">
                                            <label for="password" class="col-sm-2 control-label">原密码</label>
                                            <div class="col-sm-8">
                                                <form:input path="operate"  placeholder="old password" class="form-control" type="password" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group" id="mima">
                                            <label for="password" class="col-sm-2 control-label">新密码</label>
                                            <div class="col-sm-8">
                                                <form:input path="password_confirmation"  data-validation="strength" placeholder="new password" class="form-control" data-validation-strength="1" type="password"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-2 control-label">确认新密码</label>
                                            <div class="col-sm-8">
                                                <form:input path="password"  data-validation="confirmation" placeholder="confirm password" class="form-control" type="password" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <div class="col-sm-10" id="queding" style="text-align:center">
                                                <button type="submit" class="btn btn-success btn-lg">确定</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                            <!------------------表单------------------> 
						<!------------------表单------------------>
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
			

			$.validate({
				 modules : 'security',
				 onModulesLoaded : function() {
					   $('input[name="password_confirmation"]').displayPasswordStrength();
					 }
			  });
			
			function showAlert(containerId, alertType, message) {
			    $("#" + containerId).append('<div class="alert alert-' + alertType + '" id="alert' + containerId + '">' + message + '</div>');
			    $("#" + containerId).alert();
			    window.setTimeout(function () { $("#" + containerId).alert('close'); }, 10000);
			}
			var message = $("#feedbackMessage").val();
			var code = "${messageCode}";
			console.log("messagecode"+code);
			if(message != null&& message !="")
			{
				
				if(code == "error")
					{
					showAlert("alertMessageDiv", "danger", message);
					
					}
				else
					{
					showAlert("alertMessageDiv", "info", message);
					}
				
			}
			
				
			
		});
	</script>
</body>
</html>