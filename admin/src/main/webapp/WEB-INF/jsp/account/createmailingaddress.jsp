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
					<h4>
						<c:if test="${address.operate=='add'}">
						新建联系地址
					   	</c:if>
						<c:if test="${address.operate=='update'}">
						修改联系地址
						</c:if>
					</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty address.feedbackMessage}">
						<div class="alert alert-success">${address.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			<form:form modelAttribute="address" method="post"
				action="${actionurl}" cssClass="form-inline" role="form">
				<form:hidden path="operate" />
				<form:hidden path="user_id" />
				<form:hidden path="address_id" />
				<div class="row myrow">
					<div class="col-md-12">



						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="address" cssClass="col-md-2 control-label"> Address<font
										color="red">&nbsp;*&nbsp; </font>:
								</form:label>
								<div class="col-md-8 item-content">
									<form:input path="address" cssClass="form-control input-sm"
										id="address" placeholder="address" data-validation="required" data-validation-error-msg="请输入Address!"/>
								</div>
							</div>
						</div>
						
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="district" cssClass="col-md-2 control-label">District<font
										color="red">&nbsp;*&nbsp; </font>:
								</form:label>
								<div class="col-md-8 item-content">
									<form:input path="district" cssClass="form-control input-sm" maxlength="45"
										id="district" placeholder="district" data-validation="required" data-validation-error-msg="请输入District!" />
								</div>
							</div>
						</div>

						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="city_code" cssClass="col-md-2 control-label">City<font
										color="red">&nbsp;*&nbsp; </font>:</form:label>
								<div class="col-md-8 item-content">
									<form:select path="city_code" cssClass="form-control input-sm"
									data-validation="required" data-validation-error-msg="请选择City!">
										<form:option value="">--Select--</form:option>
										<form:options items="${applicationScope.cityMap}" />
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="postal_code" cssClass="col-md-2 control-label">ZIP/Postal Code<font
										color="red">&nbsp;*&nbsp; </font>:
								</form:label>
								<div class="col-md-4 item-content">
									<form:input path="postal_code" cssClass="form-control input-sm"
										id="postal_code" placeholder="V5J0A8" maxlength="45"
										data-validation="required" data-validation-error-msg="请输入Postal Code !"
										/>
								</div>
							</div>
						</div>

					</div>
				</div>

				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
						<button type="submit" class="btn btn-success">
							<c:if test="${address.operate=='add'}">
						新建
					   	</c:if>
							<c:if test="${address.operate=='update'}">
						修改
						</c:if>
						</button>
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
	
		function removeSpaces(string) {
		 	return string.split(' ').join('');
		}
		
		$(document).ready(function() {
				  
			$.validate();
			
		});
	</script>

</body>
</html>