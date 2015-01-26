<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>服务日志</title>
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
<%@ include file="/WEB-INF/jsp/inc/adminnav.jsp"%>
	<div class="container">
		<div class="row">
			<%@ include file="/WEB-INF/jsp/account/accountheader.jsp"%>


			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty userhistoryQuery.feedbackMessage}">
						<div class="alert alert-success">${userhistoryQuery.feedbackMessage}</div>
					</c:if>
				</div>
			</div>

			<h4>
				<strong>新增日志</strong>
			</h4>

			<form:form modelAttribute="userhistoryQuery" method="post"
				action="createHistory.html?id=${userhistoryQuery.user_id}"
				cssClass="form-inline" role="form">
				<div class="row myrow">
					<div class="col-sm-8">
						<form:textarea path="content"
							cssClass="form-control input-sm editbox" rows="10" />
					</div>
				</div>

				<div class="row myrow">
					<div class="col-md-12">
						<div class="col-md-3">
							<h4>当前记录人 : &nbsp; ${username} &nbsp;</h4>
						</div>
						<div class="col-md-9">
							<button type="submit" class="btn btn-success btn-sm ">增加</button>
						</div>
					</div>
				</div>

			</form:form>






			<div class="row hr">
				<hr class="col-md-8">
			</div>


			<h4>
				历史日志&nbsp;： <span class="badge">${userHistorylist.size()}</span>
			</h4>

			<br>
			<div class="row myrow">
				<div class="col-md-12">

					<c:forEach items="${userHistorylist}" var="var"
						varStatus="rowCounter">

						<div class="panel panel-info col-md-8">
							<div class="panel-body">

								<div class="row myrow">
									<div class="col-md-2">
										<fmt:formatDate value="${var.created_at}"
											pattern="YYYY/MM/dd " />
									</div>
									<div class="col-md-6">记录人： ${var.creater_name }</div>
								</div>

								<p class="bg-primary">${var.content}</p>
							</div>
						</div>

					</c:forEach>

				</div>
			</div>


		</div>
		<!-- content over -->

	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymceinit.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#accountTabList #userhistoryTab").addClass("active");
		});
	</script>

</body>
</html>