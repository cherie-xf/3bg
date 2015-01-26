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
			<%@ include file="/WEB-INF/jsp/customerservice/accountheader.jsp"%>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<!-- 
					<c:if test="${not empty account.feedbackMessage}">
						<div class="alert alert-success">${account.feedbackMessage}</div>
					</c:if>
					-->
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-10 " id="alertMessageDiv"></div>
			</div>
			<form:form modelAttribute="account" method="post"
				action="${actionurl}" cssClass="form-inline" role="form">
				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:hidden path="feedbackMessage" id="feedbackMessage" />
								<form:label path="profession" cssClass="col-md-2 control-label">职业信息:</form:label>
								<div class="col-md-8">
									<form:textarea path="profession"
										cssClass="form-control input-sm editbox" rows="10" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="health" cssClass="col-md-2 control-label">健康信息:</form:label>
								<div class="col-md-8">
									<form:textarea path="health"
										cssClass="form-control input-sm editbox" rows="10" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="familyInfo" cssClass="col-md-2 control-label">家庭成员信息:</form:label>
								<div class="col-md-8">
									<form:textarea path="familyInfo"
										cssClass="form-control input-sm editbox" rows="10" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="carInfo" cssClass="col-md-2 control-label">汽车信息:</form:label>
								<div class="col-md-8">
									<form:textarea path="carInfo"
										cssClass="form-control input-sm editbox" rows="10" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="petInfo" cssClass="col-md-2 control-label">宠物信息:</form:label>
								<div class="col-md-8">
									<form:textarea path="petInfo"
										cssClass="form-control input-sm editbox" rows="10" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="propertyInfo"
									cssClass="col-md-2 control-label">贵重物品:</form:label>
								<div class="col-md-8">
									<form:textarea path="propertyInfo"
										cssClass="form-control input-sm editbox" rows="10" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12" style="text-align: center">
								<button type="submit" class="btn btn-success">确定</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div id="insertHtml" style="display: none"></div>
				<div id="insertValue" style="display: none"></div>
				<!-- other description, such as the register rule -->
			</div>
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
			$("#accountTabList #otherTab").addClass("active");
			
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
			
			$(".openEditPage").colorbox({
				iframe : true,
				width : "60%",
				height : "60%",
				onClosed : function() {
					location.reload();
				}
			});

			$(".openViewPage").colorbox({
				iframe : true,
				width : "60%",
				height : "60%",
				onClosed : function() {
				}
			});

		});
	</script>
</body>
</html>
