<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>会员申请</title>
	<%@ include file="/WEB-INF/inc/simplehead.jsp"%>
	<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css">
</head>
<body>
	<div class="container">
	
		<div class="row">
			<div class="col-sm-12">
				<c:if test="${membership.operate=='add'}">
					<c:set var="actionurl" value="create.html"></c:set>
				</c:if>
				<c:if test="${membership.operate=='update'}">
					<c:set var="actionurl" value="update.html"></c:set>						
				</c:if>
								
			    <c:set var="typecode" value="${membership.type_code}"></c:set>
			    <c:set var="membertype" value="${fn:substring(typecode,0,1)}"></c:set>
			    
				<form:form modelAttribute="membership" method="post" action="${actionurl}" cssClass="form-horizontal" enctype="multipart/form-data">
					<form:hidden path="created_by" />
					<form:hidden path="operate" />
					<form:hidden path="user_id" />						
					<form:hidden path="businessProfile.member_id"/>
					<form:hidden path="member_id" />
					<form:hidden path="status" />
					<form:hidden path="type_name" />
					<c:if test="${membership.operate=='update'}">											
						<form:hidden path="type_code" />							
					</c:if>
					<div class="row">
						<div class="col-sm-12">
							<c:if test="${membership.operate=='add'}">
							    <h4>会员申请</h4>
							</c:if>
							<c:if test="${membership.operate=='update'}">
							    <h4>服务商信息更新</h4>
							</c:if>					   
						</div>
					</div>		
					
					<c:if test="${membership.operate=='add'}">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label for="type_code" class="col-md-2 control-label">会员类型</label>
									<div class="col-md-8">
										<form:select path="type_code" cssClass="form-control input-sm" maxlength="120" >
											<c:if test="${membership.memberTypeNotIs == 'C'}">
							  					<form:options items="${applicationScope.businessMemberTypeMap}"/>								            	
											</c:if>
											<c:if test="${membership.memberTypeNotIs == 'S'}">
							  					<form:options items="${applicationScope.CandBMemberTypeMap}"/>
								            </c:if>
										 </form:select>
									</div>
								</div>
							</div>
						</div>
					</c:if>						
					
					<div id="businessProfileDiv" >						 
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
									   <form:hidden path="businessProfile.categoryids" id="categoryids" data-validation="required"/>
									   <form:hidden class="form-control" path="businessProfile.categorynames" id="categorynames"/>
									   <span id="categorynamespan">${membership.businessProfile.categorynames} </span>
									   <a class="openViewPage btn btn-default btn-sm"	href="../dialog/categorydialog.html">选择业务</a>
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
									<c:if test="${membership.businessProfile.support_doc_name != null}">
										<div class="col-md-4 item-content">
											<a href="${membership.businessProfile.support_doc}">${membership.businessProfile.support_doc_name}</a>									
										</div>
									</c:if>
									<div class="col-md-4 item-content">											
										<input type="file" name="businessProfile.support_doc_file" id="fileBrowser"/> 
									</div>
								</div>
							</div>
						</div>							
					</div>						
					
					<div class="row">
						<div class="col-sm-12" style="text-align: center;">
							<button type="submit" class="btn btn-success" name="_eventId_finish">提交</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<!--col-sm-8-->
		
		<div class="row">
	        <div class="col-sm-12">
	            <div id="insertHtml" style="display:none"></div>
	            <div id="insertValue" style="display:none"></div>
	        </div>		
		</div>        
	</div>
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript">
	
		var businessHtml = "";
		$(document).ready(function(){	
			businessHtml = $('#businessProfileDiv').html();
			 
			$.validate();
	    	 
	    	$(".openEditPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ 
	 	    	document.forms[0].submit();
	 	    }});
	 	    
	 	    $(".openViewPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ } });	 	    
	
	 	    //Show business profile form only when selecting 'B' member_type.
		    var type_code = $('#type_code').val();		    
		    if(type_code.charAt(0) == "B"){
		    	$('#businessProfileDiv').html(businessHtml);
		    	$("#datepicker3").datepicker();
		 	    $(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ } });	 
		    }else{
		    	$('#businessProfileDiv').html('');
		    }
		});
		
		//Show business profile form only when selecting 'B' member_type.
		$('#type_code').on('change', function (e) {
		    var optionSelected = $("option:selected", this);
		    var type_code = this.value;		    
		    if(type_code.charAt(0) == "B"){
		    	$('#businessProfileDiv').html(businessHtml);
		    	$("#datepicker3").datepicker();
		    	$(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ } });	
		    }else{
		    	$('#businessProfileDiv').html('');
		    }	    
		});
		
		$("form").submit(function(e) {			
			var typeName = $('#type_code option:selected').text();
		 	$('#type_name').val(typeName);
	    });		
	</script>

</body>
</html>