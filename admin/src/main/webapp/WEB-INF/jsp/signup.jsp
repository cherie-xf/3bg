<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>管理员注册</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
</head>
<body>
	<div class="wrap">

		<!--container Start-->
		<div class="container main">
			<div class="row">
				<div class="col-md-12">
					<ol class="breadcrumb">
						<li class="active">管理员注册</li>
					</ol>
				</div>
			</div>

			<form:form modelAttribute="signupquery" method="post"
				action="signup.html" cssClass="form-horizontal" role="form">
				<div class="row">
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-3"></div>
							<div class="col-md-9">
								<c:if test="${not empty userquery.feedbackMessage}">
									<div class="alert alert-warning">${userquery.feedbackMessage}</div>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<form:label path="email" cssClass="col-md-3 control-label">邮件地址<font
											color="red">*</font>:</form:label>
									<div class="col-md-9">
										<form:input path="email" cssClass="form-control"
											placeholder="your login account，for example: yourname@gmail.com"
											maxlength="45" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<form:label path="password" cssClass="col-md-3 control-label">密码<font
											color="red">*</font>:</form:label>
									<div class="col-md-9">
										<form:password path="password" cssClass="form-control"
											placeholder="password" maxlength="128" />
										<form:errors path="password" cssClass="error" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<form:label path="verifyPassword"
										cssClass="col-md-3 control-label">确认密码<font
											color="red">*</font>:</form:label>
									<div class="col-md-9">
										<form:password path="verifyPassword" cssClass="form-control"
											placeholder="verifyPassword" maxlength="128" />
										<form:errors path="verifyPassword" cssClass="error" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" style="text-align: center">
								<button type="submit" id="submit" class="btn btn-success">
									注册</button>
							</div>
						</div>

					</div>
					<div class="col-md-6">
						<!-- other description, such as the register rule -->
					</div>


				</div>
			</form:form>

			<div class="row myrow"></div>
		</div>
		<!--container-->

	</div>
	<!--wrap end-->

	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script>
		$(document).ready(function() {
			console.log("load dictionary");
			var getting = $.get("system/refreshDictionary.json");
			getting.done(function(data) {
				console.log(data);
			});
		});
	</script>

</body>
</html>