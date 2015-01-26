<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>会员帐号增加/修改</title>
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
						<c:if test="${membership.operate=='add'}">
						会员帐号增加
					   	</c:if>
						<c:if test="${membership.operate=='update'}">
						会员帐号修改
						</c:if>
					</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty membership.feedbackMessage}">
						<div class="alert alert-success">${membership.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			<c:if test="${membership.operate=='add'}">
				<c:set var="actionurl" value="create.html"></c:set>
			</c:if>
			<c:if test="${membership.operate=='update'}">
				<c:set var="actionurl" value="update.html"></c:set>
			</c:if>

			<form:form modelAttribute="membership" method="post" action="${actionurl}" cssClass="form-inline" role="form" enctype="multipart/form-data">
				<form:hidden path="created_by" />
				<form:hidden path="operate" />
				<form:hidden path="user_id" />
				<form:hidden path="businessProfile.member_id"/>
				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="member_id" cssClass="col-md-2 control-label">会员ID<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content form-inline">
									<form:input path="member_id" cssClass="form-control input-sm" placeholder="由系统自动生成会员ID" maxlength="120" readonly ="${membership.operate=='update'}" 
										data-validation="required" data-validation-if-checked="memberIDManualInput"/>
									<c:if test="${membership.operate=='add'}">
										<div class="checkbox">
								    		  <form:label path="memberIDManualInput">
								      			<form:checkbox path="memberIDManualInput"/> 手动输入会员ID
								    		  </form:label>
								  		 </div>										
									</c:if>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="type_code" cssClass="col-md-2 control-label">会员类型:</form:label>
								<div class="col-md-8 item-content">
									<c:choose> 
										<c:when test="${membership.subMemberType == 'B'}"> 
											<form:select path="type_code" cssClass="form-control input-sm" maxlength="120" >
							                   <form:options items="${applicationScope.businessMemberTypeMap}"/>
							                 </form:select> 
										</c:when> 
										<c:when test="${membership.subMemberType == 'C'}"> 
											<form:select path="type_code" cssClass="form-control input-sm" maxlength="120" >
							                   <form:options items="${applicationScope.customerMemberTypeMap}"/>
							                 </form:select> 
										</c:when> 
										<c:when test="${membership.subMemberType == 'S'}"> 
											<form:select path="type_code" cssClass="form-control input-sm" maxlength="120" >
							                   <form:options items="${applicationScope.innerMemberTypeMap}"/>
							                 </form:select> 
										</c:when> 
										<c:otherwise> 
											<form:select path="type_code" cssClass="form-control input-sm" maxlength="120" >
							                   <form:options items="${applicationScope.memberTypeMap}"/>
							                 </form:select> 
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="credit" cssClass="col-md-2 control-label">积分:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="credit" cssClass="form-control input-sm" placeholder="积分" maxlength="120"
										data-validation="number" data-validation-optional="true"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="valid_from" cssClass="col-md-2 control-label">生效日期:</form:label>
								<div class="col-md-8 item-content">
									<fmt:formatDate value="${membership.valid_from}" var="validFromString" pattern="MM/dd/YYYY"/>
									<form:input path="valid_from" value = "${validFromString}" cssClass="form-control input-sm" id="datepicker1" placeholder="MM/dd/YYYY" 
										data-validation="date" data-validation-format="MM/dd/YYYY" data-validation-optional="true"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="valid_to" cssClass="col-md-2 control-label">失效日期:</form:label>
								<div class="col-md-8 item-content">
									<fmt:formatDate value="${membership.valid_to}" var="validToString" pattern="MM/dd/YYYY"/>
									<form:input path="valid_to" value = "${validToString}" cssClass="form-control input-sm" id="datepicker2" placeholder="MM/dd/YYYY"
										data-validation="date" data-validation-format="MM/dd/YYYY" data-validation-optional="true"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="valid_to" cssClass="col-md-2 control-label">会员状态:</form:label>
								<div class="col-md-8 item-content">									
									<form:label path="status" class="radio-inline">
								  		<form:radiobutton path="status" name="inlineRadioOptions" value="0"/> Inactive
									</form:label>
									<form:label path="status" class="radio-inline">
								  		<form:radiobutton path="status" name="inlineRadioOptions" value="1"/> Active
									</form:label>
									<form:label path="status" class="radio-inline">
								  		<form:radiobutton path="status" name="inlineRadioOptions" value="2"/> Suspend
									</form:label>									
								</div>
							</div>
						</div>						
					</div>
				</div>
				<div id="businessProfileDiv" >
					<%@ include file="/WEB-INF/jsp/membership/businessprofileform.jsp"%>					
				</div>
							
				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
						<c:if test="${membership.operate=='add'}">
						<button name="submitBtn" type="submit" class="btn btn-success">创建</button>
						</c:if>
						<c:if test="${membership.operate=='update'}">
						<button name="submitBtn" type="submit" class="btn btn-success">保存</button>
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
		var businessHtml = "";
		$(document).ready(function() {
			 businessHtml = $('#businessProfileDiv').html();
			 
			 $.validate();
	    	 $("#datepicker1").datepicker(); 
	    	 $("#datepicker2").datepicker();	    	 
	    	 
	    	 $(".openEditPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ 
	 	    	document.forms[0].submit();
	 	    }});
	 	    
	 	    $(".openViewPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ } });	 	    

	 	    //Allow user to update member_id manully when creating and no auto generate checkbox checked.
	 	    if($("input:checkbox[name=memberIDManualInput]").is(':checked')){
	 	    	$('input[name=member_id]').prop('placeholder', '请输入会员ID');
	 	  		$('#member_id').prop("readonly", false);
	 	  	}else{
	 	  		$('input[name=member_id]').prop('placeholder', '由系统自动生成会员ID');
		    	$('#member_id').prop("readonly", true);	
		    }	
	 	    
	 	    //Show business profile form only when selecting 'B' member_type.
		    var type_code = $('#type_code').val();		    
		    if(type_code.charAt(0) == "B"){
		    	$('#businessProfileDiv').html(businessHtml);
		    	$("#datepicker3").datepicker();
		    	$("#datepicker4").datepicker();
		 	    $(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ } });	 	    
		    	editorInit();
		    }else{
		    	$('#businessProfileDiv').html('');
		    }
		});
		
		//Validte if category selected.
		$("form").submit(function(e) {			 
		 	if($("#alertMessageDiv").length > 0 && $("div[name='categorynameDiv']").length == 0){
				var message = "请选择分类业务";				
				showAlert("alertMessageDiv", "danger", message);
				e.preventDefault();
			}else{
				$("#alertMessageDiv").empty();
			}	 
	        
	    });
		
		function showAlert(containerId, alertType, message) {
			$("#" + containerId).empty();
		    $("#" + containerId).append('<div class="alert alert-' + alertType + '" id="alert' + containerId + '">' + 
		    		"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span><span class='sr-only'>Error:</span> "
		    		+ message + '</div>');
		    $("#" + containerId).alert();
		    //window.setTimeout(function () { $("#" + containerId).alert('close'); }, 10000);
		}		
		
 	    //Show business profile form only when selecting 'B' member_type.
		$('#type_code').on('change', function (e) {
		    var optionSelected = $("option:selected", this);
		    var type_code = this.value;		    
		    if(type_code.charAt(0) == "B"){
		    	$('#businessProfileDiv').html(businessHtml);
		    	$("#datepicker3").datepicker();
		    	$("#datepicker4").datepicker();
		 	    $(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ } });	 	    
		    	editorInit();
		    }else{
		    	$('#businessProfileDiv').html('');
		    }	    
		});
		
 	    //Allow user to update member_id manully when creating and no auto generate checkbox checked.
		$("input:checkbox[name=memberIDManualInput]").change(function() {
			var $this = $(this);
		    if ($this.is(':checked')) {	    
		    	$('input[name=member_id]').prop('placeholder', '请输入会员ID');
	 	  		$('#member_id').prop("readonly", false);
	 	  	}else{
	 	  		$('input[name=member_id]').prop('placeholder', '由系统自动生成会员ID');
		    	$('#member_id').prop("readonly", true);
	 	  	}
		});
 	    
 	    //To initial the rich editor.
 	    function editorInit(){
 	    	
 	    	tinymce.init({
		        selector: ".editbox",
		        //language: "zh_CN",
		        relative_urls: false,
		        plugins: [
		            "advlist autolink lists link image charmap preview anchor",
		            "searchreplace visualblocks visualchars code fullscreen",
		            "media save table contextmenu directionality",
		            "emoticons paste textcolor"
		        ],
		        menubar:false,
		        toolbar1: "link imgbutton media | undo redo |  bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent",
		        toolbar2: "forecolor backcolor | styleselect | table | code fullscreen",	    
		        content_css:"/css/globalcss.css",
		        setup: function(editor) {
		            editor.addButton('imgbutton', {
		                text: false,
		                title: 'insert image',
		                icon: 'image',
		                onclick: function() {
		                    lightbox(editor);
		                }
		            });
		        }
		    }); 
 	    	
 	    }	    
			
	    function lightbox(editor){
	   	 $.colorbox({width:"90%", height:"100%", href:"/admin/dialog/medialib.html", iframe:true,fixed:true,
	   	    onComplete:function(){
	   	    	$("#insertHtml").html("");
	   	    },
	   		onCleanup:function(){
	   		 var insertHtml=$("#insertHtml").html();
	   		 editor.insertContent(insertHtml);
	   	    }, 
	   	    onClosed:function(){
	   		 
	   	    }});
	   }  
	</script>

</body>
</html>