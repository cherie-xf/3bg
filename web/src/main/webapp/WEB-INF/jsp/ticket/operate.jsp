<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>个人信息管理</title>
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
	<div class="container tbscontainer">
		<div class="main">
			<div class="row">
				<div class="row myrow">

					<div class="col-sm-6 col-md-10">
						<div class="page-header">


							<h4>
								Ticket详细
								<c:if
									test="${ticket.status_code =='10'|| ticket.status_code =='20'||ticket.status_code =='30'}">
									<a href="cancle.html?id=${ticket.id}"
										class="btn btn-danger btn-sm" title="Tikcet取消后将停止处理无法撤消">取消Ticket</a>

								</c:if>

							</h4>



						</div>
					</div>
				</div>

				<div class="col-md-1"></div>
				<div class="col-md-8 " id="alertMessageDiv"></div>
			</div>

		</div>

		<div class="row myrow">

			<div class="col-sm-6 col-md-10">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">

						<div class="row myrow">
							<div class=" col-md-12">
								<div class="col-md-3">
									<p>标题 ：${ticket.title}</p>

								</div>
								<div class="col-md-3">
									<p>客户姓名 :${ticket.last_name} ${ticket.first_name}</p>

								</div>
								<div class="col-md-3">
									<p>联系电话 ：${ticket.phone}</p>

								</div>
								<div class="col-md-3">
									<p>
										录入时间 ：
										<fmt:formatDate value="${ticket.created_at}"
											pattern="YYYY/MM/dd HH:mm" />
									</p>

								</div>

							</div>
						</div>
					</div>
					<div class="panel-body">
						<p class="text-info">${ticket.description}</p>
					</div>


				</div>
			</div>
		</div>


		<div class="row myrow">
			<div class="col-md-10">
				<label for="contact_value"
					Class="col-xs-12 col-sm-2 col-md-2 control-label">回复: <span
					class="badge">${ticketReplylist.size()}</span></label>
			</div>
		</div>


		<br>
		<div class="row myrow">
			<div class="col-md-10">

				<c:forEach items="${ticketReplylist}" var="var"
					varStatus="rowCounter">

					<div class="panel panel-info ">
						<div class="panel-body">
							<h5>
								<span class="glyphicon glyphicon-user"></span>
								&nbsp;${var.replier_name }&nbsp;&nbsp;于
								<fmt:formatDate value="${var.reply_time}"
									pattern="YYYY/MM/dd HH:mm" />
								&nbsp;&nbsp;回复到:
							</h5>
							<p class="bg-primary">${var.reply_msg}</p>
						</div>
					</div>

				</c:forEach>

			</div>
		</div>


		<form:form modelAttribute="ticketReplyQuery" method="post"
			action="createReply.html?id=${ticket.id}&userLevel=${ticket.operate}"
			cssClass="form-inline" role="form">


			<c:if
				test="${ticket.status_code =='10'|| ticket.status_code =='20'||ticket.status_code =='30'}">

				<div class="row myrow">
					<div class="col-sm-10">
						<form:textarea path="reply_msg" class="form-control" rows="3"></form:textarea>
					</div>
				</div>
				<br>
				<div class="row myrow">
					<div class="col-md-10">
						<label for="contact_value"
							Class="col-xs-12 col-sm-2 col-md-2 control-label">当前回复人:</label>

						<div class="col-md-2">${username}</div>
						<div class="col-md-6" style="text-align: center">
							<button type="submit" class="btn btn-success btn-sm ">回&nbsp;&nbsp;复</button>
						</div>
					</div>
				</div>
			</c:if>
		</form:form>





	</div>



	<!--wrap end-->
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymceinit.js"></script>
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