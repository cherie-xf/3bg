<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>会员申请</title>
<%@ include file="/WEB-INF/inc/head.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css">

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
					    <c:set var="typecode" value="${signupForm.membership.type_code}"></c:set>
					    <c:set var="membertype" value="${fn:substring(typecode,0,1)}"></c:set>
						<form:form modelAttribute="signupForm" method="post" action="${flowExecutionUrl}" cssClass="form-horizontal" enctype="multipart/form-data">
							<div class="row">
								<div class="col-sm-12">
								   <c:if test="${membertype=='C'}"> 
									<h4>消费者会员申请</h4>
								   </c:if>
								   <c:if test="${membertype=='B'}"> 
									<h4>服务商会员申请</h4>
								   </c:if>
								</div>
							</div>
							
							<c:if test="${not empty membertype}">
							  <c:if test="${membertype == 'B'}"> 
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label for="type_code" class="col-sm-2 control-label">会员类型</label>
											<div class="col-sm-8">
											<form:select path="membership.type_code" cssClass="form-control input-sm" maxlength="120" >
								                   <form:options items="${applicationScope.businessMemberTypeMap}"/>
											 </form:select> 
											</div>
										</div>
									</div>
								</div>
							  </c:if>
							</c:if>
							
							<c:if test="${(empty membertype)}">
							  <div class="row">
								<div class="col-md-12">
									<h4>消费者会员介绍</h4>
									<p>adfjfka</p>
									<h4>服务商会员介绍</h4>
									<p>adfjfka</p>
								</div>
							  </div>
							</c:if>							
							
							<c:if test="${(membertype == 'C')}">
							  <div class="row">
								<div class="col-md-12">
									<h4>消费者会员介绍</h4>
									<p>adfjfka</p>
								</div>
							  </div>
							</c:if>
							
							<c:if test="${membertype == 'B'}"> 
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="businessProfile.business_name"	class="col-md-2 control-label">公司名称(英文)</label>
										<div class="col-md-8 item-content">
											<form:input class="form-control" path="businessProfile.business_name" placeholder="Business Name" data-validation="required"/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="businessProfile.categorynames" class="col-md-2 control-label">业务范围</label>
										<div class="col-md-8 item-content" id="categoryListDiv">
										   <form:hidden path="businessProfile.categoryids" placeholder="Owner Name" id="categoryids" data-validation="required"/>
										   <form:hidden class="form-control" path="businessProfile.categorynames" id="categorynames"/>
										   <span id="categorynamespan">${signupForm.businessProfile.categorynames} </span>
										   <a class="openViewPage btn btn-default btn-sm"	href="dialog/categorydialog.html">选择业务</a>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="businessProfile.owner_name"	class="col-md-2 control-label">联系人姓名:</label>
										<div class="col-md-8 item-content">
											<form:input class="form-control" path="businessProfile.owner_name" placeholder="Contact Name" data-validation="required"/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="businessProfile.email"	class="col-md-2 control-label">业务邮箱:</label>
										<div class="col-md-8 item-content">
											<form:input class="form-control" path="businessProfile.email" placeholder="Contact Email" data-validation="required"/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="businessProfile.phone"	class="col-md-2 control-label">业务电话:</label>
										<div class="col-md-8 item-content">
											<form:input class="form-control" path="businessProfile.phone" placeholder="Contact Phone" data-validation="required"/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="businessProfile.address"	class="col-md-2 control-label">公司地址:</label>
										<div class="col-md-8 item-content">
											<form:input class="form-control" path="businessProfile.address" placeholder="Company Address"/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="businessProfile.postcode" class="col-md-2 control-label">邮编:</label>
										<div class="col-md-8 item-content">
											<form:input class="form-control" path="businessProfile.postcode" placeholder="Postal Code"/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="businessProfile.website" class="col-md-2 control-label">公司网站:</label>
										<div class="col-md-8 item-content">
											<form:input class="form-control" path="businessProfile.website" placeholder="Website"/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="businessProfile.wechat" class="col-md-2 control-label">微信:</label>
										<div class="col-md-8 item-content">
											<form:input class="form-control" path="businessProfile.wechat" placeholder="Wechat"/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="businessProfile.is_person" class="col-md-2 control-label">Business类型</label>
										<div class="col-md-8 item-content">
											<form:label path="businessProfile.is_person" class="radio-inline">
											  <form:radiobutton path="businessProfile.is_person" name="inlineRadioOptions" id="inlineRadio1" value="true"/> 个人
											</form:label>
											<form:label path="businessProfile.is_person" class="radio-inline">
											  <form:radiobutton path="businessProfile.is_person" name="inlineRadioOptions" id="inlineRadio1" value="false"/> 企业
											</form:label>								
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<form:label path="businessProfile.business_number" cssClass="col-md-2 control-label">资格证号/BN:</form:label>
										<div class="col-md-8 item-content">
											<form:input path="businessProfile.business_number" cssClass="form-control input-sm" placeholder="资格证号/BN" maxlength="120" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<form:label path="businessProfile.tax_number" cssClass="col-md-2 control-label">税号:</form:label>
										<div class="col-md-8 item-content">
											<form:input path="businessProfile.tax_number" cssClass="form-control input-sm" placeholder="税号" maxlength="120" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<form:label path="businessProfile.wcb" cssClass="col-md-2 control-label">WCB号:</form:label>
										<div class="col-md-8 item-content">
											<form:input path="businessProfile.wcb" cssClass="form-control input-sm" placeholder="WCB号" maxlength="120" />
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<form:label path="businessProfile.business_start" cssClass="col-md-2 control-label">Business始于:</form:label>
										<div class="col-md-8 item-content">
											<fmt:formatDate value="${businessProfile.business_start}" var="businessStartString" pattern="MM/dd/YYYY"/>
											<form:input path="businessProfile.business_start" value = "${businessStartString}" cssClass="form-control input-sm" id="datepicker3" placeholder="Business start from" />
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<form:label path="businessProfile.referal_info" cssClass="col-md-2 control-label">推荐人信息:</form:label>
										<div class="col-md-8 item-content">
										    <form:textarea path="businessProfile.referal_info" cssClass="form-control input-sm" rows="3" />
										</div>
									</div>
								</div>
							</div>							
							
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<form:label path="businessProfile.support_doc" cssClass="col-md-2 control-label">支持文档:</form:label>
										<div class="col-md-4 item-content">
											<input type="file" name="businessProfile.support_doc_file" id="fileBrowser"/> 
										</div>
									</div>
								</div>
							</div>
							</c:if>
							<div class="row">
								<div class="col-sm-6" style="text-align: left;">
									<button type="submit" class="btn btn-success" name="_eventId_back"> 上一步 </button>
								</div>
								<div class="col-sm-4" style="text-align: right;">
									<button type="submit" class="btn btn-success" name="_eventId_finish">完成</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
				<!--col-sm-8-->
			</div>
			<div class="row">
		        <div class="col-sm-12">
		            <div id="insertHtml" style="display:none"></div>
		            <div id="insertValue" style="display:none"></div>
		        </div>		
			</div>
		</div>
		<!-- main -->
	</div>
	<!--container-->
	<!--wrap end-->

	<!--footer start-->
	<%@include file="/WEB-INF/inc/globalfooter.jsp"%>
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script>
		// $.validate({
		//     form:'#contactform'
		// });

		$(document).ready(function(){
					$("#datepicker").datepicker();
					
				    $(".openEditPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ 
				    	document.forms[0].submit();
				    }});
				    
				    $(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ } });

					$.validate();	
				});
	</script>

</body>
</html>