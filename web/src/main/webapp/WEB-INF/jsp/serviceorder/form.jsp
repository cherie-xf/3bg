<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>增加/修改订单</title>
<%@ include file="/WEB-INF/inc/simplehead.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css">
<style type="text/css">
.checkboxdiv {
	display: inline-block;
	width: 120px;
}
.form-control_inline {
	display: inline-block;	
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
						<c:if test="${serviceOrder.operate=='add'}">
						新建订单
					   	</c:if>
					   	<c:if test="${serviceOrder.operate=='copy'}">
						复制订单
					   	</c:if>
						<c:if test="${serviceOrder.operate=='update'}">
						订单详情
						</c:if>
					</h4>
				</div>
			</div>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty serviceOrder.feedbackMessage}">
						<div class="alert alert-success">${serviceOrder.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			<c:if test="${serviceOrder.operate=='add'}">
				<c:set var="actionurl" value="create.html"></c:set>
			</c:if>
			<c:if test="${serviceOrder.operate=='copy'}">
				<c:set var="actionurl" value="copy.html"></c:set>
			</c:if>
			<c:if test="${serviceOrder.operate=='update'}">
				<c:set var="actionurl" value="update.html"></c:set>
			</c:if>

			<form:form id="myform" modelAttribute="serviceOrder" method="post"
				action="${actionurl}" cssClass="form-horizontal" role="form"
				enctype="multipart/form-data">
				<form:hidden path="created_by" />
				<form:hidden path="operate" />
				<form:hidden path="order_id" />
				<form:hidden path="user_id" />
				<form:hidden path="customer_member_id" />
				<form:hidden path="business_member_id" />				
				<form:hidden path="service_id" />
				<form:hidden path="service_title" />
				<form:hidden path="cover_img" />
				<form:hidden path="face_negotiable" />
				<form:hidden path="basic_price" />
				<form:hidden path="unit_price" />
				<form:hidden path="unit_id" />
				<%-- <form:hidden path="unit_quantity" /> --%>
				<form:hidden path="tax_included" />
				<form:hidden path="gst_rate" />
				<form:hidden path="pst_rate" />
				<form:hidden path="warrant" />
				<form:hidden path="business_name" />
				<div class="row myrow">
					<div class="col-md-12">
						<c:if test="${serviceOrder.operate=='update'}">
							<div class="row myrow">						
								<div class="col-sm-12">
									<div class="form-group">
										<label for="last_name" class="col-sm-2 control-label"></label>
										<div class="col-sm-10">
											订单ID: ${serviceOrder.order_id}	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下单时间: ${serviceOrder.orderDate} ${serviceOrder.orderTime}
										</div>
									</div>
								</div>																
							</div>						
						</c:if>
						<div class="row myrow">
							<div class="col-sm-12">
								<div class="form-group">
									<label for="status_id" class="col-sm-2 control-label">订单状态</label>
									<div class="col-sm-8">
										<form:select path="status_id" cssClass="form-control input-sm" >
											<form:options items="${applicationScope.orderStatusMap}" />
										</form:select>	
									</div>
								</div>
							</div>
						</div>						
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="user_id" cssClass="col-md-2">确认联系方式</form:label>
								<c:if test="${serviceOrder.operate eq 'add' or serviceOrder.operate eq 'copy'}">
									<div class="col-md-4 item-content" id="categoryListDiv">
										<a class="openViewPage btn btn-default btn-sm" 
										href="../dialog/chooseuser.html">浏览用户...</a>
									</div>
							   	</c:if>								
							</div>
						</div>
						<div class="row myrow">						
							<div class="col-sm-6">
								<div class="form-group">
									<label for="last_name" class="col-sm-4 control-label">姓<font color="red">*</font></label>
									<div class="col-sm-4">
										<form:input path="last_name" class="form-control" placeholder="last name" data-validation="required"/>
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="first_name" class="col-sm-4 control-label">名<font color="red">*</font></label>
									<div class="col-sm-4">
										<form:input path="first_name" class="form-control" placeholder="first name" data-validation="required"/>
									</div>
								</div>
							</div>							
						</div>
						<div class="row myrow">
							<div class="col-sm-12">
								<div class="form-group">
									<label for="email" class="col-sm-2 control-label">邮箱<font color="red">*</font></label>
									<div class="col-sm-8">
										<form:input path="email" id="email" class="form-control" placeholder="email" 
										data-validation="email"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-sm-12">
								<div class="form-group">
									<label for="cell" class="col-sm-2 control-label">手机<font color="red">*</font></label>
									<div class="col-sm-8">
										<form:input path="cell" class="form-control" placeholder="cell" 
										data-validation-allowing="int" data-validation="number" maxlength="15"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-sm-12">
								<div class="form-group">
									<label for="address" class="col-sm-2 control-label">联系地址</label>
									<div class="col-sm-8">
										<form:input path="address" class="form-control" placeholder="address" />
									</div>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-sm-6">
								<div class="form-group">
									<label for="city" class="col-sm-4 control-label">城市</label>
									<div class="col-sm-6">
										<form:select path="city" items="${applicationScope.cityMap}" class="form-control">
										</form:select>
									</div>							
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="postcode" class="col-sm-4 control-label">邮编</label>
									<div class="col-sm-4">
										<form:input path="postcode" class="form-control" placeholder="postal code"/>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="service_id"
									cssClass="col-md-2 ">订单信息<font color="red">*</font></form:label>
								<c:if test="${not(serviceOrder.operate eq 'update')}">
									<div class="col-md-2 item-content" >
										<a class="openViewPage btn btn-default btn-sm" 
											href="../dialog/serviceproduct.html">浏览服务产品...</a>
									</div>
									<div class="col-md-8 item-content" ><div id="alertMessageDiv1"></div></div>
								</c:if>
							</div>
						</div>

					   <div class="row myrow">
						   <div class="col-md-12">
							 <table class="table">
							      <tr valign="middle">  
						           <td>
						           	<img id="covImg" alt="cover image" src="${serviceOrder.cover_img}" height="60" width="60">		           	 
						           </td>
							       <td>
							       		<ul class="list-unstyled">
								       		<li><p id="p_service_id">产品ID: ${serviceOrder.service_id}</p></li>
								       		<li><p id="p_service_title">${serviceOrder.service_title}</p></li>
							       		</ul>								       							         	
							       </td>
							       <td>
							         	<p id="p_basic_price">起步价：  $${serviceOrder.basePrice}</p>
							       </td>
							       <td>
							       		<div class="row myrow">
							       			<p id="p_unit_price">
							       			单价： $${serviceOrder.unitPrice} / ${serviceOrder.unit_name} * <form:input data-validation='number' data-validation-allowing='int, range[0;99999]' class='input-sm' path="unit_quantity" size="3" maxlength="5" onchange='changeUnitQuantity(event)'/>
							       			</p>
							       		</div>
							         	 
							       </td>
							       <td class="text-right">
							         	<p id="p_priceBeforeTax">共计：  $${serviceOrder.priceBeforeTax}</p>
							       </td>   
		       
							      </tr>	
<%-- 							      <c:if test="${serviceOrder.tax_included == false}"> --%>
							      	<tr>  
							           <td>
							           	GST:		           	 
							           </td>
								       <td></td>
								       <td></td>
								       <td></td>
								       <td class="text-right">
								         <p id="p_gstTax">$${serviceOrder.gstTax}</p>
								       </td>	       
							      	</tr>
							      	<tr>  
							           <td>
							           	PST:		           	 
							           </td>
								       <td></td>
								       <td></td>
								       <td></td>
								       <td class="text-right">
								          <p id="p_pstTax">$${serviceOrder.pstTax}</p>
								       </td>	       
							      	</tr>
<%-- 							      </c:if> --%>
							      	<tr>  
							           <td>
							           	<b>合计:</b>		           	 
							           </td>
								       <td></td>
								       <td></td>
								       <td></td>
								       <td class="text-right">
								         <p id="p_priceTotal"><b>$${serviceOrder.priceTotal}</b></p>
								       </td>	       
							      	</tr>						    
							  </table> 
						   </div> 
					   </div>						
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="warrant"
									cssClass="col-md-2 ">质保承诺</form:label>																
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-6">
								<p id="p_warrant">${serviceOrder.warrant}</p>							
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="serviceScheduleList"
									cssClass="col-md-2 ">服务时间<font color="red">*</font></form:label>																								
							</div>
						</div>
						<c:forEach items="${serviceOrder.serviceScheduleList}" var="var" varStatus="rowCounter">
							<div class="row myrow">
								<div class="col-md-12">
									<form:hidden path="serviceScheduleList[${rowCounter.index}].order_id"/>
									<form:hidden path="serviceScheduleList[${rowCounter.index}].id"/>
									<form:hidden path="serviceScheduleList[${rowCounter.index}].servicetime"/>
									<form:hidden path="serviceScheduleList[${rowCounter.index}].selected"/>
									<fmt:formatDate value="${var.servicetime}" var="datePartial" pattern="MM/dd/YYYY"/>
									<fmt:formatDate value="${var.servicetime}" var="timePartial" pattern="h:mm a"/>
		                			<div class="form-inline">			
			                			<form:label path="selectedServiceTimeIndex" class="radio-inline">
												<form:radiobutton path="selectedServiceTimeIndex" name="servicetimeradios" value="${rowCounter.index}"/>
										</form:label>											                				                			
		                				<form:input path="serviceScheduleList[${rowCounter.index}].datePartial" value = "${datePartial}" cssClass="form-control_inline input-sm" id="datepicker${rowCounter.index}" placeholder="日期" 
		                					data-validation-optional="true" data-validation="date" data-validation-format="MM/dd/YYYY"/>
									    <form:input path="serviceScheduleList[${rowCounter.index}].timePartial" value = "${timePartial}" size="5" cssClass="input-sm form-control_inline" id="timepicker${rowCounter.index}" placeholder="时间" 
									    	data-validation-optional="true" data-validation="time"/>
									</div>									
								</div>
							</div>							
						</c:forEach>						
						<div class="row myrow">
								<div class="col-md-12">
									<form:label path="selectedServiceTimeIndex" class="radio-inline">
										<form:radiobutton path="selectedServiceTimeIndex" name="servicetimeradios" value="3"/> 不选择
									</form:label>
									<div id="alertMessageDiv2"></div>
								</div>
						</div>						
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="requirement"
									cssClass="col-md-2">定制需求</form:label>																
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-10">
								<form:textarea path="requirement" cssClass="form-control input-sm" placeholder="定制需求" rows="6" />																
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
								<c:if test="${serviceOrder.operate=='add'}">
									<button type="submit" class="btn btn-success">新建</button>
								</c:if>
								<c:if test="${serviceOrder.operate=='copy'}">
									<button type="submit" class="btn btn-success">复制</button>
								</c:if>
								<c:if test="${serviceOrder.operate=='update'}">
									<button type="submit" class="btn btn-success">修改</button>
								</c:if>
								<button type="reset" class="btn btn-warning">重填</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<!-- contact over -->

	</div>
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script src="/js/ui/jquery.ui.timepicker.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymceinit.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("input[id^='datepicker']").datepicker();			
			$("input[id^='timepicker']").timepicker({'timeFormat': 'g:i A'});			
			
			$(".openEditPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ 
	 	    	document.forms[0].submit();
	 	    }});
	 	    
	 	    $(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ } });
	 	    
	 	   $.validate();
		}); 
		
		
		//Validte funtion.
		$("form").submit(function(e) {
			var length =  $("#alertMessageDiv1").length;
			var service_id = $("#service_id").val();			 
		 	
			if(length > 0 && service_id == ""){//Validte service prodcut is selected.
				var message = "请选择服务产品";				
				showAlert("alertMessageDiv1", "danger", message);
				e.preventDefault();
			}else {
		 		$("#alertMessageDiv1").empty();
		 	}
			
			
			if($("#alertMessageDiv2").length > 0 && ($("input[id^='datepicker']").val() == "" || $("input[id^='timepicker']").val() == "")){
				var message = "请设置服务日期和时间";				
				showAlert("alertMessageDiv2", "danger", message);
				e.preventDefault();
			}else {
		 		$("#alertMessageDiv2").empty();
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
		
		//Ajax call to update user info when selecting a user.
 		$('#user_id').on('change', function (e) {
		    //var optionSelected = $("option:selected", this);
		    var user_id = this.value;
		    $.get("../serviceorder/getUserAndCMember.json?user_id=" + user_id, function(data,status){
		    	
		    	$("#customer_member_id").val(data.customer_member_id);
		    	$("#last_name").val(data.last_name);
		    	$("#first_name").val(data.first_name);
		    	$("#email").val(data.email);
		    	$("#cell").val(data.cell);
		    	$("#address").val(data.address);
		    	$("#city").val(data.city);
		    	$("#postcode").val(data.postcode);    	
		    });	
		}); 		
		
		//Ajax call to update service product info when selecting a service product.
 		$('#service_id').on('change', function (e) {
 			var unit_quantity = 0;
		    var service_id = this.value;
		    if($.isNumeric(unit_quantity) && Math.floor(unit_quantity) == unit_quantity){
		    	
		    	$.get("../serviceorder/getServiceProduct.json?unit_quantity=" + unit_quantity + "&service_id=" + service_id, function(data,status){
			    	writeServiceProductData(data);	    			    	
			    });
		    	
		    } 
		    
		});
		
		function changeUnitQuantity(e){
			var unit_quantity = e.target.value;
 			var service_id = $('#service_id').val();
 			
			if($.isNumeric(unit_quantity) && Math.floor(unit_quantity) == unit_quantity){
		    	
		    	$.get("../serviceorder/getServiceProduct.json?unit_quantity=" + unit_quantity + "&service_id=" + service_id, function(data,status){
			    	writeServiceProductData(data);	    			    	
			    });
		    	
		    };	
		}
		
		$("input[name='servicetimeradios']").change(function(){
		    // Do something interesting here
		});
 		
		function writeServiceProductData(data){
			$("#business_member_id").val(data.business_member_id);
	    	$("#service_id").val(data.service_id);
	    	$("#service_title").val(data.service_title);
	    	$("#cover_img").val(data.cover_img);
	    	$("#face_negotiable").val(data.face_negotiable);
	    	$("#basic_price").val(data.basic_price);
	    	$("#unit_price").val(data.unit_price);
	    	$("#unit_id").val(data.unit_id);
	    	$("#tax_included").val(data.tax_included);
	    	$("#gst_rate").val(data.gst_rate);
	    	$("#pst_rate").val(data.pst_rate);
	    	$("#warrant").val(data.warrant);
	    	$("#business_name").val(data.business_name);
	    	
	    	//$('#unit_quantity').val(data.unit_quantity);
	    	
	    	$('#p_service_id').text('产品ID: ' + data.service_id);
	    	$('#p_service_title').text(data.service_title);
	    	$('#p_basic_price').text('起步价：  $' + data.basePrice);
	    	$('#p_unit_price').html('单价： $' + data.unitPrice + ' / ' + data.unit_name + ' * ' +
	    			"<input data-validation='number' data-validation-allowing='int, range[0;99999]' type='text' id='unit_quantity' name='unit_quantity' class='input-sm' value='" + data.unit_quantity + "' size='3' maxlength='5' onchange='changeUnitQuantity(event)'/>");	    			
	    	
	    	$('#p_priceBeforeTax').text('共计： $' + data.priceBeforeTax);
	    	
	    	$('#p_gstTax').text('$' + data.gstTax);
		    $('#p_pstTax').text('$' + data.pstTax);
	    	
	    	$('#p_priceTotal').html('<b>$' + data.priceTotal + '</b>');
	    	$('#covImg').attr("src", data.cover_img);
	    	$('#p_warrant').text(data.warrant);
		}	

		
	</script>
</body>
</html>