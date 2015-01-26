<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>用户详细信息</title>
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
					<c:if test="${sysUser.operate=='add'}">
						新建用户
					</c:if>
					<c:if test="${sysUser.operate=='update'}">
						修改用户
					</c:if>
				</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty sysUser.feedbackMessage}">
						<div class="alert alert-success">${sysUser.feedbackMessage}</div>
					</c:if>
				</div>
			</div>			
				<c:if test="${sysUser.operate=='add'}">
					<c:set var="actionurl" value="adduser.html"></c:set>
				</c:if>
				<c:if test="${sysUser.operate=='update'}">
					<c:set var="actionurl" value="updateuser.html"></c:set>
				</c:if>
							
			<form:form modelAttribute="sysUser" method="post" action="${actionurl}" cssClass="form-inline" role="form">
				<form:hidden path="userid" />
				<form:hidden path="operate" />
				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-6">
								<form:label path="email" cssClass="col-md-3 control-label">邮箱:<font color="red">*</font>:</form:label>
								<div class="col-md-9 item-content">
								   <c:if test="${sysUser.operate=='add'}">
									<form:input path="email" cssClass="form-control input-sm" placeholder="Email" data-validation="email" maxlength="45" />
								   </c:if>
								   <c:if test="${sysUser.operate=='update'}">
									<form:input path="email" cssClass="form-control input-sm" readonly="readonly" placeholder="Email" maxlength="45" />
								   </c:if>
								</div>
							</div>
							<div class="col-md-6">
								<form:label path="pass" cssClass="col-md-3 control-label">密码:</form:label>
								<div class="col-md-9">
									<form:input path="pass" cssClass="form-control input-sm"
										placeholder="Password" maxlength="45" />
								</div>
							</div>							
						</div>
					</div>

					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-6">
								<form:label path="usertype" cssClass="col-md-3 control-label">用户类型:</form:label>
								<div class="col-md-9">
									<form:select path="usertype" items="${sysUser.usertypelist}" cssClass="form-control input-sm"/>
								</div>
							</div>

							<div class="col-md-6">
								<form:label path="status" cssClass="col-md-3 control-label">状态:</form:label>
								<div class="col-md-9">
									<form:radiobutton path="status" value="0" />
									<img src="/images/icon-fail.png">未激活
									<form:radiobutton path="status" value="1" />
									<img src="/images/icon-ok.png">激活
									<form:radiobutton path="status" value="2" />
									<img src="/images/lock.png">锁定
								</div>
							</div>
						</div>
					</div>



					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-6">
								<form:label path="firstname" cssClass="col-md-3 control-label">名:</form:label>
								<div class="col-md-9">
									<form:input path="firstname" cssClass="form-control input-sm"
										placeholder="firstname" maxlength="45" />
								</div>
							</div>
							<div class="col-md-6">
								<form:label path="lastname" cssClass="col-md-3 control-label">姓:</form:label>
								<div class="col-md-9">
									<form:input path="lastname" cssClass="form-control input-sm"
										placeholder="lastname" maxlength="45" />
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-6">
								<form:label path="birth" cssClass="col-md-3 control-label">生日:</form:label>
								<div class="col-md-9">
									<form:input path="birth" cssClass="form-control input-sm" placeholder="MM/dd/YYYY" maxlength="45" id="datepicker"/>
								</div>
							</div>
							<div class="col-md-6">
								<form:label path="sex" cssClass="col-md-3 control-label">性别:</form:label>
								<div class="col-md-9">
								    <form:radiobutton path="sex" value="M" />男
								    <form:radiobutton path="sex" value="F" />女
								</div>
							</div>
						</div>
					</div>



		</div>

		<div class="row myrow">
			<div class="col-md-12">
				<div class="row myrow">
					<div class="col-md-6">
						<form:label path="phone" cssClass="col-md-3 control-label">电话:</form:label>
						<div class="col-md-9">
							<form:input path="phone" cssClass="form-control input-sm" data-validation="number" data-validation-allowing="int" placeholder="Phone" maxlength="20" />
						</div>
					</div>

					<div class="col-md-6">
						<form:label path="cell" cssClass="col-md-3 control-label">手机:</form:label>
						<div class="col-md-9">
							<form:input path="cell" cssClass="form-control input-sm"
								placeholder="Cell" maxlength="20" />
						</div>
					</div>
			    </div>
			</div>		
			<div class="col-md-12">
				<div class="row myrow">					
					<div class="col-md-6">
						<form:label path="address" cssClass="col-md-3 control-label">街道:</form:label>
						<div class="col-md-9">
							<form:input path="address" cssClass="form-control input-sm"
								placeholder="Street Address" maxlength="20" />
						</div>
					</div>
					<div class="col-md-6">
						<form:label path="city" cssClass="col-md-3 control-label">城市:</form:label>
						<div class="col-md-9">
							<form:input path="city" cssClass="form-control input-sm"
								placeholder="City" maxlength="20" />
						</div>
					</div>
			    </div>
			</div>		
		</div>




		<div class="row myrow">
		  <div class="col-md-12">
		    <div class="col-md-12">
		      <form:label path="description" cssClass="control-label">用户介绍:</form:label>
		    </div>
			<div class="col-md-12">
				<form:textarea path="description" cssClass="form-control input-sm editbox" rows="10"/>
			</div>
	      </div>
		</div>


		<div class="row myrow">
			<div class="col-md-12" style="text-align: center">
				<button type="submit" class="btn btn-success">保存</button>
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
     $(document).ready(function(){
         $("#datepicker").datepicker();
         
         $.validate({
        	 modules:'security'
         });          
     });
   </script>

</body>
</html>