<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>资讯表单</title>
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
						<c:if test="${content.operate=='add'}">
						新建资讯内容
					   </c:if>
						<c:if test="${content.operate=='update'}">
						修改资讯内容
					    </c:if>
					</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty content.feedbackMessage}">
						<div class="alert alert-success">${content.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			<c:if test="${content.operate=='add'}">
				<c:set var="actionurl" value="create.html"></c:set>
			</c:if>
			<c:if test="${content.operate=='update'}">
				<c:set var="actionurl" value="update.html"></c:set>
			</c:if>

			<form:form modelAttribute="content" method="post" action="${actionurl}"
				cssClass="form-inline" role="form" enctype="multipart/form-data">
				<form:hidden path="created_by" />
				<form:hidden path="operate" />
				<form:hidden path="sid" />
				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="title" cssClass="col-md-2 control-label">标题 <font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="title" cssClass="form-control input-sm" placeholder="Content Title" maxlength="120"
										data-validation="required" data-validation-error-msg="请输入标题!" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="brief" cssClass="col-md-2 control-label">简介</form:label>
								<div class="col-md-8 item-content">
									<form:textarea path="brief" cssClass="form-control input-sm" placeholder="Brief" maxlength="255" rows="3"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="class_id" cssClass="col-md-2 control-label">内容分类:</form:label>
								<div class="col-md-8">
						            <div class="form-group">
						                 <form:select path="class_id" cssClass="form-control input-sm">
						                   <form:options items="${applicationScope.contentClassMap}"/>
						                 </form:select>
					                </div>	
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="content" cssClass="col-md-2 control-label">内容:</form:label>
								<div class="col-md-8">
									<form:textarea path="Content" cssClass="form-control input-sm editbox" rows="15" />
								</div>
							</div>						
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="meta_keyword"
									cssClass="col-md-2 control-label">关键字:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="meta_keyword"
										cssClass="form-control input-sm" placeholder="Meta Keyword"
										maxlength="256" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="meta_desc" cssClass="col-md-2 control-label">搜索简述:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="meta_desc" cssClass="form-control input-sm" placeholder="Meta Description" maxlength="256"/>
								</div>
							</div>
						</div>

						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="status" cssClass="col-md-2 control-label">内容状态:</form:label>
								<div class="col-sm-8">
								<div class="col-md-8 item-content">
									<c:forEach items="${applicationScope.blogStatusMap}" var="var" varStatus="rowCounter">
										<form:label path="status" class="radio-inline">
									  		<form:radiobutton path="status" value="${var.key}"/> ${var.value}
										</form:label>										
									</c:forEach>
								</div>							
								</div>

							</div>
						</div>

					</div>

				</div>
				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
					<button type="submit" class="btn btn-success">
						<c:if test="${content.operate=='add'}">
							创建
						</c:if>
						<c:if test="${content.operate=='update'}">
							保存
						</c:if>
						</button>
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
			
		});
	</script>

</body>
</html>