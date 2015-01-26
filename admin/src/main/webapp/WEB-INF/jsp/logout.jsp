<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>退出管理员系统</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
</head>
<body>
	<div class="wrap">

		<!--container Start-->
		<div class="container main">
			<div class="row">
				<div class="col-md-12">
					<ol class="breadcrumb">
						<li class="active">退出管理员系统</li>
					</ol>
				</div>
			</div>

			<form:form modelAttribute="logoutquery" method="post"
				action="j_spring_security_logout" cssClass="form-horizontal"
				role="form">
				<div class="row">
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-3"></div>
							<div class="col-md-9">
								<c:if test="${not empty logoutquery.feedbackMessage}">
									<div class="alert alert-warning">${logoutquery.feedbackMessage}</div>
								</c:if>
							</div>
						</div>
						<div class="row">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="col-md-12" style="text-align: center">
								<button type="submit" id="submit" class="btn btn-success">
									退出</button>
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