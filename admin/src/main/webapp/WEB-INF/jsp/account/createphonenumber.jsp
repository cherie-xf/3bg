<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>创建/修改用户账户</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css">
<style type="text/css">
.checkboxdiv {
	display: inline-block;
	width: 120px;
}
</style>
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="row myrow">
				<div class="col-md-12">
					<h4>
						<c:if test="${contact.operate=='add'}">
						增加电话号码
					   	</c:if>
						<c:if test="${contact.operate=='update'}">
						修改电话号码
						</c:if>
					</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty contact.feedbackMessage}">
						<div class="alert alert-success">${contact.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			<form:form modelAttribute="contact" method="post"
				action="createphonenumber.html" cssClass="form-inline" role="form">
				<form:hidden path="operate" />
				<form:hidden path="user_id" />
				<form:hidden path="id" />
				<div class="row myrow">
					<div class="col-md-12">

						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="type_code" cssClass="col-md-2 control-label">分类:</form:label>
								<div class="col-md-8 item-content">
									<form:select path="type_code" cssClass="form-control input-sm" name="type_code" 
									data-validation="required" data-validation-error-msg="请选择分类!">
										<form:option value="">----Select----</form:option>
										<form:options items="${applicationScope.phoneContactTypeMap}" />
									</form:select>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="contact_value"
									cssClass="col-md-2 control-label">电话号码:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="contact_value"
										cssClass="form-control input-sm"
										placeholder="Enter phone number" maxlength="20"
										data-validation-allowing="int" data-validation="number"  data-validation-error-msg="请输入正确电话号码!"/>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
						<button type="submit" class="btn btn-success">
							<c:if test="${contact.operate=='add'}">
						增加
					   	</c:if>
							<c:if test="${contact.operate=='update'}">
						修改
						</c:if>
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymceinit.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$.validate();
			
		});
	</script>

</body>
</html>