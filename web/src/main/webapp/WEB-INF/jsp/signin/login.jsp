<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>用户登录</title>
<%@ include file="/WEB-INF/inc/head.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css"/>

</head>
<body>
	<div class="wrap">
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/topnav.jsp"%>
		<!--globalnav end-->
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/globalnav.jsp"%>
		<!--globalnav end-->

		<!--container Start-->
		<div class="container tbscontainer">
			<div class="main">
				<div class="row">
					<div class="col-sm-4">
						<img src="/images/1.jpg" class="img-responsive hidden-xs"
							alt="Responsive image">
					</div>
					<div class="col-sm-8">
					    <form:form modelAttribute="user" method="post" action="j_spring_security_check" cssClass="form-horizontal">
							<div class="row">
								<div class="col-sm-12">
									<h4 class="yonghu">用户登录</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="email" class="col-sm-2 control-label">邮箱</label>
										<div class="col-sm-8">
											<form:input path="email" id="email" class="form-control" placeholder="email"/>
										</div>
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="password" class="col-sm-2 control-label">登录密码</label>
										<div class="col-sm-8">
											<form:password path="password" class="form-control" placeholder="login password"/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="password" class="col-sm-2 control-label"></label>
										<div class="col-sm-8">
											<div class="checkbox">
											    <label>
											      <input type="checkbox" name="rememberme"/> 记住我
											    </label>
											 </div>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-10" style="text-align:center;">
								   <button type="submit" class="btn btn-success"> 登录 </button> <span><a href="signin/forgetpassword.html">忘记密码</a></span>
								</div>
							</div>
						</form:form>
					</div>
				</div>
				<!--col-sm-8-->
			</div>
			<!-- main -->
		</div>
		<!--container-->
	</div>
	<!--wrap end-->

	<!--footer start-->
	<%@include file="/WEB-INF/inc/globalfooter.jsp"%>
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script>
		$(document).ready(function() {
			
			$.validate({
				 modules:'security',
				 onModulesLoaded : function() {
				   $('input[name="confirmpassword"]').displayPasswordStrength();
				 }
			});			
			
			if($("#agreeTerm").prop("checked")){
				$("#nextbutton").removeAttr("disabled");
			}		
			$("#agreeTerm").click(function(){
				if($(this).prop("checked")){
					$("#nextbutton").removeAttr("disabled");
				}else{
					$("#nextbutton").attr("disabled","disabled");						
				}
			});
			
			$(".dateSelector").change(function(){
				if($("#monthSelector").val()!=''&&$("#daySelector").val()!=''&&$("#yearSelector").val()!=''){
				  var birthday = $("#monthSelector").val()+"/"+$("#daySelector").val()+"/"+$("#yearSelector").val();
				  console.log(birthday);
				  $("#birthday").val(birthday);
				}
			});
			
	    
			var getting = $.get("system/refreshDictionary.json");
			getting.done(function(data) {
				console.log(data);
			});			
			
		});
	</script>

</body>
</html>