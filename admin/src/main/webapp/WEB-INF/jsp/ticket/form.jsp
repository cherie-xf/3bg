<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>Ticket 表单</title>
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
				<div class="col-md-12">
					<h4>
						<c:if test="${ticket.operate=='add'}">
						新建Ticket
					   </c:if>
						<c:if test="${ticket.operate=='update'}">
						修改Ticket
					    </c:if>
					</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
				<!--
					<c:if test="${not empty ticket.feedbackMessage}">
						<div class="alert alert-success">${ticket.feedbackMessage}</div>
					</c:if>
					-->
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-10 " id="alertMessageDiv"></div>
			</div>
			
			<c:if test="${ticket.operate=='add'}">
				<c:set var="actionurl" value="create.html"></c:set>
			</c:if>
			<c:if test="${ticket.operate=='update'}">
				<c:set var="actionurl" value="update.html?id=${ticket.id}"></c:set>
			</c:if>

			<form:form modelAttribute="ticket" method="post"
				action="${actionurl}" cssClass="form-inline" role="form">
				<form:hidden path="created_by" />
				<form:hidden path="operate" />
				<form:hidden path="user_id" id="user_id" />
				<form:hidden path="feedbackMessage" id="feedbackMessage" />

				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="title" cssClass="col-md-2 control-label">标题:<font
										color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="title" cssClass="form-control input-sm"
										placeholder="Ticket Title" maxlength="120" 
										data-validation="required" data-validation-error-msg="请输入标题!"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="status_code" cssClass="col-md-2 control-label">Ticket状态:</form:label>
								<div class="col-md-8">
									<form:select path="status_code"
										items="${applicationScope.ticketStatusMap}"
										cssClass="form-control input-sm" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="type_code" cssClass="col-md-2 control-label">Ticket来源:</form:label>
								<div class="col-md-8">
									<form:select path="type_code"
										items="${applicationScope.sourceTypeMap}"
										cssClass="form-control input-sm" />
								</div>
							</div>
						</div>
					</div>

				</div>

				<div class="row myrow">
					<div class="col-md-12">
						<form:label path="first_name" cssClass="col-md-2 control-label">客户姓名:</form:label>
						<div class="col-md-8 item-content">
							<div class="col-md-4 item-content" id="chooseUserDiv">
								<c:out value="${var.user_id}" />
							</div>
							<a class="openViewPage btn btn-default btn-sm"
								href="../dialog/chooseuser.html">选择用户</a>

						</div>

					</div>
				</div>


				<div class="row myrow">
					<div class="col-md-12">
						<form:label path="description" cssClass="col-md-2 control-label">详细描述:</form:label>
						<div class="col-md-8">
							<form:textarea path="description"
								cssClass="form-control input-sm editbox" rows="10" />
						</div>
					</div>
				</div>

				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
						<c:if test="${ticket.operate=='add'}">
							<button type="submit" class="btn btn-success">创建</button>
						</c:if>
						<c:if test="${ticket.operate=='update'}">
							<button type="submit" class="btn btn-success">保存</button>
						</c:if>
						<button type="reset" class="btn btn-warning">重填</button>
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
			$.validate();
			
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
			
			$(".openEditPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ 
	 	    	document.forms[0].submit();
	 	    }});
	 	    
	 	    $(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ 
	 	    	var user_id = $("#user_id").val();
	 	    	console.log("changed!!!!!!!!!!!!"+user_id);
	 	    	$.get("../ticket/getUerInfo.json?user_id=" + user_id, function(data,status){
			    	
			    	$("#chooseUserDiv").empty();
			    	
			    	var name = data;
			    	console.log("name!!!!!!!!!!!!"+name);
			    	var appendhtml1="<h4> "+name;
			    	var appendhtml2="</h4>"
			    		var appendhtml=  appendhtml1 + appendhtml2;											
					
 					$("#chooseUserDiv").append(appendhtml);
	 		    	
			    });
	 	    	
	 	    	
	 	    } });	 
	 	    
		});
	</script>

</body>
</html>