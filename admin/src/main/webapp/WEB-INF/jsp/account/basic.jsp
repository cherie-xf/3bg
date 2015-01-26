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

				<div class="col-md-10">

					<c:if test="${not empty account.feedbackMessage}">
						<!-- 
						<div class="alert alert-warning alert-success" role="alert"
							id="alertmessage">
							<button type="button" class="close" data-dismiss="alert">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<strong>Success!</strong> ${account.feedbackMessage}
						</div>
						-->

					</c:if>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-10 " id="alertMessageDiv"></div>
			</div>

			<form:form modelAttribute="account" method="post"
				action="basicUpdate.html?id=${account.id}" cssClass="form-inline"
				role="form">
				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:hidden path="feedbackMessage" id="feedbackMessage" />
								<form:hidden path="email" id="email" />
								<form:hidden path="phone" id="phone" />
								<form:label path="email" cssClass="col-md-2 control-label">邮箱：</form:label>
								<div class="col-md-8 item-content">
									<p class="form-control-static input-sm">${account.email}</p>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="email" cssClass="col-md-2 control-label">手机：</form:label>
								<div class="col-md-8 item-content">
									<p class="form-control-static input-sm">${account.phone}</p>
								</div>
							</div>
						</div>

						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="lastName" cssClass="col-md-2 control-label">姓:</form:label>
								<div class="col-md-3 item-content">
									<form:input path="lastName" cssClass="form-control input-sm"
										placeholder="姓" maxlength="20"/>
								</div>
								<form:label path="firstName" cssClass="col-md-2 control-label">名:</form:label>
								<div class="col-md-3 item-content">
									<form:input path="firstName" cssClass="form-control input-sm"
										placeholder="名" maxlength="20"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="preferredName"
									cssClass="col-md-2 control-label">英文名:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="preferredName"
										cssClass="form-control input-sm" placeholder="" maxlength="45"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="preferredName"
									cssClass="col-md-2 control-label">性别:</form:label>
								<div class="col-md-8 item-content">
									<form:label path="gender" class="radio-inline">
										<form:radiobutton path="gender" name="gender" value="M" />男
									</form:label>
									<form:label path="gender" class="radio-inline">
										<form:radiobutton path="gender" name="gender" value="F" />女
									</form:label>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="preferredName"
									cssClass="col-md-2 control-label">生日:</form:label>
								<div class="col-md-8 item-content">
									<label><form:select path="yearOfBirth"
											cssClass="form-control input-sm">
											<form:option value=""></form:option>
											<form:options items="${yearMap}" />
										</form:select> </label> <label><form:select path="monthOfBirth"
											cssClass="form-control input-sm">
											<form:option value=""></form:option>
											<form:options items="${monthMap}" />
										</form:select> </label> <label><form:select path="dayOfBirth"
											cssClass="form-control input-sm">
											<form:option value=""></form:option>
											<form:options items="${dayOfMonthMap}" />
										</form:select> </label>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="preferredName"
									cssClass="col-md-2 control-label">血型:</form:label>
								<div class="col-md-8 item-content">
									<form:select path="bloodType" cssClass="form-control input-sm">
										<form:option value=""></form:option>
										<form:options items="${bloodTypeMap}" />
									</form:select>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="preferredName"
									cssClass="col-md-2 control-label">语言:</form:label>
								<div class="col-md-8 item-content">
									<form:select path="preferredLanguage"
										cssClass="form-control input-sm">
										<form:option value=""></form:option>
										<form:options items="${applicationScope.languageMap}" />
									</form:select>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="passportNumber"
									cssClass="col-md-2 control-label">护照号:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="passportNumber"
										cssClass="form-control input-sm" placeholder="" maxlength="45"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="chineseIdNumber"
									cssClass="col-md-2 control-label">中国身份证号:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="chineseIdNumber"
										cssClass="form-control input-sm" placeholder=""  maxlength="45"
										data-validation="length" data-validation-length="max18" data-validation-optional="true"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="driverLicense"
									cssClass="col-md-2 control-label">本地驾照:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="driverLicense"
										cssClass="form-control input-sm" placeholder="" maxlength="45"
										data-validation="length" data-validation-length="max18" data-validation-optional="true"/>
								</div>
							</div>
						</div>

						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="driverLicense"
									cssClass="col-md-2 control-label">账号状态:</form:label>
								<div class="col-md-8 item-content">
									<form:label path="status" class="radio-inline">
										<form:radiobutton path="status" name="status"
											id="inlineRadio1" value="0" />未激活
									</form:label>
									<form:label path="status" class="radio-inline">
										<form:radiobutton path="status" name="status"
											id="inlineRadio2" value="1" />激活
									</form:label>
									<form:label path="status" class="radio-inline">
										<form:radiobutton path="status" name="status"
											id="inlineRadio3" value="2" />锁死
									</form:label>
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
	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$.validate();
			$("#accountTabList #basicTab").addClass("active");
			
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
				width : "80%",
				height : "90%",
				onClosed : function() {
					location.reload();
				}
			});

			$(".openViewPage").colorbox({
				iframe : true,
				width : "80%",
				height : "90%",
				onClosed : function() {
				}
			});

		});
	</script>
</body>
</html>
