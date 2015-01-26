<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>新建客服帐号</title>
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
		<!-- Contact Information -->

		<div class="row">
			<div class="row myrow">
				<div class="row myrow">
					<div class="col-md-2"></div>
					<div class="col-md-10">
						<c:if test="${not empty membership.feedbackMessage}">
							<div class="alert alert-success">${membership.feedbackMessage}</div>
						</c:if>
					</div>
				</div>

				<form:form modelAttribute="customerRepInfo" method="post"
					action="create.html" cssClass="form-inline" role="form">

					<div class="row myrow">
						<div class="col-md-12">
							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="email" cssClass="col-md-2 control-label">邮箱<font
											color="red">*</font>:</form:label>
									<div class="col-md-8 item-content">
										<form:input path="email" cssClass="form-control input-sm" maxlength="64"
											id="email" placeholder="example@gmail.com" data-validation="email" data-validation-error-msg="请输入正确邮箱!" />
									</div>
								</div>
							</div>

							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="phone" cssClass="col-md-2 control-label">手机<font
											color="red">*</font>:</form:label>
									<div class="col-md-8 item-content">
										<form:input path="phone" cssClass="form-control input-sm"
											id="phone" placeholder="phone" 
											data-validation-allowing="int" data-validation="number" maxlength="15" data-validation-error-msg="请输入正确手机号!"/>
									</div>
								</div>
							</div>

							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="lastName" cssClass="col-md-2 control-label">姓<font
											color="red">*</font>:</form:label>
									<div class="col-md-8 item-content">
										<form:input path="lastName" cssClass="form-control input-sm"
											id="lastName" placeholder="姓" maxlength="20"
											data-validation="required" data-validation-error-msg="请输入姓名!"/>
									</div>
								</div>
							</div>

							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="firstName" cssClass="col-md-2 control-label">名<font
											color="red">*</font>:</form:label>
									<div class="col-md-8 item-content">
										<form:input path="firstName" cssClass="form-control input-sm"
											id="firstName" placeholder="名" maxlength="20"
											data-validation="required" data-validation-error-msg="请输入姓名!"/>
									</div>
								</div>
							</div>

							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="password_confirmation" cssClass="col-md-2 control-label">登录密码<font
											color="red">*</font>:</form:label>
									<div class="col-md-8 item-content">
										<form:password path="password_confirmation"
											cssClass="form-control input-sm" id="password_confirmation" placeholder="" 
											data-validation="strength" data-validation-strength="1" maxlength="128"/>
									</div>
								</div>
							</div>

							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="password"
										cssClass="col-md-2 control-label">重复登录密码<font
											color="red">*</font>:</form:label>
									<div class="col-md-8 item-content">
										<form:password path="password"
											cssClass="form-control input-sm" id="password"
											placeholder="" data-validation="confirmation" data-validation-error-msg="密码不一致!" maxlength="128"/>
									</div>
								</div>
							</div>
							
							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="member_id" cssClass="col-md-2 control-label">会员ID<font
											color="red">*</font>:</form:label>
									<div class="col-md-8 item-content form-inline">
										<form:input path="member_id" cssClass="form-control input-sm"
											placeholder="会员ID" maxlength="120" readonly ="true"
											   data-validation="required" data-validation-if-checked="memberIDManualInput" />
										<div class="checkbox">
								    		  <form:label path="memberIDManualInput">
								      			<form:checkbox path="memberIDManualInput"/> 手动输入会员ID
								    		  </form:label>
								  		 </div>		
									</div>
								</div>
							</div>

							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="type_code" cssClass="col-md-2 control-label">会员类型:</form:label>
									<div class="col-md-8 item-content">
										<form:select path="type_code" cssClass="form-control input-sm"
											maxlength="120">
											<form:options items="${applicationScope.innerMemberTypeMap}" />
										</form:select>
									</div>
								</div>
							</div>

							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="credit" cssClass="col-md-2 control-label">积分:</form:label>
									<div class="col-md-8 item-content">
										<form:input path="credit" cssClass="form-control input-sm"
											placeholder="积分" maxlength="120" 
											data-validation="number" data-validation-optional="true"/>
									</div>
								</div>
							</div>
							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="valid_from" cssClass="col-md-2 control-label">生效日期:</form:label>
									<div class="col-md-8 item-content">
										<fmt:formatDate value="${membership.valid_from}"
											var="validFromString" pattern="MM/dd/YYYY" />
										<form:input path="valid_from" value="${validFromString}"
											cssClass="form-control input-sm" id="datepicker1"
											placeholder="生效日期" 
											data-validation="date" data-validation-format="mm/dd/yyyy" data-validation-help="mm/dd/yyyy" type="text" data-validation-optional="true"/>
									</div>
								</div>
							</div>
							<div class="row myrow">
								<div class="col-md-12">
									<form:label path="valid_to" cssClass="col-md-2 control-label">失效日期:</form:label>
									<div class="col-md-8 item-content">
										<fmt:formatDate value="${membership.valid_to}"
											var="validToString" pattern="MM/dd/YYYY" />
										<form:input path="valid_to" value="${validToString}"
											cssClass="form-control input-sm" id="datepicker2"
											placeholder="失效日期" 
											data-validation="date" data-validation-format="mm/dd/yyyy" data-validation-help="mm/dd/yyyy" data-validation-optional="true"/>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row myrow">
						<div class="col-md-12" style="text-align: center">
							<button type="submit" class="btn btn-success" >创建</button>

							<button type="reset" class="btn btn-warning">重填</button>
						</div>
					</div>
				</form:form>
				
			</div>
		</div>
		<!-- contact over -->

	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymceinit.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$.validate({
				 modules : 'security',
				 onModulesLoaded : function() {
					   $('input[name="password_confirmation"]').displayPasswordStrength();
					 }
			  });
			
			$("#datepicker1").datepicker();
			$("#datepicker2").datepicker();
			$("#datepicker3").datepicker();

			$(".openEditPage").colorbox({
				iframe : true,
				width : "80%",
				height : "90%",
				onClosed : function() {
					document.forms[0].submit();
				}
			});

			$(".openViewPage").colorbox({
				iframe : true,
				width : "80%",
				height : "90%",
				onClosed : function() {
				}
			});
			//Allow user to update member_id manully when creating and no auto generate checkbox checked.
	 	    if($("input:checkbox[name=memberIDManualInput]").is(':checked')){
	 	  		$('#member_id').prop("readonly", false);
	 	  	}else{
		    	$('#member_id').prop("readonly", true);	
		    }
			$("input:checkbox[name=memberIDManualInput]").change(function() {
				var $this = $(this);
			    if ($this.is(':checked')) {	    
			    	$('#member_id').prop("readonly", false);		    	
			    }else{
			    	$('#member_id').prop("readonly", true);		    	
			    }
			});

		});
		
		
	</script>
</body>
</html>