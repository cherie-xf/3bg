<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>blog管理</title>
<%@ include file="/WEB-INF/inc/simplehead.jsp"%>
<style type="text/css">
#wrapper {
	border: 1px #e4e4e4 solid;
	padding: 20px;
	border-radius: 4px;
	box-shadow: 0 0 6px #ccc;
}
</style>
</head>
<body>
	<div class="container">
		<div class="main">
			<div class="row">

				<div class="col-sm-6 col-md-10">
					<div class="page-header">
						<h4>
							<c:if test="${blog.operate=='add'}">
						新建Blog
					   </c:if>
							<c:if test="${blog.operate=='update'}">
						修改Blog
					    </c:if>
						</h4>
					</div>
				</div>


				<c:if test="${blog.operate=='add'}">
					<c:set var="actionurl" value="create.html"></c:set>
				</c:if>
				<c:if test="${blog.operate=='update'}">
					<c:set var="actionurl" value="update.html"></c:set>
				</c:if>

				<div class="row myrow">
					<div class="col-md-1"></div>
					<div class="col-md-8 " id="alertMessageDiv"></div>
				</div>
				<form:form modelAttribute="blog" method="post" action="${actionurl}"
					cssClass="form-horizontal" role="form"
					enctype="multipart/form-data" data-validation="radiocheck">
					<form:hidden path="created_by" />
					<form:hidden path="operate" />
					<form:hidden path="blog_id" />

					<div class="row">
						<div class="col-md-12">
							<div class="form-group">

								<label for="contact_value"
									Class="col-xs-12 col-sm-2 col-md-1 control-label">标题:</label>
								<div class="col-xs-12 col-sm-8 col-md-8">
									<form:input path="title" cssClass="form-control input-sm"
										placeholder="Blog Title" maxlength="120"
										data-validation="required" data-validation-error-msg="请输入标题!" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<div class="form-group">

								<label for="contact_value"
									Class="col-xs-12 col-sm-2 col-md-1 control-label">Blog分类:</label>
								<div class="col-xs-12 col-sm-8 col-md-8">
									<form:select path="class_id"
										items="${applicationScope.blogClassMap}"
										cssClass="form-control input-sm" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<div class="form-group">

								<label for="contact_value"
									Class="col-xs-12 col-sm-2 col-md-1 control-label">简述:</label>
								<div class="col-xs-12 col-sm-8 col-md-8">
									<form:input path="meta_dec" cssClass="form-control input-sm"
										placeholder="Meta Dec" maxlength="256" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<div class="form-group">

								<label for="contact_value"
									Class="col-xs-12 col-sm-2 col-md-1 control-label">Blog状态:</label>
								<div class="col-xs-12 col-sm-8 col-md-8">

									<div class="col-sm-8"  >


									<form:label path="blog_status" class="radio-inline">
											<form:radiobutton path="blog_status" name="blog_status"
												value="10" title="" data-validation="blog_status_check" id="defaultRadio"/>草稿
									</form:label>
										<form:label path="blog_status" class="radio-inline">
											<form:radiobutton path="blog_status" name="blog_status"
												value="20" />发布
									</form:label>
									<form:label path="blog_status" class="radio-inline">
											<form:radiobutton path="blog_status" name="blog_status"
												value="30" />不发布
									</form:label>
									
									</div>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="col-md-12">
							<div class="form-group">

								<label for="contact_value"
									Class="col-xs-12 col-sm-2 col-md-1 control-label">内容:</label>
								<div class="col-xs-12 col-sm-8 col-md-8">
									<form:textarea path="Content"
										cssClass="form-control input-sm editbox" rows="10" />
								</div>
							</div>
						</div>
					</div>

					<div class="row myrow">
						<div class="col-md-10" style="text-align: center">
							<button type="submit" class="btn btn-success">
								<c:if test="${blog.operate=='add'}">
							创建
						</c:if>
								<c:if test="${blog.operate=='update'}">
							保存
						</c:if>
							</button>
							<button type="reset" class="btn btn-warning">重填</button>
						</div>
					</div>
				</form:form>




			</div>
		</div>
	</div>


	<!--wrap end-->
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymceinit.js"></script>
	<script>
		$(document).ready(
				
				
				
				function() {
					
					function checkRadio()
					{
						var blog_status = $('input[name="blog_status"]:checked').val();
						console.log("blog_status="+blog_status);
						if(typeof blog_status === 'undefined')
							{
							 console.log("happend");
							 $('#defaultRadio').attr("title","defaultchecked");
							 
							}
					}
					
					$.formUtils.addValidator({
						name : 'blog_status_check',
						validatorFunction : function(value, $el, config, language, $form) {
							//console.log("radiocheckvalue=="+value);
							//console.log("radiocheckel=="+$el.prop("checked"));
							//console.log('$el.prop("checked")=true');
							checkRadio();
							console.log("title="+$el.prop("title"));
							
							if( $el.prop("title")=="defaultchecked")
							{
								$el.attr("title","");
								return false;
							}
						return true;
						},
						errorMessage : '请选择blog状态!',
						
						});
					
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
				
				});
	</script>

</body>
</html>