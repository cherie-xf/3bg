<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>用户详细信息</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
</head>
<body>
	<div class="container">
		<!-- Contact Information -->

		<div class="row">
			<div class="row myrow">
				<div class="col-md-12">
				  <h4>操作成功</h4>
				</div>
			</div>
			<div class="row myrow">
			    <div class="col-md-12" align="center">
				      <img src="/images/smile.jpg">
				</div>
				<div class="col-md-12">
					<c:if test="${not empty message}">
						<div class="alert alert-success">${message}</div>
					</c:if>
				</div>
			</div>			
	   </div>
	</div>
</body>
</html>