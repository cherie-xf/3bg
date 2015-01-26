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
							action="basicUpdate.html" cssClass="form-horizontal" role="form">
							<form:hidden path="feedbackMessage" id="feedbackMessage" />
							<form:hidden path="email" id="email" />
							<form:hidden path="phone" id="phone" />
							<div class="row">
								<div class="col-sm-12">
									<ol class="breadcrumb mybreadcrumb">
									  <li class="active">我的服务宝</li>
									  <li class="active">个人信息管理</li>
									  <li class="active">基本信息</li>
									</ol>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label class="col-xs-12 col-sm-2 col-md-3 control-label">邮箱：</label>
										<div class="col-xs-12 col-sm-8 col-md-8">
											<p class="form-control-static">${account.email}</p>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label class="col-xs-12 col-sm-2 col-md-3 control-label">手机：</label>
										<div class="col-xs-12 col-sm-8 col-md-8">
											<p class="form-control-static">${account.phone}</p>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputxing"
											class="col-xs-12 col-sm-2 col-md-3 control-label">姓：</label>
										<div class="col-xs-12 col-sm-8 col-md-8">
											<form:input path="lastName" Class="form-control"
												placeholder="姓" maxlength="20" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputming"
											class="col-xs-12 col-sm-2 col-md-3 control-label">名：</label>
										<div class="col-xs-12 col-sm-8 col-md-8">

											<form:input path="firstName" Class="form-control"
												placeholder="名" maxlength="20" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputenglishname"
											class="col-xs-12 col-sm-2 col-md-3 control-label">英文名：</label>
										<div class="col-xs-12 col-sm-8 col-md-8">
											<form:input path="preferredName" Class="form-control"
												placeholder="" maxlength="45" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inlineRadio1"
											class="col-xs-12 col-sm-2 col-md-3 control-label">性别：</label>
										<div class="col-xs-12 col-sm-8 col-md-8">
											<form:label path="gender" class="radio-inline">
												<form:radiobutton path="gender" name="gender" value="M" />男
											</form:label>
											<form:label path="gender" class="radio-inline">
												<form:radiobutton path="gender" name="gender" value="F" />女
											</form:label>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputEmail3"
											class="col-xs-12 col-md-3 control-label">生日：</label> <input
											id="birthday" name="userProfile.birthday"
											class="form-control" type="hidden" value="">
										<div class="col-sm-3">
											<div class="input-group">
												<form:select path="yearOfBirth" Class="form-control">
													<form:option value="">Year</form:option>
													<form:options items="${yearMap}" />
												</form:select>
												<span class="input-group-addon">年</span>
											</div>
										</div>
										<div class="col-sm-3">
											<div class="input-group">
												<form:select path="monthOfBirth" Class="form-control">
													<form:option value="">Month</form:option>
													<form:options items="${monthMap}" />
												</form:select>
												<span class="input-group-addon">月</span>
											</div>
										</div>
										<div class="col-sm-3">
											<div class="input-group">
												<form:select path="dayOfBirth" cssClass="form-control">
													<form:option value="">Day</form:option>
													<form:options items="${dayOfMonthMap}" />
												</form:select>
												<span class="input-group-addon">日</span>
											</div>
										</div>


									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="Selectblood"
											class="col-xs-12 col-sm-2 col-md-3 control-label">血型：</label>
										<div class="col-xs-12 col-sm-2 col-md-2 ">
											<form:select path="bloodType" cssClass="form-control ">
												<form:option value="">--Select--</form:option>
												<form:options items="${bloodTypeMap}" />
											</form:select>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="Selectlang"
											class="col-xs-12 col-sm-2 col-md-3 control-label">语言：</label>
										<div class="col-xs-12 col-sm-2 col-md-2" id="yuyan">
											<form:select path="preferredLanguage"
												cssClass="form-control ">
												<form:option value="">--Select--</form:option>
												<form:options items="${applicationScope.languageMap}" />
											</form:select>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputHuzhao"
											class="col-xs-12 col-sm-2 col-md-3 control-label">护照号：</label>
										<div class="col-xs-12 col-sm-8 col-md-8">
											<form:input path="passportNumber" cssClass="form-control"
												placeholder="" maxlength="45" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputSfz"
											class="col-xs-12 col-sm-2 col-md-3 control-label">中国身份证号：</label>
										<div class="col-xs-12 col-sm-8 col-md-8">
											<form:input path="chineseIdNumber" cssClass="form-control"
												placeholder="" maxlength="45" data-validation="length"
												data-validation-length="max18"
												data-validation-optional="true" />
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputJz"
											class="col-xs-12 col-sm-2 col-md-3 control-label">本地驾照号：</label>
										<div class="col-xs-12 col-sm-8 col-md-8">
											<form:input path="driverLicense" cssClass="form-control"
												placeholder="" maxlength="45" data-validation="length"
												data-validation-length="max18"
												data-validation-optional="true" />
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

					</div>
					<!-- end col-md-9 -->
					
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