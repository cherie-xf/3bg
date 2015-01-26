<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>用户注册</title>
<%@ include file="/WEB-INF/inc/head.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css"/>

</head>
<body>
	<div class="wrap">
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
					    <form:form modelAttribute="signupForm" method="post" action="${flowExecutionUrl}" cssClass="form-horizontal">
							<div class="row">
								<div class="col-sm-12">
									<h4 class="yonghu">用户注册</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="user.email" class="col-sm-2 control-label">邮箱</label>
										<div class="col-sm-8">
											<form:input path="user.email" id="email" class="form-control" placeholder="email" 
											data-validation="server" data-validation-url="signup/validateemail.html"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">手机</label>
										<div class="col-sm-8">
											<form:input path="user.phone" class="form-control" placeholder="cell" 
											data-validation-allowing="int" data-validation="number" maxlength="15"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label">姓</label>
										<div class="col-sm-6">
											<form:input path="user.lastName" class="form-control" placeholder="last name" data-validation="required"/>
										</div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">名</label>
										<div class="col-sm-6">
											<form:input path="user.firstName" class="form-control" placeholder="first name" data-validation="required"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group" id="mima">
										<label for="password" class="col-sm-2 control-label">登录密码</label>
										<div class="col-sm-8">
											<form:password path="user.password_confirmation" id="password_confirmation" data-validation="strength" data-validation-strength="1" class="form-control" placeholder="login password"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">确认密码</label>
										<div class="col-sm-8">
											<form:password path="user.password" id="password" data-validation="confirmation" class="form-control" placeholder="Confirm password"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">成为会员</label>
										<div class="col-sm-8">
											<div class="radio">
												<label> 
												    <form:radiobutton path="membership.type_code" value="C1" checked="checked"/>普通会员
												</label>
											</div>
											<div class="radio">
												<label> 
												    <form:radiobutton path="membership.type_code" value="C2"/>VIP会员
												</label>
											</div>
											<div class="radio">
												<label> 
												    <form:radiobutton path="membership.type_code" value="B1"/>服务商会员
												</label>
											</div>
											<div class="radio">
												<label> 
                                                     <form:radiobutton path="membership.type_code" value=""/>暂不成为会员
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<!-- panel-group -->
								<div class="col-sm-12">
									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="headingOne">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
													aria-controls="collapseOne"> 更多选项<span class="glyphicon glyphicon-arrow-down"></span>
												</a>
											</h4>
										</div>
										<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne"
											style="padding-top: 15px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="form-group">
														<label for="inputEmail3" class="col-sm-2 control-label">性别</label>
														<div class="col-sm-2">
															<div class="radio">
																<label> 
																  <form:radiobutton path="userProfile.gender" value="M"/>男
																</label>
															</div>
														</div>
														<div class="col-sm-2">
															<div class="radio">
																<label> 
																   <form:radiobutton path="userProfile.gender" value="F"/>女
																</label>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<div class="form-group">
														<label for="inputEmail3" class="col-sm-2 control-label">生日</label>
														<form:hidden path="userProfile.birthday" id="birthday" class="form-control"/>
														<div class="col-sm-3">
															<div class="input-group">
															  <select class="form-control dateSelector" id="yearSelector">
															    <%@ include file="/WEB-INF/inc/yearoption.jsp"%>
															  </select>
															  <span class="input-group-addon">年</span>
															</div>
														</div>
														<div class="col-sm-3">
														  <div class="input-group">
															  <select class="form-control dateSelector" id="monthSelector">
															    <%@ include file="/WEB-INF/inc/monthoption.jsp"%>
															  </select>
															  <span class="input-group-addon">月</span>
														  </div>
														</div>
														<div class="col-sm-3">
														  <div class="input-group">
															  <select class="form-control dateSelector" id="daySelector">
															    <%@ include file="/WEB-INF/inc/dayoption.jsp"%>
															  </select>
															  <span class="input-group-addon">日</span>
														  </div>
														</div>													
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<div class="form-group">
														<label for="inputPassword3" class="col-sm-2 control-label">微信</label>
														<div class="col-sm-8">
														    <input type="hidden" name="contact.type_code" value="30WC"/>
															<form:input path="contact.contact_value" class="form-control" placeholder="wechat"/>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<div class="form-group">
														<label for="inputPassword3" class="col-sm-2 control-label">地址</label>
														<div class="col-sm-8">
															<form:input path="address.address" class="form-control" placeholder="address"/>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<div class="form-group">
														<label for="inputPassword3" class="col-sm-2 control-label">城市</label>
														<div class="col-sm-8">
															<form:select path="address.city_code" items="${applicationScope.cityMap}" class="form-control">
															</form:select>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<div class="form-group">
														<label for="inputPassword3" class="col-sm-2 control-label">邮编</label>
														<div class="col-sm-8">
															<form:input path="address.postal_code" class="form-control" placeholder="postal code"/>
														</div>
													</div>
												</div>
											</div>
																		
										</div><!-- collapse end -->
									</div>
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										
										<div class="col-sm-8">
											<div class="checkbox">
											    <label>
											      <form:checkbox path="userProfile.agreeTerm" id="agreeTerm"/> 阅读并同意 <a href="#">服务条款</a>
											    </label>
											 </div>
										</div>
									</div>
								</div>
							</div>	

							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										
										<div class="col-sm-8">
											<div class="checkbox">
											    <label>
											      <form:checkbox path="userProfile.smsContact" id="agreeSms"/> 同意接受三桥服务以电话、短信、邮件和微信方式与我进行业务联系
											    </label>
											 </div>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										
										<div class="col-sm-8">
											<div class="checkbox">
											    <label>
											      <form:checkbox path="userProfile.newsLetter"/> 同意订阅新闻邮件
											    </label>
											 </div>
										</div>
									</div>
								</div>
							</div>	
	
							<div class="row">
								<div class="col-sm-10" style="text-align:right;">
								   <button type="submit" class="btn btn-success" name="_eventId_next" disabled="disabled" id="nextbutton"> 下一步 </button>
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
				   $('input[name="user.password_confirmation"]').displayPasswordStrength();
				 }
			});			
			
			$("#email").focus();
			
			if($("#agreeTerm").prop("checked")&&$("#agreeSms").prop("checked")){
				$("#nextbutton").removeAttr("disabled");
			}		
			
			$("#agreeTerm").click(function(){
				if($(this).prop("checked")&&$("#agreeSms").prop("checked")){
					$("#nextbutton").removeAttr("disabled");
				}else{
					$("#nextbutton").attr("disabled","disabled");						
				}
			});
			
			$("#agreeSms").click(function(){
				if($(this).prop("checked")&&$("#agreeTerm").prop("checked")){
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