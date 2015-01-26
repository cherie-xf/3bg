<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>个人信息管理</title>
<%@ include file="/WEB-INF/inc/simplehead.jsp"%>


</head>
<body>
	<div class="container tbscontainer">

		<div class="row">
			<div class="row myrow">
				<div class="col-md-12">
					<div class="col-md-2"></div>
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


			<form:form modelAttribute="address" method="post" action="${actionurl}"
				class="form-horizontal" role="form">
				<form:hidden path="operate" />
				<form:hidden path="user_id" />
				<form:hidden path="address_id" />
				
				<div class="row myrow">
					<div class="col-md-12">
						<div class="form-group">

							<label for="contact_value"
								Class="col-xs-12 col-sm-2 col-md-3 control-label">Address<font
										color="red">&nbsp;*&nbsp; </font>:</label>
							<div class="col-xs-12 col-sm-8 col-md-8">
								<form:input path="address" cssClass="form-control input-sm"
										id="address" placeholder="address" data-validation="required" data-validation-error-msg="请输入Address!"/>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row myrow">
					<div class="col-md-12">
						<div class="form-group">

							<label for="contact_value"
								Class="col-xs-12 col-sm-2 col-md-3 control-label">District:</label>
							<div class="col-xs-12 col-sm-8 col-md-8">
								<form:input path="district" cssClass="form-control input-sm" maxlength="45"
										id="district" placeholder="district"/>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label for="type_code"
								Class="col-xs-12 col-sm-2 col-md-3 control-label">City<font
										color="red">&nbsp;*&nbsp; </font>:</label>
							<div class="col-xs-12 col-sm-8 col-md-8">
								<form:select path="city_code" cssClass="form-control input-sm"
									data-validation="required" data-validation-error-msg="请选择City!">
										<form:option value="">----Select----</form:option>
										<form:options items="${applicationScope.cityMap}" />
									</form:select>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row myrow">
					<div class="col-md-12">
						<div class="form-group">

							<label for="contact_value"
								Class="col-xs-12 col-sm-2 col-md-3 control-label">ZIP/Postal code<font
										color="red">&nbsp;*&nbsp; </font>:</label>
							<div class="col-xs-12 col-sm-8 col-md-8">
								<form:input path="postal_code" cssClass="form-control input-sm"
										id="postal_code" placeholder="postal code" maxlength="45"
										data-validation="required" data-validation-error-msg="请输入Postal Code !"/>
							</div>
						</div>
					</div>
				</div>

				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
						<button type="submit" class="btn btn-success">
							<c:if test="${address.operate=='add'}">
						增加
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


	<!--wrap end-->
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script>
		$(document).ready(function() {

			$.validate();

		});
	</script>

</body>
</html>