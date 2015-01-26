<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>增加/修改服务产品</title>
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
						<c:if test="${serviceProduct.operate=='add'}">
						增加服务产品
					   	</c:if>
						<c:if test="${serviceProduct.operate=='update'}">
						修改服务产品
						</c:if>
					</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty serviceProduct.feedbackMessage}">
						<div class="alert alert-success">${serviceProduct.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			<c:if test="${serviceProduct.operate=='add'}">
				<c:set var="actionurl" value="create.html"></c:set>
			</c:if>
			<c:if test="${serviceProduct.operate=='update'}">
				<c:set var="actionurl" value="update.html"></c:set>
			</c:if>

			<form:form id="myform" modelAttribute="serviceProduct" method="post"
				action="${actionurl}" cssClass="form-inline" role="form"
				enctype="multipart/form-data">
				<form:hidden path="created_by" />
				<form:hidden path="operate" />
				<form:hidden path="service_id" />
				<c:if test="${serviceProduct.operate=='add'}">
					<form:hidden path="member_id" />
				</c:if>				
				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="service_title"
									cssClass="col-md-2 control-label">产品名称<font
										color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="service_title"
										cssClass="form-control input-sm" placeholder="产品名称"
										maxlength="120" readonly="${serviceProduct.operate=='update'}"
										data-validation="required"  />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="member_id" cssClass="col-md-2 control-label">服务商会员ID<font
										color="red">*</font>:</form:label>
								<c:if test="${serviceProduct.operate=='add'}">
									<div class="col-md-1 item-content" style="display:none" >
										<p id="p_member_id"></p>
									</div>
									<div class="col-md-2 item-content" >
										<a class="openViewPage btn btn-default btn-sm" href="../dialog/businessmember.html">浏览服务商会员...</a>																														
									</div>
									<div class="col-md-6 item-content"><div id="alertMessageDiv1"></div></div>																	
							   	</c:if>
								<c:if test="${serviceProduct.operate=='update'}">
									<div class="col-md-8 item-content">
										<form:input path="member_id" cssClass="form-control input-sm" placeholder="服务商会员ID" maxlength="120"
											readonly="${serviceProduct.operate=='update'}" />
									</div>
								</c:if>							
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="category_id"
									cssClass="col-md-2 control-label">产品分类<font
										color="red">*</font>:</form:label>
								<div class="col-md-8 item-content" id="categoryListDiv">
									<c:forEach items="${memberCategoryMap}" var="var" varStatus="rowCounter">
										<form:label path="category_id" class="radio-inline">
									  		<form:radiobutton path="category_id" name="inlineRadioOptions" value="${var.key}"/> ${var.value}
										</form:label>										
									</c:forEach>
									
								</div>
								<div class="col-md-8 item-content" ><div id="alertMessageDiv2"></div></div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="face_negotiable" cssClass="col-md-2 control-label">是否面议:</form:label>
								<div class="col-md-8 item-content">
									<form:label path="face_negotiable" class="radio-inline">
									  		<form:radiobutton path="face_negotiable" name="inlineRadioOptions" value="true" />面议
									</form:label>
									<form:label path="face_negotiable" class="radio-inline">
									  		<form:radiobutton path="face_negotiable" name="inlineRadioOptions" value="false" />不面议
									</form:label>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="basic_price" cssClass="col-md-2 control-label">起步价:</form:label>
								<div class="col-md-8 item-content input-group">
									<span class="input-group-addon">$</span>
									<form:input path="basic_price" cssClass="form-control input-sm"
										placeholder="起步价" maxlength="120"
										readonly="${serviceProduct.face_negotiable==true}"
										data-validation="number" data-validation-allowing="float" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="unit_price" cssClass="col-md-2 control-label">单价:</form:label>
								<div class="col-md-8 item-content input-group ">
									<span class="input-group-addon">$</span>
									<form:input path="unit_price" cssClass="form-control input-sm" placeholder="单价" maxlength="120" readonly="${serviceProduct.face_negotiable==true}" 
										data-validation="number" data-validation-allowing="float"/>
									<span class="input-group-addon"> / </span>
									<form:select path="unit_id" cssClass="form-control input-sm" >
										<form:options items="${applicationScope.serviceUnitMap}" />
									</form:select>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="tax_included" cssClass="col-md-2 control-label">是否含税:</form:label>
								<div class="col-md-8 item-content">
									<form:label path="tax_included" class="radio-inline">
									  		<form:radiobutton path="tax_included" name="inlineRadioOptions" value="true" />包含
									</form:label>
									<form:label path="tax_included" class="radio-inline">
									  		<form:radiobutton path="tax_included" name="inlineRadioOptions" value="false" />不含
									</form:label>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="gstRate" cssClass="col-md-2 control-label">GST:</form:label>
								<div class="col-md-2 item-content input-group">
									<form:input path="gstRate" cssClass="form-control input-sm" maxlength="120" readonly="${serviceProduct.tax_included==true}"
										data-validation="number" data-validation-allowing="float"/>
									<span class="input-group-addon">%</span>
								</div>
								<form:label path="pstRate" cssClass="col-md-1 control-label">PST:</form:label>
								<div class="col-md-2 item-content input-group">
									<form:input path="pstRate" cssClass="form-control input-sm" maxlength="120" readonly="${serviceProduct.tax_included==true}"
										data-validation="number" data-validation-allowing="float"/>
									<span class="input-group-addon">%</span>
								</div>								
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="serviceAreaList"
									cssClass="col-md-2 control-label">服务区域<font
										color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<div class="col-md-12 item-content">
										<form:label path="serviceAreaList" class="checkbox-inline">
											<form:checkbox id="serviceAreaAll" path="" value="all" />以下所有区域
										</form:label>
									</div>
									<c:forEach items="${applicationScope.cityMap}" var="var" varStatus="rowCounter">
										<div class="col-md-3 item-content">
											<form:label path="serviceAreaList" class="checkbox-inline">
												<form:checkbox path="serviceAreaList" value="${var.key}"/>${var.value}
											</form:label>
										</div>
									</c:forEach>
									<div class="col-md-10 item-content" ><div id="alertMessageDiv3"></div></div>
								</div>
								
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="cover_img"
									cssClass="col-md-2 control-label">封面图片:</form:label>
								<div class="col-md-8 item-content">									
									<div id="insertHtml2" ><img alt="cover image" src="${serviceProduct.cover_img}" height="150" width="150"></div>																				
									<form:hidden path="cover_img"/>
									<a class="openViewPage btn btn-default btn-sm" href="medialib.html">插入图片</a>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="description" cssClass="col-md-2 control-label">产品描述:</form:label>
								<div class="col-md-8 item-content">
									<form:textarea path="description" cssClass="form-control input-sm editbox" placeholder="产品描述" rows="6" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="warrant" cssClass="col-md-2 control-label">质保承诺:</form:label>
								<div class="col-md-8 item-content">
									<form:textarea path="warrant" cssClass="form-control input-sm " placeholder="质保承诺" rows="3" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="meta_keywords" cssClass="col-md-2 control-label">搜索关键字:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="meta_keywords" cssClass="form-control input-sm" placeholder="SEO关键字" maxlength="120"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="meta_desc" cssClass="col-md-2 control-label">搜索描述:</form:label>
								<div class="col-md-8 item-content">
									<form:textarea path="meta_desc" cssClass="form-control input-sm " placeholder="被搜索引擎搜出来后，显示给用户的简要描述" rows="3" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="status_id" cssClass="col-md-2 control-label">状态:</form:label>
								<div class="col-md-8 item-content">
									<c:forEach items="${applicationScope.serviceStatusMap}" var="var" varStatus="rowCounter">										
										<form:label path="status_id" class="radio-inline">
									  		<form:radiobutton path="status_id" name="inlineRadioOptions" value="${var.key}"/> ${var.value}
										</form:label>										
									</c:forEach>								
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="recommend_level_id" cssClass="col-md-2 control-label">推荐级别:</form:label>
								<div class="col-md-8 item-content">
									<c:forEach items="${applicationScope.recommendLevelMap}" var="var" varStatus="rowCounter">
										<form:label path="recommend_level_id" class="radio-inline">
									  		<form:radiobutton path="recommend_level_id" name="inlineRadioOptions" value="${var.key}"/> ${var.value}
										</form:label>										
									</c:forEach>
								</div>
							</div>
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
				<div class="row myrow">
					<div class="col-md-12" style="text-align: center">
						<c:if test="${serviceProduct.operate=='add'}">
							<button type="submit" class="btn btn-success">创建</button>
						</c:if>
						<c:if test="${serviceProduct.operate=='update'}">
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
			$(".openEditPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ 
	 	    	document.forms[0].submit();
	 	    }});
	 	    
	 	    $(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ } });
	 	    
	 	   var imgstr = $('#cover_img').val(); 
	 	   if(imgstr == null || imgstr == ""){
	 		  $("#insertHtml2").css('display','none');
	 	   }else{
	 		  $("#insertHtml2").css('display','block');
	 	   }

	 	   $.validate();
	 	   
		});
		
		//Ajax call to update category radio buttons when user selects a different member id.
		$('#member_id').on('change', function (e) {
		    var optionSelected = $("option:selected", this);
		    var member_id = this.value;
		    $('#p_member_id').text(member_id);
		    $('#p_member_id').parent().css( 'display', 'block' );
		    $.get("../serviceproduct/getMemberCategory.json?member_id=" + member_id, function(data,status){
		    	
		    	$("#categoryListDiv").empty();
		    	
 		    	for(i=0; i<data.length; i++){
 					var categoryId=data[i].category_id;
 					var categoryName=data[i].category_name;					
 					var appendhtml1="<label for=\"category_id\" class=\"radio-inline\">";
 					var appendhtml2="<input id=\"category_id" + i + "\" name=\"category_id\" name=\"inlineRadioOptions\" type=\"radio\" value=\"" + categoryId + "\"/>" + categoryName;
 					var appendhtml3="</label>";					
										
					var appendhtml= appendhtml1 + appendhtml2 + appendhtml3;											
									
 					$("#categoryListDiv").append(appendhtml);
 					$("#alertMessageDiv1").empty();
 					$("#alertMessageDiv2").empty();
 				 }
		    });	
		});
		
		//Validte funtion.
		$("form").submit(function(e) {
			var length =  $("#alertMessageDiv1").length;
			var member_id = $("#member_id").val();			 
		 	
			if(length > 0 && member_id == ""){//Validte member is selected.
				var message = "请选择服务商会员";				
				showAlert("alertMessageDiv1", "danger", message);
				e.preventDefault();
			}else if (!$("input[name='category_id']:checked").val()) {// Validate category is selected.
		 		var message = "请选择产品分类";				
				showAlert("alertMessageDiv2", "danger", message);
				e.preventDefault();
		 	}
			
			if (!$("input[name='serviceAreaList']:checked").val()) {// Validate service area is selected.
		 		var message = "请选择服务区域";				
				showAlert("alertMessageDiv3", "danger", message);
				e.preventDefault();
		 	}else {
		 		$("#alertMessageDiv3").empty();
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
		
		//check or uncheck all service area will select or unselect all citites.
		$('#serviceAreaAll').change(function() {
			var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("input:checkbox[name=serviceAreaList]").prop("checked", true);		    	
		    } else {
		    	$("input:checkbox[name=serviceAreaList]").prop("checked", false);		        
		    }
		});

		$("input:checkbox[name=serviceAreaList]").change(function() {
			var $this = $(this);
		    if (!$this.is(':checked')) {	    
		    	$('#serviceAreaAll').prop("checked", false);		        
		    }
		});
		
		$('#face_negotiable1').change(function() {
			var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("input:text[name=basic_price]").prop("readonly", true);
		    	$("input:text[name=unit_price]").prop("readonly", true);		    			        
		    }
		});
		
		$('#face_negotiable2').change(function() {
			var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("input:text[name=basic_price]").prop("readonly", false);
		    	$("input:text[name=unit_price]").prop("readonly", false);		    			        
		    }
		});
		
		$('#tax_included1').change(function() {
			var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("input:text[name=gstRate]").prop("readonly", true);
		    	$("input:text[name=pstRate]").prop("readonly", true);		    	
		    } 
		});
		
		$('#tax_included2').change(function() {
			var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("input:text[name=gstRate]").prop("readonly", false);
		    	$("input:text[name=pstRate]").prop("readonly", false);		    	
		    } 
		});
		
	</script>
</body>
</html>