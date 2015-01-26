<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>个人信息管理</title>
<%@ include file="/WEB-INF/inc/simplehead.jsp"%>
</head>
<body class="nobackgournd">
	<div class="container tbscontainer">

		<div class="row">
			<div class="row myrow">
				<div class="col-md-12">
					<div class="col-md-2"></div>
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


			<form:form modelAttribute="contact" method="post" action="phone.html"
				class="form-horizontal" role="form">
				<form:hidden path="operate" />
				<form:hidden path="user_id" />
				<form:hidden path="id" />
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label for="type_code"
								Class="col-xs-12 col-sm-2 col-md-3 control-label">分类:</label>
							<div class="col-xs-12 col-sm-8 col-md-8">
								<form:select path="type_code" cssClass="form-control input-sm"
									data-validation="required" data-validation-error-msg="请选择分类!">
									<form:option value="">----Select----</form:option>
									<form:options items="${applicationScope.phoneContactTypeMap}" />
								</form:select>
							</div>
						</div>
					</div>
				</div>
				<div class="row myrow">
					<div class="col-md-12">
					<div class="form-group">
						
						<label for="contact_value" Class="col-xs-12 col-sm-2 col-md-3 control-label">电话号码:</label>
						<div class="col-xs-12 col-sm-8 col-md-8">
							<form:input path="contact_value" cssClass="form-control input-sm"
								placeholder="Enter phone number" maxlength="20"
								data-validation="number" data-validation-error-msg="请输入正确电话号码!" />
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


	<!--wrap end-->
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script>
		$(document).ready(function(){
					$.validate();
		});
	</script>

</body>
</html>