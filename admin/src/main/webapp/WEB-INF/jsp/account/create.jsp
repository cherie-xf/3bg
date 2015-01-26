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
					<h4>新建用户账户</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty account.feedbackMessage}">
						<div class="alert alert-success">${account.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			
			<form:form modelAttribute="account" method="post" action="${actionurl}" cssClass="form-inline" role="form">

				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="email" cssClass="col-md-2 control-label">邮箱<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="email" cssClass="form-control input-sm" id="email" placeholder="example@gmail.com"
									maxlength="64" data-validation="email" data-validation-error-msg="请输入正确邮箱!"/>
								</div>
							</div>
						</div>

						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="phone" cssClass="col-md-2 control-label">手机<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="phone" cssClass="form-control input-sm" id="phone" placeholder="phone"
									data-validation-allowing="int" data-validation="number" maxlength="15" data-validation-error-msg="请输入正确手机号!"/>
								</div>
							</div>
						</div>
						
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="lastName" cssClass="col-md-2 control-label">姓
								<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="lastName" cssClass="form-control input-sm" id="lastName" placeholder="姓" 
									maxlength="20" data-validation="required" data-validation-error-msg="请输入姓名!"/>
								</div>
							</div>
						</div>

						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="firstName" cssClass="col-md-2 control-label">名<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="firstName" cssClass="form-control input-sm" id="firstName" placeholder="名" 
									maxlength="20" data-validation="required" data-validation-error-msg="请输入姓名!"/>
								</div>
							</div>
						</div>

						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="password_confirmation" cssClass="col-md-2 control-label">登录密码<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:password path="password_confirmation" id="password_confirmation" cssClass="form-control input-sm"  placeholder="" 
									data-validation="strength" data-validation-strength="1" maxlength="128"/>
								</div>
							</div>
						</div>
						
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="password" cssClass="col-md-2 control-label">重复登录密码<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:password path="password" id="password" data-validation="confirmation"  cssClass="form-control input-sm"  placeholder="" 
									 data-validation-error-msg="密码不一致!" maxlength="128"/>
								</div>
							</div>
						</div>																		
					</div>
				</div>	

				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
						<button type="submit" class="btn btn-success">确定</button>
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
			$.validate({
				 modules : 'security',
				 onModulesLoaded : function() {
					   $('input[name="password_confirmation"]').displayPasswordStrength();
					 }
			  });
		});
	</script>

</body>
</html>