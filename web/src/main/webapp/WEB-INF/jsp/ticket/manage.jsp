<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>Ticket管理</title>
<!--#include file="header.shtml" -->
<%@ include file="/WEB-INF/inc/head.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css" />
<style type="text/css">
.myrow{
          margin-bottom:10px;
       }
</style>
</head>
<body>
	<div class="wrap">
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/topnav.jsp"%>
		<!--globalnav end-->
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/globalnav.jsp"%>
		<!--globalnav end-->




		<!----------------左侧边栏---------------->
		<!--#include file="leftbar.html" -->

		<!----------------右侧边栏---------------->
		<!--<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main1">-->

		<div class="container tbscontainer">
			<div class="main">

				<div class="row">
					<div class="col-sm-2">
						<%@ include file="/WEB-INF/inc/leftbar.jsp"%>
					</div>
					<div class="col-sm-10">
						<form:form modelAttribute="ticketQuery" method="post"
							action="/web/ticket/manage.html" cssClass="form-inline"
							role="form">

							<div class="row">
								<div class="col-sm-12">
									<ol class="breadcrumb mybreadcrumb">
										<li class="active">我的服务宝</li>
										<li class="active">Ticket管理</li>
										<li class="active">Ticket管理</li>
									</ol>
								</div>
							</div>
							<div class="row myrow">
								<div class="col-sm-12">
									<div class="form-group">
										<form:label path="title" cssClass="sr-only">标题:</form:label>
										<form:input path="title" cssClass="form-control input-sm"
											placeholder="标题" />
									</div>
									<div class="form-group">
										<form:label path="phone" cssClass="sr-only">电话:</form:label>
										<form:input path="phone" cssClass="form-control input-sm"
											placeholder="电话" />
									</div>
									<div class="form-group">
										<form:select path="status_code"
											cssClass="form-control input-sm">
											<form:option value="">--所有状态--</form:option>
											<form:options items="${applicationScope.ticketStatusMap}" />
										</form:select>
									</div>



									<button type="submit" class="btn btn-primary btn-sm">Search</button>
									<a href="create.html"
										class="btn btn-success btn-sm openEditPage">创建Ticket</a>
								</div>
							</div>
							<div class="row myrow">
								<div class="col-sm-12">

									<table class="table table-striped table-hover">
										<tr>
											<th><b>Ticket标题</b></th>
											<th><b>客户姓名</b></th>
											<th><b>联系电话</b></th>
											<th><b>录入时间</b></th>
											<th><b>更新时间</b></th>
											<th><b>状态</b></th>
											<th><b>客服编号</b></th>
											<th><b>操作</b></th>
										</tr>
										<c:forEach items="${ticketmnglist}" var="var"
											varStatus="rowCounter">
											<tr>
												<td>${var.title}</td>
												<td>${var.last_name}${var.first_name}</td>
												<td>${var.phone}</td>
												<td><fmt:formatDate value="${var.created_at}"
														pattern="MM/dd/YYYY HH:mm" /></td>
												<td><fmt:formatDate value="${var.modified_at}"
														pattern="MM/dd/YYYY HH:mm" /></td>
												<td><c:set var="status_code" value="${var.status_code}" />
													${applicationScope.ticketStatusMap[status_code]}</td>
												<td>${var.processor_id}</td>

												<td><a class="openEditPage"
													href="operate.html?id=${var.id}&userLevel=super"> <c:choose>
															<c:when
																test="${var.status_code =='10'|| var.status_code =='20'|| var.status_code =='30'}">
																<span class="glyphicon glyphicon-edit text-success"
																	title="处理"></span>

															</c:when>
															<c:otherwise>
																<span class="glyphicon glyphicon-zoom-in text-info"
																	title="查看"></span>

															</c:otherwise>
														</c:choose>
												</a></td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>

						</form:form>
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

					$(".openEditPage").colorbox({
						iframe : true,
						width : "80%",
						height : "80%",
						onClosed : function() {
							location.reload();
						}
					});

					$(".openViewPage").colorbox({
						iframe : true,
						width : "80%",
						height : "80%",
						onClosed : function() {
						}
					});

				});
	</script>
</body>
</html>