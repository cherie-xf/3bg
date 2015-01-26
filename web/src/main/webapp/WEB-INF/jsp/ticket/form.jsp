<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>ticket管理</title>
<%@ include file="/WEB-INF/inc/simplehead.jsp"%>
<style type="text/css">
#wrapper {
	border: 1px #e4e4e4 solid;
	padding: 20px;
	border-radius: 4px;
	box-shadow: 0 0 6px #ccc;
}
</style>
</head>
<body>
	<div  class="container">
		<div class="main">
			<div class="row">
				<div class="row myrow">
					<div class="col-sm-6 col-md-10">
						<div class="page-header">

							<h4>
								<c:if test="${ticket.operate=='add'}">
						新建Ticket
					   </c:if>
								<c:if test="${ticket.operate=='update'}">
						修改Ticket
					    </c:if>
							</h4>
						</div>
					</div>
				</div>

				<c:if test="${ticket.operate=='add'}">
					<c:set var="actionurl" value="create.html"></c:set>
				</c:if>
				<c:if test="${ticket.operate=='update'}">
					<c:set var="actionurl" value="update.html?id=${ticket.id}"></c:set>
				</c:if>

				<div class="row myrow">
					<div class="col-md-1"></div>
					<div class="col-md-8 " id="alertMessageDiv"></div>
				</div>
				<form:form modelAttribute="ticket" method="post"
					action="${actionurl}" class="form-horizontal" role="form">
					<form:hidden path="operate" />
					<form:hidden path="user_id" />
					<form:hidden path="id" />
					<form:hidden path="feedbackMessage" id="feedbackMessage" />

					<div class="row">
						<div class="col-md-12">
							<div class="form-group">

								<label for="contact_value"
									Class="col-xs-12 col-sm-2 col-md-1 control-label">标题:</label>
								<div class="col-xs-12 col-sm-8 col-md-8">
									<form:input path="title" cssClass="form-control input-sm"
										placeholder="Ticket Title" maxlength="120"
										data-validation="required" data-validation-error-msg="请输入标题!" />
								</div>
							</div>
						</div>
					</div>

					<div class="row myrow">
						<div class="col-md-12">
							<div class="form-group">

								<label for="contact_value"
									Class="col-xs-12 col-sm-2 col-md-1 control-label">详细描述:</label>
								<div class="col-xs-12 col-sm-8 col-md-8">
									<form:textarea path="description"
										cssClass="form-control input-sm editbox" rows="10" />
								</div>
							</div>
						</div>
					</div>

					<div class="row myrow">
						<div class="col-md-10" style="text-align: center">
							<c:if test="${ticket.operate=='add'}">
								<button type="submit" class="btn btn-success">创建</button>
							</c:if>
							<c:if test="${ticket.operate=='update'}">
								<button type="submit" class="btn btn-success">保存</button>
							</c:if>
							<button type="reset" class="btn btn-warning">重填</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>


	<!--wrap end-->
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymceinit.js"></script>
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