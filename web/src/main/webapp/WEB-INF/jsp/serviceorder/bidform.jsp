<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>Bid订单</title>
<%@ include file="/WEB-INF/inc/simplehead.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css">
<style type="text/css">

.form-control_inline {
	display: inline-block;	
}
</style>
</head>
<body>
	<div class="container">
		<!-- Contact Information -->		
		<div class="col-sm-9">      
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
				<form:hidden path="unit_quantity" />
				<form:hidden path="tax_included" />
				<form:hidden path="gst_rate" />
				<form:hidden path="pst_rate" />
				<form:hidden path="warrant" />
				<form:hidden path="business_name" />				
			<div class="row">
	           <div class="col-sm-12">
		           <div class="form-group">	             
		              <h2 style="text-align:left; padding:10px">Bid订单</h2>	             
		           </div>
	           </div>
          	 </div>          	 
			 <div class="row myrow">						
				<div class="col-sm-12">
					<div class="form-group">
						<div class="col-sm-10">
						  <c:set var="status_id" value="${serviceOrder.status_id}"/>
						    订单ID: ${serviceOrder.order_id}	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单状态： ${applicationScope.orderStatusMap[status_id]} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下单时间: ${serviceOrder.orderDate} ${serviceOrder.orderTime}
						</div>
					</div>
				</div>																
			  </div>			  
              <div class="row">
                  <div class="col-sm-12">
					<h3>联系方式</h3>
                  </div>
              </div>
              <div class="col-sm-12">
                  <div class="row">
                      <div class="col-sm-12">
                          <div class="form-group">
                              <label class="col-sm-2 control-label">姓名：</label>
                              <div class="col-sm-10 form-control-static">
                                  <div class="col-sm-12">
	                                  <form:hidden path="last_name" />
	                                  <form:hidden path="first_name" />
                                      <label>${serviceOrder.last_name}, ${serviceOrder.first_name}</label>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
				  <div class="row">
                      <div class="col-sm-12">
                          <div class="form-group">
                              <label class="col-sm-2 control-label">联系电话：</label>
                              <div class="col-sm-10">
                                  <div class="col-sm-12" class="form-control">
                                  	  <form:hidden path="cell" />
                                  	  <label>${serviceOrder.cell}</label>                                      
                                  </div>                                  
                              </div>
                          </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-12">
                          <div class="form-group">
                              <label class="col-sm-2 control-label">电子邮箱：</label>
                              <div class="col-sm-10">
                                  <div class="col-sm-12" class="form-control">
                                  	<form:hidden path="email" />
                                  	<label>${serviceOrder.email}</label>                                      
                                  </div>                                  
                              </div>
                          </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-12">
                          <div class="form-group">
                              <label class="col-sm-2 control-label">联系地址：</label>
                              <div class="col-sm-10">                                   
                                  <div class="col-sm-12" class="form-control">
                                      <form:hidden path="city" />
	                                  <form:hidden path="address" />
                                  	  <form:hidden path="postcode" />
                                  		<c:set var="city" value="${serviceOrder.city}"/>
                                      <label>${serviceOrder.address} ${applicationScope.cityMap[city]} ${serviceOrder.postcode}</label>
                                  </div>                     
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-12">
                      <h3>
                          	订单信息
                      </h3>
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
				         	<p id="p_basic_price">起步价：  $<form:input data-validation='number' data-validation-allowing="float" class='input-sm' path="basePrice" size="3" onchange='changePrices(event)' /></p>
				       </td>
				       <td>
				       		<div class="row myrow">
				       			<p id="p_unit_price">
				       			单价： $<form:input data-validation='number' data-validation-allowing="float" class='input-sm' path="unitPrice" size="3" onchange='changePrices(event)'  /> / ${serviceOrder.unit_name} * ${serviceOrder.unit_quantity}
				       			</p>
				       		</div>				         	 
				       </td>
				       <td class="text-right">
				         	<p id="p_priceBeforeTax">共计：  $${serviceOrder.priceBeforeTax}</p>
				       </td>   
      
				      </tr>	
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
              <div class="row">
                  <div class="col-sm-12">
                      <h3>质保承诺</h3>
                  </div>
                  <div class="col-sm-12">
                      <p id="p_warrant">${serviceOrder.warrant}</p>
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-12">
                      <h3>服务时间</h3>
                  </div>
              </div>
				<c:forEach items="${serviceOrder.serviceScheduleList}" var="var" varStatus="rowCounter">
					<div class="row">
						<div class="col-sm-12">
							<form:hidden path="serviceScheduleList[${rowCounter.index}].order_id"/>
							<form:hidden path="serviceScheduleList[${rowCounter.index}].id"/>
							<form:hidden path="serviceScheduleList[${rowCounter.index}].servicetime"/>
							<form:hidden path="serviceScheduleList[${rowCounter.index}].selected"/>
							<fmt:formatDate value="${var.servicetime}" var="datePartial" pattern="MM/dd/YYYY"/>
							<fmt:formatDate value="${var.servicetime}" var="timePartial" pattern="h:mm a"/>
	               			<div class="form-inline">			
	                			<form:label path="selectedServiceTimeIndex" class="radio-inline">
										<form:radiobutton path="selectedServiceTimeIndex" value="${rowCounter.index}"/>
								</form:label>
								<form:input path="serviceScheduleList[${rowCounter.index}].datePartial" value = "${datePartial}" cssClass="form-control_inline input-sm" id="datepicker${rowCounter.index}" placeholder="日期" 
	               					data-validation-optional="true" data-validation="date" data-validation-format="MM/dd/YYYY" />
							    <form:input path="serviceScheduleList[${rowCounter.index}].timePartial" value = "${timePartial}" size="5" cssClass="input-sm form-control_inline" id="timepicker${rowCounter.index}" placeholder="时间" 
							    	data-validation-optional="true" data-validation="time" />
							</div>									
						</div>
					</div>							
				</c:forEach>						
				<div class="row">
						<div class="col-sm-12">
							<%-- <form:label path="selectedServiceTimeIndex" class="radio-inline">
								<form:radiobutton path="selectedServiceTimeIndex" name="servicetimeradios" value="3"/> 不选择
							</form:label> --%>
							<div id="alertMessageDiv2"></div>
						</div>
				</div>	
                
                <div class="row">
                    <div class="col-sm-12">
                        <h3>定制需求</h3>
                        <div class="form-group">
                            <div class="col-xs-12 col-md-12">
                            	<form:hidden path="requirement" />
                            	<p>${serviceOrder.requirement}</p>                                
                            </div>
                        </div>
                    </div>
                </div>
 				<div class="row">
                    <div class="col-sm-12">
                        <div class="form-group" id="mima">
                            <label for="password" class="col-sm-3 control-label"></label>
                            <div class="col-sm-8">
                                <button type="submit" class="btn btn-primary btn-lg btn-block">提交订单</button>
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
			}else{
				var checked = $('input[name=selectedServiceTimeIndex]:checked').val();
				if(!checked){
					var message = "请选择服务日期和时间";				
					showAlert("alertMessageDiv2", "danger", message);
					e.preventDefault();
				}else {
		 			$("#alertMessageDiv2").empty();
				}
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
		
		function changePrices(e){
			var unit_price = $('#unitPrice').val();
 			var service_id = $('#service_id').val();
 			var basic_price = $('#basePrice').val();
 			var unit_quantity = $('#unit_quantity').val();
 			
			if($.isNumeric(basic_price) && $.isNumeric(unit_price) && $.isNumeric(unit_quantity) && Math.floor(unit_quantity) == unit_quantity){
		    	
				$.get("../serviceorder/getServiceProductByPrices.json?basic_price=" + basic_price + 
		    			"&service_id=" + service_id + 
		    			"&unit_price=" + unit_price + 
		    			"&unit_quantity=" + unit_quantity, 
		    		function(data,status){
			    		writeServiceProductData(data);	    			    	
			    	}
		    	);
		    	
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
	    	$('#p_basic_price').html('起步价：  $' + "<input id='basePrice' name='basePrice' data-validation-allowing='float' data-validation='number' class='input-sm' onchange='changePrices(event)' type='text' value='" + data.basePrice + "' size='3'/>");
	    			
	    				    	
	    	$('#p_unit_price').html('单价： $' + "<input data-validation='number' data-validation-allowing='float' type='text' id='unitPrice' name='unitPrice' class='input-sm' value='" + data.unitPrice + "' size='3' onchange='changePrices(event)'/>" + ' / ' + data.unit_name + ' * ' + data.unit_quantity);    			
	    	
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