<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>上传图片</title>
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
					<h4>上传图片</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty media.feedbackMessage}">
						<div class="alert alert-success">${media.feedbackMessage}</div>
					</c:if>
				</div>
			</div>

			<form:form modelAttribute="media" method="post" action="create.html" cssClass="form-inline" role="form" enctype="multipart/form-data">
				<form:hidden path="created_by" />
				<div class="row myrow">
					<div class="col-md-12">
						<div class="form-group">
							<form:label path="picfile" cssClass="col-md-3 control-label">本地文件:</form:label>
							<div class="col-md-9">
								<input type="file" name="picfile" id="imgbrowser" accept="image/*" /> 
								    <img id="imgloading" src="/images/ajax-loader.gif" style="display:none"><span>文件请使用英文名,中文名不支持。</span>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="title" cssClass="col-md-2 control-label">关键字:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="title" cssClass="form-control input-sm"
										placeholder="定义图片的关键字，如蓝天白云。如不填写，系统默认取文件名为关键字" maxlength="120" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<form:label path="description" cssClass="col-md-2 control-label">描述:</form:label>
						<div class="col-md-8">
							<form:textarea path="description"
								cssClass="form-control input-sm editbox" rows="3" />
						</div>
					</div>
				</div>

				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
						<button type="submit" class="btn btn-success">上传</button>
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
	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>

</body>
</html>