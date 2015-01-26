<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>服务商增加/修改</title>
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
						<c:if test="${businessProfile.operate=='add'}">
						服务商增加
					   	</c:if>
						<c:if test="${businessProfile.operate=='update'}">
						服务商修改
						</c:if>
					</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty businessProfile.feedbackMessage}">
						<div class="alert alert-success">${businessProfile.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			<c:if test="${businessProfile.operate=='add'}">
				<c:set var="actionurl" value="create.html"></c:set>
			</c:if>
			<c:if test="${businessProfile.operate=='update'}">
				<c:set var="actionurl" value="update.html"></c:set>
			</c:if>

			<form:form modelAttribute="businessProfile" method="post" action="${actionurl}" cssClass="form-inline" role="form" enctype="multipart/form-data">
				<form:hidden path="created_by" />
				<form:hidden path="operate" />
				<form:hidden path="tcpip" />				
				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="member_id" cssClass="col-md-2 control-label">新服务商ID<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content form-inline">
									<form:input path="member_id" cssClass="form-control input-sm" placeholder="新服务商ID由系统自动生成" maxlength="120" readonly ="true"  />
								</div>
							</div>
						</div>
						
				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="website" cssClass="col-md-2 control-label">公司网址：</form:label>
								<div class="col-md-8 item-content">
									<form:input path="website" cssClass="form-control input-sm" placeholder="公司网址" maxlength="255" 
										data-validation="url" data-validation-optional="true"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="category_list" cssClass="col-md-2 control-label">业务范围:</form:label>
								<div class="col-md-8 item-content" id="categoryListDiv">
										<c:forEach items="${businessProfile.category_list}" var="var" varStatus="rowCounter">
											<form:hidden path="category_list[${rowCounter.index}].member_id"/>
											<form:hidden path="category_list[${rowCounter.index}].category_id"/>
											<div name='categorynameDiv' class="col-md-2 item-content">
												<c:out value="${var.category_name}"/>
											</div>
										</c:forEach>
										<a class="openViewPage btn btn-default btn-sm" href="choosecategory.html?member_id=${businessProfile.member_id}">选择业务</a>
										<div class="" id="alertMessageDiv"></div>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="business_name" cssClass="col-md-2 control-label">公司名称：</form:label>
								<div class="col-md-8 item-content">
									<form:input path="business_name" cssClass="form-control input-sm" placeholder="公司名称" maxlength="255"
										data-validation="required" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="owner_name" cssClass="col-md-2 control-label">公司负责人:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="owner_name" cssClass="form-control input-sm" placeholder="公司负责人" maxlength="45" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="description" cssClass="col-md-2 control-label">公司描述:</form:label>
								<div class="col-md-8 item-content">
									<form:textarea path="description" cssClass="form-control input-sm editbox" placeholder="公司描述" rows="6" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="phone" cssClass="col-md-2 control-label">手机号:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="phone" cssClass="form-control input-sm" placeholder="手机号" maxlength="45" 
										data-validation="required" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="email" cssClass="col-md-2 control-label">电子邮箱:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="email" cssClass="form-control input-sm" placeholder="电子邮箱" maxlength="255"
										 />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="address" cssClass="col-md-2 control-label">公司地址:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="address" cssClass="form-control input-sm" placeholder="公司地址" maxlength="255" 
									 	data-validation="required" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="wechat" cssClass="col-md-2 control-label">微信号:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="wechat" cssClass="form-control input-sm" placeholder="微信号" maxlength="45" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="postcode" cssClass="col-md-2 control-label">邮编:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="postcode" cssClass="form-control input-sm" placeholder="邮编" maxlength="45" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="is_person" cssClass="col-md-2 control-label">Business类型:</form:label>
								<div class="col-md-8 item-content">
									<form:label path="is_person" class="radio-inline">
									  <form:radiobutton path="is_person" name="inlineRadioOptions" id="inlineRadio1" value="true"/> 个人
									</form:label>
									<form:label path="is_person" class="radio-inline">
									  <form:radiobutton path="is_person" name="inlineRadioOptions" id="inlineRadio1" value="false"/> 企业
									</form:label>								
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="business_start" cssClass="col-md-2 control-label">Business始于:</form:label>
								<div class="col-md-8 item-content">
									<fmt:formatDate value="${businessProfile.business_start}" var="businessStartString" pattern="MM/dd/YYYY"/>
									<form:input path="business_start" value = "${businessStartString}" cssClass="form-control input-sm" id="datepicker3" placeholder="Business始于" 
										data-validation="date" data-validation-format="MM/dd/YYYY" data-validation-optional="true"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="business_number" cssClass="col-md-2 control-label">资格证号/BN:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="business_number" cssClass="form-control input-sm" placeholder="资格证号/BN" maxlength="120" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="tax_number" cssClass="col-md-2 control-label">税号:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="tax_number" cssClass="form-control input-sm" placeholder="税号" maxlength="120" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="wcb" cssClass="col-md-2 control-label">WCB号:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="wcb" cssClass="form-control input-sm" placeholder="WCB号" maxlength="120" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<div class="col-md-2"></div>
								<div class="col-md-8 item-content">
									<form:label path="quick_respond" class="checkbox-inline">
  										<form:checkbox path="quick_respond"/> 2小时快速反馈
									</form:label>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="referal_info" cssClass="col-md-2 control-label">推荐人信息:</form:label>
								<div class="col-md-8 item-content">
									<form:textarea path="referal_info" cssClass="form-control input-sm" placeholder="推荐人信息" rows="6" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="contract_date" cssClass="col-md-2 control-label">签约日期:</form:label>
								<div class="col-md-8 item-content">
									<fmt:formatDate value="${businessProfile.contract_date}" var="contract_dateString" pattern="MM/dd/YYYY"/>
									<form:input path="contract_date" value = "${contract_dateString}" cssClass="form-control input-sm" id="datepicker4" placeholder="签约日期" 
										data-validation-optional="true"  data-validation="date" data-validation-format="MM/dd/YYYY" />
								</div>
							</div>
						</div>
						<%-- <div class="row myrow">
							<div class="col-md-12">
								<form:label path="support_doc" cssClass="col-md-2 control-label">支持文档:</form:label>
								<div class="col-md-4 item-content">
									<a href="${businessProfile.support_doc}">${businessProfile.support_doc_name}</a>									
								</div>
								<div class="col-md-4 item-content">
									<input type="file" name="support_doc_file" id="fileBrowser"/> 
								</div>
							</div>
						</div> --%>
						<div class="row myrow">
							<div class="col-md-12">
								<div class="col-md-2"></div>
								<div class="col-md-8 item-content">
									<form:label path="publication" class="checkbox-inline">
  										<form:checkbox path="publication"/> 发布服务商信息（Busines Number,税号, WCB等不会发布）
									</form:label>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="recommend_level_id" cssClass="col-md-2 control-label">推荐级别:</form:label>
								<div class="col-md-8 item-content">
									<form:select path="recommend_level_id" cssClass="form-control input-sm" maxlength="120" >
					                   <form:options items="${recommendLevelMap}"/>
					                 </form:select>
								</div>
							</div>
						</div>
<!-- 						To support inserting image, add below div.						 -->
						<div class="row">
					        <div class="col-sm-12">
					            <div id="insertHtml" style="display:none"></div>
					            <div id="insertValue" style="display:none"></div>
					        </div>		
						</div>
												
					</div>
				</div>
												
					</div>
				</div>				
							
				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
						<c:if test="${businessProfile.operate=='add'}">
						<button type="submit" class="btn btn-success">创建</button>
						</c:if>
						<c:if test="${businessProfile.operate=='update'}">
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
	    	 $("#datepicker3").datepicker();
	    	 $("#datepicker4").datepicker();
	    	 
	    	 $(".openEditPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ 
	 	    	document.forms[0].submit();
	 	    }});
	 	    
	 	    $(".openViewPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ } });	 	    

		});
		
		//Validte if category selected.
		$("form").submit(function(e) {
			 
		 	if($("#alertMessageDiv").length > 0 && $("div[name='categorynameDiv']").length == 0){
// 				var message = "请选择分类业务";				
// 				showAlert("alertMessageDiv", "danger", message);
// 				e.preventDefault();
			}else{
				$("#alertMessageDiv").empty();
			}	 
	        
	    });
		
		function showAlert(containerId, alertType, message) {
			$("#" + containerId).empty();
		    $("#" + containerId).append('<div class="alert alert-' + alertType + '" id="alert' + containerId + '">' + message + '</div>');
		    $("#" + containerId).alert();
		    //window.setTimeout(function () { $("#" + containerId).alert('close'); }, 10000);
		}
		

	</script>

</body>
</html>