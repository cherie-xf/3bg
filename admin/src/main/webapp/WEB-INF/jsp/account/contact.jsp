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
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty account.feedbackMessage}">
						<div class="alert alert-success">${account.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			<form:form method="get" action="${actionurl}" cssClass="form-inline"
				role="form">
				<input type="hidden" name="id" value="${id}" />
				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<span class="glyphicon glyphicon-phone-alt text-danger" ></span>&nbsp;&nbsp;联系电话&nbsp;<a href="createphonenumber.html?userid=${id}"
									class="openEditPage"><span
									class="glyphicon glyphicon-plus-sign"></span></a>
							</div>
						</div>
						<c:forEach items="${phoneContactList}" var="var"
							varStatus="rowCounter">
							<div class="row myrow">
								<div class="col-md-12">
									<label class="col-md-2 control-label"><c:set
											var="type_code" value="${var.type_code}" />
											
											<img src="${contactTypeIconMap[type_code]}">
											${contactTypeMap[type_code]}：
											</label>
									<div class="col-md-8 item-content">
										<span>${var.contact_value}</span><a
											href="createphonenumber.html?userid=${id}&id=${var.id}"
											class="openEditPage"> <span
											class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;<a
											href="deletecontact/${var.id}.html"> <span
											class="glyphicon glyphicon-minus-sign"></span></a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>

					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<span class="glyphicon glyphicon-envelope text-success" ></span>&nbsp;&nbsp;联系邮箱&nbsp;<a href="createemailaddress.html?userid=${id}"
									class="openEditPage"><span
									class="glyphicon glyphicon-plus-sign"></span></a>
							</div>
						</div>
						<c:forEach items="${emailContactList}" var="var"
							varStatus="rowCounter">
							<div class="row myrow">
								<div class="col-md-12">
									<label class="col-md-2 control-label"><c:set
											var="type_code" value="${var.type_code}" />
											<img src="${contactTypeIconMap[var.type_code]}">
											${contactTypeMap[type_code]}：
											</label>
									<div class="col-md-8 item-content">
										<span>${var.contact_value}</span><a
											href="createemailaddress.html?userid=${id}&id=${var.id}"
											class="openEditPage"> <span
											class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;<a
											href="deletecontact/${var.id}.html"> <span
											class="glyphicon glyphicon-minus-sign"></span></a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>

					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<span class="glyphicon glyphicon-link text-warning" ></span>&nbsp;&nbsp;社交网络账号&nbsp;<a href="createsocialaccount.html?userid=${id}"
									class="openEditPage"><span
									class="glyphicon glyphicon-plus-sign"></span></a>
							</div>
						</div>
						<c:forEach items="${socialContactList}" var="var"
							varStatus="rowCounter">
							<div class="row myrow">
								<div class="col-md-12">
									<label class="col-md-2 control-label"><c:set
											var="type_code" value="${var.type_code}" />
											<!-- 
											${contactTypeMap[type_code]}：
											
											<c:if test="${var.type_code=='35QQ'}">
			               						<img src="/images/icon-social-qq-24.png">
					        				</c:if>
					         				<c:if test="${var.type_code=='30WC'}">
					               				<img src="/images/icon-social-wechat-24.png">
					        				</c:if>
					        				 <c:if test="${var.type_code=='40FB'}">
					               				<img src="/images/icon-social-facebook-24.png">
					         				</c:if>
											-->
											<img src="${contactTypeIconMap[var.type_code]}">
											${contactTypeMap[type_code]}：
											</label>
									<div class="col-md-8 item-content">
										<span>${var.contact_value}</span><a
											href="createsocialaccount.html?userid=${id}&id=${var.id}"
											class="openEditPage"> <span
											class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;<a
											href="deletecontact/${var.id}.html"> <span
											class="glyphicon glyphicon-minus-sign"></span></a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>

					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<span class="glyphicon glyphicon-home text-info" ></span>&nbsp;&nbsp;联系地址&nbsp;<a href="createmailingaddress.html?userid=${id}"
									class="openEditPage"><span
									class="glyphicon glyphicon-plus-sign"></span></a>
							</div>
						</div>
						<c:forEach items="${addressList}" var="var" varStatus="rowCounter">
							<div class="row myrow">
								<div class="col-md-12">
									<div class="col-md-8 item-content">
										<span>${var.address} </span> <span>${var.district}</span><span><c:set
												var="city_code" value="${var.city_code}" />
											${applicationScope.cityMap[var.city_code]}</span>&nbsp;&nbsp;<span>${var.postal_code}</span><a
											href="createmailingaddress.html?userid=${id}&address_id=${var.address_id}"
											class="openEditPage"> <span
											class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;<a
											href="deleteaddress/${var.address_id}.html"> <span
											class="glyphicon glyphicon-minus-sign"></span></a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

			</form:form>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#accountTabList #contactTab").addClass("active");

			$(".openEditPage").colorbox({
				iframe : true,
				width : "60%",
				height : "40%",
				onClosed : function() {
					document.forms[0].submit();
				}
			});

			$(".openViewPage").colorbox({
				iframe : true,
				width : "60%",
				height : "40%",
				onClosed : function() {
				}
			});

		});
	</script>
</body>
</html>
