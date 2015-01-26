<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>分类表单</title>
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
						<c:if test="${category.operate=='add'}">
						新建分类
					   	</c:if>
						<c:if test="${category.operate=='update'}">
						修改分类
						</c:if>
					</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty category.feedbackMessage}">
						<div class="alert alert-success">${category.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			<c:if test="${category.operate=='add'}">
				<c:set var="actionurl" value="create.html"></c:set>
			</c:if>
			<c:if test="${category.operate=='update'}">
				<c:set var="actionurl" value="update.html"></c:set>
			</c:if>

			<form:form modelAttribute="category" method="post" action="${actionurl}" cssClass="form-inline" role="form">
				<form:hidden path="created_by" />
				<form:hidden path="operate" />
				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="category_id" cssClass="col-md-2 control-label">分类ID<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="category_id" cssClass="form-control input-sm" placeholder="分类ID" maxlength="120"
										data-validation="required" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="category_name" cssClass="col-md-2 control-label">分类名<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="category_name" cssClass="form-control input-sm" placeholder="分类名" maxlength="45"
										data-validation="required" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="english_name" cssClass="col-md-2 control-label">英文名:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="english_name" cssClass="form-control input-sm" placeholder="英文名" maxlength="45" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="parent_id" cssClass="col-md-2 control-label">上级分类ID:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="parent_id" cssClass="form-control input-sm" placeholder="上级分类ID" maxlength="16" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="leaf" cssClass="col-md-2 control-label">叶节点:</form:label>
								<div class="col-md-8 item-content">
									<form:checkbox path="leaf" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="enable" cssClass="col-md-2 control-label">启用:</form:label>
								<div class="col-md-8 item-content">
									<form:checkbox path="enable" value="true" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="priority" cssClass="col-md-2 control-label">优先级:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="priority" cssClass="form-control input-sm" placeholder="20" maxlength="120" 
										data-validation="number" data-validation-allowing="range[0;1000]" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="cssclass" cssClass="col-md-2 control-label">CSSclass:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="cssclass" cssClass="form-control input-sm" placeholder="CSSclass" maxlength="45" />
								</div>
							</div>
						</div>
						
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="description" cssClass="col-md-2 control-label">描述:</form:label>
								<div class="col-md-8 item-content">
									<form:textarea path="description" cssClass="form-control input-sm" placeholder="描述" rows="3" />
								</div>
							</div>
						</div>							
					</div>
				</div>

				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
						<c:if test="${category.operate=='add'}">
						<button type="submit" class="btn btn-success">创建</button>
						</c:if>
						<c:if test="${category.operate=='update'}">
						<button type="submit" class="btn btn-success">保存</button>
						</c:if>
						<button type="reset" class="btn btn-warning">重填</button>
					</div>
				</div>
			</form:form>
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
			$.validate();
		});
	</script>

</body>
</html>