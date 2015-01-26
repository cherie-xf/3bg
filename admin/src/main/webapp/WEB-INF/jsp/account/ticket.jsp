<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>

<title>用户账户管理</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
<link href="/css/treeview.css" rel="stylesheet" />
</head>
<body>

	<%@ include file="/WEB-INF/jsp/inc/adminnav.jsp"%>
	<div class="container">
		<div class="row">
			<%@ include file="/WEB-INF/jsp/account/accountheader.jsp"%>
			<div class="row myrow">
				<div class="col-md-12">
					<table class="table table-striped table-hover">
						<tr>
							<th><b>Ticet标题</b></th>
							<th><b>客户姓名</b></th>
							<th><b>联系电话</b></th>
							<th><b>录入时间</b></th>
							<th><b>更新时间</b></th>
							<th><b>状态</b></th>
							<th><b>客服</b></th>
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
												<span class="glyphicon glyphicon-edit text-success" title="处理"></span>
												
												 </c:when>
											<c:otherwise>
											<span class="glyphicon glyphicon-zoom-in text-info" title="查看"></span>
											 
											 </c:otherwise>
										</c:choose>
								</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			
			
			
			
			
			
			
			
			

		</div>
	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#accountTabList #ticketTab").addClass("active");

			$(".openEditPage").colorbox({
				iframe : true,
				width : "90%",
				height : "90%",
				onClosed : function() {
					location.reload();
				}
			});

			$(".openViewPage").colorbox({
				iframe : true,
				width : "90%",
				height : "90%",
				onClosed : function() {
				}
			});

		});
	</script>
</body>
</html>
