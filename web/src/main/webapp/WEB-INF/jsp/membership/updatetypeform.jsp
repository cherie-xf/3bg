<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>升降级申请</title>
	<%@ include file="/WEB-INF/inc/simplehead.jsp"%>
</head>
<body>
	<div class="container">
            <h3>会员ID:<em class="hy-id">${memberTypeChangeForm.member_id}</em>升降级申请</h3>
            <div class="modal-body">
                <form:form class="form-horizontal" modelAttribute="memberTypeChangeForm" method="post" action="updatetype.html" role="form" enctype="multipart/form-data">
					<form:hidden path="member_id" />
					<form:hidden path="currentTypeCode"/>
					<form:hidden path="currentTypeName"/>
					<form:hidden path="newTypeName"/>
					<div class="form-group">
                        <form:label path="newTypeName" class="col-md-3 control-label">原会员类型：</form:label>
                        <div class="col-sm-9 text-left">
                            <p class="form-control-static">${memberTypeChangeForm.currentTypeName}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="newTypeCode" class="col-md-3 control-label">变更为：</form:label>
                        <div class="col-md-4" >
                        	<c:choose> 
	                        	<c:when test="${memberTypeChangeForm.subMemberType == 'B'}"> 
									<form:select path="newTypeCode" cssClass="form-control" data-validation="required" >
									   <form:option value="">--选择类型--</form:option>
					                   <form:options items="${applicationScope.businessMemberTypeMap}"/>
					                 </form:select> 
								</c:when> 
								<c:when test="${memberTypeChangeForm.subMemberType == 'C'}"> 
									<form:select path="newTypeCode" cssClass="form-control" data-validation="required">
									   <form:option value="">--选择类型--</form:option>
					                   <form:options items="${applicationScope.customerMemberTypeMap}"/>
					                 </form:select> 
								</c:when> 
							</c:choose>                           
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="changeReason" class="col-md-3 control-label">变更原因：</form:label>
                        <div class="col-md-9">
                        	<form:textarea path="changeReason" cssClass="form-control" rows="3" data-validation="required"/>
                        </div>
                    </div>                    
                    <div class="row myrow">
						<div class="col-md-12" style="text-align: center">
							<button name="submitBtn" type="submit" class="btn btn-success">提交申请</button>
						</div>
					</div>
                </form:form>
            </div>        
	</div>
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript">
		var businessHtml = "";
		$(document).ready(function() {
			 $.validate();	    	    	 
	    	 
	    	 $(".openEditPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ 
	 	    	document.forms[0].submit();
	 	    }});
	 	    
	 	    $(".openViewPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ } });
	 	    
	 	    var currentTypeCode = $("#currentTypeCode").val();	 	   
	 	    $('#newTypeCode > option[value=' + currentTypeCode + ']').prop('disabled',true); 	    

		});
		
		//Validte if category selected.
		$("form").submit(function(e) {			 
		 	var typeName = $('#newTypeCode option:selected').text();
		 	$('#newTypeName').val(typeName);	        
	    });		
	</script>

</body>
</html>